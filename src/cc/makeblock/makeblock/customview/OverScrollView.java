package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Scroller;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class OverScrollView
  extends FrameLayout
  implements View.OnTouchListener
{
  static final int ANIMATED_SCROLL_GAP = 250;
  private static final int INVALID_POINTER = -1;
  static final float MAX_SCROLL_FACTOR = 0.5F;
  static final float OVERSHOOT_TENSION = 0.75F;
  protected View child;
  boolean hasFailedObtainingScrollFields;
  boolean isInFlingMode = false;
  private int mActivePointerId = -1;
  private View mChildToScrollTo = null;
  protected Context mContext;
  private boolean mFillViewport;
  private boolean mIsBeingDragged = false;
  private boolean mIsLayoutDirty = true;
  private float mLastMotionY;
  private long mLastScroll;
  private int mMaximumVelocity;
  private int mMinimumVelocity;
  private boolean mScrollViewMovedFocus;
  Field mScrollXField;
  Field mScrollYField;
  private Scroller mScroller;
  private boolean mSmoothScrollingEnabled = true;
  private final Rect mTempRect = new Rect();
  private int mTouchSlop;
  private VelocityTracker mVelocityTracker;
  DisplayMetrics metrics;
  private Runnable overScrollerSpringbackTask;
  int prevScrollY;
  
  public OverScrollView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public OverScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public OverScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
    initScrollView();
    setFillViewport(true);
    initBounce();
  }
  
  private void SetScrollX(int paramInt)
  {
    if (this.mScrollXField != null) {}
    try
    {
      this.mScrollXField.setInt(this, paramInt);
      return;
    }
    catch (Exception localException) {}
  }
  
  private void SetScrollY(int paramInt)
  {
    if (this.mScrollYField != null) {}
    try
    {
      this.mScrollYField.setInt(this, paramInt);
      return;
    }
    catch (Exception localException) {}
  }
  
  private boolean canScroll()
  {
    boolean bool2 = false;
    View localView = getChildAt(0);
    boolean bool1 = bool2;
    if (localView != null)
    {
      int i = localView.getHeight();
      bool1 = bool2;
      if (getHeight() < getPaddingTop() + i + getPaddingBottom()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private int clamp(int paramInt1, int paramInt2, int paramInt3)
  {
    int i;
    if ((paramInt2 >= paramInt3) || (paramInt1 < 0)) {
      i = 0;
    }
    do
    {
      return i;
      i = paramInt1;
    } while (paramInt2 + paramInt1 <= paramInt3);
    return paramInt3 - paramInt2;
  }
  
  private void doScrollY(int paramInt)
  {
    if (paramInt != 0)
    {
      if (this.mSmoothScrollingEnabled) {
        smoothScrollBy(0, paramInt);
      }
    }
    else {
      return;
    }
    scrollBy(0, paramInt);
  }
  
  private View findFocusableViewInBounds(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = getFocusables(2);
    Object localObject2 = null;
    int m = 0;
    int i1 = localArrayList.size();
    int k = 0;
    if (k < i1)
    {
      View localView = (View)localArrayList.get(k);
      int n = localView.getTop();
      int i2 = localView.getBottom();
      Object localObject1 = localObject2;
      int j = m;
      int i;
      if (paramInt1 < i2)
      {
        localObject1 = localObject2;
        j = m;
        if (n < paramInt2)
        {
          if ((paramInt1 >= n) || (i2 >= paramInt2)) {
            break label133;
          }
          i = 1;
          label103:
          if (localObject2 != null) {
            break label139;
          }
          localObject1 = localView;
          j = i;
        }
      }
      for (;;)
      {
        k += 1;
        localObject2 = localObject1;
        m = j;
        break;
        label133:
        i = 0;
        break label103;
        label139:
        if (((paramBoolean) && (n < ((View)localObject2).getTop())) || ((!paramBoolean) && (i2 > ((View)localObject2).getBottom()))) {}
        for (n = 1;; n = 0)
        {
          if (m == 0) {
            break label218;
          }
          localObject1 = localObject2;
          j = m;
          if (i == 0) {
            break;
          }
          localObject1 = localObject2;
          j = m;
          if (n == 0) {
            break;
          }
          localObject1 = localView;
          j = m;
          break;
        }
        label218:
        if (i != 0)
        {
          localObject1 = localView;
          j = 1;
        }
        else
        {
          localObject1 = localObject2;
          j = m;
          if (n != 0)
          {
            localObject1 = localView;
            j = m;
          }
        }
      }
    }
    return (View)localObject2;
  }
  
  private View findFocusableViewInMyBounds(boolean paramBoolean, int paramInt, View paramView)
  {
    int j = getVerticalFadingEdgeLength() / 2;
    int i = paramInt + j;
    paramInt = getHeight() + paramInt - j;
    if ((paramView != null) && (paramView.getTop() < paramInt) && (paramView.getBottom() > i)) {
      return paramView;
    }
    return findFocusableViewInBounds(paramBoolean, i, paramInt);
  }
  
  private void initBounce()
  {
    this.metrics = this.mContext.getResources().getDisplayMetrics();
    this.mScroller = new Scroller(getContext(), new OvershootInterpolator(0.75F));
    this.overScrollerSpringbackTask = new Runnable()
    {
      public void run()
      {
        OverScrollView.this.mScroller.computeScrollOffset();
        OverScrollView.this.scrollTo(0, OverScrollView.this.mScroller.getCurrY());
        if (!OverScrollView.this.mScroller.isFinished()) {
          OverScrollView.this.post(this);
        }
      }
    };
    this.prevScrollY = getPaddingTop();
    try
    {
      this.mScrollXField = View.class.getDeclaredField("mScrollX");
      this.mScrollYField = View.class.getDeclaredField("mScrollY");
      return;
    }
    catch (Exception localException)
    {
      this.hasFailedObtainingScrollFields = true;
    }
  }
  
  private void initScrollView()
  {
    this.mScroller = new Scroller(getContext());
    setFocusable(true);
    setDescendantFocusability(262144);
    setWillNotDraw(false);
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(this.mContext);
    this.mTouchSlop = localViewConfiguration.getScaledTouchSlop();
    this.mMinimumVelocity = localViewConfiguration.getScaledMinimumFlingVelocity();
    this.mMaximumVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
    setOnTouchListener(this);
    post(new Runnable()
    {
      public void run()
      {
        OverScrollView.this.scrollTo(0, OverScrollView.this.child.getPaddingTop());
      }
    });
  }
  
  private boolean isOffScreen(View paramView)
  {
    boolean bool = false;
    if (!isWithinDeltaOfScreen(paramView, 0, getHeight())) {
      bool = true;
    }
    return bool;
  }
  
  private boolean isViewDescendantOf(View paramView1, View paramView2)
  {
    if (paramView1 == paramView2) {}
    do
    {
      return true;
      paramView1 = paramView1.getParent();
    } while (((paramView1 instanceof ViewGroup)) && (isViewDescendantOf((View)paramView1, paramView2)));
    return false;
  }
  
  private boolean isWithinDeltaOfScreen(View paramView, int paramInt1, int paramInt2)
  {
    paramView.getDrawingRect(this.mTempRect);
    offsetDescendantRectToMyCoords(paramView, this.mTempRect);
    return (this.mTempRect.bottom + paramInt1 >= getScrollY()) && (this.mTempRect.top - paramInt1 <= getScrollY() + paramInt2);
  }
  
  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = (paramMotionEvent.getAction() & 0xFF00) >> 8;
    if (paramMotionEvent.getPointerId(i) == this.mActivePointerId) {
      if (i != 0) {
        break label63;
      }
    }
    label63:
    for (i = 1;; i = 0)
    {
      this.mLastMotionY = paramMotionEvent.getY(i);
      this.mActivePointerId = paramMotionEvent.getPointerId(i);
      if (this.mVelocityTracker != null) {
        this.mVelocityTracker.clear();
      }
      return;
    }
  }
  
  private boolean overScrollView()
  {
    int i = getHeight();
    int k = this.child.getPaddingTop();
    int m = this.child.getHeight() - this.child.getPaddingBottom();
    int j = getScrollY();
    if (j < k)
    {
      onOverScroll(j);
      i = k - j;
      this.mScroller.startScroll(0, j, 0, i, 500);
      post(this.overScrollerSpringbackTask);
      this.prevScrollY = j;
      return true;
    }
    if (j + i > m)
    {
      if (this.child.getHeight() - this.child.getPaddingTop() - this.child.getPaddingBottom() < i) {}
      for (i = k - j;; i = m - i - j)
      {
        i += onOverScroll(j);
        break;
      }
    }
    this.isInFlingMode = true;
    return false;
  }
  
  private boolean scrollAndFocus(int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool2 = true;
    int j = getHeight();
    int i = getScrollY();
    j = i + j;
    if (paramInt1 == 33) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      View localView = findFocusableViewInBounds(bool1, paramInt2, paramInt3);
      Object localObject = localView;
      if (localView == null) {
        localObject = this;
      }
      if ((paramInt2 < i) || (paramInt3 > j)) {
        break;
      }
      bool1 = false;
      if ((localObject != findFocus()) && (((View)localObject).requestFocus(paramInt1)))
      {
        this.mScrollViewMovedFocus = true;
        this.mScrollViewMovedFocus = false;
      }
      return bool1;
    }
    if (bool1) {
      paramInt2 -= i;
    }
    for (;;)
    {
      doScrollY(paramInt2);
      bool1 = bool2;
      break;
      paramInt2 = paramInt3 - j;
    }
  }
  
  private void scrollToChild(View paramView)
  {
    paramView.getDrawingRect(this.mTempRect);
    offsetDescendantRectToMyCoords(paramView, this.mTempRect);
    int i = computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
    if (i != 0) {
      scrollBy(0, i);
    }
  }
  
  private boolean scrollToChildRect(Rect paramRect, boolean paramBoolean)
  {
    int i = computeScrollDeltaToGetChildRectOnScreen(paramRect);
    if (i != 0) {}
    for (boolean bool = true;; bool = false)
    {
      if (bool)
      {
        if (!paramBoolean) {
          break;
        }
        scrollBy(0, i);
      }
      return bool;
    }
    smoothScrollBy(0, i);
    return bool;
  }
  
  public void addView(View paramView)
  {
    if (getChildCount() > 0) {
      throw new IllegalStateException("ScrollView can host only one direct child");
    }
    super.addView(paramView);
    initChildPointer();
  }
  
  public void addView(View paramView, int paramInt)
  {
    if (getChildCount() > 0) {
      throw new IllegalStateException("ScrollView can host only one direct child");
    }
    super.addView(paramView, paramInt);
    initChildPointer();
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (getChildCount() > 0) {
      throw new IllegalStateException("ScrollView can host only one direct child");
    }
    super.addView(paramView, paramInt, paramLayoutParams);
  }
  
  public void addView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (getChildCount() > 0) {
      throw new IllegalStateException("ScrollView can host only one direct child");
    }
    super.addView(paramView, paramLayoutParams);
    initChildPointer();
  }
  
  public boolean arrowScroll(int paramInt)
  {
    boolean bool = false;
    View localView2 = findFocus();
    View localView1 = localView2;
    if (localView2 == this) {
      localView1 = null;
    }
    localView2 = FocusFinder.getInstance().findNextFocus(this, localView1, paramInt);
    int k = getMaxScrollAmount();
    if ((localView2 != null) && (isWithinDeltaOfScreen(localView2, k, getHeight())))
    {
      localView2.getDrawingRect(this.mTempRect);
      offsetDescendantRectToMyCoords(localView2, this.mTempRect);
      doScrollY(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
      localView2.requestFocus(paramInt);
      if ((localView1 != null) && (localView1.isFocused()) && (isOffScreen(localView1)))
      {
        paramInt = getDescendantFocusability();
        setDescendantFocusability(131072);
        requestFocus();
        setDescendantFocusability(paramInt);
      }
      bool = true;
      label145:
      return bool;
    }
    int j = k;
    int i;
    if ((paramInt == 33) && (getScrollY() < j))
    {
      i = getScrollY();
      label170:
      if (i == 0) {
        break label247;
      }
      if (paramInt != 130) {
        break label249;
      }
    }
    for (;;)
    {
      doScrollY(i);
      break;
      i = j;
      if (paramInt != 130) {
        break label170;
      }
      i = j;
      if (getChildCount() <= 0) {
        break label170;
      }
      int m = getChildAt(0).getBottom();
      int n = getScrollY() + getHeight();
      i = j;
      if (m - n >= k) {
        break label170;
      }
      i = m - n;
      break label170;
      label247:
      break label145;
      label249:
      i = -i;
    }
  }
  
  public void computeScroll()
  {
    if (this.hasFailedObtainingScrollFields) {
      super.computeScroll();
    }
    while (!this.mScroller.computeScrollOffset()) {
      return;
    }
    int i = getScrollX();
    int j = getScrollY();
    int m = this.mScroller.getCurrX();
    int k = this.mScroller.getCurrY();
    if (getChildCount() > 0)
    {
      View localView = getChildAt(0);
      m = clamp(m, getWidth() - getPaddingRight() - getPaddingLeft(), localView.getWidth());
      k = clamp(k, getHeight() - getPaddingBottom() - getPaddingTop(), localView.getHeight());
      if ((m != i) || (k != j))
      {
        SetScrollX(m);
        SetScrollY(k);
        onScrollChanged(m, k, i, j);
      }
    }
    awakenScrollBars();
    postInvalidate();
  }
  
  protected int computeScrollDeltaToGetChildRectOnScreen(Rect paramRect)
  {
    if (getChildCount() == 0) {}
    int m;
    int j;
    do
    {
      return 0;
      m = getHeight();
      i = getScrollY();
      int k = i + m;
      int n = getVerticalFadingEdgeLength();
      j = i;
      if (paramRect.top > 0) {
        j = i + n;
      }
      i = k;
      if (paramRect.bottom < getChildAt(0).getHeight()) {
        i = k - n;
      }
      if ((paramRect.bottom > i) && (paramRect.top > j))
      {
        if (paramRect.height() > m) {}
        for (j = 0 + (paramRect.top - j);; j = 0 + (paramRect.bottom - i)) {
          return Math.min(j, getChildAt(0).getBottom() - i);
        }
      }
    } while ((paramRect.top >= j) || (paramRect.bottom >= i));
    if (paramRect.height() > m) {}
    for (int i = 0 - (i - paramRect.bottom);; i = 0 - (j - paramRect.top)) {
      return Math.max(i, -getScrollY());
    }
  }
  
  protected int computeVerticalScrollOffset()
  {
    return Math.max(0, super.computeVerticalScrollOffset());
  }
  
  protected int computeVerticalScrollRange()
  {
    int i = getChildCount();
    int j = getHeight();
    int k = getPaddingBottom();
    int m = getPaddingTop();
    if (i == 0) {
      return j - k - m;
    }
    return getChildAt(0).getBottom();
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return (super.dispatchKeyEvent(paramKeyEvent)) || (executeKeyEvent(paramKeyEvent));
  }
  
  public boolean executeKeyEvent(KeyEvent paramKeyEvent)
  {
    boolean bool2 = false;
    this.mTempRect.setEmpty();
    if (!canScroll())
    {
      bool1 = bool2;
      if (isFocused())
      {
        bool1 = bool2;
        if (paramKeyEvent.getKeyCode() != 4)
        {
          View localView = findFocus();
          paramKeyEvent = localView;
          if (localView == this) {
            paramKeyEvent = null;
          }
          paramKeyEvent = FocusFinder.getInstance().findNextFocus(this, paramKeyEvent, 130);
          bool1 = bool2;
          if (paramKeyEvent != null)
          {
            bool1 = bool2;
            if (paramKeyEvent != this)
            {
              bool1 = bool2;
              if (paramKeyEvent.requestFocus(130)) {
                bool1 = true;
              }
            }
          }
        }
      }
      return bool1;
    }
    bool2 = false;
    boolean bool1 = bool2;
    if (paramKeyEvent.getAction() == 0) {}
    switch (paramKeyEvent.getKeyCode())
    {
    default: 
      bool1 = bool2;
    case 19: 
    case 20: 
      for (;;)
      {
        return bool1;
        if (!paramKeyEvent.isAltPressed())
        {
          bool1 = arrowScroll(33);
        }
        else
        {
          bool1 = fullScroll(33);
          continue;
          if (!paramKeyEvent.isAltPressed()) {
            bool1 = arrowScroll(130);
          } else {
            bool1 = fullScroll(130);
          }
        }
      }
    }
    if (paramKeyEvent.isShiftPressed()) {}
    for (int i = 33;; i = 130)
    {
      pageScroll(i);
      bool1 = bool2;
      break;
    }
  }
  
  public void fling(int paramInt)
  {
    boolean bool;
    Object localObject;
    if (getChildCount() > 0)
    {
      int i = getHeight();
      int j = getPaddingBottom();
      int k = getPaddingTop();
      int m = getChildAt(0).getHeight();
      this.mScroller.fling(getScrollX(), getScrollY(), 0, paramInt, 0, 0, 0, Math.max(0, m - (i - j - k)));
      if (paramInt <= 0) {
        break label146;
      }
      bool = true;
      View localView = findFocusableViewInMyBounds(bool, this.mScroller.getFinalY(), findFocus());
      localObject = localView;
      if (localView == null) {
        localObject = this;
      }
      if (localObject != findFocus()) {
        if (!bool) {
          break label152;
        }
      }
    }
    label146:
    label152:
    for (paramInt = 130;; paramInt = 33)
    {
      if (((View)localObject).requestFocus(paramInt))
      {
        this.mScrollViewMovedFocus = true;
        this.mScrollViewMovedFocus = false;
      }
      invalidate();
      return;
      bool = false;
      break;
    }
  }
  
  public boolean fullScroll(int paramInt)
  {
    if (paramInt == 130) {}
    for (int i = 1;; i = 0)
    {
      int j = getHeight();
      this.mTempRect.top = 0;
      this.mTempRect.bottom = j;
      if (i != 0)
      {
        i = getChildCount();
        if (i > 0)
        {
          View localView = getChildAt(i - 1);
          this.mTempRect.bottom = localView.getBottom();
          this.mTempRect.top = (this.mTempRect.bottom - j);
        }
      }
      return scrollAndFocus(paramInt, this.mTempRect.top, this.mTempRect.bottom);
    }
  }
  
  protected float getBottomFadingEdgeStrength()
  {
    if (getChildCount() == 0) {
      return 0.0F;
    }
    int i = getVerticalFadingEdgeLength();
    int j = getHeight();
    int k = getPaddingBottom();
    j = getChildAt(0).getBottom() - getScrollY() - (j - k);
    if (j < i) {
      return j / i;
    }
    return 1.0F;
  }
  
  public int getMaxScrollAmount()
  {
    return (int)(0.5F * (getBottom() - getTop()));
  }
  
  protected float getTopFadingEdgeStrength()
  {
    if (getChildCount() == 0) {
      return 0.0F;
    }
    int i = getVerticalFadingEdgeLength();
    if (getScrollY() < i) {
      return getScrollY() / i;
    }
    return 1.0F;
  }
  
  public boolean inChild(int paramInt1, int paramInt2)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (getChildCount() > 0)
    {
      int i = getScrollY();
      View localView = getChildAt(0);
      bool1 = bool2;
      if (paramInt2 >= localView.getTop() - i)
      {
        bool1 = bool2;
        if (paramInt2 < localView.getBottom() - i)
        {
          bool1 = bool2;
          if (paramInt1 >= localView.getLeft())
          {
            bool1 = bool2;
            if (paramInt1 < localView.getRight()) {
              bool1 = true;
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public void initChildPointer()
  {
    this.child = getChildAt(0);
    this.child.setPadding(0, 1500, 0, 1500);
  }
  
  public boolean isFillViewport()
  {
    return this.mFillViewport;
  }
  
  public boolean isOverScrolled()
  {
    return (getScrollY() < this.child.getPaddingTop()) || (getScrollY() > this.child.getBottom() - this.child.getPaddingBottom() - getHeight());
  }
  
  public boolean isSmoothScrollingEnabled()
  {
    return this.mSmoothScrollingEnabled;
  }
  
  protected void measureChild(View paramView, int paramInt1, int paramInt2)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    paramView.measure(getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight(), localLayoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
  }
  
  protected void measureChildWithMargins(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    paramView.measure(getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight() + localMarginLayoutParams.leftMargin + localMarginLayoutParams.rightMargin + paramInt2, localMarginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(localMarginLayoutParams.topMargin + localMarginLayoutParams.bottomMargin, 0));
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = true;
    int i = paramMotionEvent.getAction();
    if ((i == 2) && (this.mIsBeingDragged)) {
      return true;
    }
    switch (i & 0xFF)
    {
    }
    for (;;)
    {
      return this.mIsBeingDragged;
      i = this.mActivePointerId;
      if (i != -1)
      {
        float f = paramMotionEvent.getY(paramMotionEvent.findPointerIndex(i));
        if ((int)Math.abs(f - this.mLastMotionY) > this.mTouchSlop)
        {
          this.mIsBeingDragged = true;
          this.mLastMotionY = f;
          continue;
          f = paramMotionEvent.getY();
          if (!inChild((int)paramMotionEvent.getX(), (int)f))
          {
            this.mIsBeingDragged = false;
          }
          else
          {
            this.mLastMotionY = f;
            this.mActivePointerId = paramMotionEvent.getPointerId(0);
            if (!this.mScroller.isFinished()) {}
            for (;;)
            {
              this.mIsBeingDragged = bool;
              break;
              bool = false;
            }
            this.mIsBeingDragged = false;
            this.mActivePointerId = -1;
            continue;
            onSecondaryPointerUp(paramMotionEvent);
          }
        }
      }
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mIsLayoutDirty = false;
    if ((this.mChildToScrollTo != null) && (isViewDescendantOf(this.mChildToScrollTo, this))) {
      scrollToChild(this.mChildToScrollTo);
    }
    this.mChildToScrollTo = null;
    scrollTo(getScrollX(), getScrollY());
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (!this.mFillViewport) {}
    View localView;
    do
    {
      do
      {
        return;
      } while ((View.MeasureSpec.getMode(paramInt2) == 0) || (getChildCount() <= 0));
      localView = getChildAt(0);
      paramInt2 = getMeasuredHeight();
    } while (localView.getMeasuredHeight() >= paramInt2);
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
    localView.measure(getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight(), localLayoutParams.width), View.MeasureSpec.makeMeasureSpec(paramInt2 - getPaddingTop() - getPaddingBottom(), 1073741824));
  }
  
  protected int onOverScroll(int paramInt)
  {
    return 0;
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect)
  {
    int i;
    View localView;
    if (paramInt == 2)
    {
      i = 130;
      if (paramRect != null) {
        break label44;
      }
      localView = FocusFinder.getInstance().findNextFocus(this, null, i);
      label24:
      if (localView != null) {
        break label58;
      }
    }
    label44:
    label58:
    while (isOffScreen(localView))
    {
      return false;
      i = paramInt;
      if (paramInt != 1) {
        break;
      }
      i = 33;
      break;
      localView = FocusFinder.getInstance().findNextFocusFromRect(this, paramRect, i);
      break label24;
    }
    return localView.requestFocus(i, paramRect);
  }
  
  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getHeight();
    int j = this.child.getPaddingTop();
    int k = this.child.getHeight() - this.child.getPaddingBottom();
    if ((this.isInFlingMode) && ((paramInt2 < j) || (paramInt2 > k - i)))
    {
      if (paramInt2 < j) {
        this.mScroller.startScroll(0, paramInt2, 0, j - paramInt2, 1000);
      }
      for (;;)
      {
        post(this.overScrollerSpringbackTask);
        this.isInFlingMode = false;
        return;
        if (paramInt2 > k - i) {
          this.mScroller.startScroll(0, paramInt2, 0, k - i - paramInt2, 1000);
        }
      }
    }
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    View localView = findFocus();
    if ((localView == null) || (this == localView)) {}
    while (!isWithinDeltaOfScreen(localView, 0, paramInt4)) {
      return;
    }
    localView.getDrawingRect(this.mTempRect);
    offsetDescendantRectToMyCoords(localView, this.mTempRect);
    doScrollY(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    this.mScroller.forceFinished(true);
    removeCallbacks(this.overScrollerSpringbackTask);
    if (paramMotionEvent.getAction() == 1) {
      return overScrollView();
    }
    if (paramMotionEvent.getAction() == 3) {
      return overScrollView();
    }
    return false;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (paramMotionEvent.getEdgeFlags() != 0)) {
      return false;
    }
    if (this.mVelocityTracker == null) {
      this.mVelocityTracker = VelocityTracker.obtain();
    }
    this.mVelocityTracker.addMovement(paramMotionEvent);
    switch (paramMotionEvent.getAction() & 0xFF)
    {
    }
    for (;;)
    {
      return true;
      float f = paramMotionEvent.getY();
      boolean bool = inChild((int)paramMotionEvent.getX(), (int)f);
      this.mIsBeingDragged = bool;
      if (!bool) {
        break;
      }
      if (!this.mScroller.isFinished()) {
        this.mScroller.abortAnimation();
      }
      this.mLastMotionY = f;
      this.mActivePointerId = paramMotionEvent.getPointerId(0);
      continue;
      if (this.mIsBeingDragged)
      {
        f = paramMotionEvent.getY(paramMotionEvent.findPointerIndex(this.mActivePointerId));
        int i = (int)(this.mLastMotionY - f);
        this.mLastMotionY = f;
        if (isOverScrolled())
        {
          scrollBy(0, i / 2);
        }
        else
        {
          scrollBy(0, i);
          continue;
          if (this.mIsBeingDragged)
          {
            paramMotionEvent = this.mVelocityTracker;
            paramMotionEvent.computeCurrentVelocity(1000, this.mMaximumVelocity);
            i = (int)paramMotionEvent.getYVelocity(this.mActivePointerId);
            if ((getChildCount() > 0) && (Math.abs(i) > this.mMinimumVelocity)) {
              fling(-i);
            }
            this.mActivePointerId = -1;
            this.mIsBeingDragged = false;
            if (this.mVelocityTracker != null)
            {
              this.mVelocityTracker.recycle();
              this.mVelocityTracker = null;
              continue;
              if ((this.mIsBeingDragged) && (getChildCount() > 0))
              {
                this.mActivePointerId = -1;
                this.mIsBeingDragged = false;
                if (this.mVelocityTracker != null)
                {
                  this.mVelocityTracker.recycle();
                  this.mVelocityTracker = null;
                  continue;
                  onSecondaryPointerUp(paramMotionEvent);
                }
              }
            }
          }
        }
      }
    }
  }
  
  public boolean pageScroll(int paramInt)
  {
    int i;
    int j;
    if (paramInt == 130)
    {
      i = 1;
      j = getHeight();
      if (i == 0) {
        break label121;
      }
      this.mTempRect.top = (getScrollY() + j);
      i = getChildCount();
      if (i > 0)
      {
        View localView = getChildAt(i - 1);
        if (this.mTempRect.top + j > localView.getBottom()) {
          this.mTempRect.top = (localView.getBottom() - j);
        }
      }
    }
    for (;;)
    {
      this.mTempRect.bottom = (this.mTempRect.top + j);
      return scrollAndFocus(paramInt, this.mTempRect.top, this.mTempRect.bottom);
      i = 0;
      break;
      label121:
      this.mTempRect.top = (getScrollY() - j);
      if (this.mTempRect.top < 0) {
        this.mTempRect.top = 0;
      }
    }
  }
  
  public void requestChildFocus(View paramView1, View paramView2)
  {
    if (!this.mScrollViewMovedFocus)
    {
      if (this.mIsLayoutDirty) {
        break label26;
      }
      scrollToChild(paramView2);
    }
    for (;;)
    {
      super.requestChildFocus(paramView1, paramView2);
      return;
      label26:
      this.mChildToScrollTo = paramView2;
    }
  }
  
  public boolean requestChildRectangleOnScreen(View paramView, Rect paramRect, boolean paramBoolean)
  {
    paramRect.offset(paramView.getLeft() - paramView.getScrollX(), paramView.getTop() - paramView.getScrollY());
    return scrollToChildRect(paramRect, paramBoolean);
  }
  
  public void requestLayout()
  {
    this.mIsLayoutDirty = true;
    super.requestLayout();
  }
  
  public void scrollTo(int paramInt1, int paramInt2)
  {
    if (getChildCount() > 0)
    {
      View localView = getChildAt(0);
      paramInt1 = clamp(paramInt1, getWidth() - getPaddingRight() - getPaddingLeft(), localView.getWidth());
      paramInt2 = clamp(paramInt2, getHeight() - getPaddingBottom() - getPaddingTop(), localView.getHeight());
      if ((paramInt1 != getScrollX()) || (paramInt2 != getScrollY())) {
        super.scrollTo(paramInt1, paramInt2);
      }
    }
  }
  
  public void setFillViewport(boolean paramBoolean)
  {
    if (paramBoolean != this.mFillViewport)
    {
      this.mFillViewport = paramBoolean;
      requestLayout();
    }
  }
  
  public void setSmoothScrollingEnabled(boolean paramBoolean)
  {
    this.mSmoothScrollingEnabled = paramBoolean;
  }
  
  public final void smoothScrollBy(int paramInt1, int paramInt2)
  {
    if (getChildCount() == 0) {
      return;
    }
    if (AnimationUtils.currentAnimationTimeMillis() - this.mLastScroll > 250L)
    {
      paramInt1 = getHeight();
      int i = getPaddingBottom();
      int j = getPaddingTop();
      i = Math.max(0, getChildAt(0).getHeight() - (paramInt1 - i - j));
      paramInt1 = getScrollY();
      paramInt2 = Math.max(0, Math.min(paramInt1 + paramInt2, i));
      this.mScroller.startScroll(getScrollX(), paramInt1, 0, paramInt2 - paramInt1);
      invalidate();
    }
    for (;;)
    {
      this.mLastScroll = AnimationUtils.currentAnimationTimeMillis();
      return;
      if (!this.mScroller.isFinished()) {
        this.mScroller.abortAnimation();
      }
      scrollBy(paramInt1, paramInt2);
    }
  }
  
  public final void smoothScrollTo(int paramInt1, int paramInt2)
  {
    smoothScrollBy(paramInt1 - getScrollX(), paramInt2 - getScrollY());
  }
  
  public final void smoothScrollToBottom()
  {
    smoothScrollTo(0, this.child.getHeight() - this.child.getPaddingTop() - getHeight());
  }
  
  public final void smoothScrollToTop()
  {
    smoothScrollTo(0, this.child.getPaddingTop());
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\OverScrollView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */