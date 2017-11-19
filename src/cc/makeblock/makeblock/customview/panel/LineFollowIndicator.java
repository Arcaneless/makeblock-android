package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.customview.CheckableAutoResizeTextView;
import cc.makeblock.makeblock.customview.cell.CellView;
import cc.makeblock.makeblock.engine.protocol.web.receive.SendValueToWidgetJson;

public class LineFollowIndicator
  extends CellView
{
  private int state = 0;
  private ImageView state_background_left;
  private ImageView state_background_right;
  private CheckableAutoResizeTextView text_on_left;
  private CheckableAutoResizeTextView text_on_right;
  private AutoResizeTextView tv_title;
  
  public LineFollowIndicator(Context paramContext)
  {
    super(paramContext);
    initViews();
  }
  
  private void initViews()
  {
    addView(((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427551, null), new FrameLayout.LayoutParams(-1, -1));
    this.state_background_left = ((ImageView)findViewById(2131296690));
    this.state_background_right = ((ImageView)findViewById(2131296691));
    this.tv_title = ((AutoResizeTextView)findViewById(2131296745));
    this.text_on_left = ((CheckableAutoResizeTextView)findViewById(2131296742));
    this.text_on_right = ((CheckableAutoResizeTextView)findViewById(2131296743));
    updateState();
  }
  
  private void setStateToOne()
  {
    this.state_background_left.setSelected(false);
    this.text_on_left.setChecked(false);
    this.state_background_right.setSelected(true);
    this.text_on_right.setChecked(true);
  }
  
  private void setStateToThree()
  {
    this.state_background_left.setSelected(true);
    this.text_on_left.setChecked(true);
    this.state_background_right.setSelected(true);
    this.text_on_right.setChecked(true);
  }
  
  private void setStateToTwo()
  {
    this.state_background_left.setSelected(true);
    this.text_on_left.setChecked(true);
    this.state_background_right.setSelected(false);
    this.text_on_right.setChecked(false);
  }
  
  private void setStateToZero()
  {
    this.state_background_left.setSelected(false);
    this.text_on_left.setChecked(false);
    this.state_background_right.setSelected(false);
    this.text_on_right.setChecked(false);
  }
  
  private void updateState()
  {
    switch (this.state)
    {
    default: 
      return;
    case 0: 
      setStateToZero();
      return;
    case 1: 
      setStateToOne();
      return;
    case 2: 
      setStateToTwo();
      return;
    }
    setStateToThree();
  }
  
  public boolean getPortSelectable()
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
  
  public boolean setWidgetValue(SendValueToWidgetJson paramSendValueToWidgetJson)
  {
    if (Integer.parseInt(paramSendValueToWidgetJson.getId()) == this.widgetData.widgetID)
    {
      this.state = Integer.parseInt(paramSendValueToWidgetJson.getValue());
      updateState();
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\LineFollowIndicator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */