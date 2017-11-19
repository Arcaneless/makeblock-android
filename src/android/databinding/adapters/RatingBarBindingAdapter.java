package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethods;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

@InverseBindingMethods({@android.databinding.InverseBindingMethod(attribute="android:rating", type=RatingBar.class)})
public class RatingBarBindingAdapter
{
  @BindingAdapter(requireAll=false, value={"android:onRatingChanged", "android:ratingAttrChanged"})
  public static void setListeners(RatingBar paramRatingBar, RatingBar.OnRatingBarChangeListener paramOnRatingBarChangeListener, final InverseBindingListener paramInverseBindingListener)
  {
    if (paramInverseBindingListener == null)
    {
      paramRatingBar.setOnRatingBarChangeListener(paramOnRatingBarChangeListener);
      return;
    }
    paramRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
    {
      public void onRatingChanged(RatingBar paramAnonymousRatingBar, float paramAnonymousFloat, boolean paramAnonymousBoolean)
      {
        if (this.val$listener != null) {
          this.val$listener.onRatingChanged(paramAnonymousRatingBar, paramAnonymousFloat, paramAnonymousBoolean);
        }
        paramInverseBindingListener.onChange();
      }
    });
  }
  
  @BindingAdapter({"android:rating"})
  public static void setRating(RatingBar paramRatingBar, float paramFloat)
  {
    if (paramRatingBar.getRating() != paramFloat) {
      paramRatingBar.setRating(paramFloat);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\RatingBarBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */