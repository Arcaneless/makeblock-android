package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class TabHostBindingAdapter
{
  @InverseBindingAdapter(attribute="android:currentTab")
  public static int getCurrentTab(TabHost paramTabHost)
  {
    return paramTabHost.getCurrentTab();
  }
  
  @InverseBindingAdapter(attribute="android:currentTab")
  public static String getCurrentTabTag(TabHost paramTabHost)
  {
    return paramTabHost.getCurrentTabTag();
  }
  
  @BindingAdapter({"android:currentTab"})
  public static void setCurrentTab(TabHost paramTabHost, int paramInt)
  {
    if (paramTabHost.getCurrentTab() != paramInt) {
      paramTabHost.setCurrentTab(paramInt);
    }
  }
  
  @BindingAdapter({"android:currentTab"})
  public static void setCurrentTabTag(TabHost paramTabHost, String paramString)
  {
    if (paramTabHost.getCurrentTabTag() != paramString) {
      paramTabHost.setCurrentTabByTag(paramString);
    }
  }
  
  @BindingAdapter(requireAll=false, value={"android:onTabChanged", "android:currentTabAttrChanged"})
  public static void setListeners(TabHost paramTabHost, TabHost.OnTabChangeListener paramOnTabChangeListener, final InverseBindingListener paramInverseBindingListener)
  {
    if (paramInverseBindingListener == null)
    {
      paramTabHost.setOnTabChangedListener(paramOnTabChangeListener);
      return;
    }
    paramTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener()
    {
      public void onTabChanged(String paramAnonymousString)
      {
        if (this.val$listener != null) {
          this.val$listener.onTabChanged(paramAnonymousString);
        }
        paramInverseBindingListener.onChange();
      }
    });
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\TabHostBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */