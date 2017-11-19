package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class AirBlockJoyStickView
  extends AirBlockBaseJoystickView
  implements View.OnTouchListener
{
  private static final int LAZY_ANGLE = 45;
  private ImageView bottomIndicator;
  private ImageView centerIndicator;
  private IncessantlyRunnable incessantlyRunnable = new IncessantlyRunnable(null);
  private BaseJoystickView.JoyStickState lastActionState;
  private ImageView leftIndicator;
  private ImageView rightIndicator;
  private ImageView topIndicator;
  
  public AirBlockJoyStickView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public AirBlockJoyStickView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2131427544, null);
    ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, -1);
    paramContext.setOnTouchListener(this);
    addView(paramContext, localLayoutParams);
    this.leftIndicator = ((ImageView)paramContext.findViewById(2131296528));
    this.topIndicator = ((ImageView)paramContext.findViewById(2131296530));
    this.rightIndicator = ((ImageView)paramContext.findViewById(2131296529));
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
    float f1 = paramView.getHeight();
    float f6 = paramView.getWidth() / 2.0F;
    float f7 = f1 / 2.0F;
    f1 = -1.0F;
    float f2 = -1.0F;
    int i;
    switch (paramMotionEvent.getAction())
    {
    default: 
      if ((f1 != -1.0F) && (f2 != -1.0F))
      {
        float f8 = f6 - this.centerIndicator.getWidth() / 2;
        float f9 = (float)Math.sqrt(Math.pow(f1 - f6, 2.0D) + Math.pow(f2 - f7, 2.0D));
        float f5 = f1;
        float f4 = f2;
        float f3 = f9;
        if (f9 > f8)
        {
          f5 = (f1 - f6) * f8 / f9 + f6;
          f4 = (f2 - f7) * f8 / f9 + f7;
          f3 = f8;
        }
        f1 = this.centerIndicator.getWidth() / 2;
        f2 = this.centerIndicator.getHeight() / 2;
        this.centerIndicator.setX(f5 - f1);
        this.centerIndicator.setY(f4 - f2);
        i = (int)(180.0D * Math.atan2(f6 - f4, f5 - f6) / 3.141592653589793D);
        f1 = f3 / f8;
        i = adjustAngle(45, i);
        if (f1 <= 0.0F) {
          break label558;
        }
        this.centerIndicator.setSelected(true);
        if ((i < -45) || (i >= 45)) {
          break label407;
        }
        this.rightIndicator.setSelected(true);
        this.leftIndicator.setSelected(false);
        this.topIndicator.setSelected(false);
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
      f1 = paramMotionEvent.getX();
      f2 = paramMotionEvent.getY();
      break;
      f1 = f6;
      f2 = f7;
      break;
      label407:
      if ((i >= 45) && (i < 135))
      {
        this.topIndicator.setSelected(true);
        this.leftIndicator.setSelected(false);
        this.rightIndicator.setSelected(false);
        this.bottomIndicator.setSelected(false);
      }
      else if ((i < -45) && (i > 65401))
      {
        this.bottomIndicator.setSelected(true);
        this.leftIndicator.setSelected(false);
        this.topIndicator.setSelected(false);
        this.rightIndicator.setSelected(false);
      }
      else if ((i <= 65401) || (i > 135))
      {
        this.leftIndicator.setSelected(true);
        this.topIndicator.setSelected(false);
        this.rightIndicator.setSelected(false);
        this.bottomIndicator.setSelected(false);
        continue;
        label558:
        this.centerIndicator.setSelected(false);
        this.leftIndicator.setSelected(false);
        this.topIndicator.setSelected(false);
        this.rightIndicator.setSelected(false);
        this.bottomIndicator.setSelected(false);
      }
    }
  }
  
  protected void sendState(BaseJoystickView.JoyStickState paramJoyStickState)
  {
    int i;
    int j;
    if (paramJoyStickState.percentR > 0.01D) {
      switch (paramJoyStickState.angle)
      {
      default: 
        i = 1;
        j = (int)(paramJoyStickState.percentR * 2000.0F);
        int k = 1400;
        sendAirBlockControlCommand(i, 0);
        i = k;
      }
    }
    for (;;)
    {
      sendAirBlockSpeedCommand(j, i);
      return;
      i = 3;
      break;
      i = 0;
      break;
      i = 2;
      break;
      j = 1600;
      i = 0;
      sendAirBlockControlCommand(10, 0);
    }
  }
  
  public void setControlListener()
  {
    setJoystickListener(new BaseJoystickView.JoystickViewListener()
    {
      public void onJoystickMoved(int paramAnonymousInt, float paramAnonymousFloat)
      {
        AirBlockJoyStickView.this.pushState(new BaseJoystickView.JoyStickState(AirBlockJoyStickView.this, paramAnonymousInt, paramAnonymousFloat));
      }
    });
  }
  
  private class IncessantlyRunnable
    implements Runnable
  {
    private IncessantlyRunnable() {}
    
    public void run()
    {
      AirBlockJoyStickView.this.joystickViewListener.onJoystickMoved(AirBlockJoyStickView.this.lastActionState.angle, AirBlockJoyStickView.this.lastActionState.percentR);
      AirBlockJoyStickView.this.postDelayed(AirBlockJoyStickView.this.incessantlyRunnable, 1000L);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\AirBlockJoyStickView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */