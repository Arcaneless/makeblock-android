package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethods;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethods;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

@BindingMethods({@android.databinding.BindingMethod(attribute="android:onItemClick", method="setOnItemClickListener", type=AdapterView.class), @android.databinding.BindingMethod(attribute="android:onItemLongClick", method="setOnItemLongClickListener", type=AdapterView.class)})
@InverseBindingMethods({@android.databinding.InverseBindingMethod(attribute="android:selectedItemPosition", type=AdapterView.class)})
public class AdapterViewBindingAdapter
{
  @BindingAdapter(requireAll=false, value={"android:onItemSelected", "android:onNothingSelected", "android:selectedItemPositionAttrChanged"})
  public static void setOnItemSelectedListener(AdapterView paramAdapterView, OnItemSelected paramOnItemSelected, OnNothingSelected paramOnNothingSelected, InverseBindingListener paramInverseBindingListener)
  {
    if ((paramOnItemSelected == null) && (paramOnNothingSelected == null) && (paramInverseBindingListener == null))
    {
      paramAdapterView.setOnItemSelectedListener(null);
      return;
    }
    paramAdapterView.setOnItemSelectedListener(new OnItemSelectedComponentListener(paramOnItemSelected, paramOnNothingSelected, paramInverseBindingListener));
  }
  
  @BindingAdapter({"android:selectedItemPosition"})
  public static void setSelectedItemPosition(AdapterView paramAdapterView, int paramInt)
  {
    if (paramAdapterView.getSelectedItemPosition() != paramInt) {
      paramAdapterView.setSelection(paramInt);
    }
  }
  
  public static abstract interface OnItemSelected
  {
    public abstract void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong);
  }
  
  public static class OnItemSelectedComponentListener
    implements AdapterView.OnItemSelectedListener
  {
    private final InverseBindingListener mAttrChanged;
    private final AdapterViewBindingAdapter.OnNothingSelected mNothingSelected;
    private final AdapterViewBindingAdapter.OnItemSelected mSelected;
    
    public OnItemSelectedComponentListener(AdapterViewBindingAdapter.OnItemSelected paramOnItemSelected, AdapterViewBindingAdapter.OnNothingSelected paramOnNothingSelected, InverseBindingListener paramInverseBindingListener)
    {
      this.mSelected = paramOnItemSelected;
      this.mNothingSelected = paramOnNothingSelected;
      this.mAttrChanged = paramInverseBindingListener;
    }
    
    public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      if (this.mSelected != null) {
        this.mSelected.onItemSelected(paramAdapterView, paramView, paramInt, paramLong);
      }
      if (this.mAttrChanged != null) {
        this.mAttrChanged.onChange();
      }
    }
    
    public void onNothingSelected(AdapterView<?> paramAdapterView)
    {
      if (this.mNothingSelected != null) {
        this.mNothingSelected.onNothingSelected(paramAdapterView);
      }
      if (this.mAttrChanged != null) {
        this.mAttrChanged.onChange();
      }
    }
  }
  
  public static abstract interface OnNothingSelected
  {
    public abstract void onNothingSelected(AdapterView<?> paramAdapterView);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\AdapterViewBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */