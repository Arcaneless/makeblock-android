package android.arch.lifecycle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
class Lifecycling
{
  private static Map<Class, Constructor<? extends GenericLifecycleObserver>> sCallbackCache;
  private static Constructor<? extends GenericLifecycleObserver> sREFLECTIVE;
  
  static
  {
    try
    {
      sREFLECTIVE = ReflectiveGenericLifecycleObserver.class.getDeclaredConstructor(new Class[] { Object.class });
      sCallbackCache = new HashMap();
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;) {}
    }
  }
  
  static String getAdapterName(String paramString)
  {
    return paramString.replace(".", "_") + "_LifecycleAdapter";
  }
  
  @NonNull
  static GenericLifecycleObserver getCallback(Object paramObject)
  {
    if ((paramObject instanceof GenericLifecycleObserver)) {
      return (GenericLifecycleObserver)paramObject;
    }
    Object localObject;
    try
    {
      localClass = paramObject.getClass();
      localObject = (Constructor)sCallbackCache.get(localClass);
    }
    catch (IllegalAccessException paramObject)
    {
      Class localClass;
      throw new RuntimeException((Throwable)paramObject);
    }
    catch (InstantiationException paramObject)
    {
      throw new RuntimeException((Throwable)paramObject);
    }
    catch (InvocationTargetException paramObject)
    {
      label49:
      label73:
      throw new RuntimeException((Throwable)paramObject);
    }
    return (GenericLifecycleObserver)((Constructor)localObject).newInstance(new Object[] { paramObject });
    Constructor localConstructor = getGeneratedAdapterConstructor(localClass);
    for (;;)
    {
      localObject = localConstructor;
      if (!localConstructor.isAccessible())
      {
        localConstructor.setAccessible(true);
        localObject = localConstructor;
      }
      sCallbackCache.put(localClass, localObject);
      return (GenericLifecycleObserver)((Constructor)localObject).newInstance(new Object[] { paramObject });
      do
      {
        localObject = sREFLECTIVE;
        break label73;
        if (localObject == null) {
          break label49;
        }
        break;
      } while (localConstructor == null);
    }
  }
  
  @Nullable
  private static Constructor<? extends GenericLifecycleObserver> getGeneratedAdapterConstructor(Class<?> paramClass)
  {
    Object localObject = paramClass.getPackage();
    String str;
    if (localObject != null)
    {
      localObject = ((Package)localObject).getName();
      str = paramClass.getCanonicalName();
      if (str != null) {
        break label31;
      }
    }
    for (;;)
    {
      return null;
      localObject = "";
      break;
      label31:
      if (((String)localObject).isEmpty()) {
        str = getAdapterName(str);
      }
      try
      {
        if (((String)localObject).isEmpty()) {}
        for (localObject = str;; localObject = (String)localObject + "." + str)
        {
          localObject = Class.forName((String)localObject).getDeclaredConstructor(new Class[] { paramClass });
          return (Constructor<? extends GenericLifecycleObserver>)localObject;
          str = str.substring(((String)localObject).length() + 1);
          break;
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        paramClass = paramClass.getSuperclass();
        if (paramClass == null) {
          continue;
        }
        return getGeneratedAdapterConstructor(paramClass);
      }
      catch (NoSuchMethodException paramClass)
      {
        throw new RuntimeException(paramClass);
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\lifecycle\Lifecycling.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */