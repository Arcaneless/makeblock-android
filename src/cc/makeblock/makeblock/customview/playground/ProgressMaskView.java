package cc.makeblock.makeblock.customview.playground;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

public class ProgressMaskView
  extends View
{
  private Drawable count_down;
  private Drawable count_down_1;
  private Drawable count_down_2;
  private Drawable count_down_3;
  private Drawable count_down_4;
  private Drawable count_down_5;
  private Drawable count_down_empty;
  private int duration;
  private int endDegrees;
  private RectF oval;
  private Paint paint;
  
  public ProgressMaskView(Context paramContext)
  {
    super(paramContext, null);
    init();
  }
  
  public ProgressMaskView(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  public ProgressMaskView(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  public ProgressMaskView(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    init();
  }
  
  private void init()
  {
    this.count_down_1 = ContextCompat.getDrawable(getContext(), 2131165804);
    this.count_down_2 = ContextCompat.getDrawable(getContext(), 2131165805);
    this.count_down_3 = ContextCompat.getDrawable(getContext(), 2131165806);
    this.count_down_4 = ContextCompat.getDrawable(getContext(), 2131165807);
    this.count_down_5 = ContextCompat.getDrawable(getContext(), 2131165808);
    this.count_down_empty = new ColorDrawable(0);
    this.count_down = this.count_down_empty;
    this.paint = new Paint();
    this.paint.setAntiAlias(true);
    this.paint.setColor(Color.argb(100, 0, 0, 0));
    this.paint.setStyle(Paint.Style.FILL);
    this.oval = new RectF();
  }
  
  public void finish()
  {
    this.endDegrees = 0;
    this.count_down = this.count_down_empty;
    invalidate();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    this.oval.left = 0.0F;
    this.oval.top = 0.0F;
    this.oval.right = getWidth();
    this.oval.bottom = getHeight();
    paramCanvas.drawArc(this.oval, 270 - this.endDegrees, this.endDegrees, true, this.paint);
    this.count_down.setBounds(getWidth() / 4, getHeight() / 4, getWidth() / 4 * 3, getHeight() / 4 * 3);
    this.count_down.draw(paramCanvas);
  }
  
  public void setDuration(int paramInt)
  {
    this.duration = paramInt;
  }
  
  public void setProgress(long paramLong)
  {
    this.endDegrees = ((int)(360L * paramLong / (this.duration * 1000)));
    if (paramLong > 4000L) {
      this.count_down = this.count_down_5;
    }
    for (;;)
    {
      invalidate();
      return;
      if (paramLong > 3000L) {
        this.count_down = this.count_down_4;
      } else if (paramLong > 2000L) {
        this.count_down = this.count_down_3;
      } else if (paramLong > 1000L) {
        this.count_down = this.count_down_2;
      } else {
        this.count_down = this.count_down_1;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\playground\ProgressMaskView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */