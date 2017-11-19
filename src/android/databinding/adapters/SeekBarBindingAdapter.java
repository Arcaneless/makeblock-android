package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethods;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

@InverseBindingMethods({@android.databinding.InverseBindingMethod(attribute="android:progress", type=SeekBar.class)})
public class SeekBarBindingAdapter
{
  @BindingAdapter(requireAll=false, value={"android:onStartTrackingTouch", "android:onStopTrackingTouch", "android:onProgressChanged", "android:progressAttrChanged"})
  public static void setOnSeekBarChangeListener(SeekBar paramSeekBar, final OnStartTrackingTouch paramOnStartTrackingTouch, final OnStopTrackingTouch paramOnStopTrackingTouch, OnProgressChanged paramOnProgressChanged, final InverseBindingListener paramInverseBindingListener)
  {
    if ((paramOnStartTrackingTouch == null) && (paramOnStopTrackingTouch == null) && (paramOnProgressChanged == null) && (paramInverseBindingListener == null))
    {
      paramSeekBar.setOnSeekBarChangeListener(null);
      return;
    }
    paramSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        if (this.val$progressChanged != null) {
          this.val$progressChanged.onProgressChanged(paramAnonymousSeekBar, paramAnonymousInt, paramAnonymousBoolean);
        }
        if (paramInverseBindingListener != null) {
          paramInverseBindingListener.onChange();
        }
      }
      
      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
        if (paramOnStartTrackingTouch != null) {
          paramOnStartTrackingTouch.onStartTrackingTouch(paramAnonymousSeekBar);
        }
      }
      
      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
        if (paramOnStopTrackingTouch != null) {
          paramOnStopTrackingTouch.onStopTrackingTouch(paramAnonymousSeekBar);
        }
      }
    });
  }
  
  @BindingAdapter({"android:progress"})
  public static void setProgress(SeekBar paramSeekBar, int paramInt)
  {
    if (paramInt != paramSeekBar.getProgress()) {
      paramSeekBar.setProgress(paramInt);
    }
  }
  
  public static abstract interface OnProgressChanged
  {
    public abstract void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean);
  }
  
  public static abstract interface OnStartTrackingTouch
  {
    public abstract void onStartTrackingTouch(SeekBar paramSeekBar);
  }
  
  public static abstract interface OnStopTrackingTouch
  {
    public abstract void onStopTrackingTouch(SeekBar paramSeekBar);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\SeekBarBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */