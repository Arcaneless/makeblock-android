package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.gzsll.jsbridge.WVJBWebView;

public class ListenerScrollWebView
  extends WVJBWebView
{
  private float mDestY;
  private float mLastY;
  private ScrollListener mScrollListener;
  private int mTouchSlop;
  
  public ListenerScrollWebView(Context paramContext)
  {
    super(paramContext);
    initView(paramContext, null);
  }
  
  public ListenerScrollWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext, paramAttributeSet);
  }
  
  private void initView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mScrollListener != null) {
      switch (paramMotionEvent.getAction())
      {
      }
    }
    for (;;)
    {
      return super.onTouchEvent(paramMotionEvent);
      this.mDestY = 0.0F;
      this.mLastY = paramMotionEvent.getY();
      continue;
      float f = paramMotionEvent.getY();
      this.mDestY = (f - this.mLastY);
      if (this.mDestY >= this.mTouchSlop)
      {
        this.mLastY = f;
        this.mScrollListener.scrollDown();
      }
      else if (this.mDestY <= -this.mTouchSlop)
      {
        this.mScrollListener.scrollUp();
        this.mLastY = f;
      }
    }
  }
  
  public void setScrollListener(ScrollListener paramScrollListener)
  {
    this.mScrollListener = paramScrollListener;
  }
  
  public static abstract interface ScrollListener
  {
    public abstract void scrollDown();
    
    public abstract void scrollUp();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\ListenerScrollWebView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */