package cc.makeblock.makeblock.customview.playground;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public abstract class Joystick
  extends FrequencyRestrictedView<JoyStickState>
  implements View.OnTouchListener
{
  protected int angle;
  private IncessantlyRunnable incessantlyRunnable = new IncessantlyRunnable(null);
  protected JoystickListener joystickListener;
  private JoyStickState lastActionState;
  protected float percentR;
  protected View rootView;
  protected ImageView stick;
  
  public Joystick(@NonNull Context paramContext)
  {
    super(paramContext);
  }
  
  public Joystick(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public Joystick(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, @AttrRes int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void addInflatedView(View paramView)
  {
    addView(paramView);
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    float f3 = paramView.getX();
    float f4 = paramView.getY();
    float f5 = paramMotionEvent.getX();
    float f8 = paramMotionEvent.getY();
    int i = getWidth() / 2;
    float f1 = paramView.getHeight();
    float f6 = paramView.getWidth() / 2.0F;
    float f7 = f1 / 2.0F;
    f1 = -1.0F;
    float f2 = -1.0F;
    float f11 = this.stick.getWidth();
    float f10 = this.stick.getHeight();
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      if ((f1 != -1.0F) && (f2 != -1.0F))
      {
        f8 = f6 - f11 / 2.0F;
        float f9 = (float)Math.sqrt(Math.pow(f1 - f6, 2.0D) + Math.pow(f2 - f7, 2.0D));
        f5 = f1;
        f4 = f2;
        f3 = f9;
        if (f9 > f8)
        {
          f5 = (f1 - f6) * f8 / f9 + f6;
          f4 = (f2 - f7) * f8 / f9 + f7;
          f3 = f8;
        }
        f1 = f11 / 2.0F;
        f2 = f10 / 2.0F;
        this.stick.setX(f5 - f1);
        this.stick.setY(f4 - f2);
        this.angle = ((int)(180.0D * Math.atan2(f6 - f4, f5 - f6) / 3.141592653589793D));
        this.percentR = (f3 / f8);
        paramView = new JoyStickState(this.angle, this.percentR);
        removeCallbacks(this.incessantlyRunnable);
        if (this.percentR > 0.01D)
        {
          this.lastActionState = paramView;
          postDelayed(this.incessantlyRunnable, 1000L);
        }
      }
      return true;
      if ((f5 - (i + f3)) * (f5 - (i + f3)) + (f8 - (i + f4)) * (f8 - (i + f4)) > i * i)
      {
        return false;
        f1 = paramMotionEvent.getX();
        f2 = paramMotionEvent.getY();
        continue;
        f1 = f6;
        f2 = f7;
      }
    }
  }
  
  protected void pushState()
  {
    super.pushState(new JoyStickState(this.angle, this.percentR));
  }
  
  protected void sendState(JoyStickState paramJoyStickState)
  {
    if (this.joystickListener != null) {
      this.joystickListener.onJoystickMoved(paramJoyStickState.angle, paramJoyStickState.percentR);
    }
  }
  
  public void setJoystickListener(JoystickListener paramJoystickListener)
  {
    this.joystickListener = paramJoystickListener;
  }
  
  private class IncessantlyRunnable
    implements Runnable
  {
    private IncessantlyRunnable() {}
    
    public void run()
    {
      Joystick.this.pushState(Joystick.this.lastActionState);
      Joystick.this.postDelayed(Joystick.this.incessantlyRunnable, 1000L);
    }
  }
  
  class JoyStickState
  {
    public int angle;
    public float percentR;
    
    JoyStickState(int paramInt, float paramFloat)
    {
      this.angle = paramInt;
      this.percentR = paramFloat;
    }
  }
  
  public static abstract interface JoystickListener
  {
    public abstract void onJoystickMoved(int paramInt, float paramFloat);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\playground\Joystick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */