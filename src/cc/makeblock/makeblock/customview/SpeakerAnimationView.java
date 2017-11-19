package cc.makeblock.makeblock.customview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class SpeakerAnimationView
  extends RelativeLayout
{
  private ObjectAnimator animator;
  private ImageView wave;
  
  public SpeakerAnimationView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public SpeakerAnimationView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    setBackgroundResource(2131165364);
    this.wave = new ImageView(paramContext);
    this.wave.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    this.wave.setImageResource(2131165365);
    this.wave.setAlpha(0.0F);
    addView(this.wave);
    this.animator = ObjectAnimator.ofFloat(this.wave, "alpha", new float[] { 0.0F, 1.0F, 0.0F });
    this.animator.setDuration(1000L);
    this.animator.setRepeatCount(-1);
  }
  
  public void setAnimationStart(boolean paramBoolean)
  {
    if (paramBoolean) {
      if (!this.animator.isRunning()) {
        this.animator.start();
      }
    }
    while (!this.animator.isRunning()) {
      return;
    }
    this.animator.cancel();
    this.wave.setAlpha(0.0F);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\SpeakerAnimationView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */