package cc.makeblock.makeblock.customview.laboratory;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class Joystick
  extends LaboratoryView<JoystickState>
  implements View.OnTouchListener
{
  private static final int LAZY_ANGLE = 10;
  public static final float WIDGET_SIZE_PERCENT = 0.9777778F;
  private ImageView centerIndicator;
  private OnJoystickTriggerListener onJoystickTriggerListener;
  
  public Joystick(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public Joystick(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  private int adjustAngle(int paramInt1, int paramInt2)
  {
    int i = paramInt2;
    if (Math.abs(paramInt2) < paramInt1) {
      i = 0;
    }
    paramInt2 = i;
    if (Math.abs(i - 90) < paramInt1) {
      paramInt2 = 90;
    }
    i = paramInt2;
    if (180 - Math.abs(paramInt2) < paramInt1) {
      i = 180;
    }
    paramInt2 = i;
    if (Math.abs(i + 90) < paramInt1) {
      paramInt2 = -90;
    }
    return paramInt2;
  }
  
  private void init(Context paramContext)
  {
    paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2131427460, this);
    paramContext.setOnTouchListener(this);
    this.centerIndicator = ((ImageView)paramContext.findViewById(2131296531));
  }
  
  public float getSizePercent()
  {
    return 0.9777778F;
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    float f1 = paramView.getHeight();
    float f6 = paramView.getWidth() / 2.0F;
    float f7 = f1 / 2.0F;
    f1 = -1.0F;
    float f2 = -1.0F;
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
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
        int i = (int)(180.0D * Math.atan2(f6 - f4, f5 - f6) / 3.141592653589793D);
        f1 = f3 / f8;
        pushState(new JoystickState(adjustAngle(10, i), f1));
      }
      return true;
      f1 = paramMotionEvent.getX();
      f2 = paramMotionEvent.getY();
      continue;
      f1 = f6;
      f2 = f7;
    }
  }
  
  protected void sendState(JoystickState paramJoystickState)
  {
    if (this.onJoystickTriggerListener != null) {
      this.onJoystickTriggerListener.onJoystickTrigger(paramJoystickState.angle, paramJoystickState.percentR);
    }
  }
  
  public void setOnJoystickTriggerListener(OnJoystickTriggerListener paramOnJoystickTriggerListener)
  {
    this.onJoystickTriggerListener = paramOnJoystickTriggerListener;
  }
  
  public class JoystickState
  {
    int angle;
    float percentR;
    
    public JoystickState(int paramInt, float paramFloat)
    {
      this.angle = paramInt;
      this.percentR = paramFloat;
    }
  }
  
  public static abstract interface OnJoystickTriggerListener
  {
    public abstract void onJoystickTrigger(int paramInt, float paramFloat);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\laboratory\Joystick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */