package cc.makeblock.makeblock.customview.playground;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import cc.makeblock.makeblock.R.styleable;

public class ButtonView
  extends FrequencyRestrictedView<ButtonState>
  implements View.OnClickListener
{
  private ImageView buttonIconHolder;
  private OnButtonClickListener mOnButtonClickListener;
  
  public ButtonView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0);
  }
  
  public ButtonView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, @AttrRes int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.ButtonView, paramInt, 0);
    paramInt = paramAttributeSet.getResourceId(0, -1);
    paramAttributeSet.recycle();
    paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2131427535, null, false);
    this.buttonIconHolder = ((ImageView)paramContext.findViewById(2131296382));
    this.buttonIconHolder.setImageResource(paramInt);
    addView(paramContext);
    paramContext.setOnClickListener(this);
  }
  
  public void onClick(View paramView)
  {
    pushState(new ButtonState(true));
  }
  
  protected void sendState(ButtonState paramButtonState)
  {
    this.mOnButtonClickListener.onButtonClicked();
  }
  
  public void setButtonIconHolder(int paramInt)
  {
    this.buttonIconHolder.setImageResource(paramInt);
  }
  
  public void setOnButtonClickListener(OnButtonClickListener paramOnButtonClickListener)
  {
    this.mOnButtonClickListener = paramOnButtonClickListener;
  }
  
  class ButtonState
  {
    private boolean pressed;
    
    ButtonState(boolean paramBoolean)
    {
      this.pressed = paramBoolean;
    }
  }
  
  public static abstract interface OnButtonClickListener
  {
    public abstract void onButtonClicked();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\playground\ButtonView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */