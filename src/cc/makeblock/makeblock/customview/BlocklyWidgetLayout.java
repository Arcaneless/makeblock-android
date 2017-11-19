package cc.makeblock.makeblock.customview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cc.makeblock.makeblock.customview.cell.CellView;

public class BlocklyWidgetLayout
  extends LinearLayout
  implements View.OnClickListener
{
  private static final String TAG = BlocklyWidgetLayout.class.getSimpleName();
  private OnShowListener listener;
  private Point size = new Point();
  LinearLayout touchArea;
  private int widgetAreaPaddingRight;
  private boolean widgetIsShown;
  private ViewGroup widget_area;
  private ImageView widget_hide_arrow;
  private LinearLayout widget_holder;
  
  public BlocklyWidgetLayout(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0);
  }
  
  public BlocklyWidgetLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0);
  }
  
  public BlocklyWidgetLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    inflate(paramContext, 2131427463, this);
    this.widget_hide_arrow = ((ImageView)findViewById(2131296773));
    this.widget_area = ((ViewGroup)findViewById(2131296770));
    this.touchArea = ((LinearLayout)findViewById(2131296734));
    this.widget_holder = ((LinearLayout)findViewById(2131296774));
    this.widgetAreaPaddingRight = this.widget_area.getPaddingRight();
    this.widgetIsShown = false;
    this.touchArea.setOnClickListener(this);
  }
  
  private void setWidgetIsShown(boolean paramBoolean)
  {
    this.widgetIsShown = paramBoolean;
    if (this.listener != null) {
      this.listener.onShow(this.widgetIsShown);
    }
  }
  
  public void hideWidget()
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.widget_holder, "translationX", new float[] { 0.0F, this.size.x + this.widget_area.getPaddingRight() * 2 });
    localObjectAnimator.setDuration(300L);
    localObjectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    localObjectAnimator.start();
    this.widget_hide_arrow.setImageResource(2131165696);
    setWidgetIsShown(false);
  }
  
  public void onClick(View paramView)
  {
    if (this.widgetIsShown)
    {
      hideWidget();
      return;
    }
    showWidget();
  }
  
  public void setCellView(CellView paramCellView, Point paramPoint)
  {
    this.size = paramPoint;
    setWidgetIsShown(false);
    this.widget_hide_arrow.setImageResource(2131165696);
    this.widget_area.removeAllViews();
    this.widget_area.addView(paramCellView);
    ViewGroup.LayoutParams localLayoutParams = paramCellView.getLayoutParams();
    localLayoutParams.height = paramPoint.y;
    localLayoutParams.width = (paramPoint.x + this.widgetAreaPaddingRight);
    paramCellView.setLayoutParams(localLayoutParams);
    this.widget_holder.setX(paramPoint.x + this.widget_area.getPaddingRight() * 2);
  }
  
  public void setOnShowListener(OnShowListener paramOnShowListener)
  {
    this.listener = paramOnShowListener;
  }
  
  public void showWidget()
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.widget_holder, "translationX", new float[] { this.widget_holder.getX(), 0.0F });
    localObjectAnimator.setDuration(300L);
    localObjectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    localObjectAnimator.start();
    this.widget_hide_arrow.setImageResource(2131165697);
    setWidgetIsShown(true);
  }
  
  public static abstract interface OnShowListener
  {
    public abstract void onShow(boolean paramBoolean);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\BlocklyWidgetLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */