package cc.makeblock.makeblock.customview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.percent.PercentRelativeLayout;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.util.AttributeSet;
import android.view.View;
import cc.makeblock.makeblock.scene.connect.IntervalAccelerateDecelerateInterpolator;

public class InfiniteProgressView
  extends PercentRelativeLayout
{
  private ObjectAnimator infiniteAnimator;
  
  public InfiniteProgressView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public InfiniteProgressView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public InfiniteProgressView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
  private void init(final Context paramContext)
  {
    setBackground(new ColorDrawable(Color.parseColor("#64FFFFFF")));
    paramContext = new View(paramContext);
    paramContext.setBackground(new ColorDrawable(Color.parseColor("#FFFFFF")));
    PercentRelativeLayout.LayoutParams localLayoutParams = new PercentRelativeLayout.LayoutParams(-1, -1);
    localLayoutParams.getPercentLayoutInfo().heightPercent = 1.0F;
    localLayoutParams.getPercentLayoutInfo().widthPercent = 0.4F;
    paramContext.setLayoutParams(localLayoutParams);
    addView(paramContext);
    post(new Runnable()
    {
      public void run()
      {
        paramContext.setX(-paramContext.getWidth());
        InfiniteProgressView.access$002(InfiniteProgressView.this, ObjectAnimator.ofFloat(paramContext, "x", new float[] { -paramContext.getWidth(), InfiniteProgressView.this.getWidth() }));
        InfiniteProgressView.this.infiniteAnimator.setInterpolator(new IntervalAccelerateDecelerateInterpolator(0.3F));
        InfiniteProgressView.this.infiniteAnimator.setDuration(1000L);
        InfiniteProgressView.this.infiniteAnimator.setRepeatCount(-1);
        InfiniteProgressView.this.infiniteAnimator.start();
      }
    });
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\InfiniteProgressView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */