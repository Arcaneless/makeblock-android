package cc.makeblock.makeblock.customview.laboratory;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class WaveView
  extends View
{
  private Bitmap background;
  private int mCenterY;
  private Paint mPaint;
  private Path mPath;
  private int mScreenHeight;
  private int mScreenWidth;
  private ValueAnimator mValueAnimatior;
  private int mWaveCount;
  private int mWaveLength;
  private int offset;
  private PorterDuffXfermode porterDuffXfermode;
  private RectF rectF;
  private boolean stop = true;
  
  public WaveView(Context paramContext)
  {
    super(paramContext);
  }
  
  public WaveView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mPaint = new Paint(1);
    this.mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    this.mPaint.setColor(Color.parseColor("#993edc85"));
    this.mWaveLength = 400;
    this.porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    setLayerType(1, null);
    this.background = BitmapFactory.decodeResource(getResources(), 2131165642);
  }
  
  public WaveView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private void startAnimation()
  {
    this.mValueAnimatior = ValueAnimator.ofInt(new int[] { 0, getWidth() });
    this.mValueAnimatior.setDuration(1000L);
    this.mValueAnimatior.setInterpolator(new LinearInterpolator());
    this.mValueAnimatior.setRepeatCount(-1);
    this.mValueAnimatior.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
      {
        WaveView.access$002(WaveView.this, ((Integer)paramAnonymousValueAnimator.getAnimatedValue()).intValue());
        WaveView.this.invalidate();
      }
    });
    this.mValueAnimatior.start();
  }
  
  public void cancel()
  {
    if (!this.stop) {
      this.stop = true;
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if ((this.mValueAnimatior != null) && (this.mValueAnimatior.isRunning()))
    {
      this.mValueAnimatior.cancel();
      clearAnimation();
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    paramCanvas.drawBitmap(this.background, 0.0F, 0.0F, this.mPaint);
    this.mPaint.setXfermode(this.porterDuffXfermode);
    this.mPath.reset();
    this.mPath.moveTo(-this.mWaveLength * 2, this.mCenterY);
    if (!this.stop)
    {
      int i = 0;
      while (i < this.mWaveCount)
      {
        this.mPath.quadTo(-this.mWaveLength * 3 / 4 + this.mWaveLength * i + this.offset - this.mWaveLength, this.mCenterY + 60, -this.mWaveLength / 2 + this.mWaveLength * i + this.offset - this.mWaveLength, this.mCenterY);
        this.mPath.quadTo(-this.mWaveLength / 4 + this.mWaveLength * i + this.offset - this.mWaveLength, this.mCenterY - 60, this.mWaveLength * i + this.offset - this.mWaveLength, this.mCenterY);
        this.mPath.quadTo(-this.mWaveLength * 3 / 4 + this.mWaveLength * i + this.offset, this.mCenterY + 60, -this.mWaveLength / 2 + this.mWaveLength * i + this.offset, this.mCenterY);
        this.mPath.quadTo(-this.mWaveLength / 4 + this.mWaveLength * i + this.offset, this.mCenterY - 60, this.mWaveLength * i + this.offset, this.mCenterY);
        i += 1;
      }
      this.mPath.lineTo(this.mScreenWidth, this.mScreenHeight);
      this.mPath.lineTo(0.0F, this.mScreenHeight);
    }
    for (;;)
    {
      this.mPath.close();
      paramCanvas.drawPath(this.mPath, this.mPaint);
      this.mPaint.setXfermode(null);
      return;
      this.mPath.arcTo(this.rectF, 0.0F, 180.0F);
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mPath = new Path();
    this.mScreenHeight = paramInt2;
    this.mScreenWidth = paramInt1;
    this.mCenterY = (paramInt2 / 2);
    this.mWaveLength = paramInt1;
    this.rectF = new RectF(0.0F, 0.0F, getWidth(), getHeight());
    if ((paramInt1 > 0) && (paramInt2 > 0))
    {
      this.mWaveCount = ((int)Math.round(this.mScreenWidth / this.mWaveLength + 1.5D));
      this.background = Bitmap.createScaledBitmap(this.background, paramInt1, paramInt2, false);
    }
  }
  
  public void start()
  {
    if (this.stop)
    {
      this.stop = false;
      invalidate();
      startAnimation();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\laboratory\WaveView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */