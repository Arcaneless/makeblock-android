package com.bumptech.glide.signature;

import com.bumptech.glide.load.Key;
import java.security.MessageDigest;

public final class EmptySignature
  implements Key
{
  private static final EmptySignature EMPTY_KEY = new EmptySignature();
  
  public static EmptySignature obtain()
  {
    return EMPTY_KEY;
  }
  
  public String toString()
  {
    return "EmptySignature";
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest) {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\signature\EmptySignature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */