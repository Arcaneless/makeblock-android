package cc.makeblock.makeblock.customview.playground;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;

public class DisableMaskView
  extends AppCompatImageView
{
  private boolean powerOn = false;
  
  public DisableMaskView(Context paramContext)
  {
    super(paramContext);
  }
  
  public DisableMaskView(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public DisableMaskView(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return getAlpha() > 0.0F;
  }
  
  public void setEnable(boolean paramBoolean)
  {
    if (paramBoolean == this.powerOn) {
      return;
    }
    this.powerOn = paramBoolean;
    if (paramBoolean)
    {
      localObjectAnimator = ObjectAnimator.ofFloat(this, "alpha", new float[] { 1.0F, 0.0F });
      localObjectAnimator.setDuration(300L);
      localObjectAnimator.setInterpolator(new AccelerateInterpolator());
      localObjectAnimator.start();
      return;
    }
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this, "alpha", new float[] { 0.0F, 1.0F });
    localObjectAnimator.setDuration(300L);
    localObjectAnimator.setInterpolator(new AccelerateInterpolator());
    localObjectAnimator.start();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\playground\DisableMaskView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */