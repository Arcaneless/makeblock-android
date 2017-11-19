package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethods;
import android.databinding.Untaggable;
import android.databinding.ViewStubProxy;
import android.view.ViewStub.OnInflateListener;

@BindingMethods({@android.databinding.BindingMethod(attribute="android:layout", method="setLayoutResource", type=android.view.ViewStub.class)})
@Untaggable({"android.view.ViewStub"})
public class ViewStubBindingAdapter
{
  @BindingAdapter({"android:onInflate"})
  public static void setOnInflateListener(ViewStubProxy paramViewStubProxy, ViewStub.OnInflateListener paramOnInflateListener)
  {
    paramViewStubProxy.setOnInflateListener(paramOnInflateListener);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\ViewStubBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */