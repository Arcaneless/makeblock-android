package cc.makeblock.makeblock.customview.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerViewBindings
{
  @BindingAdapter({"onItemClick"})
  public static void onItemClick(RecyclerView paramRecyclerView, OnItemClickListener paramOnItemClickListener)
  {
    paramRecyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(paramRecyclerView, paramOnItemClickListener));
  }
  
  public static abstract interface OnItemClickListener
  {
    public abstract void onItemClick(View paramView, int paramInt);
  }
  
  public static class RecyclerViewItemClickListener
    implements RecyclerView.OnItemTouchListener
  {
    private GestureDetectorCompat mGestureDetectorCompat;
    
    public RecyclerViewItemClickListener(final RecyclerView paramRecyclerView, final RecyclerViewBindings.OnItemClickListener paramOnItemClickListener)
    {
      this.mGestureDetectorCompat = new GestureDetectorCompat(paramRecyclerView.getContext(), new GestureDetector.SimpleOnGestureListener()
      {
        public boolean onSingleTapUp(MotionEvent paramAnonymousMotionEvent)
        {
          paramAnonymousMotionEvent = paramRecyclerView.findChildViewUnder(paramAnonymousMotionEvent.getX(), paramAnonymousMotionEvent.getY());
          if ((paramAnonymousMotionEvent != null) && (paramOnItemClickListener != null)) {
            paramOnItemClickListener.onItemClick(paramAnonymousMotionEvent, paramRecyclerView.getChildAdapterPosition(paramAnonymousMotionEvent));
          }
          return true;
        }
      });
    }
    
    public boolean onInterceptTouchEvent(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent)
    {
      this.mGestureDetectorCompat.onTouchEvent(paramMotionEvent);
      return false;
    }
    
    public void onRequestDisallowInterceptTouchEvent(boolean paramBoolean) {}
    
    public void onTouchEvent(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent) {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\bindingadapter\RecyclerViewBindings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */