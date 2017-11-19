package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.SystemClock;
import com.iflytek.cloud.SpeechError;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class u
  extends C
{
  private MSCSessionInfo c = new MSCSessionInfo();
  private MSCSessionInfo d = new MSCSessionInfo();
  private MSCSessionInfo e = new MSCSessionInfo();
  private byte[] f = null;
  
  private void a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SpeechError
  {
    try
    {
      paramInt1 = MSC.QISVAudioWrite(this.a, null, paramArrayOfByte, paramInt1, paramInt2, this.c);
      if (paramInt1 != 0) {
        throw new SpeechError(paramInt1);
      }
    }
    finally {}
  }
  
  public int a(Context paramContext, B paramB)
    throws UnsupportedEncodingException, SpeechError
  {
    long l = SystemClock.elapsedRealtime();
    String str2 = paramB.v().e("vid");
    String str1 = ai.b(paramContext, paramB);
    ad.a("sendRequest enter ");
    ae.a("LastDataFlag", null);
    int i;
    if (str2 == null)
    {
      paramContext = null;
      paramContext = MSC.QISVQueDelModel(paramContext, str1.getBytes(paramB.p()), this.e);
      ae.a("GetNotifyResult", null);
      MSC.QISVQueDelModelRelease(paramContext);
      if (this.e.errorcode == 0) {
        break label119;
      }
      i = this.e.errorcode;
    }
    for (;;)
    {
      if ((i == 0) || (-1 == i)) {
        break label151;
      }
      throw new SpeechError(i);
      paramContext = str2.getBytes(paramB.p());
      break;
      label119:
      if ("true".equals(new String(this.e.buffer))) {
        i = 0;
      } else {
        i = -1;
      }
    }
    label151:
    ad.a("sendRequest leavel:" + i + " time:" + (SystemClock.elapsedRealtime() - l));
    return i;
  }
  
  public int a(Context paramContext, String paramString, B paramB)
    throws SpeechError, UnsupportedEncodingException
  {
    paramContext = ai.b(paramContext, paramB);
    long l = SystemClock.elapsedRealtime();
    ae.a("MSCSessionBegin", null);
    byte[] arrayOfByte = paramContext.getBytes(paramB.p());
    if (paramString == null) {}
    for (paramContext = null;; paramContext = paramString.getBytes(paramB.p()))
    {
      this.a = MSC.QISVSessionBegin(arrayOfByte, paramContext, this.c);
      ae.a("SessionBeginEnd", null);
      ad.a("sessionBegin ErrCode:" + this.c.errorcode + " time:" + (SystemClock.elapsedRealtime() - l));
      int i = this.c.errorcode;
      if ((i == 0) || (i == 10129) || (i == 10113) || (i == 10132)) {
        break;
      }
      throw new SpeechError(i);
    }
    return 0;
  }
  
  public void a()
    throws SpeechError
  {
    try
    {
      ae.a("LastDataFlag", null);
      a(new byte[0], 0, 4);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void a(String paramString)
  {
    if (this.a == null) {
      return;
    }
    ad.a("sessionEnd enter ");
    long l = System.currentTimeMillis();
    if (MSC.QISVSessionEnd(this.a, paramString.getBytes()) == 0) {}
    for (boolean bool = true;; bool = false)
    {
      ad.a("sessionEnd leavel:" + bool + " time:" + (System.currentTimeMillis() - l));
      this.a = null;
      this.b = null;
      return;
    }
  }
  
  public void a(byte[] paramArrayOfByte, int paramInt)
    throws SpeechError
  {
    try
    {
      a(paramArrayOfByte, paramInt, 2);
      return;
    }
    finally
    {
      paramArrayOfByte = finally;
      throw paramArrayOfByte;
    }
  }
  
  public String b(String paramString)
  {
    Object localObject2 = null;
    for (;;)
    {
      try
      {
        localObject1 = this.a;
        if (localObject1 != null) {
          continue;
        }
        localObject1 = localObject2;
      }
      finally
      {
        try
        {
          if (MSC.QISVGetParam(this.a, paramString.getBytes(), this.c) != 0) {
            continue;
          }
          localObject1 = new String(this.c.buffer);
        }
        catch (Exception paramString)
        {
          Object localObject1 = localObject2;
        }
        paramString = finally;
      }
      return (String)localObject1;
      localObject1 = localObject2;
    }
  }
  
  /* Error */
  public boolean b()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 19	com/iflytek/cloud/thirdparty/u:c	Lcom/iflytek/msc/MSCSessionInfo;
    //   6: getfield 186	com/iflytek/msc/MSCSessionInfo:epstatues	I
    //   9: istore_1
    //   10: iload_1
    //   11: iconst_3
    //   12: if_icmplt +9 -> 21
    //   15: iconst_1
    //   16: istore_2
    //   17: aload_0
    //   18: monitorexit
    //   19: iload_2
    //   20: ireturn
    //   21: iconst_0
    //   22: istore_2
    //   23: goto -6 -> 17
    //   26: astore_3
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_3
    //   30: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	31	0	this	u
    //   9	4	1	i	int
    //   16	7	2	bool	boolean
    //   26	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	10	26	finally
  }
  
  /* Error */
  public int c()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 33	com/iflytek/cloud/thirdparty/u:a	[C
    //   8: ldc -67
    //   10: invokevirtual 163	java/lang/String:getBytes	()[B
    //   13: aload_0
    //   14: getfield 21	com/iflytek/cloud/thirdparty/u:d	Lcom/iflytek/msc/MSCSessionInfo;
    //   17: invokestatic 182	com/iflytek/msc/MSC:QISVGetParam	([C[BLcom/iflytek/msc/MSCSessionInfo;)I
    //   20: istore_1
    //   21: iload_1
    //   22: ifne +34 -> 56
    //   25: new 91	java/lang/String
    //   28: dup
    //   29: new 91	java/lang/String
    //   32: dup
    //   33: aload_0
    //   34: getfield 21	com/iflytek/cloud/thirdparty/u:d	Lcom/iflytek/msc/MSCSessionInfo;
    //   37: getfield 114	com/iflytek/msc/MSCSessionInfo:buffer	[B
    //   40: invokespecial 117	java/lang/String:<init>	([B)V
    //   43: invokespecial 191	java/lang/String:<init>	(Ljava/lang/String;)V
    //   46: invokestatic 197	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   49: istore_3
    //   50: iload_3
    //   51: istore_1
    //   52: aload_0
    //   53: monitorexit
    //   54: iload_1
    //   55: ireturn
    //   56: ldc -57
    //   58: invokestatic 78	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
    //   61: iload_2
    //   62: istore_1
    //   63: goto -11 -> 52
    //   66: astore 4
    //   68: new 123	java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial 124	java/lang/StringBuilder:<init>	()V
    //   75: ldc -55
    //   77: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: iload_1
    //   81: invokevirtual 133	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   84: invokevirtual 141	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: invokestatic 78	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
    //   90: iload_2
    //   91: istore_1
    //   92: goto -40 -> 52
    //   95: astore 4
    //   97: aload_0
    //   98: monitorexit
    //   99: aload 4
    //   101: athrow
    //   102: astore 4
    //   104: iconst_0
    //   105: istore_1
    //   106: goto -38 -> 68
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	109	0	this	u
    //   20	86	1	i	int
    //   1	90	2	j	int
    //   49	2	3	k	int
    //   66	1	4	localException1	Exception
    //   95	5	4	localObject	Object
    //   102	1	4	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   25	50	66	java/lang/Exception
    //   56	61	66	java/lang/Exception
    //   4	21	95	finally
    //   25	50	95	finally
    //   56	61	95	finally
    //   68	90	95	finally
    //   4	21	102	java/lang/Exception
  }
  
  public byte[] d()
  {
    return this.f;
  }
  
  public C.a e()
    throws SpeechError
  {
    Date localDate1 = new Date();
    this.f = MSC.QISVGetResult(this.a, null, this.c);
    Date localDate2 = new Date();
    StringBuilder localStringBuilder = new StringBuilder().append("QISVGetResult leavel:");
    boolean bool;
    int i;
    if (this.f != null)
    {
      bool = true;
      ad.a(bool + " time:" + (localDate2.getTime() - localDate1.getTime()));
      i = this.c.errorcode;
      if (i != 0) {
        break label219;
      }
      i = this.c.rsltstatus;
      switch (i)
      {
      }
    }
    do
    {
      return C.a.c;
      bool = false;
      break;
      ad.a("ResultStatus: noResult" + i);
      throw new SpeechError(20005);
    } while (this.f == null);
    ad.a("ResultStatus: hasResult" + i);
    return C.a.a;
    label219:
    ad.a("Result: error errorcode is " + i);
    throw new SpeechError(i);
  }
  
  protected String f()
  {
    if (this.b == null) {
      this.b = b("sid");
    }
    return this.b;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */