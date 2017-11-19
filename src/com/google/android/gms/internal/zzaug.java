package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzaug
{
  public static final class zza
    extends zzbut
  {
    private static volatile zza[] zzbvH;
    public String name;
    public Boolean zzbvI;
    public Boolean zzbvJ;
    
    public zza()
    {
      zzMu();
    }
    
    public static zza[] zzMt()
    {
      if (zzbvH == null) {}
      synchronized (zzbur.zzcsf)
      {
        if (zzbvH == null) {
          zzbvH = new zza[0];
        }
        return zzbvH;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        do
        {
          return true;
          if (!(paramObject instanceof zza)) {
            return false;
          }
          paramObject = (zza)paramObject;
          if (this.name == null)
          {
            if (((zza)paramObject).name != null) {
              return false;
            }
          }
          else if (!this.name.equals(((zza)paramObject).name)) {
            return false;
          }
          if (this.zzbvI == null)
          {
            if (((zza)paramObject).zzbvI != null) {
              return false;
            }
          }
          else if (!this.zzbvI.equals(((zza)paramObject).zzbvI)) {
            return false;
          }
          if (this.zzbvJ != null) {
            break;
          }
        } while (((zza)paramObject).zzbvJ == null);
        return false;
      } while (this.zzbvJ.equals(((zza)paramObject).zzbvJ));
      return false;
    }
    
    public int hashCode()
    {
      int k = 0;
      int m = getClass().getName().hashCode();
      int i;
      int j;
      if (this.name == null)
      {
        i = 0;
        if (this.zzbvI != null) {
          break label72;
        }
        j = 0;
        label32:
        if (this.zzbvJ != null) {
          break label83;
        }
      }
      for (;;)
      {
        return (j + (i + (m + 527) * 31) * 31) * 31 + k;
        i = this.name.hashCode();
        break;
        label72:
        j = this.zzbvI.hashCode();
        break label32;
        label83:
        k = this.zzbvJ.hashCode();
      }
    }
    
    public zza zzM(zzbul paramzzbul)
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
          this.name = paramzzbul.readString();
          break;
        case 16: 
          this.zzbvI = Boolean.valueOf(paramzzbul.zzacA());
          break;
        case 24: 
          this.zzbvJ = Boolean.valueOf(paramzzbul.zzacA());
        }
      }
    }
    
    public zza zzMu()
    {
      this.name = null;
      this.zzbvI = null;
      this.zzbvJ = null;
      this.zzcsg = -1;
      return this;
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.name != null) {
        paramzzbum.zzq(1, this.name);
      }
      if (this.zzbvI != null) {
        paramzzbum.zzg(2, this.zzbvI.booleanValue());
      }
      if (this.zzbvJ != null) {
        paramzzbum.zzg(3, this.zzbvJ.booleanValue());
      }
      super.zza(paramzzbum);
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.name != null) {
        i = j + zzbum.zzr(1, this.name);
      }
      j = i;
      if (this.zzbvI != null) {
        j = i + zzbum.zzh(2, this.zzbvI.booleanValue());
      }
      i = j;
      if (this.zzbvJ != null) {
        i = j + zzbum.zzh(3, this.zzbvJ.booleanValue());
      }
      return i;
    }
  }
  
  public static final class zzb
    extends zzbut
  {
    public String zzbqf;
    public Long zzbvK;
    public Integer zzbvL;
    public zzaug.zzc[] zzbvM;
    public zzaug.zza[] zzbvN;
    public zzauf.zza[] zzbvO;
    
    public zzb()
    {
      zzMv();
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        return true;
        if (!(paramObject instanceof zzb)) {
          return false;
        }
        paramObject = (zzb)paramObject;
        if (this.zzbvK == null)
        {
          if (((zzb)paramObject).zzbvK != null) {
            return false;
          }
        }
        else if (!this.zzbvK.equals(((zzb)paramObject).zzbvK)) {
          return false;
        }
        if (this.zzbqf == null)
        {
          if (((zzb)paramObject).zzbqf != null) {
            return false;
          }
        }
        else if (!this.zzbqf.equals(((zzb)paramObject).zzbqf)) {
          return false;
        }
        if (this.zzbvL == null)
        {
          if (((zzb)paramObject).zzbvL != null) {
            return false;
          }
        }
        else if (!this.zzbvL.equals(((zzb)paramObject).zzbvL)) {
          return false;
        }
        if (!zzbur.equals(this.zzbvM, ((zzb)paramObject).zzbvM)) {
          return false;
        }
        if (!zzbur.equals(this.zzbvN, ((zzb)paramObject).zzbvN)) {
          return false;
        }
      } while (zzbur.equals(this.zzbvO, ((zzb)paramObject).zzbvO));
      return false;
    }
    
    public int hashCode()
    {
      int k = 0;
      int m = getClass().getName().hashCode();
      int i;
      int j;
      if (this.zzbvK == null)
      {
        i = 0;
        if (this.zzbqf != null) {
          break label105;
        }
        j = 0;
        label32:
        if (this.zzbvL != null) {
          break label116;
        }
      }
      for (;;)
      {
        return ((((j + (i + (m + 527) * 31) * 31) * 31 + k) * 31 + zzbur.hashCode(this.zzbvM)) * 31 + zzbur.hashCode(this.zzbvN)) * 31 + zzbur.hashCode(this.zzbvO);
        i = this.zzbvK.hashCode();
        break;
        label105:
        j = this.zzbqf.hashCode();
        break label32;
        label116:
        k = this.zzbvL.hashCode();
      }
    }
    
    public zzb zzMv()
    {
      this.zzbvK = null;
      this.zzbqf = null;
      this.zzbvL = null;
      this.zzbvM = zzaug.zzc.zzMw();
      this.zzbvN = zzaug.zza.zzMt();
      this.zzbvO = zzauf.zza.zzMj();
      this.zzcsg = -1;
      return this;
    }
    
    public zzb zzN(zzbul paramzzbul)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzbul.zzacu();
        int j;
        Object localObject;
        switch (i)
        {
        default: 
          if (zzbuw.zzb(paramzzbul, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.zzbvK = Long.valueOf(paramzzbul.zzacx());
          break;
        case 18: 
          this.zzbqf = paramzzbul.readString();
          break;
        case 24: 
          this.zzbvL = Integer.valueOf(paramzzbul.zzacy());
          break;
        case 34: 
          j = zzbuw.zzc(paramzzbul, 34);
          if (this.zzbvM == null) {}
          for (i = 0;; i = this.zzbvM.length)
          {
            localObject = new zzaug.zzc[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbvM, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzaug.zzc();
              paramzzbul.zza(localObject[j]);
              paramzzbul.zzacu();
              j += 1;
            }
          }
          localObject[j] = new zzaug.zzc();
          paramzzbul.zza(localObject[j]);
          this.zzbvM = ((zzaug.zzc[])localObject);
          break;
        case 42: 
          j = zzbuw.zzc(paramzzbul, 42);
          if (this.zzbvN == null) {}
          for (i = 0;; i = this.zzbvN.length)
          {
            localObject = new zzaug.zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbvN, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzaug.zza();
              paramzzbul.zza(localObject[j]);
              paramzzbul.zzacu();
              j += 1;
            }
          }
          localObject[j] = new zzaug.zza();
          paramzzbul.zza(localObject[j]);
          this.zzbvN = ((zzaug.zza[])localObject);
          break;
        case 50: 
          j = zzbuw.zzc(paramzzbul, 50);
          if (this.zzbvO == null) {}
          for (i = 0;; i = this.zzbvO.length)
          {
            localObject = new zzauf.zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbvO, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzauf.zza();
              paramzzbul.zza(localObject[j]);
              paramzzbul.zzacu();
              j += 1;
            }
          }
          localObject[j] = new zzauf.zza();
          paramzzbul.zza(localObject[j]);
          this.zzbvO = ((zzauf.zza[])localObject);
        }
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      int j = 0;
      if (this.zzbvK != null) {
        paramzzbum.zzb(1, this.zzbvK.longValue());
      }
      if (this.zzbqf != null) {
        paramzzbum.zzq(2, this.zzbqf);
      }
      if (this.zzbvL != null) {
        paramzzbum.zzF(3, this.zzbvL.intValue());
      }
      int i;
      Object localObject;
      if ((this.zzbvM != null) && (this.zzbvM.length > 0))
      {
        i = 0;
        while (i < this.zzbvM.length)
        {
          localObject = this.zzbvM[i];
          if (localObject != null) {
            paramzzbum.zza(4, (zzbut)localObject);
          }
          i += 1;
        }
      }
      if ((this.zzbvN != null) && (this.zzbvN.length > 0))
      {
        i = 0;
        while (i < this.zzbvN.length)
        {
          localObject = this.zzbvN[i];
          if (localObject != null) {
            paramzzbum.zza(5, (zzbut)localObject);
          }
          i += 1;
        }
      }
      if ((this.zzbvO != null) && (this.zzbvO.length > 0))
      {
        i = j;
        while (i < this.zzbvO.length)
        {
          localObject = this.zzbvO[i];
          if (localObject != null) {
            paramzzbum.zza(6, (zzbut)localObject);
          }
          i += 1;
        }
      }
      super.zza(paramzzbum);
    }
    
    protected int zzv()
    {
      int m = 0;
      int j = super.zzv();
      int i = j;
      if (this.zzbvK != null) {
        i = j + zzbum.zzf(1, this.zzbvK.longValue());
      }
      j = i;
      if (this.zzbqf != null) {
        j = i + zzbum.zzr(2, this.zzbqf);
      }
      i = j;
      if (this.zzbvL != null) {
        i = j + zzbum.zzH(3, this.zzbvL.intValue());
      }
      j = i;
      Object localObject;
      if (this.zzbvM != null)
      {
        j = i;
        if (this.zzbvM.length > 0)
        {
          j = 0;
          while (j < this.zzbvM.length)
          {
            localObject = this.zzbvM[j];
            k = i;
            if (localObject != null) {
              k = i + zzbum.zzc(4, (zzbut)localObject);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (this.zzbvN != null)
      {
        i = j;
        if (this.zzbvN.length > 0)
        {
          i = j;
          j = 0;
          while (j < this.zzbvN.length)
          {
            localObject = this.zzbvN[j];
            k = i;
            if (localObject != null) {
              k = i + zzbum.zzc(5, (zzbut)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      int k = i;
      if (this.zzbvO != null)
      {
        k = i;
        if (this.zzbvO.length > 0)
        {
          j = m;
          for (;;)
          {
            k = i;
            if (j >= this.zzbvO.length) {
              break;
            }
            localObject = this.zzbvO[j];
            k = i;
            if (localObject != null) {
              k = i + zzbum.zzc(6, (zzbut)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      return k;
    }
  }
  
  public static final class zzc
    extends zzbut
  {
    private static volatile zzc[] zzbvP;
    public String value;
    public String zzaA;
    
    public zzc()
    {
      zzMx();
    }
    
    public static zzc[] zzMw()
    {
      if (zzbvP == null) {}
      synchronized (zzbur.zzcsf)
      {
        if (zzbvP == null) {
          zzbvP = new zzc[0];
        }
        return zzbvP;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        do
        {
          return true;
          if (!(paramObject instanceof zzc)) {
            return false;
          }
          paramObject = (zzc)paramObject;
          if (this.zzaA == null)
          {
            if (((zzc)paramObject).zzaA != null) {
              return false;
            }
          }
          else if (!this.zzaA.equals(((zzc)paramObject).zzaA)) {
            return false;
          }
          if (this.value != null) {
            break;
          }
        } while (((zzc)paramObject).value == null);
        return false;
      } while (this.value.equals(((zzc)paramObject).value));
      return false;
    }
    
    public int hashCode()
    {
      int j = 0;
      int k = getClass().getName().hashCode();
      int i;
      if (this.zzaA == null)
      {
        i = 0;
        if (this.value != null) {
          break label56;
        }
      }
      for (;;)
      {
        return (i + (k + 527) * 31) * 31 + j;
        i = this.zzaA.hashCode();
        break;
        label56:
        j = this.value.hashCode();
      }
    }
    
    public zzc zzMx()
    {
      this.zzaA = null;
      this.value = null;
      this.zzcsg = -1;
      return this;
    }
    
    public zzc zzO(zzbul paramzzbul)
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
          this.zzaA = paramzzbul.readString();
          break;
        case 18: 
          this.value = paramzzbul.readString();
        }
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.zzaA != null) {
        paramzzbum.zzq(1, this.zzaA);
      }
      if (this.value != null) {
        paramzzbum.zzq(2, this.value);
      }
      super.zza(paramzzbum);
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.zzaA != null) {
        i = j + zzbum.zzr(1, this.zzaA);
      }
      j = i;
      if (this.value != null) {
        j = i + zzbum.zzr(2, this.value);
      }
      return j;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzaug.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */