package cc.makeblock.makeblock.customview.playground;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import cc.makeblock.makeblock.R.styleable;

public class HexagonSkillView
  extends View
  implements View.OnClickListener
{
  private float angleSize;
  private Canvas canvasSrc;
  private int coolingTime;
  private Drawable count_down;
  private Drawable count_down_1;
  private Drawable count_down_2;
  private Drawable count_down_3;
  private Drawable count_down_4;
  private Drawable count_down_5;
  private Drawable count_down_empty;
  private RectF layerRectF;
  private Handler mHandler;
  private OnSkillClickListener mOnSkillClickListener;
  private Paint mPaint;
  private Path mPath;
  private PorterDuffXfermode mPorterDuffXfermode;
  private boolean show = false;
  private Bitmap sixAngleBitmap;
  private Bitmap srcBitmap;
  private long starTime;
  private int startAngle;
  
  public HexagonSkillView(Context paramContext)
  {
    super(paramContext);
    initView(paramContext, null);
  }
  
  public HexagonSkillView(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext, paramAttributeSet);
  }
  
  public HexagonSkillView(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initView(paramContext, paramAttributeSet);
  }
  
  private void animationFinish()
  {
    this.angleSize = 0.0F;
    createSrcBitmap(getWidth(), getHeight());
    this.count_down = this.count_down_empty;
    invalidate();
    setClickable(true);
  }
  
  private void createSixAngleBitmap(int paramInt1, int paramInt2, int paramInt3)
  {
    this.mPath.reset();
    this.mPath.moveTo(-paramInt3 / 2, paramInt3);
    this.mPath.lineTo(paramInt3 / 2, paramInt3);
    this.mPath.lineTo(paramInt3, 0.0F);
    this.mPath.lineTo(paramInt3 / 2, -paramInt3);
    this.mPath.lineTo(-paramInt3 / 2, -paramInt3);
    this.mPath.lineTo(-paramInt3, 0.0F);
    this.mPath.close();
    this.sixAngleBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(this.sixAngleBitmap);
    localCanvas.save();
    localCanvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
    localCanvas.drawPath(this.mPath, this.mPaint);
    localCanvas.restore();
  }
  
  private void createSrcBitmap(int paramInt1, int paramInt2)
  {
    if (this.srcBitmap == null)
    {
      this.srcBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
      this.canvasSrc = new Canvas(this.srcBitmap);
      this.canvasSrc.translate(-paramInt1 / 2, -paramInt2 / 2);
    }
    this.canvasSrc.drawColor(0, PorterDuff.Mode.CLEAR);
    RectF localRectF = new RectF(0.0F, 0.0F, paramInt1 * 2, paramInt2 * 2);
    this.canvasSrc.drawArc(localRectF, this.startAngle, -this.angleSize, true, this.mPaint);
  }
  
  private void initHandler()
  {
    this.mHandler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        super.handleMessage(paramAnonymousMessage);
        HexagonSkillView.access$002(HexagonSkillView.this, 360.0F - (float)(360L * (System.currentTimeMillis() - HexagonSkillView.this.starTime)) * 1.0F / (HexagonSkillView.this.coolingTime * 1000));
        if (HexagonSkillView.this.angleSize >= 0.0F)
        {
          HexagonSkillView.this.updateRemainTime();
          HexagonSkillView.this.createSrcBitmap(HexagonSkillView.this.getWidth(), HexagonSkillView.this.getHeight());
          HexagonSkillView.this.invalidate();
          sendEmptyMessageDelayed(0, 10L);
          return;
        }
        HexagonSkillView.this.animationFinish();
      }
    };
  }
  
  private void initView(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SkillView, 0, 0);
    this.coolingTime = paramContext.getInt(0, -1);
    paramContext.recycle();
    setOnClickListener(this);
    this.mPaint = new Paint(1);
    this.mPaint.setColor(-16777216);
    this.mPaint.setAlpha(200);
    this.mPaint.setStyle(Paint.Style.FILL);
    this.mPath = new Path();
    this.mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    this.count_down_1 = ContextCompat.getDrawable(getContext(), 2131165804);
    this.count_down_2 = ContextCompat.getDrawable(getContext(), 2131165805);
    this.count_down_3 = ContextCompat.getDrawable(getContext(), 2131165806);
    this.count_down_4 = ContextCompat.getDrawable(getContext(), 2131165807);
    this.count_down_5 = ContextCompat.getDrawable(getContext(), 2131165808);
    this.count_down_empty = new ColorDrawable(0);
    this.count_down = this.count_down_empty;
    setScaleX(0.0F);
    setScaleY(0.0F);
  }
  
  public void onClick(View paramView)
  {
    if (this.coolingTime > 0) {
      startAngleAnimation();
    }
    if (this.mOnSkillClickListener != null) {
      this.mOnSkillClickListener.onSkillTriggered();
    }
    setClickable(false);
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mHandler != null) {
      this.mHandler.removeCallbacksAndMessages(null);
    }
    this.mPorterDuffXfermode = null;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.srcBitmap != null)
    {
      paramCanvas.saveLayer(this.layerRectF, null, 31);
      paramCanvas.drawBitmap(this.sixAngleBitmap, 0.0F, 0.0F, this.mPaint);
      this.mPaint.setXfermode(this.mPorterDuffXfermode);
      paramCanvas.drawBitmap(this.srcBitmap, 0.0F, 0.0F, this.mPaint);
      this.mPaint.setXfermode(null);
      paramCanvas.restore();
      if (this.count_down != this.count_down_empty) {
        this.count_down.draw(paramCanvas);
      }
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.layerRectF = new RectF(0.0F, 0.0F, paramInt1, paramInt2);
    createSixAngleBitmap(paramInt1, paramInt2, paramInt1 / 2);
    createSrcBitmap(paramInt1, paramInt2);
  }
  
  public void setCancel(boolean paramBoolean)
  {
    if (this.mHandler != null)
    {
      this.mHandler.removeCallbacksAndMessages(null);
      animationFinish();
    }
  }
  
  public void setOnSkillTriggerListener(OnSkillClickListener paramOnSkillClickListener)
  {
    this.mOnSkillClickListener = paramOnSkillClickListener;
  }
  
  public void setShow(boolean paramBoolean)
  {
    if (paramBoolean == this.show) {
      return;
    }
    this.show = paramBoolean;
    if (paramBoolean)
    {
      localObjectAnimator1 = ObjectAnimator.ofFloat(this, "scaleX", new float[] { 0.0F, 1.0F });
      localObjectAnimator2 = ObjectAnimator.ofFloat(this, "scaleY", new float[] { 0.0F, 1.0F });
      localObjectAnimator1.setDuration(300L);
      localObjectAnimator1.setInterpolator(new OvershootInterpolator());
      localObjectAnimator2.setDuration(300L);
      localObjectAnimator2.setInterpolator(new OvershootInterpolator());
      localObjectAnimator1.start();
      localObjectAnimator2.start();
      return;
    }
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(this, "scaleX", new float[] { 1.0F, 0.0F });
    ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(this, "scaleY", new float[] { 1.0F, 0.0F });
    localObjectAnimator1.setDuration(300L);
    localObjectAnimator1.setInterpolator(new AccelerateInterpolator());
    localObjectAnimator2.setDuration(300L);
    localObjectAnimator2.setInterpolator(new AccelerateInterpolator());
    localObjectAnimator1.start();
    localObjectAnimator2.start();
  }
  
  public void startAngleAnimation()
  {
    if (this.mHandler == null) {
      initHandler();
    }
    this.starTime = System.currentTimeMillis();
    this.startAngle = -90;
    this.angleSize = 360.0F;
    this.mHandler.sendEmptyMessage(0);
  }
  
  public void updateRemainTime()
  {
    int i = (int)(this.coolingTime * 1000 * this.angleSize / 360.0F);
    if (i > 4000) {
      this.count_down = this.count_down_5;
    }
    for (;;)
    {
      this.count_down.setBounds(getWidth() / 4, getHeight() / 4, getWidth() / 4 * 3, getHeight() / 4 * 3);
      return;
      if (i > 3000) {
        this.count_down = this.count_down_4;
      } else if (i > 2000) {
        this.count_down = this.count_down_3;
      } else if (i > 1000) {
        this.count_down = this.count_down_2;
      } else {
        this.count_down = this.count_down_1;
      }
    }
  }
  
  public static abstract interface OnSkillClickListener
  {
    public abstract void onSkillTriggered();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\playground\HexagonSkillView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */