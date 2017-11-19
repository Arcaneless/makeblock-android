package cc.makeblock.makeblock.customview;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.OvershootInterpolator;
import cc.makeblock.makeblock.R.styleable;

public class RadioAnimationLayout
  extends PercentRelativeLayout
  implements View.OnClickListener
{
  private PercentRelativeLayout leftRect;
  private OnTextSelectListener onTextSelectListener;
  private PercentRelativeLayout rightRect;
  private FitWidthTextView textLeft;
  private FitWidthTextView textRight;
  
  public RadioAnimationLayout(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null);
  }
  
  public RadioAnimationLayout(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }
  
  public RadioAnimationLayout(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RadioAnimationLayout, 0, 0);
    int i = paramAttributeSet.getResourceId(0, -1);
    int j = paramAttributeSet.getResourceId(1, -1);
    paramAttributeSet.recycle();
    paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2131427495, this);
    this.textLeft = ((FitWidthTextView)paramContext.findViewById(2131296552));
    this.textRight = ((FitWidthTextView)paramContext.findViewById(2131296647));
    this.leftRect = ((PercentRelativeLayout)paramContext.findViewById(2131296551));
    this.rightRect = ((PercentRelativeLayout)paramContext.findViewById(2131296645));
    this.textLeft.setText(i);
    this.textRight.setText(j);
    this.leftRect.setOnClickListener(this);
    this.rightRect.setOnClickListener(this);
    getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      public void onGlobalLayout()
      {
        RadioAnimationLayout.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        RadioAnimationLayout.this.textRight.setPivotX(RadioAnimationLayout.this.textRight.getWidth() / 2);
        RadioAnimationLayout.this.textRight.setPivotY(RadioAnimationLayout.this.textRight.getHeight());
        RadioAnimationLayout.this.textRight.setScaleX(0.9F);
        RadioAnimationLayout.this.textRight.setScaleY(0.9F);
        RadioAnimationLayout.this.textLeft.setSelected(true);
        RadioAnimationLayout.this.textRight.setSelected(false);
        RadioAnimationLayout.this.textLeft.setTextColor(-1);
        RadioAnimationLayout.this.textRight.setTextColor(-12303292);
      }
    });
  }
  
  private void setLeftTextSelected()
  {
    if (this.textLeft.isSelected()) {
      return;
    }
    Object localObject = new ValueAnimator();
    ((ValueAnimator)localObject).setIntValues(new int[] { -12303292, -1 });
    ((ValueAnimator)localObject).setEvaluator(new ArgbEvaluator());
    ((ValueAnimator)localObject).addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
      {
        RadioAnimationLayout.this.textLeft.setTextColor(((Integer)paramAnonymousValueAnimator.getAnimatedValue()).intValue());
      }
    });
    ((ValueAnimator)localObject).setDuration(300L);
    ((ValueAnimator)localObject).start();
    localObject = new ValueAnimator();
    ((ValueAnimator)localObject).setIntValues(new int[] { -1, -12303292 });
    ((ValueAnimator)localObject).setEvaluator(new ArgbEvaluator());
    ((ValueAnimator)localObject).addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
      {
        RadioAnimationLayout.this.textRight.setTextColor(((Integer)paramAnonymousValueAnimator.getAnimatedValue()).intValue());
      }
    });
    ((ValueAnimator)localObject).setDuration(300L);
    ((ValueAnimator)localObject).start();
    localObject = ObjectAnimator.ofFloat(this.textLeft, "scaleX", new float[] { 0.9F, 1.0F });
    ((ObjectAnimator)localObject).setInterpolator(new OvershootInterpolator());
    ((ObjectAnimator)localObject).setDuration(300L);
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.textLeft, "scaleY", new float[] { 0.9F, 1.0F });
    localObjectAnimator.setInterpolator(new OvershootInterpolator());
    localObjectAnimator.setDuration(300L);
    this.textLeft.setPivotX(this.textLeft.getWidth() / 2);
    this.textLeft.setPivotY(this.textLeft.getHeight());
    ((ObjectAnimator)localObject).start();
    localObjectAnimator.start();
    localObject = ObjectAnimator.ofFloat(this.textRight, "scaleX", new float[] { 1.0F, 0.9F });
    ((ObjectAnimator)localObject).setInterpolator(new OvershootInterpolator());
    ((ObjectAnimator)localObject).setDuration(300L);
    localObjectAnimator = ObjectAnimator.ofFloat(this.textRight, "scaleY", new float[] { 1.0F, 0.9F });
    localObjectAnimator.setInterpolator(new OvershootInterpolator());
    localObjectAnimator.setDuration(300L);
    this.textRight.setPivotX(this.textRight.getWidth() / 2);
    this.textRight.setPivotY(this.textRight.getHeight());
    ((ObjectAnimator)localObject).start();
    localObjectAnimator.start();
    this.textLeft.setSelected(true);
    this.textRight.setSelected(false);
  }
  
  private void setRightTextSelected()
  {
    if (this.textRight.isSelected()) {
      return;
    }
    Object localObject = new ValueAnimator();
    ((ValueAnimator)localObject).setIntValues(new int[] { -1, -12303292 });
    ((ValueAnimator)localObject).setEvaluator(new ArgbEvaluator());
    ((ValueAnimator)localObject).addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
      {
        RadioAnimationLayout.this.textLeft.setTextColor(((Integer)paramAnonymousValueAnimator.getAnimatedValue()).intValue());
      }
    });
    ((ValueAnimator)localObject).setDuration(300L);
    ((ValueAnimator)localObject).start();
    localObject = new ValueAnimator();
    ((ValueAnimator)localObject).setIntValues(new int[] { -12303292, -1 });
    ((ValueAnimator)localObject).setEvaluator(new ArgbEvaluator());
    ((ValueAnimator)localObject).addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
      {
        RadioAnimationLayout.this.textRight.setTextColor(((Integer)paramAnonymousValueAnimator.getAnimatedValue()).intValue());
      }
    });
    ((ValueAnimator)localObject).setDuration(300L);
    ((ValueAnimator)localObject).start();
    localObject = ObjectAnimator.ofFloat(this.textLeft, "scaleX", new float[] { 1.0F, 0.9F });
    ((ObjectAnimator)localObject).setInterpolator(new OvershootInterpolator());
    ((ObjectAnimator)localObject).setDuration(300L);
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.textLeft, "scaleY", new float[] { 1.0F, 0.9F });
    localObjectAnimator.setInterpolator(new OvershootInterpolator());
    localObjectAnimator.setDuration(300L);
    this.textLeft.setPivotX(this.textLeft.getWidth() / 2);
    this.textLeft.setPivotY(this.textLeft.getHeight());
    ((ObjectAnimator)localObject).start();
    localObjectAnimator.start();
    localObject = ObjectAnimator.ofFloat(this.textRight, "scaleX", new float[] { 0.9F, 1.0F });
    ((ObjectAnimator)localObject).setInterpolator(new OvershootInterpolator());
    ((ObjectAnimator)localObject).setDuration(300L);
    localObjectAnimator = ObjectAnimator.ofFloat(this.textRight, "scaleY", new float[] { 0.9F, 1.0F });
    localObjectAnimator.setInterpolator(new OvershootInterpolator());
    localObjectAnimator.setDuration(300L);
    this.textRight.setPivotX(this.textRight.getWidth() / 2);
    this.textRight.setPivotY(this.textRight.getHeight());
    ((ObjectAnimator)localObject).start();
    localObjectAnimator.start();
    this.textRight.setSelected(true);
    this.textLeft.setSelected(false);
  }
  
  public void onClick(View paramView)
  {
    if (this.onTextSelectListener != null)
    {
      if (paramView != this.leftRect) {
        break label30;
      }
      this.onTextSelectListener.onTextSelect(0);
      setLeftTextSelected();
    }
    label30:
    while (paramView != this.rightRect) {
      return;
    }
    setRightTextSelected();
    this.onTextSelectListener.onTextSelect(1);
  }
  
  public void setOnTextSelectListener(OnTextSelectListener paramOnTextSelectListener)
  {
    this.onTextSelectListener = paramOnTextSelectListener;
  }
  
  public static abstract interface OnTextSelectListener
  {
    public abstract void onTextSelect(int paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\RadioAnimationLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */