package cc.makeblock.makeblock.customview.laboratory;

import android.content.Context;
import android.graphics.Canvas;
import android.support.percent.PercentRelativeLayout;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class LaboratoryCellView
  extends PercentRelativeLayout
{
  private static final float DRAG_PHOTO_VIEW_ALPHA = 0.8F;
  private static final int SHOW_TIME = 300;
  private int currentMode = 1;
  private int index;
  private boolean isCheckPressLong;
  private int mDownRawX;
  private int mDownRawY;
  private onDragListener mDragListener;
  private View mDragView;
  private boolean mIsDragging;
  private int mItemOffsetX;
  private int mItemOffsetY;
  private int mMoveRawX;
  private int mMoveRawY;
  private int mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
  private WindowManager.LayoutParams mWindowLayoutParams;
  private WindowManager mWindowManager;
  private ViewGroup mWindowView;
  
  public LaboratoryCellView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public LaboratoryCellView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public LaboratoryCellView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setWillNotDraw(false);
  }
  
  private void createDragPhotoView(View paramView)
  {
    if (this.mWindowManager == null) {
      this.mWindowManager = ((WindowManager)getContext().getSystemService("window"));
    }
    if (this.mWindowLayoutParams == null) {
      initWindowLayoutParams();
    }
    this.mWindowLayoutParams.x = (this.mDownRawX - this.mItemOffsetX);
    this.mWindowLayoutParams.y = (this.mDownRawY - this.mItemOffsetY);
    this.mWindowView = new FrameLayout(getContext())
    {
      protected void onDraw(Canvas paramAnonymousCanvas)
      {
        super.onDraw(paramAnonymousCanvas);
      }
    };
    this.mWindowView.setTag(2131296603, Integer.valueOf(getIndex()));
    this.mWindowView.setBackgroundResource(2131165278);
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(this.mDragView.getWidth(), this.mDragView.getHeight());
    localLayoutParams.gravity = 17;
    this.mWindowView.addView(paramView, localLayoutParams);
    this.mWindowManager.addView(this.mWindowView, this.mWindowLayoutParams);
  }
  
  private void initWindowLayoutParams()
  {
    this.mWindowLayoutParams = new WindowManager.LayoutParams();
    this.mWindowLayoutParams.width = ((int)(getWidth() * 1.1F));
    this.mWindowLayoutParams.height = ((int)(getHeight() * 1.1F));
    this.mWindowLayoutParams.gravity = 8388659;
    this.mWindowLayoutParams.format = -3;
    this.mWindowLayoutParams.flags = 1944;
    this.mWindowLayoutParams.type = 2;
    this.mWindowLayoutParams.windowAnimations = 0;
    this.mWindowLayoutParams.alpha = 0.8F;
  }
  
  private boolean isDragging()
  {
    return ((LaboratoryPanelLayout)getParent()).isDragging();
  }
  
  private void setNormalBg(int paramInt)
  {
    int i = 0;
    if (paramInt == 1) {
      i = 2131165273;
    }
    for (;;)
    {
      setBackgroundResource(i);
      return;
      if (paramInt == 2) {
        if (isEmpty()) {
          i = 2131165275;
        } else {
          i = 2131165276;
        }
      }
    }
  }
  
  private void startDrag()
  {
    if ((isEmpty()) || (isDragging())) {}
    do
    {
      return;
      this.mIsDragging = true;
      this.mDragView = removeCellView();
      this.mDragView.setTag(2131296605, this.mDragView.getLayoutParams());
      createDragPhotoView(this.mDragView);
    } while (this.mDragListener == null);
    this.mDragListener.onStart(this.mWindowView, this.mDownRawX - this.mItemOffsetX, this.mDownRawY - this.mItemOffsetY, this.mDownRawX, this.mDownRawY);
  }
  
  private void stopDrag(int paramInt1, int paramInt2)
  {
    if (this.mWindowView == null) {
      return;
    }
    if ((this.mWindowView != null) && (this.mWindowManager != null)) {
      this.mWindowManager.removeView(this.mWindowView);
    }
    if (this.mDragListener != null) {
      this.mDragListener.onDrop(this.mWindowView, paramInt1, paramInt2, paramInt1 + this.mItemOffsetX, paramInt2 + this.mItemOffsetY);
    }
    this.mIsDragging = false;
    this.mDragView = null;
    this.mWindowView = null;
  }
  
  private void updateDragView(final int paramInt1, final int paramInt2)
  {
    post(new Runnable()
    {
      public void run()
      {
        if (LaboratoryCellView.this.mWindowView != null)
        {
          LaboratoryCellView.this.mWindowLayoutParams.x = paramInt1;
          LaboratoryCellView.this.mWindowLayoutParams.y = paramInt2;
          LaboratoryCellView.this.mWindowManager.updateViewLayout(LaboratoryCellView.this.mWindowView, LaboratoryCellView.this.mWindowLayoutParams);
          if (LaboratoryCellView.this.mDragListener != null) {
            LaboratoryCellView.this.mDragListener.onDrag(LaboratoryCellView.this.mWindowView, paramInt1, paramInt2, paramInt1 + LaboratoryCellView.this.mItemOffsetX, paramInt2 + LaboratoryCellView.this.mItemOffsetY);
          }
        }
      }
    });
  }
  
  public void addCellView(View paramView, float paramFloat)
  {
    removeAllViews();
    PercentRelativeLayout.LayoutParams localLayoutParams = generateDefaultLayoutParams();
    localLayoutParams.width = 0;
    localLayoutParams.height = 0;
    localLayoutParams.getPercentLayoutInfo().widthPercent = paramFloat;
    localLayoutParams.getPercentLayoutInfo().heightPercent = paramFloat;
    localLayoutParams.addRule(13);
    addView(paramView, localLayoutParams);
    setBackground();
    postDelayed(new Runnable()
    {
      public void run()
      {
        LaboratoryCellView.this.requestLayout();
      }
    }, 150L);
  }
  
  public void draw(Canvas paramCanvas)
  {
    if ((isEmpty()) && (this.currentMode == 3) && (getBackground() == null)) {
      setBackgroundResource(2131165273);
    }
    super.draw(paramCanvas);
  }
  
  public int getIndex()
  {
    return this.index;
  }
  
  public boolean isEmpty()
  {
    return getChildCount() == 0;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.currentMode == 2) {
      return true;
    }
    return super.onInterceptTouchEvent(paramMotionEvent);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = true;
    if (this.currentMode == 1) {
      bool = super.onTouchEvent(paramMotionEvent);
    }
    do
    {
      do
      {
        return bool;
        switch (paramMotionEvent.getAction())
        {
        default: 
          return true;
        case 0: 
          this.mDownRawX = ((int)paramMotionEvent.getRawX());
          this.mDownRawY = ((int)paramMotionEvent.getRawY());
          this.mItemOffsetX = ((int)paramMotionEvent.getX());
          this.mItemOffsetY = ((int)paramMotionEvent.getY());
          this.isCheckPressLong = true;
          postDelayed(new CheckLongClickRunnable(null), ViewConfiguration.getLongPressTimeout());
          if (!this.mIsDragging) {
            super.onTouchEvent(paramMotionEvent);
          }
        case 2: 
          this.mMoveRawX = ((int)paramMotionEvent.getRawX());
          this.mMoveRawY = ((int)paramMotionEvent.getRawY());
          if (this.mIsDragging)
          {
            updateDragView(this.mMoveRawX - this.mItemOffsetX, this.mMoveRawY - this.mItemOffsetY);
            return true;
          }
          int i = this.mMoveRawX;
          int j = this.mDownRawX;
          int k = this.mMoveRawY;
          int m = this.mDownRawY;
          if (((Math.abs(i - j) >= this.mTouchSlop) || (Math.abs(k - m) >= this.mTouchSlop)) && (!isEmpty()))
          {
            this.isCheckPressLong = false;
            startDrag();
            requestDisallowInterceptTouchEvent(true);
          }
          break;
        }
      } while (this.mIsDragging);
      super.onTouchEvent(paramMotionEvent);
      return true;
      this.isCheckPressLong = false;
      if (this.mIsDragging)
      {
        stopDrag((int)paramMotionEvent.getRawX() - this.mItemOffsetX, (int)paramMotionEvent.getRawY() - this.mItemOffsetY);
        return true;
      }
    } while (isDragging());
    super.onTouchEvent(paramMotionEvent);
    return true;
  }
  
  public View removeCellView()
  {
    View localView = getChildAt(0);
    if (localView != null)
    {
      localView.setVisibility(0);
      removeAllViews();
    }
    return localView;
  }
  
  public void setBackground()
  {
    int i;
    if (this.currentMode == 2) {
      if (isEmpty()) {
        i = 2131165274;
      }
    }
    for (;;)
    {
      setBackgroundResource(i);
      return;
      i = 2131165276;
      continue;
      i = 2131165273;
    }
  }
  
  public void setDragListener(onDragListener paramonDragListener)
  {
    this.mDragListener = paramonDragListener;
  }
  
  public void setIndex(int paramInt)
  {
    this.index = paramInt;
    setTag(2131296607, Integer.valueOf(paramInt));
  }
  
  public void setMode(int paramInt)
  {
    this.currentMode = paramInt;
    setNormalBg(paramInt);
  }
  
  private class CheckLongClickRunnable
    implements Runnable
  {
    private CheckLongClickRunnable() {}
    
    public void run()
    {
      if ((!LaboratoryCellView.this.mIsDragging) && (LaboratoryCellView.this.isCheckPressLong))
      {
        LaboratoryCellView.this.startDrag();
        LaboratoryCellView.this.requestDisallowInterceptTouchEvent(true);
      }
    }
  }
  
  public static abstract interface onDragListener
  {
    public abstract boolean onDrag(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
    
    public abstract boolean onDrop(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
    
    public abstract boolean onStart(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\laboratory\LaboratoryCellView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */