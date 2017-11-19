package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public final class zzh
  extends zzc.zza
{
  private Fragment zzaQq;
  
  private zzh(Fragment paramFragment)
  {
    this.zzaQq = paramFragment;
  }
  
  public static zzh zza(Fragment paramFragment)
  {
    if (paramFragment != null) {
      return new zzh(paramFragment);
    }
    return null;
  }
  
  public Bundle getArguments()
  {
    return this.zzaQq.getArguments();
  }
  
  public int getId()
  {
    return this.zzaQq.getId();
  }
  
  public boolean getRetainInstance()
  {
    return this.zzaQq.getRetainInstance();
  }
  
  public String getTag()
  {
    return this.zzaQq.getTag();
  }
  
  public int getTargetRequestCode()
  {
    return this.zzaQq.getTargetRequestCode();
  }
  
  public boolean getUserVisibleHint()
  {
    return this.zzaQq.getUserVisibleHint();
  }
  
  public zzd getView()
  {
    return zze.zzA(this.zzaQq.getView());
  }
  
  public boolean isAdded()
  {
    return this.zzaQq.isAdded();
  }
  
  public boolean isDetached()
  {
    return this.zzaQq.isDetached();
  }
  
  public boolean isHidden()
  {
    return this.zzaQq.isHidden();
  }
  
  public boolean isInLayout()
  {
    return this.zzaQq.isInLayout();
  }
  
  public boolean isRemoving()
  {
    return this.zzaQq.isRemoving();
  }
  
  public boolean isResumed()
  {
    return this.zzaQq.isResumed();
  }
  
  public boolean isVisible()
  {
    return this.zzaQq.isVisible();
  }
  
  public void setHasOptionsMenu(boolean paramBoolean)
  {
    this.zzaQq.setHasOptionsMenu(paramBoolean);
  }
  
  public void setMenuVisibility(boolean paramBoolean)
  {
    this.zzaQq.setMenuVisibility(paramBoolean);
  }
  
  public void setRetainInstance(boolean paramBoolean)
  {
    this.zzaQq.setRetainInstance(paramBoolean);
  }
  
  public void setUserVisibleHint(boolean paramBoolean)
  {
    this.zzaQq.setUserVisibleHint(paramBoolean);
  }
  
  public void startActivity(Intent paramIntent)
  {
    this.zzaQq.startActivity(paramIntent);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    this.zzaQq.startActivityForResult(paramIntent, paramInt);
  }
  
  public zzd zzAZ()
  {
    return zze.zzA(this.zzaQq.getActivity());
  }
  
  public zzc zzBa()
  {
    return zza(this.zzaQq.getParentFragment());
  }
  
  public zzd zzBb()
  {
    return zze.zzA(this.zzaQq.getResources());
  }
  
  public zzc zzBc()
  {
    return zza(this.zzaQq.getTargetFragment());
  }
  
  public void zzC(zzd paramzzd)
  {
    paramzzd = (View)zze.zzE(paramzzd);
    this.zzaQq.registerForContextMenu(paramzzd);
  }
  
  public void zzD(zzd paramzzd)
  {
    paramzzd = (View)zze.zzE(paramzzd);
    this.zzaQq.unregisterForContextMenu(paramzzd);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\dynamic\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */