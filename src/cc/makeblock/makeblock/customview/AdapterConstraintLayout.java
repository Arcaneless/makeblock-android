package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdapterConstraintLayout
  extends ConstraintLayout
{
  private static final int DESIGN_HEIGHT = 1920;
  private static final float DESIGN_SCALE = 3.0F;
  private static final int DESIGN_WIDTH = 1080;
  private float mFontScale;
  private float mScale;
  private float mScaleX = 0.0F;
  private float mScaleY = 0.0F;
  
  public AdapterConstraintLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AdapterConstraintLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public AdapterConstraintLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramAttributeSet = new Point();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getSize(paramAttributeSet);
    if (paramAttributeSet.x > paramAttributeSet.y) {
      this.mScaleX = (paramAttributeSet.x * 1.0F / 1920.0F);
    }
    for (this.mScaleY = (paramAttributeSet.y * 1.0F / 1080.0F);; this.mScaleY = (paramAttributeSet.y * 1.0F / 1920.0F))
    {
      float f1 = 3.0F / getResources().getDisplayMetrics().density;
      float f2 = 3.0F / getResources().getDisplayMetrics().scaledDensity;
      float f3 = Math.min(this.mScaleX, this.mScaleY);
      this.mScale = (f3 * f1);
      this.mScaleX *= f1;
      this.mScaleY *= f1;
      this.mFontScale = (f3 * f2);
      return;
      this.mScaleX = (paramAttributeSet.x * 1.0F / 1080.0F);
    }
  }
  
  private void adapterMargin(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams))
    {
      paramLayoutParams = (ViewGroup.MarginLayoutParams)paramLayoutParams;
      paramLayoutParams.leftMargin = ((int)(paramLayoutParams.leftMargin * this.mScaleX));
      paramLayoutParams.topMargin = ((int)(paramLayoutParams.topMargin * this.mScaleY));
      paramLayoutParams.rightMargin = ((int)(paramLayoutParams.rightMargin * this.mScaleX));
      paramLayoutParams.bottomMargin = ((int)(paramLayoutParams.bottomMargin * this.mScaleY));
    }
  }
  
  private void adapterPadding(View paramView)
  {
    paramView.setPadding((int)(paramView.getPaddingLeft() * this.mScaleX), (int)(paramView.getPaddingTop() * this.mScaleY), (int)(paramView.getPaddingRight() * this.mScaleX), (int)(paramView.getPaddingBottom() * this.mScaleY));
  }
  
  private void adapterSize(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams.width > 0) && (paramLayoutParams.height > 0))
    {
      paramLayoutParams.width = ((int)(paramLayoutParams.width * this.mScale));
      paramLayoutParams.height = ((int)(paramLayoutParams.height * this.mScale));
    }
    do
    {
      return;
      if (paramLayoutParams.width > 0) {
        paramLayoutParams.width = ((int)(paramLayoutParams.width * this.mScaleX));
      }
    } while (paramLayoutParams.height <= 0);
    paramLayoutParams.height = ((int)(paramLayoutParams.height * this.mScaleY));
  }
  
  private void adapterTextSize(View paramView)
  {
    if ((paramView instanceof TextView))
    {
      f = ((TextView)paramView).getTextSize();
      ((TextView)paramView).setTextSize(0, this.mFontScale * f);
    }
    do
    {
      return;
      if ((paramView instanceof Button))
      {
        f = ((Button)paramView).getTextSize();
        ((Button)paramView).setTextSize(0, this.mFontScale * f);
        return;
      }
    } while (!(paramView instanceof EditText));
    float f = ((EditText)paramView).getTextSize();
    ((EditText)paramView).setTextSize(0, this.mFontScale * f);
  }
  
  private void transformSize(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (paramView.getTag(2131296481) != null) {
      return;
    }
    paramView.setTag(2131296481, Boolean.valueOf(true));
    adapterSize(paramLayoutParams);
    adapterTextSize(paramView);
    adapterMargin(paramLayoutParams);
    adapterPadding(paramView);
  }
  
  public void addView(View paramView)
  {
    super.addView(paramView);
    if (!isInEditMode()) {
      transformSize(paramView, paramView.getLayoutParams());
    }
  }
  
  public void addView(View paramView, int paramInt)
  {
    super.addView(paramView, paramInt);
    if (!isInEditMode()) {
      transformSize(paramView, paramView.getLayoutParams());
    }
  }
  
  public void addView(View paramView, int paramInt1, int paramInt2)
  {
    super.addView(paramView, paramInt1, paramInt2);
    if (!isInEditMode()) {
      transformSize(paramView, paramView.getLayoutParams());
    }
  }
  
  public void addView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (!isInEditMode()) {
      transformSize(paramView, paramLayoutParams);
    }
    super.addView(paramView, paramLayoutParams);
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    transformSize(this, getLayoutParams());
  }
  
  public void onViewRemoved(View paramView)
  {
    super.onViewRemoved(paramView);
    paramView.setTag(2131296481, null);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\AdapterConstraintLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */