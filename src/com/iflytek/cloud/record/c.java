package com.iflytek.cloud.record;

import android.content.Context;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.thirdparty.ad;

public class c
{
  AudioManager.OnAudioFocusChangeListener a = new AudioManager.OnAudioFocusChangeListener()
  {
    public void onAudioFocusChange(int paramAnonymousInt)
    {
      if ((paramAnonymousInt == -2) || (paramAnonymousInt == -3) || (paramAnonymousInt == -1))
      {
        ad.a("PcmPlayer", "pause start");
        if (c.this.c())
        {
          ad.a("PcmPlayer", "pause success");
          c.a(c.this, true);
          if (c.a(c.this) != null) {
            c.a(c.this).a();
          }
        }
      }
      label72:
      do
      {
        do
        {
          do
          {
            break label72;
            do
            {
              return;
            } while (paramAnonymousInt != 1);
            ad.a("PcmPlayer", "resume start");
          } while (!c.b(c.this));
          c.a(c.this, false);
        } while (!c.this.d());
        ad.a("PcmPlayer", "resume success");
      } while (c.a(c.this) == null);
      c.a(c.this).b();
    }
  };
  private AudioTrack b = null;
  private b c = null;
  private Context d = null;
  private b e = null;
  private a f = null;
  private volatile int g = 0;
  private int h = 3;
  private boolean i = true;
  private int j;
  private boolean k = false;
  private boolean l = false;
  private Object m = new Object();
  private Object n = this;
  private int o = 0;
  private Handler p = new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      }
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return;
              } while (c.a(c.this) == null);
              c.a(c.this).a((SpeechError)paramAnonymousMessage.obj);
              c.a(c.this, null);
              return;
            } while (c.a(c.this) == null);
            c.a(c.this).a();
            return;
          } while (c.a(c.this) == null);
          c.a(c.this).b();
          return;
        } while (c.a(c.this) == null);
        c.a(c.this).a(paramAnonymousMessage.arg1, paramAnonymousMessage.arg2, c.n(c.this));
        return;
      } while (c.a(c.this) == null);
      c.a(c.this).c();
      c.a(c.this, null);
    }
  };
  
  public c(Context paramContext)
  {
    this.d = paramContext;
  }
  
  public c(Context paramContext, int paramInt, boolean paramBoolean)
  {
    this.d = paramContext;
    this.h = paramInt;
    this.k = paramBoolean;
  }
  
  private boolean a(int paramInt1, int paramInt2)
  {
    boolean bool = false;
    synchronized (this.n)
    {
      if (paramInt1 == this.g)
      {
        this.g = paramInt2;
        bool = true;
      }
      return bool;
    }
  }
  
  private void f()
    throws Exception
  {
    ad.a("PcmPlayer", "createAudio start");
    int i1 = this.c.a();
    this.j = AudioTrack.getMinBufferSize(i1, 2, 2);
    if (this.b != null) {
      b();
    }
    ad.a("PcmPlayer", "createAudio || mStreamType = " + this.h);
    this.b = new AudioTrack(this.h, i1, 2, 2, this.j * 2, 1);
    if ((this.j == -2) || (this.j == -1)) {
      throw new Exception();
    }
    ad.a("PcmPlayer", "createAudio end");
  }
  
  private void g()
    throws Exception
  {
    b localb = this.e;
    if ((this.b == null) || ((localb != null) && (localb.a() != this.h)))
    {
      ad.a("PcmPlayer", "prepAudioPlayer || audiotrack is null or stream type is change.");
      f();
      if (localb != null) {
        localb.a(this.h);
      }
    }
  }
  
  public int a()
  {
    return this.g;
  }
  
  public boolean a(b paramb, a parama)
  {
    ad.a("PcmPlayer", "play mPlaytate= " + this.g + ",mAudioFocus= " + this.i);
    boolean bool = true;
    synchronized (this.n)
    {
      if ((this.g != 4) && (this.g != 0) && (this.g != 3) && (this.e != null))
      {
        bool = false;
        return bool;
      }
      this.c = paramb;
      this.f = parama;
      this.e = new b(null);
      this.e.start();
    }
  }
  
  public void b()
  {
    synchronized (this.m)
    {
      if (this.b != null)
      {
        if (this.b.getPlayState() == 3) {
          this.b.stop();
        }
        this.b.release();
        this.b = null;
      }
      ad.a("PcmPlayer", "mAudioTrack released");
      return;
    }
  }
  
  public boolean c()
  {
    if ((this.g == 4) || (this.g == 3)) {
      return false;
    }
    this.g = 3;
    return true;
  }
  
  public boolean d()
  {
    return a(3, 2);
  }
  
  public void e()
  {
    synchronized (this.n)
    {
      this.g = 4;
      return;
    }
  }
  
  public static abstract interface a
  {
    public abstract void a();
    
    public abstract void a(int paramInt1, int paramInt2, int paramInt3);
    
    public abstract void a(SpeechError paramSpeechError);
    
    public abstract void b();
    
    public abstract void c();
  }
  
  private class b
    extends Thread
  {
    private int b = c.c(c.this);
    
    private b() {}
    
    public int a()
    {
      return this.b;
    }
    
    public void a(int paramInt)
    {
      this.b = paramInt;
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: ldc 34
      //   2: ldc 36
      //   4: invokestatic 41	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;Ljava/lang/String;)V
      //   7: ldc 34
      //   9: new 43	java/lang/StringBuilder
      //   12: dup
      //   13: invokespecial 44	java/lang/StringBuilder:<init>	()V
      //   16: ldc 46
      //   18: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   21: aload_0
      //   22: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   25: invokestatic 54	com/iflytek/cloud/record/c:d	(Lcom/iflytek/cloud/record/c;)Z
      //   28: invokevirtual 57	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
      //   31: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   34: invokestatic 41	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;Ljava/lang/String;)V
      //   37: aload_0
      //   38: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   41: invokestatic 54	com/iflytek/cloud/record/c:d	(Lcom/iflytek/cloud/record/c;)Z
      //   44: ifeq +405 -> 449
      //   47: aload_0
      //   48: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   51: invokestatic 65	com/iflytek/cloud/record/c:e	(Lcom/iflytek/cloud/record/c;)Landroid/content/Context;
      //   54: aload_0
      //   55: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   58: invokestatic 68	com/iflytek/cloud/record/c:f	(Lcom/iflytek/cloud/record/c;)Z
      //   61: invokestatic 74	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   64: aload_0
      //   65: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   68: getfield 77	com/iflytek/cloud/record/c:a	Landroid/media/AudioManager$OnAudioFocusChangeListener;
      //   71: invokestatic 82	com/iflytek/cloud/thirdparty/T:a	(Landroid/content/Context;Ljava/lang/Boolean;Landroid/media/AudioManager$OnAudioFocusChangeListener;)Z
      //   74: pop
      //   75: aload_0
      //   76: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   79: invokestatic 86	com/iflytek/cloud/record/c:g	(Lcom/iflytek/cloud/record/c;)Lcom/iflytek/cloud/record/b;
      //   82: invokevirtual 90	com/iflytek/cloud/record/b:c	()V
      //   85: aload_0
      //   86: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   89: invokestatic 94	com/iflytek/cloud/record/c:h	(Lcom/iflytek/cloud/record/c;)Ljava/lang/Object;
      //   92: astore_2
      //   93: aload_2
      //   94: monitorenter
      //   95: aload_0
      //   96: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   99: invokestatic 97	com/iflytek/cloud/record/c:i	(Lcom/iflytek/cloud/record/c;)I
      //   102: iconst_4
      //   103: if_icmpeq +23 -> 126
      //   106: aload_0
      //   107: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   110: invokestatic 97	com/iflytek/cloud/record/c:i	(Lcom/iflytek/cloud/record/c;)I
      //   113: iconst_3
      //   114: if_icmpeq +12 -> 126
      //   117: aload_0
      //   118: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   121: iconst_2
      //   122: invokestatic 100	com/iflytek/cloud/record/c:a	(Lcom/iflytek/cloud/record/c;I)I
      //   125: pop
      //   126: aload_2
      //   127: monitorexit
      //   128: aload_0
      //   129: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   132: invokestatic 97	com/iflytek/cloud/record/c:i	(Lcom/iflytek/cloud/record/c;)I
      //   135: iconst_4
      //   136: if_icmpeq +491 -> 627
      //   139: aload_0
      //   140: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   143: invokestatic 103	com/iflytek/cloud/record/c:j	(Lcom/iflytek/cloud/record/c;)V
      //   146: aload_0
      //   147: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   150: invokestatic 97	com/iflytek/cloud/record/c:i	(Lcom/iflytek/cloud/record/c;)I
      //   153: iconst_2
      //   154: if_icmpeq +14 -> 168
      //   157: aload_0
      //   158: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   161: invokestatic 97	com/iflytek/cloud/record/c:i	(Lcom/iflytek/cloud/record/c;)I
      //   164: iconst_1
      //   165: if_icmpne +622 -> 787
      //   168: aload_0
      //   169: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   172: invokestatic 86	com/iflytek/cloud/record/c:g	(Lcom/iflytek/cloud/record/c;)Lcom/iflytek/cloud/record/b;
      //   175: invokevirtual 106	com/iflytek/cloud/record/b:g	()Z
      //   178: ifeq +408 -> 586
      //   181: aload_0
      //   182: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   185: iconst_1
      //   186: iconst_2
      //   187: invokestatic 109	com/iflytek/cloud/record/c:a	(Lcom/iflytek/cloud/record/c;II)Z
      //   190: ifeq +17 -> 207
      //   193: aload_0
      //   194: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   197: invokestatic 113	com/iflytek/cloud/record/c:k	(Lcom/iflytek/cloud/record/c;)Landroid/os/Handler;
      //   200: iconst_2
      //   201: invokestatic 119	android/os/Message:obtain	(Landroid/os/Handler;I)Landroid/os/Message;
      //   204: invokevirtual 122	android/os/Message:sendToTarget	()V
      //   207: aload_0
      //   208: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   211: invokestatic 86	com/iflytek/cloud/record/c:g	(Lcom/iflytek/cloud/record/c;)Lcom/iflytek/cloud/record/b;
      //   214: invokevirtual 124	com/iflytek/cloud/record/b:d	()I
      //   217: istore_1
      //   218: aload_0
      //   219: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   222: invokestatic 86	com/iflytek/cloud/record/c:g	(Lcom/iflytek/cloud/record/c;)Lcom/iflytek/cloud/record/b;
      //   225: invokevirtual 127	com/iflytek/cloud/record/b:e	()Lcom/iflytek/cloud/record/b$a;
      //   228: astore_2
      //   229: aload_2
      //   230: ifnull +34 -> 264
      //   233: aload_0
      //   234: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   237: aload_2
      //   238: getfield 131	com/iflytek/cloud/record/b$a:d	I
      //   241: invokestatic 133	com/iflytek/cloud/record/c:b	(Lcom/iflytek/cloud/record/c;I)I
      //   244: pop
      //   245: aload_0
      //   246: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   249: invokestatic 113	com/iflytek/cloud/record/c:k	(Lcom/iflytek/cloud/record/c;)Landroid/os/Handler;
      //   252: iconst_3
      //   253: iload_1
      //   254: aload_2
      //   255: getfield 135	com/iflytek/cloud/record/b$a:c	I
      //   258: invokestatic 138	android/os/Message:obtain	(Landroid/os/Handler;III)Landroid/os/Message;
      //   261: invokevirtual 122	android/os/Message:sendToTarget	()V
      //   264: aload_0
      //   265: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   268: invokestatic 142	com/iflytek/cloud/record/c:l	(Lcom/iflytek/cloud/record/c;)Landroid/media/AudioTrack;
      //   271: invokevirtual 147	android/media/AudioTrack:getPlayState	()I
      //   274: iconst_3
      //   275: if_icmpeq +13 -> 288
      //   278: aload_0
      //   279: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   282: invokestatic 142	com/iflytek/cloud/record/c:l	(Lcom/iflytek/cloud/record/c;)Landroid/media/AudioTrack;
      //   285: invokevirtual 150	android/media/AudioTrack:play	()V
      //   288: aload_0
      //   289: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   292: invokestatic 86	com/iflytek/cloud/record/c:g	(Lcom/iflytek/cloud/record/c;)Lcom/iflytek/cloud/record/b;
      //   295: aload_0
      //   296: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   299: invokestatic 142	com/iflytek/cloud/record/c:l	(Lcom/iflytek/cloud/record/c;)Landroid/media/AudioTrack;
      //   302: aload_0
      //   303: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   306: invokestatic 153	com/iflytek/cloud/record/c:m	(Lcom/iflytek/cloud/record/c;)I
      //   309: invokevirtual 156	com/iflytek/cloud/record/b:a	(Landroid/media/AudioTrack;I)V
      //   312: goto -184 -> 128
      //   315: astore_2
      //   316: aload_2
      //   317: invokestatic 159	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/Throwable;)V
      //   320: aload_0
      //   321: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   324: invokestatic 113	com/iflytek/cloud/record/c:k	(Lcom/iflytek/cloud/record/c;)Landroid/os/Handler;
      //   327: iconst_0
      //   328: new 161	com/iflytek/cloud/SpeechError
      //   331: dup
      //   332: sipush 20011
      //   335: invokespecial 163	com/iflytek/cloud/SpeechError:<init>	(I)V
      //   338: invokestatic 166	android/os/Message:obtain	(Landroid/os/Handler;ILjava/lang/Object;)Landroid/os/Message;
      //   341: invokevirtual 122	android/os/Message:sendToTarget	()V
      //   344: aload_0
      //   345: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   348: invokestatic 94	com/iflytek/cloud/record/c:h	(Lcom/iflytek/cloud/record/c;)Ljava/lang/Object;
      //   351: astore_2
      //   352: aload_2
      //   353: monitorenter
      //   354: aload_0
      //   355: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   358: iconst_4
      //   359: invokestatic 100	com/iflytek/cloud/record/c:a	(Lcom/iflytek/cloud/record/c;I)I
      //   362: pop
      //   363: aload_2
      //   364: monitorexit
      //   365: aload_0
      //   366: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   369: invokestatic 142	com/iflytek/cloud/record/c:l	(Lcom/iflytek/cloud/record/c;)Landroid/media/AudioTrack;
      //   372: ifnull +22 -> 394
      //   375: aload_0
      //   376: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   379: invokestatic 142	com/iflytek/cloud/record/c:l	(Lcom/iflytek/cloud/record/c;)Landroid/media/AudioTrack;
      //   382: invokevirtual 169	android/media/AudioTrack:release	()V
      //   385: aload_0
      //   386: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   389: aconst_null
      //   390: invokestatic 172	com/iflytek/cloud/record/c:a	(Lcom/iflytek/cloud/record/c;Landroid/media/AudioTrack;)Landroid/media/AudioTrack;
      //   393: pop
      //   394: aload_0
      //   395: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   398: invokestatic 54	com/iflytek/cloud/record/c:d	(Lcom/iflytek/cloud/record/c;)Z
      //   401: ifeq +465 -> 866
      //   404: aload_0
      //   405: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   408: invokestatic 65	com/iflytek/cloud/record/c:e	(Lcom/iflytek/cloud/record/c;)Landroid/content/Context;
      //   411: aload_0
      //   412: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   415: invokestatic 68	com/iflytek/cloud/record/c:f	(Lcom/iflytek/cloud/record/c;)Z
      //   418: invokestatic 74	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   421: aload_0
      //   422: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   425: getfield 77	com/iflytek/cloud/record/c:a	Landroid/media/AudioManager$OnAudioFocusChangeListener;
      //   428: invokestatic 174	com/iflytek/cloud/thirdparty/T:b	(Landroid/content/Context;Ljava/lang/Boolean;Landroid/media/AudioManager$OnAudioFocusChangeListener;)Z
      //   431: pop
      //   432: aload_0
      //   433: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   436: aconst_null
      //   437: invokestatic 177	com/iflytek/cloud/record/c:a	(Lcom/iflytek/cloud/record/c;Lcom/iflytek/cloud/record/c$b;)Lcom/iflytek/cloud/record/c$b;
      //   440: pop
      //   441: ldc 34
      //   443: ldc -77
      //   445: invokestatic 41	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;Ljava/lang/String;)V
      //   448: return
      //   449: aload_0
      //   450: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   453: invokestatic 65	com/iflytek/cloud/record/c:e	(Lcom/iflytek/cloud/record/c;)Landroid/content/Context;
      //   456: aload_0
      //   457: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   460: invokestatic 68	com/iflytek/cloud/record/c:f	(Lcom/iflytek/cloud/record/c;)Z
      //   463: invokestatic 74	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   466: aconst_null
      //   467: invokestatic 82	com/iflytek/cloud/thirdparty/T:a	(Landroid/content/Context;Ljava/lang/Boolean;Landroid/media/AudioManager$OnAudioFocusChangeListener;)Z
      //   470: pop
      //   471: goto -396 -> 75
      //   474: astore_3
      //   475: aload_0
      //   476: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   479: invokestatic 94	com/iflytek/cloud/record/c:h	(Lcom/iflytek/cloud/record/c;)Ljava/lang/Object;
      //   482: astore_2
      //   483: aload_2
      //   484: monitorenter
      //   485: aload_0
      //   486: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   489: iconst_4
      //   490: invokestatic 100	com/iflytek/cloud/record/c:a	(Lcom/iflytek/cloud/record/c;I)I
      //   493: pop
      //   494: aload_2
      //   495: monitorexit
      //   496: aload_0
      //   497: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   500: invokestatic 142	com/iflytek/cloud/record/c:l	(Lcom/iflytek/cloud/record/c;)Landroid/media/AudioTrack;
      //   503: ifnull +22 -> 525
      //   506: aload_0
      //   507: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   510: invokestatic 142	com/iflytek/cloud/record/c:l	(Lcom/iflytek/cloud/record/c;)Landroid/media/AudioTrack;
      //   513: invokevirtual 169	android/media/AudioTrack:release	()V
      //   516: aload_0
      //   517: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   520: aconst_null
      //   521: invokestatic 172	com/iflytek/cloud/record/c:a	(Lcom/iflytek/cloud/record/c;Landroid/media/AudioTrack;)Landroid/media/AudioTrack;
      //   524: pop
      //   525: aload_0
      //   526: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   529: invokestatic 54	com/iflytek/cloud/record/c:d	(Lcom/iflytek/cloud/record/c;)Z
      //   532: ifeq +304 -> 836
      //   535: aload_0
      //   536: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   539: invokestatic 65	com/iflytek/cloud/record/c:e	(Lcom/iflytek/cloud/record/c;)Landroid/content/Context;
      //   542: aload_0
      //   543: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   546: invokestatic 68	com/iflytek/cloud/record/c:f	(Lcom/iflytek/cloud/record/c;)Z
      //   549: invokestatic 74	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   552: aload_0
      //   553: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   556: getfield 77	com/iflytek/cloud/record/c:a	Landroid/media/AudioManager$OnAudioFocusChangeListener;
      //   559: invokestatic 174	com/iflytek/cloud/thirdparty/T:b	(Landroid/content/Context;Ljava/lang/Boolean;Landroid/media/AudioManager$OnAudioFocusChangeListener;)Z
      //   562: pop
      //   563: aload_0
      //   564: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   567: aconst_null
      //   568: invokestatic 177	com/iflytek/cloud/record/c:a	(Lcom/iflytek/cloud/record/c;Lcom/iflytek/cloud/record/c$b;)Lcom/iflytek/cloud/record/c$b;
      //   571: pop
      //   572: ldc 34
      //   574: ldc -77
      //   576: invokestatic 41	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;Ljava/lang/String;)V
      //   579: aload_3
      //   580: athrow
      //   581: astore_3
      //   582: aload_2
      //   583: monitorexit
      //   584: aload_3
      //   585: athrow
      //   586: aload_0
      //   587: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   590: invokestatic 86	com/iflytek/cloud/record/c:g	(Lcom/iflytek/cloud/record/c;)Lcom/iflytek/cloud/record/b;
      //   593: invokevirtual 181	com/iflytek/cloud/record/b:f	()Z
      //   596: ifeq +151 -> 747
      //   599: ldc -73
      //   601: invokestatic 186	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
      //   604: aload_0
      //   605: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   608: iconst_4
      //   609: invokestatic 100	com/iflytek/cloud/record/c:a	(Lcom/iflytek/cloud/record/c;I)I
      //   612: pop
      //   613: aload_0
      //   614: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   617: invokestatic 113	com/iflytek/cloud/record/c:k	(Lcom/iflytek/cloud/record/c;)Landroid/os/Handler;
      //   620: iconst_4
      //   621: invokestatic 119	android/os/Message:obtain	(Landroid/os/Handler;I)Landroid/os/Message;
      //   624: invokevirtual 122	android/os/Message:sendToTarget	()V
      //   627: aload_0
      //   628: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   631: invokestatic 142	com/iflytek/cloud/record/c:l	(Lcom/iflytek/cloud/record/c;)Landroid/media/AudioTrack;
      //   634: ifnull +13 -> 647
      //   637: aload_0
      //   638: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   641: invokestatic 142	com/iflytek/cloud/record/c:l	(Lcom/iflytek/cloud/record/c;)Landroid/media/AudioTrack;
      //   644: invokevirtual 189	android/media/AudioTrack:stop	()V
      //   647: aload_0
      //   648: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   651: invokestatic 94	com/iflytek/cloud/record/c:h	(Lcom/iflytek/cloud/record/c;)Ljava/lang/Object;
      //   654: astore_2
      //   655: aload_2
      //   656: monitorenter
      //   657: aload_0
      //   658: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   661: iconst_4
      //   662: invokestatic 100	com/iflytek/cloud/record/c:a	(Lcom/iflytek/cloud/record/c;I)I
      //   665: pop
      //   666: aload_2
      //   667: monitorexit
      //   668: aload_0
      //   669: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   672: invokestatic 142	com/iflytek/cloud/record/c:l	(Lcom/iflytek/cloud/record/c;)Landroid/media/AudioTrack;
      //   675: ifnull +22 -> 697
      //   678: aload_0
      //   679: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   682: invokestatic 142	com/iflytek/cloud/record/c:l	(Lcom/iflytek/cloud/record/c;)Landroid/media/AudioTrack;
      //   685: invokevirtual 169	android/media/AudioTrack:release	()V
      //   688: aload_0
      //   689: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   692: aconst_null
      //   693: invokestatic 172	com/iflytek/cloud/record/c:a	(Lcom/iflytek/cloud/record/c;Landroid/media/AudioTrack;)Landroid/media/AudioTrack;
      //   696: pop
      //   697: aload_0
      //   698: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   701: invokestatic 54	com/iflytek/cloud/record/c:d	(Lcom/iflytek/cloud/record/c;)Z
      //   704: ifeq +192 -> 896
      //   707: aload_0
      //   708: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   711: invokestatic 65	com/iflytek/cloud/record/c:e	(Lcom/iflytek/cloud/record/c;)Landroid/content/Context;
      //   714: aload_0
      //   715: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   718: invokestatic 68	com/iflytek/cloud/record/c:f	(Lcom/iflytek/cloud/record/c;)Z
      //   721: invokestatic 74	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   724: aload_0
      //   725: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   728: getfield 77	com/iflytek/cloud/record/c:a	Landroid/media/AudioManager$OnAudioFocusChangeListener;
      //   731: invokestatic 174	com/iflytek/cloud/thirdparty/T:b	(Landroid/content/Context;Ljava/lang/Boolean;Landroid/media/AudioManager$OnAudioFocusChangeListener;)Z
      //   734: pop
      //   735: aload_0
      //   736: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   739: aconst_null
      //   740: invokestatic 177	com/iflytek/cloud/record/c:a	(Lcom/iflytek/cloud/record/c;Lcom/iflytek/cloud/record/c$b;)Lcom/iflytek/cloud/record/c$b;
      //   743: pop
      //   744: goto -303 -> 441
      //   747: aload_0
      //   748: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   751: iconst_2
      //   752: iconst_1
      //   753: invokestatic 109	com/iflytek/cloud/record/c:a	(Lcom/iflytek/cloud/record/c;II)Z
      //   756: ifeq +22 -> 778
      //   759: ldc -65
      //   761: invokestatic 186	com/iflytek/cloud/thirdparty/ad:a	(Ljava/lang/String;)V
      //   764: aload_0
      //   765: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   768: invokestatic 113	com/iflytek/cloud/record/c:k	(Lcom/iflytek/cloud/record/c;)Landroid/os/Handler;
      //   771: iconst_1
      //   772: invokestatic 119	android/os/Message:obtain	(Landroid/os/Handler;I)Landroid/os/Message;
      //   775: invokevirtual 122	android/os/Message:sendToTarget	()V
      //   778: ldc2_w 192
      //   781: invokestatic 197	com/iflytek/cloud/record/c$b:sleep	(J)V
      //   784: goto -656 -> 128
      //   787: aload_0
      //   788: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   791: invokestatic 97	com/iflytek/cloud/record/c:i	(Lcom/iflytek/cloud/record/c;)I
      //   794: iconst_3
      //   795: if_icmpne -667 -> 128
      //   798: iconst_2
      //   799: aload_0
      //   800: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   803: invokestatic 142	com/iflytek/cloud/record/c:l	(Lcom/iflytek/cloud/record/c;)Landroid/media/AudioTrack;
      //   806: invokevirtual 147	android/media/AudioTrack:getPlayState	()I
      //   809: if_icmpeq +13 -> 822
      //   812: aload_0
      //   813: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   816: invokestatic 142	com/iflytek/cloud/record/c:l	(Lcom/iflytek/cloud/record/c;)Landroid/media/AudioTrack;
      //   819: invokevirtual 200	android/media/AudioTrack:pause	()V
      //   822: ldc2_w 192
      //   825: invokestatic 197	com/iflytek/cloud/record/c$b:sleep	(J)V
      //   828: goto -700 -> 128
      //   831: astore_3
      //   832: aload_2
      //   833: monitorexit
      //   834: aload_3
      //   835: athrow
      //   836: aload_0
      //   837: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   840: invokestatic 65	com/iflytek/cloud/record/c:e	(Lcom/iflytek/cloud/record/c;)Landroid/content/Context;
      //   843: aload_0
      //   844: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   847: invokestatic 68	com/iflytek/cloud/record/c:f	(Lcom/iflytek/cloud/record/c;)Z
      //   850: invokestatic 74	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   853: aconst_null
      //   854: invokestatic 174	com/iflytek/cloud/thirdparty/T:b	(Landroid/content/Context;Ljava/lang/Boolean;Landroid/media/AudioManager$OnAudioFocusChangeListener;)Z
      //   857: pop
      //   858: goto -295 -> 563
      //   861: astore_3
      //   862: aload_2
      //   863: monitorexit
      //   864: aload_3
      //   865: athrow
      //   866: aload_0
      //   867: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   870: invokestatic 65	com/iflytek/cloud/record/c:e	(Lcom/iflytek/cloud/record/c;)Landroid/content/Context;
      //   873: aload_0
      //   874: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   877: invokestatic 68	com/iflytek/cloud/record/c:f	(Lcom/iflytek/cloud/record/c;)Z
      //   880: invokestatic 74	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   883: aconst_null
      //   884: invokestatic 174	com/iflytek/cloud/thirdparty/T:b	(Landroid/content/Context;Ljava/lang/Boolean;Landroid/media/AudioManager$OnAudioFocusChangeListener;)Z
      //   887: pop
      //   888: goto -456 -> 432
      //   891: astore_3
      //   892: aload_2
      //   893: monitorexit
      //   894: aload_3
      //   895: athrow
      //   896: aload_0
      //   897: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   900: invokestatic 65	com/iflytek/cloud/record/c:e	(Lcom/iflytek/cloud/record/c;)Landroid/content/Context;
      //   903: aload_0
      //   904: getfield 14	com/iflytek/cloud/record/c$b:a	Lcom/iflytek/cloud/record/c;
      //   907: invokestatic 68	com/iflytek/cloud/record/c:f	(Lcom/iflytek/cloud/record/c;)Z
      //   910: invokestatic 74	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   913: aconst_null
      //   914: invokestatic 174	com/iflytek/cloud/thirdparty/T:b	(Landroid/content/Context;Ljava/lang/Boolean;Landroid/media/AudioManager$OnAudioFocusChangeListener;)Z
      //   917: pop
      //   918: goto -183 -> 735
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	921	0	this	b
      //   217	37	1	i	int
      //   315	2	2	localException	Exception
      //   474	106	3	localObject3	Object
      //   581	4	3	localObject4	Object
      //   831	4	3	localObject5	Object
      //   861	4	3	localObject6	Object
      //   891	4	3	localObject7	Object
      // Exception table:
      //   from	to	target	type
      //   0	75	315	java/lang/Exception
      //   75	95	315	java/lang/Exception
      //   128	168	315	java/lang/Exception
      //   168	207	315	java/lang/Exception
      //   207	229	315	java/lang/Exception
      //   233	264	315	java/lang/Exception
      //   264	288	315	java/lang/Exception
      //   288	312	315	java/lang/Exception
      //   449	471	315	java/lang/Exception
      //   584	586	315	java/lang/Exception
      //   586	627	315	java/lang/Exception
      //   627	647	315	java/lang/Exception
      //   747	778	315	java/lang/Exception
      //   778	784	315	java/lang/Exception
      //   787	822	315	java/lang/Exception
      //   822	828	315	java/lang/Exception
      //   0	75	474	finally
      //   75	95	474	finally
      //   128	168	474	finally
      //   168	207	474	finally
      //   207	229	474	finally
      //   233	264	474	finally
      //   264	288	474	finally
      //   288	312	474	finally
      //   316	344	474	finally
      //   449	471	474	finally
      //   584	586	474	finally
      //   586	627	474	finally
      //   627	647	474	finally
      //   747	778	474	finally
      //   778	784	474	finally
      //   787	822	474	finally
      //   822	828	474	finally
      //   95	126	581	finally
      //   126	128	581	finally
      //   582	584	581	finally
      //   485	496	831	finally
      //   832	834	831	finally
      //   354	365	861	finally
      //   862	864	861	finally
      //   657	668	891	finally
      //   892	894	891	finally
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\record\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */