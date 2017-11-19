package com.pnikosis.materialishprogress;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.provider.Settings.Global;
import android.provider.Settings.System;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;

public class ProgressWheel
  extends View
{
  private static final String TAG = ProgressWheel.class.getSimpleName();
  private int barColor = -1442840576;
  private float barExtraLength = 0.0F;
  private boolean barGrowingFromFront = true;
  private final int barLength = 16;
  private final int barMaxLength = 270;
  private Paint barPaint = new Paint();
  private double barSpinCycleTime = 460.0D;
  private int barWidth = 4;
  private ProgressCallback callback;
  private RectF circleBounds = new RectF();
  private int circleRadius = 28;
  private boolean fillRadius = false;
  private boolean isSpinning = false;
  private long lastTimeAnimated = 0L;
  private boolean linearProgress;
  private float mProgress = 0.0F;
  private float mTargetProgress = 0.0F;
  private final long pauseGrowingTime = 200L;
  private long pausedTimeWithoutGrowing = 0L;
  private int rimColor = 16777215;
  private Paint rimPaint = new Paint();
  private int rimWidth = 4;
  private boolean shouldAnimate;
  private float spinSpeed = 230.0F;
  private double timeStartGrowing = 0.0D;
  
  public ProgressWheel(Context paramContext)
  {
    super(paramContext);
    setAnimationEnabled();
  }
  
  public ProgressWheel(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    parseAttributes(paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ProgressWheel));
    setAnimationEnabled();
  }
  
  private void parseAttributes(TypedArray paramTypedArray)
  {
    DisplayMetrics localDisplayMetrics = getContext().getResources().getDisplayMetrics();
    this.barWidth = ((int)TypedValue.applyDimension(1, this.barWidth, localDisplayMetrics));
    this.rimWidth = ((int)TypedValue.applyDimension(1, this.rimWidth, localDisplayMetrics));
    this.circleRadius = ((int)TypedValue.applyDimension(1, this.circleRadius, localDisplayMetrics));
    this.circleRadius = ((int)paramTypedArray.getDimension(R.styleable.ProgressWheel_matProg_circleRadius, this.circleRadius));
    this.fillRadius = paramTypedArray.getBoolean(R.styleable.ProgressWheel_matProg_fillRadius, false);
    this.barWidth = ((int)paramTypedArray.getDimension(R.styleable.ProgressWheel_matProg_barWidth, this.barWidth));
    this.rimWidth = ((int)paramTypedArray.getDimension(R.styleable.ProgressWheel_matProg_rimWidth, this.rimWidth));
    this.spinSpeed = (paramTypedArray.getFloat(R.styleable.ProgressWheel_matProg_spinSpeed, this.spinSpeed / 360.0F) * 360.0F);
    this.barSpinCycleTime = paramTypedArray.getInt(R.styleable.ProgressWheel_matProg_barSpinCycleTime, (int)this.barSpinCycleTime);
    this.barColor = paramTypedArray.getColor(R.styleable.ProgressWheel_matProg_barColor, this.barColor);
    this.rimColor = paramTypedArray.getColor(R.styleable.ProgressWheel_matProg_rimColor, this.rimColor);
    this.linearProgress = paramTypedArray.getBoolean(R.styleable.ProgressWheel_matProg_linearProgress, false);
    if (paramTypedArray.getBoolean(R.styleable.ProgressWheel_matProg_progressIndeterminate, false)) {
      spin();
    }
    paramTypedArray.recycle();
  }
  
  private void runCallback()
  {
    if (this.callback != null)
    {
      float f = Math.round(this.mProgress * 100.0F / 360.0F) / 100.0F;
      this.callback.onProgressUpdate(f);
    }
  }
  
  private void runCallback(float paramFloat)
  {
    if (this.callback != null) {
      this.callback.onProgressUpdate(paramFloat);
    }
  }
  
  @TargetApi(17)
  private void setAnimationEnabled()
  {
    float f;
    if (Build.VERSION.SDK_INT >= 17)
    {
      f = Settings.Global.getFloat(getContext().getContentResolver(), "animator_duration_scale", 1.0F);
      if (f == 0.0F) {
        break label53;
      }
    }
    label53:
    for (boolean bool = true;; bool = false)
    {
      this.shouldAnimate = bool;
      return;
      f = Settings.System.getFloat(getContext().getContentResolver(), "animator_duration_scale", 1.0F);
      break;
    }
  }
  
  private void setupBounds(int paramInt1, int paramInt2)
  {
    int j = getPaddingTop();
    int k = getPaddingBottom();
    int m = getPaddingLeft();
    int n = getPaddingRight();
    if (!this.fillRadius)
    {
      int i = Math.min(Math.min(paramInt1 - m - n, paramInt2 - k - j), this.circleRadius * 2 - this.barWidth * 2);
      paramInt1 = (paramInt1 - m - n - i) / 2 + m;
      paramInt2 = (paramInt2 - j - k - i) / 2 + j;
      this.circleBounds = new RectF(this.barWidth + paramInt1, this.barWidth + paramInt2, paramInt1 + i - this.barWidth, paramInt2 + i - this.barWidth);
      return;
    }
    this.circleBounds = new RectF(this.barWidth + m, this.barWidth + j, paramInt1 - n - this.barWidth, paramInt2 - k - this.barWidth);
  }
  
  private void setupPaints()
  {
    this.barPaint.setColor(this.barColor);
    this.barPaint.setAntiAlias(true);
    this.barPaint.setStyle(Paint.Style.STROKE);
    this.barPaint.setStrokeWidth(this.barWidth);
    this.rimPaint.setColor(this.rimColor);
    this.rimPaint.setAntiAlias(true);
    this.rimPaint.setStyle(Paint.Style.STROKE);
    this.rimPaint.setStrokeWidth(this.rimWidth);
  }
  
  private void updateBarLength(long paramLong)
  {
    if (this.pausedTimeWithoutGrowing >= 200L)
    {
      this.timeStartGrowing += paramLong;
      if (this.timeStartGrowing > this.barSpinCycleTime)
      {
        this.timeStartGrowing -= this.barSpinCycleTime;
        this.pausedTimeWithoutGrowing = 0L;
        if (this.barGrowingFromFront) {
          break label111;
        }
      }
      label111:
      for (boolean bool = true;; bool = false)
      {
        this.barGrowingFromFront = bool;
        f = (float)Math.cos((this.timeStartGrowing / this.barSpinCycleTime + 1.0D) * 3.141592653589793D) / 2.0F + 0.5F;
        if (!this.barGrowingFromFront) {
          break;
        }
        this.barExtraLength = (f * 254.0F);
        return;
      }
      float f = 254.0F * (1.0F - f);
      this.mProgress += this.barExtraLength - f;
      this.barExtraLength = f;
      return;
    }
    this.pausedTimeWithoutGrowing += paramLong;
  }
  
  public int getBarColor()
  {
    return this.barColor;
  }
  
  public int getBarWidth()
  {
    return this.barWidth;
  }
  
  public int getCircleRadius()
  {
    return this.circleRadius;
  }
  
  public float getProgress()
  {
    if (this.isSpinning) {
      return -1.0F;
    }
    return this.mProgress / 360.0F;
  }
  
  public int getRimColor()
  {
    return this.rimColor;
  }
  
  public int getRimWidth()
  {
    return this.rimWidth;
  }
  
  public float getSpinSpeed()
  {
    return this.spinSpeed / 360.0F;
  }
  
  public boolean isSpinning()
  {
    return this.isSpinning;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    paramCanvas.drawArc(this.circleBounds, 360.0F, 360.0F, false, this.rimPaint);
    int i = 0;
    if (!this.shouldAnimate) {}
    for (;;)
    {
      return;
      float f1;
      float f2;
      if (this.isSpinning)
      {
        i = 1;
        long l = SystemClock.uptimeMillis() - this.lastTimeAnimated;
        f1 = (float)l * this.spinSpeed / 1000.0F;
        updateBarLength(l);
        this.mProgress += f1;
        if (this.mProgress > 360.0F)
        {
          this.mProgress -= 360.0F;
          runCallback(-1.0F);
        }
        this.lastTimeAnimated = SystemClock.uptimeMillis();
        f1 = this.mProgress - 90.0F;
        f2 = 16.0F + this.barExtraLength;
        if (isInEditMode())
        {
          f1 = 0.0F;
          f2 = 135.0F;
        }
        paramCanvas.drawArc(this.circleBounds, f1, f2, false, this.barPaint);
      }
      while (i != 0)
      {
        invalidate();
        return;
        f1 = this.mProgress;
        if (this.mProgress != this.mTargetProgress)
        {
          i = 1;
          f2 = (float)(SystemClock.uptimeMillis() - this.lastTimeAnimated) / 1000.0F;
          float f3 = this.spinSpeed;
          this.mProgress = Math.min(this.mProgress + f2 * f3, this.mTargetProgress);
          this.lastTimeAnimated = SystemClock.uptimeMillis();
        }
        if (f1 != this.mProgress) {
          runCallback();
        }
        f2 = 0.0F;
        f1 = this.mProgress;
        if (!this.linearProgress)
        {
          f2 = (float)(1.0D - Math.pow(1.0F - this.mProgress / 360.0F, 2.0F * 2.0F)) * 360.0F;
          f1 = (float)(1.0D - Math.pow(1.0F - this.mProgress / 360.0F, 2.0F)) * 360.0F;
        }
        if (isInEditMode()) {
          f1 = 360.0F;
        }
        paramCanvas.drawArc(this.circleBounds, f2 - 90.0F, f1, false, this.barPaint);
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int j = this.circleRadius + getPaddingLeft() + getPaddingRight();
    int i = this.circleRadius + getPaddingTop() + getPaddingBottom();
    int k = View.MeasureSpec.getMode(paramInt1);
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    int m = View.MeasureSpec.getMode(paramInt2);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    if (k == 1073741824) {
      if ((m != 1073741824) && (k != 1073741824)) {
        break label114;
      }
    }
    for (;;)
    {
      setMeasuredDimension(paramInt1, paramInt2);
      return;
      if (k == Integer.MIN_VALUE)
      {
        paramInt1 = Math.min(j, paramInt1);
        break;
      }
      paramInt1 = j;
      break;
      label114:
      if (m == Integer.MIN_VALUE) {
        paramInt2 = Math.min(i, paramInt2);
      } else {
        paramInt2 = i;
      }
    }
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof WheelSavedState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (WheelSavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    this.mProgress = paramParcelable.mProgress;
    this.mTargetProgress = paramParcelable.mTargetProgress;
    this.isSpinning = paramParcelable.isSpinning;
    this.spinSpeed = paramParcelable.spinSpeed;
    this.barWidth = paramParcelable.barWidth;
    this.barColor = paramParcelable.barColor;
    this.rimWidth = paramParcelable.rimWidth;
    this.rimColor = paramParcelable.rimColor;
    this.circleRadius = paramParcelable.circleRadius;
    this.linearProgress = paramParcelable.linearProgress;
    this.fillRadius = paramParcelable.fillRadius;
    this.lastTimeAnimated = SystemClock.uptimeMillis();
  }
  
  public Parcelable onSaveInstanceState()
  {
    WheelSavedState localWheelSavedState = new WheelSavedState(super.onSaveInstanceState());
    localWheelSavedState.mProgress = this.mProgress;
    localWheelSavedState.mTargetProgress = this.mTargetProgress;
    localWheelSavedState.isSpinning = this.isSpinning;
    localWheelSavedState.spinSpeed = this.spinSpeed;
    localWheelSavedState.barWidth = this.barWidth;
    localWheelSavedState.barColor = this.barColor;
    localWheelSavedState.rimWidth = this.rimWidth;
    localWheelSavedState.rimColor = this.rimColor;
    localWheelSavedState.circleRadius = this.circleRadius;
    localWheelSavedState.linearProgress = this.linearProgress;
    localWheelSavedState.fillRadius = this.fillRadius;
    return localWheelSavedState;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    setupBounds(paramInt1, paramInt2);
    setupPaints();
    invalidate();
  }
  
  protected void onVisibilityChanged(View paramView, int paramInt)
  {
    super.onVisibilityChanged(paramView, paramInt);
    if (paramInt == 0) {
      this.lastTimeAnimated = SystemClock.uptimeMillis();
    }
  }
  
  public void resetCount()
  {
    this.mProgress = 0.0F;
    this.mTargetProgress = 0.0F;
    invalidate();
  }
  
  public void setBarColor(int paramInt)
  {
    this.barColor = paramInt;
    setupPaints();
    if (!this.isSpinning) {
      invalidate();
    }
  }
  
  public void setBarWidth(int paramInt)
  {
    this.barWidth = paramInt;
    if (!this.isSpinning) {
      invalidate();
    }
  }
  
  public void setCallback(ProgressCallback paramProgressCallback)
  {
    this.callback = paramProgressCallback;
    if (!this.isSpinning) {
      runCallback();
    }
  }
  
  public void setCircleRadius(int paramInt)
  {
    this.circleRadius = paramInt;
    if (!this.isSpinning) {
      invalidate();
    }
  }
  
  public void setInstantProgress(float paramFloat)
  {
    if (this.isSpinning)
    {
      this.mProgress = 0.0F;
      this.isSpinning = false;
    }
    float f;
    if (paramFloat > 1.0F) {
      f = paramFloat - 1.0F;
    }
    while (f == this.mTargetProgress)
    {
      return;
      f = paramFloat;
      if (paramFloat < 0.0F) {
        f = 0.0F;
      }
    }
    this.mTargetProgress = Math.min(f * 360.0F, 360.0F);
    this.mProgress = this.mTargetProgress;
    this.lastTimeAnimated = SystemClock.uptimeMillis();
    invalidate();
  }
  
  public void setLinearProgress(boolean paramBoolean)
  {
    this.linearProgress = paramBoolean;
    if (!this.isSpinning) {
      invalidate();
    }
  }
  
  public void setProgress(float paramFloat)
  {
    if (this.isSpinning)
    {
      this.mProgress = 0.0F;
      this.isSpinning = false;
      runCallback();
    }
    float f;
    if (paramFloat > 1.0F) {
      f = paramFloat - 1.0F;
    }
    while (f == this.mTargetProgress)
    {
      return;
      f = paramFloat;
      if (paramFloat < 0.0F) {
        f = 0.0F;
      }
    }
    if (this.mProgress == this.mTargetProgress) {
      this.lastTimeAnimated = SystemClock.uptimeMillis();
    }
    this.mTargetProgress = Math.min(f * 360.0F, 360.0F);
    invalidate();
  }
  
  public void setRimColor(int paramInt)
  {
    this.rimColor = paramInt;
    setupPaints();
    if (!this.isSpinning) {
      invalidate();
    }
  }
  
  public void setRimWidth(int paramInt)
  {
    this.rimWidth = paramInt;
    if (!this.isSpinning) {
      invalidate();
    }
  }
  
  public void setSpinSpeed(float paramFloat)
  {
    this.spinSpeed = (360.0F * paramFloat);
  }
  
  public void spin()
  {
    this.lastTimeAnimated = SystemClock.uptimeMillis();
    this.isSpinning = true;
    invalidate();
  }
  
  public void stopSpinning()
  {
    this.isSpinning = false;
    this.mProgress = 0.0F;
    this.mTargetProgress = 0.0F;
    invalidate();
  }
  
  public static abstract interface ProgressCallback
  {
    public abstract void onProgressUpdate(float paramFloat);
  }
  
  static class WheelSavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<WheelSavedState> CREATOR = new Parcelable.Creator()
    {
      public ProgressWheel.WheelSavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new ProgressWheel.WheelSavedState(paramAnonymousParcel, null);
      }
      
      public ProgressWheel.WheelSavedState[] newArray(int paramAnonymousInt)
      {
        return new ProgressWheel.WheelSavedState[paramAnonymousInt];
      }
    };
    int barColor;
    int barWidth;
    int circleRadius;
    boolean fillRadius;
    boolean isSpinning;
    boolean linearProgress;
    float mProgress;
    float mTargetProgress;
    int rimColor;
    int rimWidth;
    float spinSpeed;
    
    private WheelSavedState(Parcel paramParcel)
    {
      super();
      this.mProgress = paramParcel.readFloat();
      this.mTargetProgress = paramParcel.readFloat();
      if (paramParcel.readByte() != 0)
      {
        bool1 = true;
        this.isSpinning = bool1;
        this.spinSpeed = paramParcel.readFloat();
        this.barWidth = paramParcel.readInt();
        this.barColor = paramParcel.readInt();
        this.rimWidth = paramParcel.readInt();
        this.rimColor = paramParcel.readInt();
        this.circleRadius = paramParcel.readInt();
        if (paramParcel.readByte() == 0) {
          break label119;
        }
        bool1 = true;
        label94:
        this.linearProgress = bool1;
        if (paramParcel.readByte() == 0) {
          break label124;
        }
      }
      label119:
      label124:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        this.fillRadius = bool1;
        return;
        bool1 = false;
        break;
        bool1 = false;
        break label94;
      }
    }
    
    WheelSavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      int i = 1;
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeFloat(this.mProgress);
      paramParcel.writeFloat(this.mTargetProgress);
      if (this.isSpinning)
      {
        paramInt = 1;
        paramParcel.writeByte((byte)paramInt);
        paramParcel.writeFloat(this.spinSpeed);
        paramParcel.writeInt(this.barWidth);
        paramParcel.writeInt(this.barColor);
        paramParcel.writeInt(this.rimWidth);
        paramParcel.writeInt(this.rimColor);
        paramParcel.writeInt(this.circleRadius);
        if (!this.linearProgress) {
          break label123;
        }
        paramInt = 1;
        label96:
        paramParcel.writeByte((byte)paramInt);
        if (!this.fillRadius) {
          break label128;
        }
      }
      label123:
      label128:
      for (paramInt = i;; paramInt = 0)
      {
        paramParcel.writeByte((byte)paramInt);
        return;
        paramInt = 0;
        break;
        paramInt = 0;
        break label96;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\pnikosis\materialishprogress\ProgressWheel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */