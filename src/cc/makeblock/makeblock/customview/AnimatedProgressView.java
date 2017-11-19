package cc.makeblock.makeblock.customview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.percent.PercentRelativeLayout;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class AnimatedProgressView
  extends PercentRelativeLayout
{
  private View progressbar;
  
  public AnimatedProgressView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public AnimatedProgressView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public AnimatedProgressView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    setBackground(new ColorDrawable(Color.parseColor("#64FFFFFF")));
    this.progressbar = new View(paramContext);
    this.progressbar.setBackground(new ColorDrawable(Color.parseColor("#FFFFFF")));
    this.progressbar.setLayoutParams(new PercentRelativeLayout.LayoutParams(-1, -1));
    addView(this.progressbar);
    this.progressbar.setPivotX(0.0F);
    this.progressbar.setScaleX(0.0F);
  }
  
  public void setProgress(float paramFloat)
  {
    if (getWidth() != 0)
    {
      ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.progressbar, "scaleX", new float[] { this.progressbar.getScaleX(), paramFloat });
      localObjectAnimator.setInterpolator(new AccelerateInterpolator());
      localObjectAnimator.setDuration(300L);
      localObjectAnimator.start();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\AnimatedProgressView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */