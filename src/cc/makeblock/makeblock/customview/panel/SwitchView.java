package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.cell.CellLayout;
import cc.makeblock.makeblock.customview.cell.CellView;

public class SwitchView
  extends CellView<DraggableSwitch.SwitchState>
{
  private DraggableSwitch draggableSwitch;
  private TextView tv_title;
  
  public SwitchView(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  private void init()
  {
    View localView = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427564, null);
    this.draggableSwitch = ((DraggableSwitch)localView.findViewById(2131296628));
    this.tv_title = ((TextView)localView.findViewById(2131296745));
    addView(localView, new FrameLayout.LayoutParams(-1, -1));
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
    if (this.draggableSwitch.isChecked()) {
      return 1;
    }
    return 0;
  }
  
  public void notifyWidgetBeanChanged()
  {
    if (!TextUtils.isEmpty(this.widgetData.name)) {
      this.tv_title.setText(this.widgetData.name);
    }
  }
  
  protected void sendState(DraggableSwitch.SwitchState paramSwitchState)
  {
    CellLayout localCellLayout = this.cellLayout;
    int j = this.widgetData.widgetID;
    if (paramSwitchState.isChecked) {}
    for (int i = 1;; i = 0)
    {
      localCellLayout.sendWidgetValue(j, i);
      return;
    }
  }
  
  public void setControlListener()
  {
    this.draggableSwitch.setOnCheckedChangeListener(new DraggableSwitch.OnCheckedChangeListener()
    {
      public void onCheckedChanged(DraggableSwitch paramAnonymousDraggableSwitch, boolean paramAnonymousBoolean)
      {
        SwitchView.this.pushState(new DraggableSwitch.SwitchState(paramAnonymousBoolean));
      }
    });
  }
  
  public void setMode(int paramInt)
  {
    super.setMode(paramInt);
    if (paramInt == 2) {
      this.draggableSwitch.setChecked(false);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\SwitchView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */