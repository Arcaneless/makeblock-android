package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zza;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.internal.zzbqj;
import com.google.android.gms.internal.zzbqk;
import com.google.android.gms.internal.zzbql;
import com.google.android.gms.internal.zzbqm;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.GetTokenResult;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FirebaseApp
{
  public static final String DEFAULT_APP_NAME = "[DEFAULT]";
  private static final List<String> zzbUA;
  private static final List<String> zzbUB;
  private static final Set<String> zzbUC;
  private static final List<String> zzbUy = Arrays.asList(new String[] { "com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId" });
  private static final List<String> zzbUz = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
  static final Map<String, FirebaseApp> zzbha = new ArrayMap();
  private static final Object zztU;
  private final String mName;
  private final FirebaseOptions zzbUD;
  private final AtomicBoolean zzbUE = new AtomicBoolean(false);
  private final AtomicBoolean zzbUF = new AtomicBoolean();
  private final List<zza> zzbUG = new CopyOnWriteArrayList();
  private final List<zzb> zzbUH = new CopyOnWriteArrayList();
  private final List<Object> zzbUI = new CopyOnWriteArrayList();
  private zzbql zzbUJ;
  private final Context zzvZ;
  
  static
  {
    zzbUA = Arrays.asList(new String[] { "com.google.android.gms.measurement.AppMeasurement" });
    zzbUB = Arrays.asList(new String[0]);
    zzbUC = Collections.emptySet();
    zztU = new Object();
  }
  
  protected FirebaseApp(Context paramContext, String paramString, FirebaseOptions paramFirebaseOptions)
  {
    this.zzvZ = ((Context)zzac.zzw(paramContext));
    this.mName = zzac.zzdv(paramString);
    this.zzbUD = ((FirebaseOptions)zzac.zzw(paramFirebaseOptions));
  }
  
  public static List<FirebaseApp> getApps(Context paramContext)
  {
    zzbqk localzzbqk = zzbqk.zzbZ(paramContext);
    ArrayList localArrayList;
    synchronized (zztU)
    {
      localArrayList = new ArrayList(zzbha.values());
      Object localObject2 = zzbqk.zzaap().zzaaq();
      ((Set)localObject2).removeAll(zzbha.keySet());
      localObject2 = ((Set)localObject2).iterator();
      if (((Iterator)localObject2).hasNext())
      {
        String str = (String)((Iterator)localObject2).next();
        localzzbqk.zzjD(str);
        localArrayList.add(initializeApp(paramContext, null, str));
      }
    }
    return localArrayList;
  }
  
  @Nullable
  public static FirebaseApp getInstance()
  {
    synchronized (zztU)
    {
      Object localObject2 = (FirebaseApp)zzbha.get("[DEFAULT]");
      if (localObject2 == null)
      {
        localObject2 = String.valueOf(zzt.zzyK());
        throw new IllegalStateException(String.valueOf(localObject2).length() + 116 + "Default FirebaseApp is not initialized in this process " + (String)localObject2 + ". Make sure to call FirebaseApp.initializeApp(Context) first.");
      }
    }
    return localFirebaseApp;
  }
  
  public static FirebaseApp getInstance(@NonNull String paramString)
  {
    for (;;)
    {
      synchronized (zztU)
      {
        localObject1 = (FirebaseApp)zzbha.get(zzit(paramString));
        if (localObject1 != null) {
          return (FirebaseApp)localObject1;
        }
        localObject1 = zzTv();
        if (((List)localObject1).isEmpty())
        {
          localObject1 = "";
          throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", new Object[] { paramString, localObject1 }));
        }
      }
      Object localObject1 = String.valueOf(TextUtils.join(", ", (Iterable)localObject1));
      if (((String)localObject1).length() != 0) {
        localObject1 = "Available app names: ".concat((String)localObject1);
      } else {
        localObject1 = new String("Available app names: ");
      }
    }
  }
  
  @Nullable
  public static FirebaseApp initializeApp(Context paramContext)
  {
    FirebaseOptions localFirebaseOptions;
    synchronized (zztU)
    {
      if (zzbha.containsKey("[DEFAULT]"))
      {
        paramContext = getInstance();
        return paramContext;
      }
      localFirebaseOptions = FirebaseOptions.fromResource(paramContext);
      if (localFirebaseOptions == null) {
        return null;
      }
    }
    paramContext = initializeApp(paramContext, localFirebaseOptions);
    return paramContext;
  }
  
  public static FirebaseApp initializeApp(Context paramContext, FirebaseOptions paramFirebaseOptions)
  {
    return initializeApp(paramContext, paramFirebaseOptions, "[DEFAULT]");
  }
  
  public static FirebaseApp initializeApp(Context paramContext, FirebaseOptions paramFirebaseOptions, String paramString)
  {
    zzbqk localzzbqk = zzbqk.zzbZ(paramContext);
    zzbQ(paramContext);
    paramString = zzit(paramString);
    if (paramContext.getApplicationContext() == null) {}
    synchronized (zztU)
    {
      while (!zzbha.containsKey(paramString))
      {
        bool = true;
        zzac.zza(bool, String.valueOf(paramString).length() + 33 + "FirebaseApp name " + paramString + " already exists!");
        zzac.zzb(paramContext, "Application context cannot be null.");
        paramContext = new FirebaseApp(paramContext, paramString, paramFirebaseOptions);
        zzbha.put(paramString, paramContext);
        localzzbqk.zzg(paramContext);
        paramContext.zza(FirebaseApp.class, paramContext, zzbUy);
        if (paramContext.zzTt())
        {
          paramContext.zza(FirebaseApp.class, paramContext, zzbUz);
          paramContext.zza(Context.class, paramContext.getApplicationContext(), zzbUA);
        }
        return paramContext;
        paramContext = paramContext.getApplicationContext();
      }
      boolean bool = false;
    }
  }
  
  private void zzTs()
  {
    if (!this.zzbUF.get()) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zza(bool, "FirebaseApp was deleted");
      return;
    }
  }
  
  private static List<String> zzTv()
  {
    zza localzza = new zza();
    synchronized (zztU)
    {
      localObject2 = zzbha.values().iterator();
      if (((Iterator)localObject2).hasNext()) {
        localzza.add(((FirebaseApp)((Iterator)localObject2).next()).getName());
      }
    }
    Object localObject2 = zzbqk.zzaap();
    if (localObject2 != null) {
      localCollection.addAll(((zzbqk)localObject2).zzaaq());
    }
    ??? = new ArrayList(localCollection);
    Collections.sort((List)???);
    return (List<String>)???;
  }
  
  private void zzTw()
  {
    zza(FirebaseApp.class, this, zzbUy);
    if (zzTt())
    {
      zza(FirebaseApp.class, this, zzbUz);
      zza(Context.class, this.zzvZ, zzbUA);
    }
  }
  
  private <T> void zza(Class<T> paramClass, T paramT, Iterable<String> paramIterable)
  {
    boolean bool = ContextCompat.isDeviceProtectedStorage(this.zzvZ);
    if (bool) {
      zzc.zzbS(this.zzvZ);
    }
    Iterator localIterator = paramIterable.iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        paramIterable = (String)localIterator.next();
        if (bool) {}
        try
        {
          if (zzbUB.contains(paramIterable))
          {
            Method localMethod = Class.forName(paramIterable).getMethod("getInstance", new Class[] { paramClass });
            int i = localMethod.getModifiers();
            if ((Modifier.isPublic(i)) && (Modifier.isStatic(i))) {
              localMethod.invoke(null, new Object[] { paramT });
            }
          }
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
          if (zzbUC.contains(paramIterable)) {
            throw new IllegalStateException(String.valueOf(paramIterable).concat(" is missing, but is required. Check if it has been removed by Proguard."));
          }
          Log.d("FirebaseApp", String.valueOf(paramIterable).concat(" is not linked. Skipping initialization."));
        }
        catch (NoSuchMethodException paramClass)
        {
          throw new IllegalStateException(String.valueOf(paramIterable).concat("#getInstance has been removed by Proguard. Add keep rule to prevent it."));
        }
        catch (InvocationTargetException paramIterable)
        {
          Log.wtf("FirebaseApp", "Firebase API initialization failure.", paramIterable);
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          paramIterable = String.valueOf(paramIterable);
          if (paramIterable.length() != 0) {}
          for (paramIterable = "Failed to initialize ".concat(paramIterable);; paramIterable = new String("Failed to initialize "))
          {
            Log.wtf("FirebaseApp", paramIterable, localIllegalAccessException);
            break;
          }
        }
      }
    }
  }
  
  public static void zzaQ(boolean paramBoolean)
  {
    synchronized (zztU)
    {
      Iterator localIterator = new ArrayList(zzbha.values()).iterator();
      while (localIterator.hasNext())
      {
        FirebaseApp localFirebaseApp = (FirebaseApp)localIterator.next();
        if (localFirebaseApp.zzbUE.get()) {
          localFirebaseApp.zzaR(paramBoolean);
        }
      }
    }
  }
  
  private void zzaR(boolean paramBoolean)
  {
    Log.d("FirebaseApp", "Notifying background state change listeners.");
    Iterator localIterator = this.zzbUH.iterator();
    while (localIterator.hasNext()) {
      ((zzb)localIterator.next()).zzaQ(paramBoolean);
    }
  }
  
  @TargetApi(14)
  private static void zzbQ(Context paramContext)
  {
    if ((zzs.zzyA()) && ((paramContext.getApplicationContext() instanceof Application))) {
      zzbqj.zza((Application)paramContext.getApplicationContext());
    }
  }
  
  private static String zzit(@NonNull String paramString)
  {
    return paramString.trim();
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof FirebaseApp)) {
      return false;
    }
    return this.mName.equals(((FirebaseApp)paramObject).getName());
  }
  
  @NonNull
  public Context getApplicationContext()
  {
    zzTs();
    return this.zzvZ;
  }
  
  @NonNull
  public String getName()
  {
    zzTs();
    return this.mName;
  }
  
  @NonNull
  public FirebaseOptions getOptions()
  {
    zzTs();
    return this.zzbUD;
  }
  
  public Task<GetTokenResult> getToken(boolean paramBoolean)
  {
    zzTs();
    if (this.zzbUJ == null) {
      return Tasks.forException(new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode."));
    }
    return this.zzbUJ.zzaS(paramBoolean);
  }
  
  public int hashCode()
  {
    return this.mName.hashCode();
  }
  
  public void setAutomaticResourceManagementEnabled(boolean paramBoolean)
  {
    zzTs();
    AtomicBoolean localAtomicBoolean = this.zzbUE;
    boolean bool;
    if (!paramBoolean)
    {
      bool = true;
      if (localAtomicBoolean.compareAndSet(bool, paramBoolean))
      {
        bool = zzbqj.zzaan().zzaao();
        if ((!paramBoolean) || (!bool)) {
          break label50;
        }
        zzaR(true);
      }
    }
    label50:
    while ((paramBoolean) || (!bool))
    {
      return;
      bool = false;
      break;
    }
    zzaR(false);
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("name", this.mName).zzg("options", this.zzbUD).toString();
  }
  
  public boolean zzTt()
  {
    return "[DEFAULT]".equals(getName());
  }
  
  public String zzTu()
  {
    String str1 = String.valueOf(zzc.zzs(getName().getBytes()));
    String str2 = String.valueOf(zzc.zzs(getOptions().getApplicationId().getBytes()));
    return String.valueOf(str1).length() + 1 + String.valueOf(str2).length() + str1 + "+" + str2;
  }
  
  public void zza(@NonNull zzbql paramzzbql)
  {
    this.zzbUJ = ((zzbql)zzac.zzw(paramzzbql));
  }
  
  @UiThread
  public void zza(@NonNull zzbqm paramzzbqm)
  {
    Log.d("FirebaseApp", "Notifying auth state listeners.");
    Iterator localIterator = this.zzbUG.iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      ((zza)localIterator.next()).zzb(paramzzbqm);
      i += 1;
    }
    Log.d("FirebaseApp", String.format("Notified %d auth state listeners.", new Object[] { Integer.valueOf(i) }));
  }
  
  public void zza(@NonNull zza paramzza)
  {
    zzTs();
    zzac.zzw(paramzza);
    this.zzbUG.add(paramzza);
  }
  
  public void zza(zzb paramzzb)
  {
    zzTs();
    if ((this.zzbUE.get()) && (zzbqj.zzaan().zzaao())) {
      paramzzb.zzaQ(true);
    }
    this.zzbUH.add(paramzzb);
  }
  
  public static abstract interface zza
  {
    public abstract void zzb(@NonNull zzbqm paramzzbqm);
  }
  
  public static abstract interface zzb
  {
    public abstract void zzaQ(boolean paramBoolean);
  }
  
  @TargetApi(24)
  private static class zzc
    extends BroadcastReceiver
  {
    private static AtomicReference<zzc> zzbUK = new AtomicReference();
    private final Context zzvZ;
    
    public zzc(Context paramContext)
    {
      this.zzvZ = paramContext;
    }
    
    private static void zzbR(Context paramContext)
    {
      if (zzbUK.get() == null)
      {
        zzc localzzc = new zzc(paramContext);
        if (zzbUK.compareAndSet(null, localzzc)) {
          paramContext.registerReceiver(localzzc, new IntentFilter("android.intent.action.USER_UNLOCKED"));
        }
      }
    }
    
    public void onReceive(Context arg1, Intent paramIntent)
    {
      synchronized ()
      {
        paramIntent = FirebaseApp.zzbha.values().iterator();
        if (paramIntent.hasNext()) {
          FirebaseApp.zza((FirebaseApp)paramIntent.next());
        }
      }
      unregister();
    }
    
    public void unregister()
    {
      this.zzvZ.unregisterReceiver(this);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\firebase\FirebaseApp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */