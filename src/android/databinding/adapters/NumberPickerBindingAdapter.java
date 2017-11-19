package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethods;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethods;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

@BindingMethods({@android.databinding.BindingMethod(attribute="android:format", method="setFormatter", type=NumberPicker.class), @android.databinding.BindingMethod(attribute="android:onScrollStateChange", method="setOnScrollListener", type=NumberPicker.class)})
@InverseBindingMethods({@android.databinding.InverseBindingMethod(attribute="android:value", type=NumberPicker.class)})
public class NumberPickerBindingAdapter
{
  @BindingAdapter(requireAll=false, value={"android:onValueChange", "android:valueAttrChanged"})
  public static void setListeners(NumberPicker paramNumberPicker, NumberPicker.OnValueChangeListener paramOnValueChangeListener, final InverseBindingListener paramInverseBindingListener)
  {
    if (paramInverseBindingListener == null)
    {
      paramNumberPicker.setOnValueChangedListener(paramOnValueChangeListener);
      return;
    }
    paramNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
    {
      public void onValueChange(NumberPicker paramAnonymousNumberPicker, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        if (this.val$listener != null) {
          this.val$listener.onValueChange(paramAnonymousNumberPicker, paramAnonymousInt1, paramAnonymousInt2);
        }
        paramInverseBindingListener.onChange();
      }
    });
  }
  
  @BindingAdapter({"android:value"})
  public static void setValue(NumberPicker paramNumberPicker, int paramInt)
  {
    if (paramNumberPicker.getValue() != paramInt) {
      paramNumberPicker.setValue(paramInt);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\NumberPickerBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */