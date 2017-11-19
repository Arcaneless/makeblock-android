package cc.makeblock.makeblock.scene.connect;

import android.view.animation.Interpolator;

public class AlphaIntervalInterpolator
  implements Interpolator
{
  private float intervalPercent;
  private float remainPercent;
  private float tempInput;
  
  public AlphaIntervalInterpolator(float paramFloat)
  {
    this.intervalPercent = paramFloat;
    this.remainPercent = (1.0F - paramFloat);
  }
  
  public float getInterpolation(float paramFloat)
  {
    if (paramFloat <= this.intervalPercent) {
      return 0.0F;
    }
    this.tempInput = ((paramFloat - this.intervalPercent) / this.remainPercent);
    return this.tempInput;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\connect\AlphaIntervalInterpolator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */