package cc.makeblock.makeblock.customview.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;

public class AnimationUtil
{
  public static Animator createAlpha(View paramView, int paramInt1, int paramInt2)
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 1.0F, 0.1F });
    localObjectAnimator.setDuration(1000L);
    localObjectAnimator.setRepeatMode(paramInt1);
    localObjectAnimator.setRepeatCount(paramInt2);
    localObjectAnimator.addListener(new AnimatorListenerAdapter()
    {
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        this.val$view.setAlpha(1.0F);
      }
    });
    paramView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener()
    {
      public void onViewAttachedToWindow(View paramAnonymousView) {}
      
      public void onViewDetachedFromWindow(View paramAnonymousView)
      {
        if (this.val$animator.isRunning()) {
          this.val$animator.cancel();
        }
      }
    });
    return localObjectAnimator;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\util\AnimationUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */