package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.Iterator;

public class WaveView
  extends FrameLayout
{
  private static final String TAG = WaveView.class.getSimpleName();
  private static final float center_radius_percent = 0.07172557F;
  private static final float duration = 4000.0F;
  private static final float end_radius_percent = 1.0F;
  private static final int mSpeed = 1000;
  private static final float total_height = 962.0F;
  private ArrayList<Circle> mCircles = new ArrayList();
  private Runnable mCreateCircleRunnable = new Runnable()
  {
    public void run()
    {
      if (WaveView.this.mIsRunning)
      {
        WaveView.this.newCircle();
        WaveView.this.postDelayed(WaveView.this.mCreateCircleRunnable, 1000L);
      }
    }
  };
  private float mHeight;
  private boolean mIsRunning = false;
  private final Paint mPaint = new Paint();
  private float mRadius;
  private Interpolator mWaveInterpolator = new AccelerateInterpolator(0.65F);
  private float mWidth;
  private int retryTime = 0;
  private RectF tempRectF = new RectF();
  
  public WaveView(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0);
  }
  
  public WaveView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0);
  }
  
  public WaveView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    setWillNotDraw(false);
    this.mCircles.clear();
    this.mPaint.setAntiAlias(true);
    this.mPaint.setColor(Color.parseColor("#34609a"));
    this.mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    start();
  }
  
  private void newCircle()
  {
    Circle localCircle = new Circle(System.currentTimeMillis());
    this.mCircles.add(localCircle);
    invalidate();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    Iterator localIterator = this.mCircles.iterator();
    while (localIterator.hasNext())
    {
      Circle localCircle = (Circle)localIterator.next();
      long l = System.currentTimeMillis();
      if ((float)(l - localCircle.mCreateTime) < 4000.0F)
      {
        this.mPaint.setAlpha(localCircle.getAlpha(l));
        float f1 = getHeight();
        float f2 = getWidth();
        float f3 = localCircle.getRadius(l);
        this.tempRectF.left = (f2 / 2.0F - f3);
        this.tempRectF.right = (f2 / 2.0F + f3);
        this.tempRectF.top = (f1 / 2.0F - f3);
        this.tempRectF.bottom = (f1 / 2.0F + f3);
        paramCanvas.drawArc(this.tempRectF, 0.0F, 360.0F, true, this.mPaint);
      }
      else
      {
        localIterator.remove();
      }
    }
    if (this.mCircles.size() > 0) {
      postInvalidateDelayed(100L);
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mHeight = paramInt2;
    this.mWidth = paramInt1;
    this.mRadius = (paramInt2 / 3 * 2);
    this.mPaint.setStrokeWidth(this.mHeight / 300.0F);
  }
  
  protected void onWindowVisibilityChanged(int paramInt)
  {
    if (paramInt != 0)
    {
      removeCallbacks(this.mCreateCircleRunnable);
      this.mIsRunning = false;
    }
    for (;;)
    {
      super.onWindowVisibilityChanged(paramInt);
      return;
      start();
    }
  }
  
  public void start()
  {
    if (!this.mIsRunning)
    {
      this.mIsRunning = true;
      this.mCreateCircleRunnable.run();
    }
  }
  
  public void stop()
  {
    this.mIsRunning = false;
  }
  
  private class Circle
  {
    private long mCreateTime;
    
    public Circle(long paramLong)
    {
      this.mCreateTime = paramLong;
    }
    
    public int getAlpha(long paramLong)
    {
      float f = (float)(paramLong - this.mCreateTime) / 4000.0F;
      return (int)((1.0F - WaveView.this.mWaveInterpolator.getInterpolation(f)) * 255.0F * 0.6D);
    }
    
    public float getRadius(long paramLong)
    {
      float f = (float)(paramLong - this.mCreateTime) / 4000.0F;
      return (0.07172557F + WaveView.this.mWaveInterpolator.getInterpolation(f) * 0.92827445F) * WaveView.this.mRadius;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\WaveView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */