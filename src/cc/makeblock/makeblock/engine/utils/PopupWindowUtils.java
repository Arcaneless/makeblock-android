package cc.makeblock.makeblock.engine.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.percent.PercentRelativeLayout;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.customview.PercentLinearLayout.LayoutParams;
import java.util.ArrayList;
import java.util.List;

public class PopupWindowUtils
{
  public static final int SIZE_DEPENDS_ON_HEIGHT = 0;
  public static final int SIZE_DEPENDS_ON_NONE = 2;
  public static final int SIZE_DEPENDS_ON_WIDTH = 1;
  private int arrowBackgroundId;
  private ColorDrawable backgroundColorDrawable;
  private List<View.OnClickListener> clickListeners;
  private List<Integer> clickableViewIds;
  private int contentBackgroundId;
  private Context context;
  private boolean dismissWhenTouchOutside;
  private List<String> dividerTextStrings;
  private int height;
  private View layoutView;
  private PopupWindow.OnDismissListener onDismissListener;
  private List<View.OnClickListener> onDividerTextClickListeners;
  private PopupWindow popupWindow;
  private View relativeParentView;
  private int sizeDependsOn;
  private float sizePercentHeight;
  private float sizePercentWidth;
  private List<String> textStrings;
  private List<Integer> textViewIds;
  private int viewId;
  private int width;
  
  private PopupWindowUtils(Builder paramBuilder)
  {
    this.viewId = paramBuilder.viewId;
    this.layoutView = paramBuilder.layoutView;
    this.sizeDependsOn = paramBuilder.sizeDependsOn;
    this.sizePercentHeight = paramBuilder.sizePercentHeight;
    this.sizePercentWidth = paramBuilder.sizePercentWidth;
    this.dismissWhenTouchOutside = paramBuilder.dismissWhenTouchOutside;
    this.onDismissListener = paramBuilder.onDismissListener;
    this.relativeParentView = paramBuilder.relativeParentView;
    this.backgroundColorDrawable = paramBuilder.backgroundColorDrawable;
    this.context = paramBuilder.context;
    this.clickableViewIds = paramBuilder.clickableViewIds;
    this.clickListeners = paramBuilder.clickListeners;
    this.textViewIds = paramBuilder.textViewIds;
    this.textStrings = paramBuilder.textStrings;
    this.arrowBackgroundId = paramBuilder.arrowBackgroundId;
    this.contentBackgroundId = paramBuilder.contentBackgroundId;
    this.dividerTextStrings = paramBuilder.dividerTextStrings;
    this.onDividerTextClickListeners = paramBuilder.onDividerTextClickListeners;
    constructPopupWindow();
  }
  
  private void constructPopupWindow()
  {
    switch (this.sizeDependsOn)
    {
    }
    int i;
    while (this.dividerTextStrings.size() > 0)
    {
      PercentRelativeLayout localPercentRelativeLayout = new PercentRelativeLayout(this.context);
      localPercentRelativeLayout.setLayoutParams(new PercentRelativeLayout.LayoutParams(-2, -1));
      localPercentRelativeLayout.setBackgroundResource(this.arrowBackgroundId);
      LinearLayout localLinearLayout = new LinearLayout(this.context, null);
      localLinearLayout.setOrientation(1);
      Object localObject1 = new LinearLayout.LayoutParams(-1, -1);
      ((LinearLayout.LayoutParams)localObject1).gravity = 1;
      localLinearLayout.setBackgroundResource(2131165345);
      localLinearLayout.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      i = 0;
      for (;;)
      {
        if (i < this.dividerTextStrings.size())
        {
          localObject1 = new AutoResizeTextView(this.context);
          ((AutoResizeTextView)localObject1).setText((CharSequence)this.dividerTextStrings.get(i));
          ((AutoResizeTextView)localObject1).setTextSize(300.0F);
          ((AutoResizeTextView)localObject1).setTextColor(this.context.getResources().getColor(2131034169));
          ((AutoResizeTextView)localObject1).setSingleLine();
          ((AutoResizeTextView)localObject1).setId(i);
          Object localObject2 = new PercentLinearLayout.LayoutParams(-2, -2);
          ((LinearLayout.LayoutParams)localObject2).weight = 1.0F;
          ((LinearLayout.LayoutParams)localObject2).gravity = 17;
          ((LinearLayout.LayoutParams)localObject2).topMargin = (ScreenHelper.SCREEN_HEIGHT * 23 / 1080);
          ((LinearLayout.LayoutParams)localObject2).bottomMargin = (ScreenHelper.SCREEN_HEIGHT * 23 / 1080);
          ((AutoResizeTextView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
          ((AutoResizeTextView)localObject1).setOnClickListener((View.OnClickListener)this.onDividerTextClickListeners.get(i));
          localLinearLayout.addView((View)localObject1);
          if (i < this.dividerTextStrings.size() - 1)
          {
            localObject1 = new View(this.context);
            ((View)localObject1).setId(i);
            ((View)localObject1).setBackgroundColor(this.context.getResources().getColor(2131034230));
            localObject2 = new LinearLayout.LayoutParams(ScreenHelper.SCREEN_WIDTH * 280 / 1920, 1);
            ((LinearLayout.LayoutParams)localObject2).gravity = 17;
            ((View)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
            localLinearLayout.addView((View)localObject1);
          }
          i += 1;
          continue;
          this.height = ((int)(ScreenHelper.SCREEN_HEIGHT * this.sizePercentHeight * ScreenHelper.SCREEN_HEIGHT / 1080.0F));
          this.width = ((int)(this.height * (ScreenHelper.SCREEN_WIDTH * this.sizePercentWidth / (ScreenHelper.SCREEN_HEIGHT * this.sizePercentHeight))));
          break;
          this.width = ((int)(ScreenHelper.SCREEN_WIDTH * this.sizePercentWidth * ScreenHelper.SCREEN_WIDTH / 1920.0F));
          this.height = ((int)(this.width * (ScreenHelper.SCREEN_HEIGHT * this.sizePercentHeight / (ScreenHelper.SCREEN_WIDTH * this.sizePercentWidth))));
          break;
          this.width = ((int)(ScreenHelper.SCREEN_WIDTH * this.sizePercentWidth));
          this.height = ((int)(ScreenHelper.SCREEN_HEIGHT * this.sizePercentHeight));
          break;
        }
      }
      localPercentRelativeLayout.addView(localLinearLayout);
      this.layoutView = localPercentRelativeLayout;
    }
    if ((this.layoutView == null) && (this.viewId != 0)) {
      this.layoutView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(this.viewId, null);
    }
    if (this.clickableViewIds.size() > 0)
    {
      i = 0;
      while (i < this.clickableViewIds.size())
      {
        this.layoutView.findViewById(((Integer)this.clickableViewIds.get(i)).intValue()).setOnClickListener((View.OnClickListener)this.clickListeners.get(i));
        i += 1;
      }
    }
    if (this.textViewIds.size() > 0)
    {
      i = 0;
      while (i < this.textViewIds.size())
      {
        ((TextView)this.layoutView.findViewById(((Integer)this.textViewIds.get(i)).intValue())).setText((CharSequence)this.textStrings.get(i));
        i += 1;
      }
    }
    if (this.contentBackgroundId > 0) {
      this.layoutView.setBackgroundResource(this.contentBackgroundId);
    }
    this.popupWindow = new PopupWindow(this.layoutView, this.width, this.height, true);
    if (this.onDismissListener != null) {
      this.popupWindow.setOnDismissListener(this.onDismissListener);
    }
    if (this.dismissWhenTouchOutside)
    {
      this.popupWindow.setTouchable(true);
      this.popupWindow.setOutsideTouchable(true);
      this.popupWindow.setBackgroundDrawable(new ColorDrawable(0));
    }
    for (;;)
    {
      if (this.backgroundColorDrawable != null) {
        this.popupWindow.setBackgroundDrawable(this.backgroundColorDrawable);
      }
      this.popupWindow.setSoftInputMode(16);
      return;
      this.popupWindow.setOutsideTouchable(false);
    }
  }
  
  public void dismiss()
  {
    this.popupWindow.dismiss();
  }
  
  public boolean isShowing()
  {
    return this.popupWindow.isShowing();
  }
  
  public void showBelowCenter()
  {
    this.popupWindow.showAsDropDown(this.relativeParentView, (this.relativeParentView.getWidth() - this.width) / 2, 0);
  }
  
  public void showBelowWithOffset(int paramInt1, int paramInt2)
  {
    this.popupWindow.showAsDropDown(this.relativeParentView, paramInt1, paramInt2);
  }
  
  public static class Builder
  {
    private int arrowBackgroundId;
    private ColorDrawable backgroundColorDrawable;
    private List<View.OnClickListener> clickListeners = new ArrayList();
    private List<Integer> clickableViewIds = new ArrayList();
    private int contentBackgroundId;
    private Context context;
    private boolean dismissWhenTouchOutside;
    private int dividerTextIndex;
    private List<String> dividerTextStrings = new ArrayList();
    private View layoutView;
    private int listenerIndex;
    private PopupWindow.OnDismissListener onDismissListener;
    private List<View.OnClickListener> onDividerTextClickListeners = new ArrayList();
    private View relativeParentView;
    private int sizeDependsOn;
    private float sizePercentHeight;
    private float sizePercentWidth;
    private List<String> textStrings = new ArrayList();
    private List<Integer> textViewIds = new ArrayList();
    private int textViewIndex;
    private int viewId;
    
    public Builder addArrowBackground(int paramInt)
    {
      this.arrowBackgroundId = paramInt;
      return this;
    }
    
    public Builder addClickableView(int paramInt, View.OnClickListener paramOnClickListener)
    {
      this.clickableViewIds.add(this.listenerIndex, Integer.valueOf(paramInt));
      this.clickListeners.add(this.listenerIndex, paramOnClickListener);
      this.listenerIndex += 1;
      return this;
    }
    
    public Builder addContentBackground(int paramInt)
    {
      this.contentBackgroundId = paramInt;
      return this;
    }
    
    public Builder addTextViewWithDivider(String paramString, View.OnClickListener paramOnClickListener)
    {
      this.dividerTextStrings.add(this.dividerTextIndex, paramString);
      this.onDividerTextClickListeners.add(this.dividerTextIndex, paramOnClickListener);
      this.dividerTextIndex += 1;
      return this;
    }
    
    public Builder addTextViewWithText(int paramInt, String paramString)
    {
      this.textViewIds.add(this.textViewIndex, Integer.valueOf(paramInt));
      this.textStrings.add(this.textViewIndex, paramString);
      this.textViewIndex += 1;
      return this;
    }
    
    public Builder backgroundColorDrawable(ColorDrawable paramColorDrawable)
    {
      this.backgroundColorDrawable = paramColorDrawable;
      return this;
    }
    
    public PopupWindowUtils build(Context paramContext)
    {
      this.context = paramContext;
      return new PopupWindowUtils(this, null);
    }
    
    public Builder dismissWhenTouchOutside(boolean paramBoolean)
    {
      this.dismissWhenTouchOutside = paramBoolean;
      return this;
    }
    
    public Builder layoutView(View paramView)
    {
      this.layoutView = paramView;
      return this;
    }
    
    public Builder onDismissListener(PopupWindow.OnDismissListener paramOnDismissListener)
    {
      this.onDismissListener = paramOnDismissListener;
      return this;
    }
    
    public Builder relativeParent(View paramView)
    {
      this.relativeParentView = paramView;
      return this;
    }
    
    public Builder sizeDependsOn(int paramInt)
    {
      this.sizeDependsOn = paramInt;
      return this;
    }
    
    public Builder sizePercent(float paramFloat1, float paramFloat2)
    {
      this.sizePercentHeight = paramFloat1;
      this.sizePercentWidth = paramFloat2;
      return this;
    }
    
    public Builder viewId(int paramInt)
    {
      this.viewId = paramInt;
      return this;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\PopupWindowUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */