package cc.makeblock.makeblock.scene.panel;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.widget.PopupWindow;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.databinding.PopupWidgetEditBinding;
import cc.makeblock.makeblock.viewmodel.WidgetEditPopupViewModel;

public class WidgetEditPopupWindow
  extends PopupWindow
{
  public static final int SHOW_ARROW_BOTTOM = 2;
  public static final int SHOW_ARROW_LEFT = 3;
  public static final int SHOW_ARROW_NONE = 4;
  public static final int SHOW_ARROW_RIGHT = 1;
  public static final int SHOW_ARROW_TOP = 0;
  private PopupWidgetEditBinding binding;
  private OnEnterBlocklyListener onEnterBlocklyListener;
  private OnPortSelectListener onPortSelectListener;
  private OnWidgetDeleteListener onWidgetDeleteListener;
  private OnWidgetRenameListener onWidgetRenameListener;
  
  public WidgetEditPopupWindow(Context paramContext, WidgetData paramWidgetData)
  {
    this.binding = ((PopupWidgetEditBinding)DataBindingUtil.inflate(LayoutInflater.from(paramContext), 2131427527, null, false));
    setContentView(this.binding.getRoot());
    this.binding.setViewModel(new WidgetEditPopupViewModel(this, paramWidgetData));
  }
  
  public void onEnterBlockly()
  {
    this.onEnterBlocklyListener.onEnterBlockly();
  }
  
  public void onPortSelect()
  {
    this.onPortSelectListener.onPortSelect();
  }
  
  public void onWidgetDelete()
  {
    this.onWidgetDeleteListener.onWidgetDelete();
  }
  
  public void onWidgetRename()
  {
    this.onWidgetRenameListener.onWidgetRename();
  }
  
  public void setArrowDirection(int paramInt)
  {
    this.binding.getViewModel().setArrowDirection(paramInt);
  }
  
  public void setDeletable(boolean paramBoolean)
  {
    this.binding.getViewModel().setDeletable(paramBoolean);
  }
  
  public void setNameChangeable(boolean paramBoolean)
  {
    this.binding.getViewModel().setNameChangeable(paramBoolean);
  }
  
  public void setOnEnterBlocklyListener(OnEnterBlocklyListener paramOnEnterBlocklyListener)
  {
    this.onEnterBlocklyListener = paramOnEnterBlocklyListener;
  }
  
  public void setOnPortSelectListener(OnPortSelectListener paramOnPortSelectListener)
  {
    this.onPortSelectListener = paramOnPortSelectListener;
  }
  
  public void setOnWidgetDeleteListener(OnWidgetDeleteListener paramOnWidgetDeleteListener)
  {
    this.onWidgetDeleteListener = paramOnWidgetDeleteListener;
  }
  
  public void setOnWidgetRenameListener(OnWidgetRenameListener paramOnWidgetRenameListener)
  {
    this.onWidgetRenameListener = paramOnWidgetRenameListener;
  }
  
  public void setPortSelectable(boolean paramBoolean)
  {
    this.binding.getViewModel().setPortSelectable(paramBoolean);
  }
  
  public void setProgrammable(boolean paramBoolean)
  {
    this.binding.getViewModel().setProgrammable(paramBoolean);
  }
  
  public static abstract interface OnEnterBlocklyListener
  {
    public abstract void onEnterBlockly();
  }
  
  public static abstract interface OnPortSelectListener
  {
    public abstract void onPortSelect();
  }
  
  public static abstract interface OnWidgetDeleteListener
  {
    public abstract void onWidgetDelete();
  }
  
  public static abstract interface OnWidgetRenameListener
  {
    public abstract void onWidgetRename();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\panel\WidgetEditPopupWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */