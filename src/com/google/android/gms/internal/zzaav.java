package com.google.android.gms.internal;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzs;

public class zzaav
{
  private final Object zzaBr;
  
  public zzaav(Activity paramActivity)
  {
    zzac.zzb(paramActivity, "Activity must not be null");
    if ((zzs.zzyx()) || ((paramActivity instanceof FragmentActivity))) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "This Activity is not supported before platform version 11 (3.0 Honeycomb). Please use FragmentActivity instead.");
      this.zzaBr = paramActivity;
      return;
    }
  }
  
  public boolean zzwl()
  {
    return this.zzaBr instanceof FragmentActivity;
  }
  
  public Activity zzwm()
  {
    return (Activity)this.zzaBr;
  }
  
  public FragmentActivity zzwn()
  {
    return (FragmentActivity)this.zzaBr;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzaav.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */