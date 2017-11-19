package com.iflytek.speech.aidl;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.iflytek.speech.GrammarListener;
import com.iflytek.speech.GrammarListener.Stub;
import com.iflytek.speech.LexiconListener;
import com.iflytek.speech.LexiconListener.Stub;
import com.iflytek.speech.RecognizerListener;
import com.iflytek.speech.RecognizerListener.Stub;

public abstract interface ISpeechRecognizer
  extends IInterface
{
  public abstract void buildGrammar(Intent paramIntent, GrammarListener paramGrammarListener)
    throws RemoteException;
  
  public abstract void cancel(RecognizerListener paramRecognizerListener)
    throws RemoteException;
  
  public abstract boolean isListening()
    throws RemoteException;
  
  public abstract void startListening(Intent paramIntent, RecognizerListener paramRecognizerListener)
    throws RemoteException;
  
  public abstract void stopListening(RecognizerListener paramRecognizerListener)
    throws RemoteException;
  
  public abstract void updateLexicon(Intent paramIntent, LexiconListener paramLexiconListener)
    throws RemoteException;
  
  public abstract void writeAudio(Intent paramIntent, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements ISpeechRecognizer
  {
    private static final String DESCRIPTOR = "com.iflytek.speech.aidl.ISpeechRecognizer";
    static final int TRANSACTION_buildGrammar = 5;
    static final int TRANSACTION_cancel = 3;
    static final int TRANSACTION_isListening = 4;
    static final int TRANSACTION_startListening = 1;
    static final int TRANSACTION_stopListening = 2;
    static final int TRANSACTION_updateLexicon = 6;
    static final int TRANSACTION_writeAudio = 7;
    
    public Stub()
    {
      attachInterface(this, "com.iflytek.speech.aidl.ISpeechRecognizer");
    }
    
    public static ISpeechRecognizer asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.iflytek.speech.aidl.ISpeechRecognizer");
      if ((localIInterface != null) && ((localIInterface instanceof ISpeechRecognizer))) {
        return (ISpeechRecognizer)localIInterface;
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
      Object localObject3 = null;
      Object localObject4 = null;
      Object localObject1 = null;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.iflytek.speech.aidl.ISpeechRecognizer");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeechRecognizer");
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
        }
        startListening((Intent)localObject1, RecognizerListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeechRecognizer");
        stopListening(RecognizerListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeechRecognizer");
        cancel(RecognizerListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeechRecognizer");
        boolean bool = isListening();
        paramParcel2.writeNoException();
        if (bool) {}
        for (paramInt1 = 1;; paramInt1 = 0)
        {
          paramParcel2.writeInt(paramInt1);
          return true;
        }
      case 5: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeechRecognizer");
        localObject1 = localObject2;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
        }
        buildGrammar((Intent)localObject1, GrammarListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeechRecognizer");
        localObject1 = localObject3;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
        }
        updateLexicon((Intent)localObject1, LexiconListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.iflytek.speech.aidl.ISpeechRecognizer");
      localObject1 = localObject4;
      if (paramParcel1.readInt() != 0) {
        localObject1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
      }
      writeAudio((Intent)localObject1, paramParcel1.createByteArray(), paramParcel1.readInt(), paramParcel1.readInt());
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class Proxy
      implements ISpeechRecognizer
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
      
      public void buildGrammar(Intent paramIntent, GrammarListener paramGrammarListener)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.iflytek.speech.aidl.ISpeechRecognizer");
            if (paramIntent != null)
            {
              localParcel1.writeInt(1);
              paramIntent.writeToParcel(localParcel1, 0);
              if (paramGrammarListener != null)
              {
                paramIntent = paramGrammarListener.asBinder();
                localParcel1.writeStrongBinder(paramIntent);
                this.mRemote.transact(5, localParcel1, localParcel2, 0);
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
      
      /* Error */
      public void cancel(RecognizerListener paramRecognizerListener)
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
        //   19: invokeinterface 73 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 55	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	com/iflytek/speech/aidl/ISpeechRecognizer$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   34: iconst_3
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 61 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 64	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 67	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 67	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_3
        //   64: invokevirtual 67	android/os/Parcel:recycle	()V
        //   67: aload_2
        //   68: invokevirtual 67	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	Proxy
        //   0	73	1	paramRecognizerListener	RecognizerListener
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
        return "com.iflytek.speech.aidl.ISpeechRecognizer";
      }
      
      public boolean isListening()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.iflytek.speech.aidl.ISpeechRecognizer");
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
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
      
      public void startListening(Intent paramIntent, RecognizerListener paramRecognizerListener)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.iflytek.speech.aidl.ISpeechRecognizer");
            if (paramIntent != null)
            {
              localParcel1.writeInt(1);
              paramIntent.writeToParcel(localParcel1, 0);
              if (paramRecognizerListener != null)
              {
                paramIntent = paramRecognizerListener.asBinder();
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
      
      /* Error */
      public void stopListening(RecognizerListener paramRecognizerListener)
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
        //   19: invokeinterface 73 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 55	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	com/iflytek/speech/aidl/ISpeechRecognizer$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   34: iconst_2
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 61 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 64	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 67	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 67	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_3
        //   64: invokevirtual 67	android/os/Parcel:recycle	()V
        //   67: aload_2
        //   68: invokevirtual 67	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	Proxy
        //   0	73	1	paramRecognizerListener	RecognizerListener
        //   3	65	2	localParcel1	Parcel
        //   7	57	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
      
      public void updateLexicon(Intent paramIntent, LexiconListener paramLexiconListener)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.iflytek.speech.aidl.ISpeechRecognizer");
            if (paramIntent != null)
            {
              localParcel1.writeInt(1);
              paramIntent.writeToParcel(localParcel1, 0);
              if (paramLexiconListener != null)
              {
                paramIntent = paramLexiconListener.asBinder();
                localParcel1.writeStrongBinder(paramIntent);
                this.mRemote.transact(6, localParcel1, localParcel2, 0);
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
      
      /* Error */
      public void writeAudio(Intent paramIntent, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 34
        //   14: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +68 -> 86
        //   21: aload 5
        //   23: iconst_1
        //   24: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   27: aload_1
        //   28: aload 5
        //   30: iconst_0
        //   31: invokevirtual 48	android/content/Intent:writeToParcel	(Landroid/os/Parcel;I)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 95	android/os/Parcel:writeByteArray	([B)V
        //   40: aload 5
        //   42: iload_3
        //   43: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   46: aload 5
        //   48: iload 4
        //   50: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   53: aload_0
        //   54: getfield 19	com/iflytek/speech/aidl/ISpeechRecognizer$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   57: bipush 7
        //   59: aload 5
        //   61: aload 6
        //   63: iconst_0
        //   64: invokeinterface 61 5 0
        //   69: pop
        //   70: aload 6
        //   72: invokevirtual 64	android/os/Parcel:readException	()V
        //   75: aload 6
        //   77: invokevirtual 67	android/os/Parcel:recycle	()V
        //   80: aload 5
        //   82: invokevirtual 67	android/os/Parcel:recycle	()V
        //   85: return
        //   86: aload 5
        //   88: iconst_0
        //   89: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   92: goto -58 -> 34
        //   95: astore_1
        //   96: aload 6
        //   98: invokevirtual 67	android/os/Parcel:recycle	()V
        //   101: aload 5
        //   103: invokevirtual 67	android/os/Parcel:recycle	()V
        //   106: aload_1
        //   107: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	108	0	this	Proxy
        //   0	108	1	paramIntent	Intent
        //   0	108	2	paramArrayOfByte	byte[]
        //   0	108	3	paramInt1	int
        //   0	108	4	paramInt2	int
        //   3	99	5	localParcel1	Parcel
        //   8	89	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	95	finally
        //   21	34	95	finally
        //   34	75	95	finally
        //   86	92	95	finally
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\speech\aidl\ISpeechRecognizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */