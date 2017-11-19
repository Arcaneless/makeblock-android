package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import cc.makeblock.makeblock.R.styleable;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import java.util.ArrayList;
import java.util.List;

public class PathMap
  extends View
  implements View.OnTouchListener
{
  private static final long DEFAULT_INTERVAL = 20L;
  private static final float DEFAULT_LINE_WIDTH = 10.0F;
  private int X_COUNT = 115;
  private int Y_COUNT = 65;
  private int bgColor;
  private int bgCornerRadius;
  private Paint bgPaint;
  private RectF bgRect;
  private Paint blankPaint = new Paint();
  private volatile List<PointF> chosenPointList;
  private boolean isFinish = true;
  private PointF lastPoint;
  private float lineWidth;
  private HandleState mHandleState = HandleState.INIT;
  private RefreshStateRunnable mRefreshStateRunnable;
  private volatile int passedIndex;
  private Path passedPath = new Path();
  private int passedPathColor;
  private Paint passedPathPaint;
  private Path path = new Path();
  private int pathColor;
  private PathFinderHelper pathFinderHelper;
  private List<PointF> pathList;
  private PathMapChangeListener pathMapChangeListener;
  private List<Integer> pathMapList;
  private Paint pathPaint;
  private float perGridX;
  private float perGridY;
  private PointF pressPoint;
  private Bitmap symbol;
  private Rect symbolRect;
  private float symbolShowHeight;
  private RectF symbolShowRect;
  private float symbolShowWidth;
  
  public PathMap(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0);
  }
  
  public PathMap(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0);
  }
  
  public PathMap(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt);
  }
  
  private void addToPathList(PointF paramPointF)
  {
    if (this.pathList.size() > 0)
    {
      PointF localPointF = (PointF)this.pathList.get(this.pathList.size() - 1);
      if (Math.pow(paramPointF.x - localPointF.x, 2.0D) + Math.pow(paramPointF.y - localPointF.y, 2.0D) > Math.pow(this.lineWidth, 2.0D)) {
        this.pathList.add(paramPointF);
      }
      return;
    }
    this.pathList.add(paramPointF);
  }
  
  private void handlePress(PointF paramPointF)
  {
    if (this.lastPoint != null) {}
    this.chosenPointList.add(paramPointF);
    this.pathMapList.add(Integer.valueOf(this.pathList.size()));
    this.lastPoint = paramPointF;
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.PathMap, paramInt, 0);
    this.bgColor = paramContext.getColor(0, getContext().getResources().getColor(2131034226));
    this.bgCornerRadius = paramContext.getInt(1, 0);
    paramContext.recycle();
    this.pathColor = getContext().getResources().getColor(2131034227);
    this.passedPathColor = getContext().getResources().getColor(2131034228);
    this.pathPaint = new Paint();
    this.pathPaint.setColor(this.pathColor);
    this.pathPaint.setStyle(Paint.Style.STROKE);
    this.pathPaint.setAntiAlias(true);
    this.pathPaint.setDither(true);
    this.passedPathPaint = new Paint(this.pathPaint);
    this.passedPathPaint.setColor(this.passedPathColor);
    this.chosenPointList = new ArrayList();
    this.pathList = new ArrayList();
    this.pathMapList = new ArrayList();
    setOnTouchListener(this);
    this.symbol = ((BitmapDrawable)ContextCompat.getDrawable(getContext(), 2131165724)).getBitmap();
    this.symbolRect = new Rect(0, 0, this.symbol.getWidth(), this.symbol.getHeight());
    this.symbolShowRect = new RectF(0.0F, 0.0F, 0.0F, 0.0F);
    this.bgPaint = new Paint();
    this.bgPaint.setColor(this.bgColor);
    this.bgRect = new RectF();
  }
  
  private boolean isTooClose(PointF paramPointF1, PointF paramPointF2)
  {
    boolean bool2 = false;
    boolean bool1;
    if ((paramPointF1 == null) || (paramPointF2 == null))
    {
      bool1 = bool2;
      if (paramPointF1 == null)
      {
        bool1 = bool2;
        if (paramPointF2 == null) {
          throw new RuntimeException("你在搞笑?");
        }
      }
    }
    else
    {
      bool1 = bool2;
      if (Math.pow(paramPointF1.x - paramPointF2.x, 2.0D) + Math.pow(paramPointF1.y - paramPointF2.y, 2.0D) < 1.0D) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private void onPress(PointF paramPointF)
  {
    if (isTooClose(paramPointF, this.lastPoint)) {
      return;
    }
    switch (this.mHandleState)
    {
    default: 
      return;
    case ???: 
      if (this.mRefreshStateRunnable == null) {
        this.mRefreshStateRunnable = new RefreshStateRunnable(null);
      }
      handlePress(paramPointF);
      postDelayed(this.mRefreshStateRunnable, 20L);
      this.mHandleState = HandleState.SEND;
      return;
    case ???: 
      this.pressPoint = paramPointF;
      this.mHandleState = HandleState.WAIT;
      return;
    }
    this.pressPoint = paramPointF;
  }
  
  private PointF pixelToLogic(PointF paramPointF)
  {
    return new PointF(paramPointF.x / this.perGridX, (getHeight() - paramPointF.y) / this.perGridY);
  }
  
  private void resetList()
  {
    this.chosenPointList.clear();
    this.pathMapList.clear();
    this.pathList.clear();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    this.bgRect.left = 0.0F;
    this.bgRect.top = 0.0F;
    this.bgRect.right = getWidth();
    this.bgRect.bottom = getHeight();
    paramCanvas.drawRoundRect(this.bgRect, this.bgCornerRadius, this.bgCornerRadius, this.bgPaint);
    PointF localPointF1 = null;
    PointF localPointF2 = localPointF1;
    if (this.chosenPointList != null)
    {
      localPointF2 = localPointF1;
      if (this.pathList.size() > 0)
      {
        int i = 0;
        this.passedPath.reset();
        localPointF1 = null;
        if ((i < this.pathList.size()) && (this.passedIndex >= 0) && ((i <= ((Integer)this.pathMapList.get(this.passedIndex)).intValue()) || (this.passedIndex == this.pathMapList.size() - 1)))
        {
          localPointF1 = (PointF)this.pathList.get(i);
          if (this.passedPath.isEmpty()) {
            this.passedPath.moveTo(localPointF1.x, localPointF1.y);
          }
          for (;;)
          {
            i += 1;
            break;
            this.passedPath.lineTo(localPointF1.x, localPointF1.y);
          }
        }
        paramCanvas.drawPath(this.passedPath, this.passedPathPaint);
        this.path.reset();
        if (localPointF1 == null) {
          localPointF1 = (PointF)this.pathList.get(0);
        }
        for (localPointF2 = localPointF1;; localPointF2 = localPointF1)
        {
          this.path.moveTo(localPointF1.x, localPointF1.y);
          while (i < this.pathList.size())
          {
            localPointF1 = (PointF)this.pathList.get(i);
            this.path.lineTo(localPointF1.x, localPointF1.y);
            i += 1;
          }
        }
        paramCanvas.drawPath(this.path, this.pathPaint);
      }
    }
    if (localPointF2 != null)
    {
      this.symbolShowRect.set(localPointF2.x - this.symbolShowWidth / 2.0F, localPointF2.y - this.symbolShowHeight / 2.0F, localPointF2.x + this.symbolShowWidth / 2.0F, localPointF2.y + this.symbolShowWidth / 2.0F);
      paramCanvas.drawBitmap(this.symbol, this.symbolRect, this.symbolShowRect, this.blankPaint);
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.X_COUNT > paramInt1) {
      this.X_COUNT = paramInt1;
    }
    if (this.Y_COUNT > paramInt2) {
      this.Y_COUNT = paramInt2;
    }
    this.perGridX = (paramInt1 / this.X_COUNT);
    this.perGridY = (paramInt2 / this.Y_COUNT);
    this.symbolShowHeight = this.symbol.getHeight();
    this.symbolShowWidth = this.symbol.getWidth();
    this.lineWidth = (0.0052083335F * ScreenHelper.SCREEN_WIDTH);
    this.pathPaint.setStrokeWidth(this.lineWidth);
    this.passedPathPaint.setStrokeWidth(this.lineWidth);
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    boolean bool = false;
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      bool = true;
      do
      {
        return bool;
        if (this.isFinish)
        {
          reset();
          invalidate();
        }
      } while (this.chosenPointList.size() != 0);
      int j = paramMotionEvent.getHistorySize();
      int i = 0;
      while (i < j)
      {
        addToPathList(new PointF(paramMotionEvent.getHistoricalX(i), paramMotionEvent.getHistoricalY(i)));
        i += 1;
      }
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      paramView = new PointF(f1, f2);
      addToPathList(paramView);
      invalidate();
      if ((f1 >= 0.0F) && (f1 <= getWidth()) && (f2 >= 0.0F) && (f2 <= getHeight()))
      {
        onPress(pixelToLogic(paramView));
        continue;
        this.lastPoint = null;
        this.isFinish = false;
        if (this.pathMapChangeListener == null) {}
      }
    }
  }
  
  public void pause()
  {
    this.pathFinderHelper.pauseDrawing();
  }
  
  public boolean play()
  {
    if ((this.chosenPointList.size() <= 1) || (this.isFinish)) {
      return false;
    }
    if (this.pathFinderHelper == null)
    {
      this.pathFinderHelper = new PathFinderHelper(this.X_COUNT, this.Y_COUNT);
      this.pathFinderHelper.setOnCurrentPointDrawFinishListener(new PathFinderHelper.OnCurrentPointDrawFinishListener()
      {
        public void onCurrentPointDrawFinish(int paramAnonymousInt)
        {
          PathMap.access$002(PathMap.this, paramAnonymousInt);
          PathMap.this.postInvalidate();
          if (PathMap.this.passedIndex == PathMap.this.chosenPointList.size() - 1) {
            PathMap.access$202(PathMap.this, true);
          }
          if ((PathMap.this.isFinish) && (PathMap.this.pathMapChangeListener != null)) {
            PathMap.this.pathMapChangeListener.onExecuteFinish();
          }
        }
      });
      this.pathFinderHelper.setPointsList(this.chosenPointList);
    }
    this.pathFinderHelper.startDrawing();
    return true;
  }
  
  public void reset()
  {
    if (this.pathFinderHelper != null) {
      this.pathFinderHelper.stopDrawing();
    }
    this.pathFinderHelper = null;
    this.passedIndex = -1;
    this.isFinish = true;
    resetList();
    postInvalidate();
  }
  
  public void setPathMapChangeListener(PathMapChangeListener paramPathMapChangeListener)
  {
    this.pathMapChangeListener = paramPathMapChangeListener;
  }
  
  private static enum HandleState
  {
    INIT,  SEND,  WAIT;
    
    private HandleState() {}
  }
  
  public static abstract interface PathMapChangeListener
  {
    public abstract void onDrawPathFinish();
    
    public abstract void onExecuteFinish();
  }
  
  private class RefreshStateRunnable
    implements Runnable
  {
    private RefreshStateRunnable() {}
    
    public void run()
    {
      switch (PathMap.2.$SwitchMap$cc$makeblock$makeblock$customview$panel$PathMap$HandleState[PathMap.this.mHandleState.ordinal()])
      {
      default: 
        return;
      case 1: 
        throw new RuntimeException("不应该进到这里的");
      case 2: 
        PathMap.access$502(PathMap.this, PathMap.HandleState.INIT);
        return;
      }
      PathMap.this.handlePress(PathMap.this.pressPoint);
      PathMap.access$502(PathMap.this, PathMap.HandleState.SEND);
      PathMap.this.postDelayed(this, 20L);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\PathMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */