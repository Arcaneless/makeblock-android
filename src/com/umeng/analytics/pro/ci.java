package com.umeng.analytics.pro;

import java.io.ByteArrayOutputStream;

public class ci
  extends ByteArrayOutputStream
{
  public ci() {}
  
  public ci(int paramInt)
  {
    super(paramInt);
  }
  
  public byte[] a()
  {
    return this.buf;
  }
  
  public int b()
  {
    return this.count;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\umeng\analytics\pro\ci.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */