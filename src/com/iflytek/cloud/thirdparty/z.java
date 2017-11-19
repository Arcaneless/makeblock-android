package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.SystemClock;
import com.iflytek.cloud.SpeechError;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;

public class z
  extends C
{
  private MSCSessionInfo c = new MSCSessionInfo();
  private MSCSessionInfo d = new MSCSessionInfo();
  private byte[] e = null;
  
  private void a(String paramString, byte[] paramArrayOfByte, int paramInt)
    throws SpeechError
  {
    try
    {
      int i = MSC.QMFVDataWrite(this.a, paramString.getBytes(), paramArrayOfByte, paramInt, this.d);
      this.c.sesstatus = this.d.sesstatus;
      ad.a("QISRAudioWrite length:" + paramInt);
      if (i != 0) {
        throw new SpeechError(this.d.errorcode);
      }
    }
    finally {}
  }
  
  /* Error */
  public int a()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 30	com/iflytek/cloud/thirdparty/z:a	[C
    //   8: ldc 79
    //   10: invokevirtual 36	java/lang/String:getBytes	()[B
    //   13: aload_0
    //   14: getfield 20	com/iflytek/cloud/thirdparty/z:d	Lcom/iflytek/msc/MSCSessionInfo;
    //   17: invokestatic 83	com/iflytek/msc/MSC:QMFVGetParam	([C[BLcom/iflytek/msc/MSCSessionInfo;)I
    //   20: istore_1
    //   21: iload_1
    //   22: ifne +34 -> 56
    //   25: new 32	java/lang/String
    //   28: dup
    //   29: new 32	java/lang/String
    //   32: dup
    //   33: aload_0
    //   34: getfield 20	com/iflytek/cloud/thirdparty/z:d	Lcom/iflytek/msc/MSCSessionInfo;
    //   37: getfield 86	com/iflytek/msc/MSCSessionInfo:buffer	[B
    //   40: invokespecial 89	java/lang/String:<init>	([B)V
    //   43: invokespecial 91	java/lang/String:<init>	(Ljava/lang/String;)V
    //   46: invokestatic 97	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   49: istore_3
    //   50: iload_3
    //   51: istore_1
    //   52: aload_0
    //   53: monitorexit
    //   54: iload_1
    //   55: ireturn
    //   56: ldc 99
    //   58: invokestatic 67	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
    //   61: iload_2
    //   62: istore_1
    //   63: goto -11 -> 52
    //   66: astore 4
    //   68: new 48	java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial 49	java/lang/StringBuilder:<init>	()V
    //   75: ldc 101
    //   77: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: iload_1
    //   81: invokevirtual 58	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   84: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: invokestatic 67	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
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
    //   0	109	0	this	z
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
  
  public int a(Context paramContext, String paramString, B paramB)
    throws SpeechError, UnsupportedEncodingException
  {
    paramContext = ai.c(paramContext, paramString, paramB);
    ad.c("sessionBegin Params:" + paramContext);
    long l = SystemClock.elapsedRealtime();
    ae.a("MSCSessionBegin", null);
    int i;
    try
    {
      this.a = MSC.QMFVSessionBegin(paramContext.getBytes(paramB.p()), this.c);
      ae.a("SessionBeginEnd", null);
      ad.a("sessionBegin ErrCode:" + this.c.errorcode + " time:" + (SystemClock.elapsedRealtime() - l));
      i = this.c.errorcode;
      if ((i != 0) && (i != 10129) && (i != 10113) && (i != 10132)) {
        throw new SpeechError(i);
      }
    }
    finally {}
    return i;
  }
  
  public void a(String paramString)
  {
    if (this.a == null) {
      return;
    }
    ad.a("sessionEnd enter ");
    long l = System.currentTimeMillis();
    if (MSC.QMFVSessionEnd(this.a, paramString.getBytes()) == 0) {}
    for (boolean bool = true;; bool = false)
    {
      ad.a("sessionEnd leavel:" + bool + " time:" + (System.currentTimeMillis() - l));
      this.a = null;
      this.b = null;
      return;
    }
  }
  
  /* Error */
  public void a(StringBuffer paramStringBuffer, byte[] paramArrayOfByte, int paramInt, boolean paramBoolean)
    throws SpeechError
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 170	java/lang/StringBuffer
    //   5: dup
    //   6: aload_1
    //   7: invokevirtual 171	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   10: invokespecial 172	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
    //   13: astore_1
    //   14: aload_1
    //   15: ldc -82
    //   17: invokevirtual 177	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   20: pop
    //   21: iload 4
    //   23: ifeq +47 -> 70
    //   26: aload_1
    //   27: iconst_1
    //   28: invokevirtual 180	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   31: pop
    //   32: new 48	java/lang/StringBuilder
    //   35: dup
    //   36: invokespecial 49	java/lang/StringBuilder:<init>	()V
    //   39: ldc -74
    //   41: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: aload_1
    //   45: invokevirtual 171	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   48: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: invokestatic 113	com/iflytek/cloud/thirdparty/ad:c	(Ljava/lang/String;)V
    //   57: aload_0
    //   58: aload_1
    //   59: invokevirtual 171	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   62: aload_2
    //   63: iload_3
    //   64: invokespecial 184	com/iflytek/cloud/thirdparty/z:a	(Ljava/lang/String;[BI)V
    //   67: aload_0
    //   68: monitorexit
    //   69: return
    //   70: aload_1
    //   71: iconst_2
    //   72: invokevirtual 180	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   75: pop
    //   76: goto -44 -> 32
    //   79: astore_1
    //   80: aload_0
    //   81: monitorexit
    //   82: aload_1
    //   83: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	z
    //   0	84	1	paramStringBuffer	StringBuffer
    //   0	84	2	paramArrayOfByte	byte[]
    //   0	84	3	paramInt	int
    //   0	84	4	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   2	21	79	finally
    //   26	32	79	finally
    //   32	67	79	finally
    //   70	76	79	finally
  }
  
  /* Error */
  public boolean a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: aload_0
    //   4: monitorenter
    //   5: iload 5
    //   7: istore 4
    //   9: aload_1
    //   10: invokestatic 191	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   13: ifne +29 -> 42
    //   16: iload 5
    //   18: istore 4
    //   20: aload_2
    //   21: invokestatic 191	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   24: ifne +18 -> 42
    //   27: aload_0
    //   28: getfield 30	com/iflytek/cloud/thirdparty/z:a	[C
    //   31: astore 6
    //   33: aload 6
    //   35: ifnonnull +12 -> 47
    //   38: iload 5
    //   40: istore 4
    //   42: aload_0
    //   43: monitorexit
    //   44: iload 4
    //   46: ireturn
    //   47: aload_0
    //   48: getfield 30	com/iflytek/cloud/thirdparty/z:a	[C
    //   51: aload_1
    //   52: ldc -63
    //   54: invokevirtual 134	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   57: aload_2
    //   58: ldc -63
    //   60: invokevirtual 134	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   63: invokestatic 197	com/iflytek/msc/MSC:QMFVSetParam	([C[B[B)I
    //   66: istore_3
    //   67: iload 5
    //   69: istore 4
    //   71: iload_3
    //   72: ifne -30 -> 42
    //   75: iconst_1
    //   76: istore 4
    //   78: goto -36 -> 42
    //   81: astore_1
    //   82: aload_1
    //   83: invokestatic 200	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/Throwable;)V
    //   86: iconst_m1
    //   87: istore_3
    //   88: goto -21 -> 67
    //   91: astore_1
    //   92: aload_0
    //   93: monitorexit
    //   94: aload_1
    //   95: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	this	z
    //   0	96	1	paramString1	String
    //   0	96	2	paramString2	String
    //   66	22	3	i	int
    //   7	70	4	bool1	boolean
    //   1	67	5	bool2	boolean
    //   31	3	6	arrayOfChar	char[]
    // Exception table:
    //   from	to	target	type
    //   47	67	81	java/io/UnsupportedEncodingException
    //   9	16	91	finally
    //   20	33	91	finally
    //   47	67	91	finally
    //   82	86	91	finally
  }
  
  protected String b()
  {
    if (this.b == null) {
      this.b = d("sid");
    }
    return this.b;
  }
  
  public void b(String paramString)
    throws SpeechError
  {
    try
    {
      ae.a("LastDataFlag", null);
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("ssub=" + paramString);
      localStringBuffer.append(",data_status=4");
      ad.a("pushEndFlag, param:" + localStringBuffer.toString());
      a(localStringBuffer.toString(), new byte[0], 0);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  /* Error */
  public int c(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 30	com/iflytek/cloud/thirdparty/z:a	[C
    //   8: astore 4
    //   10: aload 4
    //   12: ifnonnull +9 -> 21
    //   15: iload_3
    //   16: istore_2
    //   17: aload_0
    //   18: monitorexit
    //   19: iload_2
    //   20: ireturn
    //   21: aload_0
    //   22: aload_1
    //   23: invokevirtual 205	com/iflytek/cloud/thirdparty/z:d	(Ljava/lang/String;)Ljava/lang/String;
    //   26: astore_1
    //   27: iload_3
    //   28: istore_2
    //   29: aload_1
    //   30: invokestatic 191	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   33: ifne -16 -> 17
    //   36: new 32	java/lang/String
    //   39: dup
    //   40: aload_1
    //   41: invokespecial 91	java/lang/String:<init>	(Ljava/lang/String;)V
    //   44: invokestatic 97	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   47: istore_2
    //   48: goto -31 -> 17
    //   51: astore_1
    //   52: aload_1
    //   53: invokestatic 200	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/Throwable;)V
    //   56: iload_3
    //   57: istore_2
    //   58: goto -41 -> 17
    //   61: astore_1
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_1
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	z
    //   0	66	1	paramString	String
    //   16	42	2	i	int
    //   1	56	3	j	int
    //   8	3	4	arrayOfChar	char[]
    // Exception table:
    //   from	to	target	type
    //   21	27	51	java/lang/Exception
    //   29	48	51	java/lang/Exception
    //   4	10	61	finally
    //   21	27	61	finally
    //   29	48	61	finally
    //   52	56	61	finally
  }
  
  public String d(String paramString)
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
          if (MSC.QMFVGetParam(this.a, paramString.getBytes(), this.c) != 0) {
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
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\z.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */