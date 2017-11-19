package cc.makeblock.makeblock.customview.laboratory;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DrawableRes;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import java.util.ArrayList;
import java.util.List;

public class LaboratoryPanelLayout
  extends FrameLayout
  implements LaboratoryCellView.onDragListener, View.OnClickListener
{
  public static final int COLUMN = 4;
  private static final float DESIGN_SCALE_RATIO = 0.9F;
  private static final int MAX_RECYCLER_SIZE = 20;
  public static final int MODE_DESIGN = 2;
  public static final int MODE_IMAGE = 3;
  public static final int MODE_PLAY = 1;
  public static final int ROW = 2;
  private int currentDragIndex;
  private int currentMode;
  private int height;
  private int heightSize;
  private SparseArray<CellViewWrap> mCellViewWraps = new SparseArray(8);
  private SparseArray<Rect> mChildRect = new SparseArray(8);
  private int mDragIndex;
  private List<onDragListener> mDragListeners = new ArrayList(2);
  private boolean mIsDraging;
  private OnCellClickListener mOnCellClickListener;
  private List<CellViewWrap> mRecyclerPool;
  private int maxHeight;
  private int maxWidth;
  private final int space = 10;
  private int width;
  private int widthSize;
  
  public LaboratoryPanelLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public LaboratoryPanelLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public LaboratoryPanelLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
    {
      public boolean onPreDraw()
      {
        LaboratoryPanelLayout.this.getViewTreeObserver().removeOnPreDrawListener(this);
        LaboratoryPanelLayout.this.initPanel();
        return false;
      }
    });
  }
  
  private void addActionView(CellViewWrap paramCellViewWrap, LaboratoryCellView paramLaboratoryCellView)
  {
    if ((paramCellViewWrap != null) && (paramCellViewWrap.view != null))
    {
      paramCellViewWrap.view.setTag(2131296606, paramCellViewWrap.type);
      paramLaboratoryCellView.addCellView(paramCellViewWrap.view, paramCellViewWrap.percent);
    }
  }
  
  private void addCellView(SparseArray<LaboratoryCellView> paramSparseArray)
  {
    int i = 0;
    while (i < 2)
    {
      int j = 0;
      while (j < 4)
      {
        int k = i * 4 + j;
        Object localObject2 = (LaboratoryCellView)paramSparseArray.get(k);
        Object localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = createCellView(i, j);
        }
        localObject2 = (CellViewWrap)this.mCellViewWraps.get(k);
        if (localObject2 != null) {
          addActionView((CellViewWrap)localObject2, (LaboratoryCellView)localObject1);
        }
        addView((View)localObject1, createLayoutParams(i, j));
        j += 1;
      }
      i += 1;
    }
  }
  
  private void attachDragView(View paramView, int paramInt)
  {
    if (paramInt >= 0)
    {
      LaboratoryCellView localLaboratoryCellView = (LaboratoryCellView)getChildAt(paramInt);
      if (localLaboratoryCellView.isEmpty())
      {
        Object localObject = (ViewGroup)paramView;
        paramView = ((ViewGroup)localObject).getChildAt(0);
        ((ViewGroup)localObject).removeAllViews();
        localObject = (PercentRelativeLayout.LayoutParams)paramView.getTag(2131296605);
        paramView.setTag(2131296605, null);
        ((PercentRelativeLayout.LayoutParams)localObject).width = 0;
        ((PercentRelativeLayout.LayoutParams)localObject).height = 0;
        localLaboratoryCellView.addView(paramView, (ViewGroup.LayoutParams)localObject);
      }
    }
  }
  
  private SparseArray<LaboratoryCellView> cacheView()
  {
    SparseArray localSparseArray = new SparseArray(8);
    int j = getChildCount();
    if (j > 0)
    {
      int i = 0;
      while (i < j)
      {
        LaboratoryCellView localLaboratoryCellView = (LaboratoryCellView)getChildAt(i);
        localSparseArray.put(localLaboratoryCellView.getIndex(), localLaboratoryCellView);
        i += 1;
      }
      removeAllViews();
    }
    return localSparseArray;
  }
  
  private void clearCellView()
  {
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      ((ViewGroup)getChildAt(i)).removeAllViews();
      i += 1;
    }
  }
  
  private LaboratoryCellView createCellView(int paramInt1, int paramInt2)
  {
    LaboratoryCellView localLaboratoryCellView = new LaboratoryCellView(getContext());
    localLaboratoryCellView.setIndex(paramInt1 * 4 + paramInt2);
    localLaboratoryCellView.setDragListener(this);
    localLaboratoryCellView.setOnClickListener(this);
    return localLaboratoryCellView;
  }
  
  private ViewGroup.LayoutParams createLayoutParams(int paramInt1, int paramInt2)
  {
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(this.widthSize, this.heightSize);
    localLayoutParams.setMargins(this.widthSize * paramInt2 + (paramInt2 + 1) * this.space, this.heightSize * paramInt1 + (paramInt1 + 1) * this.space, 0, 0);
    localLayoutParams.gravity = 0;
    return localLayoutParams;
  }
  
  private int getDragIndex(View paramView, int paramInt1, int paramInt2)
  {
    int m = paramView.getWidth() / 2;
    int n = paramView.getHeight() / 2;
    int k = -1;
    int i1 = this.mChildRect.size();
    int j = 0;
    for (;;)
    {
      int i = k;
      if (j < i1)
      {
        if ((((Rect)this.mChildRect.get(j)).contains(paramInt1 + m, paramInt2 + n)) && (((LaboratoryCellView)getChildAt(j)).isEmpty())) {
          i = j;
        }
      }
      else
      {
        if (i >= 0) {
          this.currentDragIndex = i;
        }
        paramInt1 = i;
        if (i < 0) {
          paramInt1 = this.currentDragIndex;
        }
        return paramInt1;
      }
      j += 1;
    }
  }
  
  private int[] getRowColumn(int paramInt)
  {
    return new int[] { paramInt / 4, paramInt % 4 };
  }
  
  private void initCell()
  {
    addCellView(cacheView());
  }
  
  private void initPanel()
  {
    this.maxHeight = getMeasuredHeight();
    this.maxWidth = getMeasuredWidth();
    setPanelSize(this.currentMode);
    setCellSize();
    initCell();
  }
  
  private void resetCellBackground()
  {
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      setCellBackground(i, 2131165275);
      i += 1;
    }
  }
  
  private void setCellBackground(int paramInt1, @DrawableRes int paramInt2)
  {
    if (paramInt1 < 0) {}
    LaboratoryCellView localLaboratoryCellView;
    do
    {
      return;
      localLaboratoryCellView = (LaboratoryCellView)getChildAt(paramInt1);
    } while ((!localLaboratoryCellView.isEmpty()) || (this.mDragIndex == localLaboratoryCellView.getIndex()));
    localLaboratoryCellView.setBackgroundResource(paramInt2);
  }
  
  private void setCellDragBg()
  {
    if (this.currentMode == 3) {
      return;
    }
    int k = getChildCount();
    int i = 0;
    label16:
    LaboratoryCellView localLaboratoryCellView;
    if (i < k)
    {
      localLaboratoryCellView = (LaboratoryCellView)getChildAt(i);
      if ((!localLaboratoryCellView.isEmpty()) || (this.mDragIndex == localLaboratoryCellView.getIndex())) {
        break label68;
      }
    }
    label68:
    for (int j = 2131165275;; j = 2131165273)
    {
      localLaboratoryCellView.setBackgroundResource(j);
      i += 1;
      break label16;
      break;
    }
  }
  
  private void setCellNotDragBg()
  {
    if (this.currentMode == 3) {
      return;
    }
    int k = getChildCount();
    int i = 0;
    label16:
    LaboratoryCellView localLaboratoryCellView;
    if (i < k)
    {
      localLaboratoryCellView = (LaboratoryCellView)getChildAt(i);
      if (!localLaboratoryCellView.isEmpty()) {
        break label56;
      }
    }
    label56:
    for (int j = 2131165275;; j = 2131165276)
    {
      localLaboratoryCellView.setBackgroundResource(j);
      i += 1;
      break label16;
      break;
    }
  }
  
  private void setCellRect()
  {
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      LaboratoryCellView localLaboratoryCellView = (LaboratoryCellView)getChildAt(i);
      int[] arrayOfInt = new int[2];
      localLaboratoryCellView.getLocationInWindow(arrayOfInt);
      int k = arrayOfInt[0];
      int m = arrayOfInt[1];
      int n = localLaboratoryCellView.getWidth();
      int i1 = localLaboratoryCellView.getHeight();
      this.mChildRect.put(localLaboratoryCellView.getIndex(), new Rect(k, m, k + n, m + i1));
      i += 1;
    }
  }
  
  private void setCellSize()
  {
    this.widthSize = (this.width / 4 - (this.space + this.space / 4));
    this.heightSize = (this.height / 2 - (this.space + this.space / 2));
  }
  
  private void setChildModel(int paramInt)
  {
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      ((LaboratoryCellView)getChildAt(i)).setMode(paramInt);
      i += 1;
    }
  }
  
  private void setPanelSize(int paramInt)
  {
    if (this.maxWidth == 0) {
      return;
    }
    if (paramInt == 2) {
      this.width = ((int)(this.maxWidth * 0.9F));
    }
    for (this.height = ((int)(this.maxHeight * 0.9F));; this.height = this.maxHeight)
    {
      ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
      localLayoutParams.width = this.width;
      localLayoutParams.height = this.height;
      setLayoutParams(localLayoutParams);
      return;
      this.width = this.maxWidth;
    }
  }
  
  private void updateSize(int paramInt)
  {
    setPanelSize(paramInt);
    setCellSize();
    initCell();
  }
  
  public void addWidgets(CellViewWrap paramCellViewWrap)
  {
    if (paramCellViewWrap == null) {
      return;
    }
    if (getChildCount() == 0)
    {
      this.mCellViewWraps.put(paramCellViewWrap.index, paramCellViewWrap);
      return;
    }
    addActionView(paramCellViewWrap, (LaboratoryCellView)getChildAt(paramCellViewWrap.index));
  }
  
  public void cacheCellView()
  {
    if (this.mRecyclerPool == null) {}
    for (;;)
    {
      return;
      int j = getChildCount();
      int k = this.mRecyclerPool.size();
      int i = 0;
      while (i < j)
      {
        View localView1 = getChildAt(i);
        if ((localView1 != null) && ((localView1 instanceof LaboratoryCellView)))
        {
          View localView2 = ((ViewGroup)localView1).getChildAt(0);
          if (localView2 != null)
          {
            String str = localView2.getTag(2131296606).toString();
            if (k >= 20) {
              this.mRecyclerPool.remove(0);
            }
            ((ViewGroup)localView1).removeView(localView2);
            this.mRecyclerPool.add(new CellViewWrap(localView2, str, -1, 0.0F));
          }
        }
        i += 1;
      }
    }
  }
  
  public boolean isDragging()
  {
    return this.mIsDraging;
  }
  
  public void onClick(View paramView)
  {
    if ((this.mOnCellClickListener != null) && (this.currentMode == 2))
    {
      int[] arrayOfInt = getRowColumn(((Integer)paramView.getTag(2131296607)).intValue());
      View localView = ((ViewGroup)paramView).getChildAt(0);
      boolean bool = true;
      if (localView != null) {
        bool = false;
      }
      this.mOnCellClickListener.onCellClick(paramView, bool, arrayOfInt[0], arrayOfInt[1]);
    }
  }
  
  public boolean onDrag(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = 0;
    int k = this.mDragListeners.size();
    int i = 0;
    while (i < k)
    {
      if (((onDragListener)this.mDragListeners.get(i)).onDrag(paramView, paramInt1, paramInt2, paramInt3, paramInt4)) {
        j = 1;
      }
      i += 1;
    }
    if (j != 0) {
      resetCellBackground();
    }
    for (;;)
    {
      return true;
      resetCellBackground();
      setCellBackground(getDragIndex(paramView, paramInt1, paramInt2), 2131165274);
    }
  }
  
  public boolean onDrop(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mIsDraging = false;
    int i = 0;
    int k = this.mDragListeners.size();
    int j = 0;
    while (j < k)
    {
      if (((onDragListener)this.mDragListeners.get(j)).onDrop(paramView, paramInt1, paramInt2, paramInt3, paramInt4)) {
        i = 1;
      }
      j += 1;
    }
    paramInt2 = getDragIndex(paramView, paramInt1, paramInt2);
    View localView = ((ViewGroup)paramView).getChildAt(0);
    int[] arrayOfInt1 = getRowColumn(paramInt2);
    int[] arrayOfInt2 = getRowColumn(this.mDragIndex);
    paramInt1 = 0;
    while (paramInt1 < k)
    {
      ((onDragListener)this.mDragListeners.get(paramInt1)).onFinish(paramView, (String)localView.getTag(2131296606), arrayOfInt2[0], arrayOfInt2[1], arrayOfInt1[0], arrayOfInt1[1]);
      paramInt1 += 1;
    }
    if (i == 0) {
      attachDragView(paramView, paramInt2);
    }
    setCellNotDragBg();
    return true;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.currentMode == 3) {
      return true;
    }
    return super.onInterceptTouchEvent(paramMotionEvent);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    setCellRect();
  }
  
  public boolean onStart(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mIsDraging = true;
    this.mDragIndex = ((Integer)paramView.getTag(2131296603)).intValue();
    setCellDragBg();
    int j = this.mDragListeners.size();
    int i = 0;
    while (i < j)
    {
      ((onDragListener)this.mDragListeners.get(i)).onStart(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
      i += 1;
    }
    paramInt1 = getDragIndex(paramView, paramInt1, paramInt2);
    if (this.mDragIndex != paramInt1)
    {
      setCellBackground(paramInt1, 2131165274);
      return true;
    }
    setCellBackground(paramInt1, 2131165273);
    return true;
  }
  
  public void registerDragListener(onDragListener paramonDragListener)
  {
    this.mDragListeners.add(paramonDragListener);
  }
  
  public void setModel(int paramInt)
  {
    if (this.currentMode != paramInt)
    {
      this.currentMode = paramInt;
      updateSize(paramInt);
      setChildModel(paramInt);
    }
  }
  
  public void setOnCellClickListener(OnCellClickListener paramOnCellClickListener)
  {
    this.mOnCellClickListener = paramOnCellClickListener;
  }
  
  public void setRecyclerPool(List<CellViewWrap> paramList)
  {
    this.mRecyclerPool = paramList;
  }
  
  public void setWidgets(List<CellViewWrap> paramList)
  {
    clearCellView();
    if (paramList != null)
    {
      int j = paramList.size();
      int i = 0;
      while (i < j)
      {
        addWidgets((CellViewWrap)paramList.get(i));
        i += 1;
      }
    }
  }
  
  public static class CellViewWrap
  {
    public final int index;
    public final float percent;
    public final String type;
    public final View view;
    
    public CellViewWrap(View paramView, String paramString, int paramInt, float paramFloat)
    {
      this.view = paramView;
      this.type = paramString;
      this.index = paramInt;
      this.percent = paramFloat;
    }
  }
  
  public static abstract interface OnCellClickListener
  {
    public abstract void onCellClick(View paramView, boolean paramBoolean, int paramInt1, int paramInt2);
  }
  
  public static abstract interface onDragListener
  {
    public abstract boolean onDrag(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
    
    public abstract boolean onDrop(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
    
    public abstract void onFinish(View paramView, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
    
    public abstract boolean onStart(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\laboratory\LaboratoryPanelLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */