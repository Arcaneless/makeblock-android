package cc.makeblock.makeblock.scene.laboratory;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import cc.makeblock.makeblock.base.NotifyBluetoothActivity;
import cc.makeblock.makeblock.customview.ProgressScrollBar;
import cc.makeblock.makeblock.customview.laboratory.LaboratoryPanelLayout;
import cc.makeblock.makeblock.customview.laboratory.LaboratoryPanelLayout.CellViewWrap;
import cc.makeblock.makeblock.databinding.ActivityLaboratoryMainBinding;
import cc.makeblock.makeblock.databinding.ItemLaboratoryBinding;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.ControllerManager.OnDeviceChangeListener;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.diy.DiyProjectBean;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import cc.makeblock.makeblock.viewmodel.laboratory.LaboratoryItemViewModel;
import cc.makeblock.makeblock.viewmodel.laboratory.LaboratoryViewModel;
import cc.makeblock.makeblock.viewmodel.laboratory.LaboratoryViewModel.LaboratoryView;
import java.util.ArrayList;
import java.util.List;

public class LaboratoryActivity
  extends NotifyBluetoothActivity
  implements LaboratoryViewModel.LaboratoryView, LifecycleOwner, ControllerManager.OnDeviceChangeListener
{
  private ActivityLaboratoryMainBinding mBinding;
  private Device mDevice;
  private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);
  private LaboratoryViewModel mViewModel;
  private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener()
  {
    public void onScrolled(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      super.onScrolled(paramAnonymousRecyclerView, paramAnonymousInt1, paramAnonymousInt2);
      float f = paramAnonymousRecyclerView.computeHorizontalScrollOffset() / (paramAnonymousRecyclerView.computeHorizontalScrollRange() - ScreenHelper.getPercentWidthToPx(0.9739583F));
      LaboratoryActivity.this.mBinding.indicatorProgress.setScrollPercent(paramAnonymousRecyclerView, f);
    }
  };
  
  public Lifecycle getLifecycle()
  {
    return this.mRegistry;
  }
  
  protected boolean isAutoShowConnectDialog()
  {
    return false;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setCurrentDevice(ControllerManager.getInstance().getCurrentDevice());
    ControllerManager.getInstance().registerDeviceListener(this);
    this.mViewModel = new LaboratoryViewModel(this);
    this.mBinding = ((ActivityLaboratoryMainBinding)DataBindingUtil.setContentView(this, 2131427369));
    this.mBinding.setViewModel(this.mViewModel);
    this.mBinding.laboratoryList.setLayoutManager(new LinearLayoutManager(this, 0, false));
    this.mBinding.laboratoryList.addItemDecoration(new LaboratoryItemDecoration());
    this.mBinding.laboratoryList.setHasFixedSize(true);
    this.mBinding.laboratoryList.setAdapter(new LaboratoryAdapter(this));
    this.mBinding.laboratoryList.addOnScrollListener(this.onScrollListener);
    this.mBinding.laboratoryList.getAdapter().registerAdapterDataObserver(new RecyclerView.AdapterDataObserver()
    {
      public void onChanged()
      {
        super.onChanged();
        LaboratoryActivity.this.mBinding.indicatorProgress.setListCount(LaboratoryActivity.this.mBinding.laboratoryList.getAdapter().getItemCount());
      }
    });
    this.mViewModel.getListLiveData().observe(this, new Observer()
    {
      public void onChanged(@Nullable List<DiyProjectBean> paramAnonymousList)
      {
        ((LaboratoryActivity.LaboratoryAdapter)LaboratoryActivity.this.mBinding.laboratoryList.getAdapter()).replaceData(paramAnonymousList);
      }
    });
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    ControllerManager.getInstance().unRegisterDeviceListener(this);
  }
  
  public void onDeviceChange(Device paramDevice)
  {
    if ((paramDevice != null) && (!paramDevice.equals(this.mDevice)) && (paramDevice.isConnected())) {
      finish();
    }
    setCurrentDevice(paramDevice);
  }
  
  public void openCP(int paramInt)
  {
    ActivityJumpUtil.jumpToLaboratoryPanelActivity(this, paramInt);
  }
  
  public void openChooseAction()
  {
    ActivityJumpUtil.jumpToChooseActionActivity(this, true);
  }
  
  public void setCurrentDevice(Device paramDevice)
  {
    this.mDevice = paramDevice;
  }
  
  public class LaboratoryAdapter
    extends RecyclerView.Adapter<RecyclerView.ViewHolder>
  {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<LaboratoryPanelLayout.CellViewWrap> mRecycledViewPool = new ArrayList();
    private List<DiyProjectBean> projectBeans = new ArrayList();
    
    public LaboratoryAdapter(Context paramContext)
    {
      this.mInflater = LayoutInflater.from(paramContext);
      this.mContext = paramContext;
    }
    
    public int getItemCount()
    {
      return this.projectBeans.size() + 1;
    }
    
    public int getItemViewType(int paramInt)
    {
      if (paramInt == 0) {
        return 2131427436;
      }
      return 2131427435;
    }
    
    public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, int paramInt)
    {
      if (paramInt > 0)
      {
        paramViewHolder = (LaboratoryViewHolder)paramViewHolder;
        paramViewHolder.getDataBinding().setVariable(79, new LaboratoryItemViewModel((DiyProjectBean)this.projectBeans.get(paramInt - 1), this.mContext, this.mRecycledViewPool));
        paramViewHolder.getDataBinding().executePendingBindings();
      }
    }
    
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
    {
      if (paramInt == 2131427436) {
        return new AddViewHolder(this.mInflater.inflate(2131427436, paramViewGroup, false));
      }
      return new LaboratoryViewHolder((ItemLaboratoryBinding)DataBindingUtil.inflate(this.mInflater, 2131427435, paramViewGroup, false), (RecyclerView)paramViewGroup);
    }
    
    public void onViewRecycled(RecyclerView.ViewHolder paramViewHolder)
    {
      super.onViewRecycled(paramViewHolder);
      if ((paramViewHolder instanceof LaboratoryViewHolder)) {
        ((LaboratoryViewHolder)paramViewHolder).recycleView();
      }
    }
    
    public void replaceData(List<DiyProjectBean> paramList)
    {
      if (paramList == null) {
        this.projectBeans.clear();
      }
      for (;;)
      {
        notifyDataSetChanged();
        return;
        this.projectBeans = paramList;
      }
    }
    
    public class AddViewHolder
      extends RecyclerView.ViewHolder
    {
      public AddViewHolder(View paramView)
      {
        super();
        paramView.findViewById(2131296288).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            LaboratoryActivity.this.mViewModel.onItemClick(0);
          }
        });
      }
    }
    
    public class LaboratoryViewHolder
      extends RecyclerView.ViewHolder
    {
      private final ItemLaboratoryBinding mDataBinding;
      
      public LaboratoryViewHolder(final ItemLaboratoryBinding paramItemLaboratoryBinding, final RecyclerView paramRecyclerView)
      {
        super();
        ViewGroup.LayoutParams localLayoutParams = paramItemLaboratoryBinding.getRoot().getLayoutParams();
        localLayoutParams.width = ((int)(ScreenHelper.SCREEN_HEIGHT * 0.746D));
        paramItemLaboratoryBinding.getRoot().setLayoutParams(localLayoutParams);
        this.mDataBinding = paramItemLaboratoryBinding;
        paramItemLaboratoryBinding.panel.setModel(3);
        paramItemLaboratoryBinding.panel.setRecyclerPool(LaboratoryActivity.LaboratoryAdapter.this.mRecycledViewPool);
        paramItemLaboratoryBinding.item.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            int i = paramRecyclerView.getChildAdapterPosition(paramItemLaboratoryBinding.getRoot());
            LaboratoryActivity.this.mViewModel.onItemClick(i);
          }
        });
      }
      
      public ViewDataBinding getDataBinding()
      {
        return this.mDataBinding;
      }
      
      public void recycleView()
      {
        this.mDataBinding.panel.cacheCellView();
      }
    }
  }
  
  static class LaboratoryItemDecoration
    extends RecyclerView.ItemDecoration
  {
    public void getItemOffsets(Rect paramRect, View paramView, RecyclerView paramRecyclerView, RecyclerView.State paramState)
    {
      paramRect.left = 25;
      paramRect.right = 25;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\laboratory\LaboratoryActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */