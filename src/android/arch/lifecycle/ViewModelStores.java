package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class ViewModelStores
{
  @MainThread
  public static ViewModelStore of(Fragment paramFragment)
  {
    return HolderFragment.holderFragmentFor(paramFragment).getViewModelStore();
  }
  
  @MainThread
  public static ViewModelStore of(FragmentActivity paramFragmentActivity)
  {
    return HolderFragment.holderFragmentFor(paramFragmentActivity).getViewModelStore();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\lifecycle\ViewModelStores.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */