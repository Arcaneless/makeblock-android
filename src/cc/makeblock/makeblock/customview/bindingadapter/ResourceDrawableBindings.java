package cc.makeblock.makeblock.customview.bindingadapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

public class ResourceDrawableBindings
{
  @BindingAdapter({"android:background"})
  public static void setImageBackground(View paramView, @DrawableRes int paramInt)
  {
    paramView.setBackgroundResource(paramInt);
  }
  
  @BindingAdapter({"android:src"})
  public static void setImageUri(ImageView paramImageView, @DrawableRes int paramInt)
  {
    paramImageView.setImageResource(paramInt);
  }
  
  @BindingAdapter({"normalSrc", "pressedSrc"})
  public static void setStateBackground(View paramView, Drawable paramDrawable1, Drawable paramDrawable2)
  {
    StateListDrawable localStateListDrawable = new StateListDrawable();
    localStateListDrawable.addState(new int[] { 16842919 }, paramDrawable2);
    localStateListDrawable.addState(new int[0], paramDrawable1);
    paramView.setBackground(localStateListDrawable);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\bindingadapter\ResourceDrawableBindings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */