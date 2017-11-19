package cc.makeblock.makeblock.customview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;

public class WidgetContainerButtonView
  extends PercentRelativeLayout
{
  private ImageView button;
  private boolean isShow = false;
  private boolean isTurnOn = false;
  
  public WidgetContainerButtonView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public WidgetContainerButtonView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    setBackgroundResource(2131165886);
    this.button = new ImageView(paramContext);
    this.button.setImageResource(2131165885);
    paramContext = new PercentRelativeLayout.LayoutParams(-1, -1);
    paramContext.addRule(13);
    this.button.setLayoutParams(paramContext);
    addView(this.button);
    this.button.setRotation(45.0F);
    setX(-ScreenHelper.getPercentWidthToPx(0.083333336F));
  }
  
  public void setShow(boolean paramBoolean)
  {
    if (paramBoolean == this.isShow) {
      return;
    }
    this.isShow = paramBoolean;
    if (paramBoolean)
    {
      localObjectAnimator = ObjectAnimator.ofFloat(this, "X", new float[] { -ScreenHelper.getPercentWidthToPx(0.078125F), ScreenHelper.getPercentWidthToPx(0.01875F) });
      localObjectAnimator.setDuration(300L);
      localObjectAnimator.setInterpolator(new DecelerateInterpolator());
      localObjectAnimator.start();
      return;
    }
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this, "X", new float[] { ScreenHelper.getPercentWidthToPx(0.01875F), -ScreenHelper.getPercentWidthToPx(0.078125F) });
    localObjectAnimator.setDuration(300L);
    localObjectAnimator.setInterpolator(new AccelerateInterpolator());
    localObjectAnimator.start();
  }
  
  public void setTurnOn(boolean paramBoolean)
  {
    if (paramBoolean == this.isTurnOn) {
      return;
    }
    this.isTurnOn = paramBoolean;
    if (paramBoolean)
    {
      localObjectAnimator = ObjectAnimator.ofFloat(this.button, "rotation", new float[] { -90.0F, 45.0F });
      localObjectAnimator.setDuration(300L);
      localObjectAnimator.setInterpolator(new OvershootInterpolator());
      localObjectAnimator.start();
      return;
    }
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.button, "rotation", new float[] { 45.0F, -90.0F });
    localObjectAnimator.setDuration(300L);
    localObjectAnimator.setInterpolator(new OvershootInterpolator());
    localObjectAnimator.start();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\WidgetContainerButtonView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */