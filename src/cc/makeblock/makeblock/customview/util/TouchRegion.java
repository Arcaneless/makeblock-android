package cc.makeblock.makeblock.customview.util;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;

public class TouchRegion
{
  private TouchDelegateGroup touchDelegateGroup;
  
  public TouchRegion(View paramView)
  {
    this.touchDelegateGroup = new TouchDelegateGroup(paramView);
  }
  
  public TouchRegion expandViewTouchRegion(View paramView, int paramInt)
  {
    expandViewTouchRegion(paramView, paramInt, paramInt, paramInt, paramInt);
    return this;
  }
  
  public TouchRegion expandViewTouchRegion(final View paramView, final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4)
  {
    if (paramView == null) {
      throw new RuntimeException("view cannot be null.");
    }
    final ViewGroup localViewGroup = (ViewGroup)paramView.getParent();
    if (localViewGroup != null) {
      localViewGroup.post(new Runnable()
      {
        public void run()
        {
          Rect localRect = new Rect();
          paramView.setEnabled(true);
          paramView.getHitRect(localRect);
          localRect.left -= paramInt1;
          localRect.top -= paramInt2;
          localRect.right += paramInt3;
          localRect.bottom += paramInt4;
          TouchRegion.this.touchDelegateGroup.addTouchDelegate(new TouchDelegate(localRect, paramView));
          if (View.class.isInstance(localViewGroup)) {
            localViewGroup.setTouchDelegate(TouchRegion.this.touchDelegateGroup);
          }
        }
      });
    }
    return this;
  }
  
  public void restoreViewTouchRegion(final View paramView)
  {
    if (paramView == null) {
      throw new RuntimeException("view cannot be null.");
    }
    final ViewGroup localViewGroup = (ViewGroup)paramView.getParent();
    if (localViewGroup != null) {
      localViewGroup.post(new Runnable()
      {
        public void run()
        {
          Rect localRect = new Rect();
          localRect.setEmpty();
          TouchRegion.this.touchDelegateGroup.addTouchDelegate(new TouchDelegate(localRect, paramView));
          if (View.class.isInstance(localViewGroup)) {
            localViewGroup.setTouchDelegate(TouchRegion.this.touchDelegateGroup);
          }
        }
      });
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\util\TouchRegion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */