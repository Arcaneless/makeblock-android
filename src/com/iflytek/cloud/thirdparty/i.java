package com.iflytek.cloud.thirdparty;

import android.os.Environment;
import android.os.MemoryFile;
import com.iflytek.aimic.AIMic;
import com.iflytek.aimic.AIMic.AsrDataListener;
import com.iflytek.aimic.AIMic.IvwDataListener;
import com.iflytek.aimic.a;
import com.iflytek.alsa.AlsaRecorder;
import com.iflytek.alsa.AlsaRecorder.PcmListener;
import com.iflytek.alsa.jni.AlsaJni;
import com.iflytek.msc.AIMIC;
import com.iflytek.msc.AIMIC.Listener;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Iterator;

public class i
  extends AIMic
{
  private static i d = null;
  private static final Object e = new Object();
  private final int a = 49152;
  private final int b = 1966080;
  private final int c = 3932160;
  private final Object f = new Object();
  private boolean g = false;
  private final byte[] h = new byte['Ā'];
  private final k i = new k(1966080L, 49152, 983040L, false, false);
  private final c j = new c("AIMicAudioWritingThread");
  private int k = 2;
  private final a l = new a(null);
  private int m = 4000;
  private final int n = 16000;
  private final int o = 2;
  private final int p = 1536;
  private AlsaRecorder q = null;
  private final b r = new b(null);
  private int s = 16000;
  private int t = 2;
  private int u = 1536;
  private int v = -3;
  private boolean w = false;
  
  private i(String paramString)
    throws UnsatisfiedLinkError, a, Throwable
  {
    j.a("aimic constructor enter: " + paramString);
    StringBuffer localStringBuffer = new StringBuffer();
    if (!l.a(paramString)) {
      localStringBuffer.append(paramString);
    }
    int i1;
    if (!AIMIC.isLoaded())
    {
      if (paramString == null) {
        break label430;
      }
      int i4 = paramString.indexOf("lib_name");
      if ((i4 < 0) || (i4 >= paramString.length())) {
        break label430;
      }
      int i3 = paramString.indexOf(',', i4);
      i1 = i3;
      if (i3 < 0) {
        i1 = paramString.length();
      }
      paramString = paramString.substring(i4, i1);
      localStringBuffer.delete(i4, i1);
    }
    for (;;)
    {
      AIMIC.loadLibrary(paramString);
      i1 = i2;
      if (!AIMIC.isValid())
      {
        i2 = AIMIC.AIMICNew(l.b(localStringBuffer.toString()), this.l);
        i1 = i2;
        if (i2 != 0)
        {
          j.b("AIMICNew return error: " + i2);
          AIMIC.AIMICDestroy(AIMIC.getHandler());
          i1 = i2;
        }
      }
      if (i1 != 0) {
        throw new a(i1);
      }
      this.j.start();
      this.g = true;
      if ((this.k == 0) || (2 == this.k)) {
        this.i.a(1966080L);
      }
      j.a("aimic constructor leave: " + i1);
      return;
      label430:
      paramString = null;
    }
  }
  
  private int a(byte[] arg1, int paramInt1, int paramInt2)
  {
    int i1 = 0;
    try
    {
      this.i.a(???, paramInt1, paramInt2);
      synchronized (this.j)
      {
        if (Thread.State.WAITING == this.j.getState()) {
          this.j.notify();
        }
        ??? = this.j.a();
        if (??? != null)
        {
          paramInt1 = ???.a();
          this.j.b();
          return paramInt1;
        }
      }
      return 26999;
    }
    catch (OutOfMemoryError ???)
    {
      j.a(???);
      if (2 == this.k)
      {
        j.b("write audio too soon, current audios  in buffer will be ignored!");
        this.i.c();
      }
      for (paramInt1 = i1;; paramInt1 = 26014)
      {
        return paramInt1;
        return 0;
        j.b("write audio too soon, please wait for a second, and try again!");
      }
    }
    catch (IllegalArgumentException ???)
    {
      j.a(???);
      return 26013;
    }
    catch (NullPointerException ???)
    {
      j.a(???);
      return 26006;
    }
    catch (Throwable ???)
    {
      j.b("write audio too soon, please wait for a second, and try again!");
      j.a(???);
    }
  }
  
  public static i a()
  {
    j.a("aimic getAIMic enter");
    synchronized (e)
    {
      i locali = d;
      j.a("aimic getAIMic leave: " + locali);
      return locali;
    }
  }
  
  public static i a(String paramString)
  {
    j.a("aimic createAIMic enter");
    synchronized (e)
    {
      i locali = d;
      if (locali != null) {}
    }
    try
    {
      d = new i(paramString);
      paramString = d;
      j.a("aimic createAIMic leave: " + paramString);
      return paramString;
    }
    catch (a paramString)
    {
      for (;;)
      {
        j.a(paramString);
      }
      paramString = finally;
      throw paramString;
    }
    catch (UnsatisfiedLinkError paramString)
    {
      for (;;)
      {
        j.a(paramString);
      }
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        j.a(paramString);
      }
    }
  }
  
  private boolean b()
  {
    return this.g;
  }
  
  private boolean b(String paramString)
  {
    return (paramString != null) && ((paramString.equals("true")) || (paramString.equals("1")));
  }
  
  private int c()
  {
    int i1 = 26008;
    if (b())
    {
      this.q = AlsaRecorder.getInstance();
      if ((this.q == null) || (this.q.getCardDevId() != this.t) || (this.q.getSampleRate() != this.s) || (this.q.getPeriodSize() != this.u))
      {
        if (this.q != null)
        {
          this.q.stopRecording();
          this.q.destroy();
          this.q = null;
        }
        j.a("create new alsa recorder");
        this.q = AlsaRecorder.createInstance(this.t, this.s, this.u);
      }
      if (this.q == null) {
        break label203;
      }
      if ((this.q.isRecording()) && (!this.r.equals(this.q.getListener())))
      {
        j.a("Current Alsa listener is not this, alsa will be stoped.");
        this.q.stopRecording();
      }
      if (!this.q.isRecording())
      {
        j.a("start Alsa recording.");
        i1 = this.q.startRecording(this.r);
      }
    }
    else
    {
      return i1;
    }
    j.a("Alsa is recording.");
    return 0;
    label203:
    return 26005;
  }
  
  private void d()
  {
    this.q = AlsaRecorder.getInstance();
    if (this.q != null)
    {
      j.a("stop alsa");
      this.q.stopRecording();
      this.r.a();
      this.q = null;
    }
  }
  
  public void destroy()
  {
    j.a("aimic destroy enter");
    synchronized (this.f)
    {
      this.g = false;
      if (this.l != null) {
        this.l.b();
      }
      this.q = AlsaRecorder.getInstance();
      if (this.q != null)
      {
        this.q.stopRecording();
        this.q.destroy();
        this.q = null;
      }
      synchronized (this.j)
      {
        if (this.j.isAlive()) {
          this.j.notify();
        }
      }
    }
    synchronized (e)
    {
      i locali = d;
      if (locali != null) {}
      try
      {
        AIMIC.AIMICDestroy(AIMIC.getHandler());
        d = null;
        System.gc();
        j.a("aimic destroy leave");
        return;
        localObject2 = finally;
        throw ((Throwable)localObject2);
        localObject3 = finally;
        throw ((Throwable)localObject3);
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          j.a(localThrowable);
        }
      }
    }
  }
  
  public String getParameter(String paramString)
  {
    j.a("aimic  getParameter enter");
    Object localObject2 = null;
    byte[] arrayOfByte = this.h;
    Object localObject1;
    synchronized (this.f)
    {
      boolean bool = b();
      localObject1 = localObject2;
      if (bool) {}
      try
      {
        i1 = AIMIC.AIMICGetParam(AIMIC.getHandler(), l.b(paramString), arrayOfByte);
        if (i1 != 0) {
          break label91;
        }
        localObject1 = l.a(arrayOfByte);
      }
      catch (Throwable paramString)
      {
        for (;;)
        {
          int i1;
          label91:
          j.a(paramString);
          localObject1 = localObject2;
        }
      }
      j.a("aimic  getParameter leave: " + (String)localObject1);
      return (String)localObject1;
      j.b("get parameter error: " + i1);
      localObject1 = localObject2;
    }
  }
  
  public String getVesion()
  {
    synchronized (this.f)
    {
      try
      {
        Object localObject1 = AIMIC.AIMICGetVersion();
        localObject1 = "1003." + l.a((byte[])localObject1);
        return (String)localObject1;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          j.a(localThrowable);
          Object localObject2 = null;
        }
      }
    }
  }
  
  public boolean isListening()
  {
    j.a("aimic  isListening enter");
    synchronized (this.f)
    {
      boolean bool = this.w;
      j.a("aimic  isListening leave ret: " + bool);
      return bool;
    }
  }
  
  public void registerListener(AIMic.AsrDataListener paramAsrDataListener)
  {
    j.a("aimic  registerListener enter: " + paramAsrDataListener);
    synchronized (this.f)
    {
      this.l.a(paramAsrDataListener);
      j.a("aimic  registerListener leave");
      return;
    }
  }
  
  public void registerListener(AIMic.IvwDataListener paramIvwDataListener)
  {
    j.a("aimic  registerListener enter: " + paramIvwDataListener);
    synchronized (this.f)
    {
      this.l.a(paramIvwDataListener);
      j.a("aimic  setParameter leave");
      return;
    }
  }
  
  public int reset()
  {
    j.a("aimic  reset enter");
    synchronized (this.f)
    {
      try
      {
        if (b()) {}
        for (i1 = AIMIC.AIMICResetEng(AIMIC.getHandler());; i1 = 26008)
        {
          j.a("aimic  reset leave: " + i1);
          return i1;
        }
      }
      catch (a locala)
      {
        for (;;)
        {
          j.a(locala);
          i1 = locala.a();
        }
      }
      catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
      {
        for (;;)
        {
          j.a(localUnsatisfiedLinkError);
          i1 = 26016;
        }
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          j.a(localThrowable);
          int i1 = 26999;
        }
      }
    }
  }
  
  /* Error */
  public int setParameter(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: new 134	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 135	java/lang/StringBuilder:<init>	()V
    //   7: ldc_w 415
    //   10: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   13: aload_1
    //   14: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: ldc_w 417
    //   20: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: aload_2
    //   24: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: invokevirtual 145	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: invokestatic 149	com/iflytek/cloud/thirdparty/j:a	(Ljava/lang/String;)V
    //   33: iconst_0
    //   34: istore_3
    //   35: aload_0
    //   36: getfield 78	com/iflytek/cloud/thirdparty/i:f	Ljava/lang/Object;
    //   39: astore 4
    //   41: aload 4
    //   43: monitorenter
    //   44: ldc_w 419
    //   47: invokestatic 149	com/iflytek/cloud/thirdparty/j:a	(Ljava/lang/String;)V
    //   50: ldc_w 421
    //   53: aload_1
    //   54: invokevirtual 424	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   57: ifeq +50 -> 107
    //   60: aload_0
    //   61: aload_2
    //   62: invokestatic 429	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   65: putfield 109	com/iflytek/cloud/thirdparty/i:m	I
    //   68: aload_0
    //   69: getfield 107	com/iflytek/cloud/thirdparty/i:l	Lcom/iflytek/cloud/thirdparty/i$a;
    //   72: aload_0
    //   73: getfield 109	com/iflytek/cloud/thirdparty/i:m	I
    //   76: invokevirtual 431	com/iflytek/cloud/thirdparty/i$a:a	(I)V
    //   79: aload 4
    //   81: monitorexit
    //   82: new 134	java/lang/StringBuilder
    //   85: dup
    //   86: invokespecial 135	java/lang/StringBuilder:<init>	()V
    //   89: ldc_w 433
    //   92: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: iload_3
    //   96: invokevirtual 208	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   99: invokevirtual 145	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   102: invokestatic 149	com/iflytek/cloud/thirdparty/j:a	(Ljava/lang/String;)V
    //   105: iload_3
    //   106: ireturn
    //   107: ldc_w 435
    //   110: aload_1
    //   111: invokevirtual 424	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   114: ifeq +32 -> 146
    //   117: aload_0
    //   118: aload_2
    //   119: invokestatic 429	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   122: putfield 124	com/iflytek/cloud/thirdparty/i:s	I
    //   125: goto -46 -> 79
    //   128: astore_1
    //   129: aload 4
    //   131: monitorexit
    //   132: aload_1
    //   133: athrow
    //   134: astore_1
    //   135: aload_1
    //   136: invokestatic 263	com/iflytek/cloud/thirdparty/j:a	(Ljava/lang/Throwable;)V
    //   139: sipush 26016
    //   142: istore_3
    //   143: goto -61 -> 82
    //   146: ldc_w 437
    //   149: aload_1
    //   150: invokevirtual 424	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   153: ifeq +14 -> 167
    //   156: aload_0
    //   157: aload_2
    //   158: invokestatic 429	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   161: putfield 126	com/iflytek/cloud/thirdparty/i:t	I
    //   164: goto -85 -> 79
    //   167: ldc_w 439
    //   170: aload_1
    //   171: invokevirtual 424	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   174: ifeq +14 -> 188
    //   177: aload_0
    //   178: aload_2
    //   179: invokestatic 429	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   182: putfield 128	com/iflytek/cloud/thirdparty/i:u	I
    //   185: goto -106 -> 79
    //   188: ldc_w 441
    //   191: aload_1
    //   192: invokevirtual 424	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   195: ifeq +18 -> 213
    //   198: aload_0
    //   199: getfield 122	com/iflytek/cloud/thirdparty/i:r	Lcom/iflytek/cloud/thirdparty/i$b;
    //   202: aload_0
    //   203: aload_2
    //   204: invokespecial 443	com/iflytek/cloud/thirdparty/i:b	(Ljava/lang/String;)Z
    //   207: invokevirtual 446	com/iflytek/cloud/thirdparty/i$b:a	(Z)V
    //   210: goto -131 -> 79
    //   213: ldc_w 448
    //   216: aload_1
    //   217: invokevirtual 424	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   220: ifeq +14 -> 234
    //   223: aload_0
    //   224: aload_2
    //   225: invokestatic 429	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   228: putfield 130	com/iflytek/cloud/thirdparty/i:v	I
    //   231: goto -152 -> 79
    //   234: ldc_w 450
    //   237: aload_1
    //   238: invokevirtual 424	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   241: ifeq +77 -> 318
    //   244: aload_0
    //   245: aload_2
    //   246: invokestatic 429	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   249: putfield 102	com/iflytek/cloud/thirdparty/i:k	I
    //   252: aload_0
    //   253: getfield 102	com/iflytek/cloud/thirdparty/i:k	I
    //   256: tableswitch	default:+112->368, 1:+41->297
    //   276: aload_0
    //   277: getfield 93	com/iflytek/cloud/thirdparty/i:i	Lcom/iflytek/cloud/thirdparty/k;
    //   280: ldc2_w 451
    //   283: aload_0
    //   284: getfield 93	com/iflytek/cloud/thirdparty/i:i	Lcom/iflytek/cloud/thirdparty/k;
    //   287: invokevirtual 454	com/iflytek/cloud/thirdparty/k:a	()J
    //   290: lsub
    //   291: invokevirtual 227	com/iflytek/cloud/thirdparty/k:a	(J)V
    //   294: goto -215 -> 79
    //   297: aload_0
    //   298: getfield 93	com/iflytek/cloud/thirdparty/i:i	Lcom/iflytek/cloud/thirdparty/k;
    //   301: ldc2_w 85
    //   304: aload_0
    //   305: getfield 93	com/iflytek/cloud/thirdparty/i:i	Lcom/iflytek/cloud/thirdparty/k;
    //   308: invokevirtual 454	com/iflytek/cloud/thirdparty/k:a	()J
    //   311: lsub
    //   312: invokevirtual 227	com/iflytek/cloud/thirdparty/k:a	(J)V
    //   315: goto -236 -> 79
    //   318: aload_0
    //   319: invokespecial 288	com/iflytek/cloud/thirdparty/i:b	()Z
    //   322: ifeq +27 -> 349
    //   325: ldc_w 456
    //   328: invokestatic 149	com/iflytek/cloud/thirdparty/j:a	(Ljava/lang/String;)V
    //   331: invokestatic 214	com/iflytek/msc/AIMIC:getHandler	()J
    //   334: aload_1
    //   335: invokestatic 199	com/iflytek/cloud/thirdparty/l:b	(Ljava/lang/String;)[B
    //   338: aload_2
    //   339: invokestatic 199	com/iflytek/cloud/thirdparty/l:b	(Ljava/lang/String;)[B
    //   342: invokestatic 459	com/iflytek/msc/AIMIC:AIMICSetParam	(J[B[B)I
    //   345: istore_3
    //   346: goto -267 -> 79
    //   349: sipush 26008
    //   352: istore_3
    //   353: goto -274 -> 79
    //   356: astore_1
    //   357: aload_1
    //   358: invokestatic 263	com/iflytek/cloud/thirdparty/j:a	(Ljava/lang/Throwable;)V
    //   361: sipush 26999
    //   364: istore_3
    //   365: goto -283 -> 82
    //   368: goto -92 -> 276
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	371	0	this	i
    //   0	371	1	paramString1	String
    //   0	371	2	paramString2	String
    //   34	331	3	i1	int
    // Exception table:
    //   from	to	target	type
    //   44	79	128	finally
    //   79	82	128	finally
    //   107	125	128	finally
    //   129	132	128	finally
    //   146	164	128	finally
    //   167	185	128	finally
    //   188	210	128	finally
    //   213	231	128	finally
    //   234	276	128	finally
    //   276	294	128	finally
    //   297	315	128	finally
    //   318	346	128	finally
    //   35	44	134	java/lang/UnsatisfiedLinkError
    //   132	134	134	java/lang/UnsatisfiedLinkError
    //   35	44	356	java/lang/Throwable
    //   132	134	356	java/lang/Throwable
  }
  
  public void setShowLog(boolean paramBoolean)
  {
    synchronized (this.f)
    {
      try
      {
        j.a(paramBoolean);
        AIMIC.AIMICDebugLog(paramBoolean);
        AlsaJni.setJniLog(paramBoolean);
        return;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          j.a(localThrowable);
        }
      }
    }
  }
  
  public int startListening()
  {
    boolean bool = false;
    j.a("aimic  startListening enter");
    int i1 = 26008;
    for (;;)
    {
      synchronized (this.f)
      {
        if (!b()) {
          break label145;
        }
        if (-3 == this.v)
        {
          i1 = c();
          break label145;
          this.w = bool;
          j.a("aimic  startListening leave: " + i1);
          return i1;
        }
        else if (-1 == this.v)
        {
          if (this.q != null)
          {
            i1 = 26014;
            j.b("startListening failed, current internal recorder is not stoped!");
          }
        }
      }
      i1 = 0;
      break label145;
      i1 = 26012;
      j.b("startListening failed, invalid audio source: " + this.v);
      label145:
      if (i1 == 0) {
        bool = true;
      }
    }
  }
  
  public void stopListening()
  {
    j.a("aimic  stopListening enter");
    synchronized (this.f)
    {
      if ((this.l == null) || (this.l.a()))
      {
        j.a("AIMic Listener is empty, alsa will stop recording.");
        d();
        this.w = false;
      }
      j.a("aimic  stopListening leave");
      return;
    }
  }
  
  public void unregisterListener(AIMic.AsrDataListener paramAsrDataListener)
  {
    j.a("aimic  unregisterListener enter: " + paramAsrDataListener);
    synchronized (this.f)
    {
      this.l.b(paramAsrDataListener);
      if (this.l.a()) {
        stopListening();
      }
      j.a("aimic  unregisterListener leave");
      return;
    }
  }
  
  public void unregisterListener(AIMic.IvwDataListener paramIvwDataListener)
  {
    j.a("aimic  unregisterListener enter: " + paramIvwDataListener);
    synchronized (this.f)
    {
      this.l.b(paramIvwDataListener);
      if (this.l.a()) {
        stopListening();
      }
      j.a("aimic  unregisterListener leave");
      return;
    }
  }
  
  public int writeAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    synchronized (this.f)
    {
      if (b())
      {
        paramInt1 = a(paramArrayOfByte, paramInt1, paramInt2);
        return paramInt1;
      }
      paramInt1 = 26008;
      j.b("write audio while not init!");
    }
  }
  
  private static class a
    implements AIMIC.Listener
  {
    private final HashSet<AIMic.IvwDataListener> a = new HashSet();
    private final HashSet<AIMic.AsrDataListener> b = new HashSet();
    private final Object c = new Object();
    private final Object d = new Object();
    private final int e = 32;
    private final int f = 4000;
    private final int g = 128000;
    private final int h = 512;
    private final k i = new k(128000L, 512, 0L, false, true);
    private final int j = 1;
    private boolean k = true;
    private int l = 128000;
    
    public void a(int paramInt)
    {
      this.l = (paramInt * 32);
    }
    
    public void a(AIMic.AsrDataListener paramAsrDataListener)
    {
      if (paramAsrDataListener != null) {
        synchronized (this.d)
        {
          this.b.add(paramAsrDataListener);
          return;
        }
      }
    }
    
    public void a(AIMic.IvwDataListener paramIvwDataListener)
    {
      if (paramIvwDataListener != null) {
        synchronized (this.c)
        {
          this.a.add(paramIvwDataListener);
          return;
        }
      }
    }
    
    public void a(a parama)
    {
      Iterator localIterator;
      synchronized (this.c)
      {
        localIterator = this.a.iterator();
        if (localIterator.hasNext()) {
          ((AIMic.IvwDataListener)localIterator.next()).onError(parama.a());
        }
      }
      synchronized (this.d)
      {
        localIterator = this.b.iterator();
        if (localIterator.hasNext()) {
          ((AIMic.AsrDataListener)localIterator.next()).onError(parama.a());
        }
      }
    }
    
    public boolean a()
    {
      return ((this.a == null) || (this.a.isEmpty())) && ((this.b == null) || (this.b.isEmpty()));
    }
    
    public void b()
    {
      if (this.a != null) {
        this.a.clear();
      }
      if (this.b != null) {
        this.b.clear();
      }
    }
    
    public void b(AIMic.AsrDataListener paramAsrDataListener)
    {
      if (paramAsrDataListener != null) {
        synchronized (this.d)
        {
          this.b.remove(paramAsrDataListener);
          return;
        }
      }
    }
    
    public void b(AIMic.IvwDataListener paramIvwDataListener)
    {
      if (paramIvwDataListener != null) {
        synchronized (this.c)
        {
          this.a.remove(paramIvwDataListener);
          return;
        }
      }
    }
    
    /* Error */
    public void onRecogAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Object paramObject)
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore 6
      //   3: new 119	java/lang/StringBuilder
      //   6: dup
      //   7: invokespecial 120	java/lang/StringBuilder:<init>	()V
      //   10: ldc 122
      //   12: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   15: invokestatic 132	java/lang/System:currentTimeMillis	()J
      //   18: invokevirtual 135	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   21: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   24: invokestatic 144	com/iflytek/cloud/thirdparty/j:c	(Ljava/lang/String;)V
      //   27: aload_0
      //   28: getfield 42	com/iflytek/cloud/thirdparty/i$a:d	Ljava/lang/Object;
      //   31: astore 10
      //   33: aload 10
      //   35: monitorenter
      //   36: iload 6
      //   38: istore 5
      //   40: aload_0
      //   41: getfield 64	com/iflytek/cloud/thirdparty/i$a:k	Z
      //   44: ifeq +20 -> 64
      //   47: iload 6
      //   49: istore 5
      //   51: aload_0
      //   52: getfield 38	com/iflytek/cloud/thirdparty/i$a:b	Ljava/util/HashSet;
      //   55: invokevirtual 107	java/util/HashSet:isEmpty	()Z
      //   58: ifeq +6 -> 64
      //   61: iconst_1
      //   62: istore 5
      //   64: aload_0
      //   65: iload 5
      //   67: putfield 64	com/iflytek/cloud/thirdparty/i$a:k	Z
      //   70: aload_0
      //   71: getfield 64	com/iflytek/cloud/thirdparty/i$a:k	Z
      //   74: ifeq +141 -> 215
      //   77: aload_0
      //   78: getfield 60	com/iflytek/cloud/thirdparty/i$a:i	Lcom/iflytek/cloud/thirdparty/k;
      //   81: invokevirtual 146	com/iflytek/cloud/thirdparty/k:b	()J
      //   84: lstore 7
      //   86: iload_2
      //   87: i2l
      //   88: lload 7
      //   90: ladd
      //   91: aload_0
      //   92: getfield 66	com/iflytek/cloud/thirdparty/i$a:l	I
      //   95: i2l
      //   96: lcmp
      //   97: ifle +78 -> 175
      //   100: new 119	java/lang/StringBuilder
      //   103: dup
      //   104: invokespecial 120	java/lang/StringBuilder:<init>	()V
      //   107: lload 7
      //   109: invokevirtual 135	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   112: ldc -108
      //   114: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   117: aload_0
      //   118: getfield 66	com/iflytek/cloud/thirdparty/i$a:l	I
      //   121: invokevirtual 151	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   124: ldc -103
      //   126: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   129: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   132: invokestatic 155	com/iflytek/cloud/thirdparty/j:a	(Ljava/lang/String;)V
      //   135: aload_0
      //   136: iconst_0
      //   137: putfield 64	com/iflytek/cloud/thirdparty/i$a:k	Z
      //   140: aload_0
      //   141: getfield 60	com/iflytek/cloud/thirdparty/i$a:i	Lcom/iflytek/cloud/thirdparty/k;
      //   144: invokevirtual 157	com/iflytek/cloud/thirdparty/k:c	()V
      //   147: aload 10
      //   149: monitorexit
      //   150: new 119	java/lang/StringBuilder
      //   153: dup
      //   154: invokespecial 120	java/lang/StringBuilder:<init>	()V
      //   157: ldc -97
      //   159: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   162: invokestatic 132	java/lang/System:currentTimeMillis	()J
      //   165: invokevirtual 135	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   168: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   171: invokestatic 144	com/iflytek/cloud/thirdparty/j:c	(Ljava/lang/String;)V
      //   174: return
      //   175: aload_0
      //   176: getfield 60	com/iflytek/cloud/thirdparty/i$a:i	Lcom/iflytek/cloud/thirdparty/k;
      //   179: aload_1
      //   180: iconst_0
      //   181: iload_2
      //   182: invokevirtual 162	com/iflytek/cloud/thirdparty/k:a	([BII)V
      //   185: goto -38 -> 147
      //   188: astore_1
      //   189: aload 10
      //   191: monitorexit
      //   192: aload_1
      //   193: athrow
      //   194: astore_1
      //   195: aload_1
      //   196: invokestatic 165	com/iflytek/cloud/thirdparty/j:a	(Ljava/lang/Throwable;)V
      //   199: aload_0
      //   200: new 95	com/iflytek/aimic/a
      //   203: dup
      //   204: aload_1
      //   205: sipush 26999
      //   208: invokespecial 168	com/iflytek/aimic/a:<init>	(Ljava/lang/Throwable;I)V
      //   211: invokevirtual 170	com/iflytek/cloud/thirdparty/i$a:a	(Lcom/iflytek/aimic/a;)V
      //   214: return
      //   215: aload_0
      //   216: getfield 38	com/iflytek/cloud/thirdparty/i$a:b	Ljava/util/HashSet;
      //   219: invokevirtual 81	java/util/HashSet:iterator	()Ljava/util/Iterator;
      //   222: astore 11
      //   224: aload 11
      //   226: invokeinterface 87 1 0
      //   231: ifeq -84 -> 147
      //   234: aload 11
      //   236: invokeinterface 91 1 0
      //   241: checkcast 103	com/iflytek/aimic/AIMic$AsrDataListener
      //   244: astore 12
      //   246: aload_0
      //   247: getfield 60	com/iflytek/cloud/thirdparty/i$a:i	Lcom/iflytek/cloud/thirdparty/k;
      //   250: invokevirtual 172	com/iflytek/cloud/thirdparty/k:d	()Z
      //   253: ifne +73 -> 326
      //   256: aload_0
      //   257: getfield 60	com/iflytek/cloud/thirdparty/i$a:i	Lcom/iflytek/cloud/thirdparty/k;
      //   260: invokevirtual 175	com/iflytek/cloud/thirdparty/k:e	()Lcom/iflytek/cloud/thirdparty/k$a;
      //   263: astore 9
      //   265: aload 9
      //   267: ifnull +52 -> 319
      //   270: aload 12
      //   272: aload 9
      //   274: invokevirtual 180	com/iflytek/cloud/thirdparty/k$a:getKey	()Ljava/lang/Object;
      //   277: checkcast 182	[B
      //   280: aload 9
      //   282: invokevirtual 185	com/iflytek/cloud/thirdparty/k$a:getValue	()Ljava/lang/Object;
      //   285: checkcast 187	java/lang/Integer
      //   288: invokevirtual 190	java/lang/Integer:intValue	()I
      //   291: iconst_0
      //   292: aconst_null
      //   293: invokeinterface 192 5 0
      //   298: aload_0
      //   299: getfield 60	com/iflytek/cloud/thirdparty/i$a:i	Lcom/iflytek/cloud/thirdparty/k;
      //   302: aload 9
      //   304: invokevirtual 195	com/iflytek/cloud/thirdparty/k:a	(Lcom/iflytek/cloud/thirdparty/k$a;)V
      //   307: aload_0
      //   308: getfield 60	com/iflytek/cloud/thirdparty/i$a:i	Lcom/iflytek/cloud/thirdparty/k;
      //   311: invokevirtual 175	com/iflytek/cloud/thirdparty/k:e	()Lcom/iflytek/cloud/thirdparty/k$a;
      //   314: astore 9
      //   316: goto -51 -> 265
      //   319: aload_0
      //   320: getfield 60	com/iflytek/cloud/thirdparty/i$a:i	Lcom/iflytek/cloud/thirdparty/k;
      //   323: invokevirtual 157	com/iflytek/cloud/thirdparty/k:c	()V
      //   326: aload 12
      //   328: aload_1
      //   329: iload_2
      //   330: iload_3
      //   331: aload 4
      //   333: invokeinterface 192 5 0
      //   338: goto -114 -> 224
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	341	0	this	a
      //   0	341	1	paramArrayOfByte	byte[]
      //   0	341	2	paramInt1	int
      //   0	341	3	paramInt2	int
      //   0	341	4	paramObject	Object
      //   38	28	5	bool1	boolean
      //   1	47	6	bool2	boolean
      //   84	24	7	l1	long
      //   263	52	9	locala	k.a
      //   31	159	10	localObject	Object
      //   222	13	11	localIterator	Iterator
      //   244	83	12	localAsrDataListener	AIMic.AsrDataListener
      // Exception table:
      //   from	to	target	type
      //   40	47	188	finally
      //   51	61	188	finally
      //   64	147	188	finally
      //   147	150	188	finally
      //   175	185	188	finally
      //   189	192	188	finally
      //   215	224	188	finally
      //   224	265	188	finally
      //   270	316	188	finally
      //   319	326	188	finally
      //   326	338	188	finally
      //   3	36	194	java/lang/Throwable
      //   150	174	194	java/lang/Throwable
      //   192	194	194	java/lang/Throwable
    }
    
    public void onWakeupAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Object paramObject)
    {
      try
      {
        j.c("onWakeupAudio enter " + System.currentTimeMillis());
        synchronized (this.c)
        {
          j.c("onWakeupAudio sync in");
          Iterator localIterator = this.a.iterator();
          if (localIterator.hasNext())
          {
            j.c("onWakeupAudio for in");
            AIMic.IvwDataListener localIvwDataListener = (AIMic.IvwDataListener)localIterator.next();
            j.c("onWakeupAudio get listener");
            localIvwDataListener.onWakeupAudio(paramArrayOfByte, paramInt1, paramInt2, paramObject);
          }
        }
      }
      catch (Throwable paramArrayOfByte)
      {
        j.a(paramArrayOfByte);
        a(new a(paramArrayOfByte, 26999));
        return;
      }
      j.c("onWakeupAudio leave " + System.currentTimeMillis());
    }
    
    /* Error */
    public void onWakeupMsg(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, int paramInt4, byte[] paramArrayOfByte2, int paramInt5, byte[] paramArrayOfByte3, int paramInt6)
    {
      // Byte code:
      //   0: ldc -44
      //   2: invokestatic 144	com/iflytek/cloud/thirdparty/j:c	(Ljava/lang/String;)V
      //   5: iconst_1
      //   6: iload_1
      //   7: if_icmpne +31 -> 38
      //   10: aload_0
      //   11: getfield 42	com/iflytek/cloud/thirdparty/i$a:d	Ljava/lang/Object;
      //   14: astore 11
      //   16: aload 11
      //   18: monitorenter
      //   19: aload_0
      //   20: getfield 66	com/iflytek/cloud/thirdparty/i$a:l	I
      //   23: ifle +108 -> 131
      //   26: iconst_1
      //   27: istore 10
      //   29: aload_0
      //   30: iload 10
      //   32: putfield 64	com/iflytek/cloud/thirdparty/i$a:k	Z
      //   35: aload 11
      //   37: monitorexit
      //   38: aload_0
      //   39: getfield 40	com/iflytek/cloud/thirdparty/i$a:c	Ljava/lang/Object;
      //   42: astore 11
      //   44: aload 11
      //   46: monitorenter
      //   47: aload_0
      //   48: getfield 36	com/iflytek/cloud/thirdparty/i$a:a	Ljava/util/HashSet;
      //   51: invokevirtual 81	java/util/HashSet:iterator	()Ljava/util/Iterator;
      //   54: astore 12
      //   56: aload 12
      //   58: invokeinterface 87 1 0
      //   63: ifeq +82 -> 145
      //   66: aload 12
      //   68: invokeinterface 91 1 0
      //   73: checkcast 93	com/iflytek/aimic/AIMic$IvwDataListener
      //   76: iload_1
      //   77: iload_2
      //   78: iload_3
      //   79: aload 4
      //   81: iload 5
      //   83: aload 6
      //   85: iload 7
      //   87: aload 8
      //   89: iload 9
      //   91: invokeinterface 214 10 0
      //   96: goto -40 -> 56
      //   99: astore 4
      //   101: aload 11
      //   103: monitorexit
      //   104: aload 4
      //   106: athrow
      //   107: astore 4
      //   109: aload 4
      //   111: invokestatic 165	com/iflytek/cloud/thirdparty/j:a	(Ljava/lang/Throwable;)V
      //   114: aload_0
      //   115: new 95	com/iflytek/aimic/a
      //   118: dup
      //   119: aload 4
      //   121: sipush 26999
      //   124: invokespecial 168	com/iflytek/aimic/a:<init>	(Ljava/lang/Throwable;I)V
      //   127: invokevirtual 170	com/iflytek/cloud/thirdparty/i$a:a	(Lcom/iflytek/aimic/a;)V
      //   130: return
      //   131: iconst_0
      //   132: istore 10
      //   134: goto -105 -> 29
      //   137: astore 4
      //   139: aload 11
      //   141: monitorexit
      //   142: aload 4
      //   144: athrow
      //   145: aload 11
      //   147: monitorexit
      //   148: ldc -40
      //   150: invokestatic 144	com/iflytek/cloud/thirdparty/j:c	(Ljava/lang/String;)V
      //   153: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	154	0	this	a
      //   0	154	1	paramInt1	int
      //   0	154	2	paramInt2	int
      //   0	154	3	paramInt3	int
      //   0	154	4	paramArrayOfByte1	byte[]
      //   0	154	5	paramInt4	int
      //   0	154	6	paramArrayOfByte2	byte[]
      //   0	154	7	paramInt5	int
      //   0	154	8	paramArrayOfByte3	byte[]
      //   0	154	9	paramInt6	int
      //   27	106	10	bool	boolean
      //   54	13	12	localIterator	Iterator
      // Exception table:
      //   from	to	target	type
      //   47	56	99	finally
      //   56	96	99	finally
      //   101	104	99	finally
      //   145	148	99	finally
      //   0	5	107	java/lang/Throwable
      //   10	19	107	java/lang/Throwable
      //   38	47	107	java/lang/Throwable
      //   104	107	107	java/lang/Throwable
      //   142	145	107	java/lang/Throwable
      //   148	153	107	java/lang/Throwable
      //   19	26	137	finally
      //   29	38	137	finally
      //   139	142	137	finally
    }
  }
  
  private class b
    implements AlsaRecorder.PcmListener
  {
    private int b = 0;
    private final int c = 49152;
    private final int d = 512000;
    private final int e = 61440000;
    private final k f = new k(512000L, 49152, 0L, true, false);
    private final k g = new k(512000L, 49152, 0L, true, false);
    private volatile k h = this.f;
    private volatile k i = this.g;
    private final String j = Environment.getExternalStorageDirectory().getPath() + "/aimic_alsa.pcm";
    private Thread k = null;
    private boolean l = false;
    private boolean m = false;
    
    private b() {}
    
    /* Error */
    private void a(int paramInt, boolean paramBoolean)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_3
      //   2: aload_0
      //   3: getfield 60	com/iflytek/cloud/thirdparty/i$b:h	Lcom/iflytek/cloud/thirdparty/k;
      //   6: astore 5
      //   8: aload 5
      //   10: monitorenter
      //   11: iload_2
      //   12: ifne +20 -> 32
      //   15: aload_0
      //   16: getfield 60	com/iflytek/cloud/thirdparty/i$b:h	Lcom/iflytek/cloud/thirdparty/k;
      //   19: invokevirtual 103	com/iflytek/cloud/thirdparty/k:b	()J
      //   22: iload_1
      //   23: i2l
      //   24: ladd
      //   25: ldc2_w 50
      //   28: lcmp
      //   29: iflt +34 -> 63
      //   32: aload_0
      //   33: getfield 60	com/iflytek/cloud/thirdparty/i$b:h	Lcom/iflytek/cloud/thirdparty/k;
      //   36: astore 4
      //   38: aload 4
      //   40: aload_0
      //   41: getfield 58	com/iflytek/cloud/thirdparty/i$b:g	Lcom/iflytek/cloud/thirdparty/k;
      //   44: invokevirtual 107	java/lang/Object:equals	(Ljava/lang/Object;)Z
      //   47: ifeq +123 -> 170
      //   50: aload_0
      //   51: getfield 56	com/iflytek/cloud/thirdparty/i$b:f	Lcom/iflytek/cloud/thirdparty/k;
      //   54: astore_3
      //   55: aload_0
      //   56: aload_3
      //   57: putfield 60	com/iflytek/cloud/thirdparty/i$b:h	Lcom/iflytek/cloud/thirdparty/k;
      //   60: aload 4
      //   62: astore_3
      //   63: aload 5
      //   65: monitorexit
      //   66: aload_3
      //   67: ifnull +102 -> 169
      //   70: aload_0
      //   71: getfield 62	com/iflytek/cloud/thirdparty/i$b:i	Lcom/iflytek/cloud/thirdparty/k;
      //   74: astore 4
      //   76: aload 4
      //   78: monitorenter
      //   79: aload_0
      //   80: aload_3
      //   81: putfield 62	com/iflytek/cloud/thirdparty/i$b:i	Lcom/iflytek/cloud/thirdparty/k;
      //   84: aload 4
      //   86: monitorexit
      //   87: aload_0
      //   88: getfield 90	com/iflytek/cloud/thirdparty/i$b:k	Ljava/lang/Thread;
      //   91: ifnull +32 -> 123
      //   94: aload_0
      //   95: getfield 90	com/iflytek/cloud/thirdparty/i$b:k	Ljava/lang/Thread;
      //   98: astore_3
      //   99: aload_3
      //   100: monitorenter
      //   101: getstatic 113	java/lang/Thread$State:WAITING	Ljava/lang/Thread$State;
      //   104: aload_0
      //   105: getfield 90	com/iflytek/cloud/thirdparty/i$b:k	Ljava/lang/Thread;
      //   108: invokevirtual 119	java/lang/Thread:getState	()Ljava/lang/Thread$State;
      //   111: if_acmpne +10 -> 121
      //   114: aload_0
      //   115: getfield 90	com/iflytek/cloud/thirdparty/i$b:k	Ljava/lang/Thread;
      //   118: invokevirtual 122	java/lang/Object:notify	()V
      //   121: aload_3
      //   122: monitorexit
      //   123: aload_0
      //   124: getfield 60	com/iflytek/cloud/thirdparty/i$b:h	Lcom/iflytek/cloud/thirdparty/k;
      //   127: astore_3
      //   128: aload_3
      //   129: monitorenter
      //   130: lconst_0
      //   131: aload_0
      //   132: getfield 60	com/iflytek/cloud/thirdparty/i$b:h	Lcom/iflytek/cloud/thirdparty/k;
      //   135: invokevirtual 103	com/iflytek/cloud/thirdparty/k:b	()J
      //   138: lcmp
      //   139: ifeq +28 -> 167
      //   142: ldc 124
      //   144: invokestatic 129	com/iflytek/cloud/thirdparty/j:b	(Ljava/lang/String;)V
      //   147: aload_0
      //   148: getfield 33	com/iflytek/cloud/thirdparty/i$b:a	Lcom/iflytek/cloud/thirdparty/i;
      //   151: invokestatic 132	com/iflytek/cloud/thirdparty/i:c	(Lcom/iflytek/cloud/thirdparty/i;)Lcom/iflytek/cloud/thirdparty/i$a;
      //   154: new 134	com/iflytek/aimic/a
      //   157: dup
      //   158: sipush 26999
      //   161: invokespecial 137	com/iflytek/aimic/a:<init>	(I)V
      //   164: invokevirtual 142	com/iflytek/cloud/thirdparty/i$a:a	(Lcom/iflytek/aimic/a;)V
      //   167: aload_3
      //   168: monitorexit
      //   169: return
      //   170: aload_0
      //   171: getfield 58	com/iflytek/cloud/thirdparty/i$b:g	Lcom/iflytek/cloud/thirdparty/k;
      //   174: astore_3
      //   175: goto -120 -> 55
      //   178: astore_3
      //   179: aload 5
      //   181: monitorexit
      //   182: aload_3
      //   183: athrow
      //   184: astore_3
      //   185: aload 4
      //   187: monitorexit
      //   188: aload_3
      //   189: athrow
      //   190: astore 4
      //   192: aload_3
      //   193: monitorexit
      //   194: aload 4
      //   196: athrow
      //   197: astore 4
      //   199: aload_3
      //   200: monitorexit
      //   201: aload 4
      //   203: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	204	0	this	b
      //   0	204	1	paramInt	int
      //   0	204	2	paramBoolean	boolean
      //   178	5	3	localObject2	Object
      //   184	16	3	localObject3	Object
      //   190	5	4	localObject4	Object
      //   197	5	4	localObject5	Object
      //   6	174	5	localk2	k
      // Exception table:
      //   from	to	target	type
      //   15	32	178	finally
      //   32	55	178	finally
      //   55	60	178	finally
      //   63	66	178	finally
      //   170	175	178	finally
      //   179	182	178	finally
      //   79	87	184	finally
      //   185	188	184	finally
      //   101	121	190	finally
      //   121	123	190	finally
      //   192	194	190	finally
      //   130	167	197	finally
      //   167	169	197	finally
      //   199	201	197	finally
    }
    
    private void a(byte[] paramArrayOfByte, int paramInt)
    {
      if (this.k == null)
      {
        ??? = new File(this.j);
        if (((File)???).exists()) {
          ((File)???).delete();
        }
        this.f.c();
        this.g.c();
        this.h = this.f;
        this.i = this.g;
        this.m = true;
        c();
      }
      try
      {
        a(paramInt, false);
        synchronized (this.h)
        {
          this.h.a(paramArrayOfByte, 0, paramInt);
          return;
        }
        return;
      }
      catch (Throwable paramArrayOfByte)
      {
        j.a(paramArrayOfByte);
        i.c(i.this).a(new a(paramArrayOfByte, 26999));
      }
    }
    
    private boolean a(RandomAccessFile paramRandomAccessFile, MemoryFile paramMemoryFile, int paramInt1, int paramInt2)
    {
      if ((paramRandomAccessFile != null) && (paramMemoryFile != null) && (paramInt1 >= 0) && (paramInt2 >= 0))
      {
        paramInt2 = paramInt1 + paramInt2;
        try
        {
          byte[] arrayOfByte = new byte[65536];
          while (paramInt2 > paramInt1)
          {
            int n = Math.min(arrayOfByte.length, paramInt2 - paramInt1);
            if (paramMemoryFile.readBytes(arrayOfByte, paramInt1, 0, n) != n)
            {
              j.b("saveFileFromMemory error: read bytes length error!");
              return false;
            }
            paramInt1 += n;
            paramRandomAccessFile.write(arrayOfByte, 0, n);
          }
          j.b("saveFileFromMemory error: arguments error");
        }
        catch (Throwable paramRandomAccessFile)
        {
          j.a(paramRandomAccessFile);
          return false;
        }
      }
      return false;
      return true;
    }
    
    private boolean b()
    {
      return this.l;
    }
    
    private void c()
    {
      this.k = new Thread("AlsaAudioSavingThread")
      {
        public void run()
        {
          j.a("alsa data save thread enter");
          for (;;)
          {
            try
            {
              localObject10 = new MemoryFile(null, 61952000);
              try
              {
                ((MemoryFile)localObject10).allowPurging(false);
                j = 0;
                i = 0;
                if (!i.b.a(i.b.this)) {
                  continue;
                }
                synchronized (i.b.b(i.b.this))
                {
                  if (i.b.b(i.b.this).d()) {
                    continue;
                  }
                  k.a locala = i.b.b(i.b.this).e();
                  ((MemoryFile)localObject10).writeBytes((byte[])locala.getKey(), 0, i, ((Integer)locala.getValue()).intValue());
                  i += ((Integer)locala.getValue()).intValue();
                  i.b.b(i.b.this).a(locala);
                }
                j.a(localThrowable2);
              }
              catch (Throwable localThrowable2)
              {
                ??? = null;
                localObject5 = localObject10;
              }
            }
            catch (Throwable localThrowable4)
            {
              Object localObject10;
              int j;
              int i;
              Object localObject5;
              Object localObject8;
              ??? = null;
              Object localObject7 = null;
              continue;
              Object localObject9 = null;
              continue;
              localObject7 = null;
              ??? = null;
              Object localObject11 = localObject10;
              continue;
              continue;
            }
            j.b("保存文件失败！");
            localObject10 = new a(localThrowable2, 26999);
            localObject8 = ???;
            ??? = localObject10;
            localObject11 = localObject5;
            localObject5 = localObject8;
            if (localObject11 != null) {}
            try
            {
              ((MemoryFile)localObject11).close();
              if (localObject5 != null) {
                ((RandomAccessFile)localObject5).close();
              }
            }
            catch (Throwable localThrowable1)
            {
              j.a(localThrowable1);
              j.b("关闭文件异常！");
              continue;
            }
            i.b.a(i.b.this, null);
            if (??? != null) {
              i.c(i.this).a((a)???);
            }
            j.a("alsa data save thread exit");
            return;
            i.b.b(i.b.this).c();
            if (61440000 > i) {
              continue;
            }
            j.c("alsa data save thread file will be truncated");
            j = 1;
            j.c("alsa data save thread file is truncated");
            i = 0;
            try
            {
              if (i.b.b(i.b.this).d())
              {
                j.c("alsa data save thread waiting");
                wait();
                j.c("alsa data save thread waked");
              }
              else
              {
                j.c("alsa data save thread buffer has been updated.");
                continue;
                synchronized (i.b.b(i.b.this))
                {
                  if (!i.b.b(i.b.this).d())
                  {
                    localObject5 = i.b.b(i.b.this).e();
                    ((MemoryFile)localObject10).writeBytes((byte[])((k.a)localObject5).getKey(), 0, i, ((Integer)((k.a)localObject5).getValue()).intValue());
                    k = ((Integer)((k.a)localObject5).getValue()).intValue();
                    i.b.b(i.b.this).a((k.a)localObject5);
                    i = k + i;
                  }
                  else
                  {
                    i.b.b(i.b.this).c();
                    if (0 != 0) {
                      continue;
                    }
                    localRandomAccessFile = new RandomAccessFile(i.b.c(i.b.this), "rw");
                    if (j != 0)
                    {
                      j = 61440000 - i;
                      if (j <= 0) {
                        continue;
                      }
                      try
                      {
                        if (i.b.a(i.b.this, localRandomAccessFile, (MemoryFile)localObject10, i, j)) {
                          continue;
                        }
                        localObject8 = new a(26999);
                        j.b("save last memory file data failed!");
                        localObject5 = localRandomAccessFile;
                        ??? = localObject8;
                        localObject11 = localObject10;
                        if (localObject8 == null)
                        {
                          localObject5 = localRandomAccessFile;
                          ??? = localObject8;
                          localObject11 = localObject10;
                          if (!i.b.a(i.b.this, localRandomAccessFile, (MemoryFile)localObject10, 0, Math.min(61440000, i)))
                          {
                            ??? = new a(26999);
                            j.b("save current memory file data failed!");
                            localObject5 = localRandomAccessFile;
                            localObject11 = localObject10;
                          }
                        }
                      }
                      catch (Throwable localThrowable3)
                      {
                        ??? = localRandomAccessFile;
                        localObject5 = localObject10;
                      }
                    }
                  }
                }
              }
            }
            finally {}
          }
          for (;;)
          {
            int k;
            RandomAccessFile localRandomAccessFile;
            j = 0;
          }
        }
      };
      this.k.start();
    }
    
    public void a()
    {
      this.m = false;
      if (b()) {
        a(0, true);
      }
    }
    
    public void a(boolean paramBoolean)
    {
      this.l = paramBoolean;
    }
    
    public void onPcmData(byte[] paramArrayOfByte, int paramInt)
    {
      j.c("Alsa onPcmData enter, " + System.currentTimeMillis());
      if (b()) {
        a(paramArrayOfByte, paramInt);
      }
      this.b = i.this.writeAudio(paramArrayOfByte, 0, paramInt);
      if (this.b != 0) {
        i.c(i.this).a(new a(this.b));
      }
      j.c("Alsa onPcmData leave, " + System.currentTimeMillis());
    }
  }
  
  private class c
    extends Thread
  {
    private final int b = 1;
    private final int c = 16;
    private final int d = 1000;
    private final k e = i.a(i.this);
    private a f = null;
    private long g = System.currentTimeMillis();
    private boolean h = true;
    
    public c(String paramString)
    {
      super();
    }
    
    private void c()
    {
      boolean bool = true;
      if ((1 != i.d(i.this)) && (120000L >= System.currentTimeMillis() - this.g))
      {
        long l = this.e.f();
        if ((49152L < l) && (l + this.e.b() > 1966080L)) {
          if (this.h) {
            break label94;
          }
        }
      }
      for (;;)
      {
        this.h = bool;
        if (!this.h) {
          this.e.g();
        }
        this.g = System.currentTimeMillis();
        return;
        label94:
        bool = false;
      }
    }
    
    public a a()
    {
      return this.f;
    }
    
    public void b()
    {
      this.f = null;
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: ldc 77
      //   2: invokestatic 81	com/iflytek/cloud/thirdparty/j:a	(Ljava/lang/String;)V
      //   5: iconst_0
      //   6: istore_1
      //   7: iconst_0
      //   8: istore_2
      //   9: aload_0
      //   10: getfield 24	com/iflytek/cloud/thirdparty/i$c:a	Lcom/iflytek/cloud/thirdparty/i;
      //   13: invokestatic 84	com/iflytek/cloud/thirdparty/i:b	(Lcom/iflytek/cloud/thirdparty/i;)Z
      //   16: ifeq +284 -> 300
      //   19: iload_1
      //   20: istore 5
      //   22: iload_2
      //   23: istore_3
      //   24: iload_1
      //   25: istore 6
      //   27: iload_2
      //   28: istore 4
      //   30: aload_0
      //   31: getfield 38	com/iflytek/cloud/thirdparty/i$c:e	Lcom/iflytek/cloud/thirdparty/k;
      //   34: invokevirtual 87	com/iflytek/cloud/thirdparty/k:e	()Lcom/iflytek/cloud/thirdparty/k$a;
      //   37: astore 7
      //   39: aload 7
      //   41: ifnull +90 -> 131
      //   44: invokestatic 92	com/iflytek/msc/AIMIC:getHandler	()J
      //   47: aload 7
      //   49: invokevirtual 98	com/iflytek/cloud/thirdparty/k$a:getKey	()Ljava/lang/Object;
      //   52: checkcast 100	[B
      //   55: iconst_0
      //   56: aload 7
      //   58: invokevirtual 103	com/iflytek/cloud/thirdparty/k$a:getValue	()Ljava/lang/Object;
      //   61: checkcast 105	java/lang/Integer
      //   64: invokevirtual 109	java/lang/Integer:intValue	()I
      //   67: invokestatic 113	com/iflytek/msc/AIMIC:AIMICAudioWrite	(J[BII)I
      //   70: istore_1
      //   71: aload_0
      //   72: getfield 38	com/iflytek/cloud/thirdparty/i$c:e	Lcom/iflytek/cloud/thirdparty/k;
      //   75: aload 7
      //   77: invokevirtual 116	com/iflytek/cloud/thirdparty/k:a	(Lcom/iflytek/cloud/thirdparty/k$a;)V
      //   80: iload_1
      //   81: ifeq +259 -> 340
      //   84: new 118	java/lang/StringBuilder
      //   87: dup
      //   88: invokespecial 120	java/lang/StringBuilder:<init>	()V
      //   91: ldc 122
      //   93: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   96: iload_1
      //   97: invokevirtual 129	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   100: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   103: invokestatic 135	com/iflytek/cloud/thirdparty/j:b	(Ljava/lang/String;)V
      //   106: aload_0
      //   107: getfield 24	com/iflytek/cloud/thirdparty/i$c:a	Lcom/iflytek/cloud/thirdparty/i;
      //   110: invokestatic 138	com/iflytek/cloud/thirdparty/i:c	(Lcom/iflytek/cloud/thirdparty/i;)Lcom/iflytek/cloud/thirdparty/i$a;
      //   113: new 140	com/iflytek/aimic/a
      //   116: dup
      //   117: iload_1
      //   118: invokespecial 143	com/iflytek/aimic/a:<init>	(I)V
      //   121: invokevirtual 148	com/iflytek/cloud/thirdparty/i$a:a	(Lcom/iflytek/aimic/a;)V
      //   124: iconst_0
      //   125: istore_1
      //   126: iconst_0
      //   127: istore_2
      //   128: goto -119 -> 9
      //   131: iload_1
      //   132: istore 5
      //   134: iload_2
      //   135: istore_3
      //   136: iload_1
      //   137: istore 6
      //   139: iload_2
      //   140: istore 4
      //   142: aload_0
      //   143: invokespecial 150	com/iflytek/cloud/thirdparty/i$c:c	()V
      //   146: sipush 1000
      //   149: iload_1
      //   150: if_icmpgt +69 -> 219
      //   153: aload_0
      //   154: monitorenter
      //   155: aload_0
      //   156: getfield 38	com/iflytek/cloud/thirdparty/i$c:e	Lcom/iflytek/cloud/thirdparty/k;
      //   159: invokevirtual 153	com/iflytek/cloud/thirdparty/k:d	()Z
      //   162: ifeq +17 -> 179
      //   165: ldc -101
      //   167: invokestatic 81	com/iflytek/cloud/thirdparty/j:a	(Ljava/lang/String;)V
      //   170: aload_0
      //   171: invokevirtual 160	java/lang/Object:wait	()V
      //   174: ldc -94
      //   176: invokestatic 81	com/iflytek/cloud/thirdparty/j:a	(Ljava/lang/String;)V
      //   179: aload_0
      //   180: monitorexit
      //   181: iconst_0
      //   182: istore_1
      //   183: goto -174 -> 9
      //   186: astore 7
      //   188: aload_0
      //   189: monitorexit
      //   190: aload 7
      //   192: athrow
      //   193: astore 7
      //   195: iconst_0
      //   196: istore_1
      //   197: aload 7
      //   199: invokestatic 165	com/iflytek/cloud/thirdparty/j:a	(Ljava/lang/Throwable;)V
      //   202: aload_0
      //   203: new 140	com/iflytek/aimic/a
      //   206: dup
      //   207: sipush 26016
      //   210: invokespecial 143	com/iflytek/aimic/a:<init>	(I)V
      //   213: putfield 40	com/iflytek/cloud/thirdparty/i$c:f	Lcom/iflytek/aimic/a;
      //   216: goto -207 -> 9
      //   219: iload_1
      //   220: iconst_1
      //   221: iadd
      //   222: istore_1
      //   223: iload_1
      //   224: istore 5
      //   226: iload_2
      //   227: istore_3
      //   228: iload_1
      //   229: istore 6
      //   231: iload_2
      //   232: istore 4
      //   234: iload_2
      //   235: iconst_1
      //   236: iadd
      //   237: bipush 16
      //   239: invokestatic 171	java/lang/Math:min	(II)I
      //   242: istore_2
      //   243: iload_1
      //   244: istore 5
      //   246: iload_2
      //   247: istore_3
      //   248: iload_1
      //   249: istore 6
      //   251: iload_2
      //   252: istore 4
      //   254: iload_2
      //   255: i2l
      //   256: invokestatic 175	com/iflytek/cloud/thirdparty/i$c:sleep	(J)V
      //   259: goto -250 -> 9
      //   262: astore 7
      //   264: iload 5
      //   266: istore_1
      //   267: iload_3
      //   268: istore_2
      //   269: goto -72 -> 197
      //   272: astore 7
      //   274: iconst_0
      //   275: istore_2
      //   276: iconst_0
      //   277: istore_1
      //   278: aload 7
      //   280: invokestatic 165	com/iflytek/cloud/thirdparty/j:a	(Ljava/lang/Throwable;)V
      //   283: aload_0
      //   284: new 140	com/iflytek/aimic/a
      //   287: dup
      //   288: sipush 26999
      //   291: invokespecial 143	com/iflytek/aimic/a:<init>	(I)V
      //   294: putfield 40	com/iflytek/cloud/thirdparty/i$c:f	Lcom/iflytek/aimic/a;
      //   297: goto -288 -> 9
      //   300: aload_0
      //   301: getfield 38	com/iflytek/cloud/thirdparty/i$c:e	Lcom/iflytek/cloud/thirdparty/k;
      //   304: invokevirtual 176	com/iflytek/cloud/thirdparty/k:c	()V
      //   307: ldc -78
      //   309: invokestatic 81	com/iflytek/cloud/thirdparty/j:a	(Ljava/lang/String;)V
      //   312: return
      //   313: astore 7
      //   315: iload 6
      //   317: istore_1
      //   318: iload 4
      //   320: istore_2
      //   321: goto -43 -> 278
      //   324: astore 7
      //   326: iconst_0
      //   327: istore_1
      //   328: goto -50 -> 278
      //   331: astore 7
      //   333: iconst_0
      //   334: istore_2
      //   335: iconst_0
      //   336: istore_1
      //   337: goto -140 -> 197
      //   340: iconst_0
      //   341: istore_1
      //   342: iconst_0
      //   343: istore_2
      //   344: goto -335 -> 9
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	347	0	this	c
      //   6	336	1	i	int
      //   8	336	2	j	int
      //   23	245	3	k	int
      //   28	291	4	m	int
      //   20	245	5	n	int
      //   25	291	6	i1	int
      //   37	39	7	locala	k.a
      //   186	5	7	localObject	Object
      //   193	5	7	localUnsatisfiedLinkError1	UnsatisfiedLinkError
      //   262	1	7	localUnsatisfiedLinkError2	UnsatisfiedLinkError
      //   272	7	7	localThrowable1	Throwable
      //   313	1	7	localThrowable2	Throwable
      //   324	1	7	localThrowable3	Throwable
      //   331	1	7	localUnsatisfiedLinkError3	UnsatisfiedLinkError
      // Exception table:
      //   from	to	target	type
      //   155	179	186	finally
      //   179	181	186	finally
      //   188	190	186	finally
      //   153	155	193	java/lang/UnsatisfiedLinkError
      //   190	193	193	java/lang/UnsatisfiedLinkError
      //   30	39	262	java/lang/UnsatisfiedLinkError
      //   142	146	262	java/lang/UnsatisfiedLinkError
      //   234	243	262	java/lang/UnsatisfiedLinkError
      //   254	259	262	java/lang/UnsatisfiedLinkError
      //   44	80	272	java/lang/Throwable
      //   84	124	272	java/lang/Throwable
      //   30	39	313	java/lang/Throwable
      //   142	146	313	java/lang/Throwable
      //   234	243	313	java/lang/Throwable
      //   254	259	313	java/lang/Throwable
      //   153	155	324	java/lang/Throwable
      //   190	193	324	java/lang/Throwable
      //   44	80	331	java/lang/UnsatisfiedLinkError
      //   84	124	331	java/lang/UnsatisfiedLinkError
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */