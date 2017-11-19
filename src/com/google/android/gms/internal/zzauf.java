package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzauf
{
  public static final class zza
    extends zzbut
  {
    private static volatile zza[] zzbvg;
    public Integer zzbvh;
    public zzauf.zze[] zzbvi;
    public zzauf.zzb[] zzbvj;
    
    public zza()
    {
      zzMk();
    }
    
    public static zza[] zzMj()
    {
      if (zzbvg == null) {}
      synchronized (zzbur.zzcsf)
      {
        if (zzbvg == null) {
          zzbvg = new zza[0];
        }
        return zzbvg;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
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
        if (!zzbur.equals(this.zzbvi, ((zza)paramObject).zzbvi)) {
          return false;
        }
      } while (zzbur.equals(this.zzbvj, ((zza)paramObject).zzbvj));
      return false;
    }
    
    public int hashCode()
    {
      int j = getClass().getName().hashCode();
      if (this.zzbvh == null) {}
      for (int i = 0;; i = this.zzbvh.hashCode()) {
        return ((i + (j + 527) * 31) * 31 + zzbur.hashCode(this.zzbvi)) * 31 + zzbur.hashCode(this.zzbvj);
      }
    }
    
    public zza zzG(zzbul paramzzbul)
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
          this.zzbvh = Integer.valueOf(paramzzbul.zzacy());
          break;
        case 18: 
          j = zzbuw.zzc(paramzzbul, 18);
          if (this.zzbvi == null) {}
          for (i = 0;; i = this.zzbvi.length)
          {
            localObject = new zzauf.zze[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbvi, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzauf.zze();
              paramzzbul.zza(localObject[j]);
              paramzzbul.zzacu();
              j += 1;
            }
          }
          localObject[j] = new zzauf.zze();
          paramzzbul.zza(localObject[j]);
          this.zzbvi = ((zzauf.zze[])localObject);
          break;
        case 26: 
          j = zzbuw.zzc(paramzzbul, 26);
          if (this.zzbvj == null) {}
          for (i = 0;; i = this.zzbvj.length)
          {
            localObject = new zzauf.zzb[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbvj, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzauf.zzb();
              paramzzbul.zza(localObject[j]);
              paramzzbul.zzacu();
              j += 1;
            }
          }
          localObject[j] = new zzauf.zzb();
          paramzzbul.zza(localObject[j]);
          this.zzbvj = ((zzauf.zzb[])localObject);
        }
      }
    }
    
    public zza zzMk()
    {
      this.zzbvh = null;
      this.zzbvi = zzauf.zze.zzMq();
      this.zzbvj = zzauf.zzb.zzMl();
      this.zzcsg = -1;
      return this;
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      int j = 0;
      if (this.zzbvh != null) {
        paramzzbum.zzF(1, this.zzbvh.intValue());
      }
      int i;
      Object localObject;
      if ((this.zzbvi != null) && (this.zzbvi.length > 0))
      {
        i = 0;
        while (i < this.zzbvi.length)
        {
          localObject = this.zzbvi[i];
          if (localObject != null) {
            paramzzbum.zza(2, (zzbut)localObject);
          }
          i += 1;
        }
      }
      if ((this.zzbvj != null) && (this.zzbvj.length > 0))
      {
        i = j;
        while (i < this.zzbvj.length)
        {
          localObject = this.zzbvj[i];
          if (localObject != null) {
            paramzzbum.zza(3, (zzbut)localObject);
          }
          i += 1;
        }
      }
      super.zza(paramzzbum);
    }
    
    protected int zzv()
    {
      int m = 0;
      int i = super.zzv();
      int j = i;
      if (this.zzbvh != null) {
        j = i + zzbum.zzH(1, this.zzbvh.intValue());
      }
      i = j;
      Object localObject;
      if (this.zzbvi != null)
      {
        i = j;
        if (this.zzbvi.length > 0)
        {
          i = j;
          j = 0;
          while (j < this.zzbvi.length)
          {
            localObject = this.zzbvi[j];
            k = i;
            if (localObject != null) {
              k = i + zzbum.zzc(2, (zzbut)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      int k = i;
      if (this.zzbvj != null)
      {
        k = i;
        if (this.zzbvj.length > 0)
        {
          j = m;
          for (;;)
          {
            k = i;
            if (j >= this.zzbvj.length) {
              break;
            }
            localObject = this.zzbvj[j];
            k = i;
            if (localObject != null) {
              k = i + zzbum.zzc(3, (zzbut)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      return k;
    }
  }
  
  public static final class zzb
    extends zzbut
  {
    private static volatile zzb[] zzbvk;
    public Integer zzbvl;
    public String zzbvm;
    public zzauf.zzc[] zzbvn;
    public Boolean zzbvo;
    public zzauf.zzd zzbvp;
    
    public zzb()
    {
      zzMm();
    }
    
    public static zzb[] zzMl()
    {
      if (zzbvk == null) {}
      synchronized (zzbur.zzcsf)
      {
        if (zzbvk == null) {
          zzbvk = new zzb[0];
        }
        return zzbvk;
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
          if (this.zzbvl == null)
          {
            if (((zzb)paramObject).zzbvl != null) {
              return false;
            }
          }
          else if (!this.zzbvl.equals(((zzb)paramObject).zzbvl)) {
            return false;
          }
          if (this.zzbvm == null)
          {
            if (((zzb)paramObject).zzbvm != null) {
              return false;
            }
          }
          else if (!this.zzbvm.equals(((zzb)paramObject).zzbvm)) {
            return false;
          }
          if (!zzbur.equals(this.zzbvn, ((zzb)paramObject).zzbvn)) {
            return false;
          }
          if (this.zzbvo == null)
          {
            if (((zzb)paramObject).zzbvo != null) {
              return false;
            }
          }
          else if (!this.zzbvo.equals(((zzb)paramObject).zzbvo)) {
            return false;
          }
          if (this.zzbvp != null) {
            break;
          }
        } while (((zzb)paramObject).zzbvp == null);
        return false;
      } while (this.zzbvp.equals(((zzb)paramObject).zzbvp));
      return false;
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = getClass().getName().hashCode();
      int i;
      int j;
      label33:
      int i1;
      int k;
      if (this.zzbvl == null)
      {
        i = 0;
        if (this.zzbvm != null) {
          break label103;
        }
        j = 0;
        i1 = zzbur.hashCode(this.zzbvn);
        if (this.zzbvo != null) {
          break label114;
        }
        k = 0;
        label51:
        if (this.zzbvp != null) {
          break label125;
        }
      }
      for (;;)
      {
        return (k + ((j + (i + (n + 527) * 31) * 31) * 31 + i1) * 31) * 31 + m;
        i = this.zzbvl.hashCode();
        break;
        label103:
        j = this.zzbvm.hashCode();
        break label33;
        label114:
        k = this.zzbvo.hashCode();
        break label51;
        label125:
        m = this.zzbvp.hashCode();
      }
    }
    
    public zzb zzH(zzbul paramzzbul)
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
          this.zzbvl = Integer.valueOf(paramzzbul.zzacy());
          break;
        case 18: 
          this.zzbvm = paramzzbul.readString();
          break;
        case 26: 
          int j = zzbuw.zzc(paramzzbul, 26);
          if (this.zzbvn == null) {}
          zzauf.zzc[] arrayOfzzc;
          for (i = 0;; i = this.zzbvn.length)
          {
            arrayOfzzc = new zzauf.zzc[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbvn, 0, arrayOfzzc, 0, i);
              j = i;
            }
            while (j < arrayOfzzc.length - 1)
            {
              arrayOfzzc[j] = new zzauf.zzc();
              paramzzbul.zza(arrayOfzzc[j]);
              paramzzbul.zzacu();
              j += 1;
            }
          }
          arrayOfzzc[j] = new zzauf.zzc();
          paramzzbul.zza(arrayOfzzc[j]);
          this.zzbvn = arrayOfzzc;
          break;
        case 32: 
          this.zzbvo = Boolean.valueOf(paramzzbul.zzacA());
          break;
        case 42: 
          if (this.zzbvp == null) {
            this.zzbvp = new zzauf.zzd();
          }
          paramzzbul.zza(this.zzbvp);
        }
      }
    }
    
    public zzb zzMm()
    {
      this.zzbvl = null;
      this.zzbvm = null;
      this.zzbvn = zzauf.zzc.zzMn();
      this.zzbvo = null;
      this.zzbvp = null;
      this.zzcsg = -1;
      return this;
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.zzbvl != null) {
        paramzzbum.zzF(1, this.zzbvl.intValue());
      }
      if (this.zzbvm != null) {
        paramzzbum.zzq(2, this.zzbvm);
      }
      if ((this.zzbvn != null) && (this.zzbvn.length > 0))
      {
        int i = 0;
        while (i < this.zzbvn.length)
        {
          zzauf.zzc localzzc = this.zzbvn[i];
          if (localzzc != null) {
            paramzzbum.zza(3, localzzc);
          }
          i += 1;
        }
      }
      if (this.zzbvo != null) {
        paramzzbum.zzg(4, this.zzbvo.booleanValue());
      }
      if (this.zzbvp != null) {
        paramzzbum.zza(5, this.zzbvp);
      }
      super.zza(paramzzbum);
    }
    
    protected int zzv()
    {
      int i = super.zzv();
      int j = i;
      if (this.zzbvl != null) {
        j = i + zzbum.zzH(1, this.zzbvl.intValue());
      }
      i = j;
      if (this.zzbvm != null) {
        i = j + zzbum.zzr(2, this.zzbvm);
      }
      j = i;
      if (this.zzbvn != null)
      {
        j = i;
        if (this.zzbvn.length > 0)
        {
          j = 0;
          while (j < this.zzbvn.length)
          {
            zzauf.zzc localzzc = this.zzbvn[j];
            int k = i;
            if (localzzc != null) {
              k = i + zzbum.zzc(3, localzzc);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (this.zzbvo != null) {
        i = j + zzbum.zzh(4, this.zzbvo.booleanValue());
      }
      j = i;
      if (this.zzbvp != null) {
        j = i + zzbum.zzc(5, this.zzbvp);
      }
      return j;
    }
  }
  
  public static final class zzc
    extends zzbut
  {
    private static volatile zzc[] zzbvq;
    public zzauf.zzf zzbvr;
    public zzauf.zzd zzbvs;
    public Boolean zzbvt;
    public String zzbvu;
    
    public zzc()
    {
      zzMo();
    }
    
    public static zzc[] zzMn()
    {
      if (zzbvq == null) {}
      synchronized (zzbur.zzcsf)
      {
        if (zzbvq == null) {
          zzbvq = new zzc[0];
        }
        return zzbvq;
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
          if (this.zzbvr == null)
          {
            if (((zzc)paramObject).zzbvr != null) {
              return false;
            }
          }
          else if (!this.zzbvr.equals(((zzc)paramObject).zzbvr)) {
            return false;
          }
          if (this.zzbvs == null)
          {
            if (((zzc)paramObject).zzbvs != null) {
              return false;
            }
          }
          else if (!this.zzbvs.equals(((zzc)paramObject).zzbvs)) {
            return false;
          }
          if (this.zzbvt == null)
          {
            if (((zzc)paramObject).zzbvt != null) {
              return false;
            }
          }
          else if (!this.zzbvt.equals(((zzc)paramObject).zzbvt)) {
            return false;
          }
          if (this.zzbvu != null) {
            break;
          }
        } while (((zzc)paramObject).zzbvu == null);
        return false;
      } while (this.zzbvu.equals(((zzc)paramObject).zzbvu));
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
      if (this.zzbvr == null)
      {
        i = 0;
        if (this.zzbvs != null) {
          break label88;
        }
        j = 0;
        if (this.zzbvt != null) {
          break label99;
        }
        k = 0;
        label42:
        if (this.zzbvu != null) {
          break label110;
        }
      }
      for (;;)
      {
        return (k + (j + (i + (n + 527) * 31) * 31) * 31) * 31 + m;
        i = this.zzbvr.hashCode();
        break;
        label88:
        j = this.zzbvs.hashCode();
        break label33;
        label99:
        k = this.zzbvt.hashCode();
        break label42;
        label110:
        m = this.zzbvu.hashCode();
      }
    }
    
    public zzc zzI(zzbul paramzzbul)
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
          if (this.zzbvr == null) {
            this.zzbvr = new zzauf.zzf();
          }
          paramzzbul.zza(this.zzbvr);
          break;
        case 18: 
          if (this.zzbvs == null) {
            this.zzbvs = new zzauf.zzd();
          }
          paramzzbul.zza(this.zzbvs);
          break;
        case 24: 
          this.zzbvt = Boolean.valueOf(paramzzbul.zzacA());
          break;
        case 34: 
          this.zzbvu = paramzzbul.readString();
        }
      }
    }
    
    public zzc zzMo()
    {
      this.zzbvr = null;
      this.zzbvs = null;
      this.zzbvt = null;
      this.zzbvu = null;
      this.zzcsg = -1;
      return this;
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.zzbvr != null) {
        paramzzbum.zza(1, this.zzbvr);
      }
      if (this.zzbvs != null) {
        paramzzbum.zza(2, this.zzbvs);
      }
      if (this.zzbvt != null) {
        paramzzbum.zzg(3, this.zzbvt.booleanValue());
      }
      if (this.zzbvu != null) {
        paramzzbum.zzq(4, this.zzbvu);
      }
      super.zza(paramzzbum);
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.zzbvr != null) {
        i = j + zzbum.zzc(1, this.zzbvr);
      }
      j = i;
      if (this.zzbvs != null) {
        j = i + zzbum.zzc(2, this.zzbvs);
      }
      i = j;
      if (this.zzbvt != null) {
        i = j + zzbum.zzh(3, this.zzbvt.booleanValue());
      }
      j = i;
      if (this.zzbvu != null) {
        j = i + zzbum.zzr(4, this.zzbvu);
      }
      return j;
    }
  }
  
  public static final class zzd
    extends zzbut
  {
    public Integer zzbvv;
    public Boolean zzbvw;
    public String zzbvx;
    public String zzbvy;
    public String zzbvz;
    
    public zzd()
    {
      zzMp();
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        do
        {
          return true;
          if (!(paramObject instanceof zzd)) {
            return false;
          }
          paramObject = (zzd)paramObject;
          if (this.zzbvv == null)
          {
            if (((zzd)paramObject).zzbvv != null) {
              return false;
            }
          }
          else if (!this.zzbvv.equals(((zzd)paramObject).zzbvv)) {
            return false;
          }
          if (this.zzbvw == null)
          {
            if (((zzd)paramObject).zzbvw != null) {
              return false;
            }
          }
          else if (!this.zzbvw.equals(((zzd)paramObject).zzbvw)) {
            return false;
          }
          if (this.zzbvx == null)
          {
            if (((zzd)paramObject).zzbvx != null) {
              return false;
            }
          }
          else if (!this.zzbvx.equals(((zzd)paramObject).zzbvx)) {
            return false;
          }
          if (this.zzbvy == null)
          {
            if (((zzd)paramObject).zzbvy != null) {
              return false;
            }
          }
          else if (!this.zzbvy.equals(((zzd)paramObject).zzbvy)) {
            return false;
          }
          if (this.zzbvz != null) {
            break;
          }
        } while (((zzd)paramObject).zzbvz == null);
        return false;
      } while (this.zzbvz.equals(((zzd)paramObject).zzbvz));
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
      if (this.zzbvv == null)
      {
        i = 0;
        if (this.zzbvw != null) {
          break label104;
        }
        j = 0;
        if (this.zzbvx != null) {
          break label115;
        }
        k = 0;
        if (this.zzbvy != null) {
          break label126;
        }
        m = 0;
        label52:
        if (this.zzbvz != null) {
          break label138;
        }
      }
      for (;;)
      {
        return (m + (k + (j + (i + (i1 + 527) * 31) * 31) * 31) * 31) * 31 + n;
        i = this.zzbvv.intValue();
        break;
        label104:
        j = this.zzbvw.hashCode();
        break label33;
        label115:
        k = this.zzbvx.hashCode();
        break label42;
        label126:
        m = this.zzbvy.hashCode();
        break label52;
        label138:
        n = this.zzbvz.hashCode();
      }
    }
    
    public zzd zzJ(zzbul paramzzbul)
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
            this.zzbvv = Integer.valueOf(i);
          }
          break;
        case 16: 
          this.zzbvw = Boolean.valueOf(paramzzbul.zzacA());
          break;
        case 26: 
          this.zzbvx = paramzzbul.readString();
          break;
        case 34: 
          this.zzbvy = paramzzbul.readString();
          break;
        case 42: 
          this.zzbvz = paramzzbul.readString();
        }
      }
    }
    
    public zzd zzMp()
    {
      this.zzbvw = null;
      this.zzbvx = null;
      this.zzbvy = null;
      this.zzbvz = null;
      this.zzcsg = -1;
      return this;
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.zzbvv != null) {
        paramzzbum.zzF(1, this.zzbvv.intValue());
      }
      if (this.zzbvw != null) {
        paramzzbum.zzg(2, this.zzbvw.booleanValue());
      }
      if (this.zzbvx != null) {
        paramzzbum.zzq(3, this.zzbvx);
      }
      if (this.zzbvy != null) {
        paramzzbum.zzq(4, this.zzbvy);
      }
      if (this.zzbvz != null) {
        paramzzbum.zzq(5, this.zzbvz);
      }
      super.zza(paramzzbum);
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.zzbvv != null) {
        i = j + zzbum.zzH(1, this.zzbvv.intValue());
      }
      j = i;
      if (this.zzbvw != null) {
        j = i + zzbum.zzh(2, this.zzbvw.booleanValue());
      }
      i = j;
      if (this.zzbvx != null) {
        i = j + zzbum.zzr(3, this.zzbvx);
      }
      j = i;
      if (this.zzbvy != null) {
        j = i + zzbum.zzr(4, this.zzbvy);
      }
      i = j;
      if (this.zzbvz != null) {
        i = j + zzbum.zzr(5, this.zzbvz);
      }
      return i;
    }
  }
  
  public static final class zze
    extends zzbut
  {
    private static volatile zze[] zzbvA;
    public String zzbvB;
    public zzauf.zzc zzbvC;
    public Integer zzbvl;
    
    public zze()
    {
      zzMr();
    }
    
    public static zze[] zzMq()
    {
      if (zzbvA == null) {}
      synchronized (zzbur.zzcsf)
      {
        if (zzbvA == null) {
          zzbvA = new zze[0];
        }
        return zzbvA;
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
          if (this.zzbvl == null)
          {
            if (((zze)paramObject).zzbvl != null) {
              return false;
            }
          }
          else if (!this.zzbvl.equals(((zze)paramObject).zzbvl)) {
            return false;
          }
          if (this.zzbvB == null)
          {
            if (((zze)paramObject).zzbvB != null) {
              return false;
            }
          }
          else if (!this.zzbvB.equals(((zze)paramObject).zzbvB)) {
            return false;
          }
          if (this.zzbvC != null) {
            break;
          }
        } while (((zze)paramObject).zzbvC == null);
        return false;
      } while (this.zzbvC.equals(((zze)paramObject).zzbvC));
      return false;
    }
    
    public int hashCode()
    {
      int k = 0;
      int m = getClass().getName().hashCode();
      int i;
      int j;
      if (this.zzbvl == null)
      {
        i = 0;
        if (this.zzbvB != null) {
          break label72;
        }
        j = 0;
        label32:
        if (this.zzbvC != null) {
          break label83;
        }
      }
      for (;;)
      {
        return (j + (i + (m + 527) * 31) * 31) * 31 + k;
        i = this.zzbvl.hashCode();
        break;
        label72:
        j = this.zzbvB.hashCode();
        break label32;
        label83:
        k = this.zzbvC.hashCode();
      }
    }
    
    public zze zzK(zzbul paramzzbul)
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
          this.zzbvl = Integer.valueOf(paramzzbul.zzacy());
          break;
        case 18: 
          this.zzbvB = paramzzbul.readString();
          break;
        case 26: 
          if (this.zzbvC == null) {
            this.zzbvC = new zzauf.zzc();
          }
          paramzzbul.zza(this.zzbvC);
        }
      }
    }
    
    public zze zzMr()
    {
      this.zzbvl = null;
      this.zzbvB = null;
      this.zzbvC = null;
      this.zzcsg = -1;
      return this;
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.zzbvl != null) {
        paramzzbum.zzF(1, this.zzbvl.intValue());
      }
      if (this.zzbvB != null) {
        paramzzbum.zzq(2, this.zzbvB);
      }
      if (this.zzbvC != null) {
        paramzzbum.zza(3, this.zzbvC);
      }
      super.zza(paramzzbum);
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.zzbvl != null) {
        i = j + zzbum.zzH(1, this.zzbvl.intValue());
      }
      j = i;
      if (this.zzbvB != null) {
        j = i + zzbum.zzr(2, this.zzbvB);
      }
      i = j;
      if (this.zzbvC != null) {
        i = j + zzbum.zzc(3, this.zzbvC);
      }
      return i;
    }
  }
  
  public static final class zzf
    extends zzbut
  {
    public Integer zzbvD;
    public String zzbvE;
    public Boolean zzbvF;
    public String[] zzbvG;
    
    public zzf()
    {
      zzMs();
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
        if (this.zzbvD == null)
        {
          if (((zzf)paramObject).zzbvD != null) {
            return false;
          }
        }
        else if (!this.zzbvD.equals(((zzf)paramObject).zzbvD)) {
          return false;
        }
        if (this.zzbvE == null)
        {
          if (((zzf)paramObject).zzbvE != null) {
            return false;
          }
        }
        else if (!this.zzbvE.equals(((zzf)paramObject).zzbvE)) {
          return false;
        }
        if (this.zzbvF == null)
        {
          if (((zzf)paramObject).zzbvF != null) {
            return false;
          }
        }
        else if (!this.zzbvF.equals(((zzf)paramObject).zzbvF)) {
          return false;
        }
      } while (zzbur.equals(this.zzbvG, ((zzf)paramObject).zzbvG));
      return false;
    }
    
    public int hashCode()
    {
      int k = 0;
      int m = getClass().getName().hashCode();
      int i;
      int j;
      if (this.zzbvD == null)
      {
        i = 0;
        if (this.zzbvE != null) {
          break label83;
        }
        j = 0;
        label32:
        if (this.zzbvF != null) {
          break label94;
        }
      }
      for (;;)
      {
        return ((j + (i + (m + 527) * 31) * 31) * 31 + k) * 31 + zzbur.hashCode(this.zzbvG);
        i = this.zzbvD.intValue();
        break;
        label83:
        j = this.zzbvE.hashCode();
        break label32;
        label94:
        k = this.zzbvF.hashCode();
      }
    }
    
    public zzf zzL(zzbul paramzzbul)
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
          case 5: 
          case 6: 
            this.zzbvD = Integer.valueOf(i);
          }
          break;
        case 18: 
          this.zzbvE = paramzzbul.readString();
          break;
        case 24: 
          this.zzbvF = Boolean.valueOf(paramzzbul.zzacA());
          break;
        case 34: 
          int j = zzbuw.zzc(paramzzbul, 34);
          if (this.zzbvG == null) {}
          String[] arrayOfString;
          for (i = 0;; i = this.zzbvG.length)
          {
            arrayOfString = new String[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbvG, 0, arrayOfString, 0, i);
              j = i;
            }
            while (j < arrayOfString.length - 1)
            {
              arrayOfString[j] = paramzzbul.readString();
              paramzzbul.zzacu();
              j += 1;
            }
          }
          arrayOfString[j] = paramzzbul.readString();
          this.zzbvG = arrayOfString;
        }
      }
    }
    
    public zzf zzMs()
    {
      this.zzbvE = null;
      this.zzbvF = null;
      this.zzbvG = zzbuw.zzcsn;
      this.zzcsg = -1;
      return this;
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.zzbvD != null) {
        paramzzbum.zzF(1, this.zzbvD.intValue());
      }
      if (this.zzbvE != null) {
        paramzzbum.zzq(2, this.zzbvE);
      }
      if (this.zzbvF != null) {
        paramzzbum.zzg(3, this.zzbvF.booleanValue());
      }
      if ((this.zzbvG != null) && (this.zzbvG.length > 0))
      {
        int i = 0;
        while (i < this.zzbvG.length)
        {
          String str = this.zzbvG[i];
          if (str != null) {
            paramzzbum.zzq(4, str);
          }
          i += 1;
        }
      }
      super.zza(paramzzbum);
    }
    
    protected int zzv()
    {
      int n = 0;
      int j = super.zzv();
      int i = j;
      if (this.zzbvD != null) {
        i = j + zzbum.zzH(1, this.zzbvD.intValue());
      }
      j = i;
      if (this.zzbvE != null) {
        j = i + zzbum.zzr(2, this.zzbvE);
      }
      i = j;
      if (this.zzbvF != null) {
        i = j + zzbum.zzh(3, this.zzbvF.booleanValue());
      }
      j = i;
      if (this.zzbvG != null)
      {
        j = i;
        if (this.zzbvG.length > 0)
        {
          int k = 0;
          int m = 0;
          j = n;
          while (j < this.zzbvG.length)
          {
            String str = this.zzbvG[j];
            int i1 = k;
            n = m;
            if (str != null)
            {
              n = m + 1;
              i1 = k + zzbum.zzkc(str);
            }
            j += 1;
            k = i1;
            m = n;
          }
          j = i + k + m * 1;
        }
      }
      return j;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzauf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */