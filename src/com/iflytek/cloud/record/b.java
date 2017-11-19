package com.iflytek.cloud.record;

import android.content.Context;
import android.media.AudioTrack;
import android.os.MemoryFile;
import com.iflytek.cloud.thirdparty.S;
import com.iflytek.cloud.thirdparty.ad;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class b
{
  private final int a = 2;
  private final int b = 1;
  private final int c = 16000;
  private final int d = 60;
  private final int e = 1920000;
  private int f = 1920000;
  private ArrayList<a> g = null;
  private Context h = null;
  private int i = 16000;
  private volatile long j = 0L;
  private MemoryFile k = null;
  private volatile long l = 0L;
  private volatile int m = 0;
  private a n = null;
  private String o = "";
  private String p = null;
  private byte[] q = null;
  private int r = 0;
  private int s = 0;
  private int t = 100;
  private final float u = 0.95F;
  
  public b(Context paramContext, int paramInt1, int paramInt2, String paramString, int paramInt3)
  {
    this.h = paramContext;
    this.j = 0L;
    this.g = new ArrayList();
    this.l = 0L;
    this.i = paramInt1;
    this.p = paramString;
    this.t = paramInt3;
    this.f = (this.i * 2 * 1 * paramInt2 + 1920000);
    ad.a("min audio seconds: " + paramInt2 + ", max audio buf size: " + this.f);
  }
  
  private void a(byte[] paramArrayOfByte)
    throws IOException
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
      return;
    }
    try
    {
      if (this.k == null)
      {
        this.o = i();
        this.k = new MemoryFile(this.o, this.f);
        this.k.allowPurging(false);
      }
      this.k.writeBytes(paramArrayOfByte, 0, (int)this.l, paramArrayOfByte.length);
      this.l += paramArrayOfByte.length;
      return;
    }
    finally {}
  }
  
  private void b(int paramInt)
    throws IOException
  {
    if (this.q == null) {
      this.q = new byte[paramInt * 10];
    }
    int i1 = this.q.length;
    paramInt = (int)(this.l - this.m);
    if (paramInt < i1) {
      i1 = paramInt;
    }
    for (int i2 = paramInt;; i2 = i1)
    {
      this.k.readBytes(this.q, this.m, 0, i1);
      this.m = (i1 + this.m);
      this.r = 0;
      this.s = i2;
      ad.a("readAudio leave, dataSize=" + i2 + ", bufLen=" + paramInt);
      return;
    }
  }
  
  private String i()
  {
    String str = S.a(this.h);
    return str + System.currentTimeMillis() + "tts.pcm";
  }
  
  public int a()
  {
    return this.i;
  }
  
  public void a(AudioTrack paramAudioTrack, int paramInt)
    throws IOException
  {
    if (this.r >= this.s) {
      b(paramInt);
    }
    if (paramInt * 2 > this.s - this.r) {}
    for (int i1 = this.s - this.r;; i1 = paramInt)
    {
      paramAudioTrack.write(this.q, this.r, i1);
      this.r = (i1 + this.r);
      if (f()) {
        b(paramAudioTrack, paramInt);
      }
      return;
    }
  }
  
  public void a(ArrayList<byte[]> arg1, int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    ad.a("buffer percent = " + paramInt1 + " beg=" + paramInt2 + " end=" + paramInt3);
    a locala = new a(this.l, this.l, paramInt2, paramInt3);
    paramInt2 = 0;
    while (paramInt2 < ???.size())
    {
      a((byte[])???.get(paramInt2));
      paramInt2 += 1;
    }
    locala.b = this.l;
    this.j = paramInt1;
    synchronized (this.g)
    {
      this.g.add(locala);
      ad.a("allSize = " + this.l + " maxSize=" + this.f);
      return;
    }
  }
  
  public boolean a(int paramInt)
  {
    if ((float)this.j > 0.95F * this.t) {}
    while ((this.l / 32L >= paramInt) && (0L < this.l)) {
      return true;
    }
    return false;
  }
  
  public boolean a(String paramString)
  {
    ad.a("save to local: format = " + paramString + " totalSize = " + this.l + " maxSize=" + this.f);
    if (S.a(this.k, this.l, this.p)) {
      return S.a(paramString, this.p, a());
    }
    return false;
  }
  
  public int b()
  {
    if (this.k != null) {
      return this.k.length();
    }
    return 0;
  }
  
  public void b(AudioTrack paramAudioTrack, int paramInt)
  {
    ad.a("mBuffer.writeTrack writeTrackBlankBlock:" + paramInt);
    paramInt += 4096;
    paramAudioTrack.write(new byte[paramInt], 0, paramInt);
  }
  
  public void c()
    throws IOException
  {
    this.m = 0;
    this.n = null;
    if (this.g.size() > 0) {
      this.n = ((a)this.g.get(0));
    }
  }
  
  public int d()
  {
    if (this.l <= 0L) {
      return 0;
    }
    return (int)((this.m - (this.s - this.r)) * this.j / this.l);
  }
  
  public a e()
  {
    if (this.n != null)
    {
      long l1 = this.m - (this.s - this.r);
      if ((l1 >= this.n.a) && (l1 <= this.n.b)) {
        return this.n;
      }
      synchronized (this.g)
      {
        Object localObject1 = this.g.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          this.n = ((a)((Iterator)localObject1).next());
          if ((l1 >= this.n.a) && (l1 <= this.n.b))
          {
            localObject1 = this.n;
            return (a)localObject1;
          }
        }
      }
    }
    return null;
  }
  
  public boolean f()
  {
    return (this.t == this.j) && (this.m >= this.l) && (this.r >= this.s);
  }
  
  protected void finalize()
    throws Throwable
  {
    h();
    super.finalize();
  }
  
  public boolean g()
  {
    return (this.m < this.l) || (this.r < this.s);
  }
  
  public void h()
  {
    ad.a("deleteFile");
    try
    {
      if (this.k != null)
      {
        this.k.close();
        this.k = null;
      }
      return;
    }
    catch (Exception localException)
    {
      ad.a(localException);
    }
  }
  
  public class a
  {
    long a;
    long b;
    int c;
    int d;
    
    public a(long paramLong1, long paramLong2, int paramInt1, int paramInt2)
    {
      this.a = paramLong1;
      this.b = paramLong2;
      this.c = paramInt1;
      this.d = paramInt2;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\record\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */