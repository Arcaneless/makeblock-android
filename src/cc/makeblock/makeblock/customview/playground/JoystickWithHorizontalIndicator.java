package cc.makeblock.makeblock.customview.playground;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import cc.makeblock.makeblock.R.styleable;

public class JoystickWithHorizontalIndicator
  extends Joystick
{
  private ImageView indicator_left;
  private ImageView indicator_right;
  
  public JoystickWithHorizontalIndicator(@NonNull Context paramContext)
  {
    super(paramContext);
    init(paramContext, null);
  }
  
  public JoystickWithHorizontalIndicator(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }
  
  public JoystickWithHorizontalIndicator(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, @AttrRes int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    LayoutInflater localLayoutInflater = (LayoutInflater)paramContext.getSystemService("layout_inflater");
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.JoystickWithHorizontalIndicator, 0, 0);
    int i = paramContext.getResourceId(0, -1);
    paramContext.recycle();
    this.rootView = localLayoutInflater.inflate(i, null);
    this.indicator_left = ((ImageView)this.rootView.findViewById(2131296528));
    this.indicator_right = ((ImageView)this.rootView.findViewById(2131296529));
    this.stick = ((ImageView)this.rootView.findViewById(2131296531));
    this.rootView.setOnTouchListener(this);
    addInflatedView(this.rootView);
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    super.onTouch(paramView, paramMotionEvent);
    if (paramMotionEvent.getAction() != 0) {
      updateState();
    }
    return true;
  }
  
  public void updateState()
  {
    if (this.percentR == 0.0F)
    {
      this.indicator_left.setSelected(false);
      this.indicator_right.setSelected(false);
    }
    for (;;)
    {
      if (this.joystickListener != null) {
        pushState();
      }
      return;
      if ((this.angle <= 90) && (this.angle >= -90))
      {
        this.indicator_left.setSelected(false);
        this.indicator_right.setSelected(true);
        this.angle = 0;
      }
      else if ((this.angle > 90) || (this.angle < -90))
      {
        this.indicator_left.setSelected(true);
        this.indicator_right.setSelected(false);
        this.angle = 180;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\playground\JoystickWithHorizontalIndicator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */