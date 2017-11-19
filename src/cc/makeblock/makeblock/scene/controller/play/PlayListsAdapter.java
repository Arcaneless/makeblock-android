package cc.makeblock.makeblock.scene.controller.play;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import cc.makeblock.makeblock.databinding.ItemPlayProjectBinding;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import cc.makeblock.makeblock.project.ProjectBean;
import cc.makeblock.makeblock.scene.controller.ListItemData;
import cc.makeblock.makeblock.scene.controller.ProjectListChangeEvent;
import cc.makeblock.makeblock.viewmodel.controller.play.PlayItemViewModel;
import java.util.ArrayList;

public class PlayListsAdapter
  extends RecyclerView.Adapter<ProjectItemViewHolder>
{
  private static final int POSITION_NEWLY_CREATED_CUSTOM_PROJECT = 1;
  private static final String TAG = PlayListsAdapter.class.getSimpleName();
  private String boardName;
  private Context context;
  private final int cpType;
  private ArrayList<ListItemData> dataList = new ArrayList();
  private LayoutInflater inflater;
  private OnListCountChangeListener onListCountChangeListener;
  private ProjectItemViewHolder selectedHolder;
  private ListItemData urlItemData;
  
  public PlayListsAdapter(Context paramContext, int paramInt)
  {
    this.cpType = paramInt;
    this.context = paramContext;
    this.inflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }
  
  private ListItemData getItemData(int paramInt)
  {
    return (ListItemData)this.dataList.get(paramInt);
  }
  
  private int getProjectBeanPosition(ProjectBean paramProjectBean)
  {
    int j = getItemCount();
    int i = 0;
    if (i < j)
    {
      Object localObject = getItemData(i);
      if (((ListItemData)localObject).getItemType() != -2) {}
      do
      {
        i += 1;
        break;
        localObject = ((ListItemData)localObject).getProjectBean();
        if (localObject == null) {
          return -1;
        }
      } while (paramProjectBean.id != ((ProjectBean)localObject).id);
      return i;
    }
    return -1;
  }
  
  private void insertItem(int paramInt, ListItemData paramListItemData)
  {
    switch (paramListItemData.getItemType())
    {
    }
    for (;;)
    {
      notifyItemInserted(paramInt);
      notifyListCountChange(this.dataList.size());
      return;
      this.urlItemData = paramListItemData;
      this.dataList.add(paramInt, paramListItemData);
    }
  }
  
  private void notifyListCountChange(int paramInt)
  {
    if (this.onListCountChangeListener != null) {
      this.onListCountChangeListener.onListCountChange(this, paramInt);
    }
  }
  
  private void refreshList()
  {
    if (this.urlItemData != null) {
      this.dataList.add(this.urlItemData);
    }
    notifyDataSetChanged();
    notifyListCountChange(this.dataList.size());
  }
  
  private void removeItem(int paramInt)
  {
    if (paramInt == -1) {
      return;
    }
    this.dataList.remove(paramInt);
    if ((paramInt == 0) && (this.urlItemData != null)) {
      this.urlItemData = null;
    }
    notifyItemRemoved(paramInt);
    notifyListCountChange(this.dataList.size());
    this.selectedHolder = null;
  }
  
  public void addPlayGroundItemData(ListItemData paramListItemData)
  {
    this.dataList.add(paramListItemData);
  }
  
  void addUrlItemData(ListItemData paramListItemData)
  {
    this.urlItemData = paramListItemData;
  }
  
  public void clearPlaygroundItemDataList()
  {
    this.dataList.clear();
  }
  
  public int getItemCount()
  {
    return this.dataList.size();
  }
  
  public long getItemId(int paramInt)
  {
    if (getItemData(paramInt).getItemType() == -3) {
      return -1L;
    }
    if (getItemData(paramInt).getItemType() == -1) {
      return -2L;
    }
    return getItemData(paramInt).hashCode();
  }
  
  public int getItemViewType(int paramInt)
  {
    if (((ListItemData)this.dataList.get(paramInt)).getTutorialInfoData() != null) {
      return -1;
    }
    if (paramInt == this.dataList.size() - 1) {
      return -3;
    }
    if (((ListItemData)this.dataList.get(paramInt)).getProjectBean() != null) {
      return Integer.parseInt(Integer.toBinaryString(((ListItemData)this.dataList.get(paramInt)).getProjectBean().isOfficial));
    }
    if (((ListItemData)this.dataList.get(paramInt)).getPlayground() != null) {
      return ((ListItemData)this.dataList.get(paramInt)).getPlayground().hashCode();
    }
    return ((ListItemData)this.dataList.get(paramInt)).getItemType();
  }
  
  public void notifyListChange(ProjectListChangeEvent paramProjectListChangeEvent, String paramString)
  {
    this.boardName = paramString;
    switch (paramProjectListChangeEvent.eventType)
    {
    default: 
      refreshList();
      return;
    case 2: 
      removeItem(getProjectBeanPosition(paramProjectListChangeEvent.listItemData.getProjectBean()));
      insertItem(1, paramProjectListChangeEvent.listItemData);
      return;
    case 0: 
      insertItem(1, paramProjectListChangeEvent.listItemData);
      return;
    case 1: 
      removeItem(getProjectBeanPosition(paramProjectListChangeEvent.listItemData.getProjectBean()));
      return;
    case 3: 
      refreshList();
      return;
    }
    setCurrentSelectedItemHolder(null);
  }
  
  public void onBindViewHolder(ProjectItemViewHolder paramProjectItemViewHolder, int paramInt)
  {
    ListItemData localListItemData = getItemData(paramInt);
    paramProjectItemViewHolder.getBinding().getViewModel().setItemData((PlayActivity)this.context, localListItemData, this.cpType);
  }
  
  public ProjectItemViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
  {
    paramViewGroup = (ItemPlayProjectBinding)DataBindingUtil.inflate(this.inflater, 2131427438, paramViewGroup, false);
    paramViewGroup.setViewModel(new PlayItemViewModel());
    paramViewGroup.getRoot().setLayoutParams(new ViewGroup.LayoutParams(ScreenHelper.getPercentHeightToPx(0.7407407F) * 580 / 800, ScreenHelper.getPercentHeightToPx(0.7407407F)));
    return new ProjectItemViewHolder(paramViewGroup, null);
  }
  
  void removeUrlItemData()
  {
    this.urlItemData = null;
  }
  
  public void setCurrentSelectedItemHolder(ProjectItemViewHolder paramProjectItemViewHolder)
  {
    if (paramProjectItemViewHolder == this.selectedHolder) {
      return;
    }
    this.selectedHolder = paramProjectItemViewHolder;
  }
  
  public void setOnListCountChangeListener(OnListCountChangeListener paramOnListCountChangeListener)
  {
    this.onListCountChangeListener = paramOnListCountChangeListener;
  }
  
  public static abstract interface OnListCountChangeListener
  {
    public abstract void onListCountChange(PlayListsAdapter paramPlayListsAdapter, int paramInt);
  }
  
  static class ProjectItemDecoration
    extends RecyclerView.ItemDecoration
  {
    public void getItemOffsets(Rect paramRect, View paramView, RecyclerView paramRecyclerView, RecyclerView.State paramState)
    {
      paramRect.left = 25;
      paramRect.right = 25;
    }
  }
  
  public static class ProjectItemViewHolder
    extends RecyclerView.ViewHolder
  {
    private ItemPlayProjectBinding binding;
    private boolean isSelected = false;
    
    private ProjectItemViewHolder(ItemPlayProjectBinding paramItemPlayProjectBinding)
    {
      super();
      this.binding = paramItemPlayProjectBinding;
    }
    
    private ItemPlayProjectBinding getBinding()
    {
      return this.binding;
    }
    
    public void setSelected(boolean paramBoolean)
    {
      this.isSelected = paramBoolean;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\controller\play\PlayListsAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */