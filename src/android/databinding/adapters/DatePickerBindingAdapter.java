package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethods;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import com.android.databinding.library.baseAdapters.R.id;

@InverseBindingMethods({@android.databinding.InverseBindingMethod(attribute="android:year", type=DatePicker.class), @android.databinding.InverseBindingMethod(attribute="android:month", type=DatePicker.class), @android.databinding.InverseBindingMethod(attribute="android:day", method="getDayOfMonth", type=DatePicker.class)})
public class DatePickerBindingAdapter
{
  @BindingAdapter(requireAll=false, value={"android:year", "android:month", "android:day", "android:onDateChanged", "android:yearAttrChanged", "android:monthAttrChanged", "android:dayAttrChanged"})
  public static void setListeners(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3, DatePicker.OnDateChangedListener paramOnDateChangedListener, InverseBindingListener paramInverseBindingListener1, InverseBindingListener paramInverseBindingListener2, InverseBindingListener paramInverseBindingListener3)
  {
    int i = paramInt1;
    if (paramInt1 == 0) {
      i = paramDatePicker.getYear();
    }
    paramInt1 = paramInt3;
    if (paramInt3 == 0) {
      paramInt1 = paramDatePicker.getDayOfMonth();
    }
    if ((paramInverseBindingListener1 == null) && (paramInverseBindingListener2 == null) && (paramInverseBindingListener3 == null))
    {
      paramDatePicker.init(i, paramInt2, paramInt1, paramOnDateChangedListener);
      return;
    }
    DateChangedListener localDateChangedListener2 = (DateChangedListener)ListenerUtil.getListener(paramDatePicker, R.id.onDateChanged);
    DateChangedListener localDateChangedListener1 = localDateChangedListener2;
    if (localDateChangedListener2 == null)
    {
      localDateChangedListener1 = new DateChangedListener(null);
      ListenerUtil.trackListener(paramDatePicker, localDateChangedListener1, R.id.onDateChanged);
    }
    localDateChangedListener1.setListeners(paramOnDateChangedListener, paramInverseBindingListener1, paramInverseBindingListener2, paramInverseBindingListener3);
    paramDatePicker.init(i, paramInt2, paramInt1, localDateChangedListener1);
  }
  
  private static class DateChangedListener
    implements DatePicker.OnDateChangedListener
  {
    InverseBindingListener mDayChanged;
    DatePicker.OnDateChangedListener mListener;
    InverseBindingListener mMonthChanged;
    InverseBindingListener mYearChanged;
    
    public void onDateChanged(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3)
    {
      if (this.mListener != null) {
        this.mListener.onDateChanged(paramDatePicker, paramInt1, paramInt2, paramInt3);
      }
      if (this.mYearChanged != null) {
        this.mYearChanged.onChange();
      }
      if (this.mMonthChanged != null) {
        this.mMonthChanged.onChange();
      }
      if (this.mDayChanged != null) {
        this.mDayChanged.onChange();
      }
    }
    
    public void setListeners(DatePicker.OnDateChangedListener paramOnDateChangedListener, InverseBindingListener paramInverseBindingListener1, InverseBindingListener paramInverseBindingListener2, InverseBindingListener paramInverseBindingListener3)
    {
      this.mListener = paramOnDateChangedListener;
      this.mYearChanged = paramInverseBindingListener1;
      this.mMonthChanged = paramInverseBindingListener2;
      this.mDayChanged = paramInverseBindingListener3;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\DatePickerBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */