package cc.makeblock.makeblock.engine.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class AndroidBug5497Workaround
{
  private FrameLayout.LayoutParams frameLayoutParams;
  private View mChildOfContent;
  private int usableHeightPrevious;
  
  private AndroidBug5497Workaround(Activity paramActivity)
  {
    this.mChildOfContent = ((FrameLayout)paramActivity.findViewById(16908290)).getChildAt(0);
    this.mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      public void onGlobalLayout()
      {
        AndroidBug5497Workaround.this.possiblyResizeChildOfContent();
      }
    });
    this.frameLayoutParams = ((FrameLayout.LayoutParams)this.mChildOfContent.getLayoutParams());
  }
  
  public static void assistActivity(Activity paramActivity)
  {
    new AndroidBug5497Workaround(paramActivity);
  }
  
  private int computeUsableHeight()
  {
    Rect localRect = new Rect();
    this.mChildOfContent.getWindowVisibleDisplayFrame(localRect);
    return localRect.bottom - localRect.top;
  }
  
  private void possiblyResizeChildOfContent()
  {
    int i = computeUsableHeight();
    int j;
    int k;
    if (i != this.usableHeightPrevious)
    {
      j = this.mChildOfContent.getRootView().getHeight();
      k = j - i;
      if (k <= j / 4) {
        break label58;
      }
    }
    label58:
    for (this.frameLayoutParams.height = (j - k);; this.frameLayoutParams.height = j)
    {
      this.mChildOfContent.requestLayout();
      this.usableHeightPrevious = i;
      return;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\AndroidBug5497Workaround.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */