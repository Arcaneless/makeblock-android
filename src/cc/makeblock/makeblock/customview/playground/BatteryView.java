package cc.makeblock.makeblock.customview.playground;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class BatteryView
  extends FrameLayout
{
  private ImageView batteryImage;
  private View rootView;
  
  public BatteryView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public BatteryView(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public BatteryView(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    this.rootView = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2131427555, null, false);
    this.batteryImage = ((ImageView)this.rootView.findViewById(2131296310));
    this.batteryImage.setImageDrawable(new ColorDrawable(0));
    addView(this.rootView);
  }
  
  public void setBatteryPercent(float paramFloat)
  {
    PercentRelativeLayout.LayoutParams localLayoutParams;
    if (paramFloat >= 0.8D)
    {
      this.rootView.setBackground(ContextCompat.getDrawable(getContext(), 2131165755));
      this.batteryImage.setImageResource(2131165759);
      localLayoutParams = (PercentRelativeLayout.LayoutParams)this.batteryImage.getLayoutParams();
      localLayoutParams.getPercentLayoutInfo().widthPercent = 1.0F;
      this.batteryImage.setLayoutParams(localLayoutParams);
      return;
    }
    if (paramFloat >= 0.6D)
    {
      this.rootView.setBackground(ContextCompat.getDrawable(getContext(), 2131165755));
      this.batteryImage.setImageResource(2131165759);
      localLayoutParams = (PercentRelativeLayout.LayoutParams)this.batteryImage.getLayoutParams();
      localLayoutParams.getPercentLayoutInfo().widthPercent = 0.8F;
      this.batteryImage.setLayoutParams(localLayoutParams);
      return;
    }
    if (paramFloat >= 0.4D)
    {
      this.rootView.setBackground(ContextCompat.getDrawable(getContext(), 2131165755));
      this.batteryImage.setImageResource(2131165759);
      localLayoutParams = (PercentRelativeLayout.LayoutParams)this.batteryImage.getLayoutParams();
      localLayoutParams.getPercentLayoutInfo().widthPercent = 0.6F;
      this.batteryImage.setLayoutParams(localLayoutParams);
      return;
    }
    if (paramFloat >= 0.2D)
    {
      this.rootView.setBackground(ContextCompat.getDrawable(getContext(), 2131165756));
      this.batteryImage.setImageResource(2131165760);
      return;
    }
    if (paramFloat > 0.0F)
    {
      this.rootView.setBackground(ContextCompat.getDrawable(getContext(), 2131165757));
      this.batteryImage.setImageResource(2131165761);
      return;
    }
    this.rootView.setBackground(ContextCompat.getDrawable(getContext(), 2131165758));
    this.batteryImage.setImageDrawable(new ColorDrawable(0));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\playground\BatteryView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */