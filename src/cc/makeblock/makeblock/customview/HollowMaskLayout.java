package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Xfermode;
import android.support.percent.PercentRelativeLayout;
import android.util.AttributeSet;
import android.view.View;
import cc.makeblock.makeblock.R.styleable;

public class HollowMaskLayout
  extends PercentRelativeLayout
{
  public static final int TYPE_CIRCLE = 1;
  public static final int TYPE_NONE = -1;
  public static final int TYPE_RECTANGLE = 2;
  public static final int TYPE_ROUND_RECTANGLE = 3;
  private int mBackgroundColor;
  private Rect mHollowRect = new Rect();
  private View mHollowView;
  private Paint mPaint;
  private Path mPath;
  private Region mRegion;
  private int mType = -1;
  private Xfermode xfermode;
  
  public HollowMaskLayout(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0, 0);
  }
  
  public HollowMaskLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0, 0);
  }
  
  public HollowMaskLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt, 0);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.HollowMaskLayout, paramInt1, paramInt2);
    this.mBackgroundColor = paramContext.getColor(0, 0);
    paramContext.recycle();
    this.mPaint = new Paint();
    this.mPaint.setColor(-1);
    this.mPaint.setAntiAlias(true);
    this.xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
    setLayerType(1, null);
    setWillNotDraw(false);
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    this.mPaint.setXfermode(this.xfermode);
    this.mPaint.setColor(this.mBackgroundColor);
    paramCanvas.drawRect(paramCanvas.getClipBounds(), this.mPaint);
    this.mPaint.setColor(0);
    if (this.mPath != null) {
      paramCanvas.drawPath(this.mPath, this.mPaint);
    }
    this.mPaint.setXfermode(null);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    float f1;
    float f2;
    float f3;
    if (this.mHollowView != null)
    {
      this.mHollowView.getGlobalVisibleRect(this.mHollowRect);
      if (this.mPath != null) {
        break label199;
      }
      this.mPath = new Path();
      f1 = (this.mHollowRect.right + this.mHollowRect.left) / 2.0F;
      f2 = (this.mHollowRect.bottom + this.mHollowRect.top) / 2.0F;
      f3 = this.mHollowRect.bottom - this.mHollowRect.top;
      float f4 = this.mHollowRect.right - this.mHollowRect.left;
      switch (this.mType)
      {
      }
    }
    for (;;)
    {
      this.mRegion = new Region();
      this.mRegion.setPath(this.mPath, new Region(this.mHollowRect));
      return;
      label199:
      this.mPath.reset();
      break;
      this.mPath.addCircle(f1, f2, f3, Path.Direction.CW);
      continue;
      this.mPath.addRect(new RectF(this.mHollowRect), Path.Direction.CW);
      continue;
      this.mPath.addArc(new RectF(this.mHollowRect.left, this.mHollowRect.top, this.mHollowRect.left + f3, this.mHollowRect.bottom), 90.0F, 180.0F);
      this.mPath.addArc(new RectF(this.mHollowRect.right - f3, this.mHollowRect.top, this.mHollowRect.right, this.mHollowRect.bottom), -90.0F, 180.0F);
      this.mPath.addRect(this.mHollowRect.left + f3 / 2.0F, this.mHollowRect.top, this.mHollowRect.right - f3 / 2.0F, this.mHollowRect.bottom, Path.Direction.CW);
      this.mPath.close();
    }
  }
  
  public void setHollowView(View paramView, int paramInt)
  {
    this.mHollowView = paramView;
    this.mType = paramInt;
    this.mPath = null;
    requestLayout();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\HollowMaskLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */