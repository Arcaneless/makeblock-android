package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.iid.MessengerCompat;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class zzf
{
  static String zzbhq = null;
  static int zzbhr = 0;
  static int zzbhs = 0;
  static int zzbht = 0;
  PendingIntent zzbga;
  Messenger zzbge;
  int zzbhA;
  long zzbhB;
  Map<String, Object> zzbhu = new HashMap();
  Messenger zzbhv;
  MessengerCompat zzbhw;
  long zzbhx;
  long zzbhy;
  int zzbhz;
  Context zzqr;
  
  public zzf(Context paramContext)
  {
    this.zzqr = paramContext;
  }
  
  private void zzG(Object paramObject)
  {
    synchronized (getClass())
    {
      Iterator localIterator = this.zzbhu.keySet().iterator();
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject = this.zzbhu.get(str);
        this.zzbhu.put(str, paramObject);
        zzh(localObject, paramObject);
      }
    }
  }
  
  public static String zzGz()
  {
    try
    {
      int i = zzbht;
      zzbht = i + 1;
      String str = Integer.toString(i);
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  static String zza(KeyPair paramKeyPair, String... paramVarArgs)
  {
    // Byte code:
    //   0: ldc 110
    //   2: aload_1
    //   3: invokestatic 116	android/text/TextUtils:join	(Ljava/lang/CharSequence;[Ljava/lang/Object;)Ljava/lang/String;
    //   6: ldc 118
    //   8: invokevirtual 122	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   11: astore_1
    //   12: aload_0
    //   13: invokevirtual 128	java/security/KeyPair:getPrivate	()Ljava/security/PrivateKey;
    //   16: astore_2
    //   17: aload_2
    //   18: instanceof 130
    //   21: ifeq +43 -> 64
    //   24: ldc -124
    //   26: astore_0
    //   27: aload_0
    //   28: invokestatic 138	java/security/Signature:getInstance	(Ljava/lang/String;)Ljava/security/Signature;
    //   31: astore_0
    //   32: aload_0
    //   33: aload_2
    //   34: invokevirtual 142	java/security/Signature:initSign	(Ljava/security/PrivateKey;)V
    //   37: aload_0
    //   38: aload_1
    //   39: invokevirtual 146	java/security/Signature:update	([B)V
    //   42: aload_0
    //   43: invokevirtual 150	java/security/Signature:sign	()[B
    //   46: invokestatic 156	com/google/firebase/iid/FirebaseInstanceId:zzv	([B)Ljava/lang/String;
    //   49: astore_0
    //   50: aload_0
    //   51: areturn
    //   52: astore_0
    //   53: ldc -98
    //   55: ldc -96
    //   57: aload_0
    //   58: invokestatic 166	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   61: pop
    //   62: aconst_null
    //   63: areturn
    //   64: ldc -88
    //   66: astore_0
    //   67: goto -40 -> 27
    //   70: astore_0
    //   71: ldc -98
    //   73: ldc -86
    //   75: aload_0
    //   76: invokestatic 166	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   79: pop
    //   80: aconst_null
    //   81: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	82	0	paramKeyPair	KeyPair
    //   0	82	1	paramVarArgs	String[]
    //   16	18	2	localPrivateKey	java.security.PrivateKey
    // Exception table:
    //   from	to	target	type
    //   0	12	52	java/io/UnsupportedEncodingException
    //   12	24	70	java/security/GeneralSecurityException
    //   27	50	70	java/security/GeneralSecurityException
  }
  
  private Intent zzb(Bundle arg1, KeyPair paramKeyPair)
    throws IOException
  {
    ConditionVariable localConditionVariable = new ConditionVariable();
    String str = zzGz();
    synchronized (getClass())
    {
      this.zzbhu.put(str, localConditionVariable);
      zza(???, paramKeyPair, str);
      localConditionVariable.block(30000L);
    }
    synchronized (getClass())
    {
      paramKeyPair = this.zzbhu.remove(str);
      if ((paramKeyPair instanceof Intent))
      {
        paramKeyPair = (Intent)paramKeyPair;
        return paramKeyPair;
        ??? = finally;
        throw ???;
      }
      if ((paramKeyPair instanceof String)) {
        throw new IOException((String)paramKeyPair);
      }
    }
    paramKeyPair = String.valueOf(paramKeyPair);
    Log.w("InstanceID/Rpc", String.valueOf(paramKeyPair).length() + 12 + "No response " + paramKeyPair);
    throw new IOException("TIMEOUT");
  }
  
  public static String zzbi(Context paramContext)
  {
    if (zzbhq != null) {
      return zzbhq;
    }
    zzbhr = Process.myUid();
    paramContext = paramContext.getPackageManager();
    Object localObject1 = paramContext.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0).iterator();
    for (;;)
    {
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ResolveInfo)((Iterator)localObject1).next();
        if (paramContext.checkPermission("com.google.android.c2dm.permission.RECEIVE", ((ResolveInfo)localObject2).serviceInfo.packageName) != 0) {}
      }
      try
      {
        localObject3 = paramContext.getApplicationInfo(((ResolveInfo)localObject2).serviceInfo.packageName, 0);
        int i = ((ApplicationInfo)localObject3).uid;
        Log.w("InstanceID/Rpc", 17 + "Found " + i);
        zzbhs = ((ApplicationInfo)localObject3).uid;
        zzbhq = ((ResolveInfo)localObject2).serviceInfo.packageName;
        localObject2 = zzbhq;
        return (String)localObject2;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException2) {}
      Object localObject2 = String.valueOf(((ResolveInfo)localObject2).serviceInfo.packageName);
      Object localObject3 = String.valueOf("com.google.android.c2dm.intent.REGISTER");
      Log.w("InstanceID/Rpc", String.valueOf(localObject2).length() + 56 + String.valueOf(localObject3).length() + "Possible malicious package " + (String)localObject2 + " declares " + (String)localObject3 + " without permission");
      continue;
      Log.w("InstanceID/Rpc", "Failed to resolve REGISTER intent, falling back");
      try
      {
        localObject1 = paramContext.getApplicationInfo("com.google.android.gms", 0);
        zzbhq = ((ApplicationInfo)localObject1).packageName;
        zzbhs = ((ApplicationInfo)localObject1).uid;
        localObject1 = zzbhq;
        return (String)localObject1;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException1)
      {
        try
        {
          paramContext = paramContext.getApplicationInfo("com.google.android.gsf", 0);
          zzbhq = paramContext.packageName;
          zzbhs = paramContext.uid;
          paramContext = zzbhq;
          return paramContext;
        }
        catch (PackageManager.NameNotFoundException paramContext)
        {
          Log.w("InstanceID/Rpc", "Both Google Play Services and legacy GSF package are missing");
          return null;
        }
      }
    }
  }
  
  private void zzeJ(String paramString)
  {
    if (!"com.google.android.gsf".equals(zzbhq)) {}
    do
    {
      return;
      this.zzbhz += 1;
    } while (this.zzbhz < 3);
    if (this.zzbhz == 3) {
      this.zzbhA = (new Random().nextInt(1000) + 1000);
    }
    this.zzbhA *= 2;
    this.zzbhB = (SystemClock.elapsedRealtime() + this.zzbhA);
    int i = this.zzbhA;
    Log.w("InstanceID/Rpc", String.valueOf(paramString).length() + 31 + "Backoff due to " + paramString + " for " + i);
  }
  
  private void zzh(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 instanceof ConditionVariable)) {
      ((ConditionVariable)paramObject1).open();
    }
    Message localMessage;
    if ((paramObject1 instanceof Messenger))
    {
      paramObject1 = (Messenger)paramObject1;
      localMessage = Message.obtain();
      localMessage.obj = paramObject2;
    }
    try
    {
      ((Messenger)paramObject1).send(localMessage);
      return;
    }
    catch (RemoteException paramObject1)
    {
      paramObject1 = String.valueOf(paramObject1);
      Log.w("InstanceID/Rpc", String.valueOf(paramObject1).length() + 24 + "Failed to send response " + (String)paramObject1);
    }
  }
  
  private void zzi(String paramString, Object paramObject)
  {
    synchronized (getClass())
    {
      Object localObject = this.zzbhu.get(paramString);
      this.zzbhu.put(paramString, paramObject);
      zzh(localObject, paramObject);
      return;
    }
  }
  
  void zzGy()
  {
    if (this.zzbge != null) {
      return;
    }
    zzbi(this.zzqr);
    this.zzbge = new Messenger(new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        zzf.this.zze(paramAnonymousMessage);
      }
    });
  }
  
  Intent zza(Bundle paramBundle, KeyPair paramKeyPair)
    throws IOException
  {
    Intent localIntent = zzb(paramBundle, paramKeyPair);
    Object localObject = localIntent;
    if (localIntent != null)
    {
      localObject = localIntent;
      if (localIntent.hasExtra("google.messenger"))
      {
        paramBundle = zzb(paramBundle, paramKeyPair);
        localObject = paramBundle;
        if (paramBundle != null)
        {
          localObject = paramBundle;
          if (paramBundle.hasExtra("google.messenger")) {
            localObject = null;
          }
        }
      }
    }
    return (Intent)localObject;
  }
  
  public void zza(Bundle paramBundle, KeyPair paramKeyPair, String paramString)
    throws IOException
  {
    long l1 = SystemClock.elapsedRealtime();
    if ((this.zzbhB != 0L) && (l1 <= this.zzbhB))
    {
      long l2 = this.zzbhB;
      int i = this.zzbhA;
      Log.w("InstanceID/Rpc", 78 + "Backoff mode, next request attempt: " + (l2 - l1) + " interval: " + i);
      throw new IOException("RETRY_LATER");
    }
    zzGy();
    if (zzbhq == null) {
      throw new IOException("MISSING_INSTANCEID_SERVICE");
    }
    this.zzbhx = SystemClock.elapsedRealtime();
    Intent localIntent = new Intent("com.google.android.c2dm.intent.REGISTER");
    localIntent.setPackage(zzbhq);
    paramBundle.putString("gmsv", Integer.toString(FirebaseInstanceId.zzK(this.zzqr, zzbi(this.zzqr))));
    paramBundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
    paramBundle.putString("app_ver", Integer.toString(FirebaseInstanceId.zzbU(this.zzqr)));
    paramBundle.putString("app_ver_name", FirebaseInstanceId.zzbg(this.zzqr));
    paramBundle.putString("cliv", "fiid-10084000");
    paramBundle.putString("appid", FirebaseInstanceId.zza(paramKeyPair));
    String str = FirebaseInstanceId.zzbT(this.zzqr);
    if (str != null) {
      paramBundle.putString("gmp_app_id", str);
    }
    str = FirebaseInstanceId.zzv(paramKeyPair.getPublic().getEncoded());
    paramBundle.putString("pub2", str);
    paramBundle.putString("sig", zza(paramKeyPair, new String[] { this.zzqr.getPackageName(), str }));
    localIntent.putExtras(paramBundle);
    zzs(localIntent);
    zzb(localIntent, paramString);
  }
  
  protected void zzb(Intent paramIntent, String paramString)
  {
    this.zzbhx = SystemClock.elapsedRealtime();
    paramIntent.putExtra("kid", String.valueOf(paramString).length() + 5 + "|ID|" + paramString + "|");
    paramIntent.putExtra("X-kid", String.valueOf(paramString).length() + 5 + "|ID|" + paramString + "|");
    boolean bool = "com.google.android.gsf".equals(zzbhq);
    if (Log.isLoggable("InstanceID/Rpc", 3))
    {
      paramString = String.valueOf(paramIntent.getExtras());
      Log.d("InstanceID/Rpc", String.valueOf(paramString).length() + 8 + "Sending " + paramString);
    }
    if (bool)
    {
      this.zzqr.startService(paramIntent);
      return;
    }
    paramIntent.putExtra("google.messenger", this.zzbge);
    if ((this.zzbhv != null) || (this.zzbhw != null))
    {
      paramString = Message.obtain();
      paramString.obj = paramIntent;
      try
      {
        if (this.zzbhv == null) {
          break label250;
        }
        this.zzbhv.send(paramString);
        return;
      }
      catch (RemoteException paramString)
      {
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
          Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
        }
      }
    }
    else
    {
      this.zzqr.startService(paramIntent);
      return;
    }
    label250:
    this.zzbhw.send(paramString);
  }
  
  public void zze(Message paramMessage)
  {
    if (paramMessage == null) {
      return;
    }
    if ((paramMessage.obj instanceof Intent))
    {
      Object localObject = (Intent)paramMessage.obj;
      ((Intent)localObject).setExtrasClassLoader(MessengerCompat.class.getClassLoader());
      if (((Intent)localObject).hasExtra("google.messenger"))
      {
        localObject = ((Intent)localObject).getParcelableExtra("google.messenger");
        if ((localObject instanceof MessengerCompat)) {
          this.zzbhw = ((MessengerCompat)localObject);
        }
        if ((localObject instanceof Messenger)) {
          this.zzbhv = ((Messenger)localObject);
        }
      }
      zzv((Intent)paramMessage.obj);
      return;
    }
    Log.w("InstanceID/Rpc", "Dropping invalid message");
  }
  
  void zzs(Intent paramIntent)
  {
    try
    {
      if (this.zzbga == null)
      {
        Intent localIntent = new Intent();
        localIntent.setPackage("com.google.example.invalidpackage");
        this.zzbga = PendingIntent.getBroadcast(this.zzqr, 0, localIntent, 0);
      }
      paramIntent.putExtra("app", this.zzbga);
      return;
    }
    finally {}
  }
  
  String zzt(Intent paramIntent)
    throws IOException
  {
    if (paramIntent == null) {
      throw new IOException("SERVICE_NOT_AVAILABLE");
    }
    String str2 = paramIntent.getStringExtra("registration_id");
    String str1 = str2;
    if (str2 == null) {
      str1 = paramIntent.getStringExtra("unregistered");
    }
    if (str1 == null)
    {
      str1 = paramIntent.getStringExtra("error");
      if (str1 != null) {
        throw new IOException(str1);
      }
      paramIntent = String.valueOf(paramIntent.getExtras());
      Log.w("InstanceID/Rpc", String.valueOf(paramIntent).length() + 29 + "Unexpected response from GCM " + paramIntent, new Throwable());
      throw new IOException("SERVICE_NOT_AVAILABLE");
    }
    return str1;
  }
  
  void zzu(Intent paramIntent)
  {
    Object localObject2 = paramIntent.getStringExtra("error");
    if (localObject2 == null)
    {
      paramIntent = String.valueOf(paramIntent.getExtras());
      Log.w("InstanceID/Rpc", String.valueOf(paramIntent).length() + 49 + "Unexpected response, no error or registration id " + paramIntent);
      return;
    }
    Object localObject1;
    label160:
    Object localObject3;
    if (Log.isLoggable("InstanceID/Rpc", 3))
    {
      localObject1 = String.valueOf(localObject2);
      if (((String)localObject1).length() != 0)
      {
        localObject1 = "Received InstanceID error ".concat((String)localObject1);
        Log.d("InstanceID/Rpc", (String)localObject1);
      }
    }
    else
    {
      if (!((String)localObject2).startsWith("|")) {
        break label399;
      }
      Object localObject4 = ((String)localObject2).split("\\|");
      if (!"ID".equals(localObject4[1]))
      {
        localObject1 = String.valueOf(localObject2);
        if (((String)localObject1).length() == 0) {
          break label333;
        }
        localObject1 = "Unexpected structured response ".concat((String)localObject1);
        Log.w("InstanceID/Rpc", (String)localObject1);
      }
      if (localObject4.length <= 2) {
        break label348;
      }
      localObject3 = localObject4[2];
      localObject4 = localObject4[3];
      localObject2 = localObject3;
      localObject1 = localObject4;
      if (((String)localObject4).startsWith(":"))
      {
        localObject1 = ((String)localObject4).substring(1);
        localObject2 = localObject3;
      }
      label218:
      paramIntent.putExtra("error", (String)localObject1);
    }
    for (;;)
    {
      if (localObject2 == null) {
        zzG(localObject1);
      }
      for (;;)
      {
        long l = paramIntent.getLongExtra("Retry-After", 0L);
        if (l <= 0L) {
          break label370;
        }
        this.zzbhy = SystemClock.elapsedRealtime();
        this.zzbhA = ((int)l * 1000);
        this.zzbhB = (SystemClock.elapsedRealtime() + this.zzbhA);
        int i = this.zzbhA;
        Log.w("InstanceID/Rpc", 52 + "Explicit request from server to backoff: " + i);
        return;
        localObject1 = new String("Received InstanceID error ");
        break;
        label333:
        localObject1 = new String("Unexpected structured response ");
        break label160;
        label348:
        localObject1 = "UNKNOWN";
        localObject2 = null;
        break label218;
        zzi((String)localObject2, localObject1);
      }
      label370:
      if ((!"SERVICE_NOT_AVAILABLE".equals(localObject1)) && (!"AUTHENTICATION_FAILED".equals(localObject1))) {
        break;
      }
      zzeJ((String)localObject1);
      return;
      label399:
      localObject3 = null;
      localObject1 = localObject2;
      localObject2 = localObject3;
    }
  }
  
  void zzv(Intent paramIntent)
  {
    if (paramIntent == null) {
      if (Log.isLoggable("InstanceID/Rpc", 3)) {
        Log.d("InstanceID/Rpc", "Unexpected response: null");
      }
    }
    do
    {
      return;
      if ("com.google.android.c2dm.intent.REGISTRATION".equals(paramIntent.getAction())) {
        break;
      }
    } while (!Log.isLoggable("InstanceID/Rpc", 3));
    paramIntent = String.valueOf(paramIntent.getAction());
    if (paramIntent.length() != 0) {}
    for (paramIntent = "Unexpected response ".concat(paramIntent);; paramIntent = new String("Unexpected response "))
    {
      Log.d("InstanceID/Rpc", paramIntent);
      return;
    }
    String str = paramIntent.getStringExtra("registration_id");
    Object localObject1 = str;
    if (str == null) {
      localObject1 = paramIntent.getStringExtra("unregistered");
    }
    if (localObject1 == null)
    {
      zzu(paramIntent);
      return;
    }
    this.zzbhx = SystemClock.elapsedRealtime();
    this.zzbhB = 0L;
    this.zzbhz = 0;
    this.zzbhA = 0;
    Object localObject2;
    if (((String)localObject1).startsWith("|"))
    {
      localObject2 = ((String)localObject1).split("\\|");
      if (!"ID".equals(localObject2[1]))
      {
        localObject1 = String.valueOf(localObject1);
        if (((String)localObject1).length() != 0)
        {
          localObject1 = "Unexpected structured response ".concat((String)localObject1);
          label196:
          Log.w("InstanceID/Rpc", (String)localObject1);
        }
      }
      else
      {
        str = localObject2[2];
        if (localObject2.length > 4)
        {
          if (!"SYNC".equals(localObject2[3])) {
            break label314;
          }
          FirebaseInstanceId.zzbh(this.zzqr);
        }
        label235:
        localObject2 = localObject2[(localObject2.length - 1)];
        localObject1 = localObject2;
        if (((String)localObject2).startsWith(":")) {
          localObject1 = ((String)localObject2).substring(1);
        }
        paramIntent.putExtra("registration_id", (String)localObject1);
      }
    }
    for (localObject1 = str;; localObject1 = null)
    {
      if (localObject1 == null)
      {
        if (!Log.isLoggable("InstanceID/Rpc", 3)) {
          break;
        }
        Log.d("InstanceID/Rpc", "Ignoring response without a request ID");
        return;
        localObject1 = new String("Unexpected structured response ");
        break label196;
        label314:
        if (!"RST".equals(localObject2[3])) {
          break label235;
        }
        FirebaseInstanceId.zza(this.zzqr, zzd.zzb(this.zzqr, null).zzaag());
        paramIntent.removeExtra("registration_id");
        zzi(str, paramIntent);
        return;
      }
      zzi((String)localObject1, paramIntent);
      return;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\firebase\iid\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */