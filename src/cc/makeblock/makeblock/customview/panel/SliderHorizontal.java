package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.cell.CellLayout;
import cc.makeblock.makeblock.customview.cell.CellView;
import cc.makeblock.makeblock.customview.panel.seekbar.HorizontalSeekBar;
import cc.makeblock.makeblock.customview.panel.seekbar.OnSeekBarValueChangeListener;
import cc.makeblock.makeblock.engine.utils.AssetsUtils;

public class SliderHorizontal
  extends CellView<SliderState>
  implements OnSeekBarValueChangeListener
{
  private HorizontalSeekBar horizontalSeekBar;
  private ImageView img_icon;
  private OnSliderValueChangeListener onSliderValueChangeListener;
  private TextView slider_value;
  private TextView tv_title;
  private float value;
  
  public SliderHorizontal(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0);
  }
  
  public SliderHorizontal(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0);
  }
  
  public SliderHorizontal(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
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
    paramContext = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427542, null);
    addView(paramContext, new FrameLayout.LayoutParams(-1, -1));
    this.slider_value = ((TextView)paramContext.findViewById(2131296677));
    this.horizontalSeekBar = ((HorizontalSeekBar)paramContext.findViewById(2131296486));
    this.slider_value.setText(formatValue(this.horizontalSeekBar.getValue()));
    this.horizontalSeekBar.setOnValueChangeListener(this);
    this.img_icon = ((ImageView)paramContext.findViewById(2131296506));
    this.tv_title = ((TextView)paramContext.findViewById(2131296745));
  }
  
  private void setValue(float paramFloat)
  {
    this.value = paramFloat;
    this.slider_value.setText(formatValue(paramFloat));
    this.horizontalSeekBar.setValue(paramFloat);
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
    return (int)this.value;
  }
  
  public void notifyWidgetBeanChanged()
  {
    this.img_icon.setImageBitmap(AssetsUtils.getImageFromAssetsFile(getContext(), this.widgetData.icon));
    if (!TextUtils.isEmpty(this.widgetData.name)) {
      this.tv_title.setText(this.widgetData.name);
    }
  }
  
  public void onSeekBarValueChange(float paramFloat)
  {
    this.value = paramFloat;
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
        SliderHorizontal.this.pushState(new SliderHorizontal.SliderState(SliderHorizontal.this, paramAnonymousFloat));
      }
    });
  }
  
  public void setMode(int paramInt)
  {
    super.setMode(paramInt);
    if (paramInt == 2) {
      post(new Runnable()
      {
        public void run()
        {
          SliderHorizontal.this.setValue(0.0F);
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\SliderHorizontal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */