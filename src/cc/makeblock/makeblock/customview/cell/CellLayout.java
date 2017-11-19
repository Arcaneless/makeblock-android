package cc.makeblock.makeblock.customview.cell;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.percent.PercentFrameLayout;
import android.support.percent.PercentFrameLayout.LayoutParams;
import android.support.percent.PercentLayoutHelper.PercentLayoutInfo;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import cc.makeblock.makeblock.R.styleable;
import cc.makeblock.makeblock.base.ToastUtil;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.panel.SpeakerView;
import cc.makeblock.makeblock.engine.protocol.web.receive.SendValueToWidgetJson;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import cc.makeblock.makeblock.scene.panel.WidgetFactory;

public class CellLayout
  extends PercentFrameLayout
{
  private CellLayoutListener cellLayoutListener;
  private int cell_height_count;
  private int cell_width_count;
  private CellView currentDragCellView;
  private float currentDragX;
  private float currentDragY;
  private boolean isDragging;
  private SendWidgetValueCallback sendWidgetValueCallback;
  private View shadowView;
  private float toolbarHeight;
  
  public CellLayout(Context paramContext)
  {
    super(paramContext);
    init(null, 0);
  }
  
  public CellLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet, 0);
  }
  
  public CellLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet, paramInt);
  }
  
  private void changeShadowState(boolean paramBoolean)
  {
    if (this.shadowView == null)
    {
      this.shadowView = new View(getContext());
      this.shadowView.setBackgroundResource(2131034162);
      addView(this.shadowView);
    }
    if (paramBoolean)
    {
      int i = (int)(this.currentDragCellView.getCellWidth() * getWidth() / this.cell_width_count);
      int j = (int)(this.currentDragCellView.getCellHeight() * getHeight() / this.cell_height_count);
      float f1 = this.currentDragX / this.cell_width_count;
      float f2 = getWidth();
      float f3 = this.currentDragY / this.cell_height_count;
      float f4 = getHeight();
      PercentFrameLayout.LayoutParams localLayoutParams = new PercentFrameLayout.LayoutParams(i, j);
      this.shadowView.setLayoutParams(localLayoutParams);
      this.shadowView.setX(f1 * f2);
      this.shadowView.setY(f3 * f4);
      this.shadowView.setVisibility(0);
      return;
    }
    this.shadowView.setVisibility(8);
  }
  
  private PercentFrameLayout.LayoutParams createChildLayoutParams(CellView paramCellView)
  {
    PercentFrameLayout.LayoutParams localLayoutParams = new PercentFrameLayout.LayoutParams(getContext(), null);
    PercentLayoutHelper.PercentLayoutInfo localPercentLayoutInfo = localLayoutParams.getPercentLayoutInfo();
    localPercentLayoutInfo.widthPercent = (paramCellView.getCellWidth() / this.cell_width_count);
    localPercentLayoutInfo.heightPercent = (paramCellView.getCellHeight() / this.cell_height_count);
    localPercentLayoutInfo.leftMarginPercent = (paramCellView.getCellX() / this.cell_width_count);
    localPercentLayoutInfo.topMarginPercent = (paramCellView.getCellY() / this.cell_height_count);
    return localLayoutParams;
  }
  
  private void init(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.CellLayout, paramInt, 0);
    this.cell_height_count = paramAttributeSet.getInt(0, 0);
    this.cell_width_count = paramAttributeSet.getInt(1, 0);
    paramAttributeSet.recycle();
    if (isInEditMode())
    {
      this.toolbarHeight = 127.98F;
      return;
    }
    this.toolbarHeight = (getResources().getFraction(2131230723, 1, 1) * ScreenHelper.SCREEN_HEIGHT);
  }
  
  private boolean isCollision(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      Object localObject = getChildAt(i);
      if (((localObject instanceof CellView)) && (localObject != this.currentDragCellView))
      {
        localObject = (CellView)localObject;
        float f1 = ((CellView)localObject).getCellX();
        float f2 = ((CellView)localObject).getCellY();
        localObject = new RectF(f1, f2, f1 + ((CellView)localObject).getCellWidth(), f2 + ((CellView)localObject).getCellHeight());
        f1 = paramInt1;
        f2 = paramInt2;
        if (((RectF)localObject).intersect(new RectF(f1, f2, f1 + paramInt4, f2 + paramInt3))) {
          return true;
        }
      }
      i += 1;
    }
    return false;
  }
  
  private int x2CellX(float paramFloat)
  {
    return Math.round(this.cell_width_count * paramFloat / getWidth());
  }
  
  private int y2CellY(float paramFloat)
  {
    return Math.round(this.cell_height_count * paramFloat / getHeight());
  }
  
  public void addCellView(CellView paramCellView)
  {
    addView(paramCellView, createChildLayoutParams(paramCellView));
  }
  
  public int getCellViewCount(Class paramClass)
  {
    int j = 0;
    int i = 0;
    while (i < getChildCount())
    {
      int k = j;
      if (paramClass.isInstance(getChildAt(i))) {
        k = j + 1;
      }
      i += 1;
      j = k;
    }
    return j;
  }
  
  public RectF getCellViewShowRectF(float paramFloat1, float paramFloat2)
  {
    float f3 = getHeight() * paramFloat2 / this.cell_height_count;
    float f4 = getWidth() * paramFloat1 / this.cell_width_count;
    float f1 = getHeight() / this.cell_height_count;
    float f2 = getWidth() / this.cell_width_count;
    if (f1 > f2) {
      f1 = f2;
    }
    for (;;)
    {
      paramFloat2 = f1 * paramFloat2;
      paramFloat1 = f1 * paramFloat1;
      return new RectF((f4 - paramFloat1) / 2.0F, (f3 - paramFloat2) / 2.0F, (f4 + paramFloat1) / 2.0F, (f3 + paramFloat2) / 2.0F);
    }
  }
  
  public RectF getCellViewShowRectF(CellView paramCellView)
  {
    return getCellViewShowRectF(paramCellView.getCellWidth(), paramCellView.getCellHeight());
  }
  
  public Point getCellViewSize(CellView paramCellView)
  {
    float f = getHeight() * paramCellView.getCellHeight() / this.cell_height_count;
    return new Point((int)(getWidth() * paramCellView.getCellWidth() / this.cell_width_count), (int)f);
  }
  
  public int getCell_height_count()
  {
    return this.cell_height_count;
  }
  
  public int getCell_width_count()
  {
    return this.cell_width_count;
  }
  
  public boolean isDragging()
  {
    return this.isDragging;
  }
  
  public void onDrag(MotionEvent paramMotionEvent)
  {
    if (this.currentDragCellView == null) {}
    int k;
    int i;
    do
    {
      return;
      switch (paramMotionEvent.getAction())
      {
      default: 
        return;
      case 0: 
        if (this.currentDragCellView.getCurrentMode() == 2)
        {
          this.currentDragX = this.currentDragCellView.getCellX();
          this.currentDragY = this.currentDragCellView.getCellY();
          this.currentDragCellView.setVisibility(4);
        }
        this.isDragging = true;
        return;
      case 2: 
        float f1 = paramMotionEvent.getRawX();
        float f2 = this.currentDragCellView.getItemOffsetX();
        float f3 = paramMotionEvent.getRawY();
        float f4 = this.currentDragCellView.getItemOffsetY();
        float f5 = this.toolbarHeight;
        int j = x2CellX(f1 - f2);
        k = y2CellY(f3 - f4 - f5);
        i = j;
        if (j < 0) {
          i = 0;
        }
        j = k;
        if (k < 0) {
          j = 0;
        }
        k = i;
        if ((int)this.currentDragCellView.getCellWidth() + i > this.cell_width_count) {
          k = (int)(this.cell_width_count - this.currentDragCellView.getCellWidth());
        }
        i = j;
        if ((int)this.currentDragCellView.getCellHeight() + j > this.cell_height_count) {
          i = (int)(this.cell_height_count - this.currentDragCellView.getCellHeight());
        }
        break;
      }
    } while (isCollision(k, i, (int)this.currentDragCellView.getCellHeight(), (int)this.currentDragCellView.getCellWidth()));
    this.currentDragX = k;
    this.currentDragY = i;
    changeShadowState(true);
    return;
    if (this.currentDragCellView.getCurrentMode() == 0)
    {
      if ((this.currentDragX == -1.0F) || (this.currentDragY == -1.0F)) {
        break label489;
      }
      paramMotionEvent = WidgetFactory.createCellView(getContext(), this.currentDragCellView.getWidgetData().deepCopy(), 2);
      paramMotionEvent.setCellX(this.currentDragX);
      paramMotionEvent.setCellY(this.currentDragY);
      paramMotionEvent.setMode(2);
      if (this.cellLayoutListener != null)
      {
        boolean bool2 = true;
        boolean bool1 = bool2;
        if ((paramMotionEvent instanceof SpeakerView))
        {
          bool1 = bool2;
          if (getCellViewCount(SpeakerView.class) > 0)
          {
            ToastUtil.showToast(2131493036);
            bool1 = false;
          }
        }
        this.cellLayoutListener.onAddCellViewByDrop(paramMotionEvent, bool1);
      }
    }
    for (;;)
    {
      this.currentDragCellView.setCellX(this.currentDragX);
      this.currentDragCellView.setCellY(this.currentDragY);
      if (this.currentDragCellView != null)
      {
        this.currentDragCellView.setVisibility(0);
        this.currentDragCellView = null;
      }
      this.currentDragX = -1.0F;
      this.currentDragY = -1.0F;
      changeShadowState(false);
      this.isDragging = false;
      return;
      label489:
      if (this.cellLayoutListener != null) {
        this.cellLayoutListener.onAddCellViewByDrop(null, false);
      }
    }
  }
  
  public void onStartDrag()
  {
    if ((this.cellLayoutListener != null) && (this.currentDragCellView != null)) {
      this.cellLayoutListener.onDragStart(this.currentDragCellView.getCurrentMode());
    }
  }
  
  public void receiveWidgetValue(SendValueToWidgetJson paramSendValueToWidgetJson)
  {
    int j = getChildCount();
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        View localView = getChildAt(i);
        if ((!(localView instanceof CellView)) || (!((CellView)localView).setWidgetValue(paramSendValueToWidgetJson))) {}
      }
      else
      {
        return;
      }
      i += 1;
    }
  }
  
  public void sendWidgetValue(int paramInt1, int paramInt2)
  {
    if (this.sendWidgetValueCallback != null) {
      this.sendWidgetValueCallback.sendWidgetValue(paramInt1, paramInt2);
    }
  }
  
  public void setCellLayoutListener(CellLayoutListener paramCellLayoutListener)
  {
    this.cellLayoutListener = paramCellLayoutListener;
  }
  
  public void setCurrentDragCellView(CellView paramCellView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.currentDragCellView = paramCellView;
      this.currentDragX = -1.0F;
      this.currentDragY = -1.0F;
      return;
    }
    this.currentDragCellView = paramCellView;
    this.currentDragX = this.currentDragCellView.getCellX();
    this.currentDragY = this.currentDragCellView.getCellY();
  }
  
  public void setMode(int paramInt)
  {
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = getChildAt(i);
      if ((localView instanceof CellView)) {
        ((CellView)localView).setMode(paramInt);
      }
      i += 1;
    }
  }
  
  public void setSendWidgetValueCallback(SendWidgetValueCallback paramSendWidgetValueCallback)
  {
    this.sendWidgetValueCallback = paramSendWidgetValueCallback;
  }
  
  public void updateChildView(CellView paramCellView)
  {
    updateViewLayout(paramCellView, createChildLayoutParams(paramCellView));
  }
  
  public static abstract interface CellLayoutListener
  {
    public abstract void onAddCellViewByDrop(CellView paramCellView, boolean paramBoolean);
    
    public abstract void onDragStart(int paramInt);
  }
  
  public static abstract interface SendWidgetValueCallback
  {
    public abstract void sendWidgetValue(int paramInt1, int paramInt2);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\cell\CellLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */