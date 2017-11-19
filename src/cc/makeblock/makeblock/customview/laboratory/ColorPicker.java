package cc.makeblock.makeblock.customview.laboratory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class ColorPicker
  extends LaboratoryView<ColorPickerState>
  implements View.OnTouchListener
{
  private static final int COLOR_VALUE_MAX = 150;
  private static final float SIZE_DESIGN_HOOP = 340.0F;
  private static final float SIZE_DESIGN_OUTER_RING = 30.0F;
  private static final float SIZE_DESIGN_PICKER_BACKGROUND = 100.0F;
  private static final float SIZE_DESIGN_THICKNESS_HOOP = 40.0F;
  private static final float SIZE_DESIGN_WIDGET = 400.0F;
  public static final float WIDGET_SIZE_PERCENT = 0.8888889F;
  private ImageView colorHoop;
  private Paint colorPaint;
  private int innerRadius;
  private OnColorPickListener onColorPickListener;
  private int outerRadius;
  private ImageView picker;
  private int pickerDiameter;
  private int pixelColor;
  private ImageView textOff;
  private ImageView textOn;
  
  public ColorPicker(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public ColorPicker(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    paramContext = LayoutInflater.from(paramContext).inflate(2131427457, this);
    this.colorHoop = ((ImageView)paramContext.findViewById(2131296406));
    this.picker = ((ImageView)paramContext.findViewById(2131296409));
    this.textOn = ((ImageView)paramContext.findViewById(2131296408));
    this.textOff = ((ImageView)paramContext.findViewById(2131296407));
    post(new Runnable()
    {
      public void run()
      {
        ColorPicker.access$002(ColorPicker.this, (int)(0.85F * ColorPicker.this.getHeight() / 2.0F));
        ColorPicker.access$102(ColorPicker.this, (int)(ColorPicker.this.outerRadius - 0.1F * ColorPicker.this.getWidth()));
        ColorPicker.access$202(ColorPicker.this, (int)(ColorPicker.this.getWidth() * 100.0F / 400.0F));
      }
    });
    this.colorPaint = new Paint();
    this.colorPaint.setStyle(Paint.Style.FILL);
    this.colorHoop.setOnTouchListener(this);
    setPickerState(false);
  }
  
  private void setPickerState(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.textOn.setVisibility(0);
      this.textOff.setVisibility(4);
      return;
    }
    this.textOff.setVisibility(0);
    this.textOn.setVisibility(4);
  }
  
  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    Bitmap localBitmap = ((BitmapDrawable)this.colorHoop.getDrawable()).getBitmap();
    int i = (int)(this.picker.getX() + this.picker.getWidth() / 2);
    int j = (int)(this.picker.getY() + this.picker.getHeight() / 2);
    int k = (int)((i - getWidth() * 30.0F / 400.0F) * localBitmap.getWidth() / this.colorHoop.getWidth());
    int m = (int)((j - getWidth() * 30.0F / 400.0F) * localBitmap.getHeight() / this.colorHoop.getHeight());
    if ((k < localBitmap.getWidth()) && (m < localBitmap.getHeight()) && (k >= 0) && (m >= 0)) {
      this.pixelColor = localBitmap.getPixel(k, m);
    }
    this.colorPaint.setColor(this.pixelColor);
    k = (int)(getWidth() * 40.0F / 400.0F);
    paramCanvas.drawCircle(i, j, k, this.colorPaint);
  }
  
  public float getSizePercent()
  {
    return 0.8888889F;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.outerRadius = ((int)(0.85F * getHeight() / 2.0F));
    this.innerRadius = ((int)(this.outerRadius - 0.1F * getWidth()));
    this.pickerDiameter = ((int)(getWidth() * 100.0F / 400.0F));
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    double d2 = Math.sqrt(Math.pow(this.outerRadius - f1, 2.0D) + Math.pow(this.outerRadius - f2, 2.0D));
    double d1 = getWidth() * 30.0F / 400.0F;
    double d3;
    double d4;
    double d5;
    double d6;
    double d7;
    if ((f1 < this.outerRadius) && (f2 < this.outerRadius))
    {
      d3 = this.outerRadius - this.outerRadius * (this.outerRadius - f2) / d2;
      d4 = this.outerRadius;
      d5 = Math.sqrt(Math.pow(this.outerRadius, 2.0D) - Math.pow(this.outerRadius - d3, 2.0D));
      d6 = (this.outerRadius - this.innerRadius) / 2 * (this.outerRadius - d3) / this.outerRadius;
      d7 = Math.sqrt(Math.pow((this.outerRadius - this.innerRadius) / 2, 2.0D) - Math.pow(d6, 2.0D));
      this.picker.setX((float)(d4 - d5 + d7 - this.pickerDiameter / 2 + d1));
      this.picker.setY((float)(d3 + d6 - this.pickerDiameter / 2 + d1));
    }
    if ((f1 > this.outerRadius) && (f2 < this.outerRadius))
    {
      d3 = this.outerRadius - this.outerRadius * (this.outerRadius - f2) / d2;
      d4 = this.outerRadius;
      d5 = Math.sqrt(Math.pow(this.outerRadius, 2.0D) - Math.pow(this.outerRadius - d3, 2.0D));
      d6 = (this.outerRadius - this.innerRadius) / 2 * (this.outerRadius - d3) / this.outerRadius;
      d7 = Math.sqrt(Math.pow((this.outerRadius - this.innerRadius) / 2, 2.0D) - Math.pow(d6, 2.0D));
      this.picker.setX((float)(d4 + d5 - d7 - this.pickerDiameter / 2 + d1));
      this.picker.setY((float)(d3 + d6 - this.pickerDiameter / 2 + d1));
    }
    if ((f1 < this.outerRadius) && (f2 > this.outerRadius))
    {
      d3 = this.outerRadius + (f2 - this.outerRadius) * this.outerRadius / d2;
      d4 = this.outerRadius;
      d5 = Math.sqrt(Math.pow(this.outerRadius, 2.0D) - Math.pow(this.outerRadius - d3, 2.0D));
      d6 = (this.outerRadius - this.innerRadius) / 2 * (this.outerRadius - d3) / this.outerRadius;
      d7 = (int)Math.sqrt(Math.pow((this.outerRadius - this.innerRadius) / 2, 2.0D) - Math.pow(d6, 2.0D));
      this.picker.setX((float)(d4 - d5 + d7 - this.pickerDiameter / 2 + d1));
      this.picker.setY((float)(d3 + d6 - this.pickerDiameter / 2 + d1));
    }
    if ((f1 > this.outerRadius) && (f2 > this.outerRadius))
    {
      d2 = this.outerRadius - this.outerRadius * (this.outerRadius - f2) / d2;
      d3 = this.outerRadius;
      d4 = Math.sqrt(Math.pow(this.outerRadius, 2.0D) - Math.pow(this.outerRadius - d2, 2.0D));
      d5 = (this.outerRadius - this.innerRadius) / 2 * (this.outerRadius - d2) / this.outerRadius;
      d6 = (int)Math.sqrt(Math.pow((this.outerRadius - this.innerRadius) / 2, 2.0D) - Math.pow(d5, 2.0D));
      this.picker.setX((float)(d3 + d4 - d6 - this.pickerDiameter / 2 + d1));
      this.picker.setY((float)(d2 + d5 - this.pickerDiameter / 2 + d1));
    }
    if (this.onColorPickListener != null)
    {
      if ((Color.red(this.pixelColor) != Color.green(this.pixelColor)) || (Color.green(this.pixelColor) != Color.blue(this.pixelColor))) {
        break label915;
      }
      pushState(new ColorPickerState(-16777216));
      setPickerState(false);
    }
    for (;;)
    {
      invalidate();
      return true;
      label915:
      pushState(new ColorPickerState(Color.rgb(Color.red(this.pixelColor), Color.green(this.pixelColor), Color.blue(this.pixelColor))));
      setPickerState(true);
    }
  }
  
  protected void reset()
  {
    setPickerState(false);
    this.picker.setX(getWidth() / 2 - this.picker.getWidth() / 2 - 2);
    this.picker.setY(0.0F);
  }
  
  protected void sendState(ColorPickerState paramColorPickerState)
  {
    if (this.onColorPickListener != null) {
      this.onColorPickListener.onColorPick(paramColorPickerState.color);
    }
  }
  
  public void setOnColorPickListener(OnColorPickListener paramOnColorPickListener)
  {
    this.onColorPickListener = paramOnColorPickListener;
  }
  
  public class ColorPickerState
  {
    int color;
    
    public ColorPickerState(int paramInt)
    {
      this.color = paramInt;
    }
  }
  
  public static abstract interface OnColorPickListener
  {
    public abstract void onColorPick(int paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\laboratory\ColorPicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */