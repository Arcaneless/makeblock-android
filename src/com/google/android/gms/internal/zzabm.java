package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

public final class zzabm
  extends Fragment
  implements zzaax
{
  private static WeakHashMap<FragmentActivity, WeakReference<zzabm>> zzaBt = new WeakHashMap();
  private int zzJh = 0;
  private Map<String, zzaaw> zzaBu = new ArrayMap();
  private Bundle zzaBv;
  
  public static zzabm zza(FragmentActivity paramFragmentActivity)
  {
    Object localObject = (WeakReference)zzaBt.get(paramFragmentActivity);
    if (localObject != null)
    {
      localObject = (zzabm)((WeakReference)localObject).get();
      if (localObject != null) {
        return (zzabm)localObject;
      }
    }
    try
    {
      zzabm localzzabm = (zzabm)paramFragmentActivity.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
      if (localzzabm != null)
      {
        localObject = localzzabm;
        if (!localzzabm.isRemoving()) {}
      }
      else
      {
        localObject = new zzabm();
        paramFragmentActivity.getSupportFragmentManager().beginTransaction().add((Fragment)localObject, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
      }
      zzaBt.put(paramFragmentActivity, new WeakReference(localObject));
      return (zzabm)localObject;
    }
    catch (ClassCastException paramFragmentActivity)
    {
      throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", paramFragmentActivity);
    }
  }
  
  private void zzb(final String paramString, @NonNull final zzaaw paramzzaaw)
  {
    if (this.zzJh > 0) {
      new Handler(Looper.getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          zzaaw localzzaaw;
          if (zzabm.zza(zzabm.this) >= 1)
          {
            localzzaaw = paramzzaaw;
            if (zzabm.zzb(zzabm.this) == null) {
              break label101;
            }
          }
          label101:
          for (Bundle localBundle = zzabm.zzb(zzabm.this).getBundle(paramString);; localBundle = null)
          {
            localzzaaw.onCreate(localBundle);
            if (zzabm.zza(zzabm.this) >= 2) {
              paramzzaaw.onStart();
            }
            if (zzabm.zza(zzabm.this) >= 3) {
              paramzzaaw.onStop();
            }
            if (zzabm.zza(zzabm.this) >= 4) {
              paramzzaaw.onDestroy();
            }
            return;
          }
        }
      });
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    Iterator localIterator = this.zzaBu.values().iterator();
    while (localIterator.hasNext()) {
      ((zzaaw)localIterator.next()).dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    Iterator localIterator = this.zzaBu.values().iterator();
    while (localIterator.hasNext()) {
      ((zzaaw)localIterator.next()).onActivityResult(paramInt1, paramInt2, paramIntent);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.zzJh = 1;
    this.zzaBv = paramBundle;
    Iterator localIterator = this.zzaBu.entrySet().iterator();
    if (localIterator.hasNext())
    {
      Object localObject = (Map.Entry)localIterator.next();
      zzaaw localzzaaw = (zzaaw)((Map.Entry)localObject).getValue();
      if (paramBundle != null) {}
      for (localObject = paramBundle.getBundle((String)((Map.Entry)localObject).getKey());; localObject = null)
      {
        localzzaaw.onCreate((Bundle)localObject);
        break;
      }
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    this.zzJh = 4;
    Iterator localIterator = this.zzaBu.values().iterator();
    while (localIterator.hasNext()) {
      ((zzaaw)localIterator.next()).onDestroy();
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (paramBundle == null) {}
    for (;;)
    {
      return;
      Iterator localIterator = this.zzaBu.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        Bundle localBundle = new Bundle();
        ((zzaaw)localEntry.getValue()).onSaveInstanceState(localBundle);
        paramBundle.putBundle((String)localEntry.getKey(), localBundle);
      }
    }
  }
  
  public void onStart()
  {
    super.onStart();
    this.zzJh = 2;
    Iterator localIterator = this.zzaBu.values().iterator();
    while (localIterator.hasNext()) {
      ((zzaaw)localIterator.next()).onStart();
    }
  }
  
  public void onStop()
  {
    super.onStop();
    this.zzJh = 3;
    Iterator localIterator = this.zzaBu.values().iterator();
    while (localIterator.hasNext()) {
      ((zzaaw)localIterator.next()).onStop();
    }
  }
  
  public <T extends zzaaw> T zza(String paramString, Class<T> paramClass)
  {
    return (zzaaw)paramClass.cast(this.zzaBu.get(paramString));
  }
  
  public void zza(String paramString, @NonNull zzaaw paramzzaaw)
  {
    if (!this.zzaBu.containsKey(paramString))
    {
      this.zzaBu.put(paramString, paramzzaaw);
      zzb(paramString, paramzzaaw);
      return;
    }
    throw new IllegalArgumentException(String.valueOf(paramString).length() + 59 + "LifecycleCallback with tag " + paramString + " already added to this fragment.");
  }
  
  public FragmentActivity zzws()
  {
    return getActivity();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzabm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */