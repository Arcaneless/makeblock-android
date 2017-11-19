package com.iflytek.alsa;

import android.util.Log;
import com.iflytek.alsa.jni.AlsaJni;

public class AlsaRecorder
{
  private static String TAG = "AlsaRecorder";
  private static int card = 2;
  private static AlsaRecorder instance;
  private static int pcmHandle = 0;
  private static int periodSize;
  private static int sampleRate = 96000;
  private a a;
  private PcmListener b;
  private boolean c;
  private int d = -1;
  private Object e = new Object();
  
  static
  {
    periodSize = 1536;
  }
  
  private AlsaRecorder(int paramInt1, int paramInt2, int paramInt3)
  {
    card = paramInt1;
    sampleRate = paramInt2;
    periodSize = paramInt3;
  }
  
  public static AlsaRecorder createInstance(int paramInt)
  {
    return createInstance(paramInt, sampleRate, periodSize);
  }
  
  public static AlsaRecorder createInstance(int paramInt1, int paramInt2)
  {
    return createInstance(paramInt1, paramInt2, periodSize);
  }
  
  public static AlsaRecorder createInstance(int paramInt1, int paramInt2, int paramInt3)
  {
    
    if (instance == null) {
      instance = new AlsaRecorder(paramInt1, paramInt2, paramInt3);
    }
    return instance;
  }
  
  public static AlsaRecorder getInstance()
  {
    return instance;
  }
  
  public static String getVersion()
  {
    return "1.3";
  }
  
  public void destroy()
  {
    stopRecording();
    instance = null;
  }
  
  public int getBufferSize()
  {
    return this.d;
  }
  
  public int getCardDevId()
  {
    return card;
  }
  
  public PcmListener getListener()
  {
    return this.b;
  }
  
  public int getPeriodSize()
  {
    return periodSize;
  }
  
  public int getSampleRate()
  {
    return sampleRate;
  }
  
  public boolean isRecording()
  {
    return this.c;
  }
  
  public void setBufferSize(int paramInt)
  {
    this.d = paramInt;
  }
  
  public int startRecording(PcmListener arg1)
  {
    if (instance == null)
    {
      Log.e(TAG, "startRecording | AlsaRecorder instance is null.");
      return -1;
    }
    if (this.c)
    {
      Log.e(TAG, "startRecording | be repeatedly called.");
      return -1;
    }
    this.b = ???;
    Thread local1 = new Thread()
    {
      public void run()
      {
        AlsaJni.pcm_open(AlsaRecorder.b(), AlsaRecorder.c(), AlsaRecorder.d(), AlsaRecorder.e());
        synchronized (AlsaRecorder.c(AlsaRecorder.this))
        {
          AlsaRecorder.c(AlsaRecorder.this).notify();
          return;
        }
      }
    };
    synchronized (this.e)
    {
      try
      {
        local1.start();
        this.e.wait(1000L);
        if (pcmHandle != 0)
        {
          if (-1 == this.d) {
            this.d = (AlsaJni.pcm_buffer_size(pcmHandle) / 2);
          }
          if (AlsaJni.pcm_start_record(this.d, this.d * 8) == 0) {
            this.c = true;
          }
          if (this.a == null)
          {
            this.a = new a();
            this.a.start();
          }
          return 0;
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          localInterruptedException.printStackTrace();
        }
      }
    }
    ((Thread)localObject).interrupt();
    Log.e("AlsaRecorder", "startRecording | open pcm device failed.");
    return -1;
  }
  
  public void stopRecording()
  {
    if (instance == null) {
      Log.e(TAG, "stopRecording | AlsaRecorder instance is null.");
    }
    while (this.a == null) {
      return;
    }
    this.a.a();
    this.a = null;
    try
    {
      synchronized (this.e)
      {
        this.e.wait(200L);
        return;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        localInterruptedException.printStackTrace();
      }
    }
  }
  
  public static abstract interface PcmListener
  {
    public abstract void onPcmData(byte[] paramArrayOfByte, int paramInt);
  }
  
  class a
    extends Thread
  {
    private boolean b = false;
    
    public a()
    {
      super();
    }
    
    public void a()
    {
      interrupt();
      this.b = true;
    }
    
    public void run()
    {
      super.run();
      for (;;)
      {
        if (this.b)
        {
          AlsaJni.pcm_close(AlsaRecorder.a());
          AlsaRecorder.a(AlsaRecorder.this, false);
          AlsaRecorder.a(0);
        }
        synchronized (AlsaRecorder.c(AlsaRecorder.this))
        {
          AlsaRecorder.c(AlsaRecorder.this).notify();
          return;
          ??? = new byte[AlsaRecorder.a(AlsaRecorder.this)];
          int i = AlsaJni.pcm_read((byte[])???, ???.length);
          if ((AlsaRecorder.b(AlsaRecorder.this) == null) || (i == 0)) {
            continue;
          }
          AlsaRecorder.b(AlsaRecorder.this).onPcmData((byte[])???, ???.length);
        }
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\alsa\AlsaRecorder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */