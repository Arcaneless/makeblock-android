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

public class DuplexSeekBar
  extends PercentRelativeLayout
{
  private float height;
  private boolean isFirst = true;
  private int max;
  private int min;
  private OnSeekBarValueChangeListener onValueChangeListener;
  private ImageView thumb_hor_img;
  private ImageView thumb_hor_line;
  private ImageView thumb_hor_line_over_left;
  private ImageView thumb_hor_line_over_right;
  private ImageView thumb_hor_shadow_img;
  private float thumb_left_x;
  private float thumb_right_x;
  private int thumb_shadow_view_width;
  private int value;
  private float width;
  
  public DuplexSeekBar(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0);
  }
  
  public DuplexSeekBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0);
  }
  
  public DuplexSeekBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.Slider, paramInt, 0);
    this.min = paramContext.getInt(1, 65281);
    this.max = paramContext.getInt(0, 255);
    this.value = paramContext.getInt(2, 0);
    paramContext.recycle();
  }
  
  private void setThumb(float paramFloat)
  {
    float f1 = paramFloat - this.thumb_hor_img.getWidth() / 2;
    paramFloat = f1;
    if (f1 < this.thumb_left_x) {
      paramFloat = this.thumb_left_x;
    }
    f1 = paramFloat;
    if (paramFloat > this.thumb_right_x) {
      f1 = this.thumb_right_x;
    }
    this.thumb_hor_img.setX(f1);
    ViewGroup.LayoutParams localLayoutParams = this.thumb_hor_line_over_left.getLayoutParams();
    int j = (int)((this.thumb_right_x + this.thumb_left_x) / 2.0F - f1);
    int i = j;
    if (j < 0) {
      i = 0;
    }
    localLayoutParams.width = i;
    this.thumb_hor_line_over_left.setLayoutParams(localLayoutParams);
    localLayoutParams = this.thumb_hor_line_over_right.getLayoutParams();
    j = (int)(f1 - (this.thumb_right_x + this.thumb_left_x) / 2.0F);
    i = j;
    if (j < 0) {
      i = 0;
    }
    localLayoutParams.width = i;
    this.thumb_hor_line_over_right.setLayoutParams(localLayoutParams);
    paramFloat = this.thumb_hor_img.getX();
    float f2 = (this.thumb_shadow_view_width + 0.0F - this.thumb_hor_img.getWidth()) / 2.0F;
    this.thumb_hor_shadow_img.setX(paramFloat - f2);
    updateValue(f1);
  }
  
  private void updateValue(float paramFloat)
  {
    this.value = ((int)(this.min + (paramFloat - this.thumb_left_x) / (this.thumb_right_x - this.thumb_left_x) * (this.max - this.min)));
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
      this.thumb_hor_img = ((ImageView)findViewById(2131296713));
      this.thumb_hor_shadow_img = ((ImageView)findViewById(2131296718));
      this.thumb_hor_line = ((ImageView)findViewById(2131296714));
      this.thumb_hor_line_over_left = ((ImageView)findViewById(2131296716));
      this.thumb_hor_line_over_right = ((ImageView)findViewById(2131296717));
      Object localObject = this.thumb_hor_shadow_img.getDrawable();
      float f1 = 0.0F;
      float f2 = 0.0F;
      if ((localObject instanceof BitmapDrawable)) {
        f1 = ((BitmapDrawable)localObject).getBitmap().getWidth();
      }
      localObject = this.thumb_hor_img.getDrawable();
      if ((localObject instanceof BitmapDrawable)) {
        f2 = ((BitmapDrawable)localObject).getBitmap().getWidth();
      }
      localObject = this.thumb_hor_shadow_img.getLayoutParams();
      this.thumb_shadow_view_width = ((int)(f1 / f2 * this.thumb_hor_img.getWidth()));
      ((ViewGroup.LayoutParams)localObject).width = this.thumb_shadow_view_width;
      this.thumb_hor_shadow_img.setLayoutParams((ViewGroup.LayoutParams)localObject);
      this.width = (paramInt3 - paramInt1);
      this.height = (paramInt4 - paramInt2);
      this.isFirst = false;
      this.thumb_left_x = (this.thumb_hor_line.getX() - 3.0F);
      this.thumb_right_x = (this.thumb_hor_line.getRight() - this.thumb_hor_img.getWidth() + 3);
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
      this.thumb_hor_shadow_img.setVisibility(4);
      setThumb(paramMotionEvent.getX());
      return true;
    case 2: 
      setThumb(paramMotionEvent.getX());
      return true;
    }
    setThumb(paramMotionEvent.getX());
    this.thumb_hor_shadow_img.setVisibility(0);
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
    setThumb(this.thumb_left_x + (paramFloat - this.min) * (this.thumb_right_x - this.thumb_left_x) / (this.max - this.min) + this.thumb_hor_img.getWidth() / 2);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\seekbar\DuplexSeekBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */