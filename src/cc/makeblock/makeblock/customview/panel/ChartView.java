package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;

public class ChartView
  extends ImageView
{
  private static final String TAG = "ChartView";
  public static final int TIME_COUNT = 30;
  private Bitmap bitmap;
  int[] dataArray = new int[30];
  private Path mPath;
  private int maxValue = 0;
  private int maxXStart;
  private int maxXStop;
  private int maxYStart;
  private int maxYStop;
  private int midValue = 0;
  private int midXStart;
  private int midXStop;
  private int midYStart;
  private int midYStop;
  private int minValue = 0;
  private int minXStart;
  private int minXStop;
  private int minYStart;
  private int minYStop;
  private Paint paint;
  private Paint paint2;
  private int textValueWidth;
  float unitX;
  
  public ChartView(Context paramContext)
  {
    super(paramContext);
    init(null, 0);
  }
  
  public ChartView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet, 0);
  }
  
  public ChartView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet, paramInt);
  }
  
  private void init(AttributeSet paramAttributeSet, int paramInt)
  {
    setLayerType(1, null);
    this.paint2 = new Paint();
    this.paint2.setColor(Color.rgb(75, 86, 97));
    this.paint2.setStrokeWidth(ScreenHelper.getPercentHeightToPx(0.0025F));
    this.paint = new Paint(1);
  }
  
  public void addValue(int paramInt)
  {
    int i = 0;
    while (i < 29)
    {
      this.dataArray[i] = this.dataArray[(i + 1)];
      if (this.dataArray[i] >= this.maxValue)
      {
        this.maxValue = this.dataArray[i];
        this.midValue = ((this.maxValue + this.minValue) / 2);
      }
      if (this.dataArray[i] <= this.minValue)
      {
        this.minValue = this.dataArray[i];
        this.midValue = ((this.maxValue + this.minValue) / 2);
      }
      i += 1;
    }
    this.dataArray[29] = paramInt;
    this.maxValue = this.dataArray[0];
    this.minValue = this.dataArray[0];
    paramInt = 1;
    while (paramInt < 30)
    {
      if (this.dataArray[paramInt] > this.maxValue)
      {
        this.maxValue = this.dataArray[paramInt];
        this.midValue = ((this.maxValue + this.minValue) / 2);
      }
      if (this.dataArray[paramInt] < this.minValue)
      {
        this.minValue = this.dataArray[paramInt];
        this.midValue = ((this.maxValue + this.minValue) / 2);
      }
      paramInt += 1;
    }
    invalidate();
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    this.paint.setStyle(Paint.Style.FILL);
    int k = getWidth();
    int m = getHeight();
    this.paint.setColor(Color.rgb(66, 76, 87));
    paramCanvas.drawRoundRect(new RectF(0.0F, 0.0F, k, m), 3.0F, 3.0F, this.paint);
    this.maxXStart = 0;
    this.maxYStart = (m / 4);
    this.maxXStop = k;
    this.maxYStop = (m / 4);
    this.midXStart = 0;
    this.midYStart = (m / 4 * 2);
    this.midXStop = k;
    this.midYStop = (m / 4 * 2);
    this.minXStart = 0;
    this.minYStart = (m / 4 * 3);
    this.minXStop = k;
    this.minYStop = (m / 4 * 3);
    paramCanvas.drawLine(this.maxXStart, this.maxYStart, this.maxXStop, this.maxYStop, this.paint2);
    if (((this.maxValue != this.midValue) && (this.minValue != this.maxValue) && (this.midValue != this.minValue)) || ((this.maxValue == this.minValue) && (this.minValue == this.midValue))) {
      paramCanvas.drawLine(this.midXStart, this.midYStart, this.midXStop, this.midYStop, this.paint2);
    }
    paramCanvas.drawLine(this.minXStart, this.minYStart, this.minXStop, this.minYStop, this.paint2);
    if (this.bitmap != null)
    {
      i = (int)(getHeight() / 1.5F);
      j = (k - i) / 2;
      m = (m - i) / 2;
      paramCanvas.drawBitmap(Bitmap.createScaledBitmap(this.bitmap, i, i, true), j, m, this.paint);
    }
    String str4 = this.maxValue + "";
    String str5 = this.minValue + "";
    String str3 = this.midValue + "";
    String str1;
    String str2;
    if (this.maxValue == this.minValue)
    {
      str1 = this.minValue + 1 + "";
      str2 = this.minValue - 1 + "";
    }
    float f2;
    for (;;)
    {
      this.paint.setTextSize(20.0F);
      this.paint.setColor(Color.rgb(255, 255, 255));
      f2 = k * 0.02F;
      paramCanvas.drawText(str1, f2, this.maxYStop - f2 / 2.0F, this.paint);
      paramCanvas.drawText(str3, f2, this.midYStop - f2 / 2.0F, this.paint);
      paramCanvas.drawText(str2, f2, this.minYStop - f2 / 2.0F, this.paint);
      this.paint.setStyle(Paint.Style.STROKE);
      this.paint.setColor(Color.parseColor("#fffceb4f"));
      this.paint.setStrokeWidth(3.0F);
      if (this.maxValue != this.minValue) {
        break;
      }
      if (this.minValue > 0) {
        paramCanvas.drawLine(f2, this.midYStart, this.midXStop, this.midYStop, this.paint);
      }
      return;
      if (this.maxValue != this.midValue)
      {
        str1 = str4;
        str2 = str5;
        if (this.midValue != this.minValue) {}
      }
      else
      {
        str3 = "";
        str1 = str4;
        str2 = str5;
      }
    }
    this.unitX = ((k - 2.0F * f2) / 30.0F);
    float f1 = k - f2;
    int i = (this.dataArray[29] - this.minValue) * (this.minYStart - this.maxYStart) / (this.maxValue - this.minValue);
    int j = this.minYStart - i;
    this.paint.setPathEffect(new CornerPathEffect(50.0F));
    this.mPath = new Path();
    this.mPath.moveTo(f1, j);
    i = 29;
    while (i >= 0)
    {
      float f3 = k;
      float f4 = this.unitX;
      float f5 = 30 - i;
      m = (this.dataArray[i] - this.minValue) * (this.minYStart - this.maxYStart) / (this.maxValue - this.minValue);
      int n = this.minYStart;
      this.mPath.lineTo(f1, j);
      f1 = f3 - f2 - f4 * f5;
      j = n - m;
      i -= 1;
    }
    this.paint.setStrokeWidth(ScreenHelper.getPercentHeightToPx(0.003F));
    paramCanvas.drawPath(this.mPath, this.paint);
  }
  
  public void setIcon(Bitmap paramBitmap)
  {
    this.bitmap = paramBitmap;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\ChartView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */