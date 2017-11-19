package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup.LayoutParams;
import cc.makeblock.makeblock.R.styleable;

public class AutoResizeTextView
  extends AppCompatTextView
{
  private float currentTextSize;
  private boolean isFixedSize;
  private boolean isWrapWidth;
  private final int[] styleAttrs = { 16843103, 16842901 };
  
  public AutoResizeTextView(Context paramContext)
  {
    super(paramContext);
    initView(paramContext, null);
  }
  
  public AutoResizeTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext, paramAttributeSet);
  }
  
  public AutoResizeTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initView(paramContext, paramAttributeSet);
  }
  
  private void initView(Context paramContext, AttributeSet paramAttributeSet)
  {
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, this.styleAttrs);
    boolean bool = localTypedArray.getBoolean(0, false);
    localTypedArray.recycle();
    setIncludeFontPadding(bool);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AutoSizeTextView);
    this.isFixedSize = paramContext.getBoolean(0, false);
    paramContext.recycle();
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
    if ((localLayoutParams != null) && (localLayoutParams.width == -2))
    {
      this.isWrapWidth = true;
      this.currentTextSize = getTextSize();
      setTextSize(TypedValue.applyDimension(2, 100.0F, getResources().getDisplayMetrics()));
    }
    TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(this, 1, 300, 1, 0);
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.isFixedSize)
    {
      TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(this, (int)getTextSize(), 300, 1, 0);
      this.isFixedSize = false;
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if ((this.isWrapWidth) && (this.currentTextSize != getTextSize())) {
      post(new Runnable()
      {
        public void run()
        {
          AutoResizeTextView.this.requestLayout();
        }
      });
    }
    this.currentTextSize = getTextSize();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\AutoResizeTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */