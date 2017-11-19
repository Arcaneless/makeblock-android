package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import cc.makeblock.makeblock.R.styleable;

public class CircleProgressView
  extends View
{
  private static final String TAG = "CircleProgressView";
  private int circleColor;
  private int circleProgressColor;
  private String circleWidthPercent;
  private float circleWidthPercentFloat;
  private int finishAngle;
  private int maxValue;
  private boolean needText = true;
  private Paint paint = new Paint();
  private float progress;
  private int startAngle;
  private int textColor;
  
  public CircleProgressView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CircleProgressView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public CircleProgressView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CircleProgressView);
    this.circleColor = paramContext.getColor(0, -7829368);
    this.circleProgressColor = paramContext.getColor(1, -16711936);
    this.textColor = paramContext.getColor(8, -16777216);
    this.circleWidthPercent = paramContext.getString(2);
    this.needText = paramContext.getBoolean(5, true);
    if (TextUtils.isEmpty(this.circleWidthPercent)) {
      this.circleWidthPercent = "6%w";
    }
    this.maxValue = paramContext.getInteger(4, 100);
    this.progress = paramContext.getFloat(6, 0.3F);
    this.startAngle = paramContext.getInteger(7, 135);
    this.finishAngle = paramContext.getInteger(3, 360);
    paramContext.recycle();
  }
  
  public static void changePaintTextSizeToSuitbale(Paint paramPaint, String paramString, int paramInt1, int paramInt2)
  {
    int i = (int)paramPaint.getTextSize();
    Object localObject = paramPaint.getFontMetricsInt();
    int j = ((Paint.FontMetricsInt)localObject).bottom;
    int k = ((Paint.FontMetricsInt)localObject).top;
    localObject = new Rect();
    paramPaint.getTextBounds(paramString, 0, paramString.length(), (Rect)localObject);
    if ((((Rect)localObject).right - ((Rect)localObject).left > paramInt1) || (j - k > paramInt2))
    {
      paramPaint.setTextSize(i - 1);
      return;
    }
    paramPaint.setTextSize(i + 1);
    changePaintTextSizeToSuitbale(paramPaint, paramString, paramInt1, paramInt2);
  }
  
  public int getMaxValue()
  {
    return this.maxValue;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    float f2 = getWidth();
    float f3 = getHeight();
    float f4 = Math.min(getWidth(), getHeight());
    float f1 = 0.0F;
    if ((this.circleWidthPercent.endsWith("%w")) || (this.circleWidthPercent.endsWith("%W"))) {
      this.circleWidthPercentFloat = (Float.parseFloat(this.circleWidthPercent.substring(0, this.circleWidthPercent.length() - 2)) / 100.0F);
    }
    for (f1 = f2 * this.circleWidthPercentFloat;; f1 = f3 * this.circleWidthPercentFloat)
    {
      do
      {
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(f1);
        this.paint.setStyle(Paint.Style.STROKE);
        Object localObject = new RectF();
        ((RectF)localObject).left = ((f2 - f4) / 2.0F + f1 / 2.0F);
        ((RectF)localObject).top = ((f3 - f4) / 2.0F + f1 / 2.0F);
        ((RectF)localObject).right = (((RectF)localObject).left + f4 - f1);
        ((RectF)localObject).bottom = (((RectF)localObject).top + f4 - f1);
        this.paint.setColor(this.circleColor);
        paramCanvas.drawArc((RectF)localObject, this.startAngle, this.finishAngle - this.startAngle, false, this.paint);
        float f5 = (this.finishAngle - this.startAngle) * this.progress + this.startAngle;
        this.paint.setColor(this.circleProgressColor);
        paramCanvas.drawArc((RectF)localObject, this.startAngle, f5 - this.startAngle, false, this.paint);
        f4 = (f4 - f1) / 2.0F;
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(this.circleColor);
        paramCanvas.drawCircle((float)(f4 * Math.cos(this.finishAngle * 3.141592653589793D / 180.0D) + f2 / 2.0F), (float)(f4 * Math.sin(this.finishAngle * 3.141592653589793D / 180.0D) + f3 / 2.0F), f1 / 2.0F, this.paint);
        float f6 = (float)(f4 * Math.cos(this.startAngle * 3.141592653589793D / 180.0D) + f2 / 2.0F);
        float f7 = (float)(f4 * Math.sin(this.startAngle * 3.141592653589793D / 180.0D) + f3 / 2.0F);
        paramCanvas.drawCircle(f6, f7, f1 / 2.0F, this.paint);
        if (f5 > this.startAngle)
        {
          this.paint.setColor(this.circleProgressColor);
          paramCanvas.drawCircle(f6, f7, f1 / 2.0F, this.paint);
          paramCanvas.drawCircle((float)(f4 * Math.cos(f5 * 3.141592653589793D / 180.0D) + f2 / 2.0F), (float)(f4 * Math.sin(f5 * 3.141592653589793D / 180.0D) + f3 / 2.0F), f1 / 2.0F, this.paint);
        }
        if (this.needText)
        {
          localObject = (int)Math.rint(this.maxValue * this.progress) + "";
          int i = (int)(getWidth() * 0.45F);
          int j = (int)(getWidth() * 0.25F);
          changePaintTextSizeToSuitbale(this.paint, (String)localObject, i, j);
          Paint.FontMetricsInt localFontMetricsInt = this.paint.getFontMetricsInt();
          i = localFontMetricsInt.bottom;
          j = localFontMetricsInt.top;
          Rect localRect = new Rect();
          this.paint.getTextBounds((String)localObject, 0, ((String)localObject).length(), localRect);
          int k = localRect.right;
          int m = localRect.left;
          k = (getWidth() - (k - m)) / 2;
          i = (getHeight() - (i - j)) / 2;
          j = localFontMetricsInt.top;
          this.paint.setColor(this.textColor);
          paramCanvas.drawText((String)localObject, k, i - j, this.paint);
        }
        return;
      } while ((!this.circleWidthPercent.endsWith("%h")) && (!this.circleWidthPercent.endsWith("%H")));
      this.circleWidthPercentFloat = (Float.parseFloat(this.circleWidthPercent.substring(0, this.circleWidthPercent.length() - 2)) / 100.0F);
    }
  }
  
  public void setMaxValue(int paramInt)
  {
    this.maxValue = paramInt;
    postInvalidate();
  }
  
  public void setNeedText(boolean paramBoolean)
  {
    this.needText = paramBoolean;
  }
  
  public void setProgress(float paramFloat)
  {
    float f = paramFloat;
    if (paramFloat < 0.0F) {
      f = 0.0F;
    }
    paramFloat = f;
    if (f > 1.0F) {
      paramFloat = 1.0F;
    }
    this.progress = paramFloat;
    postInvalidate();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\CircleProgressView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */