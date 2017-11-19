package cc.makeblock.makeblock.customview.util;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;

class TouchDelegateGroup
  extends TouchDelegate
{
  private static final Rect DEFAULT_RECT = new Rect();
  private TouchDelegate mCurrentTouchDelegate;
  private ArrayList<TouchDelegate> mTouchDelegates;
  
  public TouchDelegateGroup(View paramView)
  {
    super(DEFAULT_RECT, paramView);
  }
  
  public void addTouchDelegate(TouchDelegate paramTouchDelegate)
  {
    if (this.mTouchDelegates == null) {
      this.mTouchDelegates = new ArrayList();
    }
    this.mTouchDelegates.add(paramTouchDelegate);
  }
  
  public void clearTouchDelegates()
  {
    if (this.mTouchDelegates != null) {
      this.mTouchDelegates.clear();
    }
    this.mCurrentTouchDelegate = null;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    Object localObject2 = null;
    Object localObject1;
    switch (paramMotionEvent.getAction())
    {
    default: 
      localObject1 = localObject2;
    }
    while (localObject1 == null)
    {
      return false;
      localObject1 = localObject2;
      if (this.mTouchDelegates != null)
      {
        Iterator localIterator = this.mTouchDelegates.iterator();
        do
        {
          localObject1 = localObject2;
          if (!localIterator.hasNext()) {
            break;
          }
          localObject1 = (TouchDelegate)localIterator.next();
        } while ((localObject1 == null) || (!((TouchDelegate)localObject1).onTouchEvent(paramMotionEvent)));
        this.mCurrentTouchDelegate = ((TouchDelegate)localObject1);
        return true;
        localObject1 = this.mCurrentTouchDelegate;
        continue;
        localObject1 = this.mCurrentTouchDelegate;
        this.mCurrentTouchDelegate = null;
      }
    }
    return ((TouchDelegate)localObject1).onTouchEvent(paramMotionEvent);
  }
  
  public void removeTouchDelegate(TouchDelegate paramTouchDelegate)
  {
    if (this.mTouchDelegates != null)
    {
      this.mTouchDelegates.remove(paramTouchDelegate);
      if (this.mTouchDelegates.isEmpty()) {
        this.mTouchDelegates = null;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\util\TouchDelegateGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */