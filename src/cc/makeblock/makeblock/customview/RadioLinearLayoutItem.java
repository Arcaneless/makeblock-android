package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Checkable;

public class RadioLinearLayoutItem
  extends PercentRelativeLayout
  implements Checkable, View.OnClickListener
{
  private static final int[] CheckedStateSet = { 16842912 };
  private boolean mChecked;
  private boolean mIsClick;
  private OnCheckedListener mOnCheckedListener;
  private OnTouchStatusListener mOnTouchStatusListener;
  
  public RadioLinearLayoutItem(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0);
  }
  
  public RadioLinearLayoutItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0);
  }
  
  public RadioLinearLayoutItem(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    setOnClickListener(this);
  }
  
  private void setChildChecked(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    int j = paramViewGroup.getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = paramViewGroup.getChildAt(i);
      if ((localView instanceof Checkable)) {
        ((Checkable)localView).setChecked(paramBoolean);
      }
      if ((localView instanceof ViewGroup)) {
        setChildChecked((ViewGroup)localView, paramBoolean);
      }
      i += 1;
    }
  }
  
  public boolean callOnClick()
  {
    this.mIsClick = super.callOnClick();
    return this.mIsClick;
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    invalidate();
  }
  
  public boolean isChecked()
  {
    return this.mChecked;
  }
  
  public void onClick(View paramView)
  {
    setChecked(true);
    this.mIsClick = true;
  }
  
  public int[] onCreateDrawableState(int paramInt)
  {
    int[] arrayOfInt = super.onCreateDrawableState(paramInt + 1);
    if (isChecked()) {
      mergeDrawableStates(arrayOfInt, CheckedStateSet);
    }
    return arrayOfInt;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = super.onTouchEvent(paramMotionEvent);
    if (this.mOnTouchStatusListener == null) {
      return bool;
    }
    switch (paramMotionEvent.getAction())
    {
    case 2: 
    default: 
      return bool;
    case 0: 
      this.mIsClick = false;
      this.mOnTouchStatusListener.onTouchStatus(this, 0);
      return bool;
    }
    postDelayed(new Runnable()
    {
      public void run()
      {
        if (RadioLinearLayoutItem.this.mIsClick)
        {
          RadioLinearLayoutItem.this.mOnTouchStatusListener.onTouchStatus(RadioLinearLayoutItem.this, 1);
          return;
        }
        RadioLinearLayoutItem.this.mOnTouchStatusListener.onTouchStatus(RadioLinearLayoutItem.this, 3);
      }
    }, 25L);
    return bool;
  }
  
  public boolean performClick()
  {
    this.mIsClick = super.performClick();
    return this.mIsClick;
  }
  
  public void setChecked(boolean paramBoolean)
  {
    if (paramBoolean != this.mChecked)
    {
      this.mChecked = paramBoolean;
      refreshDrawableState();
      setChildChecked(this, this.mChecked);
    }
    if ((paramBoolean) && (this.mOnCheckedListener != null)) {
      this.mOnCheckedListener.onChecked(this);
    }
  }
  
  public void setOnCheckedListener(OnCheckedListener paramOnCheckedListener)
  {
    this.mOnCheckedListener = paramOnCheckedListener;
  }
  
  public void setOnTouchStatusListener(OnTouchStatusListener paramOnTouchStatusListener)
  {
    this.mOnTouchStatusListener = paramOnTouchStatusListener;
  }
  
  public void toggle()
  {
    if (!this.mChecked) {}
    for (boolean bool = true;; bool = false)
    {
      setChecked(bool);
      return;
    }
  }
  
  public static abstract interface OnCheckedListener
  {
    public abstract void onChecked(RadioLinearLayoutItem paramRadioLinearLayoutItem);
  }
  
  public static abstract interface OnTouchStatusListener
  {
    public abstract void onTouchStatus(View paramView, int paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\RadioLinearLayoutItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */