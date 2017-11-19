package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.cell.CellLayout;
import cc.makeblock.makeblock.customview.cell.CellView;

public class ButtonView
  extends CellView<ButtonState>
{
  private static final String TAG = "ButtonView";
  private ButtonViewListener buttonViewListener;
  private boolean pressed;
  private TextView tv_subTitle;
  
  public ButtonView(Context paramContext)
  {
    super(paramContext);
    init(null, 0, paramContext);
  }
  
  public ButtonView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(null, 0, paramContext);
  }
  
  public ButtonView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(null, 0, paramContext);
  }
  
  private void init(AttributeSet paramAttributeSet, int paramInt, Context paramContext)
  {
    paramAttributeSet = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427536, null);
    addView(paramAttributeSet);
    this.tv_subTitle = ((TextView)findViewById(2131296744));
    paramAttributeSet.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction() & 0xFF)
        {
        }
        do
        {
          do
          {
            return true;
            this.val$layout_bg.setSelected(true);
            ButtonView.access$002(ButtonView.this, true);
          } while (ButtonView.this.buttonViewListener == null);
          ButtonView.this.buttonViewListener.onButtonStateChanged(true);
          return true;
          ButtonView.access$002(ButtonView.this, false);
          this.val$layout_bg.setSelected(false);
        } while (ButtonView.this.buttonViewListener == null);
        ButtonView.this.buttonViewListener.onButtonStateChanged(false);
        return true;
      }
    });
  }
  
  public boolean getPortSelectable()
  {
    return true;
  }
  
  public boolean getProgrammable()
  {
    return true;
  }
  
  public int getValue()
  {
    if (isPressed()) {
      return 1;
    }
    return 0;
  }
  
  public boolean isPressed()
  {
    return this.pressed;
  }
  
  public void notifyWidgetBeanChanged()
  {
    if (!TextUtils.isEmpty(this.widgetData.name)) {
      this.tv_subTitle.setText(this.widgetData.name);
    }
  }
  
  protected void sendState(ButtonState paramButtonState)
  {
    WidgetData localWidgetData = getWidgetData();
    CellLayout localCellLayout = this.cellLayout;
    int j = localWidgetData.widgetID;
    if (paramButtonState.pressed) {}
    for (int i = 1;; i = 0)
    {
      localCellLayout.sendWidgetValue(j, i);
      return;
    }
  }
  
  public void setButtonViewListener(ButtonViewListener paramButtonViewListener)
  {
    this.buttonViewListener = paramButtonViewListener;
  }
  
  public void setControlListener()
  {
    setButtonViewListener(new ButtonViewListener()
    {
      public void onButtonStateChanged(boolean paramAnonymousBoolean)
      {
        ButtonView.this.pushState(new ButtonView.ButtonState(ButtonView.this, paramAnonymousBoolean));
      }
    });
  }
  
  public class ButtonState
  {
    private boolean pressed;
    
    public ButtonState(boolean paramBoolean)
    {
      this.pressed = paramBoolean;
    }
  }
  
  public static abstract interface ButtonViewListener
  {
    public abstract void onButtonStateChanged(boolean paramBoolean);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\ButtonView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */