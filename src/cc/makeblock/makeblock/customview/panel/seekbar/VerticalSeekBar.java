package cc.makeblock.makeblock.customview.panel.seekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.support.percent.PercentRelativeLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import cc.makeblock.makeblock.R.styleable;

public class VerticalSeekBar
  extends PercentRelativeLayout
{
  private float height;
  private boolean isFirst = true;
  private int max;
  private int min;
  private OnSeekBarValueChangeListener onValueChangeListener;
  private float thumb_bottom_y;
  private int thumb_shadow_view_height;
  private float thumb_top_y;
  private ImageView thumb_ver_img;
  private ImageView thumb_ver_line;
  private ImageView thumb_ver_line_over;
  private ImageView thumb_ver_shadow_img;
  private int value;
  private float width;
  
  public VerticalSeekBar(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0);
  }
  
  public VerticalSeekBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0);
  }
  
  public VerticalSeekBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.Slider, paramInt, 0);
    this.min = paramContext.getInt(1, 0);
    this.max = paramContext.getInt(0, 100);
    this.value = paramContext.getInt(2, 0);
    paramContext.recycle();
  }
  
  private void setThumb(float paramFloat)
  {
    float f1 = paramFloat - this.thumb_ver_img.getHeight() / 2;
    paramFloat = f1;
    if (f1 < this.thumb_top_y) {
      paramFloat = this.thumb_top_y;
    }
    f1 = paramFloat;
    if (paramFloat > this.thumb_bottom_y) {
      f1 = this.thumb_bottom_y;
    }
    this.thumb_ver_img.setY(f1);
    ViewGroup.LayoutParams localLayoutParams = this.thumb_ver_line_over.getLayoutParams();
    localLayoutParams.height = ((int)(this.thumb_bottom_y - f1));
    this.thumb_ver_line_over.setLayoutParams(localLayoutParams);
    paramFloat = this.thumb_ver_img.getY();
    float f2 = (this.thumb_shadow_view_height + 0.0F - this.thumb_ver_img.getHeight()) / 2.0F;
    this.thumb_ver_shadow_img.setY(paramFloat - f2);
    updateValue(f1);
  }
  
  private void updateValue(float paramFloat)
  {
    this.value = ((int)(this.min + (this.thumb_bottom_y - paramFloat) / (this.thumb_bottom_y - this.thumb_top_y) * (this.max - this.min)));
    if (this.value < this.min) {
      this.value = this.min;
    }
    for (;;)
    {
      if (this.onValueChangeListener != null) {
        this.onValueChangeListener.onSeekBarValueChange(this.value);
      }
      return;
      if (this.value > this.max) {
        this.value = this.max;
      }
    }
  }
  
  public float getValue()
  {
    return this.value;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    return true;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.isFirst)
    {
      this.thumb_ver_img = ((ImageView)findViewById(2131296719));
      this.thumb_ver_shadow_img = ((ImageView)findViewById(2131296722));
      this.thumb_ver_line = ((ImageView)findViewById(2131296720));
      this.thumb_ver_line_over = ((ImageView)findViewById(2131296721));
      Object localObject = this.thumb_ver_shadow_img.getDrawable();
      float f1 = 0.0F;
      float f2 = 0.0F;
      if ((localObject instanceof BitmapDrawable)) {
        f1 = ((BitmapDrawable)localObject).getBitmap().getHeight();
      }
      localObject = this.thumb_ver_img.getDrawable();
      if ((localObject instanceof BitmapDrawable)) {
        f2 = ((BitmapDrawable)localObject).getBitmap().getHeight();
      }
      localObject = this.thumb_ver_shadow_img.getLayoutParams();
      this.thumb_shadow_view_height = ((int)(f1 / f2 * this.thumb_ver_img.getHeight()));
      ((ViewGroup.LayoutParams)localObject).height = this.thumb_shadow_view_height;
      this.thumb_ver_shadow_img.setLayoutParams((ViewGroup.LayoutParams)localObject);
      this.width = (paramInt3 - paramInt1);
      this.height = (paramInt4 - paramInt2);
      this.isFirst = false;
      this.thumb_top_y = (this.thumb_ver_line.getY() - 3.0F);
      this.thumb_bottom_y = (this.thumb_ver_line.getBottom() - this.thumb_ver_img.getWidth() + 3);
      setValue(this.value);
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if ((paramInt1 != 0) && (paramInt2 != 0) && ((this.width != paramInt1) || (this.height != paramInt2)))
    {
      this.width = paramInt1;
      this.height = paramInt2;
      this.isFirst = true;
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    default: 
      return super.onTouchEvent(paramMotionEvent);
    case 0: 
      this.thumb_ver_shadow_img.setVisibility(4);
      setThumb(paramMotionEvent.getY());
      return true;
    case 2: 
      setThumb(paramMotionEvent.getY());
      return true;
    }
    setThumb(paramMotionEvent.getY());
    this.thumb_ver_shadow_img.setVisibility(0);
    return true;
  }
  
  public void setMax(int paramInt)
  {
    this.max = paramInt;
  }
  
  public void setMin(int paramInt)
  {
    this.min = paramInt;
  }
  
  public void setOnValueChangeListener(OnSeekBarValueChangeListener paramOnSeekBarValueChangeListener)
  {
    this.onValueChangeListener = paramOnSeekBarValueChangeListener;
  }
  
  public void setValue(float paramFloat)
  {
    setThumb(this.thumb_bottom_y - (paramFloat - this.min) * (this.thumb_bottom_y - this.thumb_top_y) / (this.max - this.min) + this.thumb_ver_img.getWidth() / 2);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\seekbar\VerticalSeekBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */