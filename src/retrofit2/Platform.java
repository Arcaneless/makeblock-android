package retrofit2;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

class Platform
{
  private static final Platform PLATFORM = ;
  
  private static Platform findPlatform()
  {
    try
    {
      Class.forName("android.os.Build");
      if (Build.VERSION.SDK_INT != 0)
      {
        Android localAndroid = new Android();
        return localAndroid;
      }
    }
    catch (ClassNotFoundException localClassNotFoundException1)
    {
      try
      {
        Class.forName("java.util.Optional");
        Java8 localJava8 = new Java8();
        return localJava8;
      }
      catch (ClassNotFoundException localClassNotFoundException2)
      {
        try
        {
          Class.forName("org.robovm.apple.foundation.NSObject");
          IOS localIOS = new IOS();
          return localIOS;
        }
        catch (ClassNotFoundException localClassNotFoundException3) {}
      }
    }
    return new Platform();
  }
  
  static Platform get()
  {
    return PLATFORM;
  }
  
  CallAdapter.Factory defaultCallAdapterFactory(Executor paramExecutor)
  {
    if (paramExecutor != null) {
      return new ExecutorCallAdapterFactory(paramExecutor);
    }
    return DefaultCallAdapterFactory.INSTANCE;
  }
  
  Executor defaultCallbackExecutor()
  {
    return null;
  }
  
  Object invokeDefaultMethod(Method paramMethod, Class<?> paramClass, Object paramObject, Object... paramVarArgs)
    throws Throwable
  {
    throw new UnsupportedOperationException();
  }
  
  boolean isDefaultMethod(Method paramMethod)
  {
    return false;
  }
  
  static class Android
    extends Platform
  {
    CallAdapter.Factory defaultCallAdapterFactory(Executor paramExecutor)
    {
      return new ExecutorCallAdapterFactory(paramExecutor);
    }
    
    public Executor defaultCallbackExecutor()
    {
      return new MainThreadExecutor();
    }
    
    static class MainThreadExecutor
      implements Executor
    {
      private final Handler handler = new Handler(Looper.getMainLooper());
      
      public void execute(Runnable paramRunnable)
      {
        this.handler.post(paramRunnable);
      }
    }
  }
  
  static class IOS
    extends Platform
  {
    CallAdapter.Factory defaultCallAdapterFactory(Executor paramExecutor)
    {
      return new ExecutorCallAdapterFactory(paramExecutor);
    }
    
    public Executor defaultCallbackExecutor()
    {
      return new MainThreadExecutor();
    }
    
    static class MainThreadExecutor
      implements Executor
    {
      private static Method addOperation;
      private static Object queue;
      
      static
      {
        try
        {
          Class localClass = Class.forName("org.robovm.apple.foundation.NSOperationQueue");
          queue = localClass.getDeclaredMethod("getMainQueue", new Class[0]).invoke(null, new Object[0]);
          addOperation = localClass.getDeclaredMethod("addOperation", new Class[] { Runnable.class });
          return;
        }
        catch (Exception localException)
        {
          throw new AssertionError(localException);
        }
      }
      
      public void execute(Runnable paramRunnable)
      {
        try
        {
          addOperation.invoke(queue, new Object[] { paramRunnable });
          return;
        }
        catch (IllegalAccessException paramRunnable)
        {
          throw new AssertionError(paramRunnable);
        }
        catch (InvocationTargetException paramRunnable)
        {
          paramRunnable = paramRunnable.getCause();
          if ((paramRunnable instanceof RuntimeException)) {
            throw ((RuntimeException)paramRunnable);
          }
          if ((paramRunnable instanceof Error)) {
            throw ((Error)paramRunnable);
          }
          throw new RuntimeException(paramRunnable);
        }
        catch (IllegalArgumentException paramRunnable)
        {
          for (;;) {}
        }
      }
    }
  }
  
  @IgnoreJRERequirement
  static class Java8
    extends Platform
  {
    Object invokeDefaultMethod(Method paramMethod, Class<?> paramClass, Object paramObject, Object... paramVarArgs)
      throws Throwable
    {
      Constructor localConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(new Class[] { Class.class, Integer.TYPE });
      localConstructor.setAccessible(true);
      return ((MethodHandles.Lookup)localConstructor.newInstance(new Object[] { paramClass, Integer.valueOf(-1) })).unreflectSpecial(paramMethod, paramClass).bindTo(paramObject).invokeWithArguments(paramVarArgs);
    }
    
    boolean isDefaultMethod(Method paramMethod)
    {
      return paramMethod.isDefault();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\retrofit2\Platform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */