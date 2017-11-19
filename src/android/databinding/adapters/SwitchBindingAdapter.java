package android.databinding.adapters;

import android.annotation.TargetApi;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethods;
import android.widget.Switch;

@TargetApi(14)
@BindingMethods({@android.databinding.BindingMethod(attribute="android:thumb", method="setThumbDrawable", type=Switch.class), @android.databinding.BindingMethod(attribute="android:track", method="setTrackDrawable", type=Switch.class)})
public class SwitchBindingAdapter
{
  @BindingAdapter({"android:switchTextAppearance"})
  public static void setSwitchTextAppearance(Switch paramSwitch, int paramInt)
  {
    paramSwitch.setSwitchTextAppearance(null, paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\SwitchBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */