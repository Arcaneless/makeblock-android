package cc.makeblock.makeblock.scene.navigation;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cc.makeblock.makeblock.base.BaseFragment;
import cc.makeblock.makeblock.bean.ChooseDeviceItem;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.databinding.FragmentNavigationDeviceBinding;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import cc.makeblock.makeblock.viewmodel.navigation.NavigationDeviceItemViewModel;
import cc.makeblock.makeblock.viewmodel.navigation.NavigationDeviceViewModel;
import cc.makeblock.makeblock.viewmodel.navigation.NavigationDeviceViewModel.NavigationDeviceView;
import java.util.ArrayList;
import java.util.List;

public class NavigationDeviceFragment
  extends BaseFragment
  implements NavigationDeviceViewModel.NavigationDeviceView
{
  private FragmentNavigationDeviceBinding mBinding;
  private NavigationDeviceViewModel mViewModel;
  
  public static Fragment newInstance()
  {
    return new NavigationDeviceFragment();
  }
  
  public void onActivityCreated(@Nullable Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.mBinding.recyclerView.setHasFixedSize(true);
    this.mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    this.mBinding.recyclerView.setAdapter(new DeviceAdapter());
    this.mBinding.setViewModel(this.mViewModel);
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mViewModel = new NavigationDeviceViewModel(this);
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    this.mBinding = ((FragmentNavigationDeviceBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427425, paramViewGroup, false));
    return this.mBinding.getRoot();
  }
  
  public void onItemClick(ChooseDeviceItem paramChooseDeviceItem)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("boardName", paramChooseDeviceItem.boardName);
    getActivity().setResult(-1, localIntent);
    getActivity().finish();
  }
  
  public void showChooseConflictDialog(final int paramInt)
  {
    ConfirmDialog localConfirmDialog = new ConfirmDialog(getActivity());
    localConfirmDialog.setTitleText(TextUtil.getStringById(2131493183));
    localConfirmDialog.setOnOperationConfirmedListener(new ConfirmDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        NavigationDeviceFragment.this.mViewModel.disconnectSelect(paramInt);
      }
    });
    localConfirmDialog.show();
  }
  
  public class DeviceAdapter
    extends RecyclerView.Adapter<DeviceViewHolder>
  {
    private List<ChooseDeviceItem> mDeviceItems = new ArrayList(0);
    
    public DeviceAdapter() {}
    
    public int getItemCount()
    {
      return this.mDeviceItems.size();
    }
    
    public int getItemViewType(int paramInt)
    {
      if (paramInt == 0) {
        return 2131427433;
      }
      return 2131427434;
    }
    
    public void onBindViewHolder(DeviceViewHolder paramDeviceViewHolder, int paramInt)
    {
      paramDeviceViewHolder.getDataBinding().setVariable(79, new NavigationDeviceItemViewModel((ChooseDeviceItem)this.mDeviceItems.get(paramInt)));
      paramDeviceViewHolder.getDataBinding().executePendingBindings();
    }
    
    public DeviceViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
    {
      LayoutInflater localLayoutInflater = LayoutInflater.from(paramViewGroup.getContext());
      if (paramInt == 2131427433) {
        return new DeviceViewHolder(DataBindingUtil.inflate(localLayoutInflater, 2131427433, paramViewGroup, false));
      }
      return new DeviceViewHolder(DataBindingUtil.inflate(localLayoutInflater, 2131427434, paramViewGroup, false));
    }
    
    public void replaceData(List<ChooseDeviceItem> paramList)
    {
      if (paramList == null) {
        this.mDeviceItems.clear();
      }
      for (;;)
      {
        notifyDataSetChanged();
        return;
        this.mDeviceItems = paramList;
      }
    }
    
    public class DeviceViewHolder
      extends RecyclerView.ViewHolder
    {
      private final ViewDataBinding mDataBinding;
      
      public DeviceViewHolder(ViewDataBinding paramViewDataBinding)
      {
        super();
        this.mDataBinding = paramViewDataBinding;
      }
      
      public ViewDataBinding getDataBinding()
      {
        return this.mDataBinding;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\navigation\NavigationDeviceFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */