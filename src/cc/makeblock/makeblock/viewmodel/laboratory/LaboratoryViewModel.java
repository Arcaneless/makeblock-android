package cc.makeblock.makeblock.viewmodel.laboratory;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.diy.DiyProjectBean;
import cc.makeblock.makeblock.engine.diy.database.AppDatabase;
import cc.makeblock.makeblock.engine.diy.database.DiyProjectDao;
import cc.makeblock.makeblock.engine.diy.database.DiyProjectEntity;
import cc.makeblock.makeblock.engine.utils.JsonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LaboratoryViewModel
  extends BaseObservable
{
  public final ObservableBoolean hasLaboratory = new ObservableBoolean(true);
  private LiveData<List<DiyProjectBean>> mListLiveData;
  private MutableLiveData<List<DiyProjectBean>> mProjectBeans = new MutableLiveData();
  private LiveData<List<DiyProjectEntity>> mProjectEntity;
  private LaboratoryView mView;
  
  public LaboratoryViewModel(LaboratoryView paramLaboratoryView)
  {
    this.mView = paramLaboratoryView;
    initData();
  }
  
  private void initData()
  {
    this.mProjectEntity = ControllerManager.getInstance().getAppDatabase().diyProjectDao().getAllAsync();
    this.mListLiveData = Transformations.switchMap(this.mProjectEntity, new Function()
    {
      public LiveData<List<DiyProjectBean>> apply(List<DiyProjectEntity> paramAnonymousList)
      {
        if ((paramAnonymousList == null) || (paramAnonymousList.size() == 0))
        {
          LaboratoryViewModel.this.mProjectBeans.setValue(null);
          LaboratoryViewModel.this.hasLaboratory.set(false);
          return LaboratoryViewModel.this.mProjectBeans;
        }
        ArrayList localArrayList = new ArrayList();
        paramAnonymousList = paramAnonymousList.iterator();
        while (paramAnonymousList.hasNext())
        {
          DiyProjectEntity localDiyProjectEntity = (DiyProjectEntity)paramAnonymousList.next();
          localArrayList.add(LaboratoryViewModel.this.projectEntityToProjectBean(localDiyProjectEntity));
        }
        LaboratoryViewModel.this.mProjectBeans.setValue(localArrayList);
        LaboratoryViewModel.this.hasLaboratory.set(true);
        return LaboratoryViewModel.this.mProjectBeans;
      }
    });
  }
  
  private DiyProjectBean projectEntityToProjectBean(DiyProjectEntity paramDiyProjectEntity)
  {
    DiyProjectBean localDiyProjectBean = (DiyProjectBean)JsonUtil.jsonToObject(paramDiyProjectEntity.project, DiyProjectBean.class);
    localDiyProjectBean.id = paramDiyProjectEntity.id;
    return localDiyProjectBean;
  }
  
  public LiveData<List<DiyProjectBean>> getListLiveData()
  {
    return this.mListLiveData;
  }
  
  public void jumpToChooseActionActivity()
  {
    this.mView.openChooseAction();
  }
  
  public void onItemClick(int paramInt)
  {
    if (paramInt < 0) {
      return;
    }
    if (paramInt == 0)
    {
      this.mView.openChooseAction();
      return;
    }
    this.mView.openCP(((DiyProjectBean)((List)this.mProjectBeans.getValue()).get(paramInt - 1)).id);
  }
  
  public static abstract interface LaboratoryView
  {
    public abstract void openCP(int paramInt);
    
    public abstract void openChooseAction();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\laboratory\LaboratoryViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */