package cc.makeblock.makeblock.customview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.percent.PercentRelativeLayout;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cc.makeblock.makeblock.R.styleable;
import java.util.ArrayList;
import java.util.List;

public class ToolBarLayout
  extends PercentRelativeLayout
{
  private boolean isHideBack;
  private final boolean isHideBluetooth;
  private View.OnClickListener mBackListener;
  private ImageView mBackView;
  private BluetoothIconView mBluetoothView;
  private List<View> mLeftViews = new ArrayList(2);
  private List<View> mRightViews = new ArrayList(2);
  private final String mTitle;
  private TextView mTitleView;
  
  public ToolBarLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ToolBarLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ToolBarLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.ToolBarLayout);
    this.mTitle = paramContext.getString(0);
    this.isHideBack = paramContext.getBoolean(1, false);
    this.isHideBluetooth = paramContext.getBoolean(2, true);
    paramContext.recycle();
  }
  
  private void addRule(int paramInt1, int paramInt2, View paramView)
  {
    ((RelativeLayout.LayoutParams)paramView.getLayoutParams()).addRule(paramInt1, paramInt2);
  }
  
  private void addRule(View paramView, int... paramVarArgs)
  {
    paramView = (RelativeLayout.LayoutParams)paramView.getLayoutParams();
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      paramView.addRule(paramVarArgs[i]);
      i += 1;
    }
  }
  
  private void attachView()
  {
    int j = this.mLeftViews.size();
    Object localObject = this.mBackView;
    int i = 0;
    View localView;
    if (i < j)
    {
      localView = (View)this.mLeftViews.get(i);
      if (localObject == null) {
        addRule(localView, new int[] { 15, 9 });
      }
      for (;;)
      {
        addRule(localView, new int[] { 15 });
        addView(localView);
        localObject = localView;
        i += 1;
        break;
        addRule(1, ((View)localObject).getId(), localView);
      }
    }
    localObject = this.mBluetoothView;
    j = this.mRightViews.size();
    i = 0;
    if (i < j)
    {
      localView = (View)this.mRightViews.get(i);
      if (localObject == null) {
        addRule(localView, new int[] { 15, 11 });
      }
      for (;;)
      {
        addRule(localView, new int[] { 15 });
        addView(localView);
        localObject = localView;
        i += 1;
        break;
        addRule(0, ((View)localObject).getId(), localView);
      }
    }
  }
  
  private void detachView()
  {
    int j = getChildCount();
    int i = 0;
    if (i < j)
    {
      View localView = getChildAt(i);
      if (localView.getId() <= 0) {
        localView.setId(generateViewId());
      }
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      if ((localLayoutParams.getGravity() == 8388611) || (localLayoutParams.getGravity() == 3)) {
        this.mLeftViews.add(localView);
      }
      for (;;)
      {
        i += 1;
        break;
        this.mRightViews.add(localView);
      }
    }
    removeAllViews();
  }
  
  private <T extends View> T inflaterView(@LayoutRes int paramInt)
  {
    View localView = LayoutInflater.from(getContext()).inflate(paramInt, this, false);
    if (localView.getId() <= 0) {
      localView.setId(View.generateViewId());
    }
    return localView;
  }
  
  private void initChildView()
  {
    if (!this.isHideBack)
    {
      this.mBackView = ((ImageView)inflaterView(2131427565));
      this.mBackView.setImageResource(2131165868);
      addRule(this.mBackView, new int[] { 15 });
      addView(this.mBackView);
      this.mBackView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (ToolBarLayout.this.mBackListener != null)
          {
            ToolBarLayout.this.mBackListener.onClick(paramAnonymousView);
            return;
          }
          ((Activity)ToolBarLayout.this.getContext()).finish();
        }
      });
    }
    this.mTitleView = ((TextView)inflaterView(2131427567));
    this.mTitleView.setText(this.mTitle);
    addRule(this.mTitleView, new int[] { 13 });
    addView(this.mTitleView);
    if (!this.isHideBluetooth)
    {
      this.mBluetoothView = ((BluetoothIconView)inflaterView(2131427566));
      addRule(this.mBluetoothView, new int[] { 15, 11 });
      addView(this.mBluetoothView);
    }
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(-2, -2);
  }
  
  public PercentRelativeLayout.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    detachView();
    initChildView();
    attachView();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }
  
  public void setOnBackClickListener(View.OnClickListener paramOnClickListener)
  {
    this.mBackListener = paramOnClickListener;
  }
  
  public void setTitle(String paramString)
  {
    this.mTitleView.setText(paramString);
  }
  
  public static class LayoutParams
    extends PercentRelativeLayout.LayoutParams
  {
    private int[] attrArrays = { 16842931 };
    private int gravity = -1;
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, this.attrArrays);
      this.gravity = paramContext.getInt(0, -1);
      paramContext.recycle();
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    public int getGravity()
    {
      return this.gravity;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\ToolBarLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */