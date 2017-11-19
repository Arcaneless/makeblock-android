package com.google.android.gms.internal;

abstract class zzats
  extends zzatr
{
  private boolean zzacO;
  
  zzats(zzatp paramzzatp)
  {
    super(paramzzatp);
    this.zzbpw.zzb(this);
  }
  
  public final void initialize()
  {
    if (this.zzacO) {
      throw new IllegalStateException("Can't initialize twice");
    }
    zzmr();
    this.zzbpw.zzLK();
    this.zzacO = true;
  }
  
  boolean isInitialized()
  {
    return this.zzacO;
  }
  
  protected abstract void zzmr();
  
  protected void zznA()
  {
    if (!isInitialized()) {
      throw new IllegalStateException("Not initialized");
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzats.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */