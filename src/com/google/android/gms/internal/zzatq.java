package com.google.android.gms.internal;

import android.content.Context;
import android.os.Binder;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzx;
import com.google.android.gms.common.zzf;
import com.google.android.gms.measurement.AppMeasurement.zzf;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class zzatq
  extends zzate.zza
{
  private final zzatp zzbpw;
  private Boolean zzbtL;
  @Nullable
  private String zzbtM;
  
  public zzatq(zzatp paramzzatp)
  {
    this(paramzzatp, null);
  }
  
  public zzatq(zzatp paramzzatp, @Nullable String paramString)
  {
    zzac.zzw(paramzzatp);
    this.zzbpw = paramzzatp;
    this.zzbtM = paramString;
  }
  
  @BinderThread
  private void zzb(zzasq paramzzasq, boolean paramBoolean)
  {
    zzac.zzw(paramzzasq);
    zzm(paramzzasq.packageName, paramBoolean);
    this.zzbpw.zzJp().zzgd(paramzzasq.zzbqf);
  }
  
  @BinderThread
  private void zzm(String paramString, boolean paramBoolean)
  {
    if (TextUtils.isEmpty(paramString))
    {
      this.zzbpw.zzJt().zzLa().log("Measurement Service called without app package");
      throw new SecurityException("Measurement Service called without app package");
    }
    try
    {
      zzn(paramString, paramBoolean);
      return;
    }
    catch (SecurityException localSecurityException)
    {
      this.zzbpw.zzJt().zzLa().zzj("Measurement Service called with invalid calling package. appId", zzati.zzfI(paramString));
      throw localSecurityException;
    }
  }
  
  /* Error */
  @BinderThread
  public List<zzaub> zza(final zzasq paramzzasq, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: iconst_0
    //   3: invokespecial 127	com/google/android/gms/internal/zzatq:zzb	(Lcom/google/android/gms/internal/zzasq;Z)V
    //   6: aload_0
    //   7: getfield 46	com/google/android/gms/internal/zzatq:zzbpw	Lcom/google/android/gms/internal/zzatp;
    //   10: invokevirtual 131	com/google/android/gms/internal/zzatp:zzJs	()Lcom/google/android/gms/internal/zzato;
    //   13: new 18	com/google/android/gms/internal/zzatq$7
    //   16: dup
    //   17: aload_0
    //   18: aload_1
    //   19: invokespecial 134	com/google/android/gms/internal/zzatq$7:<init>	(Lcom/google/android/gms/internal/zzatq;Lcom/google/android/gms/internal/zzasq;)V
    //   22: invokevirtual 140	com/google/android/gms/internal/zzato:zzd	(Ljava/util/concurrent/Callable;)Ljava/util/concurrent/Future;
    //   25: astore_3
    //   26: aload_3
    //   27: invokeinterface 146 1 0
    //   32: checkcast 148	java/util/List
    //   35: astore 4
    //   37: new 150	java/util/ArrayList
    //   40: dup
    //   41: aload 4
    //   43: invokeinterface 154 1 0
    //   48: invokespecial 157	java/util/ArrayList:<init>	(I)V
    //   51: astore_3
    //   52: aload 4
    //   54: invokeinterface 161 1 0
    //   59: astore 4
    //   61: aload 4
    //   63: invokeinterface 167 1 0
    //   68: ifeq +75 -> 143
    //   71: aload 4
    //   73: invokeinterface 170 1 0
    //   78: checkcast 172	com/google/android/gms/internal/zzaud
    //   81: astore 5
    //   83: iload_2
    //   84: ifne +14 -> 98
    //   87: aload 5
    //   89: getfield 175	com/google/android/gms/internal/zzaud:mName	Ljava/lang/String;
    //   92: invokestatic 178	com/google/android/gms/internal/zzaue:zzgg	(Ljava/lang/String;)Z
    //   95: ifne -34 -> 61
    //   98: aload_3
    //   99: new 180	com/google/android/gms/internal/zzaub
    //   102: dup
    //   103: aload 5
    //   105: invokespecial 183	com/google/android/gms/internal/zzaub:<init>	(Lcom/google/android/gms/internal/zzaud;)V
    //   108: invokeinterface 187 2 0
    //   113: pop
    //   114: goto -53 -> 61
    //   117: astore_3
    //   118: aload_0
    //   119: getfield 46	com/google/android/gms/internal/zzatq:zzbpw	Lcom/google/android/gms/internal/zzatp;
    //   122: invokevirtual 91	com/google/android/gms/internal/zzatp:zzJt	()Lcom/google/android/gms/internal/zzati;
    //   125: invokevirtual 97	com/google/android/gms/internal/zzati:zzLa	()Lcom/google/android/gms/internal/zzati$zza;
    //   128: ldc -67
    //   130: aload_1
    //   131: getfield 59	com/google/android/gms/internal/zzasq:packageName	Ljava/lang/String;
    //   134: invokestatic 116	com/google/android/gms/internal/zzati:zzfI	(Ljava/lang/String;)Ljava/lang/Object;
    //   137: aload_3
    //   138: invokevirtual 193	com/google/android/gms/internal/zzati$zza:zze	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   141: aconst_null
    //   142: areturn
    //   143: aload_3
    //   144: areturn
    //   145: astore_3
    //   146: goto -28 -> 118
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	149	0	this	zzatq
    //   0	149	1	paramzzasq	zzasq
    //   0	149	2	paramBoolean	boolean
    //   25	74	3	localObject1	Object
    //   117	27	3	localInterruptedException	InterruptedException
    //   145	1	3	localExecutionException	ExecutionException
    //   35	37	4	localObject2	Object
    //   81	23	5	localzzaud	zzaud
    // Exception table:
    //   from	to	target	type
    //   26	61	117	java/lang/InterruptedException
    //   61	83	117	java/lang/InterruptedException
    //   87	98	117	java/lang/InterruptedException
    //   98	114	117	java/lang/InterruptedException
    //   26	61	145	java/util/concurrent/ExecutionException
    //   61	83	145	java/util/concurrent/ExecutionException
    //   87	98	145	java/util/concurrent/ExecutionException
    //   98	114	145	java/util/concurrent/ExecutionException
  }
  
  @BinderThread
  public void zza(final long paramLong, final String paramString1, final String paramString2, final String paramString3)
  {
    this.zzbpw.zzJs().zzm(new Runnable()
    {
      public void run()
      {
        if (paramString2 == null)
        {
          zzatq.zza(zzatq.this).zzJm().zza(paramString3, null);
          return;
        }
        AppMeasurement.zzf localzzf = new AppMeasurement.zzf();
        localzzf.zzbpz = paramString1;
        localzzf.zzbpA = paramString2;
        localzzf.zzbpB = paramLong;
        zzatq.zza(zzatq.this).zzJm().zza(paramString3, localzzf);
      }
    });
  }
  
  @BinderThread
  public void zza(final zzasq paramzzasq)
  {
    zzb(paramzzasq, false);
    this.zzbpw.zzJs().zzm(new Runnable()
    {
      public void run()
      {
        zzatq.zza(zzatq.this).zzLL();
        zzatq.zza(zzatq.this).zze(paramzzasq);
      }
    });
  }
  
  @BinderThread
  public void zza(final zzatb paramzzatb, final zzasq paramzzasq)
  {
    zzac.zzw(paramzzatb);
    zzb(paramzzasq, false);
    this.zzbpw.zzJs().zzm(new Runnable()
    {
      public void run()
      {
        zzatq.zza(zzatq.this).zzLL();
        zzatq.zza(zzatq.this).zzb(paramzzatb, paramzzasq);
      }
    });
  }
  
  @BinderThread
  public void zza(final zzatb paramzzatb, final String paramString1, String paramString2)
  {
    zzac.zzw(paramzzatb);
    zzac.zzdv(paramString1);
    zzm(paramString1, true);
    this.zzbpw.zzJs().zzm(new Runnable()
    {
      public void run()
      {
        zzatq.zza(zzatq.this).zzLL();
        zzatq.zza(zzatq.this).zzb(paramzzatb, paramString1);
      }
    });
  }
  
  @BinderThread
  public void zza(final zzaub paramzzaub, final zzasq paramzzasq)
  {
    zzac.zzw(paramzzaub);
    zzb(paramzzasq, false);
    if (paramzzaub.getValue() == null)
    {
      this.zzbpw.zzJs().zzm(new Runnable()
      {
        public void run()
        {
          zzatq.zza(zzatq.this).zzLL();
          zzatq.zza(zzatq.this).zzc(paramzzaub, paramzzasq);
        }
      });
      return;
    }
    this.zzbpw.zzJs().zzm(new Runnable()
    {
      public void run()
      {
        zzatq.zza(zzatq.this).zzLL();
        zzatq.zza(zzatq.this).zzb(paramzzaub, paramzzasq);
      }
    });
  }
  
  @BinderThread
  public byte[] zza(final zzatb paramzzatb, final String paramString)
  {
    zzac.zzdv(paramString);
    zzac.zzw(paramzzatb);
    zzm(paramString, true);
    this.zzbpw.zzJt().zzLf().zzj("Log and bundle. event", paramzzatb.name);
    long l1 = this.zzbpw.zznq().nanoTime() / 1000000L;
    Object localObject = this.zzbpw.zzJs().zze(new Callable()
    {
      public byte[] zzLN()
        throws Exception
      {
        zzatq.zza(zzatq.this).zzLL();
        return zzatq.zza(zzatq.this).zza(paramzzatb, paramString);
      }
    });
    try
    {
      byte[] arrayOfByte = (byte[])((Future)localObject).get();
      localObject = arrayOfByte;
      if (arrayOfByte == null)
      {
        this.zzbpw.zzJt().zzLa().zzj("Log and bundle returned null. appId", zzati.zzfI(paramString));
        localObject = new byte[0];
      }
      long l2 = this.zzbpw.zznq().nanoTime() / 1000000L;
      this.zzbpw.zzJt().zzLf().zzd("Log and bundle processed. event, size, time_ms", paramzzatb.name, Integer.valueOf(localObject.length), Long.valueOf(l2 - l1));
      return (byte[])localObject;
    }
    catch (InterruptedException localInterruptedException)
    {
      this.zzbpw.zzJt().zzLa().zzd("Failed to log and bundle. appId, event, error", zzati.zzfI(paramString), paramzzatb.name, localInterruptedException);
      return null;
    }
    catch (ExecutionException localExecutionException)
    {
      for (;;) {}
    }
  }
  
  @BinderThread
  public void zzb(final zzasq paramzzasq)
  {
    zzb(paramzzasq, false);
    this.zzbpw.zzJs().zzm(new Runnable()
    {
      public void run()
      {
        zzatq.zza(zzatq.this).zzLL();
        zzatq.zza(zzatq.this).zzd(paramzzasq);
      }
    });
  }
  
  @BinderThread
  public String zzc(zzasq paramzzasq)
  {
    zzb(paramzzasq, false);
    return this.zzbpw.zzfR(paramzzasq.packageName);
  }
  
  protected void zzn(String paramString, boolean paramBoolean)
    throws SecurityException
  {
    if (paramBoolean) {
      if (this.zzbtL == null)
      {
        if (("com.google.android.gms".equals(this.zzbtM)) || (zzx.zzf(this.zzbpw.getContext(), Binder.getCallingUid())) || (zzf.zzav(this.zzbpw.getContext()).zza(this.zzbpw.getContext().getPackageManager(), Binder.getCallingUid())))
        {
          paramBoolean = true;
          this.zzbtL = Boolean.valueOf(paramBoolean);
        }
      }
      else {
        if (!this.zzbtL.booleanValue()) {
          break label95;
        }
      }
    }
    label95:
    do
    {
      return;
      paramBoolean = false;
      break;
      if ((this.zzbtM == null) && (com.google.android.gms.common.zze.zzc(this.zzbpw.getContext(), Binder.getCallingUid(), paramString))) {
        this.zzbtM = paramString;
      }
    } while (paramString.equals(this.zzbtM));
    throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[] { paramString }));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */