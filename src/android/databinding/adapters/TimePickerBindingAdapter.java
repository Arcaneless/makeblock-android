package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.os.Build.VERSION;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class TimePickerBindingAdapter
{
  @InverseBindingAdapter(attribute="android:hour")
  public static int getHour(TimePicker paramTimePicker)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return paramTimePicker.getHour();
    }
    paramTimePicker = paramTimePicker.getCurrentHour();
    if (paramTimePicker == null) {
      return 0;
    }
    return paramTimePicker.intValue();
  }
  
  @InverseBindingAdapter(attribute="android:minute")
  public static int getMinute(TimePicker paramTimePicker)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return paramTimePicker.getMinute();
    }
    paramTimePicker = paramTimePicker.getCurrentMinute();
    if (paramTimePicker == null) {
      return 0;
    }
    return paramTimePicker.intValue();
  }
  
  @BindingAdapter({"android:hour"})
  public static void setHour(TimePicker paramTimePicker, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      if (paramTimePicker.getHour() != paramInt) {
        paramTimePicker.setHour(paramInt);
      }
    }
    while (paramTimePicker.getCurrentHour().intValue() == paramInt) {
      return;
    }
    paramTimePicker.setCurrentHour(Integer.valueOf(paramInt));
  }
  
  @BindingAdapter(requireAll=false, value={"android:onTimeChanged", "android:hourAttrChanged", "android:minuteAttrChanged"})
  public static void setListeners(TimePicker paramTimePicker, TimePicker.OnTimeChangedListener paramOnTimeChangedListener, final InverseBindingListener paramInverseBindingListener1, final InverseBindingListener paramInverseBindingListener2)
  {
    if ((paramInverseBindingListener1 == null) && (paramInverseBindingListener2 == null))
    {
      paramTimePicker.setOnTimeChangedListener(paramOnTimeChangedListener);
      return;
    }
    paramTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener()
    {
      public void onTimeChanged(TimePicker paramAnonymousTimePicker, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        if (this.val$listener != null) {
          this.val$listener.onTimeChanged(paramAnonymousTimePicker, paramAnonymousInt1, paramAnonymousInt2);
        }
        if (paramInverseBindingListener1 != null) {
          paramInverseBindingListener1.onChange();
        }
        if (paramInverseBindingListener2 != null) {
          paramInverseBindingListener2.onChange();
        }
      }
    });
  }
  
  @BindingAdapter({"android:minute"})
  public static void setMinute(TimePicker paramTimePicker, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      if (paramTimePicker.getMinute() != paramInt) {
        paramTimePicker.setMinute(paramInt);
      }
    }
    while (paramTimePicker.getCurrentMinute().intValue() == paramInt) {
      return;
    }
    paramTimePicker.setCurrentHour(Integer.valueOf(paramInt));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\TimePickerBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */