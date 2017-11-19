package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Checkable;
import cc.makeblock.makeblock.engine.utils.BitmapUtils;

public class DraggableSwitch
  extends View
  implements Checkable
{
  private static final String backgroundOffColor = "#d6d6d6";
  private static final String backgroundOnColor = "#92defd";
  private static final String backgroundSignOffColor = "#afafaf";
  private static final String backgroundSignOnColor = "#24a3d1";
  private Drawable backgroundDrawable;
  private Bitmap indicatorBitmap;
  private Rect indicatorRect;
  private int mAnimDuration;
  private boolean mChecked = false;
  private RectF mDrawRect;
  private float mFlingVelocity;
  private Interpolator mInterpolator;
  private boolean mIsRtl = false;
  private int mMaxAnimDuration = -1;
  private float mMemoX;
  private OnCheckedChangeListener mOnCheckedChangeListener;
  private boolean mRunning = false;
  private float mStartPosition;
  private long mStartTime;
  private float mStartX;
  private float mThumbPosition;
  private int mThumbRadius = -1;
  private final Runnable mUpdater = new Runnable()
  {
    public void run()
    {
      DraggableSwitch.this.update();
    }
  };
  private int measuredHeight;
  private int measuredWidth;
  private Drawable offSignDrawable;
  private Drawable onSignInDrawable;
  private Drawable onSignOutDrawable;
  
  public DraggableSwitch(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public DraggableSwitch(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public DraggableSwitch(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
  private int getMiddleColor(int paramInt1, int paramInt2, float paramFloat)
  {
    if (paramInt1 == paramInt2) {}
    do
    {
      return paramInt2;
      if (paramFloat == 0.0F) {
        return paramInt1;
      }
    } while (paramFloat == 1.0F);
    return Color.argb(getMiddleValue(Color.alpha(paramInt1), Color.alpha(paramInt2), paramFloat), getMiddleValue(Color.red(paramInt1), Color.red(paramInt2), paramFloat), getMiddleValue(Color.green(paramInt1), Color.green(paramInt2), paramFloat), getMiddleValue(Color.blue(paramInt1), Color.blue(paramInt2), paramFloat));
  }
  
  private int getMiddleValue(int paramInt1, int paramInt2, float paramFloat)
  {
    return Math.round(paramInt1 + (paramInt2 - paramInt1) * paramFloat);
  }
  
  private void init(Context paramContext)
  {
    new BitmapFactory.Options().inJustDecodeBounds = true;
    this.indicatorBitmap = BitmapFactory.decodeResource(getResources(), 2131166014);
    this.backgroundDrawable = BitmapUtils.getDrawable(getContext(), 2131165945);
    this.onSignOutDrawable = BitmapUtils.getDrawable(getContext(), 2131165948);
    this.onSignInDrawable = BitmapUtils.getDrawable(getContext(), 2131165947);
    this.offSignDrawable = BitmapUtils.getDrawable(getContext(), 2131165946);
    this.indicatorRect = new Rect();
    this.mDrawRect = new RectF();
    this.mFlingVelocity = ViewConfiguration.get(paramContext).getScaledMinimumFlingVelocity();
    this.mInterpolator = new DecelerateInterpolator();
    this.mMaxAnimDuration = 200;
  }
  
  private void resetAnimation()
  {
    this.mStartTime = SystemClock.uptimeMillis();
    this.mStartPosition = this.mThumbPosition;
    float f2 = this.mMaxAnimDuration;
    if (this.mChecked) {}
    for (float f1 = 1.0F - this.mStartPosition;; f1 = this.mStartPosition)
    {
      this.mAnimDuration = ((int)(f1 * f2));
      return;
    }
  }
  
  private void startAnimation()
  {
    if (getHandler() != null)
    {
      resetAnimation();
      this.mRunning = true;
      getHandler().postAtTime(this.mUpdater, SystemClock.uptimeMillis() + 16L);
      invalidate();
      return;
    }
    if (this.mChecked) {}
    for (float f = 1.0F;; f = 0.0F)
    {
      this.mThumbPosition = f;
      break;
    }
  }
  
  private void stopAnimation()
  {
    this.mRunning = false;
    if (this.mChecked) {}
    for (float f = 1.0F;; f = 0.0F)
    {
      this.mThumbPosition = f;
      if (getHandler() != null) {
        getHandler().removeCallbacks(this.mUpdater);
      }
      invalidate();
      return;
    }
  }
  
  private void update()
  {
    float f2 = Math.min(1.0F, (float)(SystemClock.uptimeMillis() - this.mStartTime) / this.mAnimDuration);
    float f1 = this.mInterpolator.getInterpolation(f2);
    if (this.mChecked)
    {
      f1 = this.mStartPosition * (1.0F - f1) + f1;
      this.mThumbPosition = f1;
      if (f2 == 1.0F) {
        stopAnimation();
      }
      if (this.mRunning)
      {
        if (getHandler() == null) {
          break label114;
        }
        getHandler().postAtTime(this.mUpdater, SystemClock.uptimeMillis() + 16L);
      }
    }
    for (;;)
    {
      invalidate();
      return;
      f1 = this.mStartPosition * (1.0F - f1);
      break;
      label114:
      stopAnimation();
    }
  }
  
  public boolean isChecked()
  {
    return this.mChecked;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    this.mThumbRadius = (this.measuredWidth / 6);
    this.mDrawRect.set(this.measuredWidth / 12, 0.0F, this.measuredWidth - this.measuredWidth / 12, this.measuredHeight);
    int j = (int)((this.mDrawRect.width() - this.mThumbRadius * 2) * this.mThumbPosition + this.mDrawRect.left + this.mThumbRadius);
    int i = j;
    if (this.mIsRtl) {
      i = (int)(2.0F * this.mDrawRect.centerX() - j);
    }
    j = (int)this.mDrawRect.centerY();
    this.backgroundDrawable.setBounds(getWidth() / 10, getHeight() / 4, getWidth() / 10 * 9, getHeight() / 4 * 3);
    this.backgroundDrawable.setColorFilter(getMiddleColor(Color.parseColor("#d6d6d6"), Color.parseColor("#92defd"), this.mThumbPosition), PorterDuff.Mode.SRC);
    this.backgroundDrawable.draw(paramCanvas);
    this.onSignOutDrawable.setBounds(this.measuredWidth / 5, this.measuredHeight / 2 - this.measuredWidth / 6 / 2, this.measuredWidth / 5 + this.measuredWidth / 6, this.measuredHeight / 2 + this.measuredWidth / 6 / 2);
    this.onSignOutDrawable.setColorFilter(getMiddleColor(Color.parseColor("#afafaf"), Color.parseColor("#24a3d1"), this.mThumbPosition), PorterDuff.Mode.SRC);
    this.onSignOutDrawable.draw(paramCanvas);
    this.onSignInDrawable.setBounds(this.measuredWidth / 5 + 6, this.measuredHeight / 2 - this.measuredWidth / 6 / 2 + 6, this.measuredWidth / 5 + this.measuredWidth / 6 - 6, this.measuredHeight / 2 + this.measuredWidth / 6 / 2 - 6);
    this.onSignInDrawable.setColorFilter(getMiddleColor(Color.parseColor("#d6d6d6"), Color.parseColor("#92defd"), this.mThumbPosition), PorterDuff.Mode.SRC);
    this.onSignInDrawable.draw(paramCanvas);
    this.offSignDrawable.setBounds(this.measuredWidth / 10 * 7, this.measuredHeight / 2 - this.measuredWidth / 6 / 2, this.measuredWidth / 10 * 7 + this.measuredWidth / 15, this.measuredHeight / 2 + this.measuredWidth / 6 / 2);
    this.offSignDrawable.setColorFilter(getMiddleColor(Color.parseColor("#afafaf"), Color.parseColor("#24a3d1"), this.mThumbPosition), PorterDuff.Mode.SRC);
    this.offSignDrawable.draw(paramCanvas);
    this.indicatorRect.set(i - this.mThumbRadius * 2, j - this.mThumbRadius * 2, this.mThumbRadius * 2 + i, this.mThumbRadius * 2 + j);
    paramCanvas.drawBitmap(this.indicatorBitmap, null, this.indicatorRect, null);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    this.measuredHeight = View.MeasureSpec.getSize(paramInt2);
    this.measuredWidth = View.MeasureSpec.getSize(paramInt1);
    setMeasuredDimension(this.measuredWidth, this.measuredHeight);
  }
  
  public boolean onTouchEvent(@NonNull MotionEvent paramMotionEvent)
  {
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool1 = false;
    super.onTouchEvent(paramMotionEvent);
    float f2 = paramMotionEvent.getX();
    float f1 = f2;
    if (this.mIsRtl) {
      f1 = 2.0F * this.mDrawRect.centerX() - f2;
    }
    switch (paramMotionEvent.getAction())
    {
    default: 
      return true;
    case 0: 
      if (getParent() != null) {
        getParent().requestDisallowInterceptTouchEvent(true);
      }
      this.mMemoX = f1;
      this.mStartX = this.mMemoX;
      this.mStartTime = SystemClock.uptimeMillis();
      return true;
    case 2: 
      f2 = (f1 - this.mMemoX) / (this.mDrawRect.width() - this.mThumbRadius);
      this.mThumbPosition = Math.min(1.0F, Math.max(0.0F, this.mThumbPosition + f2));
      this.mMemoX = f1;
      invalidate();
      return true;
    case 1: 
      if (getParent() != null) {
        getParent().requestDisallowInterceptTouchEvent(false);
      }
      f1 = (f1 - this.mStartX) / (float)(SystemClock.uptimeMillis() - this.mStartTime) * 1000.0F;
      long l1 = SystemClock.uptimeMillis();
      long l2 = this.mStartTime;
      if (Math.abs(f1) >= this.mFlingVelocity)
      {
        if (f1 > 0.0F) {
          bool1 = true;
        }
        setChecked(bool1);
        return true;
      }
      if ((l1 - l2 < 500L) && (((!this.mChecked) && (this.mThumbPosition < 0.1F)) || ((this.mChecked) && (this.mThumbPosition > 0.9F))))
      {
        toggle();
        return true;
      }
      bool1 = bool2;
      if (this.mThumbPosition > 0.5F) {
        bool1 = true;
      }
      setChecked(bool1);
      return true;
    }
    if (getParent() != null) {
      getParent().requestDisallowInterceptTouchEvent(false);
    }
    bool1 = bool3;
    if (this.mThumbPosition > 0.5F) {
      bool1 = true;
    }
    setChecked(bool1);
    return true;
  }
  
  public void setChecked(boolean paramBoolean)
  {
    if (this.mChecked != paramBoolean)
    {
      this.mChecked = paramBoolean;
      if (this.mOnCheckedChangeListener != null) {
        this.mOnCheckedChangeListener.onCheckedChanged(this, this.mChecked);
      }
    }
    if (this.mChecked) {}
    for (float f = 1.0F;; f = 0.0F)
    {
      if (this.mThumbPosition != f) {
        startAnimation();
      }
      return;
    }
  }
  
  public void setCheckedImmediately(boolean paramBoolean)
  {
    if (this.mChecked != paramBoolean)
    {
      this.mChecked = paramBoolean;
      if (this.mOnCheckedChangeListener != null) {
        this.mOnCheckedChangeListener.onCheckedChanged(this, this.mChecked);
      }
    }
    if (this.mChecked) {}
    for (float f = 1.0F;; f = 0.0F)
    {
      this.mThumbPosition = f;
      invalidate();
      return;
    }
  }
  
  public void setOnCheckedChangeListener(OnCheckedChangeListener paramOnCheckedChangeListener)
  {
    this.mOnCheckedChangeListener = paramOnCheckedChangeListener;
  }
  
  public void toggle()
  {
    if (isEnabled()) {
      if (this.mChecked) {
        break label22;
      }
    }
    label22:
    for (boolean bool = true;; bool = false)
    {
      setChecked(bool);
      return;
    }
  }
  
  public static abstract interface OnCheckedChangeListener
  {
    public abstract void onCheckedChanged(DraggableSwitch paramDraggableSwitch, boolean paramBoolean);
  }
  
  public static class SwitchState
  {
    boolean isChecked;
    
    SwitchState(boolean paramBoolean)
    {
      this.isChecked = paramBoolean;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\DraggableSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */