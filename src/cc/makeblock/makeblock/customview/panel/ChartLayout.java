package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
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

public class ChartLayout
  extends CellView
{
  private static final String TAG = "ChartLayout";
  private ChartView chartView;
  private TextView tv_title;
  
  public ChartLayout(Context paramContext)
  {
    super(paramContext);
    init(null, 0);
  }
  
  public ChartLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet, 0);
  }
  
  public ChartLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet, paramInt);
  }
  
  private void init(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427537, null);
    addView(paramAttributeSet, new FrameLayout.LayoutParams(-1, -1));
    this.chartView = ((ChartView)paramAttributeSet.findViewById(2131296392));
    this.tv_title = ((TextView)paramAttributeSet.findViewById(2131296745));
  }
  
  public void addValue(int paramInt)
  {
    this.chartView.addValue(paramInt);
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
    if ((!TextUtils.isEmpty(this.widgetData.icon)) && (AssetsUtils.getImageFromAssetsFile(getContext(), this.widgetData.icon) != null)) {
      this.chartView.setIcon(AssetsUtils.getImageFromAssetsFile(getContext(), this.widgetData.icon));
    }
    if (!TextUtils.isEmpty(this.widgetData.name)) {
      this.tv_title.setText(this.widgetData.name);
    }
  }
  
  protected void sendState(Object paramObject)
  {
    throw new RuntimeException("按目前的逻辑,ChartLayout不应该发数据");
  }
  
  public void setControlListener() {}
  
  public boolean setWidgetValue(SendValueToWidgetJson paramSendValueToWidgetJson)
  {
    if (Integer.parseInt(paramSendValueToWidgetJson.getId()) == this.widgetData.widgetID)
    {
      addValue((int)Float.parseFloat(paramSendValueToWidgetJson.getValue()));
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\ChartLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */