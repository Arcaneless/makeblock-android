package com.bumptech.glide.request.transition;

import android.view.View;

public class ViewPropertyTransition<R>
  implements Transition<R>
{
  private final Animator animator;
  
  public ViewPropertyTransition(Animator paramAnimator)
  {
    this.animator = paramAnimator;
  }
  
  public boolean transition(R paramR, Transition.ViewAdapter paramViewAdapter)
  {
    if (paramViewAdapter.getView() != null) {
      this.animator.animate(paramViewAdapter.getView());
    }
    return false;
  }
  
  public static abstract interface Animator
  {
    public abstract void animate(View paramView);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\request\transition\ViewPropertyTransition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */