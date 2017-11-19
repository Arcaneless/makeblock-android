package android.arch.lifecycle;

import android.annotation.SuppressLint;
import android.app.Application;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ViewModelProviders
{
  @SuppressLint({"StaticFieldLeak"})
  private static DefaultFactory sDefaultFactory;
  
  private static void initializeFactoryIfNeeded(Application paramApplication)
  {
    if (sDefaultFactory == null) {
      sDefaultFactory = new DefaultFactory(paramApplication);
    }
  }
  
  @MainThread
  public static ViewModelProvider of(@NonNull Fragment paramFragment)
  {
    FragmentActivity localFragmentActivity = paramFragment.getActivity();
    if (localFragmentActivity == null) {
      throw new IllegalArgumentException("Can't create ViewModelProvider for detached fragment");
    }
    initializeFactoryIfNeeded(localFragmentActivity.getApplication());
    return new ViewModelProvider(ViewModelStores.of(paramFragment), sDefaultFactory);
  }
  
  @MainThread
  public static ViewModelProvider of(@NonNull Fragment paramFragment, @NonNull ViewModelProvider.Factory paramFactory)
  {
    return new ViewModelProvider(ViewModelStores.of(paramFragment), paramFactory);
  }
  
  @MainThread
  public static ViewModelProvider of(@NonNull FragmentActivity paramFragmentActivity)
  {
    initializeFactoryIfNeeded(paramFragmentActivity.getApplication());
    return new ViewModelProvider(ViewModelStores.of(paramFragmentActivity), sDefaultFactory);
  }
  
  @MainThread
  public static ViewModelProvider of(@NonNull FragmentActivity paramFragmentActivity, @NonNull ViewModelProvider.Factory paramFactory)
  {
    return new ViewModelProvider(ViewModelStores.of(paramFragmentActivity), paramFactory);
  }
  
  public static class DefaultFactory
    extends ViewModelProvider.NewInstanceFactory
  {
    private Application mApplication;
    
    public DefaultFactory(@NonNull Application paramApplication)
    {
      this.mApplication = paramApplication;
    }
    
    public <T extends ViewModel> T create(Class<T> paramClass)
    {
      if (AndroidViewModel.class.isAssignableFrom(paramClass)) {
        try
        {
          ViewModel localViewModel = (ViewModel)paramClass.getConstructor(new Class[] { Application.class }).newInstance(new Object[] { this.mApplication });
          return localViewModel;
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          throw new RuntimeException("Cannot create an instance of " + paramClass, localNoSuchMethodException);
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          throw new RuntimeException("Cannot create an instance of " + paramClass, localIllegalAccessException);
        }
        catch (InstantiationException localInstantiationException)
        {
          throw new RuntimeException("Cannot create an instance of " + paramClass, localInstantiationException);
        }
        catch (InvocationTargetException localInvocationTargetException)
        {
          throw new RuntimeException("Cannot create an instance of " + paramClass, localInvocationTargetException);
        }
      }
      return super.create(paramClass);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\lifecycle\ViewModelProviders.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */