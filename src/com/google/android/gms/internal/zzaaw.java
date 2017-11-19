package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzaaw
{
  protected final zzaax zzaBs;
  
  protected zzaaw(zzaax paramzzaax)
  {
    this.zzaBs = paramzzaax;
  }
  
  protected static zzaax zzc(zzaav paramzzaav)
  {
    if (paramzzaav.zzwl()) {
      return zzabm.zza(paramzzaav.zzwn());
    }
    return zzaay.zzt(paramzzaav.zzwm());
  }
  
  public static zzaax zzs(Activity paramActivity)
  {
    return zzc(new zzaav(paramActivity));
  }
  
  @MainThread
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  public Activity getActivity()
  {
    return this.zzaBs.zzwo();
  }
  
  @MainThread
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  @MainThread
  public void onCreate(Bundle paramBundle) {}
  
  @MainThread
  public void onDestroy() {}
  
  @MainThread
  public void onSaveInstanceState(Bundle paramBundle) {}
  
  @MainThread
  public void onStart() {}
  
  @MainThread
  public void onStop() {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzaaw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */