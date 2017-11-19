package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class HorizontalJoyStickView
  extends AirBlockBaseJoystickView
  implements View.OnTouchListener
{
  private ImageView centerIndicator;
  private IncessantlyRunnable incessantlyRunnable = new IncessantlyRunnable(null);
  private boolean isCar;
  private BaseJoystickView.JoyStickState lastActionState;
  private ImageView leftIndicator;
  private ImageView rightIndicator;
  
  public HorizontalJoyStickView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public HorizontalJoyStickView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2131427543, null);
    ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, -1);
    paramContext.setOnTouchListener(this);
    addView(paramContext, localLayoutParams);
    this.leftIndicator = ((ImageView)paramContext.findViewById(2131296528));
    this.rightIndicator = ((ImageView)paramContext.findViewById(2131296529));
    this.centerIndicator = ((ImageView)paramContext.findViewById(2131296527));
    setControlListener();
  }
  
  public boolean getPortSelectable()
  {
    return false;
  }
  
  public boolean getProgrammable()
  {
    return false;
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    float f4 = paramView.getWidth() / 2.0F;
    float f1 = -1.0F;
    int i;
    switch (paramMotionEvent.getAction())
    {
    default: 
      if (f1 != -1.0F)
      {
        float f5 = f4 - this.centerIndicator.getWidth() / 2.0F;
        float f6 = Math.abs(f1 - f4);
        float f3 = f1;
        float f2 = f6;
        if (f6 > f5)
        {
          f3 = (f1 - f4) * f5 / f6 + f4;
          f2 = f5;
        }
        f1 = this.centerIndicator.getWidth() / 2.0F;
        this.centerIndicator.setX(f3 - f1);
        if (f3 - f4 > 0.0F) {
          break label269;
        }
        i = 180;
        label150:
        f1 = f2 / f5;
        if (f1 <= 0.0F) {
          break label294;
        }
        this.centerIndicator.setSelected(true);
        if (i != 180) {
          break label275;
        }
        this.leftIndicator.setSelected(true);
        this.rightIndicator.setSelected(false);
      }
      break;
    }
    for (;;)
    {
      paramView = new BaseJoystickView.JoyStickState(this, i, f1);
      removeCallbacks(this.incessantlyRunnable);
      this.joystickViewListener.onJoystickMoved(i, f1);
      if (f1 > 0.01D)
      {
        this.lastActionState = paramView;
        postDelayed(this.incessantlyRunnable, 1000L);
      }
      return true;
      f1 = paramMotionEvent.getX();
      break;
      f1 = f4;
      break;
      label269:
      i = 0;
      break label150;
      label275:
      this.rightIndicator.setSelected(true);
      this.leftIndicator.setSelected(false);
      continue;
      label294:
      this.centerIndicator.setSelected(false);
      this.leftIndicator.setSelected(false);
      this.rightIndicator.setSelected(false);
    }
  }
  
  protected void sendState(BaseJoystickView.JoyStickState paramJoyStickState)
  {
    int i = paramJoyStickState.angle;
    float f = paramJoyStickState.percentR;
    int k;
    if (i == 180)
    {
      k = -(int)(f * 20.0F + 20.0F);
      if (f >= 0.01D) {
        break label102;
      }
      if (!this.isCar) {
        break label90;
      }
      j = 2000;
    }
    for (i = 1400;; i = 1600)
    {
      sendAirBlockControlCommand(30, k);
      k = j;
      j = i;
      sendAirBlockSpeedCommand(k, j);
      return;
      k = (int)(f * 20.0F + 20.0F);
      break;
      label90:
      j = 1600;
    }
    label102:
    if (this.isCar) {
      i = (int)(2000.0F * f);
    }
    for (int j = 1400;; j = 0)
    {
      sendAirBlockControlCommand(6, k);
      k = i;
      break;
      i = (int)(1600.0F * f);
    }
  }
  
  public void setControlListener()
  {
    setJoystickListener(new BaseJoystickView.JoystickViewListener()
    {
      public void onJoystickMoved(int paramAnonymousInt, float paramAnonymousFloat)
      {
        HorizontalJoyStickView.this.pushState(new BaseJoystickView.JoyStickState(HorizontalJoyStickView.this, paramAnonymousInt, paramAnonymousFloat));
      }
    });
  }
  
  public void setIsCar(boolean paramBoolean)
  {
    this.isCar = paramBoolean;
  }
  
  private class IncessantlyRunnable
    implements Runnable
  {
    private IncessantlyRunnable() {}
    
    public void run()
    {
      HorizontalJoyStickView.this.joystickViewListener.onJoystickMoved(HorizontalJoyStickView.this.lastActionState.angle, HorizontalJoyStickView.this.lastActionState.percentR);
      HorizontalJoyStickView.this.postDelayed(HorizontalJoyStickView.this.incessantlyRunnable, 1000L);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\HorizontalJoyStickView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */