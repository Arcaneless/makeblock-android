package cc.makeblock.makeblock.customview.playground;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cc.makeblock.makeblock.R.styleable;

public class SignalView
  extends FrameLayout
{
  private int background_resource;
  private int cover_resource;
  private int error_resource;
  private View rootView;
  private ImageView signal;
  
  public SignalView(@NonNull Context paramContext)
  {
    super(paramContext);
    init(paramContext, null);
  }
  
  public SignalView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }
  
  public SignalView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, @AttrRes int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SignalView, 0, 0);
    this.background_resource = paramAttributeSet.getResourceId(0, 0);
    this.cover_resource = paramAttributeSet.getResourceId(1, 0);
    this.error_resource = paramAttributeSet.getResourceId(2, 0);
    paramAttributeSet.recycle();
    this.rootView = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2131427557, null, false);
    this.signal = ((ImageView)this.rootView.findViewById(2131296674));
    this.signal.setImageDrawable(new ColorDrawable(0));
    addView(this.rootView);
  }
  
  private void updateSignalState(float paramFloat)
  {
    this.rootView.setBackground(ContextCompat.getDrawable(getContext(), this.background_resource));
    this.signal.setImageResource(this.cover_resource);
    PercentRelativeLayout.LayoutParams localLayoutParams = (PercentRelativeLayout.LayoutParams)this.signal.getLayoutParams();
    localLayoutParams.getPercentLayoutInfo().widthPercent = paramFloat;
    this.signal.setLayoutParams(localLayoutParams);
  }
  
  public void setSignalPercent(float paramFloat)
  {
    if (paramFloat >= 0.8F) {
      updateSignalState(1.0F);
    }
    do
    {
      return;
      if (paramFloat >= 0.6F)
      {
        updateSignalState(0.8F);
        return;
      }
      if (paramFloat >= 0.4F)
      {
        updateSignalState(0.6F);
        return;
      }
      if (paramFloat >= 0.2F)
      {
        updateSignalState(0.4F);
        return;
      }
      if (paramFloat > 0.0F)
      {
        updateSignalState(0.2F);
        return;
      }
    } while (paramFloat != 0.0F);
    if (this.error_resource != 0)
    {
      this.rootView.setBackground(ContextCompat.getDrawable(getContext(), this.error_resource));
      this.signal.setImageDrawable(new ColorDrawable(0));
      return;
    }
    updateSignalState(0.0F);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\playground\SignalView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */