package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.cell.CellLayout;
import cc.makeblock.makeblock.customview.cell.CellView;

public class DirectionView
  extends CellView<DirectionState>
{
  public static final int DIRECTION_BOTTOM = 4;
  public static final int DIRECTION_LEFT = 1;
  public static final int DIRECTION_RIGHT = 2;
  public static final int DIRECTION_TOP = 3;
  private static final String TAG = "DirectionView";
  private DirectionViewListener directionViewListener;
  private ImageView img_bottom;
  private ImageView img_left;
  private ImageView img_right;
  private ImageView img_top;
  private View.OnTouchListener onTouchListener = new View.OnTouchListener()
  {
    public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
    {
      boolean bool = true;
      switch (paramAnonymousMotionEvent.getAction() & 0xFF)
      {
      }
      for (;;)
      {
        bool = false;
        label35:
        return bool;
        Bitmap localBitmap = Bitmap.createBitmap(paramAnonymousView.getDrawingCache());
        int i;
        if (paramAnonymousMotionEvent.getX() > localBitmap.getWidth())
        {
          i = localBitmap.getWidth();
          label67:
          if (paramAnonymousMotionEvent.getY() <= localBitmap.getHeight()) {
            break label151;
          }
        }
        label151:
        for (int j = localBitmap.getHeight(); Color.alpha(localBitmap.getPixel(i, j)) > 100; j = (int)paramAnonymousMotionEvent.getY())
        {
          paramAnonymousView.setSelected(true);
          if (DirectionView.this.directionViewListener == null) {
            break label35;
          }
          DirectionView.this.directionViewListener.onDirectionButtonStateChanged(DirectionView.this.getButtonID(paramAnonymousView), true);
          return true;
          i = (int)paramAnonymousMotionEvent.getX();
          break label67;
        }
        paramAnonymousView.setSelected(false);
        if (DirectionView.this.directionViewListener != null) {
          DirectionView.this.directionViewListener.onDirectionButtonStateChanged(DirectionView.this.getButtonID(paramAnonymousView), false);
        }
      }
    }
  };
  
  public DirectionView(Context paramContext)
  {
    super(paramContext);
    init(null, 0);
  }
  
  public DirectionView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet, 0);
  }
  
  public DirectionView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet, paramInt);
  }
  
  private int getButtonID(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131296503: 
    case 2131296504: 
    case 2131296505: 
    case 2131296506: 
    case 2131296508: 
    default: 
      return 0;
    case 2131296507: 
      return 1;
    case 2131296509: 
      return 2;
    case 2131296510: 
      return 3;
    }
    return 4;
  }
  
  private void init(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427539, null);
    addView(paramAttributeSet, new FrameLayout.LayoutParams(-1, -1));
    this.img_left = ((ImageView)paramAttributeSet.findViewById(2131296507));
    this.img_right = ((ImageView)paramAttributeSet.findViewById(2131296509));
    this.img_top = ((ImageView)paramAttributeSet.findViewById(2131296510));
    this.img_bottom = ((ImageView)paramAttributeSet.findViewById(2131296502));
    this.img_left.setDrawingCacheEnabled(true);
    this.img_right.setDrawingCacheEnabled(true);
    this.img_top.setDrawingCacheEnabled(true);
    this.img_bottom.setDrawingCacheEnabled(true);
    this.img_left.setOnTouchListener(this.onTouchListener);
    this.img_right.setOnTouchListener(this.onTouchListener);
    this.img_top.setOnTouchListener(this.onTouchListener);
    this.img_bottom.setOnTouchListener(this.onTouchListener);
  }
  
  public boolean getProgrammable()
  {
    return true;
  }
  
  protected void sendState(DirectionState paramDirectionState)
  {
    this.cellLayout.sendWidgetValue(this.widgetData.widgetID, paramDirectionState.value);
  }
  
  public void setControlListener()
  {
    setDirectionViewListener(new DirectionViewListener()
    {
      public void onDirectionButtonStateChanged(int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        int i = 0;
        switch (paramAnonymousInt)
        {
        default: 
          paramAnonymousInt = i;
        }
        for (;;)
        {
          DirectionView.this.pushState(new DirectionView.DirectionState(DirectionView.this, paramAnonymousInt));
          return;
          if (paramAnonymousBoolean)
          {
            paramAnonymousInt = 11;
          }
          else
          {
            paramAnonymousInt = 10;
            continue;
            if (paramAnonymousBoolean)
            {
              paramAnonymousInt = 21;
            }
            else
            {
              paramAnonymousInt = 20;
              continue;
              if (paramAnonymousBoolean)
              {
                paramAnonymousInt = 31;
              }
              else
              {
                paramAnonymousInt = 30;
                continue;
                if (paramAnonymousBoolean) {
                  paramAnonymousInt = 41;
                } else {
                  paramAnonymousInt = 40;
                }
              }
            }
          }
        }
      }
    });
  }
  
  public void setDirectionViewListener(DirectionViewListener paramDirectionViewListener)
  {
    this.directionViewListener = paramDirectionViewListener;
  }
  
  public class DirectionState
  {
    public int value;
    
    public DirectionState(int paramInt)
    {
      this.value = paramInt;
    }
  }
  
  public static abstract interface DirectionViewListener
  {
    public abstract void onDirectionButtonStateChanged(int paramInt, boolean paramBoolean);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\DirectionView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */