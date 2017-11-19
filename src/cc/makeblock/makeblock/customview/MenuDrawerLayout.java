package cc.makeblock.makeblock.customview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v4.widget.DrawerLayout.LayoutParams;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import cc.makeblock.makeblock.R.styleable;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import java.util.LinkedList;
import java.util.List;

public class MenuDrawerLayout
  extends DrawerLayout
  implements DrawerLayout.DrawerListener
{
  private boolean animatorMode = false;
  private boolean hasPost = false;
  private View headView;
  private boolean isFirstAdding = true;
  private LinearLayout.LayoutParams itemLayoutParams;
  private FrameLayout mFlHeader;
  private LinearLayout mMenuLinearLayout;
  private OnHideListener mOnHideListener;
  private OnShowListener mOnShowListener;
  private LinkedList<View> waitAddViews;
  
  public MenuDrawerLayout(Context paramContext)
  {
    super(paramContext);
    initView(paramContext, null);
  }
  
  public MenuDrawerLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext, paramAttributeSet);
  }
  
  public MenuDrawerLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initView(paramContext, paramAttributeSet);
  }
  
  private void addMenuLayout()
  {
    View localView = LayoutInflater.from(getContext()).inflate(2131427561, this, false);
    localView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView) {}
    });
    this.mFlHeader = ((FrameLayout)localView.findViewById(2131296456));
    this.mMenuLinearLayout = ((LinearLayout)localView.findViewById(2131296563));
    DrawerLayout.LayoutParams localLayoutParams = (DrawerLayout.LayoutParams)localView.getLayoutParams();
    localLayoutParams.gravity = 8388613;
    localLayoutParams.width = ((int)(ScreenHelper.SCREEN_WIDTH * 0.5F));
    addView(localView);
  }
  
  private void addMenus()
  {
    if (this.animatorMode) {
      if (!this.hasPost)
      {
        if (!addSingleItem()) {
          break label43;
        }
        postDelayed(new Runnable()
        {
          public void run()
          {
            MenuDrawerLayout.access$002(MenuDrawerLayout.this, false);
            MenuDrawerLayout.this.addMenus();
          }
        }, 200L);
        this.hasPost = true;
      }
    }
    for (;;)
    {
      return;
      label43:
      this.hasPost = false;
      return;
      while (addSingleItem()) {}
    }
  }
  
  private boolean addSingleItem()
  {
    final View localView = (View)this.waitAddViews.poll();
    if (localView == null) {
      return false;
    }
    if (this.isFirstAdding) {
      this.isFirstAdding = false;
    }
    for (;;)
    {
      this.mMenuLinearLayout.addView(localView, createItemLayoutParams(localView.getTag()));
      if (this.animatorMode)
      {
        localView.setVisibility(4);
        localView.post(new Runnable()
        {
          public void run()
          {
            localView.setVisibility(0);
            ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(localView, "x", new float[] { localView.getX() + localView.getWidth(), localView.getX() });
            localObjectAnimator.setDuration(500L);
            localObjectAnimator.setInterpolator(new OvershootInterpolator(1.2F));
            localObjectAnimator.start();
          }
        });
      }
      return true;
      this.mMenuLinearLayout.addView(createLineView());
    }
  }
  
  private void clearMenus()
  {
    this.mMenuLinearLayout.removeAllViews();
  }
  
  private ViewGroup.LayoutParams createItemLayoutParams(Object paramObject)
  {
    this.itemLayoutParams.height = getPercentHeight(paramObject);
    return this.itemLayoutParams;
  }
  
  private View createLineView()
  {
    View localView = new View(getContext());
    localView.setBackground(ContextCompat.getDrawable(getContext(), 2131165344));
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, (int)getContext().getResources().getDimension(2131099766));
    int i = (int)getContext().getResources().getDimension(2131099791);
    localLayoutParams.setMargins(i, 0, i, 0);
    localView.setLayoutParams(localLayoutParams);
    return localView;
  }
  
  private int getPercentHeight(Object paramObject)
  {
    int j = -2;
    if (paramObject == null) {
      return -2;
    }
    paramObject = paramObject.toString();
    int i = j;
    if (!TextUtils.isEmpty((CharSequence)paramObject))
    {
      i = j;
      if (((String)paramObject).contains("%"))
      {
        float f = Float.parseFloat(((String)paramObject).replace("%", ""));
        i = (int)(ScreenHelper.SCREEN_HEIGHT * (f * 0.01F));
      }
    }
    return i;
  }
  
  private void initView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this.waitAddViews = new LinkedList();
    this.itemLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MenuDrawerLayout, 0, 0);
    this.animatorMode = paramContext.getBoolean(0, false);
    paramContext.recycle();
    setDrawerLockMode(1);
    addDrawerListener(this);
  }
  
  public void clearMenu(boolean paramBoolean)
  {
    clearMenus();
    this.isFirstAdding = true;
  }
  
  public void onDrawerClosed(View paramView)
  {
    if (this.mOnHideListener != null) {
      this.mOnHideListener.onHide(paramView);
    }
    setDrawerLockMode(1);
  }
  
  public void onDrawerOpened(View paramView)
  {
    if (this.mOnShowListener != null) {
      this.mOnShowListener.onShow(paramView);
    }
    setDrawerLockMode(3);
  }
  
  public void onDrawerSlide(View paramView, float paramFloat) {}
  
  public void onDrawerStateChanged(int paramInt) {}
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    addMenuLayout();
  }
  
  public void setHeaderView(View paramView)
  {
    if (this.headView == paramView) {
      return;
    }
    this.mFlHeader.removeAllViews();
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, getPercentHeight(paramView.getTag()));
    this.mFlHeader.addView(paramView, localLayoutParams);
    this.headView = paramView;
  }
  
  public void setLeftMenuStatus(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      openDrawer(8388611);
      return;
    }
    closeDrawer(8388611);
  }
  
  public void setMenuViews(List<View> paramList)
  {
    this.waitAddViews.addAll(paramList);
    clearMenus();
    addMenus();
  }
  
  public void setOnHide(OnHideListener paramOnHideListener)
  {
    this.mOnHideListener = paramOnHideListener;
  }
  
  public void setOnShow(OnShowListener paramOnShowListener)
  {
    this.mOnShowListener = paramOnShowListener;
  }
  
  public void setRightMenuStatus(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      openDrawer(8388613);
      return;
    }
    closeDrawer(8388613);
  }
  
  public void setWaitAddViews(List<View> paramList)
  {
    this.waitAddViews.addAll(paramList);
    addMenus();
  }
  
  public static abstract interface OnHideListener
  {
    public abstract void onHide(View paramView);
  }
  
  public static abstract interface OnShowListener
  {
    public abstract void onShow(View paramView);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\MenuDrawerLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */