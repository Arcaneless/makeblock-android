package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.measurement.AppMeasurement.zza;
import java.io.IOException;
import java.util.Map;

public class zzatn
  extends zzats
{
  private final Map<String, Map<String, String>> zzbsH = new ArrayMap();
  private final Map<String, Map<String, Boolean>> zzbsI = new ArrayMap();
  private final Map<String, Map<String, Boolean>> zzbsJ = new ArrayMap();
  private final Map<String, zzaug.zzb> zzbsK = new ArrayMap();
  private final Map<String, String> zzbsL = new ArrayMap();
  
  zzatn(zzatp paramzzatp)
  {
    super(paramzzatp);
  }
  
  private Map<String, String> zza(zzaug.zzb paramzzb)
  {
    ArrayMap localArrayMap = new ArrayMap();
    if ((paramzzb != null) && (paramzzb.zzbvM != null))
    {
      paramzzb = paramzzb.zzbvM;
      int j = paramzzb.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramzzb[i];
        if (localObject != null) {
          localArrayMap.put(((zzaug.zzc)localObject).zzaA, ((zzaug.zzc)localObject).value);
        }
        i += 1;
      }
    }
    return localArrayMap;
  }
  
  private void zza(String paramString, zzaug.zzb paramzzb)
  {
    ArrayMap localArrayMap1 = new ArrayMap();
    ArrayMap localArrayMap2 = new ArrayMap();
    if ((paramzzb != null) && (paramzzb.zzbvN != null))
    {
      paramzzb = paramzzb.zzbvN;
      int j = paramzzb.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramzzb[i];
        if (localObject != null)
        {
          String str = (String)AppMeasurement.zza.zzbpx.get(((zzaug.zza)localObject).name);
          if (str != null) {
            ((zzaug.zza)localObject).name = str;
          }
          localArrayMap1.put(((zzaug.zza)localObject).name, ((zzaug.zza)localObject).zzbvI);
          localArrayMap2.put(((zzaug.zza)localObject).name, ((zzaug.zza)localObject).zzbvJ);
        }
        i += 1;
      }
    }
    this.zzbsI.put(paramString, localArrayMap1);
    this.zzbsJ.put(paramString, localArrayMap2);
  }
  
  @WorkerThread
  private zzaug.zzb zze(String paramString, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return new zzaug.zzb();
    }
    paramArrayOfByte = zzbul.zzad(paramArrayOfByte);
    zzaug.zzb localzzb = new zzaug.zzb();
    try
    {
      localzzb.zzb(paramArrayOfByte);
      zzJt().zzLg().zze("Parsed config. version, gmp_app_id", localzzb.zzbvK, localzzb.zzbqf);
      return localzzb;
    }
    catch (IOException paramArrayOfByte)
    {
      zzJt().zzLc().zze("Unable to merge remote config. appId", zzati.zzfI(paramString), paramArrayOfByte);
    }
    return null;
  }
  
  @WorkerThread
  private void zzfN(String paramString)
  {
    zznA();
    zzmq();
    zzac.zzdv(paramString);
    if (!this.zzbsK.containsKey(paramString))
    {
      localObject = zzJo().zzfA(paramString);
      if (localObject == null)
      {
        this.zzbsH.put(paramString, null);
        this.zzbsI.put(paramString, null);
        this.zzbsJ.put(paramString, null);
        this.zzbsK.put(paramString, null);
        this.zzbsL.put(paramString, null);
      }
    }
    else
    {
      return;
    }
    Object localObject = zze(paramString, (byte[])localObject);
    this.zzbsH.put(paramString, zza((zzaug.zzb)localObject));
    zza(paramString, (zzaug.zzb)localObject);
    this.zzbsK.put(paramString, localObject);
    this.zzbsL.put(paramString, null);
  }
  
  @WorkerThread
  String zzW(String paramString1, String paramString2)
  {
    zzmq();
    zzfN(paramString1);
    paramString1 = (Map)this.zzbsH.get(paramString1);
    if (paramString1 != null) {
      return (String)paramString1.get(paramString2);
    }
    return null;
  }
  
  @WorkerThread
  boolean zzX(String paramString1, String paramString2)
  {
    zzmq();
    zzfN(paramString1);
    if ((zzJp().zzgj(paramString1)) && (zzaue.zzgg(paramString2))) {}
    while ((zzJp().zzgk(paramString1)) && (zzaue.zzfW(paramString2))) {
      return true;
    }
    paramString1 = (Map)this.zzbsI.get(paramString1);
    if (paramString1 != null)
    {
      paramString1 = (Boolean)paramString1.get(paramString2);
      if (paramString1 == null) {
        return false;
      }
      return paramString1.booleanValue();
    }
    return false;
  }
  
  @WorkerThread
  boolean zzY(String paramString1, String paramString2)
  {
    zzmq();
    zzfN(paramString1);
    paramString1 = (Map)this.zzbsJ.get(paramString1);
    if (paramString1 != null)
    {
      paramString1 = (Boolean)paramString1.get(paramString2);
      if (paramString1 == null) {
        return false;
      }
      return paramString1.booleanValue();
    }
    return false;
  }
  
  @WorkerThread
  protected boolean zzb(String paramString1, byte[] paramArrayOfByte, String paramString2)
  {
    zznA();
    zzmq();
    zzac.zzdv(paramString1);
    zzaug.zzb localzzb = zze(paramString1, paramArrayOfByte);
    if (localzzb == null) {
      return false;
    }
    zza(paramString1, localzzb);
    this.zzbsK.put(paramString1, localzzb);
    this.zzbsL.put(paramString1, paramString2);
    this.zzbsH.put(paramString1, zza(localzzb));
    zzJh().zza(paramString1, localzzb.zzbvO);
    try
    {
      localzzb.zzbvO = null;
      paramString2 = new byte[localzzb.zzacZ()];
      localzzb.zza(zzbum.zzae(paramString2));
      paramArrayOfByte = paramString2;
    }
    catch (IOException paramString2)
    {
      for (;;)
      {
        zzJt().zzLc().zze("Unable to serialize reduced-size config. Storing full config instead. appId", zzati.zzfI(paramString1), paramString2);
      }
    }
    zzJo().zzd(paramString1, paramArrayOfByte);
    return true;
  }
  
  @WorkerThread
  protected zzaug.zzb zzfO(String paramString)
  {
    zznA();
    zzmq();
    zzac.zzdv(paramString);
    zzfN(paramString);
    return (zzaug.zzb)this.zzbsK.get(paramString);
  }
  
  @WorkerThread
  protected String zzfP(String paramString)
  {
    zzmq();
    return (String)this.zzbsL.get(paramString);
  }
  
  @WorkerThread
  protected void zzfQ(String paramString)
  {
    zzmq();
    this.zzbsL.put(paramString, null);
  }
  
  protected void zzmr() {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */