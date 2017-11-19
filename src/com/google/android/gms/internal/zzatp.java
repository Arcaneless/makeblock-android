package com.google.android.gms.internal;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzatp
{
  private static volatile zzatp zzbtc;
  private final Context mContext;
  private final boolean zzacO;
  private long zzbtA;
  private FileLock zzbtB;
  private FileChannel zzbtC;
  private List<Long> zzbtD;
  private int zzbtE;
  private int zzbtF;
  private long zzbtG;
  private final zzast zzbtd;
  private final zzatl zzbte;
  private final zzati zzbtf;
  private final zzato zzbtg;
  private final zzaty zzbth;
  private final zzatn zzbti;
  private final AppMeasurement zzbtj;
  private final FirebaseAnalytics zzbtk;
  private final zzaue zzbtl;
  private final zzasu zzbtm;
  private final zzatg zzbtn;
  private final zzatj zzbto;
  private final zzatv zzbtp;
  private final zzatw zzbtq;
  private final zzasw zzbtr;
  private final zzatu zzbts;
  private final zzatf zzbtt;
  private final zzatk zzbtu;
  private final zzaua zzbtv;
  private final zzass zzbtw;
  private final zzaso zzbtx;
  private boolean zzbty;
  private Boolean zzbtz;
  private final zze zzuI;
  
  zzatp(zzatt paramzzatt)
  {
    zzac.zzw(paramzzatt);
    this.mContext = paramzzatt.mContext;
    this.zzbtG = -1L;
    this.zzuI = paramzzatt.zzn(this);
    this.zzbtd = paramzzatt.zza(this);
    Object localObject = paramzzatt.zzb(this);
    ((zzatl)localObject).initialize();
    this.zzbte = ((zzatl)localObject);
    localObject = paramzzatt.zzc(this);
    ((zzati)localObject).initialize();
    this.zzbtf = ((zzati)localObject);
    zzJt().zzLe().zzj("App measurement is starting up, version", Long.valueOf(zzJv().zzJD()));
    zzJt().zzLe().log("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
    zzJt().zzLf().log("Debug-level message logging enabled");
    zzJt().zzLf().zzj("AppMeasurement singleton hash", Integer.valueOf(System.identityHashCode(this)));
    localObject = paramzzatt.zzj(this);
    ((zzaue)localObject).initialize();
    this.zzbtl = ((zzaue)localObject);
    localObject = paramzzatt.zzq(this);
    ((zzasw)localObject).initialize();
    this.zzbtr = ((zzasw)localObject);
    localObject = paramzzatt.zzr(this);
    ((zzatf)localObject).initialize();
    this.zzbtt = ((zzatf)localObject);
    zzJv().zzKk();
    localObject = ((zzatf)localObject).zzjI();
    if (zzJp().zzgh((String)localObject))
    {
      zzJt().zzLe().log("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
      localObject = paramzzatt.zzk(this);
      ((zzasu)localObject).initialize();
      this.zzbtm = ((zzasu)localObject);
      localObject = paramzzatt.zzl(this);
      ((zzatg)localObject).initialize();
      this.zzbtn = ((zzatg)localObject);
      localObject = paramzzatt.zzu(this);
      ((zzass)localObject).initialize();
      this.zzbtw = ((zzass)localObject);
      this.zzbtx = paramzzatt.zzv(this);
      localObject = paramzzatt.zzm(this);
      ((zzatj)localObject).initialize();
      this.zzbto = ((zzatj)localObject);
      localObject = paramzzatt.zzo(this);
      ((zzatv)localObject).initialize();
      this.zzbtp = ((zzatv)localObject);
      localObject = paramzzatt.zzp(this);
      ((zzatw)localObject).initialize();
      this.zzbtq = ((zzatw)localObject);
      localObject = paramzzatt.zzi(this);
      ((zzatu)localObject).initialize();
      this.zzbts = ((zzatu)localObject);
      localObject = paramzzatt.zzt(this);
      ((zzaua)localObject).initialize();
      this.zzbtv = ((zzaua)localObject);
      this.zzbtu = paramzzatt.zzs(this);
      this.zzbtj = paramzzatt.zzh(this);
      this.zzbtk = paramzzatt.zzg(this);
      localObject = paramzzatt.zze(this);
      ((zzaty)localObject).initialize();
      this.zzbth = ((zzaty)localObject);
      localObject = paramzzatt.zzf(this);
      ((zzatn)localObject).initialize();
      this.zzbti = ((zzatn)localObject);
      paramzzatt = paramzzatt.zzd(this);
      paramzzatt.initialize();
      this.zzbtg = paramzzatt;
      if (this.zzbtE != this.zzbtF) {
        zzJt().zzLa().zze("Not all components initialized", Integer.valueOf(this.zzbtE), Integer.valueOf(this.zzbtF));
      }
      this.zzacO = true;
      this.zzbtd.zzKk();
      if (!(this.mContext.getApplicationContext() instanceof Application)) {
        break label580;
      }
      if (Build.VERSION.SDK_INT < 14) {
        break label564;
      }
      zzJi().zzLQ();
    }
    for (;;)
    {
      this.zzbtg.zzm(new Runnable()
      {
        public void run()
        {
          zzatp.this.start();
        }
      });
      return;
      zzati.zza localzza = zzJt().zzLe();
      localObject = String.valueOf(localObject);
      if (((String)localObject).length() != 0) {}
      for (localObject = "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat((String)localObject);; localObject = new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app "))
      {
        localzza.log((String)localObject);
        break;
      }
      label564:
      zzJt().zzLf().log("Not tracking deep linking pre-ICS");
      continue;
      label580:
      zzJt().zzLc().log("Application context is not an Application");
    }
  }
  
  private boolean zzLH()
  {
    zzmq();
    zznA();
    return (zzJo().zzKM()) || (!TextUtils.isEmpty(zzJo().zzKG()));
  }
  
  @WorkerThread
  private void zzLI()
  {
    zzmq();
    zznA();
    if (!zzLM()) {
      return;
    }
    if ((!zzLt()) || (!zzLH()))
    {
      zzLz().unregister();
      zzLA().cancel();
      return;
    }
    long l2 = zzLJ();
    if (l2 == 0L)
    {
      zzLz().unregister();
      zzLA().cancel();
      return;
    }
    if (!zzLy().zzpA())
    {
      zzLz().zzpx();
      zzLA().cancel();
      return;
    }
    long l3 = zzJu().zzbsi.get();
    long l4 = zzJv().zzKw();
    long l1 = l2;
    if (!zzJp().zzf(l3, l4)) {
      l1 = Math.max(l2, l3 + l4);
    }
    zzLz().unregister();
    l2 = l1 - zznq().currentTimeMillis();
    l1 = l2;
    if (l2 <= 0L)
    {
      l1 = zzJv().zzKz();
      zzJu().zzbsg.set(zznq().currentTimeMillis());
    }
    zzJt().zzLg().zzj("Upload scheduled in approximately ms", Long.valueOf(l1));
    zzLA().zzx(l1);
  }
  
  private long zzLJ()
  {
    long l3 = zznq().currentTimeMillis();
    long l1 = zzJv().zzKC();
    long l2;
    label54:
    long l6;
    long l5;
    long l4;
    if ((zzJo().zzKN()) || (zzJo().zzKH()))
    {
      i = 1;
      if (i == 0) {
        break label115;
      }
      l2 = zzJv().zzKy();
      l6 = zzJu().zzbsg.get();
      l5 = zzJu().zzbsh.get();
      l4 = Math.max(zzJo().zzKK(), zzJo().zzKL());
      if (l4 != 0L) {
        break label127;
      }
      l2 = 0L;
    }
    label115:
    label127:
    do
    {
      do
      {
        return l2;
        i = 0;
        break;
        l2 = zzJv().zzKx();
        break label54;
        l4 = l3 - Math.abs(l4 - l3);
        l6 = Math.abs(l6 - l3);
        l5 = l3 - Math.abs(l5 - l3);
        l6 = Math.max(l3 - l6, l5);
        l3 = l4 + l1;
        l1 = l3;
        if (i != 0)
        {
          l1 = l3;
          if (l6 > 0L) {
            l1 = Math.min(l4, l6) + l2;
          }
        }
        if (!zzJp().zzf(l6, l2)) {
          l1 = l6 + l2;
        }
        l2 = l1;
      } while (l5 == 0L);
      l2 = l1;
    } while (l5 < l4);
    int i = 0;
    for (;;)
    {
      if (i >= zzJv().zzKE()) {
        break label295;
      }
      l1 += (1 << i) * zzJv().zzKD();
      l2 = l1;
      if (l1 > l5) {
        break;
      }
      i += 1;
    }
    label295:
    return 0L;
  }
  
  private void zza(zzatr paramzzatr)
  {
    if (paramzzatr == null) {
      throw new IllegalStateException("Component not created");
    }
  }
  
  private void zza(zzats paramzzats)
  {
    if (paramzzats == null) {
      throw new IllegalStateException("Component not created");
    }
    if (!paramzzats.isInitialized()) {
      throw new IllegalStateException("Component not initialized");
    }
  }
  
  private boolean zza(zzasx paramzzasx)
  {
    if (paramzzasx.zzbqI == null) {}
    Object localObject;
    boolean bool;
    do
    {
      return false;
      localObject = paramzzasx.zzbqI.iterator();
      while (((Iterator)localObject).hasNext()) {
        if ("_r".equals((String)((Iterator)localObject).next())) {
          return true;
        }
      }
      bool = zzJq().zzY(paramzzasx.zzVQ, paramzzasx.mName);
      localObject = zzJo().zza(zzLE(), paramzzasx.zzVQ, false, false, false, false, false);
    } while ((!bool) || (((zzasu.zza)localObject).zzbqz >= zzJv().zzfp(paramzzasx.zzVQ)));
    return true;
  }
  
  private zzauh.zza[] zza(String paramString, zzauh.zzg[] paramArrayOfzzg, zzauh.zzb[] paramArrayOfzzb)
  {
    zzac.zzdv(paramString);
    return zzJh().zza(paramString, paramArrayOfzzb, paramArrayOfzzg);
  }
  
  public static zzatp zzbu(Context paramContext)
  {
    zzac.zzw(paramContext);
    zzac.zzw(paramContext.getApplicationContext());
    if (zzbtc == null) {}
    try
    {
      if (zzbtc == null) {
        zzbtc = new zzatt(paramContext).zzLP();
      }
      return zzbtc;
    }
    finally {}
  }
  
  @WorkerThread
  private void zzf(zzasq paramzzasq)
  {
    zzmq();
    zznA();
    zzac.zzw(paramzzasq);
    zzac.zzdv(paramzzasq.packageName);
    zzasp localzzasp2 = zzJo().zzfy(paramzzasq.packageName);
    String str = zzJu().zzfL(paramzzasq.packageName);
    int i = 0;
    zzasp localzzasp1;
    if (localzzasp2 == null)
    {
      localzzasp1 = new zzasp(this, paramzzasq.packageName);
      localzzasp1.zzfh(zzJu().zzLj());
      localzzasp1.zzfj(str);
      i = 1;
    }
    for (;;)
    {
      int j = i;
      if (!TextUtils.isEmpty(paramzzasq.zzbqf))
      {
        j = i;
        if (!paramzzasq.zzbqf.equals(localzzasp1.getGmpAppId()))
        {
          localzzasp1.zzfi(paramzzasq.zzbqf);
          j = 1;
        }
      }
      i = j;
      if (!TextUtils.isEmpty(paramzzasq.zzbqn))
      {
        i = j;
        if (!paramzzasq.zzbqn.equals(localzzasp1.zzJy()))
        {
          localzzasp1.zzfk(paramzzasq.zzbqn);
          i = 1;
        }
      }
      j = i;
      if (paramzzasq.zzbqh != 0L)
      {
        j = i;
        if (paramzzasq.zzbqh != localzzasp1.zzJD())
        {
          localzzasp1.zzaa(paramzzasq.zzbqh);
          j = 1;
        }
      }
      i = j;
      if (!TextUtils.isEmpty(paramzzasq.zzbhg))
      {
        i = j;
        if (!paramzzasq.zzbhg.equals(localzzasp1.zzmy()))
        {
          localzzasp1.setAppVersion(paramzzasq.zzbhg);
          i = 1;
        }
      }
      if (paramzzasq.zzbqm != localzzasp1.zzJB())
      {
        localzzasp1.zzZ(paramzzasq.zzbqm);
        i = 1;
      }
      j = i;
      if (!TextUtils.isEmpty(paramzzasq.zzbqg))
      {
        j = i;
        if (!paramzzasq.zzbqg.equals(localzzasp1.zzJC()))
        {
          localzzasp1.zzfl(paramzzasq.zzbqg);
          j = 1;
        }
      }
      i = j;
      if (paramzzasq.zzbqi != localzzasp1.zzJE())
      {
        localzzasp1.zzab(paramzzasq.zzbqi);
        i = 1;
      }
      if (paramzzasq.zzbqk != localzzasp1.zzJF())
      {
        localzzasp1.setMeasurementEnabled(paramzzasq.zzbqk);
        i = 1;
      }
      j = i;
      if (!TextUtils.isEmpty(paramzzasq.zzbqj))
      {
        j = i;
        if (!paramzzasq.zzbqj.equals(localzzasp1.zzJQ()))
        {
          localzzasp1.zzfm(paramzzasq.zzbqj);
          j = 1;
        }
      }
      if (j != 0) {
        zzJo().zza(localzzasp1);
      }
      return;
      localzzasp1 = localzzasp2;
      if (!str.equals(localzzasp2.zzJx()))
      {
        localzzasp2.zzfj(str);
        localzzasp2.zzfh(zzJu().zzLj());
        i = 1;
        localzzasp1 = localzzasp2;
      }
    }
  }
  
  private boolean zzj(String paramString, long paramLong)
  {
    zzJo().beginTransaction();
    for (;;)
    {
      zzauh.zze localzze;
      int i;
      int k;
      int n;
      int i1;
      int m;
      Object localObject2;
      zzauh.zzc[] arrayOfzzc;
      long l;
      try
      {
        zza localzza = new zza(null);
        zzJo().zza(paramString, paramLong, this.zzbtG, localzza);
        if (localzza.isEmpty()) {
          break label1738;
        }
        bool1 = false;
        localzze = localzza.zzbtI;
        localzze.zzbwd = new zzauh.zzb[localzza.zztf.size()];
        i = 0;
        k = 0;
        if (k < localzza.zztf.size())
        {
          if (zzJq().zzX(localzza.zzbtI.zzaR, ((zzauh.zzb)localzza.zztf.get(k)).name))
          {
            zzJt().zzLc().zze("Dropping blacklisted raw event. appId", zzati.zzfI(paramString), ((zzauh.zzb)localzza.zztf.get(k)).name);
            if (zzJp().zzgj(localzza.zzbtI.zzaR)) {
              break label1775;
            }
            if (!zzJp().zzgk(localzza.zzbtI.zzaR)) {
              break label1790;
            }
            break label1775;
            if ((j != 0) || ("_err".equals(((zzauh.zzb)localzza.zztf.get(k)).name))) {
              break label1772;
            }
            zzJp().zza(11, "_ev", ((zzauh.zzb)localzza.zztf.get(k)).name, 0);
            break label1781;
          }
          if (!zzJq().zzY(localzza.zzbtI.zzaR, ((zzauh.zzb)localzza.zztf.get(k)).name)) {
            break label1769;
          }
          n = 0;
          j = 0;
          if (((zzauh.zzb)localzza.zztf.get(k)).zzbvV == null) {
            ((zzauh.zzb)localzza.zztf.get(k)).zzbvV = new zzauh.zzc[0];
          }
          localObject1 = ((zzauh.zzb)localzza.zztf.get(k)).zzbvV;
          i1 = localObject1.length;
          m = 0;
          if (m < i1)
          {
            localObject2 = localObject1[m];
            if ("_c".equals(((zzauh.zzc)localObject2).name))
            {
              ((zzauh.zzc)localObject2).zzbvZ = Long.valueOf(1L);
              n = 1;
              break label1796;
            }
            if (!"_r".equals(((zzauh.zzc)localObject2).name)) {
              break label1766;
            }
            ((zzauh.zzc)localObject2).zzbvZ = Long.valueOf(1L);
            j = 1;
            break label1796;
          }
          if (n == 0)
          {
            zzJt().zzLg().zzj("Marking event as conversion", ((zzauh.zzb)localzza.zztf.get(k)).name);
            localObject1 = (zzauh.zzc[])Arrays.copyOf(((zzauh.zzb)localzza.zztf.get(k)).zzbvV, ((zzauh.zzb)localzza.zztf.get(k)).zzbvV.length + 1);
            localObject2 = new zzauh.zzc();
            ((zzauh.zzc)localObject2).name = "_c";
            ((zzauh.zzc)localObject2).zzbvZ = Long.valueOf(1L);
            localObject1[(localObject1.length - 1)] = localObject2;
            ((zzauh.zzb)localzza.zztf.get(k)).zzbvV = ((zzauh.zzc[])localObject1);
          }
          if (j == 0)
          {
            zzJt().zzLg().zzj("Marking event as real-time", ((zzauh.zzb)localzza.zztf.get(k)).name);
            localObject1 = (zzauh.zzc[])Arrays.copyOf(((zzauh.zzb)localzza.zztf.get(k)).zzbvV, ((zzauh.zzb)localzza.zztf.get(k)).zzbvV.length + 1);
            localObject2 = new zzauh.zzc();
            ((zzauh.zzc)localObject2).name = "_r";
            ((zzauh.zzc)localObject2).zzbvZ = Long.valueOf(1L);
            localObject1[(localObject1.length - 1)] = localObject2;
            ((zzauh.zzb)localzza.zztf.get(k)).zzbvV = ((zzauh.zzc[])localObject1);
          }
          bool2 = true;
          boolean bool3 = zzaue.zzfW(((zzauh.zzb)localzza.zztf.get(k)).name);
          if (zzJo().zza(zzLE(), localzza.zzbtI.zzaR, false, false, false, false, true).zzbqz > zzJv().zzfp(localzza.zzbtI.zzaR))
          {
            localObject1 = (zzauh.zzb)localzza.zztf.get(k);
            j = 0;
            if (j >= ((zzauh.zzb)localObject1).zzbvV.length) {
              break label1805;
            }
            if (!"_r".equals(localObject1.zzbvV[j].name)) {
              break label1821;
            }
            localObject2 = new zzauh.zzc[((zzauh.zzb)localObject1).zzbvV.length - 1];
            if (j > 0) {
              System.arraycopy(((zzauh.zzb)localObject1).zzbvV, 0, localObject2, 0, j);
            }
            if (j < localObject2.length) {
              System.arraycopy(((zzauh.zzb)localObject1).zzbvV, j + 1, localObject2, j, localObject2.length - j);
            }
            ((zzauh.zzb)localObject1).zzbvV = ((zzauh.zzc[])localObject2);
            break label1805;
          }
          if ((!bool3) || (zzJo().zza(zzLE(), localzza.zzbtI.zzaR, false, false, true, false, false).zzbqx <= zzJv().zzfo(localzza.zzbtI.zzaR))) {
            break label1877;
          }
          zzJt().zzLc().zzj("Too many conversions. Not logging as conversion. appId", zzati.zzfI(paramString));
          localObject3 = (zzauh.zzb)localzza.zztf.get(k);
          j = 0;
          localObject1 = null;
          arrayOfzzc = ((zzauh.zzb)localObject3).zzbvV;
          n = arrayOfzzc.length;
          m = 0;
          if (m < n)
          {
            localObject2 = arrayOfzzc[m];
            if ("_c".equals(((zzauh.zzc)localObject2).name))
            {
              localObject1 = localObject2;
              break label1812;
            }
            if (!"_err".equals(((zzauh.zzc)localObject2).name)) {
              break label1763;
            }
            j = 1;
            break label1812;
          }
          if ((j != 0) && (localObject1 != null))
          {
            localObject2 = new zzauh.zzc[((zzauh.zzb)localObject3).zzbvV.length - 1];
            j = 0;
            arrayOfzzc = ((zzauh.zzb)localObject3).zzbvV;
            i1 = arrayOfzzc.length;
            m = 0;
            break label1830;
            ((zzauh.zzb)localObject3).zzbvV = ((zzauh.zzc[])localObject2);
            bool1 = bool2;
            localzze.zzbwd[i] = ((zzauh.zzb)localzza.zztf.get(k));
            i += 1;
            break label1781;
          }
          if (localObject1 != null)
          {
            ((zzauh.zzc)localObject1).name = "_err";
            ((zzauh.zzc)localObject1).zzbvZ = Long.valueOf(10L);
            bool1 = bool2;
            continue;
          }
          zzJt().zzLa().zzj("Did not find conversion parameter. appId", zzati.zzfI(paramString));
          break label1877;
        }
        if (i < localzza.zztf.size()) {
          localzze.zzbwd = ((zzauh.zzb[])Arrays.copyOf(localzze.zzbwd, i));
        }
        localzze.zzbww = zza(localzza.zzbtI.zzaR, localzza.zzbtI.zzbwe, localzze.zzbwd);
        localzze.zzbwg = Long.valueOf(Long.MAX_VALUE);
        localzze.zzbwh = Long.valueOf(Long.MIN_VALUE);
        i = 0;
        if (i < localzze.zzbwd.length)
        {
          localObject1 = localzze.zzbwd[i];
          if (((zzauh.zzb)localObject1).zzbvW.longValue() < localzze.zzbwg.longValue()) {
            localzze.zzbwg = ((zzauh.zzb)localObject1).zzbvW;
          }
          if (((zzauh.zzb)localObject1).zzbvW.longValue() <= localzze.zzbwh.longValue()) {
            break label1884;
          }
          localzze.zzbwh = ((zzauh.zzb)localObject1).zzbvW;
          break label1884;
        }
        localObject2 = localzza.zzbtI.zzaR;
        Object localObject3 = zzJo().zzfy((String)localObject2);
        if (localObject3 == null)
        {
          zzJt().zzLa().zzj("Bundling raw events w/o app info. appId", zzati.zzfI(paramString));
          if (localzze.zzbwd.length > 0)
          {
            zzJv().zzKk();
            localObject1 = zzJq().zzfO(localzza.zzbtI.zzaR);
            if ((localObject1 != null) && (((zzaug.zzb)localObject1).zzbvK != null)) {
              break label1725;
            }
            zzJt().zzLc().zzj("Did not find measurement config or missing version info. appId", zzati.zzfI(paramString));
            zzJo().zza(localzze, bool1);
          }
          zzJo().zzG(localzza.zzbtJ);
          zzJo().zzfF((String)localObject2);
          zzJo().setTransactionSuccessful();
          i = localzze.zzbwd.length;
          if (i <= 0) {
            break label1893;
          }
          bool1 = true;
          return bool1;
        }
        if (localzze.zzbwd.length <= 0) {
          continue;
        }
        paramLong = ((zzasp)localObject3).zzJA();
        if (paramLong != 0L)
        {
          localObject1 = Long.valueOf(paramLong);
          localzze.zzbwj = ((Long)localObject1);
          l = ((zzasp)localObject3).zzJz();
          if (l != 0L) {
            break label1754;
          }
          if (paramLong == 0L) {
            break label1719;
          }
          localObject1 = Long.valueOf(paramLong);
          localzze.zzbwi = ((Long)localObject1);
          ((zzasp)localObject3).zzJJ();
          localzze.zzbwu = Integer.valueOf((int)((zzasp)localObject3).zzJG());
          ((zzasp)localObject3).zzX(localzze.zzbwg.longValue());
          ((zzasp)localObject3).zzY(localzze.zzbwh.longValue());
          localzze.zzbqj = ((zzasp)localObject3).zzJR();
          zzJo().zza((zzasp)localObject3);
          continue;
        }
        localObject1 = null;
      }
      finally
      {
        zzJo().endTransaction();
      }
      continue;
      label1719:
      Object localObject1 = null;
      continue;
      label1725:
      localzze.zzbwB = ((zzaug.zzb)localObject1).zzbvK;
      continue;
      label1738:
      zzJo().setTransactionSuccessful();
      zzJo().endTransaction();
      return false;
      label1754:
      paramLong = l;
      continue;
      break label1868;
      label1763:
      break label1812;
      label1766:
      break label1796;
      label1769:
      continue;
      label1772:
      break label1781;
      label1775:
      int j = 1;
      continue;
      label1781:
      k += 1;
      continue;
      label1790:
      j = 0;
      continue;
      label1796:
      m += 1;
      continue;
      label1805:
      boolean bool2 = bool1;
      continue;
      label1812:
      m += 1;
      continue;
      label1821:
      j += 1;
      continue;
      for (;;)
      {
        label1830:
        if (m >= i1) {
          break label1875;
        }
        zzauh.zzc localzzc = arrayOfzzc[m];
        if (localzzc == localObject1) {
          break;
        }
        n = j + 1;
        localObject2[j] = localzzc;
        j = n;
        label1868:
        m += 1;
      }
      label1875:
      continue;
      label1877:
      boolean bool1 = bool2;
      continue;
      label1884:
      i += 1;
      continue;
      label1893:
      bool1 = false;
    }
  }
  
  public Context getContext()
  {
    return this.mContext;
  }
  
  public String getGmpAppIdOnPackageSide(final String paramString)
  {
    zzJd();
    Object localObject = zzJs().zze(new Callable()
    {
      public String zzou()
        throws Exception
      {
        zzasp localzzasp = zzatp.this.zzJo().zzfy(paramString);
        if (localzzasp == null) {
          return null;
        }
        return localzzasp.getGmpAppId();
      }
    });
    try
    {
      localObject = (String)((Future)localObject).get(30000L, TimeUnit.MILLISECONDS);
      return (String)localObject;
    }
    catch (InterruptedException localInterruptedException)
    {
      zzJt().zzLa().zze("Failed to get gmp app id. appId", zzati.zzfI(paramString), localInterruptedException);
      return null;
    }
    catch (ExecutionException localExecutionException)
    {
      for (;;) {}
    }
    catch (TimeoutException localTimeoutException)
    {
      for (;;) {}
    }
  }
  
  @WorkerThread
  public boolean isEnabled()
  {
    boolean bool = false;
    zzmq();
    zznA();
    if (zzJv().zzKl()) {
      return false;
    }
    Boolean localBoolean = zzJv().zzKm();
    if (localBoolean != null) {
      bool = localBoolean.booleanValue();
    }
    for (;;)
    {
      return zzJu().zzaG(bool);
      if (!zzJv().zzwk()) {
        bool = true;
      }
    }
  }
  
  @WorkerThread
  protected void start()
  {
    zzmq();
    zzJo().zzKI();
    if (zzJu().zzbsg.get() == 0L) {
      zzJu().zzbsg.set(zznq().currentTimeMillis());
    }
    if (!zzLt())
    {
      if (isEnabled())
      {
        if (!zzJp().zzbV("android.permission.INTERNET")) {
          zzJt().zzLa().log("App is missing INTERNET permission");
        }
        if (!zzJp().zzbV("android.permission.ACCESS_NETWORK_STATE")) {
          zzJt().zzLa().log("App is missing ACCESS_NETWORK_STATE permission");
        }
        zzJv().zzKk();
        zzacx.zzaQ(getContext());
        if (!zzatm.zzi(getContext(), false)) {
          zzJt().zzLa().log("AppMeasurementReceiver not registered/enabled");
        }
        if (!zzatx.zzj(getContext(), false)) {
          zzJt().zzLa().log("AppMeasurementService not registered/enabled");
        }
        zzJt().zzLa().log("Uploading is not possible. App measurement disabled");
      }
      zzLI();
      return;
    }
    zzJv().zzKk();
    String str;
    if (!TextUtils.isEmpty(zzJj().getGmpAppId()))
    {
      str = zzJu().zzLm();
      if (str != null) {
        break label271;
      }
      zzJu().zzfM(zzJj().getGmpAppId());
    }
    for (;;)
    {
      zzJv().zzKk();
      if (TextUtils.isEmpty(zzJj().getGmpAppId())) {
        break;
      }
      zzJi().zzLR();
      break;
      label271:
      if (!str.equals(zzJj().getGmpAppId()))
      {
        zzJt().zzLe().log("Rechecking which service to use due to a GMP App Id change");
        zzJu().zzLo();
        this.zzbtq.disconnect();
        this.zzbtq.zzoc();
        zzJu().zzfM(zzJj().getGmpAppId());
      }
    }
  }
  
  protected void zzH(List<Long> paramList)
  {
    if (!paramList.isEmpty()) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzas(bool);
      if (this.zzbtD == null) {
        break;
      }
      zzJt().zzLa().log("Set uploading progress before finishing the previous upload");
      return;
    }
    this.zzbtD = new ArrayList(paramList);
  }
  
  void zzJd()
  {
    zzJv().zzKk();
    throw new IllegalStateException("Unexpected call on client side");
  }
  
  void zzJe()
  {
    zzJv().zzKk();
  }
  
  public zzaso zzJg()
  {
    zza(this.zzbtx);
    return this.zzbtx;
  }
  
  public zzass zzJh()
  {
    zza(this.zzbtw);
    return this.zzbtw;
  }
  
  public zzatu zzJi()
  {
    zza(this.zzbts);
    return this.zzbts;
  }
  
  public zzatf zzJj()
  {
    zza(this.zzbtt);
    return this.zzbtt;
  }
  
  public zzasw zzJk()
  {
    zza(this.zzbtr);
    return this.zzbtr;
  }
  
  public zzatw zzJl()
  {
    zza(this.zzbtq);
    return this.zzbtq;
  }
  
  public zzatv zzJm()
  {
    zza(this.zzbtp);
    return this.zzbtp;
  }
  
  public zzatg zzJn()
  {
    zza(this.zzbtn);
    return this.zzbtn;
  }
  
  public zzasu zzJo()
  {
    zza(this.zzbtm);
    return this.zzbtm;
  }
  
  public zzaue zzJp()
  {
    zza(this.zzbtl);
    return this.zzbtl;
  }
  
  public zzatn zzJq()
  {
    zza(this.zzbti);
    return this.zzbti;
  }
  
  public zzaty zzJr()
  {
    zza(this.zzbth);
    return this.zzbth;
  }
  
  public zzato zzJs()
  {
    zza(this.zzbtg);
    return this.zzbtg;
  }
  
  public zzati zzJt()
  {
    zza(this.zzbtf);
    return this.zzbtf;
  }
  
  public zzatl zzJu()
  {
    zza(this.zzbte);
    return this.zzbte;
  }
  
  public zzast zzJv()
  {
    return this.zzbtd;
  }
  
  public zzaua zzLA()
  {
    zza(this.zzbtv);
    return this.zzbtv;
  }
  
  FileChannel zzLB()
  {
    return this.zzbtC;
  }
  
  @WorkerThread
  void zzLC()
  {
    zzmq();
    zznA();
    if ((zzLM()) && (zzLD())) {
      zzv(zza(zzLB()), zzJj().zzKZ());
    }
  }
  
  @WorkerThread
  boolean zzLD()
  {
    zzmq();
    Object localObject = this.zzbtm.zznV();
    localObject = new File(getContext().getFilesDir(), (String)localObject);
    try
    {
      this.zzbtC = new RandomAccessFile((File)localObject, "rw").getChannel();
      this.zzbtB = this.zzbtC.tryLock();
      if (this.zzbtB != null)
      {
        zzJt().zzLg().log("Storage concurrent access okay");
        return true;
      }
      zzJt().zzLa().log("Storage concurrent data access panic");
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      for (;;)
      {
        zzJt().zzLa().zzj("Failed to acquire storage lock", localFileNotFoundException);
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        zzJt().zzLa().zzj("Failed to access storage lock file", localIOException);
      }
    }
    return false;
  }
  
  long zzLE()
  {
    return (zznq().currentTimeMillis() + zzJu().zzLk()) / 1000L / 60L / 60L / 24L;
  }
  
  @WorkerThread
  protected boolean zzLF()
  {
    zzmq();
    return this.zzbtD != null;
  }
  
  @WorkerThread
  public void zzLG()
  {
    int j = 0;
    zzmq();
    zznA();
    zzJv().zzKk();
    Object localObject1 = zzJu().zzLn();
    if (localObject1 == null) {
      zzJt().zzLc().log("Upload data called on the client side before use of service was decided");
    }
    long l1;
    String str1;
    int i;
    Object localObject3;
    do
    {
      return;
      if (((Boolean)localObject1).booleanValue())
      {
        zzJt().zzLa().log("Upload called in the client side when service should be used");
        return;
      }
      if (zzLF())
      {
        zzJt().zzLc().log("Uploading requested multiple times");
        return;
      }
      if (!zzLy().zzpA())
      {
        zzJt().zzLc().log("Network not connected, ignoring upload request");
        zzLI();
        return;
      }
      l1 = zznq().currentTimeMillis();
      zzao(l1 - zzJv().zzKv());
      long l2 = zzJu().zzbsg.get();
      if (l2 != 0L) {
        zzJt().zzLf().zzj("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(l1 - l2)));
      }
      str1 = zzJo().zzKG();
      if (TextUtils.isEmpty(str1)) {
        break;
      }
      if (this.zzbtG == -1L) {
        this.zzbtG = zzJo().zzKO();
      }
      i = zzJv().zzfu(str1);
      int k = zzJv().zzfv(str1);
      localObject3 = zzJo().zzn(str1, i, k);
    } while (((List)localObject3).isEmpty());
    localObject1 = ((List)localObject3).iterator();
    Object localObject4;
    do
    {
      if (!((Iterator)localObject1).hasNext()) {
        break;
      }
      localObject4 = (zzauh.zze)((Pair)((Iterator)localObject1).next()).first;
    } while (TextUtils.isEmpty(((zzauh.zze)localObject4).zzbwq));
    Object localObject2;
    for (localObject1 = ((zzauh.zze)localObject4).zzbwq;; localObject2 = null)
    {
      if (localObject1 != null)
      {
        i = 0;
        if (i < ((List)localObject3).size())
        {
          localObject4 = (zzauh.zze)((Pair)((List)localObject3).get(i)).first;
          if (TextUtils.isEmpty(((zzauh.zze)localObject4).zzbwq)) {}
          while (((zzauh.zze)localObject4).zzbwq.equals(localObject1))
          {
            i += 1;
            break;
          }
        }
      }
      for (localObject1 = ((List)localObject3).subList(0, i);; localObject2 = localObject3)
      {
        localObject4 = new zzauh.zzd();
        ((zzauh.zzd)localObject4).zzbwa = new zzauh.zze[((List)localObject1).size()];
        localObject3 = new ArrayList(((List)localObject1).size());
        i = j;
        while (i < ((zzauh.zzd)localObject4).zzbwa.length)
        {
          ((zzauh.zzd)localObject4).zzbwa[i] = ((zzauh.zze)((Pair)((List)localObject1).get(i)).first);
          ((List)localObject3).add((Long)((Pair)((List)localObject1).get(i)).second);
          localObject4.zzbwa[i].zzbwp = Long.valueOf(zzJv().zzJD());
          localObject4.zzbwa[i].zzbwf = Long.valueOf(l1);
          localObject4.zzbwa[i].zzbwv = Boolean.valueOf(zzJv().zzKk());
          i += 1;
        }
        if (zzJt().zzai(2)) {}
        for (localObject1 = zzaue.zzb((zzauh.zzd)localObject4);; localObject2 = null)
        {
          byte[] arrayOfByte = zzJp().zza((zzauh.zzd)localObject4);
          String str2 = zzJv().zzKu();
          try
          {
            URL localURL = new URL(str2);
            zzH((List)localObject3);
            zzJu().zzbsh.set(l1);
            localObject3 = "?";
            if (((zzauh.zzd)localObject4).zzbwa.length > 0) {
              localObject3 = localObject4.zzbwa[0].zzaR;
            }
            zzJt().zzLg().zzd("Uploading data. app, uncompressed size, data", localObject3, Integer.valueOf(arrayOfByte.length), localObject1);
            zzLy().zza(str1, localURL, arrayOfByte, null, new zzatj.zza()
            {
              public void zza(String paramAnonymousString, int paramAnonymousInt, Throwable paramAnonymousThrowable, byte[] paramAnonymousArrayOfByte, Map<String, List<String>> paramAnonymousMap)
              {
                zzatp.this.zza(paramAnonymousInt, paramAnonymousThrowable, paramAnonymousArrayOfByte);
              }
            });
            return;
          }
          catch (MalformedURLException localMalformedURLException)
          {
            zzJt().zzLa().zze("Failed to parse upload URL. Not uploading. appId", zzati.zzfI(str1), str2);
            return;
          }
          this.zzbtG = -1L;
          localObject2 = zzJo().zzam(l1 - zzJv().zzKv());
          if (TextUtils.isEmpty((CharSequence)localObject2)) {
            break;
          }
          localObject2 = zzJo().zzfy((String)localObject2);
          if (localObject2 == null) {
            break;
          }
          zzb((zzasp)localObject2);
          return;
        }
      }
    }
  }
  
  void zzLK()
  {
    this.zzbtF += 1;
  }
  
  @WorkerThread
  void zzLL()
  {
    zzmq();
    zznA();
    if (!this.zzbty)
    {
      zzJt().zzLe().log("This instance being marked as an uploader");
      zzLC();
    }
    this.zzbty = true;
  }
  
  @WorkerThread
  boolean zzLM()
  {
    zzmq();
    zznA();
    return this.zzbty;
  }
  
  @WorkerThread
  protected boolean zzLt()
  {
    boolean bool2 = false;
    zznA();
    zzmq();
    if ((this.zzbtz == null) || (this.zzbtA == 0L) || ((this.zzbtz != null) && (!this.zzbtz.booleanValue()) && (Math.abs(zznq().elapsedRealtime() - this.zzbtA) > 1000L)))
    {
      this.zzbtA = zznq().elapsedRealtime();
      zzJv().zzKk();
      boolean bool1 = bool2;
      if (zzJp().zzbV("android.permission.INTERNET"))
      {
        bool1 = bool2;
        if (zzJp().zzbV("android.permission.ACCESS_NETWORK_STATE"))
        {
          zzacx.zzaQ(getContext());
          bool1 = bool2;
          if (zzatm.zzi(getContext(), false))
          {
            bool1 = bool2;
            if (zzatx.zzj(getContext(), false)) {
              bool1 = true;
            }
          }
        }
      }
      this.zzbtz = Boolean.valueOf(bool1);
      if (this.zzbtz.booleanValue()) {
        this.zzbtz = Boolean.valueOf(zzJp().zzgd(zzJj().getGmpAppId()));
      }
    }
    return this.zzbtz.booleanValue();
  }
  
  public zzati zzLu()
  {
    if ((this.zzbtf != null) && (this.zzbtf.isInitialized())) {
      return this.zzbtf;
    }
    return null;
  }
  
  zzato zzLv()
  {
    return this.zzbtg;
  }
  
  public AppMeasurement zzLw()
  {
    return this.zzbtj;
  }
  
  public FirebaseAnalytics zzLx()
  {
    return this.zzbtk;
  }
  
  public zzatj zzLy()
  {
    zza(this.zzbto);
    return this.zzbto;
  }
  
  public zzatk zzLz()
  {
    if (this.zzbtu == null) {
      throw new IllegalStateException("Network broadcast receiver not created");
    }
    return this.zzbtu;
  }
  
  public void zzV(boolean paramBoolean)
  {
    zzLI();
  }
  
  @WorkerThread
  int zza(FileChannel paramFileChannel)
  {
    zzmq();
    if ((paramFileChannel == null) || (!paramFileChannel.isOpen())) {
      zzJt().zzLa().log("Bad chanel to read from");
    }
    ByteBuffer localByteBuffer;
    for (;;)
    {
      return 0;
      localByteBuffer = ByteBuffer.allocate(4);
      try
      {
        paramFileChannel.position(0L);
        i = paramFileChannel.read(localByteBuffer);
        if (i != 4)
        {
          if (i == -1) {
            continue;
          }
          zzJt().zzLc().zzj("Unexpected data length. Bytes read", Integer.valueOf(i));
          return 0;
        }
      }
      catch (IOException paramFileChannel)
      {
        zzJt().zzLa().zzj("Failed to read from channel", paramFileChannel);
        return 0;
      }
    }
    localByteBuffer.flip();
    int i = localByteBuffer.getInt();
    return i;
  }
  
  @WorkerThread
  protected void zza(int paramInt, Throwable paramThrowable, byte[] paramArrayOfByte)
  {
    int i = 0;
    zzmq();
    zznA();
    byte[] arrayOfByte = paramArrayOfByte;
    if (paramArrayOfByte == null) {
      arrayOfByte = new byte[0];
    }
    paramArrayOfByte = this.zzbtD;
    this.zzbtD = null;
    if (((paramInt == 200) || (paramInt == 204)) && (paramThrowable == null))
    {
      zzJu().zzbsg.set(zznq().currentTimeMillis());
      zzJu().zzbsh.set(0L);
      zzLI();
      zzJt().zzLg().zze("Successful upload. Got network response. code, size", Integer.valueOf(paramInt), Integer.valueOf(arrayOfByte.length));
      zzJo().beginTransaction();
      try
      {
        paramThrowable = paramArrayOfByte.iterator();
        while (paramThrowable.hasNext())
        {
          paramArrayOfByte = (Long)paramThrowable.next();
          zzJo().zzal(paramArrayOfByte.longValue());
        }
      }
      finally
      {
        zzJo().endTransaction();
      }
      zzJo().endTransaction();
      if ((zzLy().zzpA()) && (zzLH()))
      {
        zzLG();
        return;
      }
      this.zzbtG = -1L;
      zzLI();
      return;
    }
    zzJt().zzLg().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(paramInt), paramThrowable);
    zzJu().zzbsh.set(zznq().currentTimeMillis());
    if ((paramInt == 503) || (paramInt == 429)) {
      i = 1;
    }
    if (i != 0) {
      zzJu().zzbsi.set(zznq().currentTimeMillis());
    }
    zzLI();
  }
  
  @WorkerThread
  void zza(zzasq paramzzasq, long paramLong)
  {
    Object localObject2 = zzJo().zzfy(paramzzasq.packageName);
    Object localObject1 = localObject2;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((zzasp)localObject2).getGmpAppId() != null)
      {
        localObject1 = localObject2;
        if (!((zzasp)localObject2).getGmpAppId().equals(paramzzasq.zzbqf))
        {
          zzJt().zzLc().zzj("New GMP App Id passed in. Removing cached database data. appId", zzati.zzfI(((zzasp)localObject2).zzjI()));
          zzJo().zzfD(((zzasp)localObject2).zzjI());
          localObject1 = null;
        }
      }
    }
    if ((localObject1 != null) && (((zzasp)localObject1).zzmy() != null) && (!((zzasp)localObject1).zzmy().equals(paramzzasq.zzbhg)))
    {
      localObject2 = new Bundle();
      ((Bundle)localObject2).putString("_pv", ((zzasp)localObject1).zzmy());
      zzb(new zzatb("_au", new zzasz((Bundle)localObject2), "auto", paramLong), paramzzasq);
    }
  }
  
  void zza(zzasx paramzzasx, zzasq paramzzasq)
  {
    zzmq();
    zznA();
    zzac.zzw(paramzzasx);
    zzac.zzw(paramzzasq);
    zzac.zzdv(paramzzasx.zzVQ);
    zzac.zzas(paramzzasx.zzVQ.equals(paramzzasq.packageName));
    zzauh.zze localzze = new zzauh.zze();
    localzze.zzbwc = Integer.valueOf(1);
    localzze.zzbwk = "android";
    localzze.zzaR = paramzzasq.packageName;
    localzze.zzbqg = paramzzasq.zzbqg;
    localzze.zzbhg = paramzzasq.zzbhg;
    localzze.zzbwx = Integer.valueOf((int)paramzzasq.zzbqm);
    localzze.zzbwo = Long.valueOf(paramzzasq.zzbqh);
    localzze.zzbqf = paramzzasq.zzbqf;
    Object localObject1;
    if (paramzzasq.zzbqi == 0L)
    {
      localObject1 = null;
      localzze.zzbwt = ((Long)localObject1);
      localObject1 = zzJu().zzfK(paramzzasq.packageName);
      if (TextUtils.isEmpty((CharSequence)((Pair)localObject1).first)) {
        break label594;
      }
      localzze.zzbwq = ((String)((Pair)localObject1).first);
      localzze.zzbwr = ((Boolean)((Pair)localObject1).second);
    }
    label594:
    while (zzJk().zzbt(this.mContext))
    {
      localzze.zzbwl = zzJk().zzkm();
      localzze.zzba = zzJk().zzKU();
      localzze.zzbwn = Integer.valueOf((int)zzJk().zzKV());
      localzze.zzbwm = zzJk().zzKW();
      localzze.zzbwp = null;
      localzze.zzbwf = null;
      localzze.zzbwg = null;
      localzze.zzbwh = null;
      localObject2 = zzJo().zzfy(paramzzasq.packageName);
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new zzasp(this, paramzzasq.packageName);
        ((zzasp)localObject1).zzfh(zzJu().zzLj());
        ((zzasp)localObject1).zzfk(paramzzasq.zzbqn);
        ((zzasp)localObject1).zzfi(paramzzasq.zzbqf);
        ((zzasp)localObject1).zzfj(zzJu().zzfL(paramzzasq.packageName));
        ((zzasp)localObject1).zzac(0L);
        ((zzasp)localObject1).zzX(0L);
        ((zzasp)localObject1).zzY(0L);
        ((zzasp)localObject1).setAppVersion(paramzzasq.zzbhg);
        ((zzasp)localObject1).zzZ(paramzzasq.zzbqm);
        ((zzasp)localObject1).zzfl(paramzzasq.zzbqg);
        ((zzasp)localObject1).zzaa(paramzzasq.zzbqh);
        ((zzasp)localObject1).zzab(paramzzasq.zzbqi);
        ((zzasp)localObject1).setMeasurementEnabled(paramzzasq.zzbqk);
        zzJo().zza((zzasp)localObject1);
      }
      localzze.zzbws = ((zzasp)localObject1).getAppInstanceId();
      localzze.zzbqn = ((zzasp)localObject1).zzJy();
      paramzzasq = zzJo().zzfx(paramzzasq.packageName);
      localzze.zzbwe = new zzauh.zzg[paramzzasq.size()];
      int i = 0;
      while (i < paramzzasq.size())
      {
        localObject1 = new zzauh.zzg();
        localzze.zzbwe[i] = localObject1;
        ((zzauh.zzg)localObject1).name = ((zzaud)paramzzasq.get(i)).mName;
        ((zzauh.zzg)localObject1).zzbwF = Long.valueOf(((zzaud)paramzzasq.get(i)).zzbvd);
        zzJp().zza((zzauh.zzg)localObject1, ((zzaud)paramzzasq.get(i)).zzYe);
        i += 1;
      }
      localObject1 = Long.valueOf(paramzzasq.zzbqi);
      break;
    }
    Object localObject2 = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
    if (localObject2 == null)
    {
      zzJt().zzLc().zzj("null secure ID. appId", zzati.zzfI(localzze.zzaR));
      localObject1 = "null";
    }
    for (;;)
    {
      localzze.zzbwA = ((String)localObject1);
      break;
      localObject1 = localObject2;
      if (((String)localObject2).isEmpty())
      {
        zzJt().zzLc().zzj("empty secure ID. appId", zzati.zzfI(localzze.zzaR));
        localObject1 = localObject2;
      }
    }
    try
    {
      long l = zzJo().zza(localzze);
      zzJo().zza(paramzzasx, l, zza(paramzzasx));
      return;
    }
    catch (IOException paramzzasx)
    {
      zzJt().zzLa().zze("Data loss. Failed to insert raw event metadata. appId", zzati.zzfI(localzze.zzaR), paramzzasx);
    }
  }
  
  @WorkerThread
  boolean zza(int paramInt, FileChannel paramFileChannel)
  {
    boolean bool = true;
    zzmq();
    if ((paramFileChannel == null) || (!paramFileChannel.isOpen()))
    {
      zzJt().zzLa().log("Bad chanel to read from");
      bool = false;
    }
    for (;;)
    {
      return bool;
      ByteBuffer localByteBuffer = ByteBuffer.allocate(4);
      localByteBuffer.putInt(paramInt);
      localByteBuffer.flip();
      try
      {
        paramFileChannel.truncate(0L);
        paramFileChannel.write(localByteBuffer);
        paramFileChannel.force(true);
        if (paramFileChannel.size() != 4L)
        {
          zzJt().zzLa().zzj("Error writing to channel. Bytes written", Long.valueOf(paramFileChannel.size()));
          return true;
        }
      }
      catch (IOException paramFileChannel)
      {
        zzJt().zzLa().zzj("Failed to write to channel", paramFileChannel);
      }
    }
    return false;
  }
  
  @WorkerThread
  public byte[] zza(@NonNull zzatb paramzzatb, @Size(min=1L) String paramString)
  {
    zznA();
    zzmq();
    zzJd();
    zzac.zzw(paramzzatb);
    zzac.zzdv(paramString);
    zzauh.zzd localzzd = new zzauh.zzd();
    zzJo().beginTransaction();
    Object localObject1;
    zzauh.zze localzze;
    Object localObject2;
    try
    {
      localObject1 = zzJo().zzfy(paramString);
      if (localObject1 == null)
      {
        zzJt().zzLf().zzj("Log and bundle not available. package_name", paramString);
        return new byte[0];
      }
      if (!((zzasp)localObject1).zzJF())
      {
        zzJt().zzLf().zzj("Log and bundle disabled. package_name", paramString);
        return new byte[0];
      }
      localzze = new zzauh.zze();
      localzzd.zzbwa = new zzauh.zze[] { localzze };
      localzze.zzbwc = Integer.valueOf(1);
      localzze.zzbwk = "android";
      localzze.zzaR = ((zzasp)localObject1).zzjI();
      localzze.zzbqg = ((zzasp)localObject1).zzJC();
      localzze.zzbhg = ((zzasp)localObject1).zzmy();
      localzze.zzbwx = Integer.valueOf((int)((zzasp)localObject1).zzJB());
      localzze.zzbwo = Long.valueOf(((zzasp)localObject1).zzJD());
      localzze.zzbqf = ((zzasp)localObject1).getGmpAppId();
      localzze.zzbwt = Long.valueOf(((zzasp)localObject1).zzJE());
      localObject2 = zzJu().zzfK(((zzasp)localObject1).zzjI());
      if (!TextUtils.isEmpty((CharSequence)((Pair)localObject2).first))
      {
        localzze.zzbwq = ((String)((Pair)localObject2).first);
        localzze.zzbwr = ((Boolean)((Pair)localObject2).second);
      }
      localzze.zzbwl = zzJk().zzkm();
      localzze.zzba = zzJk().zzKU();
      localzze.zzbwn = Integer.valueOf((int)zzJk().zzKV());
      localzze.zzbwm = zzJk().zzKW();
      localzze.zzbws = ((zzasp)localObject1).getAppInstanceId();
      localzze.zzbqn = ((zzasp)localObject1).zzJy();
      localObject2 = zzJo().zzfx(((zzasp)localObject1).zzjI());
      localzze.zzbwe = new zzauh.zzg[((List)localObject2).size()];
      int i = 0;
      while (i < ((List)localObject2).size())
      {
        localObject3 = new zzauh.zzg();
        localzze.zzbwe[i] = localObject3;
        ((zzauh.zzg)localObject3).name = ((zzaud)((List)localObject2).get(i)).mName;
        ((zzauh.zzg)localObject3).zzbwF = Long.valueOf(((zzaud)((List)localObject2).get(i)).zzbvd);
        zzJp().zza((zzauh.zzg)localObject3, ((zzaud)((List)localObject2).get(i)).zzYe);
        i += 1;
      }
      localObject2 = paramzzatb.zzbqP.zzKY();
      if ("_iap".equals(paramzzatb.name))
      {
        ((Bundle)localObject2).putLong("_c", 1L);
        zzJt().zzLf().log("Marking in-app purchase as real-time");
        ((Bundle)localObject2).putLong("_r", 1L);
      }
      ((Bundle)localObject2).putString("_o", paramzzatb.zzbqQ);
      if (zzJp().zzgh(localzze.zzaR))
      {
        zzJp().zza((Bundle)localObject2, "_dbg", Long.valueOf(1L));
        zzJp().zza((Bundle)localObject2, "_r", Long.valueOf(1L));
      }
      Object localObject3 = zzJo().zzP(paramString, paramzzatb.name);
      if (localObject3 == null)
      {
        localObject3 = new zzasy(paramString, paramzzatb.name, 1L, 0L, paramzzatb.zzbqR);
        zzJo().zza((zzasy)localObject3);
        l1 = 0L;
      }
      for (;;)
      {
        paramzzatb = new zzasx(this, paramzzatb.zzbqQ, paramString, paramzzatb.name, paramzzatb.zzbqR, l1, (Bundle)localObject2);
        localObject2 = new zzauh.zzb();
        localzze.zzbwd = new zzauh.zzb[] { localObject2 };
        ((zzauh.zzb)localObject2).zzbvW = Long.valueOf(paramzzatb.zzavX);
        ((zzauh.zzb)localObject2).name = paramzzatb.mName;
        ((zzauh.zzb)localObject2).zzbvX = Long.valueOf(paramzzatb.zzbqH);
        ((zzauh.zzb)localObject2).zzbvV = new zzauh.zzc[paramzzatb.zzbqI.size()];
        localObject3 = paramzzatb.zzbqI.iterator();
        i = 0;
        while (((Iterator)localObject3).hasNext())
        {
          Object localObject4 = (String)((Iterator)localObject3).next();
          zzauh.zzc localzzc = new zzauh.zzc();
          ((zzauh.zzb)localObject2).zzbvV[i] = localzzc;
          localzzc.name = ((String)localObject4);
          localObject4 = paramzzatb.zzbqI.get((String)localObject4);
          zzJp().zza(localzzc, localObject4);
          i += 1;
        }
        l1 = ((zzasy)localObject3).zzbqL;
        localObject3 = ((zzasy)localObject3).zzan(paramzzatb.zzbqR).zzKX();
        zzJo().zza((zzasy)localObject3);
      }
      localzze.zzbww = zza(((zzasp)localObject1).zzjI(), localzze.zzbwe, localzze.zzbwd);
    }
    finally
    {
      zzJo().endTransaction();
    }
    localzze.zzbwg = ((zzauh.zzb)localObject2).zzbvW;
    localzze.zzbwh = ((zzauh.zzb)localObject2).zzbvW;
    long l1 = ((zzasp)localObject1).zzJA();
    long l2;
    if (l1 != 0L)
    {
      paramzzatb = Long.valueOf(l1);
      localzze.zzbwj = paramzzatb;
      l2 = ((zzasp)localObject1).zzJz();
      if (l2 != 0L) {
        break label1157;
      }
    }
    for (;;)
    {
      if (l1 != 0L) {}
      for (paramzzatb = Long.valueOf(l1);; paramzzatb = null)
      {
        localzze.zzbwi = paramzzatb;
        ((zzasp)localObject1).zzJJ();
        localzze.zzbwu = Integer.valueOf((int)((zzasp)localObject1).zzJG());
        localzze.zzbwp = Long.valueOf(zzJv().zzJD());
        localzze.zzbwf = Long.valueOf(zznq().currentTimeMillis());
        localzze.zzbwv = Boolean.TRUE;
        ((zzasp)localObject1).zzX(localzze.zzbwg.longValue());
        ((zzasp)localObject1).zzY(localzze.zzbwh.longValue());
        zzJo().zza((zzasp)localObject1);
        zzJo().setTransactionSuccessful();
        zzJo().endTransaction();
        try
        {
          paramzzatb = new byte[localzzd.zzacZ()];
          localObject1 = zzbum.zzae(paramzzatb);
          localzzd.zza((zzbum)localObject1);
          ((zzbum)localObject1).zzacM();
          paramzzatb = zzJp().zzk(paramzzatb);
          return paramzzatb;
        }
        catch (IOException paramzzatb)
        {
          zzJt().zzLa().zze("Data loss. Failed to bundle and serialize. appId", zzati.zzfI(paramString), paramzzatb);
          return null;
        }
        paramzzatb = null;
        break;
      }
      label1157:
      l1 = l2;
    }
  }
  
  boolean zzao(long paramLong)
  {
    return zzj(null, paramLong);
  }
  
  void zzb(zzasp paramzzasp)
  {
    String str1 = zzJv().zzO(paramzzasp.getGmpAppId(), paramzzasp.getAppInstanceId());
    try
    {
      URL localURL = new URL(str1);
      zzJt().zzLg().zzj("Fetching remote configuration", paramzzasp.zzjI());
      zzaug.zzb localzzb = zzJq().zzfO(paramzzasp.zzjI());
      Object localObject2 = null;
      String str2 = zzJq().zzfP(paramzzasp.zzjI());
      Object localObject1 = localObject2;
      if (localzzb != null)
      {
        localObject1 = localObject2;
        if (!TextUtils.isEmpty(str2))
        {
          localObject1 = new ArrayMap();
          ((Map)localObject1).put("If-Modified-Since", str2);
        }
      }
      zzLy().zza(paramzzasp.zzjI(), localURL, (Map)localObject1, new zzatj.zza()
      {
        public void zza(String paramAnonymousString, int paramAnonymousInt, Throwable paramAnonymousThrowable, byte[] paramAnonymousArrayOfByte, Map<String, List<String>> paramAnonymousMap)
        {
          zzatp.this.zzb(paramAnonymousString, paramAnonymousInt, paramAnonymousThrowable, paramAnonymousArrayOfByte, paramAnonymousMap);
        }
      });
      return;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      zzJt().zzLa().zze("Failed to parse config URL. Not fetching. appId", zzati.zzfI(paramzzasp.zzjI()), str1);
    }
  }
  
  @WorkerThread
  void zzb(zzasq paramzzasq, long paramLong)
  {
    zzmq();
    zznA();
    Bundle localBundle = new Bundle();
    localBundle.putLong("_c", 1L);
    localBundle.putLong("_r", 1L);
    localBundle.putLong("_uwa", 0L);
    localBundle.putLong("_pfo", 0L);
    localBundle.putLong("_sys", 0L);
    localBundle.putLong("_sysu", 0L);
    if (getContext().getPackageManager() == null) {
      zzJt().zzLa().zzj("PackageManager is null, first open report might be inaccurate. appId", zzati.zzfI(paramzzasq.packageName));
    }
    for (;;)
    {
      long l = zzJo().zzfE(paramzzasq.packageName);
      if (l >= 0L) {
        localBundle.putLong("_pfo", l);
      }
      zzb(new zzatb("_f", new zzasz(localBundle), "auto", paramLong), paramzzasq);
      return;
      try
      {
        localObject1 = zzacx.zzaQ(getContext()).getPackageInfo(paramzzasq.packageName, 0);
        if ((localObject1 != null) && (((PackageInfo)localObject1).firstInstallTime != 0L) && (((PackageInfo)localObject1).firstInstallTime != ((PackageInfo)localObject1).lastUpdateTime)) {
          localBundle.putLong("_uwa", 1L);
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException1)
      {
        try
        {
          Object localObject1 = zzacx.zzaQ(getContext()).getApplicationInfo(paramzzasq.packageName, 0);
          if (localObject1 == null) {
            continue;
          }
          if ((((ApplicationInfo)localObject1).flags & 0x1) != 0) {
            localBundle.putLong("_sys", 1L);
          }
          if ((((ApplicationInfo)localObject1).flags & 0x80) == 0) {
            continue;
          }
          localBundle.putLong("_sysu", 1L);
          continue;
          localNameNotFoundException1 = localNameNotFoundException1;
          zzJt().zzLa().zze("Package info is null, first open report might be inaccurate. appId", zzati.zzfI(paramzzasq.packageName), localNameNotFoundException1);
          Object localObject2 = null;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException2)
        {
          for (;;)
          {
            zzJt().zzLa().zze("Application info is null, first open report might be inaccurate. appId", zzati.zzfI(paramzzasq.packageName), localNameNotFoundException2);
            Object localObject3 = null;
          }
        }
      }
    }
  }
  
  @WorkerThread
  void zzb(zzatb paramzzatb, zzasq paramzzasq)
  {
    long l2 = System.nanoTime();
    zzmq();
    zznA();
    String str = paramzzasq.packageName;
    zzac.zzdv(str);
    if (!zzaue.zzc(paramzzatb, paramzzasq)) {
      return;
    }
    if ((!paramzzasq.zzbqk) && (!"_in".equals(paramzzatb.name)))
    {
      zzf(paramzzasq);
      return;
    }
    if (zzJq().zzX(str, paramzzatb.name))
    {
      zzJt().zzLc().zze("Dropping blacklisted event. appId", zzati.zzfI(str), paramzzatb.name);
      if ((zzJp().zzgj(str)) || (zzJp().zzgk(str))) {}
      for (int i = 1;; i = 0)
      {
        if ((i == 0) && (!"_err".equals(paramzzatb.name))) {
          zzJp().zza(11, "_ev", paramzzatb.name, 0);
        }
        if (i == 0) {
          break;
        }
        paramzzatb = zzJo().zzfy(str);
        if (paramzzatb == null) {
          break;
        }
        l1 = Math.max(paramzzatb.zzJI(), paramzzatb.zzJH());
        if (Math.abs(zznq().currentTimeMillis() - l1) <= zzJv().zzKp()) {
          break;
        }
        zzJt().zzLf().log("Fetching config for blacklisted app");
        zzb(paramzzatb);
        return;
      }
    }
    if (zzJt().zzai(2)) {
      zzJt().zzLg().zzj("Logging event", paramzzatb);
    }
    zzJo().beginTransaction();
    Bundle localBundle;
    boolean bool1;
    boolean bool2;
    for (;;)
    {
      try
      {
        localBundle = paramzzatb.zzbqP.zzKY();
        zzf(paramzzasq);
        double d1;
        Object localObject2;
        if (("_iap".equals(paramzzatb.name)) || ("ecommerce_purchase".equals(paramzzatb.name)))
        {
          localObject1 = localBundle.getString("currency");
          if (!"ecommerce_purchase".equals(paramzzatb.name)) {
            continue;
          }
          double d2 = localBundle.getDouble("value") * 1000000.0D;
          d1 = d2;
          if (d2 == 0.0D) {
            d1 = localBundle.getLong("value") * 1000000.0D;
          }
          if ((d1 > 9.223372036854776E18D) || (d1 < -9.223372036854776E18D)) {
            continue;
          }
          l1 = Math.round(d1);
          if (!TextUtils.isEmpty((CharSequence)localObject1))
          {
            localObject2 = ((String)localObject1).toUpperCase(Locale.US);
            if (((String)localObject2).matches("[A-Z]{3}"))
            {
              localObject1 = String.valueOf("_ltv_");
              localObject2 = String.valueOf(localObject2);
              if (((String)localObject2).length() == 0) {
                continue;
              }
              localObject1 = ((String)localObject1).concat((String)localObject2);
              localObject2 = zzJo().zzR(str, (String)localObject1);
              if ((localObject2 != null) && ((((zzaud)localObject2).zzYe instanceof Long))) {
                break label795;
              }
              zzJo().zzz(str, zzJv().zzfr(str) - 1);
              localObject1 = new zzaud(str, (String)localObject1, zznq().currentTimeMillis(), Long.valueOf(l1));
              if (!zzJo().zza((zzaud)localObject1))
              {
                zzJt().zzLa().zzd("Too many unique user properties are set. Ignoring user property. appId", zzati.zzfI(str), ((zzaud)localObject1).mName, ((zzaud)localObject1).zzYe);
                zzJp().zza(9, null, null, 0);
              }
            }
          }
        }
        bool1 = zzaue.zzfW(paramzzatb.name);
        bool2 = "_err".equals(paramzzatb.name);
        localObject1 = zzJo().zza(zzLE(), str, true, bool1, false, bool2, false);
        l1 = ((zzasu.zza)localObject1).zzbqw - zzJv().zzKc();
        if (l1 <= 0L) {
          break;
        }
        if (l1 % 1000L == 1L) {
          zzJt().zzLa().zze("Data loss. Too many events logged. appId, count", zzati.zzfI(str), Long.valueOf(((zzasu.zza)localObject1).zzbqw));
        }
        zzJp().zza(16, "_ev", paramzzatb.name, 0);
        zzJo().setTransactionSuccessful();
        return;
        zzJt().zzLc().zze("Data lost. Currency value is too big. appId", zzati.zzfI(str), Double.valueOf(d1));
        zzJo().setTransactionSuccessful();
        return;
        l1 = localBundle.getLong("value");
        continue;
        localObject1 = new String((String)localObject1);
        continue;
        l3 = ((Long)((zzaud)localObject2).zzYe).longValue();
      }
      finally
      {
        zzJo().endTransaction();
      }
      label795:
      long l3;
      localObject1 = new zzaud(str, (String)localObject1, zznq().currentTimeMillis(), Long.valueOf(l1 + l3));
    }
    if (bool1)
    {
      l1 = ((zzasu.zza)localObject1).zzbqv - zzJv().zzKd();
      if (l1 > 0L)
      {
        if (l1 % 1000L == 1L) {
          zzJt().zzLa().zze("Data loss. Too many public events logged. appId, count", zzati.zzfI(str), Long.valueOf(((zzasu.zza)localObject1).zzbqv));
        }
        zzJp().zza(16, "_ev", paramzzatb.name, 0);
        zzJo().setTransactionSuccessful();
        zzJo().endTransaction();
        return;
      }
    }
    if (bool2)
    {
      l1 = ((zzasu.zza)localObject1).zzbqy - zzJv().zzfn(paramzzasq.packageName);
      if (l1 > 0L)
      {
        if (l1 == 1L) {
          zzJt().zzLa().zze("Too many error events logged. appId, count", zzati.zzfI(str), Long.valueOf(((zzasu.zza)localObject1).zzbqy));
        }
        zzJo().setTransactionSuccessful();
        zzJo().endTransaction();
        return;
      }
    }
    zzJp().zza(localBundle, "_o", paramzzatb.zzbqQ);
    if (zzJp().zzgh(str))
    {
      zzJp().zza(localBundle, "_dbg", Long.valueOf(1L));
      zzJp().zza(localBundle, "_r", Long.valueOf(1L));
    }
    long l1 = zzJo().zzfz(str);
    if (l1 > 0L) {
      zzJt().zzLc().zze("Data lost. Too many events stored on disk, deleted. appId", zzati.zzfI(str), Long.valueOf(l1));
    }
    paramzzatb = new zzasx(this, paramzzatb.zzbqQ, str, paramzzatb.name, paramzzatb.zzbqR, 0L, localBundle);
    Object localObject1 = zzJo().zzP(str, paramzzatb.mName);
    if (localObject1 == null)
    {
      l1 = zzJo().zzfG(str);
      zzJv().zzKb();
      if (l1 >= 500L)
      {
        zzJt().zzLa().zzd("Too many event names used, ignoring event. appId, name, supported count", zzati.zzfI(str), paramzzatb.mName, Integer.valueOf(zzJv().zzKb()));
        zzJp().zza(8, null, null, 0);
        zzJo().endTransaction();
        return;
      }
    }
    for (localObject1 = new zzasy(str, paramzzatb.mName, 0L, 0L, paramzzatb.zzavX);; localObject1 = ((zzasy)localObject1).zzan(paramzzatb.zzavX))
    {
      zzJo().zza((zzasy)localObject1);
      zza(paramzzatb, paramzzasq);
      zzJo().setTransactionSuccessful();
      if (zzJt().zzai(2)) {
        zzJt().zzLg().zzj("Event recorded", paramzzatb);
      }
      zzJo().endTransaction();
      zzLI();
      zzJt().zzLg().zzj("Background event processing time, ms", Long.valueOf((System.nanoTime() - l2 + 500000L) / 1000000L));
      return;
      paramzzatb = paramzzatb.zza(this, ((zzasy)localObject1).zzbqL);
    }
  }
  
  @WorkerThread
  void zzb(zzatb paramzzatb, String paramString)
  {
    zzasp localzzasp = zzJo().zzfy(paramString);
    if ((localzzasp == null) || (TextUtils.isEmpty(localzzasp.zzmy())))
    {
      zzJt().zzLf().zzj("No app data available; dropping event", paramString);
      return;
    }
    try
    {
      String str = zzacx.zzaQ(getContext()).getPackageInfo(paramString, 0).versionName;
      if ((localzzasp.zzmy() != null) && (!localzzasp.zzmy().equals(str)))
      {
        zzJt().zzLc().zzj("App version does not match; dropping event. appId", zzati.zzfI(paramString));
        return;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      if (!"_ui".equals(paramzzatb.name)) {
        zzJt().zzLc().zzj("Could not find package. appId", zzati.zzfI(paramString));
      }
      zzb(paramzzatb, new zzasq(paramString, localzzasp.getGmpAppId(), localzzasp.zzmy(), localzzasp.zzJB(), localzzasp.zzJC(), localzzasp.zzJD(), localzzasp.zzJE(), null, localzzasp.zzJF(), false, localzzasp.zzJy()));
    }
  }
  
  void zzb(zzats paramzzats)
  {
    this.zzbtE += 1;
  }
  
  /* Error */
  @WorkerThread
  void zzb(zzaub paramzzaub, zzasq paramzzasq)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: iconst_0
    //   4: istore_3
    //   5: aload_0
    //   6: invokevirtual 426	com/google/android/gms/internal/zzatp:zzmq	()V
    //   9: aload_0
    //   10: invokevirtual 429	com/google/android/gms/internal/zzatp:zznA	()V
    //   13: aload_2
    //   14: getfield 698	com/google/android/gms/internal/zzasq:zzbqf	Ljava/lang/String;
    //   17: invokestatic 445	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   20: ifeq +4 -> 24
    //   23: return
    //   24: aload_2
    //   25: getfield 759	com/google/android/gms/internal/zzasq:zzbqk	Z
    //   28: ifne +9 -> 37
    //   31: aload_0
    //   32: aload_2
    //   33: invokespecial 1758	com/google/android/gms/internal/zzatp:zzf	(Lcom/google/android/gms/internal/zzasq;)V
    //   36: return
    //   37: aload_0
    //   38: invokevirtual 228	com/google/android/gms/internal/zzatp:zzJp	()Lcom/google/android/gms/internal/zzaue;
    //   41: aload_1
    //   42: getfield 1912	com/google/android/gms/internal/zzaub:name	Ljava/lang/String;
    //   45: invokevirtual 1915	com/google/android/gms/internal/zzaue:zzga	(Ljava/lang/String;)I
    //   48: istore 5
    //   50: iload 5
    //   52: ifeq +53 -> 105
    //   55: aload_0
    //   56: invokevirtual 228	com/google/android/gms/internal/zzatp:zzJp	()Lcom/google/android/gms/internal/zzaue;
    //   59: aload_1
    //   60: getfield 1912	com/google/android/gms/internal/zzaub:name	Ljava/lang/String;
    //   63: aload_0
    //   64: invokevirtual 148	com/google/android/gms/internal/zzatp:zzJv	()Lcom/google/android/gms/internal/zzast;
    //   67: invokevirtual 1918	com/google/android/gms/internal/zzast:zzJV	()I
    //   70: iconst_1
    //   71: invokevirtual 1921	com/google/android/gms/internal/zzaue:zza	(Ljava/lang/String;IZ)Ljava/lang/String;
    //   74: astore_2
    //   75: aload_1
    //   76: getfield 1912	com/google/android/gms/internal/zzaub:name	Ljava/lang/String;
    //   79: ifnull +11 -> 90
    //   82: aload_1
    //   83: getfield 1912	com/google/android/gms/internal/zzaub:name	Ljava/lang/String;
    //   86: invokevirtual 406	java/lang/String:length	()I
    //   89: istore_3
    //   90: aload_0
    //   91: invokevirtual 228	com/google/android/gms/internal/zzatp:zzJp	()Lcom/google/android/gms/internal/zzaue;
    //   94: iload 5
    //   96: ldc_w 841
    //   99: aload_2
    //   100: iload_3
    //   101: invokevirtual 844	com/google/android/gms/internal/zzaue:zza	(ILjava/lang/String;Ljava/lang/String;I)V
    //   104: return
    //   105: aload_0
    //   106: invokevirtual 228	com/google/android/gms/internal/zzatp:zzJp	()Lcom/google/android/gms/internal/zzaue;
    //   109: aload_1
    //   110: getfield 1912	com/google/android/gms/internal/zzaub:name	Ljava/lang/String;
    //   113: aload_1
    //   114: invokevirtual 1924	com/google/android/gms/internal/zzaub:getValue	()Ljava/lang/Object;
    //   117: invokevirtual 1927	com/google/android/gms/internal/zzaue:zzm	(Ljava/lang/String;Ljava/lang/Object;)I
    //   120: istore 5
    //   122: iload 5
    //   124: ifeq +75 -> 199
    //   127: aload_0
    //   128: invokevirtual 228	com/google/android/gms/internal/zzatp:zzJp	()Lcom/google/android/gms/internal/zzaue;
    //   131: aload_1
    //   132: getfield 1912	com/google/android/gms/internal/zzaub:name	Ljava/lang/String;
    //   135: aload_0
    //   136: invokevirtual 148	com/google/android/gms/internal/zzatp:zzJv	()Lcom/google/android/gms/internal/zzast;
    //   139: invokevirtual 1918	com/google/android/gms/internal/zzast:zzJV	()I
    //   142: iconst_1
    //   143: invokevirtual 1921	com/google/android/gms/internal/zzaue:zza	(Ljava/lang/String;IZ)Ljava/lang/String;
    //   146: astore_2
    //   147: aload_1
    //   148: invokevirtual 1924	com/google/android/gms/internal/zzaub:getValue	()Ljava/lang/Object;
    //   151: astore_1
    //   152: iload 4
    //   154: istore_3
    //   155: aload_1
    //   156: ifnull +28 -> 184
    //   159: aload_1
    //   160: instanceof 399
    //   163: ifne +13 -> 176
    //   166: iload 4
    //   168: istore_3
    //   169: aload_1
    //   170: instanceof 1448
    //   173: ifeq +11 -> 184
    //   176: aload_1
    //   177: invokestatic 402	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   180: invokevirtual 406	java/lang/String:length	()I
    //   183: istore_3
    //   184: aload_0
    //   185: invokevirtual 228	com/google/android/gms/internal/zzatp:zzJp	()Lcom/google/android/gms/internal/zzaue;
    //   188: iload 5
    //   190: ldc_w 841
    //   193: aload_2
    //   194: iload_3
    //   195: invokevirtual 844	com/google/android/gms/internal/zzaue:zza	(ILjava/lang/String;Ljava/lang/String;I)V
    //   198: return
    //   199: aload_0
    //   200: invokevirtual 228	com/google/android/gms/internal/zzatp:zzJp	()Lcom/google/android/gms/internal/zzaue;
    //   203: aload_1
    //   204: getfield 1912	com/google/android/gms/internal/zzaub:name	Ljava/lang/String;
    //   207: aload_1
    //   208: invokevirtual 1924	com/google/android/gms/internal/zzaub:getValue	()Ljava/lang/Object;
    //   211: invokevirtual 1930	com/google/android/gms/internal/zzaue:zzn	(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    //   214: astore 7
    //   216: aload 7
    //   218: ifnull -195 -> 23
    //   221: new 1496	com/google/android/gms/internal/zzaud
    //   224: dup
    //   225: aload_2
    //   226: getfield 674	com/google/android/gms/internal/zzasq:packageName	Ljava/lang/String;
    //   229: aload_1
    //   230: getfield 1912	com/google/android/gms/internal/zzaub:name	Ljava/lang/String;
    //   233: aload_1
    //   234: getfield 1933	com/google/android/gms/internal/zzaub:zzbuZ	J
    //   237: aload 7
    //   239: invokespecial 1829	com/google/android/gms/internal/zzaud:<init>	(Ljava/lang/String;Ljava/lang/String;JLjava/lang/Object;)V
    //   242: astore_1
    //   243: aload_0
    //   244: invokevirtual 138	com/google/android/gms/internal/zzatp:zzJt	()Lcom/google/android/gms/internal/zzati;
    //   247: invokevirtual 175	com/google/android/gms/internal/zzati:zzLf	()Lcom/google/android/gms/internal/zzati$zza;
    //   250: ldc_w 1935
    //   253: aload_1
    //   254: getfield 1497	com/google/android/gms/internal/zzaud:mName	Ljava/lang/String;
    //   257: aload 7
    //   259: invokevirtual 369	com/google/android/gms/internal/zzati$zza:zze	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   262: aload_0
    //   263: invokevirtual 433	com/google/android/gms/internal/zzatp:zzJo	()Lcom/google/android/gms/internal/zzasu;
    //   266: invokevirtual 785	com/google/android/gms/internal/zzasu:beginTransaction	()V
    //   269: aload_0
    //   270: aload_2
    //   271: invokespecial 1758	com/google/android/gms/internal/zzatp:zzf	(Lcom/google/android/gms/internal/zzasq;)V
    //   274: aload_0
    //   275: invokevirtual 433	com/google/android/gms/internal/zzatp:zzJo	()Lcom/google/android/gms/internal/zzasu;
    //   278: aload_1
    //   279: invokevirtual 1832	com/google/android/gms/internal/zzasu:zza	(Lcom/google/android/gms/internal/zzaud;)Z
    //   282: istore 6
    //   284: aload_0
    //   285: invokevirtual 433	com/google/android/gms/internal/zzatp:zzJo	()Lcom/google/android/gms/internal/zzasu;
    //   288: invokevirtual 944	com/google/android/gms/internal/zzasu:setTransactionSuccessful	()V
    //   291: iload 6
    //   293: ifeq +32 -> 325
    //   296: aload_0
    //   297: invokevirtual 138	com/google/android/gms/internal/zzatp:zzJt	()Lcom/google/android/gms/internal/zzati;
    //   300: invokevirtual 175	com/google/android/gms/internal/zzati:zzLf	()Lcom/google/android/gms/internal/zzati$zza;
    //   303: ldc_w 1937
    //   306: aload_1
    //   307: getfield 1497	com/google/android/gms/internal/zzaud:mName	Ljava/lang/String;
    //   310: aload_1
    //   311: getfield 1507	com/google/android/gms/internal/zzaud:zzYe	Ljava/lang/Object;
    //   314: invokevirtual 369	com/google/android/gms/internal/zzati$zza:zze	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   317: aload_0
    //   318: invokevirtual 433	com/google/android/gms/internal/zzatp:zzJo	()Lcom/google/android/gms/internal/zzasu;
    //   321: invokevirtual 947	com/google/android/gms/internal/zzasu:endTransaction	()V
    //   324: return
    //   325: aload_0
    //   326: invokevirtual 138	com/google/android/gms/internal/zzatp:zzJt	()Lcom/google/android/gms/internal/zzati;
    //   329: invokevirtual 364	com/google/android/gms/internal/zzati:zzLa	()Lcom/google/android/gms/internal/zzati$zza;
    //   332: ldc_w 1939
    //   335: aload_1
    //   336: getfield 1497	com/google/android/gms/internal/zzaud:mName	Ljava/lang/String;
    //   339: aload_1
    //   340: getfield 1507	com/google/android/gms/internal/zzaud:zzYe	Ljava/lang/Object;
    //   343: invokevirtual 369	com/google/android/gms/internal/zzati$zza:zze	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   346: aload_0
    //   347: invokevirtual 228	com/google/android/gms/internal/zzatp:zzJp	()Lcom/google/android/gms/internal/zzaue;
    //   350: bipush 9
    //   352: aconst_null
    //   353: aconst_null
    //   354: iconst_0
    //   355: invokevirtual 844	com/google/android/gms/internal/zzaue:zza	(ILjava/lang/String;Ljava/lang/String;I)V
    //   358: goto -41 -> 317
    //   361: astore_1
    //   362: aload_0
    //   363: invokevirtual 433	com/google/android/gms/internal/zzatp:zzJo	()Lcom/google/android/gms/internal/zzasu;
    //   366: invokevirtual 947	com/google/android/gms/internal/zzasu:endTransaction	()V
    //   369: aload_1
    //   370: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	371	0	this	zzatp
    //   0	371	1	paramzzaub	zzaub
    //   0	371	2	paramzzasq	zzasq
    //   4	191	3	i	int
    //   1	166	4	j	int
    //   48	141	5	k	int
    //   282	10	6	bool	boolean
    //   214	44	7	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   269	291	361	finally
    //   296	317	361	finally
    //   325	358	361	finally
  }
  
  @WorkerThread
  void zzb(String paramString, int paramInt, Throwable paramThrowable, byte[] paramArrayOfByte, Map<String, List<String>> paramMap)
  {
    int j = 0;
    zzmq();
    zznA();
    zzac.zzdv(paramString);
    byte[] arrayOfByte = paramArrayOfByte;
    if (paramArrayOfByte == null) {
      arrayOfByte = new byte[0];
    }
    zzJo().beginTransaction();
    label93:
    label108:
    int i;
    for (;;)
    {
      try
      {
        paramArrayOfByte = zzJo().zzfy(paramString);
        if ((paramInt == 200) || (paramInt == 204)) {
          break label478;
        }
        if (paramInt == 304)
        {
          break label478;
          if (paramArrayOfByte == null)
          {
            zzJt().zzLc().zzj("App does not exist in onConfigFetched. appId", zzati.zzfI(paramString));
            zzJo().setTransactionSuccessful();
          }
        }
        else
        {
          i = 0;
          continue;
        }
        if ((i == 0) && (paramInt != 404)) {
          break;
        }
        if (paramMap != null)
        {
          paramThrowable = (List)paramMap.get("Last-Modified");
          if ((paramThrowable != null) && (paramThrowable.size() > 0))
          {
            paramThrowable = (String)paramThrowable.get(0);
            break label488;
            label172:
            if (zzJq().zzfO(paramString) != null) {
              continue;
            }
            bool = zzJq().zzb(paramString, null, null);
            if (bool) {
              continue;
            }
          }
        }
        else
        {
          paramThrowable = null;
          continue;
        }
        paramThrowable = null;
        break label488;
        label218:
        boolean bool = zzJq().zzb(paramString, arrayOfByte, paramThrowable);
        if (!bool) {
          return;
        }
        paramArrayOfByte.zzad(zznq().currentTimeMillis());
        zzJo().zza(paramArrayOfByte);
        if (paramInt == 404)
        {
          zzJt().zzLc().zzj("Config not found. Using empty config. appId", zzati.zzfI(paramString));
          if ((!zzLy().zzpA()) || (!zzLH())) {
            break label351;
          }
          zzLG();
          continue;
        }
        zzJt().zzLg().zze("Successfully fetched config. Got network response. code, size", Integer.valueOf(paramInt), Integer.valueOf(arrayOfByte.length));
      }
      finally
      {
        zzJo().endTransaction();
      }
      continue;
      label351:
      zzLI();
    }
    paramArrayOfByte.zzae(zznq().currentTimeMillis());
    zzJo().zza(paramArrayOfByte);
    zzJt().zzLg().zze("Fetching config failed. code, error", Integer.valueOf(paramInt), paramThrowable);
    zzJq().zzfQ(paramString);
    zzJu().zzbsh.set(zznq().currentTimeMillis());
    if (paramInt != 503)
    {
      i = j;
      if (paramInt == 429) {}
    }
    for (;;)
    {
      if (i != 0) {
        zzJu().zzbsi.set(zznq().currentTimeMillis());
      }
      zzLI();
      break label93;
      label478:
      if (paramThrowable != null) {
        break label108;
      }
      i = 1;
      break;
      label488:
      if (paramInt == 404) {
        break label172;
      }
      if (paramInt != 304) {
        break label218;
      }
      break label172;
      i = 1;
    }
  }
  
  @WorkerThread
  void zzc(zzasq paramzzasq, long paramLong)
  {
    Bundle localBundle = new Bundle();
    localBundle.putLong("_et", 1L);
    zzb(new zzatb("_e", new zzasz(localBundle), "auto", paramLong), paramzzasq);
  }
  
  @WorkerThread
  void zzc(zzaub paramzzaub, zzasq paramzzasq)
  {
    zzmq();
    zznA();
    if (TextUtils.isEmpty(paramzzasq.zzbqf)) {
      return;
    }
    if (!paramzzasq.zzbqk)
    {
      zzf(paramzzasq);
      return;
    }
    zzJt().zzLf().zzj("Removing user property", paramzzaub.name);
    zzJo().beginTransaction();
    try
    {
      zzf(paramzzasq);
      zzJo().zzQ(paramzzasq.packageName, paramzzaub.name);
      zzJo().setTransactionSuccessful();
      zzJt().zzLf().zzj("User property removed", paramzzaub.name);
      return;
    }
    finally
    {
      zzJo().endTransaction();
    }
  }
  
  void zzd(zzasq paramzzasq)
  {
    zzmq();
    zznA();
    zzac.zzdv(paramzzasq.packageName);
    zzf(paramzzasq);
  }
  
  @WorkerThread
  void zzd(zzasq paramzzasq, long paramLong)
  {
    zzb(new zzatb("_cd", new zzasz(new Bundle()), "auto", paramLong), paramzzasq);
  }
  
  /* Error */
  @WorkerThread
  public void zze(zzasq paramzzasq)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 426	com/google/android/gms/internal/zzatp:zzmq	()V
    //   4: aload_0
    //   5: invokevirtual 429	com/google/android/gms/internal/zzatp:zznA	()V
    //   8: aload_1
    //   9: invokestatic 94	com/google/android/gms/common/internal/zzac:zzw	(Ljava/lang/Object;)Ljava/lang/Object;
    //   12: pop
    //   13: aload_1
    //   14: getfield 674	com/google/android/gms/internal/zzasq:packageName	Ljava/lang/String;
    //   17: invokestatic 650	com/google/android/gms/common/internal/zzac:zzdv	(Ljava/lang/String;)Ljava/lang/String;
    //   20: pop
    //   21: aload_1
    //   22: getfield 698	com/google/android/gms/internal/zzasq:zzbqf	Ljava/lang/String;
    //   25: invokestatic 445	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   28: ifeq +4 -> 32
    //   31: return
    //   32: aload_1
    //   33: getfield 759	com/google/android/gms/internal/zzasq:zzbqk	Z
    //   36: ifne +9 -> 45
    //   39: aload_0
    //   40: aload_1
    //   41: invokespecial 1758	com/google/android/gms/internal/zzatp:zzf	(Lcom/google/android/gms/internal/zzasq;)V
    //   44: return
    //   45: aload_0
    //   46: invokevirtual 513	com/google/android/gms/internal/zzatp:zznq	()Lcom/google/android/gms/common/util/zze;
    //   49: invokeinterface 518 1 0
    //   54: lstore_2
    //   55: aload_0
    //   56: invokevirtual 433	com/google/android/gms/internal/zzatp:zzJo	()Lcom/google/android/gms/internal/zzasu;
    //   59: invokevirtual 785	com/google/android/gms/internal/zzasu:beginTransaction	()V
    //   62: aload_0
    //   63: aload_1
    //   64: lload_2
    //   65: invokevirtual 1979	com/google/android/gms/internal/zzatp:zza	(Lcom/google/android/gms/internal/zzasq;J)V
    //   68: aload_0
    //   69: aload_1
    //   70: invokespecial 1758	com/google/android/gms/internal/zzatp:zzf	(Lcom/google/android/gms/internal/zzasq;)V
    //   73: aload_0
    //   74: invokevirtual 433	com/google/android/gms/internal/zzatp:zzJo	()Lcom/google/android/gms/internal/zzasu;
    //   77: aload_1
    //   78: getfield 674	com/google/android/gms/internal/zzasq:packageName	Ljava/lang/String;
    //   81: ldc_w 1721
    //   84: invokevirtual 1606	com/google/android/gms/internal/zzasu:zzP	(Ljava/lang/String;Ljava/lang/String;)Lcom/google/android/gms/internal/zzasy;
    //   87: ifnonnull +63 -> 150
    //   90: aload_0
    //   91: new 1911	com/google/android/gms/internal/zzaub
    //   94: dup
    //   95: ldc_w 1981
    //   98: lload_2
    //   99: lconst_1
    //   100: lload_2
    //   101: ldc2_w 1982
    //   104: ldiv
    //   105: ladd
    //   106: ldc2_w 1982
    //   109: lmul
    //   110: invokestatic 160	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   113: ldc_w 1414
    //   116: invokespecial 1986	com/google/android/gms/internal/zzaub:<init>	(Ljava/lang/String;JLjava/lang/Object;Ljava/lang/String;)V
    //   119: aload_1
    //   120: invokevirtual 1988	com/google/android/gms/internal/zzatp:zzb	(Lcom/google/android/gms/internal/zzaub;Lcom/google/android/gms/internal/zzasq;)V
    //   123: aload_0
    //   124: aload_1
    //   125: lload_2
    //   126: invokevirtual 1990	com/google/android/gms/internal/zzatp:zzb	(Lcom/google/android/gms/internal/zzasq;J)V
    //   129: aload_0
    //   130: aload_1
    //   131: lload_2
    //   132: invokevirtual 1992	com/google/android/gms/internal/zzatp:zzc	(Lcom/google/android/gms/internal/zzasq;J)V
    //   135: aload_0
    //   136: invokevirtual 433	com/google/android/gms/internal/zzatp:zzJo	()Lcom/google/android/gms/internal/zzasu;
    //   139: invokevirtual 944	com/google/android/gms/internal/zzasu:setTransactionSuccessful	()V
    //   142: aload_0
    //   143: invokevirtual 433	com/google/android/gms/internal/zzatp:zzJo	()Lcom/google/android/gms/internal/zzasu;
    //   146: invokevirtual 947	com/google/android/gms/internal/zzasu:endTransaction	()V
    //   149: return
    //   150: aload_1
    //   151: getfield 1995	com/google/android/gms/internal/zzasq:zzbql	Z
    //   154: ifeq -19 -> 135
    //   157: aload_0
    //   158: aload_1
    //   159: lload_2
    //   160: invokevirtual 1997	com/google/android/gms/internal/zzatp:zzd	(Lcom/google/android/gms/internal/zzasq;J)V
    //   163: goto -28 -> 135
    //   166: astore_1
    //   167: aload_0
    //   168: invokevirtual 433	com/google/android/gms/internal/zzatp:zzJo	()Lcom/google/android/gms/internal/zzasu;
    //   171: invokevirtual 947	com/google/android/gms/internal/zzasu:endTransaction	()V
    //   174: aload_1
    //   175: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	176	0	this	zzatp
    //   0	176	1	paramzzasq	zzasq
    //   54	106	2	l	long
    // Exception table:
    //   from	to	target	type
    //   62	135	166	finally
    //   135	142	166	finally
    //   150	163	166	finally
  }
  
  public String zzfR(final String paramString)
  {
    Object localObject = zzJs().zzd(new Callable()
    {
      public String zzou()
        throws Exception
      {
        zzasp localzzasp = zzatp.this.zzJo().zzfy(paramString);
        if (localzzasp == null) {
          return null;
        }
        return localzzasp.getAppInstanceId();
      }
    });
    try
    {
      localObject = (String)((Future)localObject).get(30000L, TimeUnit.MILLISECONDS);
      return (String)localObject;
    }
    catch (InterruptedException localInterruptedException)
    {
      zzJt().zzLa().zze("Failed to get app instance id. appId", zzati.zzfI(paramString), localInterruptedException);
      return null;
    }
    catch (ExecutionException localExecutionException)
    {
      for (;;) {}
    }
    catch (TimeoutException localTimeoutException)
    {
      for (;;) {}
    }
  }
  
  @WorkerThread
  public void zzmq()
  {
    zzJs().zzmq();
  }
  
  void zznA()
  {
    if (!this.zzacO) {
      throw new IllegalStateException("AppMeasurement is not initialized");
    }
  }
  
  public zze zznq()
  {
    return this.zzuI;
  }
  
  @WorkerThread
  boolean zzv(int paramInt1, int paramInt2)
  {
    zzmq();
    if (paramInt1 > paramInt2)
    {
      zzJt().zzLa().zze("Panic: can't downgrade version. Previous, current version", Integer.valueOf(paramInt1), Integer.valueOf(paramInt2));
      return false;
    }
    if (paramInt1 < paramInt2)
    {
      if (zza(paramInt2, zzLB())) {
        zzJt().zzLg().zze("Storage version upgraded. Previous, current version", Integer.valueOf(paramInt1), Integer.valueOf(paramInt2));
      }
    }
    else {
      return true;
    }
    zzJt().zzLa().zze("Storage version upgrade failed. Previous, current version", Integer.valueOf(paramInt1), Integer.valueOf(paramInt2));
    return false;
  }
  
  private class zza
    implements zzasu.zzb
  {
    zzauh.zze zzbtI;
    List<Long> zzbtJ;
    long zzbtK;
    List<zzauh.zzb> zztf;
    
    private zza() {}
    
    private long zza(zzauh.zzb paramzzb)
    {
      return paramzzb.zzbvW.longValue() / 1000L / 60L / 60L;
    }
    
    boolean isEmpty()
    {
      return (this.zztf == null) || (this.zztf.isEmpty());
    }
    
    public boolean zza(long paramLong, zzauh.zzb paramzzb)
    {
      zzac.zzw(paramzzb);
      if (this.zztf == null) {
        this.zztf = new ArrayList();
      }
      if (this.zzbtJ == null) {
        this.zzbtJ = new ArrayList();
      }
      if ((this.zztf.size() > 0) && (zza((zzauh.zzb)this.zztf.get(0)) != zza(paramzzb))) {
        return false;
      }
      long l = this.zzbtK + paramzzb.zzacZ();
      if (l >= zzatp.this.zzJv().zzKr()) {
        return false;
      }
      this.zzbtK = l;
      this.zztf.add(paramzzb);
      this.zzbtJ.add(Long.valueOf(paramLong));
      return this.zztf.size() < zzatp.this.zzJv().zzKs();
    }
    
    public void zzb(zzauh.zze paramzze)
    {
      zzac.zzw(paramzze);
      this.zzbtI = paramzze;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */