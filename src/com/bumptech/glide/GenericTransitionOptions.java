package com.bumptech.glide;

import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.request.transition.ViewPropertyTransition.Animator;

public final class GenericTransitionOptions<TranscodeType>
  extends TransitionOptions<GenericTransitionOptions<TranscodeType>, TranscodeType>
{
  public static <TranscodeType> GenericTransitionOptions<TranscodeType> with(int paramInt)
  {
    return (GenericTransitionOptions)new GenericTransitionOptions().transition(paramInt);
  }
  
  public static <TranscodeType> GenericTransitionOptions<TranscodeType> with(TransitionFactory<? super TranscodeType> paramTransitionFactory)
  {
    return (GenericTransitionOptions)new GenericTransitionOptions().transition(paramTransitionFactory);
  }
  
  public static <TranscodeType> GenericTransitionOptions<TranscodeType> with(ViewPropertyTransition.Animator paramAnimator)
  {
    return (GenericTransitionOptions)new GenericTransitionOptions().transition(paramAnimator);
  }
  
  public static <TranscodeType> GenericTransitionOptions<TranscodeType> withNoTransition()
  {
    return (GenericTransitionOptions)new GenericTransitionOptions().dontTransition();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\GenericTransitionOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */