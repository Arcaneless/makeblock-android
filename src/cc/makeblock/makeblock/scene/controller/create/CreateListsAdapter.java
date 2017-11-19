package cc.makeblock.makeblock.scene.controller.create;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import cc.makeblock.makeblock.databinding.ItemCreateProjectBinding;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import cc.makeblock.makeblock.project.ProjectBean;
import cc.makeblock.makeblock.project.ProjectStoreUtils;
import cc.makeblock.makeblock.scene.controller.ListItemData;
import cc.makeblock.makeblock.scene.controller.ProjectListChangeEvent;
import cc.makeblock.makeblock.viewmodel.controller.create.CreateItemViewModel;
import java.util.ArrayList;

public class CreateListsAdapter
  extends RecyclerView.Adapter<ProjectItemViewHolder>
{
  private static final int FLIP_ANIMATION_DURATION = 250;
  private static final int POSITION_NEWLY_CREATED_CUSTOM_PROJECT = 1;
  private static final String TAG = CreateListsAdapter.class.getSimpleName();
  private String boardName;
  private Context context;
  private ArrayList<ProjectBean> customProjectBeans = new ArrayList();
  private ArrayList<ListItemData> dataList = new ArrayList();
  private LayoutInflater inflater;
  private OnListCountChangeListener onListCountChangeListener;
  private ProjectItemViewHolder selectedHolder;
  private ListItemData urlItemData;
  
  public CreateListsAdapter(Context paramContext, boolean paramBoolean)
  {
    this.context = paramContext;
    this.inflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }
  
  private void addProjectBeansToList(ArrayList<ProjectBean> paramArrayList)
  {
    int i = 0;
    while (i < paramArrayList.size())
    {
      this.dataList.add(new ListItemData((ProjectBean)paramArrayList.get(i)));
      i += 1;
    }
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
      this.customProjectBeans = ProjectStoreUtils.query(this.context, this.boardName);
      this.dataList.add(paramInt, new ListItemData((ProjectBean)this.customProjectBeans.get(0)));
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
    this.dataList.clear();
    this.dataList.add(new ListItemData(-3));
    this.customProjectBeans = ProjectStoreUtils.query(this.context, this.boardName);
    if ((this.customProjectBeans != null) && (this.customProjectBeans.size() > 0)) {
      addProjectBeansToList(this.customProjectBeans);
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
  
  public ProjectItemViewHolder getCurrentSelectedItemHolder()
  {
    return this.selectedHolder;
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
    if (((((ListItemData)this.dataList.get(paramInt)).getTutorialInfoData() == null) && (paramInt == 0)) || ((((ListItemData)this.dataList.get(paramInt)).getTutorialInfoData() != null) && (paramInt == 1))) {
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
  
  public void notifyListChange(ProjectListChangeEvent paramProjectListChangeEvent)
  {
    notifyListChange(paramProjectListChangeEvent, this.boardName);
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
  
  public void onBindViewHolder(final ProjectItemViewHolder paramProjectItemViewHolder, int paramInt)
  {
    ListItemData localListItemData = getItemData(paramInt);
    paramProjectItemViewHolder.getBinding().getViewModel().setItemData((CreateActivity)this.context, localListItemData);
    paramProjectItemViewHolder.binding.itemProjectFront.setRotationY(0.0F);
    paramProjectItemViewHolder.binding.itemProjectBack.setRotationY(-90.0F);
    paramProjectItemViewHolder.binding.itemProjectMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        StatisticsTool.getInstance().onEvent("CustomProjectClickMenu", "自定义项目点击菜单");
        CreateListsAdapter.this.setCurrentSelectedItemHolder(paramProjectItemViewHolder);
      }
    });
    paramProjectItemViewHolder.binding.itemProjectDismiss.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CreateListsAdapter.this.setCurrentSelectedItemHolder(null);
      }
    });
  }
  
  public ProjectItemViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
  {
    paramViewGroup = (ItemCreateProjectBinding)DataBindingUtil.inflate(this.inflater, 2131427432, paramViewGroup, false);
    paramViewGroup.setViewModel(new CreateItemViewModel());
    paramViewGroup.getRoot().setLayoutParams(new ViewGroup.LayoutParams(ScreenHelper.getPercentHeightToPx(0.7407407F) * 580 / 800, ScreenHelper.getPercentHeightToPx(0.7407407F)));
    return new ProjectItemViewHolder(paramViewGroup, null);
  }
  
  public void setCurrentSelectedItemHolder(ProjectItemViewHolder paramProjectItemViewHolder)
  {
    if (paramProjectItemViewHolder == this.selectedHolder) {
      return;
    }
    if (this.selectedHolder != null)
    {
      this.selectedHolder.cancelAnimator();
      this.selectedHolder.flipToFront();
    }
    if (paramProjectItemViewHolder != null) {
      paramProjectItemViewHolder.flipToBack();
    }
    this.selectedHolder = paramProjectItemViewHolder;
  }
  
  public void setOnListCountChangeListener(OnListCountChangeListener paramOnListCountChangeListener)
  {
    this.onListCountChangeListener = paramOnListCountChangeListener;
  }
  
  public static abstract interface OnListCountChangeListener
  {
    public abstract void onListCountChange(CreateListsAdapter paramCreateListsAdapter, int paramInt);
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
    private ItemCreateProjectBinding binding;
    private ObjectAnimator flipToBackBackAnimator;
    private ObjectAnimator flipToBackFrontAnimator;
    private ObjectAnimator flipToFrontBackAnimator;
    private ObjectAnimator flipToFrontFrontAnimator;
    private boolean isSelected = false;
    
    private ProjectItemViewHolder(ItemCreateProjectBinding paramItemCreateProjectBinding)
    {
      super();
      this.binding = paramItemCreateProjectBinding;
    }
    
    private ItemCreateProjectBinding getBinding()
    {
      return this.binding;
    }
    
    void cancelAnimator()
    {
      if (this.flipToBackFrontAnimator != null)
      {
        this.flipToBackBackAnimator.cancel();
        this.flipToBackFrontAnimator.cancel();
      }
      if (this.flipToFrontBackAnimator != null)
      {
        this.flipToFrontFrontAnimator.cancel();
        this.flipToFrontBackAnimator.cancel();
      }
    }
    
    void flipToBack()
    {
      PercentRelativeLayout localPercentRelativeLayout = this.binding.itemProjectFront;
      localPercentRelativeLayout.setRotationY(0.0F);
      this.flipToBackFrontAnimator = ObjectAnimator.ofFloat(localPercentRelativeLayout, "rotationY", new float[] { 0.0F, 90.0F });
      this.flipToBackFrontAnimator.setDuration(250L);
      this.flipToBackFrontAnimator.setInterpolator(new AccelerateInterpolator());
      this.flipToBackFrontAnimator.start();
      localPercentRelativeLayout = this.binding.itemProjectBack;
      localPercentRelativeLayout.setRotationY(-90.0F);
      this.flipToBackBackAnimator = ObjectAnimator.ofFloat(localPercentRelativeLayout, "rotationY", new float[] { -90.0F, 0.0F });
      this.flipToBackBackAnimator.setDuration(250L);
      this.flipToBackBackAnimator.setInterpolator(new OvershootInterpolator());
      this.flipToBackBackAnimator.setStartDelay(250L);
      this.flipToBackBackAnimator.start();
    }
    
    void flipToFront()
    {
      PercentRelativeLayout localPercentRelativeLayout = this.binding.itemProjectFront;
      localPercentRelativeLayout.setRotationY(90.0F);
      this.flipToFrontFrontAnimator = ObjectAnimator.ofFloat(localPercentRelativeLayout, "rotationY", new float[] { 90.0F, 0.0F });
      this.flipToFrontFrontAnimator.setDuration(250L);
      this.flipToFrontFrontAnimator.setInterpolator(new OvershootInterpolator());
      this.flipToFrontFrontAnimator.setStartDelay(250L);
      this.flipToFrontFrontAnimator.start();
      localPercentRelativeLayout = this.binding.itemProjectBack;
      localPercentRelativeLayout.setRotationY(0.0F);
      this.flipToFrontBackAnimator = ObjectAnimator.ofFloat(localPercentRelativeLayout, "rotationY", new float[] { 0.0F, -90.0F });
      this.flipToFrontBackAnimator.setDuration(250L);
      this.flipToFrontBackAnimator.setInterpolator(new AccelerateInterpolator());
      this.flipToFrontBackAnimator.start();
    }
    
    public boolean getIsSelected()
    {
      return this.isSelected;
    }
    
    public void setSelected(boolean paramBoolean)
    {
      this.isSelected = paramBoolean;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\controller\create\CreateListsAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */