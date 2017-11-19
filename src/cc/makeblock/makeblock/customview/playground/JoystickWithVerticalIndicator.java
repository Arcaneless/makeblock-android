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

public class JoystickWithVerticalIndicator
  extends Joystick
{
  private ImageView indicator_bottom;
  private ImageView indicator_top;
  
  public JoystickWithVerticalIndicator(@NonNull Context paramContext)
  {
    super(paramContext);
    init(paramContext, null);
  }
  
  public JoystickWithVerticalIndicator(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }
  
  public JoystickWithVerticalIndicator(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, @AttrRes int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    LayoutInflater localLayoutInflater = (LayoutInflater)paramContext.getSystemService("layout_inflater");
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.JoystickWithVerticalIndicator, 0, 0);
    int i = paramContext.getResourceId(0, -1);
    paramContext.recycle();
    this.rootView = localLayoutInflater.inflate(i, null);
    this.stick = ((ImageView)this.rootView.findViewById(2131296531));
    this.rootView.setOnTouchListener(this);
    addInflatedView(this.rootView);
    this.indicator_top = ((ImageView)this.rootView.findViewById(2131296530));
    this.indicator_bottom = ((ImageView)this.rootView.findViewById(2131296526));
  }
  
  private void updateState()
  {
    if (this.percentR == 0.0F)
    {
      this.indicator_top.setSelected(false);
      this.indicator_bottom.setSelected(false);
    }
    for (;;)
    {
      if (this.joystickListener != null) {
        pushState();
      }
      return;
      if (this.angle > 0)
      {
        this.indicator_top.setSelected(true);
        this.indicator_bottom.setSelected(false);
        this.angle = 90;
      }
      else if (this.angle < 0)
      {
        this.indicator_top.setSelected(false);
        this.indicator_bottom.setSelected(true);
        this.angle = -90;
      }
    }
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    super.onTouch(paramView, paramMotionEvent);
    if (paramMotionEvent.getAction() != 0) {
      updateState();
    }
    return true;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\playground\JoystickWithVerticalIndicator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */