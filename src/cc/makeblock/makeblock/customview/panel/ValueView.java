package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.cell.CellView;
import cc.makeblock.makeblock.engine.protocol.web.receive.SendValueToWidgetJson;
import cc.makeblock.makeblock.engine.utils.AssetsUtils;

public class ValueView
  extends CellView
{
  private static final String TAG = "ValueView";
  private Bitmap bitmapNor;
  private Bitmap bitmapPressed;
  private TextView tv_title;
  private TextView tv_value;
  private TextView tv_value_shadow;
  
  public ValueView(Context paramContext)
  {
    super(paramContext);
    init(null, 0, paramContext);
  }
  
  public ValueView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(null, 0, paramContext);
  }
  
  public ValueView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(null, 0, paramContext);
  }
  
  private void init(AttributeSet paramAttributeSet, int paramInt, Context paramContext)
  {
    paramAttributeSet = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427569, null);
    addView(paramAttributeSet, new FrameLayout.LayoutParams(-1, -1));
    this.tv_value = ((TextView)paramAttributeSet.findViewById(2131296750));
    this.tv_value_shadow = ((TextView)paramAttributeSet.findViewById(2131296751));
    paramContext = Typeface.createFromAsset(paramContext.getAssets(), "font/diode.TTF");
    this.tv_value.setTypeface(paramContext);
    this.tv_value_shadow.setTypeface(paramContext);
    this.tv_title = ((TextView)paramAttributeSet.findViewById(2131296745));
    setValue(0);
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
    this.bitmapNor = AssetsUtils.getImageFromAssetsFile(getContext(), this.widgetData.icon);
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
      post(new Runnable()
      {
        public void run()
        {
          ValueView.this.setValue(0);
        }
      });
    }
  }
  
  public void setValue(int paramInt)
  {
    if ((paramInt < -9) && (paramInt > -100))
    {
      this.tv_value.setText(String.valueOf("  " + paramInt));
      return;
    }
    if ((paramInt < 0) && (paramInt > -10))
    {
      this.tv_value.setText(String.valueOf("    " + paramInt));
      return;
    }
    this.tv_value.setText(String.format("%04d", new Object[] { Integer.valueOf(paramInt) }));
  }
  
  public boolean setWidgetValue(SendValueToWidgetJson paramSendValueToWidgetJson)
  {
    if (Integer.parseInt(paramSendValueToWidgetJson.getId()) == this.widgetData.widgetID)
    {
      float f = Float.parseFloat(paramSendValueToWidgetJson.getValue());
      if (f > 9999.0F)
      {
        setValue(9999);
        return true;
      }
      if (f < -999.0F)
      {
        setValue(64537);
        return true;
      }
      setValue((int)f);
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\ValueView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */