package com.bumptech.glide.load;

import java.nio.charset.Charset;
import java.security.MessageDigest;

public abstract interface Key
{
  public static final Charset CHARSET = Charset.forName("UTF-8");
  public static final String STRING_CHARSET_NAME = "UTF-8";
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
  
  public abstract void updateDiskCacheKey(MessageDigest paramMessageDigest);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\Key.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */