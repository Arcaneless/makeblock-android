package cc.makeblock.makeblock.scene.panel;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.percent.PercentLayoutHelper.PercentLayoutInfo;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.PortSelectItemView;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;

public class PortSelectPopupWindow
  extends PopupWindow
  implements View.OnClickListener
{
  private View currentBoard;
  private PortSelectItemView currentSelectItem = null;
  private PortSelectItemView currentSelectSlot = null;
  private OnOkButtonClickListener mOnOkButtonClickListener;
  private boolean mShowSlot = false;
  private TextView mTitleText;
  private TextView port_select_board_name;
  private View select_port_board_mcore;
  private View select_port_board_me_auriga;
  private View select_port_board_megapi;
  private View select_port_board_orion;
  private View select_port_slot;
  private WidgetData widgetData;
  
  public PortSelectPopupWindow(View paramView, int paramInt1, int paramInt2, boolean paramBoolean, String paramString)
  {
    super(paramView, paramInt1, paramInt2, paramBoolean);
    this.select_port_slot = paramView.findViewById(2131296668);
    this.select_port_board_me_auriga = paramView.findViewById(2131296665);
    this.select_port_board_orion = paramView.findViewById(2131296667);
    this.select_port_board_mcore = paramView.findViewById(2131296664);
    this.select_port_board_megapi = paramView.findViewById(2131296666);
    this.port_select_board_name = ((TextView)paramView.findViewById(2131296624));
    this.select_port_board_orion.setVisibility(8);
    this.select_port_board_mcore.setVisibility(8);
    this.select_port_board_megapi.setVisibility(8);
    this.select_port_slot.setVisibility(8);
    this.select_port_board_me_auriga.setVisibility(8);
    switch (cc.makeblock.makeblock.engine.device.DeviceBoardName.valueOf(paramString))
    {
    }
    for (;;)
    {
      this.currentBoard.setVisibility(0);
      this.mTitleText = ((TextView)paramView.findViewById(2131296627));
      paramView.findViewById(2131296625).setOnClickListener(this);
      paramView.findViewById(2131296626).setOnClickListener(this);
      paramView.findViewById(2131296326).setOnClickListener(this);
      paramView.findViewById(2131296327).setOnClickListener(this);
      paramView.findViewById(2131296330).setOnClickListener(this);
      paramView.findViewById(2131296332).setOnClickListener(this);
      paramView.findViewById(2131296333).setOnClickListener(this);
      paramView.findViewById(2131296334).setOnClickListener(this);
      paramView.findViewById(2131296335).setOnClickListener(this);
      paramView.findViewById(2131296336).setOnClickListener(this);
      paramView.findViewById(2131296337).setOnClickListener(this);
      paramView.findViewById(2131296338).setOnClickListener(this);
      paramView.findViewById(2131296339).setOnClickListener(this);
      paramView.findViewById(2131296331).setOnClickListener(this);
      paramView.findViewById(2131296340).setOnClickListener(this);
      paramView.findViewById(2131296342).setOnClickListener(this);
      paramView.findViewById(2131296328).setOnClickListener(this);
      paramView.findViewById(2131296329).setOnClickListener(this);
      paramView.findViewById(2131296325).setOnClickListener(this);
      paramView.findViewById(2131296360).setOnClickListener(this);
      paramView.findViewById(2131296361).setOnClickListener(this);
      paramView.findViewById(2131296362).setOnClickListener(this);
      paramView.findViewById(2131296363).setOnClickListener(this);
      paramView.findViewById(2131296364).setOnClickListener(this);
      paramView.findViewById(2131296365).setOnClickListener(this);
      paramView.findViewById(2131296366).setOnClickListener(this);
      paramView.findViewById(2131296367).setOnClickListener(this);
      paramView.findViewById(2131296369).setOnClickListener(this);
      paramView.findViewById(2131296368).setOnClickListener(this);
      paramView.findViewById(2131296321).setOnClickListener(this);
      paramView.findViewById(2131296322).setOnClickListener(this);
      paramView.findViewById(2131296323).setOnClickListener(this);
      paramView.findViewById(2131296324).setOnClickListener(this);
      paramView.findViewById(2131296315).setOnClickListener(this);
      paramView.findViewById(2131296316).setOnClickListener(this);
      paramView.findViewById(2131296318).setOnClickListener(this);
      paramView.findViewById(2131296319).setOnClickListener(this);
      paramView.findViewById(2131296320).setOnClickListener(this);
      paramView.findViewById(2131296344).setOnClickListener(this);
      paramView.findViewById(2131296348).setOnClickListener(this);
      paramView.findViewById(2131296349).setOnClickListener(this);
      paramView.findViewById(2131296350).setOnClickListener(this);
      paramView.findViewById(2131296345).setOnClickListener(this);
      paramView.findViewById(2131296346).setOnClickListener(this);
      paramView.findViewById(2131296347).setOnClickListener(this);
      paramView.findViewById(2131296351).setOnClickListener(this);
      paramView.findViewById(2131296352).setOnClickListener(this);
      paramView.findViewById(2131296353).setOnClickListener(this);
      paramView.findViewById(2131296354).setOnClickListener(this);
      paramView.findViewById(2131296355).setOnClickListener(this);
      paramView.findViewById(2131296356).setOnClickListener(this);
      paramView.findViewById(2131296357).setOnClickListener(this);
      paramView.findViewById(2131296358).setOnClickListener(this);
      paramView.findViewById(2131296359).setOnClickListener(this);
      paramView.findViewById(2131296370).setOnClickListener(this);
      paramView.findViewById(2131296371).setOnClickListener(this);
      return;
      this.currentBoard = this.select_port_board_mcore;
      this.port_select_board_name.setText(2131493311);
      continue;
      this.currentBoard = this.select_port_board_orion;
      this.port_select_board_name.setText(2131493298);
      continue;
      this.currentBoard = this.select_port_board_me_auriga;
      this.port_select_board_name.setText(2131493297);
      continue;
      this.currentBoard = this.select_port_board_megapi;
      this.port_select_board_name.setText(2131493299);
    }
  }
  
  private void checkChildStatus(View paramView, WidgetData paramWidgetData)
  {
    if ((paramView instanceof ViewGroup))
    {
      int j = ((ViewGroup)paramView).getChildCount();
      int i = 0;
      while (i < j)
      {
        checkChildStatus(((ViewGroup)paramView).getChildAt(i), paramWidgetData);
        i += 1;
      }
    }
    if ((paramView instanceof PortSelectItemView))
    {
      ((PortSelectItemView)paramView).checkStatus(paramWidgetData);
      if (paramView.isSelected()) {
        checkSlot((PortSelectItemView)paramView);
      }
    }
  }
  
  private void checkSlot(PortSelectItemView paramPortSelectItemView)
  {
    boolean bool;
    if (paramPortSelectItemView.getSlot() == 0) {
      if (this.widgetData.slot != null) {
        bool = true;
      }
    }
    for (;;)
    {
      showSlot(bool);
      return;
      bool = false;
      continue;
      bool = false;
    }
  }
  
  private void clearSelected(View paramView)
  {
    if ((paramView instanceof ViewGroup))
    {
      int j = ((ViewGroup)paramView).getChildCount();
      int i = 0;
      while (i < j)
      {
        clearSelected(((ViewGroup)paramView).getChildAt(i));
        i += 1;
      }
    }
    paramView.setSelected(false);
  }
  
  public static PortSelectPopupWindow createPortSelectPopupWindow(Activity paramActivity, String paramString)
  {
    View localView1 = paramActivity.getLayoutInflater().inflate(2131427524, null);
    View localView2 = localView1.findViewById(2131296416);
    int i = (int)(ScreenHelper.SCREEN_WIDTH * 0.81458336F);
    localView2.setLayoutParams(new FrameLayout.LayoutParams(i, (int)(i * 1.0F / 1564.0F * 1024.0F), 17));
    paramString = new PortSelectPopupWindow(localView1, -1, -1, true, paramString);
    paramString.setTouchable(true);
    paramString.setOutsideTouchable(true);
    paramString.setBackgroundDrawable(new BitmapDrawable(paramActivity.getResources(), (Bitmap)null));
    return paramString;
  }
  
  private void onSelectedBoardItem(View paramView)
  {
    PortSelectItemView localPortSelectItemView;
    boolean bool;
    if ((paramView instanceof PortSelectItemView))
    {
      localPortSelectItemView = (PortSelectItemView)paramView;
      if (localPortSelectItemView.isSelected()) {
        break label55;
      }
      bool = true;
      if (!bool) {
        break label60;
      }
      clearSelected(this.currentBoard);
      checkSlot((PortSelectItemView)paramView);
    }
    label55:
    label60:
    for (this.currentSelectItem = ((PortSelectItemView)paramView);; this.currentSelectItem = null)
    {
      localPortSelectItemView.setSelected(bool);
      return;
      bool = false;
      break;
    }
  }
  
  private void onSelectedSlotItem(View paramView)
  {
    boolean bool;
    if ((paramView instanceof PortSelectItemView))
    {
      paramView = (PortSelectItemView)paramView;
      if (paramView.isSelected()) {
        break label44;
      }
      bool = true;
      if (!bool) {
        break label49;
      }
      clearSelected(this.select_port_slot);
    }
    label44:
    label49:
    for (this.currentSelectSlot = paramView;; this.currentSelectSlot = null)
    {
      paramView.setSelected(bool);
      return;
      bool = false;
      break;
    }
  }
  
  private void showSlot(boolean paramBoolean)
  {
    bool2 = true;
    Object localObject1;
    Object localObject2;
    if (paramBoolean) {
      if (this.mShowSlot != paramBoolean)
      {
        this.select_port_slot.setVisibility(0);
        localObject1 = (PercentRelativeLayout.LayoutParams)this.currentBoard.getLayoutParams();
        localObject2 = ((PercentRelativeLayout.LayoutParams)localObject1).getPercentLayoutInfo();
        ((PercentLayoutHelper.PercentLayoutInfo)localObject2).leftMarginPercent = ((float)(((PercentLayoutHelper.PercentLayoutInfo)localObject2).leftMarginPercent + 0.09D));
        this.currentBoard.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      }
    }
    try
    {
      i = Integer.parseInt(this.widgetData.slot);
      localObject1 = this.select_port_slot.findViewById(2131296370);
      localObject2 = this.select_port_slot.findViewById(2131296371);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        int i;
        boolean bool1;
        continue;
        if (i == 1)
        {
          bool1 = true;
          continue;
          if (i == 2) {
            bool1 = bool2;
          }
        }
      }
    }
    ((View)localObject1).setSelected(bool1);
    break label247;
    label112:
    ((View)localObject2).setSelected(bool1);
    if (((View)localObject1).isSelected()) {
      this.currentSelectSlot = ((PortSelectItemView)localObject1);
    }
    if (((View)localObject2).isSelected()) {
      this.currentSelectSlot = ((PortSelectItemView)localObject2);
    }
    for (;;)
    {
      this.mShowSlot = paramBoolean;
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label112;
      if (this.mShowSlot != paramBoolean)
      {
        this.select_port_slot.setVisibility(8);
        localObject1 = (PercentRelativeLayout.LayoutParams)this.currentBoard.getLayoutParams();
        localObject2 = ((PercentRelativeLayout.LayoutParams)localObject1).getPercentLayoutInfo();
        ((PercentLayoutHelper.PercentLayoutInfo)localObject2).leftMarginPercent = ((float)(((PercentLayoutHelper.PercentLayoutInfo)localObject2).leftMarginPercent - 0.09D));
        this.currentBoard.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      }
    }
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131296625: 
      dismiss();
      return;
    case 2131296626: 
      if (this.currentSelectItem != null) {
        this.widgetData.port = this.currentSelectItem.getPort();
      }
      if (this.currentSelectSlot != null) {
        this.widgetData.slot = (this.currentSelectSlot.getSlot() + "");
      }
      if (this.mOnOkButtonClickListener != null) {
        this.mOnOkButtonClickListener.onOkClick();
      }
      dismiss();
      return;
    case 2131296370: 
    case 2131296371: 
      onSelectedSlotItem(paramView);
      return;
    }
    onSelectedBoardItem(paramView);
  }
  
  public void setOnOkButtonClickListener(OnOkButtonClickListener paramOnOkButtonClickListener)
  {
    this.mOnOkButtonClickListener = paramOnOkButtonClickListener;
  }
  
  public void setWidgetData(WidgetData paramWidgetData)
  {
    this.widgetData = paramWidgetData;
    this.currentSelectSlot = null;
    this.currentSelectItem = null;
    clearSelected(getContentView());
    checkChildStatus(this.currentBoard, this.widgetData);
    this.mTitleText.setText(this.widgetData.name);
  }
  
  public static abstract interface OnOkButtonClickListener
  {
    public abstract void onOkClick();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\panel\PortSelectPopupWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */