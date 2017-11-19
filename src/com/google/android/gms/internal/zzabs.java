package com.google.android.gms.internal;

import android.os.Binder;

public abstract class zzabs<T>
{
  private static String READ_PERMISSION = "com.google.android.providers.gsf.permission.READ_GSERVICES";
  private static zza zzaCd;
  private static int zzaCe;
  private static final Object zztU = new Object();
  protected final String zzAH;
  protected final T zzAI;
  private T zzaCf = null;
  
  static
  {
    zzaCd = null;
    zzaCe = 0;
  }
  
  protected zzabs(String paramString, T paramT)
  {
    this.zzAH = paramString;
    this.zzAI = paramT;
  }
  
  public static zzabs<String> zzA(String paramString1, String paramString2)
  {
    new zzabs(paramString1, paramString2)
    {
      protected String zzdi(String paramAnonymousString)
      {
        paramAnonymousString = this.zzAH;
        paramAnonymousString = (String)this.zzAI;
        throw new NullPointerException();
      }
    };
  }
  
  public static zzabs<Float> zza(String paramString, Float paramFloat)
  {
    new zzabs(paramString, paramFloat)
    {
      protected Float zzdh(String paramAnonymousString)
      {
        paramAnonymousString = this.zzAH;
        paramAnonymousString = (Float)this.zzAI;
        throw new NullPointerException();
      }
    };
  }
  
  public static zzabs<Integer> zza(String paramString, Integer paramInteger)
  {
    new zzabs(paramString, paramInteger)
    {
      protected Integer zzdg(String paramAnonymousString)
      {
        paramAnonymousString = this.zzAH;
        paramAnonymousString = (Integer)this.zzAI;
        throw new NullPointerException();
      }
    };
  }
  
  public static zzabs<Long> zza(String paramString, Long paramLong)
  {
    new zzabs(paramString, paramLong)
    {
      protected Long zzdf(String paramAnonymousString)
      {
        paramAnonymousString = this.zzAH;
        paramAnonymousString = (Long)this.zzAI;
        throw new NullPointerException();
      }
    };
  }
  
  public static zzabs<Boolean> zzj(String paramString, boolean paramBoolean)
  {
    new zzabs(paramString, Boolean.valueOf(paramBoolean))
    {
      protected Boolean zzde(String paramAnonymousString)
      {
        paramAnonymousString = this.zzAH;
        paramAnonymousString = (Boolean)this.zzAI;
        throw new NullPointerException();
      }
    };
  }
  
  public final T get()
  {
    try
    {
      Object localObject1 = zzdd(this.zzAH);
      return (T)localObject1;
    }
    catch (SecurityException localSecurityException)
    {
      long l = Binder.clearCallingIdentity();
      try
      {
        Object localObject2 = zzdd(this.zzAH);
        return (T)localObject2;
      }
      finally
      {
        Binder.restoreCallingIdentity(l);
      }
    }
  }
  
  protected abstract T zzdd(String paramString);
  
  private static abstract interface zza
  {
    public abstract Long getLong(String paramString, Long paramLong);
    
    public abstract String getString(String paramString1, String paramString2);
    
    public abstract Boolean zza(String paramString, Boolean paramBoolean);
    
    public abstract Float zzb(String paramString, Float paramFloat);
    
    public abstract Integer zzb(String paramString, Integer paramInteger);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzabs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */