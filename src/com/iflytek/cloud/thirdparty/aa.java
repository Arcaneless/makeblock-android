package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aa
  implements ac
{
  private static final Pattern a = Pattern.compile("attachment;\\s*filename\\s*=\\s*\"([^\"]*)\"");
  private static Random b = new Random(SystemClock.uptimeMillis());
  private long c;
  private int d;
  private ab e;
  private boolean f;
  private Z g;
  private long h;
  private long i;
  private String j;
  private String k;
  private boolean l;
  private String m;
  private String n;
  private FileOutputStream o;
  private long p;
  private long q;
  private long r;
  private Context s;
  
  public aa()
  {
    this(System.currentTimeMillis(), 0, null);
  }
  
  public aa(long paramLong, int paramInt, Context paramContext)
  {
    this.c = paramLong;
    this.d = paramInt;
    this.s = paramContext;
    this.g = new Z(this);
  }
  
  private static String a(String paramString)
  {
    try
    {
      paramString = a.matcher(paramString);
      if (paramString.find())
      {
        paramString = paramString.group(1);
        return paramString;
      }
    }
    catch (IllegalStateException paramString) {}
    return null;
  }
  
  private static String a(String paramString1, String paramString2, int paramInt)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString1 != null)
    {
      int i1 = paramString2.lastIndexOf('.');
      String str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramString2.substring(i1 + 1));
      if (str != null)
      {
        localObject1 = localObject2;
        if (str.equalsIgnoreCase(paramString1)) {}
      }
      else
      {
        localObject1 = a(paramString1, false);
      }
    }
    paramString1 = (String)localObject1;
    if (localObject1 == null) {
      paramString1 = paramString2.substring(paramInt);
    }
    return paramString1;
  }
  
  private static String a(String paramString1, String paramString2, String paramString3)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    int i1;
    if (0 == 0)
    {
      localObject1 = localObject2;
      if (paramString2 != null)
      {
        paramString2 = a(paramString2);
        localObject1 = paramString2;
        if (paramString2 != null)
        {
          i1 = paramString2.lastIndexOf('/') + 1;
          localObject1 = paramString2;
          if (i1 > 0) {
            localObject1 = paramString2.substring(i1);
          }
        }
      }
    }
    if ((localObject1 == null) && (paramString3 != null))
    {
      paramString3 = Uri.decode(paramString3);
      if ((paramString3 != null) && (!paramString3.endsWith("/")) && (paramString3.indexOf('?') < 0))
      {
        i1 = paramString3.lastIndexOf('/') + 1;
        paramString2 = paramString3;
        if (i1 <= 0) {}
      }
    }
    for (paramString2 = paramString3.substring(i1);; paramString2 = (String)localObject1)
    {
      paramString3 = paramString2;
      if (paramString2 == null)
      {
        paramString1 = Uri.decode(paramString1);
        paramString3 = paramString2;
        if (paramString1 != null)
        {
          paramString3 = paramString2;
          if (!paramString1.endsWith("/"))
          {
            paramString3 = paramString2;
            if (paramString1.indexOf('?') < 0)
            {
              i1 = paramString1.lastIndexOf('/') + 1;
              paramString3 = paramString2;
              if (i1 > 0) {
                paramString3 = paramString1.substring(i1);
              }
            }
          }
        }
      }
      paramString1 = paramString3;
      if (paramString3 == null) {
        paramString1 = "downloadfile";
      }
      return paramString1.replaceAll("[^a-zA-Z0-9\\.\\-_]+", "_");
    }
  }
  
  private static String a(String paramString, boolean paramBoolean)
  {
    Object localObject1 = null;
    if (paramString != null)
    {
      localObject2 = MimeTypeMap.getSingleton().getExtensionFromMimeType(paramString);
      localObject1 = localObject2;
      if (localObject2 != null) {
        localObject1 = "." + (String)localObject2;
      }
    }
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      if ((paramString == null) || (!paramString.toLowerCase().startsWith("text/"))) {
        break label85;
      }
      if (!paramString.equalsIgnoreCase("text/html")) {
        break label76;
      }
      localObject2 = ".html";
    }
    label76:
    label85:
    do
    {
      do
      {
        return (String)localObject2;
        localObject2 = localObject1;
      } while (!paramBoolean);
      return ".txt";
      localObject2 = localObject1;
    } while (!paramBoolean);
    return ".bin";
  }
  
  private static String[] a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    paramString3 = a(paramString2, paramString3, paramString4);
    int i1 = paramString3.lastIndexOf('.');
    if (i1 < 0) {
      paramString2 = a(paramString5, true);
    }
    for (;;)
    {
      paramString4 = paramString1;
      if (!paramString1.endsWith(File.separator)) {
        paramString4 = paramString1 + File.separator;
      }
      return new String[] { b(paramString4 + paramString3, paramString2, paramString6), paramString2 };
      paramString2 = a(paramString5, paramString3, i1);
      paramString3 = paramString3.substring(0, i1);
    }
  }
  
  private static String b(String paramString1, String paramString2, String paramString3)
  {
    String str1 = paramString1 + paramString2;
    String str2 = paramString1 + paramString3;
    if ((!new File(str1).exists()) && (!new File(str2).exists())) {
      return paramString1;
    }
    str1 = paramString1 + "-";
    int i1 = 1;
    int i2 = 1;
    for (;;)
    {
      if (i1 >= 1000000000) {
        break label236;
      }
      int i3 = 0;
      for (;;)
      {
        if (i3 >= 9) {
          break label228;
        }
        paramString1 = str1 + i2;
        String str3 = paramString1 + paramString2;
        str2 = paramString1 + paramString3;
        if ((!new File(str3).exists()) && (!new File(str2).exists())) {
          break;
        }
        i2 += b.nextInt(i1) + 1;
        i3 += 1;
      }
      label228:
      i1 *= 10;
    }
    label236:
    return null;
  }
  
  private void e()
  {
    if (this.o != null) {}
    try
    {
      this.o.close();
      this.o = null;
      this.k = null;
      return;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  public int a(long paramLong, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    ad.c("onStart, url = " + paramString2);
    if (this.f) {
      return -2;
    }
    paramString2 = a(this.n, paramString2, paramString4, paramString5, paramString1, ".tmp");
    if (this.k == null)
    {
      this.k = paramString2[0];
      if (this.k == null) {
        return 20010;
      }
      this.k = this.k.concat(".tmp");
    }
    this.m = paramString2[1];
    ad.c("onStart, bytesRead : " + this.h + ", length : " + paramLong);
    ad.c("onStart, filename : " + this.k + ", extension : " + this.m);
    if (X.a()) {}
    for (long l1 = X.a(X.b()); l1 < paramLong - this.h; l1 = X.a(this.s)) {
      return 20010;
    }
    paramString2 = new File(this.n);
    if (!paramString2.exists()) {
      paramString2.mkdirs();
    }
    if (this.f) {
      return -2;
    }
    ad.c("create file success");
    try
    {
      this.i = paramLong;
      this.q = (this.i / 100L);
      this.p = SystemClock.elapsedRealtime();
      this.r = 0L;
      this.o = new FileOutputStream(this.k, true);
      if (this.f) {
        return -2;
      }
      if (this.e != null) {
        this.e.a(paramLong, paramString1, this.k, paramString3, this);
      }
      return 0;
    }
    catch (FileNotFoundException paramString1)
    {
      ad.b("onStart, create file error", paramString1.toString());
    }
    return 20010;
  }
  
  public int a(byte[] paramArrayOfByte, int paramInt)
  {
    if (this.f) {}
    for (;;)
    {
      return -2;
      try
      {
        this.o.write(paramArrayOfByte, 0, paramInt);
        if (!this.f)
        {
          this.o.flush();
          if (!this.f)
          {
            this.h += paramInt;
            if ((this.e != null) && (this.h - this.r >= this.q))
            {
              long l1 = SystemClock.elapsedRealtime();
              if (l1 - this.p >= 40L)
              {
                paramInt = (int)(this.h * 100L / this.i);
                this.e.a(this.h, paramInt, this);
                this.r = this.h;
                this.p = l1;
              }
            }
            return 0;
          }
        }
      }
      catch (IOException paramArrayOfByte) {}
    }
    return 20010;
  }
  
  public void a()
  {
    try
    {
      this.f = true;
      this.g.a();
      if (!TextUtils.isEmpty(this.k))
      {
        File localFile = new File(this.k);
        if (localFile.exists()) {
          localFile.delete();
        }
      }
      e();
      return;
    }
    finally {}
  }
  
  public void a(int paramInt)
  {
    ad.b("onError errorCode = " + paramInt);
    if ((!this.f) && (this.e != null)) {
      this.e.a(paramInt, this);
    }
    e();
  }
  
  public void a(ab paramab)
  {
    this.e = paramab;
  }
  
  public void a(String paramString1, String paramString2, String paramString3, boolean paramBoolean, String paramString4)
  {
    ad.c("currentPath : " + paramString2 + ", specifiedPath : " + paramString3 + ", url : " + paramString1);
    long l1 = 0L;
    this.k = null;
    String str;
    if (paramString2 != null)
    {
      str = paramString2;
      this.j = paramString3;
      this.l = paramBoolean;
      paramString3 = new File(str);
      if (!paramString3.exists()) {
        break label214;
      }
      if (!paramString3.isDirectory()) {
        break label184;
      }
      this.n = str;
      this.l = false;
    }
    for (;;)
    {
      this.h = l1;
      ad.c("mFileDir : " + this.n + ", mBytesRead : " + this.h + ", mSpecifiedPath : " + this.j);
      this.g.a(paramString1, l1, paramString4, 4096);
      return;
      str = paramString3;
      break;
      label184:
      this.n = paramString3.getParentFile().getAbsolutePath();
      if (paramString2 != null)
      {
        l1 = paramString3.length();
        this.k = str;
        continue;
        label214:
        if (str.endsWith(File.separator))
        {
          this.n = str;
          this.l = false;
        }
        else
        {
          this.n = str.substring(0, str.lastIndexOf(File.separator));
        }
      }
    }
  }
  
  public long b()
  {
    return this.c;
  }
  
  public void c()
  {
    ad.c("onFinish | cover = " + this.l + " mFilename = " + this.k + " mSpecifiedPath = " + this.j);
    try
    {
      if (this.l) {}
      for (String str = this.j;; str = this.k.substring(0, this.k.lastIndexOf('.')).concat(this.m))
      {
        File localFile = new File(str);
        if (localFile.exists()) {
          localFile.delete();
        }
        new File(this.k).renameTo(localFile);
        if ((!this.f) && (this.e != null)) {
          this.e.a(str, this);
        }
        e();
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      a(20014);
    }
  }
  
  public void d()
  {
    ad.a("onCancel");
    e();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\aa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */