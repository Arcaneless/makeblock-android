package cc.makeblock.makeblock.scene.connect;

import android.view.animation.AccelerateDecelerateInterpolator;

public class IntervalAccelerateDecelerateInterpolator
  extends AccelerateDecelerateInterpolator
{
  private final float intervalPercent;
  
  public IntervalAccelerateDecelerateInterpolator(float paramFloat)
  {
    if ((paramFloat < 0.0F) || (paramFloat >= 1.0F)) {
      throw new RuntimeException("你好,再见");
    }
    this.intervalPercent = paramFloat;
  }
  
  public float getInterpolation(float paramFloat)
  {
    float f = paramFloat / (1.0F - this.intervalPercent);
    paramFloat = f;
    if (f > 1.0F) {
      paramFloat = 1.0F;
    }
    return super.getInterpolation(paramFloat);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\connect\IntervalAccelerateDecelerateInterpolator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */