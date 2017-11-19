package com.iflytek.cloud.record;

import android.media.AudioRecord;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.thirdparty.ad;

public class PcmRecorder
  extends Thread
{
  public static final int RATE16K = 16000;
  public static final int READ_INTERVAL40MS = 40;
  private final short a = 16;
  private byte[] b = null;
  private AudioRecord c = null;
  private PcmRecordListener d = null;
  private PcmRecordListener e = null;
  private volatile boolean f = false;
  private double g = 0.0D;
  private double h = 0.0D;
  private int i = 16000;
  private int j = 40;
  private int k = 40;
  private int l;
  
  public PcmRecorder(int paramInt1, int paramInt2)
  {
    this(paramInt1, paramInt2, 1);
  }
  
  public PcmRecorder(int paramInt1, int paramInt2, int paramInt3)
  {
    this.l = paramInt3;
    this.i = paramInt1;
    this.j = paramInt2;
    if ((this.j < 40) || (this.j > 100)) {
      this.j = 40;
    }
    this.k = 10;
  }
  
  private double a(byte[] paramArrayOfByte, int paramInt)
  {
    if ((paramArrayOfByte == null) || (paramInt <= 0)) {
      return 0.0D;
    }
    int m = paramArrayOfByte.length;
    paramInt = 0;
    double d1 = 0.0D;
    while (paramInt < m)
    {
      d1 += paramArrayOfByte[paramInt];
      paramInt += 1;
    }
    double d2 = d1 / paramArrayOfByte.length;
    m = paramArrayOfByte.length;
    d1 = 0.0D;
    paramInt = 0;
    while (paramInt < m)
    {
      d1 += Math.pow(paramArrayOfByte[paramInt] - d2, 2.0D);
      paramInt += 1;
    }
    return Math.sqrt(d1 / (paramArrayOfByte.length - 1));
  }
  
  private int a()
    throws SpeechError
  {
    int m;
    if ((this.c == null) || (this.d == null)) {
      m = 0;
    }
    int n;
    do
    {
      return m;
      n = this.c.read(this.b, 0, this.b.length);
      if ((n > 0) && (this.d != null))
      {
        this.d.onRecordBuffer(this.b, 0, n);
        return n;
      }
      m = n;
    } while (n >= 0);
    ad.b("Record read data error: " + n);
    throw new SpeechError(20006);
  }
  
  private void b()
  {
    try
    {
      if (this.c != null)
      {
        ad.a("release record begin");
        this.c.release();
        this.c = null;
        if (this.e != null)
        {
          this.e.onRecordReleased();
          this.e = null;
        }
        ad.a("release record over");
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ad.b(localException.toString());
      }
    }
    finally {}
  }
  
  protected void a(short paramShort, int paramInt1, int paramInt2)
    throws SpeechError
  {
    if (this.c != null) {
      b();
    }
    short s = paramInt1 * paramInt2 / 1000;
    int n = s * 4 * 16 * paramShort / 8;
    if (paramShort == 1) {}
    for (paramInt2 = 2;; paramInt2 = 3)
    {
      int i1 = AudioRecord.getMinBufferSize(paramInt1, paramInt2, 2);
      int m = n;
      if (n < i1) {
        m = i1;
      }
      this.c = new AudioRecord(this.l, paramInt1, paramInt2, 2, m);
      this.b = new byte[s * paramShort * 16 / 8];
      ad.a("\nSampleRate:" + paramInt1 + "\nChannel:" + paramInt2 + "\nFormat:" + 2 + "\nFramePeriod:" + s + "\nBufferSize:" + m + "\nMinBufferSize:" + i1 + "\nActualBufferSize:" + this.b.length + "\n");
      if (this.c.getState() == 1) {
        break;
      }
      ad.a("create AudioRecord error");
      throw new SpeechError(20006);
    }
  }
  
  protected void finalize()
    throws Throwable
  {
    b();
    super.finalize();
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 4
    //   3: iconst_0
    //   4: istore_3
    //   5: aload_0
    //   6: getfield 51	com/iflytek/cloud/record/PcmRecorder:f	Z
    //   9: istore 6
    //   11: iload 6
    //   13: ifne +16 -> 29
    //   16: aload_0
    //   17: iconst_1
    //   18: aload_0
    //   19: getfield 57	com/iflytek/cloud/record/PcmRecorder:i	I
    //   22: aload_0
    //   23: getfield 59	com/iflytek/cloud/record/PcmRecorder:j	I
    //   26: invokevirtual 170	com/iflytek/cloud/record/PcmRecorder:a	(SII)V
    //   29: iconst_0
    //   30: istore_3
    //   31: aload_0
    //   32: getfield 51	com/iflytek/cloud/record/PcmRecorder:f	Z
    //   35: istore 6
    //   37: iload 6
    //   39: ifne +134 -> 173
    //   42: aload_0
    //   43: getfield 45	com/iflytek/cloud/record/PcmRecorder:c	Landroid/media/AudioRecord;
    //   46: invokevirtual 173	android/media/AudioRecord:startRecording	()V
    //   49: aload_0
    //   50: getfield 45	com/iflytek/cloud/record/PcmRecorder:c	Landroid/media/AudioRecord;
    //   53: invokevirtual 176	android/media/AudioRecord:getRecordingState	()I
    //   56: iconst_3
    //   57: if_icmpeq +116 -> 173
    //   60: new 79	com/iflytek/cloud/SpeechError
    //   63: dup
    //   64: sipush 20006
    //   67: invokespecial 113	com/iflytek/cloud/SpeechError:<init>	(I)V
    //   70: athrow
    //   71: astore 9
    //   73: iload_3
    //   74: iconst_1
    //   75: iadd
    //   76: istore_3
    //   77: iload_3
    //   78: bipush 10
    //   80: if_icmpge +82 -> 162
    //   83: ldc2_w 177
    //   86: invokestatic 182	com/iflytek/cloud/record/PcmRecorder:sleep	(J)V
    //   89: goto -58 -> 31
    //   92: astore 9
    //   94: aload 9
    //   96: invokestatic 185	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/Throwable;)V
    //   99: aload_0
    //   100: getfield 47	com/iflytek/cloud/record/PcmRecorder:d	Lcom/iflytek/cloud/record/PcmRecorder$PcmRecordListener;
    //   103: ifnull +22 -> 125
    //   106: aload_0
    //   107: getfield 47	com/iflytek/cloud/record/PcmRecorder:d	Lcom/iflytek/cloud/record/PcmRecorder$PcmRecordListener;
    //   110: new 79	com/iflytek/cloud/SpeechError
    //   113: dup
    //   114: sipush 20006
    //   117: invokespecial 113	com/iflytek/cloud/SpeechError:<init>	(I)V
    //   120: invokeinterface 189 2 0
    //   125: aload_0
    //   126: invokespecial 132	com/iflytek/cloud/record/PcmRecorder:b	()V
    //   129: return
    //   130: astore 9
    //   132: iload_3
    //   133: iconst_1
    //   134: iadd
    //   135: istore_3
    //   136: iload_3
    //   137: bipush 10
    //   139: if_icmpge +12 -> 151
    //   142: ldc2_w 177
    //   145: invokestatic 182	com/iflytek/cloud/record/PcmRecorder:sleep	(J)V
    //   148: goto -143 -> 5
    //   151: new 79	com/iflytek/cloud/SpeechError
    //   154: dup
    //   155: sipush 20006
    //   158: invokespecial 113	com/iflytek/cloud/SpeechError:<init>	(I)V
    //   161: athrow
    //   162: new 79	com/iflytek/cloud/SpeechError
    //   165: dup
    //   166: sipush 20006
    //   169: invokespecial 113	com/iflytek/cloud/SpeechError:<init>	(I)V
    //   172: athrow
    //   173: aload_0
    //   174: getfield 47	com/iflytek/cloud/record/PcmRecorder:d	Lcom/iflytek/cloud/record/PcmRecorder$PcmRecordListener;
    //   177: ifnull +140 -> 317
    //   180: aload_0
    //   181: getfield 47	com/iflytek/cloud/record/PcmRecorder:d	Lcom/iflytek/cloud/record/PcmRecorder$PcmRecordListener;
    //   184: iconst_1
    //   185: invokeinterface 193 2 0
    //   190: goto +127 -> 317
    //   193: aload_0
    //   194: getfield 51	com/iflytek/cloud/record/PcmRecorder:f	Z
    //   197: ifne -72 -> 125
    //   200: aload_0
    //   201: invokespecial 195	com/iflytek/cloud/record/PcmRecorder:a	()I
    //   204: istore 5
    //   206: lload 7
    //   208: ldc2_w 177
    //   211: ladd
    //   212: lstore 7
    //   214: iload_3
    //   215: istore 4
    //   217: iload_3
    //   218: ifeq +85 -> 303
    //   221: aload_0
    //   222: getfield 53	com/iflytek/cloud/record/PcmRecorder:g	D
    //   225: dstore_1
    //   226: aload_0
    //   227: iload 5
    //   229: i2d
    //   230: dload_1
    //   231: dadd
    //   232: putfield 53	com/iflytek/cloud/record/PcmRecorder:g	D
    //   235: aload_0
    //   236: aload_0
    //   237: getfield 55	com/iflytek/cloud/record/PcmRecorder:h	D
    //   240: aload_0
    //   241: aload_0
    //   242: getfield 43	com/iflytek/cloud/record/PcmRecorder:b	[B
    //   245: aload_0
    //   246: getfield 43	com/iflytek/cloud/record/PcmRecorder:b	[B
    //   249: arraylength
    //   250: invokespecial 197	com/iflytek/cloud/record/PcmRecorder:a	([BI)D
    //   253: dadd
    //   254: putfield 55	com/iflytek/cloud/record/PcmRecorder:h	D
    //   257: iload_3
    //   258: istore 4
    //   260: lload 7
    //   262: ldc2_w 198
    //   265: lcmp
    //   266: iflt +37 -> 303
    //   269: aload_0
    //   270: getfield 53	com/iflytek/cloud/record/PcmRecorder:g	D
    //   273: dconst_0
    //   274: dcmpl
    //   275: ifeq +12 -> 287
    //   278: aload_0
    //   279: getfield 55	com/iflytek/cloud/record/PcmRecorder:h	D
    //   282: dconst_0
    //   283: dcmpl
    //   284: ifne +42 -> 326
    //   287: ldc -55
    //   289: invokestatic 110	com/iflytek/cloud/thirdparty/ad:b	(Ljava/lang/String;)V
    //   292: new 79	com/iflytek/cloud/SpeechError
    //   295: dup
    //   296: sipush 20006
    //   299: invokespecial 113	com/iflytek/cloud/SpeechError:<init>	(I)V
    //   302: athrow
    //   303: aload_0
    //   304: getfield 61	com/iflytek/cloud/record/PcmRecorder:k	I
    //   307: i2l
    //   308: invokestatic 182	com/iflytek/cloud/record/PcmRecorder:sleep	(J)V
    //   311: iload 4
    //   313: istore_3
    //   314: goto -121 -> 193
    //   317: lconst_0
    //   318: lstore 7
    //   320: iload 4
    //   322: istore_3
    //   323: goto -130 -> 193
    //   326: iconst_0
    //   327: istore 4
    //   329: goto -26 -> 303
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	332	0	this	PcmRecorder
    //   225	6	1	d1	double
    //   4	319	3	m	int
    //   1	327	4	n	int
    //   204	24	5	i1	int
    //   9	29	6	bool	boolean
    //   206	1	7	localObject	Object
    //   212	107	7	l1	long
    //   71	1	9	localException1	Exception
    //   92	3	9	localException2	Exception
    //   130	1	9	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   42	71	71	java/lang/Exception
    //   5	11	92	java/lang/Exception
    //   31	37	92	java/lang/Exception
    //   83	89	92	java/lang/Exception
    //   142	148	92	java/lang/Exception
    //   151	162	92	java/lang/Exception
    //   162	173	92	java/lang/Exception
    //   173	190	92	java/lang/Exception
    //   193	206	92	java/lang/Exception
    //   221	257	92	java/lang/Exception
    //   269	287	92	java/lang/Exception
    //   287	303	92	java/lang/Exception
    //   303	311	92	java/lang/Exception
    //   16	29	130	java/lang/Exception
  }
  
  public void startRecording(PcmRecordListener paramPcmRecordListener)
    throws SpeechError
  {
    this.d = paramPcmRecordListener;
    setPriority(10);
    start();
  }
  
  public void stopRecord(boolean paramBoolean)
  {
    this.f = true;
    if (this.e == null) {
      this.e = this.d;
    }
    this.d = null;
    if (paramBoolean) {}
    try
    {
      ad.a("stopRecord...release");
      if (this.c != null)
      {
        if ((3 == this.c.getRecordingState()) && (1 == this.c.getState()))
        {
          ad.a("stopRecord releaseRecording ing...");
          this.c.release();
          ad.a("stopRecord releaseRecording end...");
          this.c = null;
        }
        if (this.e != null)
        {
          this.e.onRecordReleased();
          this.e = null;
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ad.b(localException.toString());
      }
    }
    finally {}
    ad.a("stop record");
  }
  
  public static abstract interface PcmRecordListener
  {
    public abstract void onError(SpeechError paramSpeechError);
    
    public abstract void onRecordBuffer(byte[] paramArrayOfByte, int paramInt1, int paramInt2);
    
    public abstract void onRecordReleased();
    
    public abstract void onRecordStarted(boolean paramBoolean);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\record\PcmRecorder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */