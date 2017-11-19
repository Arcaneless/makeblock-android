package cc.makeblock.makeblock.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.scene.panel.WidgetEditPopupWindow;

public class WidgetEditPopupViewModel
  extends BaseObservable
{
  private int arrowDirection = 0;
  private boolean deletable = false;
  private WidgetData mWidgetData;
  private WidgetEditPopupWindow mWidgetEditPopupWindow;
  private boolean nameChangeable = false;
  private boolean portSelectable = false;
  private boolean programmable = false;
  
  public WidgetEditPopupViewModel(WidgetEditPopupWindow paramWidgetEditPopupWindow, WidgetData paramWidgetData)
  {
    this.mWidgetEditPopupWindow = paramWidgetEditPopupWindow;
    this.mWidgetData = paramWidgetData;
  }
  
  public void delete(View paramView)
  {
    this.mWidgetEditPopupWindow.dismiss();
    this.mWidgetEditPopupWindow.onWidgetDelete();
  }
  
  public void enterBlockly(View paramView)
  {
    this.mWidgetEditPopupWindow.dismiss();
    this.mWidgetEditPopupWindow.onEnterBlockly();
  }
  
  @Bindable
  public int getArrowDirection()
  {
    return this.arrowDirection;
  }
  
  @Bindable
  public String getWidgetPopupTitle()
  {
    return this.mWidgetData.name;
  }
  
  @Bindable
  public boolean isDeletable()
  {
    return this.deletable;
  }
  
  @Bindable
  public boolean isNameChangeable()
  {
    return this.nameChangeable;
  }
  
  @Bindable
  public boolean isPortSelectable()
  {
    return this.portSelectable;
  }
  
  @Bindable
  public boolean isProgrammable()
  {
    return this.programmable;
  }
  
  public void rename(View paramView)
  {
    this.mWidgetEditPopupWindow.dismiss();
    this.mWidgetEditPopupWindow.onWidgetRename();
  }
  
  public void selectPort(View paramView)
  {
    this.mWidgetEditPopupWindow.dismiss();
    this.mWidgetEditPopupWindow.onPortSelect();
  }
  
  public void setArrowDirection(int paramInt)
  {
    this.arrowDirection = paramInt;
  }
  
  public void setDeletable(boolean paramBoolean)
  {
    this.deletable = paramBoolean;
  }
  
  public void setNameChangeable(boolean paramBoolean)
  {
    this.nameChangeable = paramBoolean;
  }
  
  public void setPortSelectable(boolean paramBoolean)
  {
    this.portSelectable = paramBoolean;
  }
  
  public void setProgrammable(boolean paramBoolean)
  {
    this.programmable = paramBoolean;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\WidgetEditPopupViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */