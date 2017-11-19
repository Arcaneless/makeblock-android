package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzae
{
  public static final class zza
    extends zzbun<zza>
  {
    public String stackTrace = null;
    public String zzaR = null;
    public Long zzaS = null;
    public String zzaT = null;
    public String zzaU = null;
    public Long zzaV = null;
    public Long zzaW = null;
    public String zzaX = null;
    public Long zzaY = null;
    public String zzaZ = null;
    
    public zza()
    {
      this.zzcsg = -1;
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.zzaR != null) {
        paramzzbum.zzq(1, this.zzaR);
      }
      if (this.zzaS != null) {
        paramzzbum.zzb(2, this.zzaS.longValue());
      }
      if (this.stackTrace != null) {
        paramzzbum.zzq(3, this.stackTrace);
      }
      if (this.zzaT != null) {
        paramzzbum.zzq(4, this.zzaT);
      }
      if (this.zzaU != null) {
        paramzzbum.zzq(5, this.zzaU);
      }
      if (this.zzaV != null) {
        paramzzbum.zzb(6, this.zzaV.longValue());
      }
      if (this.zzaW != null) {
        paramzzbum.zzb(7, this.zzaW.longValue());
      }
      if (this.zzaX != null) {
        paramzzbum.zzq(8, this.zzaX);
      }
      if (this.zzaY != null) {
        paramzzbum.zzb(9, this.zzaY.longValue());
      }
      if (this.zzaZ != null) {
        paramzzbum.zzq(10, this.zzaZ);
      }
      super.zza(paramzzbum);
    }
    
    public zza zze(zzbul paramzzbul)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzbul.zzacu();
        switch (i)
        {
        default: 
          if (super.zza(paramzzbul, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.zzaR = paramzzbul.readString();
          break;
        case 16: 
          this.zzaS = Long.valueOf(paramzzbul.zzacx());
          break;
        case 26: 
          this.stackTrace = paramzzbul.readString();
          break;
        case 34: 
          this.zzaT = paramzzbul.readString();
          break;
        case 42: 
          this.zzaU = paramzzbul.readString();
          break;
        case 48: 
          this.zzaV = Long.valueOf(paramzzbul.zzacx());
          break;
        case 56: 
          this.zzaW = Long.valueOf(paramzzbul.zzacx());
          break;
        case 66: 
          this.zzaX = paramzzbul.readString();
          break;
        case 72: 
          this.zzaY = Long.valueOf(paramzzbul.zzacx());
          break;
        case 82: 
          this.zzaZ = paramzzbul.readString();
        }
      }
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.zzaR != null) {
        i = j + zzbum.zzr(1, this.zzaR);
      }
      j = i;
      if (this.zzaS != null) {
        j = i + zzbum.zzf(2, this.zzaS.longValue());
      }
      i = j;
      if (this.stackTrace != null) {
        i = j + zzbum.zzr(3, this.stackTrace);
      }
      j = i;
      if (this.zzaT != null) {
        j = i + zzbum.zzr(4, this.zzaT);
      }
      i = j;
      if (this.zzaU != null) {
        i = j + zzbum.zzr(5, this.zzaU);
      }
      j = i;
      if (this.zzaV != null) {
        j = i + zzbum.zzf(6, this.zzaV.longValue());
      }
      i = j;
      if (this.zzaW != null) {
        i = j + zzbum.zzf(7, this.zzaW.longValue());
      }
      j = i;
      if (this.zzaX != null) {
        j = i + zzbum.zzr(8, this.zzaX);
      }
      i = j;
      if (this.zzaY != null) {
        i = j + zzbum.zzf(9, this.zzaY.longValue());
      }
      j = i;
      if (this.zzaZ != null) {
        j = i + zzbum.zzr(10, this.zzaZ);
      }
      return j;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */