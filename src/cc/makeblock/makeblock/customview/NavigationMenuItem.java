package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import cc.makeblock.makeblock.R.styleable;

public class NavigationMenuItem
  extends FrameLayout
{
  private CheckImageView mCheckImageView;
  private boolean mChecked;
  private Drawable mIcon;
  private View mLineView;
  private String mTitle;
  private CheckTextView mTitleView;
  
  public NavigationMenuItem(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public NavigationMenuItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public NavigationMenuItem(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setBackground(getResources().getDrawable(2131165893));
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.NavigationMenuItem);
    this.mTitle = paramContext.getString(0);
    this.mIcon = paramContext.getDrawable(1);
    paramContext.recycle();
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    View localView = LayoutInflater.from(getContext()).inflate(2131427490, this, true);
    this.mCheckImageView = ((CheckImageView)localView.findViewById(2131296488));
    this.mTitleView = ((CheckTextView)localView.findViewById(2131296724));
    this.mLineView = localView.findViewById(2131296555);
    this.mCheckImageView.setImageDrawable(this.mIcon);
    this.mTitleView.setText(this.mTitle);
    this.mLineView.setVisibility(4);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = super.onTouchEvent(paramMotionEvent);
    switch (paramMotionEvent.getAction())
    {
    case 2: 
    default: 
      return bool;
    case 0: 
      this.mLineView.setVisibility(0);
      return bool;
    case 3: 
      this.mLineView.setVisibility(4);
      return bool;
    }
    postDelayed(new Runnable()
    {
      public void run()
      {
        if (!NavigationMenuItem.this.mChecked) {
          NavigationMenuItem.this.mLineView.setVisibility(4);
        }
      }
    }, 10L);
    return bool;
  }
  
  public void setChecked(boolean paramBoolean)
  {
    this.mChecked = paramBoolean;
    if (paramBoolean)
    {
      setBackground(getResources().getDrawable(2131165672));
      this.mLineView.setVisibility(0);
    }
    for (;;)
    {
      this.mCheckImageView.setChecked(paramBoolean);
      this.mTitleView.setChecked(paramBoolean);
      this.mTitleView.setPressed(paramBoolean);
      return;
      setBackground(getResources().getDrawable(2131165893));
      this.mLineView.setVisibility(4);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\NavigationMenuItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */