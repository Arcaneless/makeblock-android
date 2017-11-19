package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class SwitchTabRadioLinearLayout
  extends RadioLinearLayout
{
  private RadioLinearLayoutItem mFirstItem;
  private CheckableAutoResizeTextView mFirstTab;
  private RadioLinearLayoutItem mSecondItem;
  private CheckableAutoResizeTextView mSecondTab;
  
  public SwitchTabRadioLinearLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public SwitchTabRadioLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public SwitchTabRadioLinearLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private void adjustTextSize()
  {
    if ((this.mFirstTab != null) && (this.mSecondTab != null))
    {
      float f1 = this.mFirstTab.getTextSize();
      float f2 = this.mSecondTab.getTextSize();
      if (f1 != f2) {
        post(new Runnable()
        {
          public void run()
          {
            SwitchTabRadioLinearLayout.this.mFirstTab.setTextSize(this.val$minTextSize);
            SwitchTabRadioLinearLayout.this.mSecondTab.setTextSize(this.val$minTextSize);
          }
        });
      }
    }
  }
  
  protected void dispatchDraw(Canvas paramCanvas)
  {
    adjustTextSize();
    super.dispatchDraw(paramCanvas);
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mFirstTab = ((CheckableAutoResizeTextView)findViewById(2131296698));
    this.mSecondTab = ((CheckableAutoResizeTextView)findViewById(2131296700));
    this.mFirstItem = ((RadioLinearLayoutItem)findViewById(2131296697));
    this.mSecondItem = ((RadioLinearLayoutItem)findViewById(2131296699));
    if ((this.mFirstItem != null) && (this.mSecondItem != null))
    {
      this.mFirstItem.setOnTouchStatusListener(new RadioLinearLayoutItem.OnTouchStatusListener()
      {
        public void onTouchStatus(View paramAnonymousView, int paramAnonymousInt)
        {
          if (SwitchTabRadioLinearLayout.this.mFirstItem.isChecked()) {}
          do
          {
            return;
            if (paramAnonymousInt == 0)
            {
              SwitchTabRadioLinearLayout.this.mFirstItem.setBackgroundResource(2131165367);
              SwitchTabRadioLinearLayout.this.mFirstTab.setChecked(true);
              SwitchTabRadioLinearLayout.this.mSecondItem.setBackground(null);
              SwitchTabRadioLinearLayout.this.mSecondTab.setChecked(false);
              return;
            }
          } while (paramAnonymousInt == 1);
          SwitchTabRadioLinearLayout.this.mFirstItem.setBackground(null);
          SwitchTabRadioLinearLayout.this.mFirstTab.setChecked(false);
          SwitchTabRadioLinearLayout.this.mSecondItem.setBackgroundResource(2131165367);
          SwitchTabRadioLinearLayout.this.mSecondTab.setChecked(true);
        }
      });
      this.mSecondItem.setOnTouchStatusListener(new RadioLinearLayoutItem.OnTouchStatusListener()
      {
        public void onTouchStatus(View paramAnonymousView, int paramAnonymousInt)
        {
          if (SwitchTabRadioLinearLayout.this.mSecondItem.isChecked()) {}
          do
          {
            return;
            if (paramAnonymousInt == 0)
            {
              SwitchTabRadioLinearLayout.this.mSecondItem.setBackgroundResource(2131165367);
              SwitchTabRadioLinearLayout.this.mSecondTab.setChecked(true);
              SwitchTabRadioLinearLayout.this.mFirstItem.setBackground(null);
              SwitchTabRadioLinearLayout.this.mFirstTab.setChecked(false);
              return;
            }
          } while (paramAnonymousInt == 1);
          SwitchTabRadioLinearLayout.this.mSecondItem.setBackground(null);
          SwitchTabRadioLinearLayout.this.mSecondTab.setChecked(false);
          SwitchTabRadioLinearLayout.this.mFirstItem.setBackgroundResource(2131165367);
          SwitchTabRadioLinearLayout.this.mFirstTab.setChecked(true);
        }
      });
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\SwitchTabRadioLinearLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */