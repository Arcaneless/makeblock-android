package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.cell.CellLayout;
import cc.makeblock.makeblock.customview.cell.CellView;
import cc.makeblock.makeblock.customview.panel.seekbar.DuplexSeekBar;
import cc.makeblock.makeblock.customview.panel.seekbar.OnSeekBarValueChangeListener;

public class SliderDuplex
  extends CellView<SliderState>
  implements OnSeekBarValueChangeListener
{
  private OnSliderValueChangeListener onSliderValueChangeListener;
  private DuplexSeekBar slider_duplex;
  private TextView slider_value;
  private int value;
  
  public SliderDuplex(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0);
  }
  
  public SliderDuplex(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0);
  }
  
  public SliderDuplex(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt);
  }
  
  private String formatValue(float paramFloat)
  {
    return (int)paramFloat + "";
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    paramContext = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427541, null);
    addView(paramContext, new FrameLayout.LayoutParams(-1, -1));
    this.slider_value = ((TextView)paramContext.findViewById(2131296677));
    this.slider_duplex = ((DuplexSeekBar)paramContext.findViewById(2131296675));
    this.slider_value.setText(formatValue(this.slider_duplex.getValue()));
    this.slider_duplex.setOnValueChangeListener(this);
  }
  
  private void setValue(int paramInt)
  {
    this.value = paramInt;
    this.slider_value.setText(formatValue(paramInt));
    this.slider_duplex.setValue(paramInt);
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
    return this.value;
  }
  
  public void notifyWidgetBeanChanged()
  {
    if (!TextUtils.isEmpty(this.widgetData.name)) {
      ((TextView)findViewById(2131296676)).setText(this.widgetData.name);
    }
  }
  
  public void onSeekBarValueChange(float paramFloat)
  {
    this.value = ((int)paramFloat);
    this.slider_value.setText(formatValue(paramFloat));
    if (this.onSliderValueChangeListener != null) {
      this.onSliderValueChangeListener.onSliderValueChange(paramFloat);
    }
  }
  
  protected void sendState(SliderState paramSliderState)
  {
    this.cellLayout.sendWidgetValue(this.widgetData.widgetID, (int)paramSliderState.value);
  }
  
  public void setControlListener()
  {
    setOnSliderValueChangeListener(new OnSliderValueChangeListener()
    {
      public void onSliderValueChange(float paramAnonymousFloat)
      {
        SliderDuplex.this.pushState(new SliderDuplex.SliderState(SliderDuplex.this, paramAnonymousFloat));
      }
    });
  }
  
  public void setMode(int paramInt)
  {
    super.setMode(paramInt);
    if (paramInt == 2)
    {
      this.value = 0;
      post(new Runnable()
      {
        public void run()
        {
          SliderDuplex.this.setValue(0);
        }
      });
    }
  }
  
  public void setOnSliderValueChangeListener(OnSliderValueChangeListener paramOnSliderValueChangeListener)
  {
    this.onSliderValueChangeListener = paramOnSliderValueChangeListener;
  }
  
  public class SliderState
  {
    public float value;
    
    public SliderState(float paramFloat)
    {
      this.value = paramFloat;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\SliderDuplex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */