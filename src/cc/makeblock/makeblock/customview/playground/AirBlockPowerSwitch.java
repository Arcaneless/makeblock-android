package cc.makeblock.makeblock.customview.playground;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class AirBlockPowerSwitch
  extends FrequencyRestrictedView<SwitchState>
{
  private ImageView powerON;
  private ImageView powerOff;
  private boolean stateOn = false;
  
  public AirBlockPowerSwitch(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public AirBlockPowerSwitch(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public AirBlockPowerSwitch(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2131427556, null, false);
    this.powerON = ((ImageView)paramContext.findViewById(2131296629));
    this.powerOff = ((ImageView)paramContext.findViewById(2131296630));
    this.powerON.setAlpha(0.0F);
    addView(paramContext);
  }
  
  private void updateState()
  {
    ObjectAnimator localObjectAnimator1;
    ObjectAnimator localObjectAnimator2;
    if (this.stateOn)
    {
      localObjectAnimator1 = ObjectAnimator.ofFloat(this.powerON, "alpha", new float[] { 1.0F, 0.0F });
      localObjectAnimator2 = ObjectAnimator.ofFloat(this.powerOff, "alpha", new float[] { 0.0F, 1.0F });
    }
    for (this.stateOn = false;; this.stateOn = true)
    {
      localObjectAnimator1.setDuration(300L);
      localObjectAnimator2.setDuration(300L);
      localObjectAnimator1.start();
      localObjectAnimator2.start();
      return;
      localObjectAnimator1 = ObjectAnimator.ofFloat(this.powerON, "alpha", new float[] { 0.0F, 1.0F });
      localObjectAnimator2 = ObjectAnimator.ofFloat(this.powerOff, "alpha", new float[] { 1.0F, 0.0F });
    }
  }
  
  protected void sendState(SwitchState paramSwitchState) {}
  
  public void setState(boolean paramBoolean)
  {
    if (paramBoolean != this.stateOn) {
      updateState();
    }
  }
  
  class SwitchState
  {
    private boolean stateOn;
    
    public SwitchState(boolean paramBoolean)
    {
      this.stateOn = paramBoolean;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\playground\AirBlockPowerSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */