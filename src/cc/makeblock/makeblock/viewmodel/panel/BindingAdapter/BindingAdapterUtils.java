package cc.makeblock.makeblock.viewmodel.panel.BindingAdapter;

import android.databinding.BindingAdapter;
import android.support.percent.PercentFrameLayout.LayoutParams;
import android.view.View;

public class BindingAdapterUtils
{
  @BindingAdapter({"frameLayout_widthPercent"})
  public static void setLayoutWidthPercent(View paramView, float paramFloat)
  {
    PercentFrameLayout.LayoutParams localLayoutParams = (PercentFrameLayout.LayoutParams)paramView.getLayoutParams();
    localLayoutParams.getPercentLayoutInfo().widthPercent = paramFloat;
    paramView.setLayoutParams(localLayoutParams);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\panel\BindingAdapter\BindingAdapterUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */