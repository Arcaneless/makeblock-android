package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.view.SurfaceView;
import android.view.View;
import java.lang.reflect.Constructor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

final class b
{
  private l a = null;
  private boolean b = false;
  private boolean c = false;
  private boolean d = false;
  private boolean e = false;
  private Context f = null;
  private d g = null;
  private String h = "";
  
  public b(d paramd)
  {
    this.g = paramd;
  }
  
  private void a(Runnable paramRunnable)
  {
    if ((this.f instanceof Activity))
    {
      ((Activity)this.f).runOnUiThread(paramRunnable);
      return;
    }
    e.Log(5, "Not running Google VR from an Activity; Ignoring execution request...");
  }
  
  private void a(String paramString)
  {
    if (this.g != null)
    {
      this.g.reportError("Google VR Error [" + this.h + "]", paramString);
      return;
    }
    e.Log(6, "Google VR Error [" + this.h + "]: " + paramString);
  }
  
  private static boolean a(int paramInt)
  {
    return Build.VERSION.SDK_INT >= paramInt;
  }
  
  private boolean a(ClassLoader paramClassLoader)
  {
    try
    {
      paramClassLoader = paramClassLoader.loadClass("com.unity3d.unitygvr.GoogleVR");
      this.a = new l(paramClassLoader, paramClassLoader.getConstructor(new Class[0]).newInstance(new Object[0]));
      this.a.a("initialize", new Class[] { Activity.class, Context.class, SurfaceView.class, Boolean.TYPE });
      this.a.a("deinitialize", new Class[0]);
      this.a.a("load", new Class[] { Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Runnable.class });
      this.a.a("enable", new Class[] { Boolean.TYPE });
      this.a.a("unload", new Class[0]);
      this.a.a("pause", new Class[0]);
      this.a.a("resume", new Class[0]);
      this.a.a("getGvrLayout", new Class[0]);
      return true;
    }
    catch (Exception paramClassLoader)
    {
      a("Exception initializing GoogleVR from Unity library. " + paramClassLoader.getLocalizedMessage());
    }
    return false;
  }
  
  private void b(boolean paramBoolean)
  {
    this.c = paramBoolean;
  }
  
  private boolean b(final Runnable paramRunnable)
  {
    final Semaphore localSemaphore = new Semaphore(0);
    a(new Runnable()
    {
      public final void run()
      {
        try
        {
          paramRunnable.run();
          return;
        }
        catch (Exception localException)
        {
          b.a(b.this, "Exception unloading Google VR on UI Thread. " + localException.getLocalizedMessage());
          return;
        }
        finally
        {
          localSemaphore.release();
        }
      }
    });
    try
    {
      if (!localSemaphore.tryAcquire(4L, TimeUnit.SECONDS))
      {
        a("Timeout waiting for vr state change!");
        return false;
      }
    }
    catch (InterruptedException paramRunnable)
    {
      a("Interrupted while trying to acquire sync lock. " + paramRunnable.getLocalizedMessage());
      return false;
    }
    return true;
  }
  
  private boolean f()
  {
    return this.c;
  }
  
  public final long a(final boolean paramBoolean1, final boolean paramBoolean2, final boolean paramBoolean3, final Runnable paramRunnable)
  {
    final AtomicLong localAtomicLong = new AtomicLong(0L);
    if ((paramBoolean1) || (paramBoolean2)) {}
    for (String str = "Daydream";; str = "Cardboard")
    {
      this.h = str;
      if ((!b(new Runnable()
      {
        public final void run()
        {
          try
          {
            localAtomicLong.set(((Long)b.a(b.this).a("load", new Object[] { Boolean.valueOf(paramBoolean1), Boolean.valueOf(paramBoolean2), Boolean.valueOf(paramBoolean3), paramRunnable })).longValue());
            b.this.c();
            return;
          }
          catch (Exception localException)
          {
            b.a(b.this, "Exception caught while loading GoogleVR. " + localException.getLocalizedMessage());
            localAtomicLong.set(0L);
          }
        }
      })) || (localAtomicLong.longValue() == 0L)) {
        a("Google VR had a fatal issue while loading. VR will not be available.");
      }
      return localAtomicLong.longValue();
    }
  }
  
  public final void a()
  {
    Activity localActivity = (Activity)this.f;
    if ((this.e) && (!this.b) && (localActivity != null))
    {
      this.b = true;
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.HOME");
      localIntent.setFlags(268435456);
      localActivity.startActivity(localIntent);
    }
  }
  
  public final void a(Intent paramIntent)
  {
    if ((paramIntent != null) && (paramIntent.getBooleanExtra("android.intent.extra.VR_LAUNCH", false))) {
      this.e = true;
    }
  }
  
  public final void a(final boolean paramBoolean)
  {
    if ((this.g == null) || (this.f == null)) {
      return;
    }
    a(new Runnable()
    {
      public final void run()
      {
        if (paramBoolean == b.b(b.this)) {}
        do
        {
          do
          {
            return;
            try
            {
              if ((!paramBoolean) || (b.b(b.this))) {
                continue;
              }
              if ((b.a(b.this) != null) && (b.c(b.this) != null) && (!b.c(b.this).addViewToPlayer((View)b.a(b.this).a("getGvrLayout", new Object[0]), true)))
              {
                b.a(b.this, "Unable to add Google VR to view hierarchy.");
                return;
              }
            }
            catch (Exception localException)
            {
              b.a(b.this, "Exception enabling Google VR on UI Thread. " + localException.getLocalizedMessage());
              return;
            }
            if (b.a(b.this) != null) {
              b.a(b.this).a("enable", new Object[] { Boolean.valueOf(true) });
            }
            b.a(b.this, true);
            return;
          } while ((paramBoolean) || (!b.b(b.this)));
          b.a(b.this, false);
          if (b.a(b.this) != null) {
            b.a(b.this).a("enable", new Object[] { Boolean.valueOf(false) });
          }
        } while ((b.a(b.this) == null) || (b.c(b.this) == null));
        b.c(b.this).removeViewFromPlayer((View)b.a(b.this).a("getGvrLayout", new Object[0]));
      }
    });
  }
  
  public final boolean a(Activity paramActivity, Context paramContext, SurfaceView paramSurfaceView)
  {
    if ((paramActivity == null) || (paramContext == null) || (paramSurfaceView == null))
    {
      a("Invalid parameters passed to Google VR initiialization.");
      return false;
    }
    this.f = paramContext;
    if (!a(19))
    {
      a("Google VR requires a device that supports an api version of 19 (KitKat) or better.");
      return false;
    }
    if ((this.e) && (!a(24)))
    {
      a("Daydream requires a device that supports an api version of 24 (Nougat) or better.");
      return false;
    }
    if (!a(UnityPlayer.class.getClassLoader())) {
      return false;
    }
    try
    {
      bool = ((Boolean)this.a.a("initialize", new Object[] { paramActivity, paramContext, paramSurfaceView, Boolean.valueOf(this.e) })).booleanValue();
      if (!bool) {
        a("Unable to initialize GoogleVR library.");
      }
      b(false);
      this.b = false;
      this.d = true;
      this.h = "";
      return true;
    }
    catch (Exception paramActivity)
    {
      for (;;)
      {
        a("Exception while trying to intialize Unity Google VR Library. " + paramActivity.getLocalizedMessage());
        boolean bool = false;
      }
    }
  }
  
  public final void b()
  {
    if (this.a != null) {
      this.a.a("pause", new Object[0]);
    }
  }
  
  public final void c()
  {
    if (this.a != null) {
      this.a.a("resume", new Object[0]);
    }
  }
  
  public final boolean d()
  {
    return this.d;
  }
  
  public final void e()
  {
    a(false);
    this.d = false;
    a(new Runnable()
    {
      public final void run()
      {
        try
        {
          if (b.a(b.this) != null)
          {
            b.a(b.this).a("unload", new Object[0]);
            b.a(b.this).a("deinitialize", new Object[0]);
            b.d(b.this);
          }
          b.a(b.this, false);
          return;
        }
        catch (Exception localException)
        {
          b.a(b.this, "Exception unloading Google VR on UI Thread. " + localException.getLocalizedMessage());
        }
      }
    });
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\unity3d\player\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */