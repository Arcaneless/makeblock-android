package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class VerticalJoyStickView
  extends AirBlockBaseJoystickView
  implements View.OnTouchListener
{
  private ImageView bottomIndicator;
  private ImageView centerIndicator;
  private IncessantlyRunnable incessantlyRunnable = new IncessantlyRunnable(null);
  private BaseJoystickView.JoyStickState lastActionState;
  private ImageView topIndicator;
  
  public VerticalJoyStickView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public VerticalJoyStickView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2131427570, null);
    ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, -1);
    paramContext.setOnTouchListener(this);
    addView(paramContext, localLayoutParams);
    this.topIndicator = ((ImageView)paramContext.findViewById(2131296530));
    this.bottomIndicator = ((ImageView)paramContext.findViewById(2131296526));
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
    float f4 = paramView.getHeight() / 2.0F;
    float f1 = -1.0F;
    int i;
    switch (paramMotionEvent.getAction())
    {
    default: 
      if (f1 != -1.0F)
      {
        float f5 = f4 - this.centerIndicator.getHeight() / 2.0F;
        float f6 = Math.abs(f1 - f4);
        float f3 = f1;
        float f2 = f6;
        if (f6 > f5)
        {
          f3 = (f1 - f4) * f5 / f6 + f4;
          f2 = f5;
        }
        f1 = this.centerIndicator.getHeight() / 2.0F;
        this.centerIndicator.setY(f3 - f1);
        if (f3 - f4 > 0.0F) {
          break label268;
        }
        i = 90;
        label149:
        f1 = f2 / f5;
        if (f1 <= 0.01F) {
          break label294;
        }
        this.centerIndicator.setSelected(true);
        if (i != 90) {
          break label275;
        }
        this.topIndicator.setSelected(true);
        this.bottomIndicator.setSelected(false);
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
      f1 = paramMotionEvent.getY();
      break;
      f1 = f4;
      break;
      label268:
      i = -90;
      break label149;
      label275:
      this.topIndicator.setSelected(false);
      this.bottomIndicator.setSelected(true);
      continue;
      label294:
      this.centerIndicator.setSelected(false);
      this.topIndicator.setSelected(false);
      this.bottomIndicator.setSelected(false);
    }
  }
  
  protected void sendState(BaseJoystickView.JoyStickState paramJoyStickState)
  {
    int i;
    int j;
    if (paramJoyStickState.percentR > 0.01D) {
      if (paramJoyStickState.angle == 90)
      {
        i = 0;
        j = (int)(paramJoyStickState.percentR * 1600.0F);
        int k = 0;
        sendAirBlockControlCommand(i, 0);
        i = k;
      }
    }
    for (;;)
    {
      sendAirBlockSpeedCommand(j, i);
      return;
      i = 1;
      break;
      j = 1600;
      i = 1600;
      sendAirBlockControlCommand(10, 0);
    }
  }
  
  public void setControlListener()
  {
    setJoystickListener(new BaseJoystickView.JoystickViewListener()
    {
      public void onJoystickMoved(int paramAnonymousInt, float paramAnonymousFloat)
      {
        VerticalJoyStickView.this.pushState(new BaseJoystickView.JoyStickState(VerticalJoyStickView.this, paramAnonymousInt, paramAnonymousFloat));
      }
    });
  }
  
  private class IncessantlyRunnable
    implements Runnable
  {
    private IncessantlyRunnable() {}
    
    public void run()
    {
      VerticalJoyStickView.this.joystickViewListener.onJoystickMoved(VerticalJoyStickView.this.lastActionState.angle, VerticalJoyStickView.this.lastActionState.percentR);
      VerticalJoyStickView.this.postDelayed(VerticalJoyStickView.this.incessantlyRunnable, 1000L);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\VerticalJoyStickView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */