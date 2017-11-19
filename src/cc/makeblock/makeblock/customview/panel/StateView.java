package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.cell.CellView;
import cc.makeblock.makeblock.engine.protocol.web.receive.SendValueToWidgetJson;

public class StateView
  extends CellView
{
  private static final String TAG = "StateView";
  private ImageView img_icon;
  private boolean isON;
  private TextView tv_off;
  private TextView tv_on;
  private TextView tv_title;
  
  public StateView(Context paramContext)
  {
    super(paramContext);
    init(null, 0);
  }
  
  public StateView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet, 0);
  }
  
  public StateView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet, paramInt);
  }
  
  private void init(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427563, null);
    addView(paramAttributeSet, new FrameLayout.LayoutParams(-1, -1));
    this.img_icon = ((ImageView)paramAttributeSet.findViewById(2131296506));
    this.tv_title = ((TextView)findViewById(2131296745));
    this.tv_on = ((TextView)findViewById(2131296741));
    this.tv_off = ((TextView)findViewById(2131296740));
    setState(false);
    paramAttributeSet.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        boolean bool = true;
        if (StateView.this.getCurrentMode() != 1) {
          bool = false;
        }
        return bool;
      }
    });
  }
  
  private void setState(boolean paramBoolean)
  {
    this.isON = paramBoolean;
    if (paramBoolean)
    {
      this.tv_on.setVisibility(0);
      this.tv_off.setVisibility(4);
    }
    for (;;)
    {
      this.img_icon.setSelected(paramBoolean);
      return;
      this.tv_on.setVisibility(4);
      this.tv_off.setVisibility(0);
    }
  }
  
  public boolean getPortSelectable()
  {
    return true;
  }
  
  public boolean getProgrammable()
  {
    return true;
  }
  
  public void notifyWidgetBeanChanged()
  {
    if (!TextUtils.isEmpty(this.widgetData.name)) {
      this.tv_title.setText(this.widgetData.name);
    }
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
  }
  
  protected void sendState(Object paramObject) {}
  
  public void setControlListener() {}
  
  public void setMode(int paramInt)
  {
    super.setMode(paramInt);
    if (paramInt == 2) {
      setState(false);
    }
  }
  
  public boolean setWidgetValue(SendValueToWidgetJson paramSendValueToWidgetJson)
  {
    boolean bool = false;
    if (Integer.parseInt(paramSendValueToWidgetJson.getId()) == this.widgetData.widgetID)
    {
      if ((int)Float.parseFloat(paramSendValueToWidgetJson.getValue()) != 0) {
        bool = true;
      }
      setState(bool);
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\StateView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */