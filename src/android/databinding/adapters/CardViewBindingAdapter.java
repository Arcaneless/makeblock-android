package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethods;
import android.support.v7.widget.CardView;

@BindingMethods({@android.databinding.BindingMethod(attribute="cardCornerRadius", method="setRadius", type=CardView.class), @android.databinding.BindingMethod(attribute="cardMaxElevation", method="setMaxCardElevation", type=CardView.class), @android.databinding.BindingMethod(attribute="cardPreventCornerOverlap", method="setPreventCornerOverlap", type=CardView.class), @android.databinding.BindingMethod(attribute="cardUseCompatPadding", method="setUseCompatPadding", type=CardView.class)})
public class CardViewBindingAdapter
{
  @BindingAdapter({"contentPadding"})
  public static void setContentPadding(CardView paramCardView, int paramInt)
  {
    paramCardView.setContentPadding(paramInt, paramInt, paramInt, paramInt);
  }
  
  @BindingAdapter({"contentPaddingBottom"})
  public static void setContentPaddingBottom(CardView paramCardView, int paramInt)
  {
    paramCardView.setContentPadding(paramCardView.getContentPaddingLeft(), paramCardView.getContentPaddingTop(), paramCardView.getContentPaddingRight(), paramInt);
  }
  
  @BindingAdapter({"contentPaddingLeft"})
  public static void setContentPaddingLeft(CardView paramCardView, int paramInt)
  {
    paramCardView.setContentPadding(paramInt, paramCardView.getContentPaddingTop(), paramCardView.getContentPaddingRight(), paramCardView.getContentPaddingBottom());
  }
  
  @BindingAdapter({"contentPaddingRight"})
  public static void setContentPaddingRight(CardView paramCardView, int paramInt)
  {
    paramCardView.setContentPadding(paramCardView.getContentPaddingLeft(), paramCardView.getContentPaddingTop(), paramInt, paramCardView.getContentPaddingBottom());
  }
  
  @BindingAdapter({"contentPaddingTop"})
  public static void setContentPaddingTop(CardView paramCardView, int paramInt)
  {
    paramCardView.setContentPadding(paramCardView.getContentPaddingLeft(), paramInt, paramCardView.getContentPaddingRight(), paramCardView.getContentPaddingBottom());
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\CardViewBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */