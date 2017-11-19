package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.measurement.AppMeasurement.zza;
import com.google.android.gms.measurement.AppMeasurement.zzg;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.GZIPOutputStream;
import javax.security.auth.x500.X500Principal;

public class zzaue
  extends zzats
{
  private final AtomicLong zzbve = new AtomicLong(0L);
  private int zzbvf;
  
  zzaue(zzatp paramzzatp)
  {
    super(paramzzatp);
  }
  
  private Object zza(int paramInt, Object paramObject, boolean paramBoolean)
  {
    Object localObject;
    if (paramObject == null) {
      localObject = null;
    }
    do
    {
      do
      {
        return localObject;
        localObject = paramObject;
      } while ((paramObject instanceof Long));
      localObject = paramObject;
    } while ((paramObject instanceof Double));
    if ((paramObject instanceof Integer)) {
      return Long.valueOf(((Integer)paramObject).intValue());
    }
    if ((paramObject instanceof Byte)) {
      return Long.valueOf(((Byte)paramObject).byteValue());
    }
    if ((paramObject instanceof Short)) {
      return Long.valueOf(((Short)paramObject).shortValue());
    }
    if ((paramObject instanceof Boolean))
    {
      if (((Boolean)paramObject).booleanValue()) {}
      for (long l = 1L;; l = 0L) {
        return Long.valueOf(l);
      }
    }
    if ((paramObject instanceof Float)) {
      return Double.valueOf(((Float)paramObject).doubleValue());
    }
    if (((paramObject instanceof String)) || ((paramObject instanceof Character)) || ((paramObject instanceof CharSequence))) {
      return zza(String.valueOf(paramObject), paramInt, paramBoolean);
    }
    return null;
  }
  
  public static String zza(zzauf.zzb paramzzb)
  {
    int i = 0;
    if (paramzzb == null) {
      return "null";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\nevent_filter {\n");
    zza(localStringBuilder, 0, "filter_id", paramzzb.zzbvl);
    zza(localStringBuilder, 0, "event_name", paramzzb.zzbvm);
    zza(localStringBuilder, 1, "event_count_filter", paramzzb.zzbvp);
    localStringBuilder.append("  filters {\n");
    paramzzb = paramzzb.zzbvn;
    int j = paramzzb.length;
    while (i < j)
    {
      zza(localStringBuilder, 2, paramzzb[i]);
      i += 1;
    }
    zza(localStringBuilder, 1);
    localStringBuilder.append("}\n}\n");
    return localStringBuilder.toString();
  }
  
  public static String zza(zzauf.zze paramzze)
  {
    if (paramzze == null) {
      return "null";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\nproperty_filter {\n");
    zza(localStringBuilder, 0, "filter_id", paramzze.zzbvl);
    zza(localStringBuilder, 0, "property_name", paramzze.zzbvB);
    zza(localStringBuilder, 1, paramzze.zzbvC);
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt)
  {
    int i = 0;
    while (i < paramInt)
    {
      paramStringBuilder.append("  ");
      i += 1;
    }
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, zzauf.zzc paramzzc)
  {
    if (paramzzc == null) {
      return;
    }
    zza(paramStringBuilder, paramInt);
    paramStringBuilder.append("filter {\n");
    zza(paramStringBuilder, paramInt, "complement", paramzzc.zzbvt);
    zza(paramStringBuilder, paramInt, "param_name", paramzzc.zzbvu);
    zza(paramStringBuilder, paramInt + 1, "string_filter", paramzzc.zzbvr);
    zza(paramStringBuilder, paramInt + 1, "number_filter", paramzzc.zzbvs);
    zza(paramStringBuilder, paramInt);
    paramStringBuilder.append("}\n");
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, zzauh.zze paramzze)
  {
    if (paramzze == null) {
      return;
    }
    zza(paramStringBuilder, paramInt);
    paramStringBuilder.append("bundle {\n");
    zza(paramStringBuilder, paramInt, "protocol_version", paramzze.zzbwc);
    zza(paramStringBuilder, paramInt, "platform", paramzze.zzbwk);
    zza(paramStringBuilder, paramInt, "gmp_version", paramzze.zzbwo);
    zza(paramStringBuilder, paramInt, "uploading_gmp_version", paramzze.zzbwp);
    zza(paramStringBuilder, paramInt, "config_version", paramzze.zzbwB);
    zza(paramStringBuilder, paramInt, "gmp_app_id", paramzze.zzbqf);
    zza(paramStringBuilder, paramInt, "app_id", paramzze.zzaR);
    zza(paramStringBuilder, paramInt, "app_version", paramzze.zzbhg);
    zza(paramStringBuilder, paramInt, "app_version_major", paramzze.zzbwx);
    zza(paramStringBuilder, paramInt, "firebase_instance_id", paramzze.zzbqn);
    zza(paramStringBuilder, paramInt, "dev_cert_hash", paramzze.zzbwt);
    zza(paramStringBuilder, paramInt, "app_store", paramzze.zzbqg);
    zza(paramStringBuilder, paramInt, "upload_timestamp_millis", paramzze.zzbwf);
    zza(paramStringBuilder, paramInt, "start_timestamp_millis", paramzze.zzbwg);
    zza(paramStringBuilder, paramInt, "end_timestamp_millis", paramzze.zzbwh);
    zza(paramStringBuilder, paramInt, "previous_bundle_start_timestamp_millis", paramzze.zzbwi);
    zza(paramStringBuilder, paramInt, "previous_bundle_end_timestamp_millis", paramzze.zzbwj);
    zza(paramStringBuilder, paramInt, "app_instance_id", paramzze.zzbws);
    zza(paramStringBuilder, paramInt, "resettable_device_id", paramzze.zzbwq);
    zza(paramStringBuilder, paramInt, "device_id", paramzze.zzbwA);
    zza(paramStringBuilder, paramInt, "limited_ad_tracking", paramzze.zzbwr);
    zza(paramStringBuilder, paramInt, "os_version", paramzze.zzba);
    zza(paramStringBuilder, paramInt, "device_model", paramzze.zzbwl);
    zza(paramStringBuilder, paramInt, "user_default_language", paramzze.zzbwm);
    zza(paramStringBuilder, paramInt, "time_zone_offset_minutes", paramzze.zzbwn);
    zza(paramStringBuilder, paramInt, "bundle_sequential_index", paramzze.zzbwu);
    zza(paramStringBuilder, paramInt, "service_upload", paramzze.zzbwv);
    zza(paramStringBuilder, paramInt, "health_monitor", paramzze.zzbqj);
    zza(paramStringBuilder, paramInt, paramzze.zzbwe);
    zza(paramStringBuilder, paramInt, paramzze.zzbww);
    zza(paramStringBuilder, paramInt, paramzze.zzbwd);
    zza(paramStringBuilder, paramInt);
    paramStringBuilder.append("}\n");
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, String paramString, zzauf.zzd paramzzd)
  {
    if (paramzzd == null) {
      return;
    }
    zza(paramStringBuilder, paramInt);
    paramStringBuilder.append(paramString);
    paramStringBuilder.append(" {\n");
    if (paramzzd.zzbvv != null)
    {
      paramString = "UNKNOWN_COMPARISON_TYPE";
      switch (paramzzd.zzbvv.intValue())
      {
      }
    }
    for (;;)
    {
      zza(paramStringBuilder, paramInt, "comparison_type", paramString);
      zza(paramStringBuilder, paramInt, "match_as_float", paramzzd.zzbvw);
      zza(paramStringBuilder, paramInt, "comparison_value", paramzzd.zzbvx);
      zza(paramStringBuilder, paramInt, "min_comparison_value", paramzzd.zzbvy);
      zza(paramStringBuilder, paramInt, "max_comparison_value", paramzzd.zzbvz);
      zza(paramStringBuilder, paramInt);
      paramStringBuilder.append("}\n");
      return;
      paramString = "LESS_THAN";
      continue;
      paramString = "GREATER_THAN";
      continue;
      paramString = "EQUAL";
      continue;
      paramString = "BETWEEN";
    }
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, String paramString, zzauf.zzf paramzzf)
  {
    if (paramzzf == null) {
      return;
    }
    zza(paramStringBuilder, paramInt);
    paramStringBuilder.append(paramString);
    paramStringBuilder.append(" {\n");
    if (paramzzf.zzbvD != null)
    {
      paramString = "UNKNOWN_MATCH_TYPE";
      switch (paramzzf.zzbvD.intValue())
      {
      }
    }
    for (;;)
    {
      zza(paramStringBuilder, paramInt, "match_type", paramString);
      zza(paramStringBuilder, paramInt, "expression", paramzzf.zzbvE);
      zza(paramStringBuilder, paramInt, "case_sensitive", paramzzf.zzbvF);
      if (paramzzf.zzbvG.length <= 0) {
        break label239;
      }
      zza(paramStringBuilder, paramInt + 1);
      paramStringBuilder.append("expression_list {\n");
      paramString = paramzzf.zzbvG;
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        paramzzf = paramString[i];
        zza(paramStringBuilder, paramInt + 2);
        paramStringBuilder.append(paramzzf);
        paramStringBuilder.append("\n");
        i += 1;
      }
      paramString = "REGEXP";
      continue;
      paramString = "BEGINS_WITH";
      continue;
      paramString = "ENDS_WITH";
      continue;
      paramString = "PARTIAL";
      continue;
      paramString = "EXACT";
      continue;
      paramString = "IN_LIST";
    }
    paramStringBuilder.append("}\n");
    label239:
    zza(paramStringBuilder, paramInt);
    paramStringBuilder.append("}\n");
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, String paramString, zzauh.zzf paramzzf)
  {
    int j = 0;
    if (paramzzf == null) {
      return;
    }
    int k = paramInt + 1;
    zza(paramStringBuilder, k);
    paramStringBuilder.append(paramString);
    paramStringBuilder.append(" {\n");
    int m;
    int i;
    long l;
    if (paramzzf.zzbwD != null)
    {
      zza(paramStringBuilder, k + 1);
      paramStringBuilder.append("results: ");
      paramString = paramzzf.zzbwD;
      m = paramString.length;
      i = 0;
      paramInt = 0;
      while (i < m)
      {
        l = paramString[i];
        if (paramInt != 0) {
          paramStringBuilder.append(", ");
        }
        paramStringBuilder.append(Long.valueOf(l));
        i += 1;
        paramInt += 1;
      }
      paramStringBuilder.append('\n');
    }
    if (paramzzf.zzbwC != null)
    {
      zza(paramStringBuilder, k + 1);
      paramStringBuilder.append("status: ");
      paramString = paramzzf.zzbwC;
      m = paramString.length;
      paramInt = 0;
      i = j;
      while (i < m)
      {
        l = paramString[i];
        if (paramInt != 0) {
          paramStringBuilder.append(", ");
        }
        paramStringBuilder.append(Long.valueOf(l));
        i += 1;
        paramInt += 1;
      }
      paramStringBuilder.append('\n');
    }
    zza(paramStringBuilder, k);
    paramStringBuilder.append("}\n");
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, String paramString, Object paramObject)
  {
    if (paramObject == null) {
      return;
    }
    zza(paramStringBuilder, paramInt + 1);
    paramStringBuilder.append(paramString);
    paramStringBuilder.append(": ");
    paramStringBuilder.append(paramObject);
    paramStringBuilder.append('\n');
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, zzauh.zza[] paramArrayOfzza)
  {
    if (paramArrayOfzza == null) {
      return;
    }
    int i = paramInt + 1;
    int j = paramArrayOfzza.length;
    paramInt = 0;
    label15:
    zzauh.zza localzza;
    if (paramInt < j)
    {
      localzza = paramArrayOfzza[paramInt];
      if (localzza != null) {
        break label38;
      }
    }
    for (;;)
    {
      paramInt += 1;
      break label15;
      break;
      label38:
      zza(paramStringBuilder, i);
      paramStringBuilder.append("audience_membership {\n");
      zza(paramStringBuilder, i, "audience_id", localzza.zzbvh);
      zza(paramStringBuilder, i, "new_audience", localzza.zzbvT);
      zza(paramStringBuilder, i, "current_data", localzza.zzbvR);
      zza(paramStringBuilder, i, "previous_data", localzza.zzbvS);
      zza(paramStringBuilder, i);
      paramStringBuilder.append("}\n");
    }
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, zzauh.zzb[] paramArrayOfzzb)
  {
    if (paramArrayOfzzb == null) {
      return;
    }
    int i = paramInt + 1;
    int j = paramArrayOfzzb.length;
    paramInt = 0;
    label15:
    zzauh.zzb localzzb;
    if (paramInt < j)
    {
      localzzb = paramArrayOfzzb[paramInt];
      if (localzzb != null) {
        break label38;
      }
    }
    for (;;)
    {
      paramInt += 1;
      break label15;
      break;
      label38:
      zza(paramStringBuilder, i);
      paramStringBuilder.append("event {\n");
      zza(paramStringBuilder, i, "name", localzzb.name);
      zza(paramStringBuilder, i, "timestamp_millis", localzzb.zzbvW);
      zza(paramStringBuilder, i, "previous_timestamp_millis", localzzb.zzbvX);
      zza(paramStringBuilder, i, "count", localzzb.count);
      zza(paramStringBuilder, i, localzzb.zzbvV);
      zza(paramStringBuilder, i);
      paramStringBuilder.append("}\n");
    }
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, zzauh.zzc[] paramArrayOfzzc)
  {
    if (paramArrayOfzzc == null) {
      return;
    }
    int i = paramInt + 1;
    int j = paramArrayOfzzc.length;
    paramInt = 0;
    label15:
    zzauh.zzc localzzc;
    if (paramInt < j)
    {
      localzzc = paramArrayOfzzc[paramInt];
      if (localzzc != null) {
        break label38;
      }
    }
    for (;;)
    {
      paramInt += 1;
      break label15;
      break;
      label38:
      zza(paramStringBuilder, i);
      paramStringBuilder.append("param {\n");
      zza(paramStringBuilder, i, "name", localzzc.name);
      zza(paramStringBuilder, i, "string_value", localzzc.zzaFy);
      zza(paramStringBuilder, i, "int_value", localzzc.zzbvZ);
      zza(paramStringBuilder, i, "double_value", localzzc.zzbvc);
      zza(paramStringBuilder, i);
      paramStringBuilder.append("}\n");
    }
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, zzauh.zzg[] paramArrayOfzzg)
  {
    if (paramArrayOfzzg == null) {
      return;
    }
    int i = paramInt + 1;
    int j = paramArrayOfzzg.length;
    paramInt = 0;
    label15:
    zzauh.zzg localzzg;
    if (paramInt < j)
    {
      localzzg = paramArrayOfzzg[paramInt];
      if (localzzg != null) {
        break label38;
      }
    }
    for (;;)
    {
      paramInt += 1;
      break label15;
      break;
      label38:
      zza(paramStringBuilder, i);
      paramStringBuilder.append("user_property {\n");
      zza(paramStringBuilder, i, "set_timestamp_millis", localzzg.zzbwF);
      zza(paramStringBuilder, i, "name", localzzg.name);
      zza(paramStringBuilder, i, "string_value", localzzg.zzaFy);
      zza(paramStringBuilder, i, "int_value", localzzg.zzbvZ);
      zza(paramStringBuilder, i, "double_value", localzzg.zzbvc);
      zza(paramStringBuilder, i);
      paramStringBuilder.append("}\n");
    }
  }
  
  public static boolean zza(Context paramContext, String paramString, boolean paramBoolean)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      if (localPackageManager == null) {
        return false;
      }
      paramContext = localPackageManager.getReceiverInfo(new ComponentName(paramContext, paramString), 2);
      if ((paramContext != null) && (paramContext.enabled)) {
        if (paramBoolean)
        {
          paramBoolean = paramContext.exported;
          if (!paramBoolean) {}
        }
        else
        {
          return true;
        }
      }
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean zza(long[] paramArrayOfLong, int paramInt)
  {
    if (paramInt >= paramArrayOfLong.length * 64) {}
    while ((paramArrayOfLong[(paramInt / 64)] & 1L << paramInt % 64) == 0L) {
      return false;
    }
    return true;
  }
  
  public static long[] zza(BitSet paramBitSet)
  {
    int k = (paramBitSet.length() + 63) / 64;
    long[] arrayOfLong = new long[k];
    int i = 0;
    if (i < k)
    {
      arrayOfLong[i] = 0L;
      int j = 0;
      for (;;)
      {
        if ((j >= 64) || (i * 64 + j >= paramBitSet.length()))
        {
          i += 1;
          break;
        }
        if (paramBitSet.get(i * 64 + j)) {
          arrayOfLong[i] |= 1L << j;
        }
        j += 1;
      }
    }
    return arrayOfLong;
  }
  
  public static boolean zzab(String paramString1, String paramString2)
  {
    if ((paramString1 == null) && (paramString2 == null)) {
      return true;
    }
    if (paramString1 == null) {
      return false;
    }
    return paramString1.equals(paramString2);
  }
  
  public static String zzb(zzauh.zzd paramzzd)
  {
    if (paramzzd == null) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\nbatch {\n");
    if (paramzzd.zzbwa != null)
    {
      paramzzd = paramzzd.zzbwa;
      int j = paramzzd.length;
      int i = 0;
      if (i < j)
      {
        zzauh.zze localzze = paramzzd[i];
        if (localzze == null) {}
        for (;;)
        {
          i += 1;
          break;
          zza(localStringBuilder, 1, localzze);
        }
      }
    }
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
  
  @WorkerThread
  static boolean zzc(zzatb paramzzatb, zzasq paramzzasq)
  {
    zzac.zzw(paramzzatb);
    zzac.zzw(paramzzasq);
    return (!TextUtils.isEmpty(paramzzasq.zzbqf)) || ("_in".equals(paramzzatb.name));
  }
  
  static MessageDigest zzcg(String paramString)
  {
    int i = 0;
    while (i < 2) {
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
        if (localMessageDigest != null) {
          return localMessageDigest;
        }
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        i += 1;
      }
    }
    return null;
  }
  
  static boolean zzfW(String paramString)
  {
    boolean bool = false;
    zzac.zzdv(paramString);
    if (paramString.charAt(0) != '_') {
      bool = true;
    }
    return bool;
  }
  
  private int zzgf(String paramString)
  {
    if ("_ldl".equals(paramString)) {
      return zzJv().zzKa();
    }
    return zzJv().zzJZ();
  }
  
  public static boolean zzgg(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (paramString.startsWith("_"));
  }
  
  static boolean zzgi(String paramString)
  {
    return (paramString != null) && (paramString.matches("(\\+|-)?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)")) && (paramString.length() <= 310);
  }
  
  public static boolean zzr(Context paramContext, String paramString)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      if (localPackageManager == null) {
        return false;
      }
      paramContext = localPackageManager.getServiceInfo(new ComponentName(paramContext, paramString), 4);
      if (paramContext != null)
      {
        boolean bool = paramContext.enabled;
        if (bool) {
          return true;
        }
      }
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  static long zzy(byte[] paramArrayOfByte)
  {
    int j = 0;
    zzac.zzw(paramArrayOfByte);
    if (paramArrayOfByte.length > 0) {}
    long l;
    for (boolean bool = true;; bool = false)
    {
      zzac.zzar(bool);
      l = 0L;
      int i = paramArrayOfByte.length - 1;
      while ((i >= 0) && (i >= paramArrayOfByte.length - 8))
      {
        l += ((paramArrayOfByte[i] & 0xFF) << j);
        j += 8;
        i -= 1;
      }
    }
    return l;
  }
  
  public boolean zzD(Intent paramIntent)
  {
    paramIntent = paramIntent.getStringExtra("android.intent.extra.REFERRER_NAME");
    return ("android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(paramIntent)) || ("https://www.google.com".equals(paramIntent)) || ("android-app://com.google.appcrawler".equals(paramIntent));
  }
  
  @WorkerThread
  long zzE(Context paramContext, String paramString)
  {
    zzmq();
    zzac.zzw(paramContext);
    zzac.zzdv(paramString);
    PackageManager localPackageManager = paramContext.getPackageManager();
    MessageDigest localMessageDigest = zzcg("MD5");
    if (localMessageDigest == null)
    {
      zzJt().zzLa().log("Could not get MD5 instance");
      return -1L;
    }
    if (localPackageManager != null) {
      try
      {
        if (!zzF(paramContext, paramString))
        {
          paramContext = zzacx.zzaQ(paramContext).getPackageInfo(getContext().getPackageName(), 64);
          if ((paramContext.signatures != null) && (paramContext.signatures.length > 0)) {
            return zzy(localMessageDigest.digest(paramContext.signatures[0].toByteArray()));
          }
          zzJt().zzLc().log("Could not get signatures");
          return -1L;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        zzJt().zzLa().zzj("Package name not found", paramContext);
      }
    }
    return 0L;
  }
  
  boolean zzF(Context paramContext, String paramString)
  {
    X500Principal localX500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
    try
    {
      paramContext = zzacx.zzaQ(paramContext).getPackageInfo(paramString, 64);
      if ((paramContext != null) && (paramContext.signatures != null) && (paramContext.signatures.length > 0))
      {
        paramContext = paramContext.signatures[0];
        boolean bool = ((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramContext.toByteArray()))).getSubjectX500Principal().equals(localX500Principal);
        return bool;
      }
    }
    catch (CertificateException paramContext)
    {
      zzJt().zzLa().zzj("Error obtaining certificate", paramContext);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        zzJt().zzLa().zzj("Package name not found", paramContext);
      }
    }
  }
  
  public long zzMi()
  {
    long l1;
    if (this.zzbve.get() == 0L) {
      synchronized (this.zzbve)
      {
        l1 = new Random(System.nanoTime() ^ zznq().currentTimeMillis()).nextLong();
        int i = this.zzbvf + 1;
        this.zzbvf = i;
        long l2 = i;
        return l1 + l2;
      }
    }
    synchronized (this.zzbve)
    {
      this.zzbve.compareAndSet(-1L, 1L);
      l1 = this.zzbve.getAndIncrement();
      return l1;
    }
  }
  
  boolean zzZ(String paramString1, String paramString2)
  {
    if (paramString2 == null)
    {
      zzJt().zzLa().zzj("Name is required and can't be null. Type", paramString1);
      return false;
    }
    if (paramString2.length() == 0)
    {
      zzJt().zzLa().zzj("Name is required and can't be empty. Type", paramString1);
      return false;
    }
    int i = paramString2.codePointAt(0);
    if (!Character.isLetter(i))
    {
      zzJt().zzLa().zze("Name must start with a letter. Type, name", paramString1, paramString2);
      return false;
    }
    int j = paramString2.length();
    i = Character.charCount(i);
    while (i < j)
    {
      int k = paramString2.codePointAt(i);
      if ((k != 95) && (!Character.isLetterOrDigit(k)))
      {
        zzJt().zzLa().zze("Name must consist of letters, digits or _ (underscores). Type, name", paramString1, paramString2);
        return false;
      }
      i += Character.charCount(k);
    }
    return true;
  }
  
  public Bundle zza(String paramString, Bundle paramBundle, @Nullable List<String> paramList, boolean paramBoolean)
  {
    Bundle localBundle = null;
    int i;
    String str1;
    int j;
    if (paramBundle != null)
    {
      localBundle = new Bundle(paramBundle);
      zzJv().zzJT();
      Iterator localIterator = paramBundle.keySet().iterator();
      i = 0;
      if (localIterator.hasNext())
      {
        str1 = (String)localIterator.next();
        if ((paramList != null) && (paramList.contains(str1))) {
          break label356;
        }
        if (!paramBoolean) {
          break label350;
        }
        j = zzgb(str1);
        label89:
        k = j;
        if (j != 0) {}
      }
    }
    label350:
    label356:
    for (int k = zzgc(str1);; k = 0)
    {
      if (k != 0)
      {
        if (zzd(localBundle, k))
        {
          localBundle.putString("_ev", zza(str1, zzJv().zzJW(), true));
          if (k == 3) {
            zzb(localBundle, str1);
          }
        }
        localBundle.remove(str1);
        break;
      }
      if ((!zzk(str1, paramBundle.get(str1))) && (!"_ev".equals(str1)))
      {
        if (zzd(localBundle, 4))
        {
          localBundle.putString("_ev", zza(str1, zzJv().zzJW(), true));
          zzb(localBundle, paramBundle.get(str1));
        }
        localBundle.remove(str1);
        break;
      }
      j = i;
      if (zzfW(str1))
      {
        i += 1;
        j = i;
        if (i > 25)
        {
          String str2 = 48 + "Event can't contain more then " + 25 + " params";
          zzJt().zzLa().zze(str2, paramString, paramBundle);
          zzd(localBundle, 5);
          localBundle.remove(str1);
          break;
        }
      }
      i = j;
      break;
      return localBundle;
      j = 0;
      break label89;
    }
  }
  
  public String zza(String paramString, int paramInt, boolean paramBoolean)
  {
    String str = paramString;
    if (paramString.length() > paramInt)
    {
      if (paramBoolean) {
        str = String.valueOf(paramString.substring(0, paramInt)).concat("...");
      }
    }
    else {
      return str;
    }
    return null;
  }
  
  public void zza(int paramInt1, String paramString1, String paramString2, int paramInt2)
  {
    zza(null, paramInt1, paramString1, paramString2, paramInt2);
  }
  
  public void zza(Bundle paramBundle, String paramString, Object paramObject)
  {
    if (paramBundle == null) {}
    do
    {
      return;
      if ((paramObject instanceof Long))
      {
        paramBundle.putLong(paramString, ((Long)paramObject).longValue());
        return;
      }
      if ((paramObject instanceof String))
      {
        paramBundle.putString(paramString, String.valueOf(paramObject));
        return;
      }
      if ((paramObject instanceof Double))
      {
        paramBundle.putDouble(paramString, ((Double)paramObject).doubleValue());
        return;
      }
    } while (paramString == null);
    if (paramObject != null) {}
    for (paramBundle = paramObject.getClass().getSimpleName();; paramBundle = null)
    {
      zzJt().zzLd().zze("Not putting event parameter. Invalid value type. name, type", paramString, paramBundle);
      return;
    }
  }
  
  public void zza(zzauh.zzc paramzzc, Object paramObject)
  {
    zzac.zzw(paramObject);
    paramzzc.zzaFy = null;
    paramzzc.zzbvZ = null;
    paramzzc.zzbvc = null;
    if ((paramObject instanceof String))
    {
      paramzzc.zzaFy = ((String)paramObject);
      return;
    }
    if ((paramObject instanceof Long))
    {
      paramzzc.zzbvZ = ((Long)paramObject);
      return;
    }
    if ((paramObject instanceof Double))
    {
      paramzzc.zzbvc = ((Double)paramObject);
      return;
    }
    zzJt().zzLa().zzj("Ignoring invalid (type) event param value", paramObject);
  }
  
  public void zza(zzauh.zzg paramzzg, Object paramObject)
  {
    zzac.zzw(paramObject);
    paramzzg.zzaFy = null;
    paramzzg.zzbvZ = null;
    paramzzg.zzbvc = null;
    if ((paramObject instanceof String))
    {
      paramzzg.zzaFy = ((String)paramObject);
      return;
    }
    if ((paramObject instanceof Long))
    {
      paramzzg.zzbvZ = ((Long)paramObject);
      return;
    }
    if ((paramObject instanceof Double))
    {
      paramzzg.zzbvc = ((Double)paramObject);
      return;
    }
    zzJt().zzLa().zzj("Ignoring invalid (type) user attribute value", paramObject);
  }
  
  public void zza(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2)
  {
    paramString1 = new Bundle();
    zzd(paramString1, paramInt1);
    if (!TextUtils.isEmpty(paramString2)) {
      paramString1.putString(paramString2, paramString3);
    }
    if ((paramInt1 == 6) || (paramInt1 == 7) || (paramInt1 == 2)) {
      paramString1.putLong("_el", paramInt2);
    }
    this.zzbpw.zzJv().zzKk();
    this.zzbpw.zzJi().zze("auto", "_err", paramString1);
  }
  
  boolean zza(String paramString1, String paramString2, int paramInt, Object paramObject)
  {
    if (paramObject == null) {}
    do
    {
      do
      {
        return true;
      } while (((paramObject instanceof Long)) || ((paramObject instanceof Float)) || ((paramObject instanceof Integer)) || ((paramObject instanceof Byte)) || ((paramObject instanceof Short)) || ((paramObject instanceof Boolean)) || ((paramObject instanceof Double)));
      if ((!(paramObject instanceof String)) && (!(paramObject instanceof Character)) && (!(paramObject instanceof CharSequence))) {
        break;
      }
      paramObject = String.valueOf(paramObject);
    } while (((String)paramObject).length() <= paramInt);
    zzJt().zzLc().zzd("Value is too long; discarded. Value kind, name, value length", paramString1, paramString2, Integer.valueOf(((String)paramObject).length()));
    return false;
    return false;
  }
  
  public byte[] zza(zzauh.zzd paramzzd)
  {
    try
    {
      byte[] arrayOfByte = new byte[paramzzd.zzacZ()];
      zzbum localzzbum = zzbum.zzae(arrayOfByte);
      paramzzd.zza(localzzbum);
      localzzbum.zzacM();
      return arrayOfByte;
    }
    catch (IOException paramzzd)
    {
      zzJt().zzLa().zzj("Data loss. Failed to serialize batch", paramzzd);
    }
    return null;
  }
  
  boolean zzaa(String paramString1, String paramString2)
  {
    if (paramString2 == null)
    {
      zzJt().zzLa().zzj("Name is required and can't be null. Type", paramString1);
      return false;
    }
    if (paramString2.length() == 0)
    {
      zzJt().zzLa().zzj("Name is required and can't be empty. Type", paramString1);
      return false;
    }
    int i = paramString2.codePointAt(0);
    if ((!Character.isLetter(i)) && (i != 95))
    {
      zzJt().zzLa().zze("Name must start with a letter or _ (underscore). Type, name", paramString1, paramString2);
      return false;
    }
    int j = paramString2.length();
    i = Character.charCount(i);
    while (i < j)
    {
      int k = paramString2.codePointAt(i);
      if ((k != 95) && (!Character.isLetterOrDigit(k)))
      {
        zzJt().zzLa().zze("Name must consist of letters, digits or _ (underscores). Type, name", paramString1, paramString2);
        return false;
      }
      i += Character.charCount(k);
    }
    return true;
  }
  
  public void zzb(Bundle paramBundle, Object paramObject)
  {
    zzac.zzw(paramBundle);
    if ((paramObject != null) && (((paramObject instanceof String)) || ((paramObject instanceof CharSequence)))) {
      paramBundle.putLong("_el", String.valueOf(paramObject).length());
    }
  }
  
  @WorkerThread
  public boolean zzbV(String paramString)
  {
    zzmq();
    if (zzacx.zzaQ(getContext()).checkCallingOrSelfPermission(paramString) == 0) {
      return true;
    }
    zzJt().zzLf().zzj("Permission not granted", paramString);
    return false;
  }
  
  boolean zzc(String paramString1, int paramInt, String paramString2)
  {
    if (paramString2 == null)
    {
      zzJt().zzLa().zzj("Name is required and can't be null. Type", paramString1);
      return false;
    }
    if (paramString2.length() > paramInt)
    {
      zzJt().zzLa().zzd("Name is too long. Type, maximum supported length, name", paramString1, Integer.valueOf(paramInt), paramString2);
      return false;
    }
    return true;
  }
  
  boolean zzc(String paramString1, Map<String, String> paramMap, String paramString2)
  {
    if (paramString2 == null)
    {
      zzJt().zzLa().zzj("Name is required and can't be null. Type", paramString1);
      return false;
    }
    if (paramString2.startsWith("firebase_"))
    {
      zzJt().zzLa().zze("Name starts with reserved prefix. Type, name", paramString1, paramString2);
      return false;
    }
    if ((paramMap != null) && (paramMap.containsKey(paramString2)))
    {
      zzJt().zzLa().zze("Name is reserved. Type, name", paramString1, paramString2);
      return false;
    }
    return true;
  }
  
  public boolean zzd(Bundle paramBundle, int paramInt)
  {
    if (paramBundle == null) {}
    while (paramBundle.getLong("_err") != 0L) {
      return false;
    }
    paramBundle.putLong("_err", paramInt);
    return true;
  }
  
  public boolean zzf(long paramLong1, long paramLong2)
  {
    if ((paramLong1 == 0L) || (paramLong2 <= 0L)) {}
    while (Math.abs(zznq().currentTimeMillis() - paramLong1) > paramLong2) {
      return true;
    }
    return false;
  }
  
  public int zzfX(String paramString)
  {
    if (!zzZ("event", paramString)) {}
    do
    {
      return 2;
      if (!zzc("event", AppMeasurement.zza.zzbpx, paramString)) {
        return 13;
      }
    } while (!zzc("event", zzJv().zzJU(), paramString));
    return 0;
  }
  
  public int zzfY(String paramString)
  {
    if (!zzaa("event", paramString)) {}
    do
    {
      return 2;
      if (!zzc("event", AppMeasurement.zza.zzbpx, paramString)) {
        return 13;
      }
    } while (!zzc("event", zzJv().zzJU(), paramString));
    return 0;
  }
  
  public int zzfZ(String paramString)
  {
    if (!zzZ("user property", paramString)) {}
    do
    {
      return 6;
      if (!zzc("user property", AppMeasurement.zzg.zzbpC, paramString)) {
        return 15;
      }
    } while (!zzc("user property", zzJv().zzJV(), paramString));
    return 0;
  }
  
  public int zzga(String paramString)
  {
    if (!zzaa("user property", paramString)) {}
    do
    {
      return 6;
      if (!zzc("user property", AppMeasurement.zzg.zzbpC, paramString)) {
        return 15;
      }
    } while (!zzc("user property", zzJv().zzJV(), paramString));
    return 0;
  }
  
  public int zzgb(String paramString)
  {
    if (!zzZ("event param", paramString)) {}
    do
    {
      return 3;
      if (!zzc("event param", null, paramString)) {
        return 14;
      }
    } while (!zzc("event param", zzJv().zzJW(), paramString));
    return 0;
  }
  
  public int zzgc(String paramString)
  {
    if (!zzaa("event param", paramString)) {}
    do
    {
      return 3;
      if (!zzc("event param", null, paramString)) {
        return 14;
      }
    } while (!zzc("event param", zzJv().zzJW(), paramString));
    return 0;
  }
  
  public boolean zzgd(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      zzJt().zzLa().log("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
      return false;
    }
    if (!zzge(paramString))
    {
      zzJt().zzLa().zzj("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", paramString);
      return false;
    }
    return true;
  }
  
  boolean zzge(String paramString)
  {
    zzac.zzw(paramString);
    return paramString.matches("^1:\\d+:android:[a-f0-9]+$");
  }
  
  public boolean zzgh(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    String str = zzJv().zzKF();
    zzJv().zzKk();
    return str.equals(paramString);
  }
  
  boolean zzgj(String paramString)
  {
    return "1".equals(zzJq().zzW(paramString, "measurement.upload.blacklist_internal"));
  }
  
  boolean zzgk(String paramString)
  {
    return "1".equals(zzJq().zzW(paramString, "measurement.upload.blacklist_public"));
  }
  
  public boolean zzk(String paramString, Object paramObject)
  {
    if (zzgg(paramString)) {
      return zza("param", paramString, zzJv().zzJY(), paramObject);
    }
    return zza("param", paramString, zzJv().zzJX(), paramObject);
  }
  
  public byte[] zzk(byte[] paramArrayOfByte)
    throws IOException
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
      localGZIPOutputStream.write(paramArrayOfByte);
      localGZIPOutputStream.close();
      localByteArrayOutputStream.close();
      paramArrayOfByte = localByteArrayOutputStream.toByteArray();
      return paramArrayOfByte;
    }
    catch (IOException paramArrayOfByte)
    {
      zzJt().zzLa().zzj("Failed to gzip content", paramArrayOfByte);
      throw paramArrayOfByte;
    }
  }
  
  public Object zzl(String paramString, Object paramObject)
  {
    if ("_ev".equals(paramString)) {
      return zza(zzJv().zzJY(), paramObject, true);
    }
    if (zzgg(paramString)) {}
    for (int i = zzJv().zzJY();; i = zzJv().zzJX()) {
      return zza(i, paramObject, false);
    }
  }
  
  public int zzm(String paramString, Object paramObject)
  {
    if ("_ldl".equals(paramString)) {}
    for (boolean bool = zza("user property referrer", paramString, zzgf(paramString), paramObject); bool; bool = zza("user property", paramString, zzgf(paramString), paramObject)) {
      return 0;
    }
    return 7;
  }
  
  protected void zzmr()
  {
    SecureRandom localSecureRandom = new SecureRandom();
    long l2 = localSecureRandom.nextLong();
    long l1 = l2;
    if (l2 == 0L)
    {
      l2 = localSecureRandom.nextLong();
      l1 = l2;
      if (l2 == 0L)
      {
        zzJt().zzLc().log("Utils falling back to Random for random id");
        l1 = l2;
      }
    }
    this.zzbve.set(l1);
  }
  
  public Object zzn(String paramString, Object paramObject)
  {
    if ("_ldl".equals(paramString)) {
      return zza(zzgf(paramString), paramObject, true);
    }
    return zza(zzgf(paramString), paramObject, false);
  }
  
  public Bundle zzu(@NonNull Uri paramUri)
  {
    Object localObject = null;
    if (paramUri == null) {
      return (Bundle)localObject;
    }
    for (;;)
    {
      try
      {
        if (paramUri.isHierarchical())
        {
          str4 = paramUri.getQueryParameter("utm_campaign");
          str3 = paramUri.getQueryParameter("utm_source");
          str2 = paramUri.getQueryParameter("utm_medium");
          str1 = paramUri.getQueryParameter("gclid");
          if ((TextUtils.isEmpty(str4)) && (TextUtils.isEmpty(str3)) && (TextUtils.isEmpty(str2)) && (TextUtils.isEmpty(str1))) {
            break;
          }
          Bundle localBundle = new Bundle();
          if (!TextUtils.isEmpty(str4)) {
            localBundle.putString("campaign", str4);
          }
          if (!TextUtils.isEmpty(str3)) {
            localBundle.putString("source", str3);
          }
          if (!TextUtils.isEmpty(str2)) {
            localBundle.putString("medium", str2);
          }
          if (!TextUtils.isEmpty(str1)) {
            localBundle.putString("gclid", str1);
          }
          str1 = paramUri.getQueryParameter("utm_term");
          if (!TextUtils.isEmpty(str1)) {
            localBundle.putString("term", str1);
          }
          str1 = paramUri.getQueryParameter("utm_content");
          if (!TextUtils.isEmpty(str1)) {
            localBundle.putString("content", str1);
          }
          str1 = paramUri.getQueryParameter("aclid");
          if (!TextUtils.isEmpty(str1)) {
            localBundle.putString("aclid", str1);
          }
          str1 = paramUri.getQueryParameter("cp1");
          if (!TextUtils.isEmpty(str1)) {
            localBundle.putString("cp1", str1);
          }
          paramUri = paramUri.getQueryParameter("anid");
          localObject = localBundle;
          if (TextUtils.isEmpty(paramUri)) {
            break;
          }
          localBundle.putString("anid", paramUri);
          return localBundle;
        }
      }
      catch (UnsupportedOperationException paramUri)
      {
        zzJt().zzLc().zzj("Install referrer url isn't a hierarchical URI", paramUri);
        return null;
      }
      String str1 = null;
      String str2 = null;
      String str3 = null;
      String str4 = null;
    }
  }
  
  /* Error */
  public byte[] zzx(byte[] paramArrayOfByte)
    throws IOException
  {
    // Byte code:
    //   0: new 802	java/io/ByteArrayInputStream
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 805	java/io/ByteArrayInputStream:<init>	([B)V
    //   8: astore_1
    //   9: new 1328	java/util/zip/GZIPInputStream
    //   12: dup
    //   13: aload_1
    //   14: invokespecial 1331	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   17: astore_3
    //   18: new 1242	java/io/ByteArrayOutputStream
    //   21: dup
    //   22: invokespecial 1243	java/io/ByteArrayOutputStream:<init>	()V
    //   25: astore 4
    //   27: sipush 1024
    //   30: newarray <illegal type>
    //   32: astore 5
    //   34: aload_3
    //   35: aload 5
    //   37: invokevirtual 1335	java/util/zip/GZIPInputStream:read	([B)I
    //   40: istore_2
    //   41: iload_2
    //   42: ifgt +17 -> 59
    //   45: aload_3
    //   46: invokevirtual 1336	java/util/zip/GZIPInputStream:close	()V
    //   49: aload_1
    //   50: invokevirtual 1337	java/io/ByteArrayInputStream:close	()V
    //   53: aload 4
    //   55: invokevirtual 1256	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   58: areturn
    //   59: aload 4
    //   61: aload 5
    //   63: iconst_0
    //   64: iload_2
    //   65: invokevirtual 1340	java/io/ByteArrayOutputStream:write	([BII)V
    //   68: goto -34 -> 34
    //   71: astore_1
    //   72: aload_0
    //   73: invokevirtual 721	com/google/android/gms/internal/zzaue:zzJt	()Lcom/google/android/gms/internal/zzati;
    //   76: invokevirtual 727	com/google/android/gms/internal/zzati:zzLa	()Lcom/google/android/gms/internal/zzati$zza;
    //   79: ldc_w 1342
    //   82: aload_1
    //   83: invokevirtual 785	com/google/android/gms/internal/zzati$zza:zzj	(Ljava/lang/String;Ljava/lang/Object;)V
    //   86: aload_1
    //   87: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	88	0	this	zzaue
    //   0	88	1	paramArrayOfByte	byte[]
    //   40	25	2	i	int
    //   17	29	3	localGZIPInputStream	java.util.zip.GZIPInputStream
    //   25	35	4	localByteArrayOutputStream	ByteArrayOutputStream
    //   32	30	5	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   0	34	71	java/io/IOException
    //   34	41	71	java/io/IOException
    //   45	59	71	java/io/IOException
    //   59	68	71	java/io/IOException
  }
  
  @WorkerThread
  public long zzz(byte[] paramArrayOfByte)
  {
    zzac.zzw(paramArrayOfByte);
    zzmq();
    MessageDigest localMessageDigest = zzcg("MD5");
    if (localMessageDigest == null)
    {
      zzJt().zzLa().log("Failed to get MD5");
      return 0L;
    }
    return zzy(localMessageDigest.digest(paramArrayOfByte));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzaue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */