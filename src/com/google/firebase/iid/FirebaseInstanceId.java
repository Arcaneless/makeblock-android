package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Map;

public class FirebaseInstanceId
{
  private static Map<String, FirebaseInstanceId> zzbha = new ArrayMap();
  private static zze zzciQ;
  private final FirebaseApp zzciR;
  private final zzd zzciS;
  private final String zzciT;
  
  private FirebaseInstanceId(FirebaseApp paramFirebaseApp, zzd paramzzd)
  {
    this.zzciR = paramFirebaseApp;
    this.zzciS = paramzzd;
    this.zzciT = zzaac();
    if (this.zzciT == null) {
      throw new IllegalStateException("IID failing to initialize, FirebaseApp is missing project ID");
    }
    FirebaseInstanceIdService.zza(this.zzciR.getApplicationContext(), this);
  }
  
  public static FirebaseInstanceId getInstance()
  {
    return getInstance(FirebaseApp.getInstance());
  }
  
  @Keep
  public static FirebaseInstanceId getInstance(@NonNull FirebaseApp paramFirebaseApp)
  {
    try
    {
      FirebaseInstanceId localFirebaseInstanceId = (FirebaseInstanceId)zzbha.get(paramFirebaseApp.getOptions().getApplicationId());
      Object localObject = localFirebaseInstanceId;
      if (localFirebaseInstanceId == null)
      {
        localObject = zzd.zzb(paramFirebaseApp.getApplicationContext(), null);
        if (zzciQ == null) {
          zzciQ = new zze(((zzd)localObject).zzaag());
        }
        localObject = new FirebaseInstanceId(paramFirebaseApp, (zzd)localObject);
        zzbha.put(paramFirebaseApp.getOptions().getApplicationId(), localObject);
      }
      return (FirebaseInstanceId)localObject;
    }
    finally {}
  }
  
  static int zzK(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext = String.valueOf(paramContext);
      Log.w("FirebaseInstanceId", String.valueOf(paramContext).length() + 23 + "Failed to find package " + paramContext);
    }
    return 0;
  }
  
  static String zza(KeyPair paramKeyPair)
  {
    paramKeyPair = paramKeyPair.getPublic().getEncoded();
    try
    {
      paramKeyPair = MessageDigest.getInstance("SHA1").digest(paramKeyPair);
      paramKeyPair[0] = ((byte)((paramKeyPair[0] & 0xF) + 112 & 0xFF));
      paramKeyPair = Base64.encodeToString(paramKeyPair, 0, 8, 11);
      return paramKeyPair;
    }
    catch (NoSuchAlgorithmException paramKeyPair)
    {
      Log.w("FirebaseInstanceId", "Unexpected error, device missing required alghorithms");
    }
    return null;
  }
  
  static void zza(Context paramContext, zzh paramzzh)
  {
    paramzzh.zzGA();
    paramzzh = new Intent();
    paramzzh.putExtra("CMD", "RST");
    zzg.zzaaj().zzf(paramContext, paramzzh);
  }
  
  static String zzbT(Context paramContext)
  {
    return getInstance().zzciR.getOptions().getApplicationId();
  }
  
  static int zzbU(Context paramContext)
  {
    return zzK(paramContext, paramContext.getPackageName());
  }
  
  static String zzbg(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext = String.valueOf(paramContext);
      Log.w("FirebaseInstanceId", String.valueOf(paramContext).length() + 38 + "Never happens: can't find own package " + paramContext);
    }
    return null;
  }
  
  static void zzbh(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("CMD", "SYNC");
    zzg.zzaaj().zzf(paramContext, localIntent);
  }
  
  static String zzv(byte[] paramArrayOfByte)
  {
    return Base64.encodeToString(paramArrayOfByte, 11);
  }
  
  public void deleteInstanceId()
    throws IOException
  {
    this.zzciS.zzb("*", "*", null);
    this.zzciS.zzGu();
  }
  
  @WorkerThread
  public void deleteToken(String paramString1, String paramString2)
    throws IOException
  {
    this.zzciS.zzb(paramString1, paramString2, null);
  }
  
  public long getCreationTime()
  {
    return this.zzciS.getCreationTime();
  }
  
  public String getId()
  {
    return zza(this.zzciS.zzGt());
  }
  
  @Nullable
  public String getToken()
  {
    zzh.zza localzza = zzaad();
    if ((localzza == null) || (localzza.zzjC(zzd.zzbhg))) {
      FirebaseInstanceIdService.zzbV(this.zzciR.getApplicationContext());
    }
    if (localzza != null) {
      return localzza.zzbwP;
    }
    return null;
  }
  
  @WorkerThread
  public String getToken(String paramString1, String paramString2)
    throws IOException
  {
    return this.zzciS.getToken(paramString1, paramString2, null);
  }
  
  String zzaac()
  {
    Object localObject = this.zzciR.getOptions().getGcmSenderId();
    if (localObject != null) {}
    String str;
    do
    {
      do
      {
        return (String)localObject;
        str = this.zzciR.getOptions().getApplicationId();
        localObject = str;
      } while (!str.startsWith("1:"));
      localObject = str.split(":");
      if (localObject.length < 2) {
        return null;
      }
      str = localObject[1];
      localObject = str;
    } while (!str.isEmpty());
    return null;
  }
  
  @Nullable
  zzh.zza zzaad()
  {
    return this.zzciS.zzaag().zzq("", this.zzciT, "*");
  }
  
  String zzaae()
    throws IOException
  {
    return getToken(this.zzciT, "*");
  }
  
  zze zzaaf()
  {
    return zzciQ;
  }
  
  public void zzju(String paramString)
  {
    zzciQ.zzju(paramString);
    FirebaseInstanceIdService.zzbV(this.zzciR.getApplicationContext());
  }
  
  void zzjv(String paramString)
    throws IOException
  {
    Object localObject2 = zzaad();
    if ((localObject2 == null) || (((zzh.zza)localObject2).zzjC(zzd.zzbhg))) {
      throw new IOException("token not available");
    }
    Bundle localBundle = new Bundle();
    Object localObject1 = String.valueOf("/topics/");
    String str = String.valueOf(paramString);
    if (str.length() != 0)
    {
      localObject1 = ((String)localObject1).concat(str);
      localBundle.putString("gcm.topic", (String)localObject1);
      localObject1 = this.zzciS;
      localObject2 = ((zzh.zza)localObject2).zzbwP;
      str = String.valueOf("/topics/");
      paramString = String.valueOf(paramString);
      if (paramString.length() == 0) {
        break label138;
      }
    }
    label138:
    for (paramString = str.concat(paramString);; paramString = new String(str))
    {
      ((zzd)localObject1).getToken((String)localObject2, paramString, localBundle);
      return;
      localObject1 = new String((String)localObject1);
      break;
    }
  }
  
  void zzjw(String paramString)
    throws IOException
  {
    Object localObject2 = zzaad();
    if ((localObject2 == null) || (((zzh.zza)localObject2).zzjC(zzd.zzbhg))) {
      throw new IOException("token not available");
    }
    Bundle localBundle = new Bundle();
    Object localObject1 = String.valueOf("/topics/");
    String str = String.valueOf(paramString);
    if (str.length() != 0)
    {
      localObject1 = ((String)localObject1).concat(str);
      localBundle.putString("gcm.topic", (String)localObject1);
      localObject1 = this.zzciS;
      localObject2 = ((zzh.zza)localObject2).zzbwP;
      str = String.valueOf("/topics/");
      paramString = String.valueOf(paramString);
      if (paramString.length() == 0) {
        break label137;
      }
    }
    label137:
    for (paramString = str.concat(paramString);; paramString = new String(str))
    {
      ((zzd)localObject1).zzb((String)localObject2, paramString, localBundle);
      return;
      localObject1 = new String((String)localObject1);
      break;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\firebase\iid\FirebaseInstanceId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */