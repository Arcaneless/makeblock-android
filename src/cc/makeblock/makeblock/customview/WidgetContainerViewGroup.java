package cc.makeblock.makeblock.customview;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.percent.PercentRelativeLayout;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import cc.makeblock.makeblock.bean.BoardGroupBean;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.cell.CellLayout;
import cc.makeblock.makeblock.customview.cell.CellView;
import cc.makeblock.makeblock.customview.panel.ChartLayout;
import cc.makeblock.makeblock.customview.panel.MusicKeyView;
import cc.makeblock.makeblock.customview.panel.PathMapView;
import cc.makeblock.makeblock.customview.panel.SliderDuplex;
import cc.makeblock.makeblock.customview.panel.SliderHorizontal;
import cc.makeblock.makeblock.engine.utils.AssetsUtils;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import cc.makeblock.makeblock.scene.panel.WidgetFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WidgetContainerViewGroup
  extends PercentRelativeLayout
{
  private static final int CONTAINER_TURN_ON_ANIMATION_DURATION = 300;
  private static final float TAB_HEIGHT_PERCENT = 0.17F;
  private static final int TAB_SELECTED_ANIMATION_DURATION = 150;
  private static final int WIDGET_CONTAINER_MARGIN_UNIT = ScreenHelper.getPercentHeightToPx(0.01F);
  private static final int WIDGET_MARGIN_UNIT = ScreenHelper.getPercentHeightToPx(0.01F);
  private int CONTAINER_HIDE_X;
  private List<BoardGroupBean> boardGroupBeanList;
  private List<CellView> cellViewList = new ArrayList();
  private Context context;
  private int lastTabPivotY;
  private LayoutInflater layoutInflater;
  private int mSelectedIndex = -1;
  private boolean mStateOn = false;
  private LinearLayout widgetContainer;
  private List<List<WidgetData>> widgetDataLists = new ArrayList();
  private ImageView widgetMenuIndicator;
  private PercentRelativeLayout widgetTabContainer;
  
  public WidgetContainerViewGroup(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    init();
  }
  
  public WidgetContainerViewGroup(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    init();
  }
  
  private void cacheCellView(CellView paramCellView)
  {
    Object localObject = ((CellLayout)((ViewGroup)getParent()).findViewById(2131296604)).getCellViewShowRectF(paramCellView);
    int i = (int)((((RectF)localObject).right - ((RectF)localObject).left) * 2.0F / 3.0F);
    int j = (int)((((RectF)localObject).bottom - ((RectF)localObject).top) * 2.0F / 3.0F);
    if (((paramCellView instanceof SliderHorizontal)) || ((paramCellView instanceof ChartLayout)))
    {
      i = (int)((((RectF)localObject).right - ((RectF)localObject).left) * 0.62F);
      j = (int)((((RectF)localObject).bottom - ((RectF)localObject).top) * 0.62F);
    }
    if ((paramCellView instanceof SliderDuplex))
    {
      i = (int)((((RectF)localObject).right - ((RectF)localObject).left) * 0.62F);
      j = (int)((((RectF)localObject).bottom - ((RectF)localObject).top) * 0.62F);
    }
    if ((paramCellView instanceof MusicKeyView))
    {
      i = (int)((((RectF)localObject).right - ((RectF)localObject).left) * 31.0F / 90.0F);
      j = (int)((((RectF)localObject).bottom - ((RectF)localObject).top) * 31.0F / 90.0F);
    }
    if ((paramCellView instanceof MusicKeyView))
    {
      i = (int)((((RectF)localObject).right - ((RectF)localObject).left) * 31.0F / 90.0F);
      j = (int)((((RectF)localObject).bottom - ((RectF)localObject).top) * 31.0F / 90.0F);
    }
    if ((paramCellView instanceof PathMapView))
    {
      i = (int)((((RectF)localObject).right - ((RectF)localObject).left) * 25.0F / 90.0F);
      j = (int)((((RectF)localObject).bottom - ((RectF)localObject).top) * 25.0F / 90.0F);
    }
    localObject = new LinearLayout.LayoutParams(i, j);
    ((LinearLayout.LayoutParams)localObject).setMargins(WIDGET_MARGIN_UNIT, WIDGET_MARGIN_UNIT * 2, WIDGET_MARGIN_UNIT, 0);
    if ((paramCellView != null) && (i > 0) && (j > 0))
    {
      paramCellView.setLayoutParams((ViewGroup.LayoutParams)localObject);
      this.cellViewList.add(paramCellView);
    }
  }
  
  private void changeTabSelectState(int paramInt)
  {
    int i = 0;
    if (i < this.widgetTabContainer.getChildCount() - 1)
    {
      Object localObject = (PercentRelativeLayout)this.widgetTabContainer.getChildAt(i + 1);
      ImageView localImageView = (ImageView)((PercentRelativeLayout)localObject).getChildAt(0);
      localObject = (TextView)((PercentRelativeLayout)localObject).getChildAt(1);
      if (i == paramInt)
      {
        localImageView.setSelected(true);
        ((TextView)localObject).setSelected(true);
      }
      for (;;)
      {
        i += 1;
        break;
        localImageView.setSelected(false);
        ((TextView)localObject).setSelected(false);
      }
    }
  }
  
  private CellView getCachedCellView(WidgetData paramWidgetData)
  {
    Iterator localIterator = this.cellViewList.iterator();
    while (localIterator.hasNext())
    {
      CellView localCellView = (CellView)localIterator.next();
      if (localCellView.getWidgetData().equals(paramWidgetData)) {
        return localCellView;
      }
    }
    return null;
  }
  
  private void inflateTabs()
  {
    int i = 0;
    while (i < this.boardGroupBeanList.size())
    {
      Object localObject1 = (BoardGroupBean)this.boardGroupBeanList.get(i);
      Object localObject2 = this.layoutInflater.inflate(2131427439, null);
      Object localObject3 = new PercentRelativeLayout.LayoutParams(-1, 0);
      ((PercentRelativeLayout.LayoutParams)localObject3).getPercentLayoutInfo().heightPercent = 0.17F;
      ((PercentRelativeLayout.LayoutParams)localObject3).getPercentLayoutInfo().topMarginPercent = (i * 0.17F);
      ((View)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject3);
      ((View)localObject2).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          WidgetContainerViewGroup.this.setTabSelected(((Integer)paramAnonymousView.getTag()).intValue());
        }
      });
      ((View)localObject2).setTag(Integer.valueOf(i));
      this.widgetTabContainer.addView((View)localObject2);
      localObject3 = (TextView)((View)localObject2).findViewById(2131296739);
      ((TextView)localObject3).setText(((BoardGroupBean)localObject1).groupName);
      ((TextView)localObject3).setTextAppearance(this.context, 2131558826);
      localObject2 = (ImageView)((View)localObject2).findViewById(2131296506);
      localObject3 = new StateListDrawable();
      Bitmap localBitmap = AssetsUtils.getImageFromAssetsFile(this.context, ((BoardGroupBean)localObject1).icon);
      localObject1 = new BitmapDrawable(AssetsUtils.getImageFromAssetsFile(this.context, ((BoardGroupBean)localObject1).icon_pre));
      ((StateListDrawable)localObject3).addState(new int[] { 16842913 }, (Drawable)localObject1);
      localObject1 = new BitmapDrawable(localBitmap);
      ((StateListDrawable)localObject3).addState(new int[] { 16842910 }, (Drawable)localObject1);
      ((ImageView)localObject2).setImageDrawable((Drawable)localObject3);
      i += 1;
    }
  }
  
  private void init()
  {
    if (!isInEditMode()) {
      ScreenHelper.updateDeviceInfo((Activity)this.context);
    }
    this.CONTAINER_HIDE_X = (-ScreenHelper.getPercentWidthToPx(0.4F));
    this.layoutInflater = ((LayoutInflater)this.context.getSystemService("layout_inflater"));
    Object localObject1 = (ViewGroup)this.layoutInflater.inflate(2131427502, this);
    this.widgetTabContainer = ((PercentRelativeLayout)((ViewGroup)localObject1).findViewById(2131296780));
    localObject1 = (LinearLayout)((ViewGroup)localObject1).findViewById(2131296771);
    this.widgetMenuIndicator = new ImageView(this.context);
    this.widgetMenuIndicator.setImageResource(2131165596);
    Object localObject2 = new PercentRelativeLayout.LayoutParams(-1, 0);
    ((PercentRelativeLayout.LayoutParams)localObject2).getPercentLayoutInfo().heightPercent = 0.17F;
    this.widgetMenuIndicator.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    this.widgetMenuIndicator.setScaleType(ImageView.ScaleType.FIT_XY);
    this.widgetTabContainer.addView(this.widgetMenuIndicator);
    this.widgetContainer = new LinearLayout(this.context);
    localObject2 = new LinearLayout.LayoutParams(-1, -1);
    ((LinearLayout.LayoutParams)localObject2).setMargins(WIDGET_CONTAINER_MARGIN_UNIT, WIDGET_CONTAINER_MARGIN_UNIT * 3, WIDGET_CONTAINER_MARGIN_UNIT, WIDGET_CONTAINER_MARGIN_UNIT * 3);
    this.widgetContainer.setOrientation(1);
    this.widgetContainer.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    ((LinearLayout)localObject1).addView(this.widgetContainer);
    setX(this.CONTAINER_HIDE_X);
  }
  
  private void setTabSelected(int paramInt)
  {
    if (this.mSelectedIndex == paramInt) {}
    for (;;)
    {
      return;
      this.mSelectedIndex = paramInt;
      final Object localObject = new TranslateAnimation(0.0F, 0.0F, this.lastTabPivotY, this.widgetMenuIndicator.getHeight() * paramInt);
      ((TranslateAnimation)localObject).setDuration(150L);
      ((TranslateAnimation)localObject).setInterpolator(new AccelerateDecelerateInterpolator());
      ((TranslateAnimation)localObject).setFillAfter(true);
      changeTabSelectState(paramInt);
      this.widgetMenuIndicator.post(new Runnable()
      {
        public void run()
        {
          WidgetContainerViewGroup.this.widgetMenuIndicator.startAnimation(localObject);
        }
      });
      this.lastTabPivotY = (this.widgetMenuIndicator.getHeight() * paramInt);
      this.widgetContainer.removeAllViews();
      localObject = (List)this.widgetDataLists.get(paramInt);
      paramInt = 0;
      while (paramInt < ((List)localObject).size())
      {
        CellView localCellView = getCachedCellView((WidgetData)((List)localObject).get(paramInt));
        if (localCellView != null) {
          this.widgetContainer.addView(localCellView);
        }
        paramInt += 1;
      }
    }
  }
  
  public void setBoardGroupBeanList(List<BoardGroupBean> paramList)
  {
    this.boardGroupBeanList = paramList;
    inflateTabs();
  }
  
  public void setTurnOn(boolean paramBoolean)
  {
    if (paramBoolean == this.mStateOn) {
      return;
    }
    this.mStateOn = paramBoolean;
    if (paramBoolean)
    {
      localObjectAnimator = ObjectAnimator.ofFloat(this, "X", new float[] { this.CONTAINER_HIDE_X, 0.0F });
      localObjectAnimator.setDuration(300L);
      localObjectAnimator.setInterpolator(new DecelerateInterpolator());
      localObjectAnimator.start();
      return;
    }
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this, "X", new float[] { 0.0F, this.CONTAINER_HIDE_X });
    localObjectAnimator.setDuration(300L);
    localObjectAnimator.setInterpolator(new DecelerateInterpolator());
    localObjectAnimator.start();
  }
  
  public void setWidgetDataList(final List<List<WidgetData>> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return;
    }
    this.widgetDataLists = paramList;
    post(new Runnable()
    {
      public void run()
      {
        int i = 0;
        while (i < paramList.size())
        {
          List localList = (List)paramList.get(i);
          int j = 0;
          while (j < localList.size())
          {
            Object localObject = (WidgetData)localList.get(j);
            localObject = WidgetFactory.createCellView(WidgetContainerViewGroup.this.context, (WidgetData)localObject, 0);
            WidgetContainerViewGroup.this.cacheCellView((CellView)localObject);
            j += 1;
          }
          i += 1;
        }
        WidgetContainerViewGroup.this.setTabSelected(0);
      }
    });
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\WidgetContainerViewGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */