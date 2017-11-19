package android.support.v7.widget;

import android.support.annotation.Nullable;
import android.support.v4.os.TraceCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

final class GapWorker
  implements Runnable
{
  static final ThreadLocal<GapWorker> sGapWorker = new ThreadLocal();
  static Comparator<Task> sTaskComparator = new Comparator()
  {
    public int compare(GapWorker.Task paramAnonymousTask1, GapWorker.Task paramAnonymousTask2)
    {
      int k = -1;
      int j;
      if (paramAnonymousTask1.view == null)
      {
        i = 1;
        if (paramAnonymousTask2.view != null) {
          break label42;
        }
        j = 1;
      }
      for (;;)
      {
        if (i != j)
        {
          if (paramAnonymousTask1.view == null)
          {
            return 1;
            i = 0;
            break;
            label42:
            j = 0;
            continue;
          }
          return -1;
        }
      }
      if (paramAnonymousTask1.immediate != paramAnonymousTask2.immediate)
      {
        if (paramAnonymousTask1.immediate) {}
        for (i = k;; i = 1) {
          return i;
        }
      }
      int i = paramAnonymousTask2.viewVelocity - paramAnonymousTask1.viewVelocity;
      if (i != 0) {
        return i;
      }
      i = paramAnonymousTask1.distanceToItem - paramAnonymousTask2.distanceToItem;
      if (i != 0) {
        return i;
      }
      return 0;
    }
  };
  long mFrameIntervalNs;
  long mPostTimeNs;
  ArrayList<RecyclerView> mRecyclerViews = new ArrayList();
  private ArrayList<Task> mTasks = new ArrayList();
  
  private void buildTaskList()
  {
    int n = this.mRecyclerViews.size();
    int j = 0;
    int i = 0;
    Object localObject;
    int k;
    while (i < n)
    {
      localObject = (RecyclerView)this.mRecyclerViews.get(i);
      k = j;
      if (((RecyclerView)localObject).getWindowVisibility() == 0)
      {
        ((RecyclerView)localObject).mPrefetchRegistry.collectPrefetchPositionsFromView((RecyclerView)localObject, false);
        k = j + ((RecyclerView)localObject).mPrefetchRegistry.mCount;
      }
      i += 1;
      j = k;
    }
    this.mTasks.ensureCapacity(j);
    i = 0;
    j = 0;
    if (j < n)
    {
      RecyclerView localRecyclerView = (RecyclerView)this.mRecyclerViews.get(j);
      int m;
      if (localRecyclerView.getWindowVisibility() != 0) {
        m = i;
      }
      LayoutPrefetchRegistryImpl localLayoutPrefetchRegistryImpl;
      int i1;
      do
      {
        j += 1;
        i = m;
        break;
        localLayoutPrefetchRegistryImpl = localRecyclerView.mPrefetchRegistry;
        i1 = Math.abs(localLayoutPrefetchRegistryImpl.mPrefetchDx) + Math.abs(localLayoutPrefetchRegistryImpl.mPrefetchDy);
        k = 0;
        m = i;
      } while (k >= localLayoutPrefetchRegistryImpl.mCount * 2);
      if (i >= this.mTasks.size())
      {
        localObject = new Task();
        this.mTasks.add(localObject);
        label197:
        m = localLayoutPrefetchRegistryImpl.mPrefetchArray[(k + 1)];
        if (m > i1) {
          break label285;
        }
      }
      label285:
      for (boolean bool = true;; bool = false)
      {
        ((Task)localObject).immediate = bool;
        ((Task)localObject).viewVelocity = i1;
        ((Task)localObject).distanceToItem = m;
        ((Task)localObject).view = localRecyclerView;
        ((Task)localObject).position = localLayoutPrefetchRegistryImpl.mPrefetchArray[k];
        i += 1;
        k += 2;
        break;
        localObject = (Task)this.mTasks.get(i);
        break label197;
      }
    }
    Collections.sort(this.mTasks, sTaskComparator);
  }
  
  private void flushTaskWithDeadline(Task paramTask, long paramLong)
  {
    if (paramTask.immediate) {}
    for (long l = Long.MAX_VALUE;; l = paramLong)
    {
      paramTask = prefetchPositionWithDeadline(paramTask.view, paramTask.position, l);
      if ((paramTask != null) && (paramTask.mNestedRecyclerView != null) && (paramTask.isBound()) && (!paramTask.isInvalid())) {
        prefetchInnerRecyclerViewWithDeadline((RecyclerView)paramTask.mNestedRecyclerView.get(), paramLong);
      }
      return;
    }
  }
  
  private void flushTasksWithDeadline(long paramLong)
  {
    int i = 0;
    for (;;)
    {
      Task localTask;
      if (i < this.mTasks.size())
      {
        localTask = (Task)this.mTasks.get(i);
        if (localTask.view != null) {}
      }
      else
      {
        return;
      }
      flushTaskWithDeadline(localTask, paramLong);
      localTask.clear();
      i += 1;
    }
  }
  
  static boolean isPrefetchPositionAttached(RecyclerView paramRecyclerView, int paramInt)
  {
    int j = paramRecyclerView.mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      RecyclerView.ViewHolder localViewHolder = RecyclerView.getChildViewHolderInt(paramRecyclerView.mChildHelper.getUnfilteredChildAt(i));
      if ((localViewHolder.mPosition == paramInt) && (!localViewHolder.isInvalid())) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private void prefetchInnerRecyclerViewWithDeadline(@Nullable RecyclerView paramRecyclerView, long paramLong)
  {
    if (paramRecyclerView == null) {}
    LayoutPrefetchRegistryImpl localLayoutPrefetchRegistryImpl;
    do
    {
      return;
      if ((paramRecyclerView.mDataSetHasChangedAfterLayout) && (paramRecyclerView.mChildHelper.getUnfilteredChildCount() != 0)) {
        paramRecyclerView.removeAndRecycleViews();
      }
      localLayoutPrefetchRegistryImpl = paramRecyclerView.mPrefetchRegistry;
      localLayoutPrefetchRegistryImpl.collectPrefetchPositionsFromView(paramRecyclerView, true);
    } while (localLayoutPrefetchRegistryImpl.mCount == 0);
    for (;;)
    {
      int i;
      try
      {
        TraceCompat.beginSection("RV Nested Prefetch");
        paramRecyclerView.mState.prepareForNestedPrefetch(paramRecyclerView.mAdapter);
      }
      finally
      {
        TraceCompat.endSection();
      }
      if (i < localLayoutPrefetchRegistryImpl.mCount * 2)
      {
        prefetchPositionWithDeadline(paramRecyclerView, localLayoutPrefetchRegistryImpl.mPrefetchArray[i], paramLong);
        i += 2;
      }
      else
      {
        TraceCompat.endSection();
        return;
        i = 0;
      }
    }
  }
  
  private RecyclerView.ViewHolder prefetchPositionWithDeadline(RecyclerView paramRecyclerView, int paramInt, long paramLong)
  {
    if (isPrefetchPositionAttached(paramRecyclerView, paramInt)) {
      return null;
    }
    RecyclerView.Recycler localRecycler = paramRecyclerView.mRecycler;
    for (;;)
    {
      RecyclerView.ViewHolder localViewHolder;
      try
      {
        paramRecyclerView.onEnterLayoutOrScroll();
        localViewHolder = localRecycler.tryGetViewHolderForPositionByDeadline(paramInt, false, paramLong);
      }
      finally
      {
        paramRecyclerView.onExitLayoutOrScroll(false);
      }
      if ((localViewHolder.isBound()) && (!localViewHolder.isInvalid()))
      {
        localRecycler.recycleView(localViewHolder.itemView);
        paramRecyclerView.onExitLayoutOrScroll(false);
        return localViewHolder;
      }
      localRecycler.addViewHolderToRecycledViewPool(localViewHolder, false);
      continue;
      if (localViewHolder == null) {}
    }
  }
  
  public void add(RecyclerView paramRecyclerView)
  {
    this.mRecyclerViews.add(paramRecyclerView);
  }
  
  void postFromTraversal(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
  {
    if ((paramRecyclerView.isAttachedToWindow()) && (this.mPostTimeNs == 0L))
    {
      this.mPostTimeNs = paramRecyclerView.getNanoTime();
      paramRecyclerView.post(this);
    }
    paramRecyclerView.mPrefetchRegistry.setPrefetchVector(paramInt1, paramInt2);
  }
  
  void prefetch(long paramLong)
  {
    buildTaskList();
    flushTasksWithDeadline(paramLong);
  }
  
  public void remove(RecyclerView paramRecyclerView)
  {
    this.mRecyclerViews.remove(paramRecyclerView);
  }
  
  public void run()
  {
    for (;;)
    {
      int j;
      try
      {
        TraceCompat.beginSection("RV Prefetch");
        boolean bool = this.mRecyclerViews.isEmpty();
        if (bool) {
          return;
        }
        j = this.mRecyclerViews.size();
      }
      finally
      {
        RecyclerView localRecyclerView;
        Object localObject1;
        long l1;
        this.mPostTimeNs = 0L;
        TraceCompat.endSection();
      }
      localRecyclerView = (RecyclerView)this.mRecyclerViews.get(i);
      l1 = localObject1;
      if (localRecyclerView.getWindowVisibility() == 0) {
        l1 = Math.max(localRecyclerView.getDrawingTime(), localObject1);
      }
      i += 1;
      long l2 = l1;
      break label146;
      if (l2 == 0L)
      {
        this.mPostTimeNs = 0L;
        TraceCompat.endSection();
        return;
      }
      prefetch(TimeUnit.MILLISECONDS.toNanos(l2) + this.mFrameIntervalNs);
      this.mPostTimeNs = 0L;
      TraceCompat.endSection();
      return;
      l2 = 0L;
      int i = 0;
      label146:
      if (i >= j) {}
    }
  }
  
  static class LayoutPrefetchRegistryImpl
    implements RecyclerView.LayoutManager.LayoutPrefetchRegistry
  {
    int mCount;
    int[] mPrefetchArray;
    int mPrefetchDx;
    int mPrefetchDy;
    
    public void addPosition(int paramInt1, int paramInt2)
    {
      if (paramInt1 < 0) {
        throw new IllegalArgumentException("Layout positions must be non-negative");
      }
      if (paramInt2 < 0) {
        throw new IllegalArgumentException("Pixel distance must be non-negative");
      }
      int i = this.mCount * 2;
      if (this.mPrefetchArray == null)
      {
        this.mPrefetchArray = new int[4];
        Arrays.fill(this.mPrefetchArray, -1);
      }
      for (;;)
      {
        this.mPrefetchArray[i] = paramInt1;
        this.mPrefetchArray[(i + 1)] = paramInt2;
        this.mCount += 1;
        return;
        if (i >= this.mPrefetchArray.length)
        {
          int[] arrayOfInt = this.mPrefetchArray;
          this.mPrefetchArray = new int[i * 2];
          System.arraycopy(arrayOfInt, 0, this.mPrefetchArray, 0, arrayOfInt.length);
        }
      }
    }
    
    void clearPrefetchPositions()
    {
      if (this.mPrefetchArray != null) {
        Arrays.fill(this.mPrefetchArray, -1);
      }
      this.mCount = 0;
    }
    
    void collectPrefetchPositionsFromView(RecyclerView paramRecyclerView, boolean paramBoolean)
    {
      this.mCount = 0;
      if (this.mPrefetchArray != null) {
        Arrays.fill(this.mPrefetchArray, -1);
      }
      RecyclerView.LayoutManager localLayoutManager = paramRecyclerView.mLayout;
      if ((paramRecyclerView.mAdapter != null) && (localLayoutManager != null) && (localLayoutManager.isItemPrefetchEnabled()))
      {
        if (!paramBoolean) {
          break label101;
        }
        if (!paramRecyclerView.mAdapterHelper.hasPendingUpdates()) {
          localLayoutManager.collectInitialPrefetchPositions(paramRecyclerView.mAdapter.getItemCount(), this);
        }
      }
      for (;;)
      {
        if (this.mCount > localLayoutManager.mPrefetchMaxCountObserved)
        {
          localLayoutManager.mPrefetchMaxCountObserved = this.mCount;
          localLayoutManager.mPrefetchMaxObservedInInitialPrefetch = paramBoolean;
          paramRecyclerView.mRecycler.updateViewCacheSize();
        }
        return;
        label101:
        if (!paramRecyclerView.hasPendingAdapterUpdates()) {
          localLayoutManager.collectAdjacentPrefetchPositions(this.mPrefetchDx, this.mPrefetchDy, paramRecyclerView.mState, this);
        }
      }
    }
    
    boolean lastPrefetchIncludedPosition(int paramInt)
    {
      if (this.mPrefetchArray != null)
      {
        int j = this.mCount;
        int i = 0;
        while (i < j * 2)
        {
          if (this.mPrefetchArray[i] == paramInt) {
            return true;
          }
          i += 2;
        }
      }
      return false;
    }
    
    void setPrefetchVector(int paramInt1, int paramInt2)
    {
      this.mPrefetchDx = paramInt1;
      this.mPrefetchDy = paramInt2;
    }
  }
  
  static class Task
  {
    public int distanceToItem;
    public boolean immediate;
    public int position;
    public RecyclerView view;
    public int viewVelocity;
    
    public void clear()
    {
      this.immediate = false;
      this.viewVelocity = 0;
      this.distanceToItem = 0;
      this.view = null;
      this.position = 0;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\support\v7\widget\GapWorker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */