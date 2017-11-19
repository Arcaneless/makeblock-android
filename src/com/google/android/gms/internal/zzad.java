package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzad
{
  public static final class zza
    extends zzbut
  {
    public zzad.zzb zzaJ;
    public zzad.zzc zzaK;
    
    public zza()
    {
      zzu();
    }
    
    public static zza zzc(byte[] paramArrayOfByte)
      throws zzbus
    {
      return (zza)zzbut.zza(new zza(), paramArrayOfByte);
    }
    
    public zza zza(zzbul paramzzbul)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzbul.zzacu();
        switch (i)
        {
        default: 
          if (zzbuw.zzb(paramzzbul, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          if (this.zzaJ == null) {
            this.zzaJ = new zzad.zzb();
          }
          paramzzbul.zza(this.zzaJ);
          break;
        case 18: 
          if (this.zzaK == null) {
            this.zzaK = new zzad.zzc();
          }
          paramzzbul.zza(this.zzaK);
        }
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.zzaJ != null) {
        paramzzbum.zza(1, this.zzaJ);
      }
      if (this.zzaK != null) {
        paramzzbum.zza(2, this.zzaK);
      }
      super.zza(paramzzbum);
    }
    
    public zza zzu()
    {
      this.zzaJ = null;
      this.zzaK = null;
      this.zzcsg = -1;
      return this;
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.zzaJ != null) {
        i = j + zzbum.zzc(1, this.zzaJ);
      }
      j = i;
      if (this.zzaK != null) {
        j = i + zzbum.zzc(2, this.zzaK);
      }
      return j;
    }
  }
  
  public static final class zzb
    extends zzbut
  {
    public Integer zzaL;
    
    public zzb()
    {
      zzw();
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.zzaL != null) {
        paramzzbum.zzF(27, this.zzaL.intValue());
      }
      super.zza(paramzzbum);
    }
    
    public zzb zzc(zzbul paramzzbul)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzbul.zzacu();
        switch (i)
        {
        default: 
          if (zzbuw.zzb(paramzzbul, i)) {}
          break;
        case 0: 
          return this;
        case 216: 
          i = paramzzbul.zzacy();
          switch (i)
          {
          default: 
            break;
          case 0: 
          case 1: 
          case 2: 
          case 3: 
          case 4: 
            this.zzaL = Integer.valueOf(i);
          }
          break;
        }
      }
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.zzaL != null) {
        i = j + zzbum.zzH(27, this.zzaL.intValue());
      }
      return i;
    }
    
    public zzb zzw()
    {
      this.zzcsg = -1;
      return this;
    }
  }
  
  public static final class zzc
    extends zzbut
  {
    public String zzaM;
    public String zzaN;
    public String zzaO;
    public String zzaP;
    public String zzaQ;
    
    public zzc()
    {
      zzx();
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.zzaM != null) {
        paramzzbum.zzq(1, this.zzaM);
      }
      if (this.zzaN != null) {
        paramzzbum.zzq(2, this.zzaN);
      }
      if (this.zzaO != null) {
        paramzzbum.zzq(3, this.zzaO);
      }
      if (this.zzaP != null) {
        paramzzbum.zzq(4, this.zzaP);
      }
      if (this.zzaQ != null) {
        paramzzbum.zzq(5, this.zzaQ);
      }
      super.zza(paramzzbum);
    }
    
    public zzc zzd(zzbul paramzzbul)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzbul.zzacu();
        switch (i)
        {
        default: 
          if (zzbuw.zzb(paramzzbul, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.zzaM = paramzzbul.readString();
          break;
        case 18: 
          this.zzaN = paramzzbul.readString();
          break;
        case 26: 
          this.zzaO = paramzzbul.readString();
          break;
        case 34: 
          this.zzaP = paramzzbul.readString();
          break;
        case 42: 
          this.zzaQ = paramzzbul.readString();
        }
      }
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.zzaM != null) {
        i = j + zzbum.zzr(1, this.zzaM);
      }
      j = i;
      if (this.zzaN != null) {
        j = i + zzbum.zzr(2, this.zzaN);
      }
      i = j;
      if (this.zzaO != null) {
        i = j + zzbum.zzr(3, this.zzaO);
      }
      j = i;
      if (this.zzaP != null) {
        j = i + zzbum.zzr(4, this.zzaP);
      }
      i = j;
      if (this.zzaQ != null) {
        i = j + zzbum.zzr(5, this.zzaQ);
      }
      return i;
    }
    
    public zzc zzx()
    {
      this.zzaM = null;
      this.zzaN = null;
      this.zzaO = null;
      this.zzaP = null;
      this.zzaQ = null;
      this.zzcsg = -1;
      return this;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */