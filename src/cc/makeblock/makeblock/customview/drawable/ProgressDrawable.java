package cc.makeblock.makeblock.customview.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import cc.makeblock.makeblock.base.App;

public class ProgressDrawable
  extends Drawable
{
  public static final int DIRECTION_LEFT_TO_RIGHT = 0;
  public static final int DIRECTION_RIGHT_TO_LEFT = 1;
  private Bitmap mBackground;
  private int mBackgroundResId;
  private int mDirection;
  private Bitmap mFill;
  private int mFillResId;
  private Paint mPaint;
  private int mPictureWidth;
  private float mProgress;
  
  public ProgressDrawable(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mBackgroundResId = paramInt1;
    this.mDirection = paramInt4;
    this.mFillResId = paramInt2;
    this.mPaint = new Paint();
    this.mPaint.setStyle(Paint.Style.FILL);
    this.mPictureWidth = paramInt3;
  }
  
  private void initBitmap()
  {
    if (this.mBackground == null)
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inDensity = this.mPictureWidth;
      localOptions.inTargetDensity = getBounds().width();
      this.mBackground = BitmapFactory.decodeResource(App.getContext().getResources(), this.mBackgroundResId, localOptions);
      this.mFill = BitmapFactory.decodeResource(App.getContext().getResources(), this.mFillResId, localOptions);
    }
  }
  
  public void draw(@NonNull Canvas paramCanvas)
  {
    initBitmap();
    paramCanvas.drawBitmap(this.mBackground, 0.0F, 0.0F, this.mPaint);
    paramCanvas.save();
    if (this.mDirection == 0) {
      paramCanvas.clipRect(0.0F, 0.0F, getBounds().right * this.mProgress, getBounds().bottom);
    }
    for (;;)
    {
      paramCanvas.drawBitmap(this.mFill, 0.0F, 0.0F, this.mPaint);
      paramCanvas.restore();
      return;
      paramCanvas.clipRect(getBounds().right * (1.0F - this.mProgress), 0.0F, getBounds().right, getBounds().bottom);
    }
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public void setAlpha(@IntRange(from=0L, to=255L) int paramInt)
  {
    this.mPaint.setAlpha(paramInt);
  }
  
  public void setColorFilter(@Nullable ColorFilter paramColorFilter)
  {
    this.mPaint.setColorFilter(paramColorFilter);
  }
  
  public void setProgress(float paramFloat)
  {
    this.mProgress = paramFloat;
    invalidateSelf();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\drawable\ProgressDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */