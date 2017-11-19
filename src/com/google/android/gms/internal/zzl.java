package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class zzl
{
  private AtomicInteger zzW = new AtomicInteger();
  private final Map<String, Queue<zzk<?>>> zzX = new HashMap();
  private final Set<zzk<?>> zzY = new HashSet();
  private final PriorityBlockingQueue<zzk<?>> zzZ = new PriorityBlockingQueue();
  private final PriorityBlockingQueue<zzk<?>> zzaa = new PriorityBlockingQueue();
  private zzg[] zzab;
  private zzc zzac;
  private List<Object> zzad = new ArrayList();
  private final zzb zzi;
  private final zzn zzj;
  private final zzf zzx;
  
  public zzl(zzb paramzzb, zzf paramzzf)
  {
    this(paramzzb, paramzzf, 4);
  }
  
  public zzl(zzb paramzzb, zzf paramzzf, int paramInt)
  {
    this(paramzzb, paramzzf, paramInt, new zze(new Handler(Looper.getMainLooper())));
  }
  
  public zzl(zzb paramzzb, zzf paramzzf, int paramInt, zzn paramzzn)
  {
    this.zzi = paramzzb;
    this.zzx = paramzzf;
    this.zzab = new zzg[paramInt];
    this.zzj = paramzzn;
  }
  
  public int getSequenceNumber()
  {
    return this.zzW.incrementAndGet();
  }
  
  public void start()
  {
    stop();
    this.zzac = new zzc(this.zzZ, this.zzaa, this.zzi, this.zzj);
    this.zzac.start();
    int i = 0;
    while (i < this.zzab.length)
    {
      zzg localzzg = new zzg(this.zzaa, this.zzx, this.zzi, this.zzj);
      this.zzab[i] = localzzg;
      localzzg.start();
      i += 1;
    }
  }
  
  public void stop()
  {
    if (this.zzac != null) {
      this.zzac.quit();
    }
    int i = 0;
    while (i < this.zzab.length)
    {
      if (this.zzab[i] != null) {
        this.zzab[i].quit();
      }
      i += 1;
    }
  }
  
  public <T> zzk<T> zze(zzk<T> paramzzk)
  {
    paramzzk.zza(this);
    synchronized (this.zzY)
    {
      this.zzY.add(paramzzk);
      paramzzk.zza(getSequenceNumber());
      paramzzk.zzc("add-to-queue");
      if (!paramzzk.zzn())
      {
        this.zzaa.add(paramzzk);
        return paramzzk;
      }
    }
    for (;;)
    {
      String str;
      synchronized (this.zzX)
      {
        str = paramzzk.zzg();
        if (this.zzX.containsKey(str))
        {
          Queue localQueue = (Queue)this.zzX.get(str);
          ??? = localQueue;
          if (localQueue == null) {
            ??? = new LinkedList();
          }
          ((Queue)???).add(paramzzk);
          this.zzX.put(str, ???);
          if (zzs.DEBUG) {
            zzs.zza("Request for cacheKey=%s is in flight, putting on hold.", new Object[] { str });
          }
          return paramzzk;
        }
      }
      this.zzX.put(str, null);
      this.zzZ.add(paramzzk);
    }
  }
  
  <T> void zzf(zzk<T> paramzzk)
  {
    Object localObject2;
    synchronized (this.zzY)
    {
      this.zzY.remove(paramzzk);
      synchronized (this.zzad)
      {
        localObject2 = this.zzad.iterator();
        if (((Iterator)localObject2).hasNext()) {
          ((Iterator)localObject2).next();
        }
      }
    }
    if (paramzzk.zzn()) {
      synchronized (this.zzX)
      {
        paramzzk = paramzzk.zzg();
        localObject2 = (Queue)this.zzX.remove(paramzzk);
        if (localObject2 != null)
        {
          if (zzs.DEBUG) {
            zzs.zza("Releasing %d waiting requests for cacheKey=%s.", new Object[] { Integer.valueOf(((Queue)localObject2).size()), paramzzk });
          }
          this.zzZ.addAll((Collection)localObject2);
        }
        return;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */