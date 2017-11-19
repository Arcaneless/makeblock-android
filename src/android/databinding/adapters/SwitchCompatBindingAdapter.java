package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethods;
import android.support.v7.widget.SwitchCompat;

@BindingMethods({@android.databinding.BindingMethod(attribute="android:thumb", method="setThumbDrawable", type=SwitchCompat.class), @android.databinding.BindingMethod(attribute="android:track", method="setTrackDrawable", type=SwitchCompat.class)})
public class SwitchCompatBindingAdapter
{
  @BindingAdapter({"android:switchTextAppearance"})
  public static void setSwitchTextAppearance(SwitchCompat paramSwitchCompat, int paramInt)
  {
    paramSwitchCompat.setSwitchTextAppearance(null, paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\SwitchCompatBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */