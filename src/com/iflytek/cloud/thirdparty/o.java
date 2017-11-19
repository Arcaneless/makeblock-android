package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.GrammarListener;
import com.iflytek.cloud.LexiconListener;
import com.iflytek.cloud.SpeechError;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;

public class o
  extends C
{
  private static GrammarListener f;
  private static LexiconListener g;
  private MSCSessionInfo c = new MSCSessionInfo();
  private MSCSessionInfo d = new MSCSessionInfo();
  private byte[] e = null;
  private String h = "";
  
  private void a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SpeechError
  {
    try
    {
      paramInt2 = MSC.QISRAudioWrite(this.a, paramArrayOfByte, paramInt1, paramInt2, this.d);
      this.c.sesstatus = this.d.sesstatus;
      ad.a("QISRAudioWrite length:" + paramInt1);
      if (paramInt2 != 0) {
        throw new SpeechError(this.d.errorcode);
      }
    }
    finally {}
  }
  
  public int a(Context paramContext, String paramString, B paramB)
    throws SpeechError, UnsupportedEncodingException
  {
    paramContext = ai.a(paramContext, paramString, paramB);
    long l = SystemClock.elapsedRealtime();
    try
    {
      ae.a("MSCSessionBegin", null);
      if (TextUtils.isEmpty(paramString))
      {
        ad.a(paramContext);
        this.a = MSC.QISRSessionBegin(null, paramContext.getBytes(paramB.p()), this.c);
      }
      int i;
      for (;;)
      {
        ae.a("SessionBeginEnd", null);
        ad.a("sessionBegin ErrCode:" + this.c.errorcode + " time:" + (SystemClock.elapsedRealtime() - l));
        i = this.c.errorcode;
        if ((i == 0) || (i == 10129) || (i == 10113) || (i == 10132)) {
          break;
        }
        throw new SpeechError(i);
        this.a = MSC.QISRSessionBegin(paramString.getBytes(paramB.p()), paramContext.getBytes(paramB.p()), this.c);
        ad.a("sessionBegin grammarId:" + paramString);
      }
      return i;
    }
    finally {}
  }
  
  public int a(String paramString1, String paramString2, GrammarListener paramGrammarListener, ag paramag)
  {
    f = paramGrammarListener;
    paramGrammarListener = paramag.toString();
    String str = paramag.b("text_encoding", "utf-8");
    paramag = paramag.b("pte", "utf-8");
    try
    {
      paramString2 = paramString2.getBytes(str);
      ae.a("LastDataFlag", null);
      int i = MSC.QISRBuildGrammar(paramString1.getBytes(paramag), paramString2, paramString2.length, paramGrammarListener.getBytes(paramag), "grammarCallBack", this);
      return i;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      ad.a(paramString1);
    }
    return 20012;
  }
  
  public int a(String paramString1, String paramString2, LexiconListener paramLexiconListener, ag paramag)
  {
    g = paramLexiconListener;
    this.h = paramString1;
    paramag.a("text_encoding", "utf-8", false);
    String str = paramag.b("text_encoding", "utf-8");
    paramLexiconListener = paramag.b("pte", "utf-8");
    paramag = paramag.toString();
    try
    {
      paramString2 = paramString2.getBytes(str);
      ae.a("LastDataFlag", null);
      int i = MSC.QISRUpdateLexicon(paramString1.getBytes(paramLexiconListener), paramString2, paramString2.length, paramag.getBytes(paramLexiconListener), "lexiconCallBack", this);
      return i;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      ad.a(paramString1);
    }
    return 20012;
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
    if (MSC.QISRSessionEnd(this.a, paramString.getBytes()) == 0) {}
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
    //   10: invokestatic 105	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   13: ifne +29 -> 42
    //   16: iload 5
    //   18: istore 4
    //   20: aload_2
    //   21: invokestatic 105	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   24: ifne +18 -> 42
    //   27: aload_0
    //   28: getfield 40	com/iflytek/cloud/thirdparty/o:a	[C
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
    //   48: getfield 40	com/iflytek/cloud/thirdparty/o:a	[C
    //   51: aload_1
    //   52: ldc -115
    //   54: invokevirtual 116	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   57: aload_2
    //   58: ldc -115
    //   60: invokevirtual 116	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   63: invokestatic 198	com/iflytek/msc/MSC:QISRSetParam	([C[B[B)I
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
    //   83: invokestatic 158	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/Throwable;)V
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
    //   0	96	0	this	o
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
  
  /* Error */
  public int b()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 40	com/iflytek/cloud/thirdparty/o:a	[C
    //   8: ldc -53
    //   10: invokevirtual 181	java/lang/String:getBytes	()[B
    //   13: aload_0
    //   14: getfield 26	com/iflytek/cloud/thirdparty/o:d	Lcom/iflytek/msc/MSCSessionInfo;
    //   17: invokestatic 207	com/iflytek/msc/MSC:QISRGetParam	([C[BLcom/iflytek/msc/MSCSessionInfo;)I
    //   20: istore_1
    //   21: iload_1
    //   22: ifne +34 -> 56
    //   25: new 112	java/lang/String
    //   28: dup
    //   29: new 112	java/lang/String
    //   32: dup
    //   33: aload_0
    //   34: getfield 26	com/iflytek/cloud/thirdparty/o:d	Lcom/iflytek/msc/MSCSessionInfo;
    //   37: getfield 210	com/iflytek/msc/MSCSessionInfo:buffer	[B
    //   40: invokespecial 213	java/lang/String:<init>	([B)V
    //   43: invokespecial 215	java/lang/String:<init>	(Ljava/lang/String;)V
    //   46: invokestatic 221	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   49: istore_3
    //   50: iload_3
    //   51: istore_1
    //   52: aload_0
    //   53: monitorexit
    //   54: iload_1
    //   55: ireturn
    //   56: ldc -33
    //   58: invokestatic 71	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
    //   61: iload_2
    //   62: istore_1
    //   63: goto -11 -> 52
    //   66: astore 4
    //   68: new 52	java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial 53	java/lang/StringBuilder:<init>	()V
    //   75: ldc -31
    //   77: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: iload_1
    //   81: invokevirtual 62	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   84: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: invokestatic 71	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
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
    //   0	109	0	this	o
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
  
  /* Error */
  public int b(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 40	com/iflytek/cloud/thirdparty/o:a	[C
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
    //   23: invokevirtual 228	com/iflytek/cloud/thirdparty/o:c	(Ljava/lang/String;)Ljava/lang/String;
    //   26: astore_1
    //   27: iload_3
    //   28: istore_2
    //   29: aload_1
    //   30: invokestatic 105	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   33: ifne -16 -> 17
    //   36: new 112	java/lang/String
    //   39: dup
    //   40: aload_1
    //   41: invokespecial 215	java/lang/String:<init>	(Ljava/lang/String;)V
    //   44: invokestatic 221	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   47: istore_2
    //   48: goto -31 -> 17
    //   51: astore_1
    //   52: aload_1
    //   53: invokestatic 158	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/Throwable;)V
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
    //   0	66	0	this	o
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
  
  protected String c()
  {
    if (this.b == null) {
      this.b = c("sid");
    }
    return this.b;
  }
  
  public String c(String paramString)
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
          if (MSC.QISRGetParam(this.a, paramString.getBytes(), this.c) != 0) {
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
  
  protected String d()
  {
    return c("audio_url");
  }
  
  int grammarCallBack(int paramInt, char[] paramArrayOfChar)
  {
    Object localObject = null;
    ae.a("GetNotifyResult", null);
    if (f != null)
    {
      if (paramInt == 0) {
        break label53;
      }
      GrammarListener localGrammarListener = f;
      if (paramInt != 0) {
        break label41;
      }
      paramArrayOfChar = (char[])localObject;
      localGrammarListener.onBuildFinish("", paramArrayOfChar);
    }
    for (;;)
    {
      return 0;
      label41:
      paramArrayOfChar = new SpeechError(paramInt);
      break;
      label53:
      f.onBuildFinish(String.valueOf(paramArrayOfChar), null);
    }
  }
  
  int lexiconCallBack(int paramInt, char[] paramArrayOfChar)
  {
    paramArrayOfChar = null;
    ae.a("GetNotifyResult", null);
    if (g != null)
    {
      if (paramInt == 0) {
        break label55;
      }
      LexiconListener localLexiconListener = g;
      String str = this.h;
      if (paramInt != 0) {
        break label43;
      }
      localLexiconListener.onLexiconUpdated(str, paramArrayOfChar);
    }
    for (;;)
    {
      return 0;
      label43:
      paramArrayOfChar = new SpeechError(paramInt);
      break;
      label55:
      g.onLexiconUpdated(this.h, null);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */