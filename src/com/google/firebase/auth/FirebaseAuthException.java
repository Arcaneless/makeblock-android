package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import com.google.firebase.FirebaseException;

public class FirebaseAuthException
  extends FirebaseException
{
  private final String zzbVH;
  
  public FirebaseAuthException(@NonNull String paramString1, @NonNull String paramString2)
  {
    super(paramString2);
    this.zzbVH = zzac.zzdv(paramString1);
  }
  
  @NonNull
  public String getErrorCode()
  {
    return this.zzbVH;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\firebase\auth\FirebaseAuthException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */