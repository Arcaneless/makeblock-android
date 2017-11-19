package com.google.android.gms.dynamic;

import android.os.IBinder;
import java.lang.reflect.Field;

public final class zze<T>
  extends zzd.zza
{
  private final T mWrappedObject;
  
  private zze(T paramT)
  {
    this.mWrappedObject = paramT;
  }
  
  public static <T> zzd zzA(T paramT)
  {
    return new zze(paramT);
  }
  
  public static <T> T zzE(zzd paramzzd)
  {
    int j = 0;
    if ((paramzzd instanceof zze)) {
      return (T)((zze)paramzzd).mWrappedObject;
    }
    IBinder localIBinder = paramzzd.asBinder();
    Field[] arrayOfField = localIBinder.getClass().getDeclaredFields();
    paramzzd = null;
    int k = arrayOfField.length;
    int i = 0;
    if (i < k)
    {
      Field localField = arrayOfField[i];
      if (localField.isSynthetic()) {
        break label169;
      }
      j += 1;
      paramzzd = localField;
    }
    label169:
    for (;;)
    {
      i += 1;
      break;
      if (j == 1)
      {
        if (!paramzzd.isAccessible())
        {
          paramzzd.setAccessible(true);
          try
          {
            paramzzd = paramzzd.get(localIBinder);
            return paramzzd;
          }
          catch (NullPointerException paramzzd)
          {
            throw new IllegalArgumentException("Binder object is null.", paramzzd);
          }
          catch (IllegalAccessException paramzzd)
          {
            throw new IllegalArgumentException("Could not access the field in remoteBinder.", paramzzd);
          }
        }
        throw new IllegalArgumentException("IObjectWrapper declared field not private!");
      }
      i = arrayOfField.length;
      throw new IllegalArgumentException(64 + "Unexpected number of IObjectWrapper declared fields: " + i);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\dynamic\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */