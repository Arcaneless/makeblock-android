package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.MeasureSpec;

public class az
  extends View
{
  Path a;
  private Drawable b;
  private Drawable c;
  private PaintFlagsDrawFilter d = new PaintFlagsDrawFilter(1, 2);
  
  public az(Context paramContext)
  {
    super(paramContext);
    try
    {
      this.b = av.a(getContext(), "voice_empty");
      this.c = av.a(getContext(), "voice_full");
      this.b.setBounds(new Rect(-this.b.getIntrinsicWidth() / 2, -this.b.getIntrinsicHeight() / 2, this.b.getIntrinsicWidth() / 2, this.b.getIntrinsicHeight() / 2));
      this.c.setBounds(new Rect(-this.c.getIntrinsicWidth() / 2, -this.c.getIntrinsicHeight() / 2, this.c.getIntrinsicWidth() / 2, this.c.getIntrinsicHeight() / 2));
      this.a = new Path();
      a(0);
      return;
    }
    catch (Exception paramContext)
    {
      ad.a(paramContext);
    }
  }
  
  public void a(int paramInt)
  {
    this.a.reset();
    this.a.addCircle(0.0F, 0.0F, this.b.getIntrinsicWidth() * paramInt / 12, Path.Direction.CCW);
  }
  
  public void finalize()
    throws Throwable
  {
    this.b = null;
    this.c = null;
    super.finalize();
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    paramCanvas.save();
    paramCanvas.setDrawFilter(this.d);
    paramCanvas.translate(getWidth() / 2, getHeight() / 2);
    this.b.draw(paramCanvas);
    paramCanvas.clipPath(this.a);
    this.c.draw(paramCanvas);
    paramCanvas.restore();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int j = View.MeasureSpec.getSize(paramInt1);
    int i = View.MeasureSpec.getSize(paramInt2);
    Drawable localDrawable = getBackground();
    if (localDrawable != null)
    {
      j = localDrawable.getMinimumWidth();
      i = localDrawable.getMinimumHeight();
    }
    setMeasuredDimension(resolveSize(j, paramInt1), resolveSize(i, paramInt2));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\az.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */