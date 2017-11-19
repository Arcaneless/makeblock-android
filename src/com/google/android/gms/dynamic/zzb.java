package com.google.android.gms.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

@SuppressLint({"NewApi"})
public final class zzb
  extends zzc.zza
{
  private Fragment zzaQn;
  
  private zzb(Fragment paramFragment)
  {
    this.zzaQn = paramFragment;
  }
  
  public static zzb zza(Fragment paramFragment)
  {
    if (paramFragment != null) {
      return new zzb(paramFragment);
    }
    return null;
  }
  
  public Bundle getArguments()
  {
    return this.zzaQn.getArguments();
  }
  
  public int getId()
  {
    return this.zzaQn.getId();
  }
  
  public boolean getRetainInstance()
  {
    return this.zzaQn.getRetainInstance();
  }
  
  public String getTag()
  {
    return this.zzaQn.getTag();
  }
  
  public int getTargetRequestCode()
  {
    return this.zzaQn.getTargetRequestCode();
  }
  
  public boolean getUserVisibleHint()
  {
    return this.zzaQn.getUserVisibleHint();
  }
  
  public zzd getView()
  {
    return zze.zzA(this.zzaQn.getView());
  }
  
  public boolean isAdded()
  {
    return this.zzaQn.isAdded();
  }
  
  public boolean isDetached()
  {
    return this.zzaQn.isDetached();
  }
  
  public boolean isHidden()
  {
    return this.zzaQn.isHidden();
  }
  
  public boolean isInLayout()
  {
    return this.zzaQn.isInLayout();
  }
  
  public boolean isRemoving()
  {
    return this.zzaQn.isRemoving();
  }
  
  public boolean isResumed()
  {
    return this.zzaQn.isResumed();
  }
  
  public boolean isVisible()
  {
    return this.zzaQn.isVisible();
  }
  
  public void setHasOptionsMenu(boolean paramBoolean)
  {
    this.zzaQn.setHasOptionsMenu(paramBoolean);
  }
  
  public void setMenuVisibility(boolean paramBoolean)
  {
    this.zzaQn.setMenuVisibility(paramBoolean);
  }
  
  public void setRetainInstance(boolean paramBoolean)
  {
    this.zzaQn.setRetainInstance(paramBoolean);
  }
  
  public void setUserVisibleHint(boolean paramBoolean)
  {
    this.zzaQn.setUserVisibleHint(paramBoolean);
  }
  
  public void startActivity(Intent paramIntent)
  {
    this.zzaQn.startActivity(paramIntent);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    this.zzaQn.startActivityForResult(paramIntent, paramInt);
  }
  
  public zzd zzAZ()
  {
    return zze.zzA(this.zzaQn.getActivity());
  }
  
  public zzc zzBa()
  {
    return zza(this.zzaQn.getParentFragment());
  }
  
  public zzd zzBb()
  {
    return zze.zzA(this.zzaQn.getResources());
  }
  
  public zzc zzBc()
  {
    return zza(this.zzaQn.getTargetFragment());
  }
  
  public void zzC(zzd paramzzd)
  {
    paramzzd = (View)zze.zzE(paramzzd);
    this.zzaQn.registerForContextMenu(paramzzd);
  }
  
  public void zzD(zzd paramzzd)
  {
    paramzzd = (View)zze.zzE(paramzzd);
    this.zzaQn.unregisterForContextMenu(paramzzd);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\dynamic\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */