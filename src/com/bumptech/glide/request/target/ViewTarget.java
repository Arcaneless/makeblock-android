package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ViewTarget<T extends View, Z>
  extends BaseTarget<Z>
{
  private static final String TAG = "ViewTarget";
  private static boolean isTagUsedAtLeastOnce = false;
  @Nullable
  private static Integer tagId = null;
  private final SizeDeterminer sizeDeterminer;
  protected final T view;
  
  public ViewTarget(T paramT)
  {
    this.view = ((View)Preconditions.checkNotNull(paramT));
    this.sizeDeterminer = new SizeDeterminer(paramT);
  }
  
  @Nullable
  private Object getTag()
  {
    if (tagId == null) {
      return this.view.getTag();
    }
    return this.view.getTag(tagId.intValue());
  }
  
  private void setTag(@Nullable Object paramObject)
  {
    if (tagId == null)
    {
      isTagUsedAtLeastOnce = true;
      this.view.setTag(paramObject);
      return;
    }
    this.view.setTag(tagId.intValue(), paramObject);
  }
  
  public static void setTagId(int paramInt)
  {
    if ((tagId != null) || (isTagUsedAtLeastOnce)) {
      throw new IllegalArgumentException("You cannot set the tag id more than once or change the tag id after the first request has been made");
    }
    tagId = Integer.valueOf(paramInt);
  }
  
  @Nullable
  public Request getRequest()
  {
    Object localObject = getTag();
    Request localRequest = null;
    if (localObject != null)
    {
      if ((localObject instanceof Request)) {
        localRequest = (Request)localObject;
      }
    }
    else {
      return localRequest;
    }
    throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
  }
  
  public void getSize(SizeReadyCallback paramSizeReadyCallback)
  {
    this.sizeDeterminer.getSize(paramSizeReadyCallback);
  }
  
  public T getView()
  {
    return this.view;
  }
  
  public void onLoadCleared(Drawable paramDrawable)
  {
    super.onLoadCleared(paramDrawable);
    this.sizeDeterminer.clearCallbacksAndListener();
  }
  
  public void removeCallback(SizeReadyCallback paramSizeReadyCallback)
  {
    this.sizeDeterminer.removeCallback(paramSizeReadyCallback);
  }
  
  public void setRequest(@Nullable Request paramRequest)
  {
    setTag(paramRequest);
  }
  
  public String toString()
  {
    return "Target for: " + this.view;
  }
  
  private static class SizeDeterminer
  {
    private static final int PENDING_SIZE = 0;
    private final List<SizeReadyCallback> cbs = new ArrayList();
    @Nullable
    private SizeDeterminerLayoutListener layoutListener;
    private final View view;
    
    SizeDeterminer(View paramView)
    {
      this.view = paramView;
    }
    
    private int getTargetDimen(int paramInt1, int paramInt2, int paramInt3)
    {
      paramInt1 -= paramInt3;
      if (isSizeValid(paramInt1)) {
        return paramInt1;
      }
      if (paramInt2 == 0) {
        return 0;
      }
      if (paramInt2 == -2) {
        return Integer.MIN_VALUE;
      }
      if (paramInt2 > 0) {
        return paramInt2 - paramInt3;
      }
      return 0;
    }
    
    private int getTargetHeight()
    {
      int j = this.view.getPaddingTop();
      int k = this.view.getPaddingBottom();
      ViewGroup.LayoutParams localLayoutParams = this.view.getLayoutParams();
      if (localLayoutParams != null) {}
      for (int i = localLayoutParams.height;; i = 0) {
        return getTargetDimen(this.view.getHeight(), i, j + k);
      }
    }
    
    private int getTargetWidth()
    {
      int j = this.view.getPaddingLeft();
      int k = this.view.getPaddingRight();
      ViewGroup.LayoutParams localLayoutParams = this.view.getLayoutParams();
      if (localLayoutParams != null) {}
      for (int i = localLayoutParams.width;; i = 0) {
        return getTargetDimen(this.view.getWidth(), i, j + k);
      }
    }
    
    private boolean isSizeValid(int paramInt)
    {
      return (paramInt > 0) || (paramInt == Integer.MIN_VALUE);
    }
    
    private boolean isViewStateAndSizeValid(int paramInt1, int paramInt2)
    {
      return (isViewStateValid()) && (isSizeValid(paramInt1)) && (isSizeValid(paramInt2));
    }
    
    private boolean isViewStateValid()
    {
      if ((this.view.getLayoutParams() != null) && (this.view.getLayoutParams().width > 0) && (this.view.getLayoutParams().height > 0)) {}
      while (!this.view.isLayoutRequested()) {
        return true;
      }
      return false;
    }
    
    private void notifyCbs(int paramInt1, int paramInt2)
    {
      Iterator localIterator = this.cbs.iterator();
      while (localIterator.hasNext()) {
        ((SizeReadyCallback)localIterator.next()).onSizeReady(paramInt1, paramInt2);
      }
    }
    
    void checkCurrentDimens()
    {
      if (this.cbs.isEmpty()) {}
      int i;
      int j;
      do
      {
        return;
        i = getTargetWidth();
        j = getTargetHeight();
      } while (!isViewStateAndSizeValid(i, j));
      notifyCbs(i, j);
      clearCallbacksAndListener();
    }
    
    void clearCallbacksAndListener()
    {
      ViewTreeObserver localViewTreeObserver = this.view.getViewTreeObserver();
      if (localViewTreeObserver.isAlive()) {
        localViewTreeObserver.removeOnPreDrawListener(this.layoutListener);
      }
      this.layoutListener = null;
      this.cbs.clear();
    }
    
    void getSize(SizeReadyCallback paramSizeReadyCallback)
    {
      int i = getTargetWidth();
      int j = getTargetHeight();
      if (isViewStateAndSizeValid(i, j)) {
        paramSizeReadyCallback.onSizeReady(i, j);
      }
      do
      {
        return;
        if (!this.cbs.contains(paramSizeReadyCallback)) {
          this.cbs.add(paramSizeReadyCallback);
        }
      } while (this.layoutListener != null);
      paramSizeReadyCallback = this.view.getViewTreeObserver();
      this.layoutListener = new SizeDeterminerLayoutListener(this);
      paramSizeReadyCallback.addOnPreDrawListener(this.layoutListener);
    }
    
    void removeCallback(SizeReadyCallback paramSizeReadyCallback)
    {
      this.cbs.remove(paramSizeReadyCallback);
    }
    
    private static class SizeDeterminerLayoutListener
      implements ViewTreeObserver.OnPreDrawListener
    {
      private final WeakReference<ViewTarget.SizeDeterminer> sizeDeterminerRef;
      
      SizeDeterminerLayoutListener(ViewTarget.SizeDeterminer paramSizeDeterminer)
      {
        this.sizeDeterminerRef = new WeakReference(paramSizeDeterminer);
      }
      
      public boolean onPreDraw()
      {
        if (Log.isLoggable("ViewTarget", 2)) {
          Log.v("ViewTarget", "OnGlobalLayoutListener called listener=" + this);
        }
        ViewTarget.SizeDeterminer localSizeDeterminer = (ViewTarget.SizeDeterminer)this.sizeDeterminerRef.get();
        if (localSizeDeterminer != null) {
          localSizeDeterminer.checkCurrentDimens();
        }
        return true;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\request\target\ViewTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */