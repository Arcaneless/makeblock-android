package cc.makeblock.makeblock.customview.cell;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import cc.makeblock.makeblock.R.styleable;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.engine.protocol.web.receive.SendValueToWidgetJson;
import cc.makeblock.makeblock.scene.panel.WidgetFactory;

public abstract class CellView<T>
  extends FrameLayout
{
  private static final long DEFAULT_INTERVAL = 150L;
  private static final float DRAG_PHOTO_VIEW_ALPHA = 0.7F;
  public static final int MODE_DESIGN = 2;
  public static final int MODE_ICON = 0;
  public static final int MODE_PLAY = 1;
  private static final String TAG = "CellView";
  private static final int long_press_time = 150;
  private static final int show_time = 300;
  protected CellLayout cellLayout;
  private float cell_height;
  private float cell_width;
  private float cell_x;
  private float cell_y;
  private boolean checkLongClick = false;
  protected int currentMode = -1;
  private T lastState;
  private int mDownRawX;
  private int mDownRawY;
  private int mDownX;
  private int mDownY;
  private View mDragView;
  private HandleState mHandleState = HandleState.INIT;
  private boolean mIsDragging;
  private int mItemOffsetX;
  private int mItemOffsetY;
  private int mMoveX;
  private int mMoveY;
  protected OnWidgetTriggeredListener mOnWidgetTriggeredListener;
  private CellView<T>.RefreshStateRunnable mRefreshStateRunnable;
  private int mTouchSlop;
  private WindowManager.LayoutParams mWindowLayoutParams;
  private WindowManager mWindowManager;
  private float realHeight = 0.0F;
  private float realWidth = 0.0F;
  protected WidgetData widgetData;
  
  public CellView(Context paramContext)
  {
    super(paramContext);
    init(null, 0);
  }
  
  public CellView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet, 0);
  }
  
  public CellView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet, paramInt);
  }
  
  private int adjustDragX(int paramInt)
  {
    return paramInt;
  }
  
  private int adjustDragY(int paramInt)
  {
    return paramInt;
  }
  
  private void createDragPhotoView()
  {
    this.mWindowManager = ((WindowManager)getContext().getSystemService("window"));
    this.mWindowLayoutParams = new WindowManager.LayoutParams();
    this.mWindowLayoutParams.width = getCpWidth();
    this.mWindowLayoutParams.height = getCpHeight();
    this.mWindowLayoutParams.gravity = 8388659;
    this.mWindowLayoutParams.format = -3;
    this.mWindowLayoutParams.flags = 1944;
    this.mWindowLayoutParams.type = 2;
    this.mWindowLayoutParams.windowAnimations = 0;
    this.mWindowLayoutParams.alpha = 0.7F;
    this.mWindowLayoutParams.x = adjustDragX(this.mDownRawX - this.mItemOffsetX);
    this.mWindowLayoutParams.y = adjustDragY(this.mDownRawY - this.mItemOffsetY);
    this.mDragView = WidgetFactory.createCellView(getContext(), getWidgetData(), this.currentMode);
    this.mDragView.setAlpha(0.7F);
    this.mWindowManager.addView(this.mDragView, this.mWindowLayoutParams);
    this.mDragView.setPivotX(getCpWidth() / 2);
    this.mDragView.setPivotY(getCpHeight() / 2);
    Object localObject = PropertyValuesHolder.ofFloat("scaleX", new float[] { getWidth() * 1.0F / getCpWidth(), 1.0F });
    PropertyValuesHolder localPropertyValuesHolder = PropertyValuesHolder.ofFloat("scaleY", new float[] { getHeight() * 1.0F / getCpHeight(), 1.0F });
    localObject = ObjectAnimator.ofPropertyValuesHolder(this.mDragView, new PropertyValuesHolder[] { localObject, localPropertyValuesHolder });
    ((ObjectAnimator)localObject).setDuration(300L);
    ((ObjectAnimator)localObject).start();
    if (this.currentMode == 0)
    {
      postDelayed(new Runnable()
      {
        public void run()
        {
          CellView.this.cellLayout.onStartDrag();
        }
      }, 300L);
      return;
    }
    this.cellLayout.onStartDrag();
  }
  
  private int getCpHeight()
  {
    return (int)(getCellHeight() * this.cellLayout.getHeight() / this.cellLayout.getCell_height_count());
  }
  
  private int getCpWidth()
  {
    return (int)(getCellWidth() * this.cellLayout.getWidth() / this.cellLayout.getCell_width_count());
  }
  
  private void init(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.CellView, paramInt, 0);
    this.cell_height = paramAttributeSet.getInt(0, 0);
    this.cell_width = paramAttributeSet.getInt(1, 0);
    this.cell_x = paramAttributeSet.getInt(2, 0);
    this.cell_y = paramAttributeSet.getInt(3, 0);
    paramAttributeSet.recycle();
    this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
  }
  
  private int measureHeight(int paramInt)
  {
    int i = View.MeasureSpec.getMode(paramInt);
    paramInt = View.MeasureSpec.getSize(paramInt);
    switch (i)
    {
    default: 
      return 0;
    }
    return paramInt;
  }
  
  private int measureWidth(int paramInt)
  {
    int i = View.MeasureSpec.getMode(paramInt);
    paramInt = View.MeasureSpec.getSize(paramInt);
    switch (i)
    {
    default: 
      return 0;
    }
    return paramInt;
  }
  
  private void notifyChange()
  {
    ViewParent localViewParent = getParent();
    if ((localViewParent instanceof CellLayout)) {
      ((CellLayout)localViewParent).updateChildView(this);
    }
  }
  
  private boolean startDrag()
  {
    this.mIsDragging = true;
    boolean bool = true;
    if (getCurrentMode() == 2) {
      bool = false;
    }
    this.cellLayout.setCurrentDragCellView(this, bool);
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.7F);
    localAlphaAnimation.setDuration(50L);
    localAlphaAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        if ((CellView.this.mIsDragging) && (CellView.this.getCurrentMode() == 2)) {
          CellView.this.setVisibility(4);
        }
      }
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
      
      public void onAnimationStart(Animation paramAnonymousAnimation) {}
    });
    startAnimation(localAlphaAnimation);
    this.mItemOffsetX = (this.mDownX + 10);
    this.mItemOffsetY = (this.mDownY + 10);
    if (this.currentMode == 0)
    {
      this.mItemOffsetX += (getCpWidth() - getWidth()) / 2;
      this.mItemOffsetY += (getCpHeight() - getHeight()) / 2;
      this.mDownX += (getCpWidth() - getWidth()) / 2;
      this.mDownY += (getCpHeight() - getHeight()) / 2;
      this.mItemOffsetX -= 10;
      this.mItemOffsetY -= 10;
    }
    createDragPhotoView();
    return true;
  }
  
  private void stopDrag()
  {
    if (this.mDragView != null)
    {
      this.mWindowManager.removeView(this.mDragView);
      this.mDragView = null;
    }
    this.mIsDragging = false;
  }
  
  private void updateDragView()
  {
    if (this.mDragView != null)
    {
      this.mWindowLayoutParams.x = adjustDragX(this.mMoveX - this.mItemOffsetX);
      this.mWindowLayoutParams.y = adjustDragY(this.mMoveY - this.mItemOffsetY);
      this.mWindowManager.updateViewLayout(this.mDragView, this.mWindowLayoutParams);
    }
  }
  
  public float getCellHeight()
  {
    return this.cell_height;
  }
  
  public float getCellWidth()
  {
    return this.cell_width;
  }
  
  public float getCellX()
  {
    return this.cell_x;
  }
  
  public float getCellY()
  {
    return this.cell_y;
  }
  
  public int getCurrentMode()
  {
    return this.currentMode;
  }
  
  public boolean getDeletable()
  {
    return true;
  }
  
  public int getItemOffsetX()
  {
    return this.mItemOffsetX;
  }
  
  public int getItemOffsetY()
  {
    return this.mItemOffsetY;
  }
  
  public boolean getNameChangeable()
  {
    return true;
  }
  
  public boolean getPortSelectable()
  {
    return false;
  }
  
  public boolean getProgrammable()
  {
    return false;
  }
  
  public int getValue()
  {
    return 0;
  }
  
  public WidgetData getWidgetData()
  {
    return this.widgetData;
  }
  
  public void notifyWidgetBeanChanged() {}
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.cellLayout = ((CellLayout)((Activity)getContext()).findViewById(2131296604));
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    this.mWindowManager = null;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.currentMode != 1) {
      return true;
    }
    return super.onInterceptTouchEvent(paramMotionEvent);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    View localView = getChildAt(0);
    float f1;
    float f2;
    if (localView != null)
    {
      f1 = this.realHeight / this.cell_height;
      f2 = this.realWidth / this.cell_width;
      if (f1 < f2) {
        break label104;
      }
      paramInt1 = 1;
      if (paramInt1 == 0) {
        break label109;
      }
      f1 = f2;
    }
    label104:
    label109:
    for (;;)
    {
      f2 = f1 * this.cell_width;
      f1 *= this.cell_height;
      if (paramInt1 == 0) {
        break label112;
      }
      localView.layout(0, (int)((this.realHeight - f1) / 2.0F), (int)f2, (int)((this.realHeight + f1) / 2.0F));
      return;
      paramInt1 = 0;
      break;
    }
    label112:
    localView.layout((int)((this.realWidth - f2) / 2.0F), 0, (int)((this.realWidth + f2) / 2.0F), (int)f1);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = measureWidth(paramInt1);
    paramInt2 = measureHeight(paramInt2);
    float f1;
    float f2;
    if ((i != 0) && (paramInt2 != 0))
    {
      this.realWidth = i;
      this.realHeight = paramInt2;
      f1 = this.realHeight / this.cell_height;
      f2 = this.realWidth / this.cell_width;
      if (f1 < f2) {
        break label122;
      }
      paramInt1 = 1;
      if (paramInt1 == 0) {
        break label127;
      }
      f1 = f2;
    }
    label122:
    label127:
    for (;;)
    {
      f2 = this.cell_width;
      paramInt1 = View.MeasureSpec.makeMeasureSpec((int)(f1 * this.cell_height), 1073741824);
      measureChildren(View.MeasureSpec.makeMeasureSpec((int)(f1 * f2), 1073741824), paramInt1);
      setMeasuredDimension(i, paramInt2);
      return;
      paramInt1 = 0;
      break;
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.currentMode == 1) {
      return false;
    }
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      return this.mIsDragging;
      this.mDownX = ((int)paramMotionEvent.getX());
      this.mDownY = ((int)paramMotionEvent.getY());
      this.mDownRawX = ((int)paramMotionEvent.getRawX());
      this.mDownRawY = ((int)paramMotionEvent.getRawY());
      this.checkLongClick = true;
      postDelayed(new CheckLongClickRunnable(null), 150L);
      this.cellLayout.onDrag(paramMotionEvent);
      return true;
      this.mMoveX = ((int)paramMotionEvent.getRawX());
      this.mMoveY = ((int)paramMotionEvent.getRawY());
      if (this.mIsDragging)
      {
        requestDisallowInterceptTouchEvent(true);
        updateDragView();
        this.cellLayout.onDrag(paramMotionEvent);
      }
      int i = this.mMoveX;
      int j = this.mDownRawX;
      int k = this.mMoveY;
      int m = this.mDownRawY;
      if ((this.checkLongClick) && ((Math.abs(i - j) >= this.mTouchSlop) || (Math.abs(k - m) >= this.mTouchSlop))) {
        this.checkLongClick = false;
      }
      return this.mIsDragging;
      if (this.mIsDragging) {
        this.cellLayout.onDrag(paramMotionEvent);
      }
      this.checkLongClick = false;
      if ((!this.mIsDragging) && (getCurrentMode() == 2) && (!this.cellLayout.isDragging()))
      {
        float f1 = paramMotionEvent.getX();
        float f2 = this.mDownX;
        float f3 = paramMotionEvent.getY();
        float f4 = this.mDownY;
        if ((Math.abs(f1 - f2) < this.mTouchSlop) && (Math.abs(f3 - f4) < this.mTouchSlop)) {
          performClick();
        }
      }
      stopDrag();
      continue;
      if (this.mIsDragging) {
        this.cellLayout.onDrag(paramMotionEvent);
      }
      this.checkLongClick = false;
      stopDrag();
    }
  }
  
  protected final void pushState(T paramT)
  {
    switch (this.mHandleState)
    {
    default: 
      return;
    case ???: 
      sendState(paramT);
      if (this.mRefreshStateRunnable == null) {
        this.mRefreshStateRunnable = new RefreshStateRunnable(150L);
      }
      postDelayed(this.mRefreshStateRunnable, 150L);
      this.mHandleState = HandleState.SEND;
      return;
    case ???: 
      this.lastState = paramT;
      this.mHandleState = HandleState.WAIT;
      return;
    }
    this.lastState = paramT;
  }
  
  protected abstract void sendState(T paramT);
  
  public void setCellHeight(float paramFloat)
  {
    this.cell_height = paramFloat;
    notifyChange();
  }
  
  public void setCellWidth(float paramFloat)
  {
    this.cell_width = paramFloat;
    notifyChange();
  }
  
  public void setCellX(float paramFloat)
  {
    this.cell_x = paramFloat;
    notifyChange();
    this.widgetData.xPosition = ((int)this.cell_x);
  }
  
  public void setCellY(float paramFloat)
  {
    this.cell_y = paramFloat;
    notifyChange();
    this.widgetData.yPosition = ((int)this.cell_y);
  }
  
  public abstract void setControlListener();
  
  public void setMode(int paramInt)
  {
    this.currentMode = paramInt;
  }
  
  public void setOnWidgetTriggeredListener(OnWidgetTriggeredListener paramOnWidgetTriggeredListener)
  {
    this.mOnWidgetTriggeredListener = paramOnWidgetTriggeredListener;
  }
  
  public void setWidgetData(WidgetData paramWidgetData)
  {
    this.widgetData = paramWidgetData;
    notifyWidgetBeanChanged();
  }
  
  public boolean setWidgetValue(SendValueToWidgetJson paramSendValueToWidgetJson)
  {
    return false;
  }
  
  public boolean shouldShowEditPopup()
  {
    return true;
  }
  
  private class CheckLongClickRunnable
    implements Runnable
  {
    private CheckLongClickRunnable() {}
    
    public void run()
    {
      if ((CellView.this.checkLongClick) && (!CellView.this.cellLayout.isDragging()))
      {
        CellView.this.startDrag();
        CellView.this.requestDisallowInterceptTouchEvent(true);
      }
    }
  }
  
  protected static enum HandleState
  {
    INIT,  SEND,  WAIT;
    
    private HandleState() {}
  }
  
  public static abstract interface OnWidgetTriggeredListener
  {
    public abstract void onWidgetTrigger(String paramString1, String paramString2);
  }
  
  private class RefreshStateRunnable
    implements Runnable
  {
    private long interval = 50L;
    
    public RefreshStateRunnable(long paramLong)
    {
      this.interval = paramLong;
    }
    
    public void run()
    {
      switch (CellView.3.$SwitchMap$cc$makeblock$makeblock$customview$cell$CellView$HandleState[CellView.this.mHandleState.ordinal()])
      {
      default: 
        return;
      case 1: 
        throw new RuntimeException("不应该进到这里的");
      case 2: 
        CellView.access$402(CellView.this, null);
        CellView.access$302(CellView.this, CellView.HandleState.INIT);
        return;
      }
      CellView.this.sendState(CellView.this.lastState);
      CellView.access$302(CellView.this, CellView.HandleState.SEND);
      CellView.this.postDelayed(this, this.interval);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\cell\CellView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */