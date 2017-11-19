package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzauh
{
  public static final class zza
    extends zzbut
  {
    private static volatile zza[] zzbvQ;
    public zzauh.zzf zzbvR;
    public zzauh.zzf zzbvS;
    public Boolean zzbvT;
    public Integer zzbvh;
    
    public zza()
    {
      zzMz();
    }
    
    public static zza[] zzMy()
    {
      if (zzbvQ == null) {}
      synchronized (zzbur.zzcsf)
      {
        if (zzbvQ == null) {
          zzbvQ = new zza[0];
        }
        return zzbvQ;
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
          if (this.zzbvh == null)
          {
            if (((zza)paramObject).zzbvh != null) {
              return false;
            }
          }
          else if (!this.zzbvh.equals(((zza)paramObject).zzbvh)) {
            return false;
          }
          if (this.zzbvR == null)
          {
            if (((zza)paramObject).zzbvR != null) {
              return false;
            }
          }
          else if (!this.zzbvR.equals(((zza)paramObject).zzbvR)) {
            return false;
          }
          if (this.zzbvS == null)
          {
            if (((zza)paramObject).zzbvS != null) {
              return false;
            }
          }
          else if (!this.zzbvS.equals(((zza)paramObject).zzbvS)) {
            return false;
          }
          if (this.zzbvT != null) {
            break;
          }
        } while (((zza)paramObject).zzbvT == null);
        return false;
      } while (this.zzbvT.equals(((zza)paramObject).zzbvT));
      return false;
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = getClass().getName().hashCode();
      int i;
      int j;
      label33:
      int k;
      if (this.zzbvh == null)
      {
        i = 0;
        if (this.zzbvR != null) {
          break label88;
        }
        j = 0;
        if (this.zzbvS != null) {
          break label99;
        }
        k = 0;
        label42:
        if (this.zzbvT != null) {
          break label110;
        }
      }
      for (;;)
      {
        return (k + (j + (i + (n + 527) * 31) * 31) * 31) * 31 + m;
        i = this.zzbvh.hashCode();
        break;
        label88:
        j = this.zzbvR.hashCode();
        break label33;
        label99:
        k = this.zzbvS.hashCode();
        break label42;
        label110:
        m = this.zzbvT.hashCode();
      }
    }
    
    public zza zzMz()
    {
      this.zzbvh = null;
      this.zzbvR = null;
      this.zzbvS = null;
      this.zzbvT = null;
      this.zzcsg = -1;
      return this;
    }
    
    public zza zzP(zzbul paramzzbul)
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
        case 8: 
          this.zzbvh = Integer.valueOf(paramzzbul.zzacy());
          break;
        case 18: 
          if (this.zzbvR == null) {
            this.zzbvR = new zzauh.zzf();
          }
          paramzzbul.zza(this.zzbvR);
          break;
        case 26: 
          if (this.zzbvS == null) {
            this.zzbvS = new zzauh.zzf();
          }
          paramzzbul.zza(this.zzbvS);
          break;
        case 32: 
          this.zzbvT = Boolean.valueOf(paramzzbul.zzacA());
        }
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.zzbvh != null) {
        paramzzbum.zzF(1, this.zzbvh.intValue());
      }
      if (this.zzbvR != null) {
        paramzzbum.zza(2, this.zzbvR);
      }
      if (this.zzbvS != null) {
        paramzzbum.zza(3, this.zzbvS);
      }
      if (this.zzbvT != null) {
        paramzzbum.zzg(4, this.zzbvT.booleanValue());
      }
      super.zza(paramzzbum);
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.zzbvh != null) {
        i = j + zzbum.zzH(1, this.zzbvh.intValue());
      }
      j = i;
      if (this.zzbvR != null) {
        j = i + zzbum.zzc(2, this.zzbvR);
      }
      i = j;
      if (this.zzbvS != null) {
        i = j + zzbum.zzc(3, this.zzbvS);
      }
      j = i;
      if (this.zzbvT != null) {
        j = i + zzbum.zzh(4, this.zzbvT.booleanValue());
      }
      return j;
    }
  }
  
  public static final class zzb
    extends zzbut
  {
    private static volatile zzb[] zzbvU;
    public Integer count;
    public String name;
    public zzauh.zzc[] zzbvV;
    public Long zzbvW;
    public Long zzbvX;
    
    public zzb()
    {
      zzMB();
    }
    
    public static zzb[] zzMA()
    {
      if (zzbvU == null) {}
      synchronized (zzbur.zzcsf)
      {
        if (zzbvU == null) {
          zzbvU = new zzb[0];
        }
        return zzbvU;
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
          if (!(paramObject instanceof zzb)) {
            return false;
          }
          paramObject = (zzb)paramObject;
          if (!zzbur.equals(this.zzbvV, ((zzb)paramObject).zzbvV)) {
            return false;
          }
          if (this.name == null)
          {
            if (((zzb)paramObject).name != null) {
              return false;
            }
          }
          else if (!this.name.equals(((zzb)paramObject).name)) {
            return false;
          }
          if (this.zzbvW == null)
          {
            if (((zzb)paramObject).zzbvW != null) {
              return false;
            }
          }
          else if (!this.zzbvW.equals(((zzb)paramObject).zzbvW)) {
            return false;
          }
          if (this.zzbvX == null)
          {
            if (((zzb)paramObject).zzbvX != null) {
              return false;
            }
          }
          else if (!this.zzbvX.equals(((zzb)paramObject).zzbvX)) {
            return false;
          }
          if (this.count != null) {
            break;
          }
        } while (((zzb)paramObject).count == null);
        return false;
      } while (this.count.equals(((zzb)paramObject).count));
      return false;
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = getClass().getName().hashCode();
      int i1 = zzbur.hashCode(this.zzbvV);
      int i;
      int j;
      label42:
      int k;
      if (this.name == null)
      {
        i = 0;
        if (this.zzbvW != null) {
          break label103;
        }
        j = 0;
        if (this.zzbvX != null) {
          break label114;
        }
        k = 0;
        label51:
        if (this.count != null) {
          break label125;
        }
      }
      for (;;)
      {
        return (k + (j + (i + ((n + 527) * 31 + i1) * 31) * 31) * 31) * 31 + m;
        i = this.name.hashCode();
        break;
        label103:
        j = this.zzbvW.hashCode();
        break label42;
        label114:
        k = this.zzbvX.hashCode();
        break label51;
        label125:
        m = this.count.hashCode();
      }
    }
    
    public zzb zzMB()
    {
      this.zzbvV = zzauh.zzc.zzMC();
      this.name = null;
      this.zzbvW = null;
      this.zzbvX = null;
      this.count = null;
      this.zzcsg = -1;
      return this;
    }
    
    public zzb zzQ(zzbul paramzzbul)
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
          int j = zzbuw.zzc(paramzzbul, 10);
          if (this.zzbvV == null) {}
          zzauh.zzc[] arrayOfzzc;
          for (i = 0;; i = this.zzbvV.length)
          {
            arrayOfzzc = new zzauh.zzc[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbvV, 0, arrayOfzzc, 0, i);
              j = i;
            }
            while (j < arrayOfzzc.length - 1)
            {
              arrayOfzzc[j] = new zzauh.zzc();
              paramzzbul.zza(arrayOfzzc[j]);
              paramzzbul.zzacu();
              j += 1;
            }
          }
          arrayOfzzc[j] = new zzauh.zzc();
          paramzzbul.zza(arrayOfzzc[j]);
          this.zzbvV = arrayOfzzc;
          break;
        case 18: 
          this.name = paramzzbul.readString();
          break;
        case 24: 
          this.zzbvW = Long.valueOf(paramzzbul.zzacx());
          break;
        case 32: 
          this.zzbvX = Long.valueOf(paramzzbul.zzacx());
          break;
        case 40: 
          this.count = Integer.valueOf(paramzzbul.zzacy());
        }
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if ((this.zzbvV != null) && (this.zzbvV.length > 0))
      {
        int i = 0;
        while (i < this.zzbvV.length)
        {
          zzauh.zzc localzzc = this.zzbvV[i];
          if (localzzc != null) {
            paramzzbum.zza(1, localzzc);
          }
          i += 1;
        }
      }
      if (this.name != null) {
        paramzzbum.zzq(2, this.name);
      }
      if (this.zzbvW != null) {
        paramzzbum.zzb(3, this.zzbvW.longValue());
      }
      if (this.zzbvX != null) {
        paramzzbum.zzb(4, this.zzbvX.longValue());
      }
      if (this.count != null) {
        paramzzbum.zzF(5, this.count.intValue());
      }
      super.zza(paramzzbum);
    }
    
    protected int zzv()
    {
      int i = super.zzv();
      int j = i;
      if (this.zzbvV != null)
      {
        j = i;
        if (this.zzbvV.length > 0)
        {
          int k = 0;
          for (;;)
          {
            j = i;
            if (k >= this.zzbvV.length) {
              break;
            }
            zzauh.zzc localzzc = this.zzbvV[k];
            j = i;
            if (localzzc != null) {
              j = i + zzbum.zzc(1, localzzc);
            }
            k += 1;
            i = j;
          }
        }
      }
      i = j;
      if (this.name != null) {
        i = j + zzbum.zzr(2, this.name);
      }
      j = i;
      if (this.zzbvW != null) {
        j = i + zzbum.zzf(3, this.zzbvW.longValue());
      }
      i = j;
      if (this.zzbvX != null) {
        i = j + zzbum.zzf(4, this.zzbvX.longValue());
      }
      j = i;
      if (this.count != null) {
        j = i + zzbum.zzH(5, this.count.intValue());
      }
      return j;
    }
  }
  
  public static final class zzc
    extends zzbut
  {
    private static volatile zzc[] zzbvY;
    public String name;
    public String zzaFy;
    public Long zzbvZ;
    public Float zzbvb;
    public Double zzbvc;
    
    public zzc()
    {
      zzMD();
    }
    
    public static zzc[] zzMC()
    {
      if (zzbvY == null) {}
      synchronized (zzbur.zzcsf)
      {
        if (zzbvY == null) {
          zzbvY = new zzc[0];
        }
        return zzbvY;
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
          if (this.name == null)
          {
            if (((zzc)paramObject).name != null) {
              return false;
            }
          }
          else if (!this.name.equals(((zzc)paramObject).name)) {
            return false;
          }
          if (this.zzaFy == null)
          {
            if (((zzc)paramObject).zzaFy != null) {
              return false;
            }
          }
          else if (!this.zzaFy.equals(((zzc)paramObject).zzaFy)) {
            return false;
          }
          if (this.zzbvZ == null)
          {
            if (((zzc)paramObject).zzbvZ != null) {
              return false;
            }
          }
          else if (!this.zzbvZ.equals(((zzc)paramObject).zzbvZ)) {
            return false;
          }
          if (this.zzbvb == null)
          {
            if (((zzc)paramObject).zzbvb != null) {
              return false;
            }
          }
          else if (!this.zzbvb.equals(((zzc)paramObject).zzbvb)) {
            return false;
          }
          if (this.zzbvc != null) {
            break;
          }
        } while (((zzc)paramObject).zzbvc == null);
        return false;
      } while (this.zzbvc.equals(((zzc)paramObject).zzbvc));
      return false;
    }
    
    public int hashCode()
    {
      int n = 0;
      int i1 = getClass().getName().hashCode();
      int i;
      int j;
      label33:
      int k;
      label42:
      int m;
      if (this.name == null)
      {
        i = 0;
        if (this.zzaFy != null) {
          break label104;
        }
        j = 0;
        if (this.zzbvZ != null) {
          break label115;
        }
        k = 0;
        if (this.zzbvb != null) {
          break label126;
        }
        m = 0;
        label52:
        if (this.zzbvc != null) {
          break label138;
        }
      }
      for (;;)
      {
        return (m + (k + (j + (i + (i1 + 527) * 31) * 31) * 31) * 31) * 31 + n;
        i = this.name.hashCode();
        break;
        label104:
        j = this.zzaFy.hashCode();
        break label33;
        label115:
        k = this.zzbvZ.hashCode();
        break label42;
        label126:
        m = this.zzbvb.hashCode();
        break label52;
        label138:
        n = this.zzbvc.hashCode();
      }
    }
    
    public zzc zzMD()
    {
      this.name = null;
      this.zzaFy = null;
      this.zzbvZ = null;
      this.zzbvb = null;
      this.zzbvc = null;
      this.zzcsg = -1;
      return this;
    }
    
    public zzc zzR(zzbul paramzzbul)
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
        case 18: 
          this.zzaFy = paramzzbul.readString();
          break;
        case 24: 
          this.zzbvZ = Long.valueOf(paramzzbul.zzacx());
          break;
        case 37: 
          this.zzbvb = Float.valueOf(paramzzbul.readFloat());
          break;
        case 41: 
          this.zzbvc = Double.valueOf(paramzzbul.readDouble());
        }
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.name != null) {
        paramzzbum.zzq(1, this.name);
      }
      if (this.zzaFy != null) {
        paramzzbum.zzq(2, this.zzaFy);
      }
      if (this.zzbvZ != null) {
        paramzzbum.zzb(3, this.zzbvZ.longValue());
      }
      if (this.zzbvb != null) {
        paramzzbum.zzc(4, this.zzbvb.floatValue());
      }
      if (this.zzbvc != null) {
        paramzzbum.zza(5, this.zzbvc.doubleValue());
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
      if (this.zzaFy != null) {
        j = i + zzbum.zzr(2, this.zzaFy);
      }
      i = j;
      if (this.zzbvZ != null) {
        i = j + zzbum.zzf(3, this.zzbvZ.longValue());
      }
      j = i;
      if (this.zzbvb != null) {
        j = i + zzbum.zzd(4, this.zzbvb.floatValue());
      }
      i = j;
      if (this.zzbvc != null) {
        i = j + zzbum.zzb(5, this.zzbvc.doubleValue());
      }
      return i;
    }
  }
  
  public static final class zzd
    extends zzbut
  {
    public zzauh.zze[] zzbwa;
    
    public zzd()
    {
      zzME();
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        return true;
        if (!(paramObject instanceof zzd)) {
          return false;
        }
        paramObject = (zzd)paramObject;
      } while (zzbur.equals(this.zzbwa, ((zzd)paramObject).zzbwa));
      return false;
    }
    
    public int hashCode()
    {
      return (getClass().getName().hashCode() + 527) * 31 + zzbur.hashCode(this.zzbwa);
    }
    
    public zzd zzME()
    {
      this.zzbwa = zzauh.zze.zzMF();
      this.zzcsg = -1;
      return this;
    }
    
    public zzd zzS(zzbul paramzzbul)
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
          int j = zzbuw.zzc(paramzzbul, 10);
          if (this.zzbwa == null) {}
          zzauh.zze[] arrayOfzze;
          for (i = 0;; i = this.zzbwa.length)
          {
            arrayOfzze = new zzauh.zze[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbwa, 0, arrayOfzze, 0, i);
              j = i;
            }
            while (j < arrayOfzze.length - 1)
            {
              arrayOfzze[j] = new zzauh.zze();
              paramzzbul.zza(arrayOfzze[j]);
              paramzzbul.zzacu();
              j += 1;
            }
          }
          arrayOfzze[j] = new zzauh.zze();
          paramzzbul.zza(arrayOfzze[j]);
          this.zzbwa = arrayOfzze;
        }
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if ((this.zzbwa != null) && (this.zzbwa.length > 0))
      {
        int i = 0;
        while (i < this.zzbwa.length)
        {
          zzauh.zze localzze = this.zzbwa[i];
          if (localzze != null) {
            paramzzbum.zza(1, localzze);
          }
          i += 1;
        }
      }
      super.zza(paramzzbum);
    }
    
    protected int zzv()
    {
      int i = super.zzv();
      int k = i;
      if (this.zzbwa != null)
      {
        k = i;
        if (this.zzbwa.length > 0)
        {
          int j = 0;
          for (;;)
          {
            k = i;
            if (j >= this.zzbwa.length) {
              break;
            }
            zzauh.zze localzze = this.zzbwa[j];
            k = i;
            if (localzze != null) {
              k = i + zzbum.zzc(1, localzze);
            }
            j += 1;
            i = k;
          }
        }
      }
      return k;
    }
  }
  
  public static final class zze
    extends zzbut
  {
    private static volatile zze[] zzbwb;
    public String zzaR;
    public String zzba;
    public String zzbhg;
    public String zzbqf;
    public String zzbqg;
    public String zzbqj;
    public String zzbqn;
    public String zzbwA;
    public Long zzbwB;
    public Integer zzbwc;
    public zzauh.zzb[] zzbwd;
    public zzauh.zzg[] zzbwe;
    public Long zzbwf;
    public Long zzbwg;
    public Long zzbwh;
    public Long zzbwi;
    public Long zzbwj;
    public String zzbwk;
    public String zzbwl;
    public String zzbwm;
    public Integer zzbwn;
    public Long zzbwo;
    public Long zzbwp;
    public String zzbwq;
    public Boolean zzbwr;
    public String zzbws;
    public Long zzbwt;
    public Integer zzbwu;
    public Boolean zzbwv;
    public zzauh.zza[] zzbww;
    public Integer zzbwx;
    public Integer zzbwy;
    public Integer zzbwz;
    
    public zze()
    {
      zzMG();
    }
    
    public static zze[] zzMF()
    {
      if (zzbwb == null) {}
      synchronized (zzbur.zzcsf)
      {
        if (zzbwb == null) {
          zzbwb = new zze[0];
        }
        return zzbwb;
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
          if (!(paramObject instanceof zze)) {
            return false;
          }
          paramObject = (zze)paramObject;
          if (this.zzbwc == null)
          {
            if (((zze)paramObject).zzbwc != null) {
              return false;
            }
          }
          else if (!this.zzbwc.equals(((zze)paramObject).zzbwc)) {
            return false;
          }
          if (!zzbur.equals(this.zzbwd, ((zze)paramObject).zzbwd)) {
            return false;
          }
          if (!zzbur.equals(this.zzbwe, ((zze)paramObject).zzbwe)) {
            return false;
          }
          if (this.zzbwf == null)
          {
            if (((zze)paramObject).zzbwf != null) {
              return false;
            }
          }
          else if (!this.zzbwf.equals(((zze)paramObject).zzbwf)) {
            return false;
          }
          if (this.zzbwg == null)
          {
            if (((zze)paramObject).zzbwg != null) {
              return false;
            }
          }
          else if (!this.zzbwg.equals(((zze)paramObject).zzbwg)) {
            return false;
          }
          if (this.zzbwh == null)
          {
            if (((zze)paramObject).zzbwh != null) {
              return false;
            }
          }
          else if (!this.zzbwh.equals(((zze)paramObject).zzbwh)) {
            return false;
          }
          if (this.zzbwi == null)
          {
            if (((zze)paramObject).zzbwi != null) {
              return false;
            }
          }
          else if (!this.zzbwi.equals(((zze)paramObject).zzbwi)) {
            return false;
          }
          if (this.zzbwj == null)
          {
            if (((zze)paramObject).zzbwj != null) {
              return false;
            }
          }
          else if (!this.zzbwj.equals(((zze)paramObject).zzbwj)) {
            return false;
          }
          if (this.zzbwk == null)
          {
            if (((zze)paramObject).zzbwk != null) {
              return false;
            }
          }
          else if (!this.zzbwk.equals(((zze)paramObject).zzbwk)) {
            return false;
          }
          if (this.zzba == null)
          {
            if (((zze)paramObject).zzba != null) {
              return false;
            }
          }
          else if (!this.zzba.equals(((zze)paramObject).zzba)) {
            return false;
          }
          if (this.zzbwl == null)
          {
            if (((zze)paramObject).zzbwl != null) {
              return false;
            }
          }
          else if (!this.zzbwl.equals(((zze)paramObject).zzbwl)) {
            return false;
          }
          if (this.zzbwm == null)
          {
            if (((zze)paramObject).zzbwm != null) {
              return false;
            }
          }
          else if (!this.zzbwm.equals(((zze)paramObject).zzbwm)) {
            return false;
          }
          if (this.zzbwn == null)
          {
            if (((zze)paramObject).zzbwn != null) {
              return false;
            }
          }
          else if (!this.zzbwn.equals(((zze)paramObject).zzbwn)) {
            return false;
          }
          if (this.zzbqg == null)
          {
            if (((zze)paramObject).zzbqg != null) {
              return false;
            }
          }
          else if (!this.zzbqg.equals(((zze)paramObject).zzbqg)) {
            return false;
          }
          if (this.zzaR == null)
          {
            if (((zze)paramObject).zzaR != null) {
              return false;
            }
          }
          else if (!this.zzaR.equals(((zze)paramObject).zzaR)) {
            return false;
          }
          if (this.zzbhg == null)
          {
            if (((zze)paramObject).zzbhg != null) {
              return false;
            }
          }
          else if (!this.zzbhg.equals(((zze)paramObject).zzbhg)) {
            return false;
          }
          if (this.zzbwo == null)
          {
            if (((zze)paramObject).zzbwo != null) {
              return false;
            }
          }
          else if (!this.zzbwo.equals(((zze)paramObject).zzbwo)) {
            return false;
          }
          if (this.zzbwp == null)
          {
            if (((zze)paramObject).zzbwp != null) {
              return false;
            }
          }
          else if (!this.zzbwp.equals(((zze)paramObject).zzbwp)) {
            return false;
          }
          if (this.zzbwq == null)
          {
            if (((zze)paramObject).zzbwq != null) {
              return false;
            }
          }
          else if (!this.zzbwq.equals(((zze)paramObject).zzbwq)) {
            return false;
          }
          if (this.zzbwr == null)
          {
            if (((zze)paramObject).zzbwr != null) {
              return false;
            }
          }
          else if (!this.zzbwr.equals(((zze)paramObject).zzbwr)) {
            return false;
          }
          if (this.zzbws == null)
          {
            if (((zze)paramObject).zzbws != null) {
              return false;
            }
          }
          else if (!this.zzbws.equals(((zze)paramObject).zzbws)) {
            return false;
          }
          if (this.zzbwt == null)
          {
            if (((zze)paramObject).zzbwt != null) {
              return false;
            }
          }
          else if (!this.zzbwt.equals(((zze)paramObject).zzbwt)) {
            return false;
          }
          if (this.zzbwu == null)
          {
            if (((zze)paramObject).zzbwu != null) {
              return false;
            }
          }
          else if (!this.zzbwu.equals(((zze)paramObject).zzbwu)) {
            return false;
          }
          if (this.zzbqj == null)
          {
            if (((zze)paramObject).zzbqj != null) {
              return false;
            }
          }
          else if (!this.zzbqj.equals(((zze)paramObject).zzbqj)) {
            return false;
          }
          if (this.zzbqf == null)
          {
            if (((zze)paramObject).zzbqf != null) {
              return false;
            }
          }
          else if (!this.zzbqf.equals(((zze)paramObject).zzbqf)) {
            return false;
          }
          if (this.zzbwv == null)
          {
            if (((zze)paramObject).zzbwv != null) {
              return false;
            }
          }
          else if (!this.zzbwv.equals(((zze)paramObject).zzbwv)) {
            return false;
          }
          if (!zzbur.equals(this.zzbww, ((zze)paramObject).zzbww)) {
            return false;
          }
          if (this.zzbqn == null)
          {
            if (((zze)paramObject).zzbqn != null) {
              return false;
            }
          }
          else if (!this.zzbqn.equals(((zze)paramObject).zzbqn)) {
            return false;
          }
          if (this.zzbwx == null)
          {
            if (((zze)paramObject).zzbwx != null) {
              return false;
            }
          }
          else if (!this.zzbwx.equals(((zze)paramObject).zzbwx)) {
            return false;
          }
          if (this.zzbwy == null)
          {
            if (((zze)paramObject).zzbwy != null) {
              return false;
            }
          }
          else if (!this.zzbwy.equals(((zze)paramObject).zzbwy)) {
            return false;
          }
          if (this.zzbwz == null)
          {
            if (((zze)paramObject).zzbwz != null) {
              return false;
            }
          }
          else if (!this.zzbwz.equals(((zze)paramObject).zzbwz)) {
            return false;
          }
          if (this.zzbwA == null)
          {
            if (((zze)paramObject).zzbwA != null) {
              return false;
            }
          }
          else if (!this.zzbwA.equals(((zze)paramObject).zzbwA)) {
            return false;
          }
          if (this.zzbwB != null) {
            break;
          }
        } while (((zze)paramObject).zzbwB == null);
        return false;
      } while (this.zzbwB.equals(((zze)paramObject).zzbwB));
      return false;
    }
    
    public int hashCode()
    {
      int i25 = 0;
      int i26 = getClass().getName().hashCode();
      int i;
      int i27;
      int i28;
      int j;
      label51:
      int k;
      label60:
      int m;
      label70:
      int n;
      label80:
      int i1;
      label90:
      int i2;
      label100:
      int i3;
      label110:
      int i4;
      label120:
      int i5;
      label130:
      int i6;
      label140:
      int i7;
      label150:
      int i8;
      label160:
      int i9;
      label170:
      int i10;
      label180:
      int i11;
      label190:
      int i12;
      label200:
      int i13;
      label210:
      int i14;
      label220:
      int i15;
      label230:
      int i16;
      label240:
      int i17;
      label250:
      int i18;
      label260:
      int i19;
      label270:
      int i29;
      int i20;
      label289:
      int i21;
      label299:
      int i22;
      label309:
      int i23;
      label319:
      int i24;
      if (this.zzbwc == null)
      {
        i = 0;
        i27 = zzbur.hashCode(this.zzbwd);
        i28 = zzbur.hashCode(this.zzbwe);
        if (this.zzbwf != null) {
          break label549;
        }
        j = 0;
        if (this.zzbwg != null) {
          break label560;
        }
        k = 0;
        if (this.zzbwh != null) {
          break label571;
        }
        m = 0;
        if (this.zzbwi != null) {
          break label583;
        }
        n = 0;
        if (this.zzbwj != null) {
          break label595;
        }
        i1 = 0;
        if (this.zzbwk != null) {
          break label607;
        }
        i2 = 0;
        if (this.zzba != null) {
          break label619;
        }
        i3 = 0;
        if (this.zzbwl != null) {
          break label631;
        }
        i4 = 0;
        if (this.zzbwm != null) {
          break label643;
        }
        i5 = 0;
        if (this.zzbwn != null) {
          break label655;
        }
        i6 = 0;
        if (this.zzbqg != null) {
          break label667;
        }
        i7 = 0;
        if (this.zzaR != null) {
          break label679;
        }
        i8 = 0;
        if (this.zzbhg != null) {
          break label691;
        }
        i9 = 0;
        if (this.zzbwo != null) {
          break label703;
        }
        i10 = 0;
        if (this.zzbwp != null) {
          break label715;
        }
        i11 = 0;
        if (this.zzbwq != null) {
          break label727;
        }
        i12 = 0;
        if (this.zzbwr != null) {
          break label739;
        }
        i13 = 0;
        if (this.zzbws != null) {
          break label751;
        }
        i14 = 0;
        if (this.zzbwt != null) {
          break label763;
        }
        i15 = 0;
        if (this.zzbwu != null) {
          break label775;
        }
        i16 = 0;
        if (this.zzbqj != null) {
          break label787;
        }
        i17 = 0;
        if (this.zzbqf != null) {
          break label799;
        }
        i18 = 0;
        if (this.zzbwv != null) {
          break label811;
        }
        i19 = 0;
        i29 = zzbur.hashCode(this.zzbww);
        if (this.zzbqn != null) {
          break label823;
        }
        i20 = 0;
        if (this.zzbwx != null) {
          break label835;
        }
        i21 = 0;
        if (this.zzbwy != null) {
          break label847;
        }
        i22 = 0;
        if (this.zzbwz != null) {
          break label859;
        }
        i23 = 0;
        if (this.zzbwA != null) {
          break label871;
        }
        i24 = 0;
        label329:
        if (this.zzbwB != null) {
          break label883;
        }
      }
      for (;;)
      {
        return (i24 + (i23 + (i22 + (i21 + (i20 + ((i19 + (i18 + (i17 + (i16 + (i15 + (i14 + (i13 + (i12 + (i11 + (i10 + (i9 + (i8 + (i7 + (i6 + (i5 + (i4 + (i3 + (i2 + (i1 + (n + (m + (k + (j + (((i + (i26 + 527) * 31) * 31 + i27) * 31 + i28) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31 + i29) * 31) * 31) * 31) * 31) * 31) * 31 + i25;
        i = this.zzbwc.hashCode();
        break;
        label549:
        j = this.zzbwf.hashCode();
        break label51;
        label560:
        k = this.zzbwg.hashCode();
        break label60;
        label571:
        m = this.zzbwh.hashCode();
        break label70;
        label583:
        n = this.zzbwi.hashCode();
        break label80;
        label595:
        i1 = this.zzbwj.hashCode();
        break label90;
        label607:
        i2 = this.zzbwk.hashCode();
        break label100;
        label619:
        i3 = this.zzba.hashCode();
        break label110;
        label631:
        i4 = this.zzbwl.hashCode();
        break label120;
        label643:
        i5 = this.zzbwm.hashCode();
        break label130;
        label655:
        i6 = this.zzbwn.hashCode();
        break label140;
        label667:
        i7 = this.zzbqg.hashCode();
        break label150;
        label679:
        i8 = this.zzaR.hashCode();
        break label160;
        label691:
        i9 = this.zzbhg.hashCode();
        break label170;
        label703:
        i10 = this.zzbwo.hashCode();
        break label180;
        label715:
        i11 = this.zzbwp.hashCode();
        break label190;
        label727:
        i12 = this.zzbwq.hashCode();
        break label200;
        label739:
        i13 = this.zzbwr.hashCode();
        break label210;
        label751:
        i14 = this.zzbws.hashCode();
        break label220;
        label763:
        i15 = this.zzbwt.hashCode();
        break label230;
        label775:
        i16 = this.zzbwu.hashCode();
        break label240;
        label787:
        i17 = this.zzbqj.hashCode();
        break label250;
        label799:
        i18 = this.zzbqf.hashCode();
        break label260;
        label811:
        i19 = this.zzbwv.hashCode();
        break label270;
        label823:
        i20 = this.zzbqn.hashCode();
        break label289;
        label835:
        i21 = this.zzbwx.hashCode();
        break label299;
        label847:
        i22 = this.zzbwy.hashCode();
        break label309;
        label859:
        i23 = this.zzbwz.hashCode();
        break label319;
        label871:
        i24 = this.zzbwA.hashCode();
        break label329;
        label883:
        i25 = this.zzbwB.hashCode();
      }
    }
    
    public zze zzMG()
    {
      this.zzbwc = null;
      this.zzbwd = zzauh.zzb.zzMA();
      this.zzbwe = zzauh.zzg.zzMI();
      this.zzbwf = null;
      this.zzbwg = null;
      this.zzbwh = null;
      this.zzbwi = null;
      this.zzbwj = null;
      this.zzbwk = null;
      this.zzba = null;
      this.zzbwl = null;
      this.zzbwm = null;
      this.zzbwn = null;
      this.zzbqg = null;
      this.zzaR = null;
      this.zzbhg = null;
      this.zzbwo = null;
      this.zzbwp = null;
      this.zzbwq = null;
      this.zzbwr = null;
      this.zzbws = null;
      this.zzbwt = null;
      this.zzbwu = null;
      this.zzbqj = null;
      this.zzbqf = null;
      this.zzbwv = null;
      this.zzbww = zzauh.zza.zzMy();
      this.zzbqn = null;
      this.zzbwx = null;
      this.zzbwy = null;
      this.zzbwz = null;
      this.zzbwA = null;
      this.zzbwB = null;
      this.zzcsg = -1;
      return this;
    }
    
    public zze zzT(zzbul paramzzbul)
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
          this.zzbwc = Integer.valueOf(paramzzbul.zzacy());
          break;
        case 18: 
          j = zzbuw.zzc(paramzzbul, 18);
          if (this.zzbwd == null) {}
          for (i = 0;; i = this.zzbwd.length)
          {
            localObject = new zzauh.zzb[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbwd, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzauh.zzb();
              paramzzbul.zza(localObject[j]);
              paramzzbul.zzacu();
              j += 1;
            }
          }
          localObject[j] = new zzauh.zzb();
          paramzzbul.zza(localObject[j]);
          this.zzbwd = ((zzauh.zzb[])localObject);
          break;
        case 26: 
          j = zzbuw.zzc(paramzzbul, 26);
          if (this.zzbwe == null) {}
          for (i = 0;; i = this.zzbwe.length)
          {
            localObject = new zzauh.zzg[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbwe, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzauh.zzg();
              paramzzbul.zza(localObject[j]);
              paramzzbul.zzacu();
              j += 1;
            }
          }
          localObject[j] = new zzauh.zzg();
          paramzzbul.zza(localObject[j]);
          this.zzbwe = ((zzauh.zzg[])localObject);
          break;
        case 32: 
          this.zzbwf = Long.valueOf(paramzzbul.zzacx());
          break;
        case 40: 
          this.zzbwg = Long.valueOf(paramzzbul.zzacx());
          break;
        case 48: 
          this.zzbwh = Long.valueOf(paramzzbul.zzacx());
          break;
        case 56: 
          this.zzbwj = Long.valueOf(paramzzbul.zzacx());
          break;
        case 66: 
          this.zzbwk = paramzzbul.readString();
          break;
        case 74: 
          this.zzba = paramzzbul.readString();
          break;
        case 82: 
          this.zzbwl = paramzzbul.readString();
          break;
        case 90: 
          this.zzbwm = paramzzbul.readString();
          break;
        case 96: 
          this.zzbwn = Integer.valueOf(paramzzbul.zzacy());
          break;
        case 106: 
          this.zzbqg = paramzzbul.readString();
          break;
        case 114: 
          this.zzaR = paramzzbul.readString();
          break;
        case 130: 
          this.zzbhg = paramzzbul.readString();
          break;
        case 136: 
          this.zzbwo = Long.valueOf(paramzzbul.zzacx());
          break;
        case 144: 
          this.zzbwp = Long.valueOf(paramzzbul.zzacx());
          break;
        case 154: 
          this.zzbwq = paramzzbul.readString();
          break;
        case 160: 
          this.zzbwr = Boolean.valueOf(paramzzbul.zzacA());
          break;
        case 170: 
          this.zzbws = paramzzbul.readString();
          break;
        case 176: 
          this.zzbwt = Long.valueOf(paramzzbul.zzacx());
          break;
        case 184: 
          this.zzbwu = Integer.valueOf(paramzzbul.zzacy());
          break;
        case 194: 
          this.zzbqj = paramzzbul.readString();
          break;
        case 202: 
          this.zzbqf = paramzzbul.readString();
          break;
        case 208: 
          this.zzbwi = Long.valueOf(paramzzbul.zzacx());
          break;
        case 224: 
          this.zzbwv = Boolean.valueOf(paramzzbul.zzacA());
          break;
        case 234: 
          j = zzbuw.zzc(paramzzbul, 234);
          if (this.zzbww == null) {}
          for (i = 0;; i = this.zzbww.length)
          {
            localObject = new zzauh.zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbww, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzauh.zza();
              paramzzbul.zza(localObject[j]);
              paramzzbul.zzacu();
              j += 1;
            }
          }
          localObject[j] = new zzauh.zza();
          paramzzbul.zza(localObject[j]);
          this.zzbww = ((zzauh.zza[])localObject);
          break;
        case 242: 
          this.zzbqn = paramzzbul.readString();
          break;
        case 248: 
          this.zzbwx = Integer.valueOf(paramzzbul.zzacy());
          break;
        case 256: 
          this.zzbwy = Integer.valueOf(paramzzbul.zzacy());
          break;
        case 264: 
          this.zzbwz = Integer.valueOf(paramzzbul.zzacy());
          break;
        case 274: 
          this.zzbwA = paramzzbul.readString();
          break;
        case 280: 
          this.zzbwB = Long.valueOf(paramzzbul.zzacx());
        }
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      int j = 0;
      if (this.zzbwc != null) {
        paramzzbum.zzF(1, this.zzbwc.intValue());
      }
      int i;
      Object localObject;
      if ((this.zzbwd != null) && (this.zzbwd.length > 0))
      {
        i = 0;
        while (i < this.zzbwd.length)
        {
          localObject = this.zzbwd[i];
          if (localObject != null) {
            paramzzbum.zza(2, (zzbut)localObject);
          }
          i += 1;
        }
      }
      if ((this.zzbwe != null) && (this.zzbwe.length > 0))
      {
        i = 0;
        while (i < this.zzbwe.length)
        {
          localObject = this.zzbwe[i];
          if (localObject != null) {
            paramzzbum.zza(3, (zzbut)localObject);
          }
          i += 1;
        }
      }
      if (this.zzbwf != null) {
        paramzzbum.zzb(4, this.zzbwf.longValue());
      }
      if (this.zzbwg != null) {
        paramzzbum.zzb(5, this.zzbwg.longValue());
      }
      if (this.zzbwh != null) {
        paramzzbum.zzb(6, this.zzbwh.longValue());
      }
      if (this.zzbwj != null) {
        paramzzbum.zzb(7, this.zzbwj.longValue());
      }
      if (this.zzbwk != null) {
        paramzzbum.zzq(8, this.zzbwk);
      }
      if (this.zzba != null) {
        paramzzbum.zzq(9, this.zzba);
      }
      if (this.zzbwl != null) {
        paramzzbum.zzq(10, this.zzbwl);
      }
      if (this.zzbwm != null) {
        paramzzbum.zzq(11, this.zzbwm);
      }
      if (this.zzbwn != null) {
        paramzzbum.zzF(12, this.zzbwn.intValue());
      }
      if (this.zzbqg != null) {
        paramzzbum.zzq(13, this.zzbqg);
      }
      if (this.zzaR != null) {
        paramzzbum.zzq(14, this.zzaR);
      }
      if (this.zzbhg != null) {
        paramzzbum.zzq(16, this.zzbhg);
      }
      if (this.zzbwo != null) {
        paramzzbum.zzb(17, this.zzbwo.longValue());
      }
      if (this.zzbwp != null) {
        paramzzbum.zzb(18, this.zzbwp.longValue());
      }
      if (this.zzbwq != null) {
        paramzzbum.zzq(19, this.zzbwq);
      }
      if (this.zzbwr != null) {
        paramzzbum.zzg(20, this.zzbwr.booleanValue());
      }
      if (this.zzbws != null) {
        paramzzbum.zzq(21, this.zzbws);
      }
      if (this.zzbwt != null) {
        paramzzbum.zzb(22, this.zzbwt.longValue());
      }
      if (this.zzbwu != null) {
        paramzzbum.zzF(23, this.zzbwu.intValue());
      }
      if (this.zzbqj != null) {
        paramzzbum.zzq(24, this.zzbqj);
      }
      if (this.zzbqf != null) {
        paramzzbum.zzq(25, this.zzbqf);
      }
      if (this.zzbwi != null) {
        paramzzbum.zzb(26, this.zzbwi.longValue());
      }
      if (this.zzbwv != null) {
        paramzzbum.zzg(28, this.zzbwv.booleanValue());
      }
      if ((this.zzbww != null) && (this.zzbww.length > 0))
      {
        i = j;
        while (i < this.zzbww.length)
        {
          localObject = this.zzbww[i];
          if (localObject != null) {
            paramzzbum.zza(29, (zzbut)localObject);
          }
          i += 1;
        }
      }
      if (this.zzbqn != null) {
        paramzzbum.zzq(30, this.zzbqn);
      }
      if (this.zzbwx != null) {
        paramzzbum.zzF(31, this.zzbwx.intValue());
      }
      if (this.zzbwy != null) {
        paramzzbum.zzF(32, this.zzbwy.intValue());
      }
      if (this.zzbwz != null) {
        paramzzbum.zzF(33, this.zzbwz.intValue());
      }
      if (this.zzbwA != null) {
        paramzzbum.zzq(34, this.zzbwA);
      }
      if (this.zzbwB != null) {
        paramzzbum.zzb(35, this.zzbwB.longValue());
      }
      super.zza(paramzzbum);
    }
    
    protected int zzv()
    {
      int m = 0;
      int j = super.zzv();
      int i = j;
      if (this.zzbwc != null) {
        i = j + zzbum.zzH(1, this.zzbwc.intValue());
      }
      j = i;
      Object localObject;
      if (this.zzbwd != null)
      {
        j = i;
        if (this.zzbwd.length > 0)
        {
          j = 0;
          while (j < this.zzbwd.length)
          {
            localObject = this.zzbwd[j];
            k = i;
            if (localObject != null) {
              k = i + zzbum.zzc(2, (zzbut)localObject);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (this.zzbwe != null)
      {
        i = j;
        if (this.zzbwe.length > 0)
        {
          i = j;
          j = 0;
          while (j < this.zzbwe.length)
          {
            localObject = this.zzbwe[j];
            k = i;
            if (localObject != null) {
              k = i + zzbum.zzc(3, (zzbut)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      j = i;
      if (this.zzbwf != null) {
        j = i + zzbum.zzf(4, this.zzbwf.longValue());
      }
      i = j;
      if (this.zzbwg != null) {
        i = j + zzbum.zzf(5, this.zzbwg.longValue());
      }
      j = i;
      if (this.zzbwh != null) {
        j = i + zzbum.zzf(6, this.zzbwh.longValue());
      }
      i = j;
      if (this.zzbwj != null) {
        i = j + zzbum.zzf(7, this.zzbwj.longValue());
      }
      j = i;
      if (this.zzbwk != null) {
        j = i + zzbum.zzr(8, this.zzbwk);
      }
      i = j;
      if (this.zzba != null) {
        i = j + zzbum.zzr(9, this.zzba);
      }
      j = i;
      if (this.zzbwl != null) {
        j = i + zzbum.zzr(10, this.zzbwl);
      }
      i = j;
      if (this.zzbwm != null) {
        i = j + zzbum.zzr(11, this.zzbwm);
      }
      j = i;
      if (this.zzbwn != null) {
        j = i + zzbum.zzH(12, this.zzbwn.intValue());
      }
      i = j;
      if (this.zzbqg != null) {
        i = j + zzbum.zzr(13, this.zzbqg);
      }
      j = i;
      if (this.zzaR != null) {
        j = i + zzbum.zzr(14, this.zzaR);
      }
      i = j;
      if (this.zzbhg != null) {
        i = j + zzbum.zzr(16, this.zzbhg);
      }
      j = i;
      if (this.zzbwo != null) {
        j = i + zzbum.zzf(17, this.zzbwo.longValue());
      }
      i = j;
      if (this.zzbwp != null) {
        i = j + zzbum.zzf(18, this.zzbwp.longValue());
      }
      j = i;
      if (this.zzbwq != null) {
        j = i + zzbum.zzr(19, this.zzbwq);
      }
      i = j;
      if (this.zzbwr != null) {
        i = j + zzbum.zzh(20, this.zzbwr.booleanValue());
      }
      j = i;
      if (this.zzbws != null) {
        j = i + zzbum.zzr(21, this.zzbws);
      }
      i = j;
      if (this.zzbwt != null) {
        i = j + zzbum.zzf(22, this.zzbwt.longValue());
      }
      j = i;
      if (this.zzbwu != null) {
        j = i + zzbum.zzH(23, this.zzbwu.intValue());
      }
      i = j;
      if (this.zzbqj != null) {
        i = j + zzbum.zzr(24, this.zzbqj);
      }
      j = i;
      if (this.zzbqf != null) {
        j = i + zzbum.zzr(25, this.zzbqf);
      }
      int k = j;
      if (this.zzbwi != null) {
        k = j + zzbum.zzf(26, this.zzbwi.longValue());
      }
      i = k;
      if (this.zzbwv != null) {
        i = k + zzbum.zzh(28, this.zzbwv.booleanValue());
      }
      j = i;
      if (this.zzbww != null)
      {
        j = i;
        if (this.zzbww.length > 0)
        {
          k = m;
          for (;;)
          {
            j = i;
            if (k >= this.zzbww.length) {
              break;
            }
            localObject = this.zzbww[k];
            j = i;
            if (localObject != null) {
              j = i + zzbum.zzc(29, (zzbut)localObject);
            }
            k += 1;
            i = j;
          }
        }
      }
      i = j;
      if (this.zzbqn != null) {
        i = j + zzbum.zzr(30, this.zzbqn);
      }
      j = i;
      if (this.zzbwx != null) {
        j = i + zzbum.zzH(31, this.zzbwx.intValue());
      }
      i = j;
      if (this.zzbwy != null) {
        i = j + zzbum.zzH(32, this.zzbwy.intValue());
      }
      j = i;
      if (this.zzbwz != null) {
        j = i + zzbum.zzH(33, this.zzbwz.intValue());
      }
      i = j;
      if (this.zzbwA != null) {
        i = j + zzbum.zzr(34, this.zzbwA);
      }
      j = i;
      if (this.zzbwB != null) {
        j = i + zzbum.zzf(35, this.zzbwB.longValue());
      }
      return j;
    }
  }
  
  public static final class zzf
    extends zzbut
  {
    public long[] zzbwC;
    public long[] zzbwD;
    
    public zzf()
    {
      zzMH();
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        return true;
        if (!(paramObject instanceof zzf)) {
          return false;
        }
        paramObject = (zzf)paramObject;
        if (!zzbur.equals(this.zzbwC, ((zzf)paramObject).zzbwC)) {
          return false;
        }
      } while (zzbur.equals(this.zzbwD, ((zzf)paramObject).zzbwD));
      return false;
    }
    
    public int hashCode()
    {
      return ((getClass().getName().hashCode() + 527) * 31 + zzbur.hashCode(this.zzbwC)) * 31 + zzbur.hashCode(this.zzbwD);
    }
    
    public zzf zzMH()
    {
      this.zzbwC = zzbuw.zzcsj;
      this.zzbwD = zzbuw.zzcsj;
      this.zzcsg = -1;
      return this;
    }
    
    public zzf zzU(zzbul paramzzbul)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzbul.zzacu();
        int j;
        long[] arrayOfLong;
        int k;
        switch (i)
        {
        default: 
          if (zzbuw.zzb(paramzzbul, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          j = zzbuw.zzc(paramzzbul, 8);
          if (this.zzbwC == null) {}
          for (i = 0;; i = this.zzbwC.length)
          {
            arrayOfLong = new long[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbwC, 0, arrayOfLong, 0, i);
              j = i;
            }
            while (j < arrayOfLong.length - 1)
            {
              arrayOfLong[j] = paramzzbul.zzacw();
              paramzzbul.zzacu();
              j += 1;
            }
          }
          arrayOfLong[j] = paramzzbul.zzacw();
          this.zzbwC = arrayOfLong;
          break;
        case 10: 
          k = paramzzbul.zzqj(paramzzbul.zzacD());
          i = paramzzbul.getPosition();
          j = 0;
          while (paramzzbul.zzacI() > 0)
          {
            paramzzbul.zzacw();
            j += 1;
          }
          paramzzbul.zzql(i);
          if (this.zzbwC == null) {}
          for (i = 0;; i = this.zzbwC.length)
          {
            arrayOfLong = new long[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbwC, 0, arrayOfLong, 0, i);
              j = i;
            }
            while (j < arrayOfLong.length)
            {
              arrayOfLong[j] = paramzzbul.zzacw();
              j += 1;
            }
          }
          this.zzbwC = arrayOfLong;
          paramzzbul.zzqk(k);
          break;
        case 16: 
          j = zzbuw.zzc(paramzzbul, 16);
          if (this.zzbwD == null) {}
          for (i = 0;; i = this.zzbwD.length)
          {
            arrayOfLong = new long[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbwD, 0, arrayOfLong, 0, i);
              j = i;
            }
            while (j < arrayOfLong.length - 1)
            {
              arrayOfLong[j] = paramzzbul.zzacw();
              paramzzbul.zzacu();
              j += 1;
            }
          }
          arrayOfLong[j] = paramzzbul.zzacw();
          this.zzbwD = arrayOfLong;
          break;
        case 18: 
          k = paramzzbul.zzqj(paramzzbul.zzacD());
          i = paramzzbul.getPosition();
          j = 0;
          while (paramzzbul.zzacI() > 0)
          {
            paramzzbul.zzacw();
            j += 1;
          }
          paramzzbul.zzql(i);
          if (this.zzbwD == null) {}
          for (i = 0;; i = this.zzbwD.length)
          {
            arrayOfLong = new long[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbwD, 0, arrayOfLong, 0, i);
              j = i;
            }
            while (j < arrayOfLong.length)
            {
              arrayOfLong[j] = paramzzbul.zzacw();
              j += 1;
            }
          }
          this.zzbwD = arrayOfLong;
          paramzzbul.zzqk(k);
        }
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      int j = 0;
      int i;
      if ((this.zzbwC != null) && (this.zzbwC.length > 0))
      {
        i = 0;
        while (i < this.zzbwC.length)
        {
          paramzzbum.zza(1, this.zzbwC[i]);
          i += 1;
        }
      }
      if ((this.zzbwD != null) && (this.zzbwD.length > 0))
      {
        i = j;
        while (i < this.zzbwD.length)
        {
          paramzzbum.zza(2, this.zzbwD[i]);
          i += 1;
        }
      }
      super.zza(paramzzbum);
    }
    
    protected int zzv()
    {
      int m = 0;
      int k = super.zzv();
      int j;
      if ((this.zzbwC != null) && (this.zzbwC.length > 0))
      {
        i = 0;
        j = 0;
        while (i < this.zzbwC.length)
        {
          j += zzbum.zzba(this.zzbwC[i]);
          i += 1;
        }
      }
      for (int i = k + j + this.zzbwC.length * 1;; i = k)
      {
        j = i;
        if (this.zzbwD != null)
        {
          j = i;
          if (this.zzbwD.length > 0)
          {
            k = 0;
            j = m;
            while (j < this.zzbwD.length)
            {
              k += zzbum.zzba(this.zzbwD[j]);
              j += 1;
            }
            j = i + k + this.zzbwD.length * 1;
          }
        }
        return j;
      }
    }
  }
  
  public static final class zzg
    extends zzbut
  {
    private static volatile zzg[] zzbwE;
    public String name;
    public String zzaFy;
    public Long zzbvZ;
    public Float zzbvb;
    public Double zzbvc;
    public Long zzbwF;
    
    public zzg()
    {
      zzMJ();
    }
    
    public static zzg[] zzMI()
    {
      if (zzbwE == null) {}
      synchronized (zzbur.zzcsf)
      {
        if (zzbwE == null) {
          zzbwE = new zzg[0];
        }
        return zzbwE;
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
          if (!(paramObject instanceof zzg)) {
            return false;
          }
          paramObject = (zzg)paramObject;
          if (this.zzbwF == null)
          {
            if (((zzg)paramObject).zzbwF != null) {
              return false;
            }
          }
          else if (!this.zzbwF.equals(((zzg)paramObject).zzbwF)) {
            return false;
          }
          if (this.name == null)
          {
            if (((zzg)paramObject).name != null) {
              return false;
            }
          }
          else if (!this.name.equals(((zzg)paramObject).name)) {
            return false;
          }
          if (this.zzaFy == null)
          {
            if (((zzg)paramObject).zzaFy != null) {
              return false;
            }
          }
          else if (!this.zzaFy.equals(((zzg)paramObject).zzaFy)) {
            return false;
          }
          if (this.zzbvZ == null)
          {
            if (((zzg)paramObject).zzbvZ != null) {
              return false;
            }
          }
          else if (!this.zzbvZ.equals(((zzg)paramObject).zzbvZ)) {
            return false;
          }
          if (this.zzbvb == null)
          {
            if (((zzg)paramObject).zzbvb != null) {
              return false;
            }
          }
          else if (!this.zzbvb.equals(((zzg)paramObject).zzbvb)) {
            return false;
          }
          if (this.zzbvc != null) {
            break;
          }
        } while (((zzg)paramObject).zzbvc == null);
        return false;
      } while (this.zzbvc.equals(((zzg)paramObject).zzbvc));
      return false;
    }
    
    public int hashCode()
    {
      int i1 = 0;
      int i2 = getClass().getName().hashCode();
      int i;
      int j;
      label33:
      int k;
      label42:
      int m;
      label52:
      int n;
      if (this.zzbwF == null)
      {
        i = 0;
        if (this.name != null) {
          break label120;
        }
        j = 0;
        if (this.zzaFy != null) {
          break label131;
        }
        k = 0;
        if (this.zzbvZ != null) {
          break label142;
        }
        m = 0;
        if (this.zzbvb != null) {
          break label154;
        }
        n = 0;
        label62:
        if (this.zzbvc != null) {
          break label166;
        }
      }
      for (;;)
      {
        return (n + (m + (k + (j + (i + (i2 + 527) * 31) * 31) * 31) * 31) * 31) * 31 + i1;
        i = this.zzbwF.hashCode();
        break;
        label120:
        j = this.name.hashCode();
        break label33;
        label131:
        k = this.zzaFy.hashCode();
        break label42;
        label142:
        m = this.zzbvZ.hashCode();
        break label52;
        label154:
        n = this.zzbvb.hashCode();
        break label62;
        label166:
        i1 = this.zzbvc.hashCode();
      }
    }
    
    public zzg zzMJ()
    {
      this.zzbwF = null;
      this.name = null;
      this.zzaFy = null;
      this.zzbvZ = null;
      this.zzbvb = null;
      this.zzbvc = null;
      this.zzcsg = -1;
      return this;
    }
    
    public zzg zzV(zzbul paramzzbul)
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
        case 8: 
          this.zzbwF = Long.valueOf(paramzzbul.zzacx());
          break;
        case 18: 
          this.name = paramzzbul.readString();
          break;
        case 26: 
          this.zzaFy = paramzzbul.readString();
          break;
        case 32: 
          this.zzbvZ = Long.valueOf(paramzzbul.zzacx());
          break;
        case 45: 
          this.zzbvb = Float.valueOf(paramzzbul.readFloat());
          break;
        case 49: 
          this.zzbvc = Double.valueOf(paramzzbul.readDouble());
        }
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.zzbwF != null) {
        paramzzbum.zzb(1, this.zzbwF.longValue());
      }
      if (this.name != null) {
        paramzzbum.zzq(2, this.name);
      }
      if (this.zzaFy != null) {
        paramzzbum.zzq(3, this.zzaFy);
      }
      if (this.zzbvZ != null) {
        paramzzbum.zzb(4, this.zzbvZ.longValue());
      }
      if (this.zzbvb != null) {
        paramzzbum.zzc(5, this.zzbvb.floatValue());
      }
      if (this.zzbvc != null) {
        paramzzbum.zza(6, this.zzbvc.doubleValue());
      }
      super.zza(paramzzbum);
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.zzbwF != null) {
        i = j + zzbum.zzf(1, this.zzbwF.longValue());
      }
      j = i;
      if (this.name != null) {
        j = i + zzbum.zzr(2, this.name);
      }
      i = j;
      if (this.zzaFy != null) {
        i = j + zzbum.zzr(3, this.zzaFy);
      }
      j = i;
      if (this.zzbvZ != null) {
        j = i + zzbum.zzf(4, this.zzbvZ.longValue());
      }
      i = j;
      if (this.zzbvb != null) {
        i = j + zzbum.zzd(5, this.zzbvb.floatValue());
      }
      j = i;
      if (this.zzbvc != null) {
        j = i + zzbum.zzb(6, this.zzbvc.doubleValue());
      }
      return j;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzauh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */