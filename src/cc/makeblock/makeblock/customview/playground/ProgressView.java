package cc.makeblock.makeblock.customview.playground;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import cc.makeblock.makeblock.R.styleable;
import cc.makeblock.makeblock.customview.CropImageView;
import cc.makeblock.makeblock.customview.CropImageView.CropType;

public class ProgressView
  extends FrameLayout
  implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener
{
  private static final int DIRECTION_LEFT_TO_RIGHT = 0;
  private static final int DIRECTION_RIGHT_TO_LEFT = 1;
  private int direction;
  private boolean hasRunShrinkAnimation = false;
  private float progress;
  private CropImageView progressImageView;
  private int shrinkAnimationDuration = 500;
  private ValueAnimator valueAnimator;
  private boolean withShrinkAnimation = true;
  
  public ProgressView(@NonNull Context paramContext)
  {
    super(paramContext);
    init(paramContext, null);
  }
  
  public ProgressView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }
  
  public ProgressView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, @AttrRes int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }
  
  public ProgressView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, @AttrRes int paramInt1, @StyleRes int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    init(paramContext, paramAttributeSet);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ProgressView, 0, 0);
    int i = paramAttributeSet.getResourceId(0, 0);
    int j = paramAttributeSet.getResourceId(2, 0);
    this.direction = paramAttributeSet.getInt(1, 0);
    paramAttributeSet.recycle();
    paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2131427560, null, false);
    this.progressImageView = ((CropImageView)paramContext.findViewById(2131296633));
    if (this.direction == 0) {
      this.progressImageView.setCropType(CropImageView.CropType.LEFT_CENTER);
    }
    for (;;)
    {
      paramAttributeSet = (PercentRelativeLayout.LayoutParams)this.progressImageView.getLayoutParams();
      paramAttributeSet.getPercentLayoutInfo().widthPercent = 0.0F;
      paramContext.setBackgroundResource(i);
      this.progressImageView.setImageResource(j);
      this.progressImageView.setLayoutParams(paramAttributeSet);
      addView(paramContext);
      return;
      this.progressImageView.setCropType(CropImageView.CropType.RIGHT_CENTER);
      ((PercentRelativeLayout.LayoutParams)this.progressImageView.getLayoutParams()).addRule(11);
    }
  }
  
  public void onAnimationCancel(Animator paramAnimator) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    if ((paramAnimator == this.valueAnimator) && (!this.hasRunShrinkAnimation))
    {
      this.hasRunShrinkAnimation = true;
      setProgress(0.0F);
    }
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    this.progress = ((Float)paramValueAnimator.getAnimatedValue()).floatValue();
    paramValueAnimator = (PercentRelativeLayout.LayoutParams)this.progressImageView.getLayoutParams();
    paramValueAnimator.getPercentLayoutInfo().widthPercent = this.progress;
    this.progressImageView.setLayoutParams(paramValueAnimator);
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    this.valueAnimator.removeAllUpdateListeners();
    this.valueAnimator.removeAllListeners();
  }
  
  public void setProgress(float paramFloat)
  {
    if (this.valueAnimator != null) {
      this.valueAnimator.cancel();
    }
    if (!this.withShrinkAnimation)
    {
      this.valueAnimator = ValueAnimator.ofFloat(new float[] { this.progress, paramFloat });
      this.valueAnimator.setDuration(300L);
      this.hasRunShrinkAnimation = true;
    }
    for (;;)
    {
      this.valueAnimator.setInterpolator(new DecelerateInterpolator());
      this.valueAnimator.addUpdateListener(this);
      this.valueAnimator.addListener(this);
      this.valueAnimator.start();
      return;
      if (this.progress < paramFloat)
      {
        this.valueAnimator = ValueAnimator.ofFloat(new float[] { this.progress, paramFloat });
        this.valueAnimator.setDuration(300L);
        this.hasRunShrinkAnimation = false;
      }
      else
      {
        this.valueAnimator = ValueAnimator.ofFloat(new float[] { this.progress, 0.0F });
        this.valueAnimator.setDuration(this.shrinkAnimationDuration);
      }
    }
  }
  
  public void setShrinkAnimationDuration(int paramInt)
  {
    this.shrinkAnimationDuration = paramInt;
  }
  
  public void setWithShrinkAnimation(boolean paramBoolean)
  {
    this.withShrinkAnimation = paramBoolean;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\playground\ProgressView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */