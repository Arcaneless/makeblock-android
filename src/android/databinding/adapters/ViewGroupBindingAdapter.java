package android.databinding.adapters;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethods;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

@BindingMethods({@android.databinding.BindingMethod(attribute="android:alwaysDrawnWithCache", method="setAlwaysDrawnWithCacheEnabled", type=ViewGroup.class), @android.databinding.BindingMethod(attribute="android:animationCache", method="setAnimationCacheEnabled", type=ViewGroup.class), @android.databinding.BindingMethod(attribute="android:splitMotionEvents", method="setMotionEventSplittingEnabled", type=ViewGroup.class)})
public class ViewGroupBindingAdapter
{
  @TargetApi(11)
  @BindingAdapter({"android:animateLayoutChanges"})
  public static void setAnimateLayoutChanges(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramViewGroup.setLayoutTransition(new LayoutTransition());
      return;
    }
    paramViewGroup.setLayoutTransition(null);
  }
  
  @BindingAdapter(requireAll=false, value={"android:onAnimationStart", "android:onAnimationEnd", "android:onAnimationRepeat"})
  public static void setListener(ViewGroup paramViewGroup, OnAnimationStart paramOnAnimationStart, final OnAnimationEnd paramOnAnimationEnd, final OnAnimationRepeat paramOnAnimationRepeat)
  {
    if ((paramOnAnimationStart == null) && (paramOnAnimationEnd == null) && (paramOnAnimationRepeat == null))
    {
      paramViewGroup.setLayoutAnimationListener(null);
      return;
    }
    paramViewGroup.setLayoutAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        if (paramOnAnimationEnd != null) {
          paramOnAnimationEnd.onAnimationEnd(paramAnonymousAnimation);
        }
      }
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
        if (paramOnAnimationRepeat != null) {
          paramOnAnimationRepeat.onAnimationRepeat(paramAnonymousAnimation);
        }
      }
      
      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
        if (this.val$start != null) {
          this.val$start.onAnimationStart(paramAnonymousAnimation);
        }
      }
    });
  }
  
  @BindingAdapter(requireAll=false, value={"android:onChildViewAdded", "android:onChildViewRemoved"})
  public static void setListener(ViewGroup paramViewGroup, OnChildViewAdded paramOnChildViewAdded, final OnChildViewRemoved paramOnChildViewRemoved)
  {
    if ((paramOnChildViewAdded == null) && (paramOnChildViewRemoved == null))
    {
      paramViewGroup.setOnHierarchyChangeListener(null);
      return;
    }
    paramViewGroup.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener()
    {
      public void onChildViewAdded(View paramAnonymousView1, View paramAnonymousView2)
      {
        if (this.val$added != null) {
          this.val$added.onChildViewAdded(paramAnonymousView1, paramAnonymousView2);
        }
      }
      
      public void onChildViewRemoved(View paramAnonymousView1, View paramAnonymousView2)
      {
        if (paramOnChildViewRemoved != null) {
          paramOnChildViewRemoved.onChildViewRemoved(paramAnonymousView1, paramAnonymousView2);
        }
      }
    });
  }
  
  public static abstract interface OnAnimationEnd
  {
    public abstract void onAnimationEnd(Animation paramAnimation);
  }
  
  public static abstract interface OnAnimationRepeat
  {
    public abstract void onAnimationRepeat(Animation paramAnimation);
  }
  
  public static abstract interface OnAnimationStart
  {
    public abstract void onAnimationStart(Animation paramAnimation);
  }
  
  public static abstract interface OnChildViewAdded
  {
    public abstract void onChildViewAdded(View paramView1, View paramView2);
  }
  
  public static abstract interface OnChildViewRemoved
  {
    public abstract void onChildViewRemoved(View paramView1, View paramView2);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\ViewGroupBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */