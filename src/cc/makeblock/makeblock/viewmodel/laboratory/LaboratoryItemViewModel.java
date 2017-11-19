package cc.makeblock.makeblock.viewmodel.laboratory;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import cc.makeblock.makeblock.customview.laboratory.LaboratoryPanelLayout.CellViewWrap;
import cc.makeblock.makeblock.customview.laboratory.LaboratoryView;
import cc.makeblock.makeblock.customview.laboratory.LaboratoryWidgetFactory;
import cc.makeblock.makeblock.engine.diy.DiyProjectBean;
import cc.makeblock.makeblock.engine.diy.Widget;
import java.util.ArrayList;
import java.util.List;

public class LaboratoryItemViewModel
  extends BaseObservable
{
  private Context mActivity;
  private List<LaboratoryPanelLayout.CellViewWrap> mCellViewWraps;
  private DiyProjectBean mProjectBean;
  
  public LaboratoryItemViewModel(DiyProjectBean paramDiyProjectBean, Context paramContext, List<LaboratoryPanelLayout.CellViewWrap> paramList)
  {
    this.mActivity = paramContext;
    this.mProjectBean = paramDiyProjectBean;
    this.mCellViewWraps = new ArrayList();
    List localList = this.mProjectBean.widgets;
    int j = localList.size();
    int i = 0;
    while (i < j)
    {
      Widget localWidget = (Widget)localList.get(i);
      paramContext = getCacheView(paramList, localWidget.type);
      paramDiyProjectBean = paramContext;
      if (paramContext == null) {
        paramDiyProjectBean = LaboratoryWidgetFactory.createWidgetView(this.mActivity, localWidget.type);
      }
      ((LaboratoryView)paramDiyProjectBean).setMode(2);
      this.mCellViewWraps.add(new LaboratoryPanelLayout.CellViewWrap(paramDiyProjectBean, localWidget.type, getIndex(localWidget.x, localWidget.y), ((LaboratoryView)paramDiyProjectBean).getSizePercent()));
      i += 1;
    }
    notifyPropertyChanged(86);
  }
  
  private View getCacheView(List<LaboratoryPanelLayout.CellViewWrap> paramList, String paramString)
  {
    if (paramList == null) {}
    for (;;)
    {
      return null;
      int j = paramList.size();
      int i = 0;
      while (i < j)
      {
        LaboratoryPanelLayout.CellViewWrap localCellViewWrap = (LaboratoryPanelLayout.CellViewWrap)paramList.get(i);
        if (localCellViewWrap.type.equals(paramString))
        {
          paramList.remove(i);
          return localCellViewWrap.view;
        }
        i += 1;
      }
    }
  }
  
  private int getIndex(int paramInt1, int paramInt2)
  {
    return paramInt1 * 4 + paramInt2;
  }
  
  @Bindable
  public List<LaboratoryPanelLayout.CellViewWrap> getWidgets()
  {
    return this.mCellViewWraps;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\laboratory\LaboratoryItemViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */