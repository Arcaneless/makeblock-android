package cc.makeblock.makeblock.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class ProgressScrollBar
  extends PercentRelativeLayout
{
  private ImageView indicator;
  private float indicatorScaleX = 1.0F;
  private boolean show = true;
  
  public ProgressScrollBar(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public ProgressScrollBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public ProgressScrollBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
  private void hideView(View paramView)
  {
    paramView = ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 1.0F, 0.0F });
    paramView.setInterpolator(new DecelerateInterpolator());
    paramView.setDuration(300L);
    paramView.addListener(new AnimatorListenerAdapter()
    {
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        super.onAnimationEnd(paramAnonymousAnimator);
        ProgressScrollBar.this.setVisibility(4);
      }
    });
    paramView.start();
  }
  
  private boolean isRecyclerScrollable(RecyclerView paramRecyclerView)
  {
    return paramRecyclerView.computeHorizontalScrollRange() > paramRecyclerView.getWidth();
  }
  
  private void showView(View paramView)
  {
    paramView = ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 0.0F, 1.0F });
    paramView.setInterpolator(new AccelerateInterpolator());
    paramView.setDuration(300L);
    paramView.start();
  }
  
  public void init(Context paramContext)
  {
    setBackgroundResource(2131165848);
    this.indicator = new ImageView(paramContext);
    this.indicator.setBackgroundResource(2131165849);
    paramContext = new PercentRelativeLayout.LayoutParams(-1, -1);
    this.indicator.setLayoutParams(paramContext);
    this.indicator.setPivotX(0.0F);
    addView(this.indicator);
    setVisibility(4);
    this.show = false;
  }
  
  public void setListCount(int paramInt)
  {
    float f = 3.0F / paramInt;
    if (this.indicatorScaleX != f)
    {
      ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.indicator, "scaleX", new float[] { this.indicatorScaleX, f });
      localObjectAnimator.setDuration(200L);
      localObjectAnimator.setInterpolator(new AccelerateInterpolator());
      localObjectAnimator.start();
      this.indicatorScaleX = f;
    }
  }
  
  public void setScrollPercent(RecyclerView paramRecyclerView, float paramFloat)
  {
    boolean bool = isRecyclerScrollable(paramRecyclerView);
    if (bool != this.show)
    {
      if (!bool) {
        break label65;
      }
      setVisibility(0);
      showView(this);
    }
    for (;;)
    {
      this.show = bool;
      this.indicator.setX((getWidth() - this.indicator.getWidth() * this.indicator.getScaleX()) * paramFloat);
      return;
      label65:
      hideView(this);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\ProgressScrollBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */