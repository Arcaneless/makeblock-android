package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import cc.makeblock.makeblock.R.styleable;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import com.viewpagerindicator.PageIndicator;

public class CycleLinePageIndicator
  extends View
  implements PageIndicator
{
  private static final int INVALID_POINTER = -1;
  private boolean mCentered;
  private int mCurrentPage;
  private float mGapWidth;
  private float mLineWidth;
  private ViewPager.OnPageChangeListener mListener;
  private final Paint mPaintSelected = new Paint(1);
  private final Paint mPaintUnselected = new Paint(1);
  private ViewPager mViewPager;
  private int releaSize;
  
  public CycleLinePageIndicator(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CycleLinePageIndicator(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 2130903440);
  }
  
  public CycleLinePageIndicator(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    if (isInEditMode()) {
      return;
    }
    Resources localResources = getResources();
    int i = localResources.getColor(2131034272);
    int j = localResources.getColor(2131034176);
    boolean bool = localResources.getBoolean(2130968583);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.LinePageIndicator, paramInt, 0);
    this.mCentered = paramContext.getBoolean(1, bool);
    float f = ScreenHelper.SCREEN_HEIGHT;
    this.mLineWidth = (ScreenHelper.SCREEN_WIDTH * 0.02F);
    this.mGapWidth = (ScreenHelper.SCREEN_WIDTH * 0.006F);
    setStrokeWidth(f * 0.004F);
    this.mPaintUnselected.setColor(paramContext.getColor(6, j));
    this.mPaintSelected.setColor(paramContext.getColor(4, i));
    this.mPaintUnselected.setStrokeCap(Paint.Cap.ROUND);
    this.mPaintSelected.setStrokeCap(Paint.Cap.ROUND);
    paramAttributeSet = paramContext.getDrawable(0);
    if (paramAttributeSet != null) {
      setBackgroundDrawable(paramAttributeSet);
    }
    paramContext.recycle();
  }
  
  private int measureHeight(int paramInt)
  {
    int i = View.MeasureSpec.getMode(paramInt);
    paramInt = View.MeasureSpec.getSize(paramInt);
    float f1;
    if (i == 1073741824) {
      f1 = paramInt;
    }
    for (;;)
    {
      return (int)Math.ceil(f1);
      float f2 = this.mPaintSelected.getStrokeWidth() + getPaddingTop() + getPaddingBottom();
      f1 = f2;
      if (i == Integer.MIN_VALUE) {
        f1 = Math.min(f2, paramInt);
      }
    }
  }
  
  private int measureWidth(int paramInt)
  {
    int i = View.MeasureSpec.getMode(paramInt);
    paramInt = View.MeasureSpec.getSize(paramInt);
    float f1;
    if ((i == 1073741824) || (this.mViewPager == null)) {
      f1 = paramInt;
    }
    for (;;)
    {
      return (int)Math.ceil(f1);
      int j = this.releaSize;
      float f2 = getPaddingLeft() + getPaddingRight() + j * this.mLineWidth + (j - 1) * this.mGapWidth;
      f1 = f2;
      if (i == Integer.MIN_VALUE) {
        f1 = Math.min(f2, paramInt);
      }
    }
  }
  
  public float getGapWidth()
  {
    return this.mGapWidth;
  }
  
  public float getLineWidth()
  {
    return this.mLineWidth;
  }
  
  public int getSelectedColor()
  {
    return this.mPaintSelected.getColor();
  }
  
  public float getStrokeWidth()
  {
    return this.mPaintSelected.getStrokeWidth();
  }
  
  public int getUnselectedColor()
  {
    return this.mPaintUnselected.getColor();
  }
  
  public boolean isCentered()
  {
    return this.mCentered;
  }
  
  public void notifyDataSetChanged()
  {
    invalidate();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.mViewPager == null) {}
    int j;
    do
    {
      return;
      j = this.releaSize;
    } while (j == 0);
    if (this.mCurrentPage >= j)
    {
      setCurrentItem(j - 1);
      return;
    }
    float f4 = this.mLineWidth + this.mGapWidth;
    float f6 = j;
    float f7 = this.mGapWidth;
    float f1 = getPaddingTop();
    float f3 = getPaddingLeft();
    float f8 = getPaddingRight();
    float f5 = f1 + (getHeight() - f1 - getPaddingBottom()) / 2.0F;
    float f2 = f3;
    f1 = f2;
    if (this.mCentered) {
      f1 = f2 + ((getWidth() - f3 - f8) / 2.0F - (f6 * f4 - f7) / 2.0F);
    }
    int i = 0;
    label145:
    if (i < j)
    {
      f2 = f1 + i * f4;
      f3 = this.mLineWidth;
      if (i != this.mCurrentPage) {
        break label206;
      }
    }
    label206:
    for (Paint localPaint = this.mPaintSelected;; localPaint = this.mPaintUnselected)
    {
      paramCanvas.drawLine(f2, f5, f2 + f3, f5, localPaint);
      i += 1;
      break label145;
      break;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(measureWidth(paramInt1), measureHeight(paramInt2));
  }
  
  public void onPageScrollStateChanged(int paramInt)
  {
    if (this.mListener != null) {
      this.mListener.onPageScrollStateChanged(paramInt);
    }
  }
  
  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    if (this.mListener != null) {
      this.mListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }
  }
  
  public void onPageSelected(int paramInt)
  {
    this.mCurrentPage = (paramInt % this.releaSize);
    invalidate();
    if (this.mListener != null) {
      this.mListener.onPageSelected(paramInt);
    }
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    this.mCurrentPage = paramParcelable.currentPage;
    requestLayout();
  }
  
  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.currentPage = this.mCurrentPage;
    return localSavedState;
  }
  
  public void setCentered(boolean paramBoolean)
  {
    this.mCentered = paramBoolean;
    invalidate();
  }
  
  public void setCurrentItem(int paramInt)
  {
    if (this.mViewPager == null) {
      throw new IllegalStateException("ViewPager has not been bound.");
    }
    this.mCurrentPage = paramInt;
    invalidate();
  }
  
  public void setGapWidth(float paramFloat)
  {
    this.mGapWidth = paramFloat;
    invalidate();
  }
  
  public void setLineWidth(float paramFloat)
  {
    this.mLineWidth = paramFloat;
    invalidate();
  }
  
  public void setOnPageChangeListener(ViewPager.OnPageChangeListener paramOnPageChangeListener)
  {
    this.mListener = paramOnPageChangeListener;
  }
  
  public void setReleaSize(int paramInt)
  {
    this.releaSize = paramInt;
  }
  
  public void setSelectedColor(int paramInt)
  {
    this.mPaintSelected.setColor(paramInt);
    invalidate();
  }
  
  public void setStrokeWidth(float paramFloat)
  {
    this.mPaintSelected.setStrokeWidth(paramFloat);
    this.mPaintUnselected.setStrokeWidth(paramFloat);
    invalidate();
  }
  
  public void setUnselectedColor(int paramInt)
  {
    this.mPaintUnselected.setColor(paramInt);
    invalidate();
  }
  
  public void setViewPager(ViewPager paramViewPager)
  {
    if (this.mViewPager == paramViewPager) {
      return;
    }
    if (this.mViewPager != null) {
      this.mViewPager.setOnPageChangeListener(null);
    }
    if (paramViewPager.getAdapter() == null) {
      throw new IllegalStateException("ViewPager does not have adapter instance.");
    }
    this.mViewPager = paramViewPager;
    this.mViewPager.setOnPageChangeListener(this);
    invalidate();
  }
  
  public void setViewPager(ViewPager paramViewPager, int paramInt)
  {
    setViewPager(paramViewPager);
    setCurrentItem(paramInt);
  }
  
  static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public CycleLinePageIndicator.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new CycleLinePageIndicator.SavedState(paramAnonymousParcel, null);
      }
      
      public CycleLinePageIndicator.SavedState[] newArray(int paramAnonymousInt)
      {
        return new CycleLinePageIndicator.SavedState[paramAnonymousInt];
      }
    };
    int currentPage;
    
    private SavedState(Parcel paramParcel)
    {
      super();
      this.currentPage = paramParcel.readInt();
    }
    
    public SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.currentPage);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\CycleLinePageIndicator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */