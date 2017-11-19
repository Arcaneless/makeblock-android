package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.cell.CellView;
import cc.makeblock.makeblock.databinding.WidgetAirDashBoardBinding;
import cc.makeblock.makeblock.engine.protocol.web.receive.SendValueToWidgetJson;
import cc.makeblock.makeblock.viewmodel.panel.AirBlockBoardViewModel;

public class AirBlockBoardView
  extends CellView
{
  private static final String TAG = AirBlockBoardView.class.getSimpleName();
  WidgetAirDashBoardBinding binding;
  
  public AirBlockBoardView(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  public AirBlockBoardView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  public AirBlockBoardView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  private void init()
  {
    this.binding = ((WidgetAirDashBoardBinding)DataBindingUtil.inflate((LayoutInflater)getContext().getSystemService("layout_inflater"), 2131427534, null, false));
    setViewModel(new AirBlockBoardViewModel());
    addView(this.binding.getRoot());
  }
  
  public boolean getPortSelectable()
  {
    return false;
  }
  
  public boolean getProgrammable()
  {
    return false;
  }
  
  protected void sendState(Object paramObject) {}
  
  public void setControlListener() {}
  
  public void setViewModel(AirBlockBoardViewModel paramAirBlockBoardViewModel)
  {
    this.binding.setViewModel(paramAirBlockBoardViewModel);
  }
  
  public boolean setWidgetValue(SendValueToWidgetJson paramSendValueToWidgetJson)
  {
    if (Integer.parseInt(paramSendValueToWidgetJson.getId()) == this.widgetData.widgetID)
    {
      this.binding.getViewModel().updateAirBoardState(paramSendValueToWidgetJson.getValue());
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\AirBlockBoardView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */