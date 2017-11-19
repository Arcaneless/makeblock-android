package cc.makeblock.makeblock.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import com.viewpagerindicator.CirclePageIndicator;

public class GuideDialog
  extends Dialog
{
  private int currentPosition;
  private Animation dialogDismissAnimation;
  private Animation dialogShowUpAnimation;
  private CirclePageIndicator indicator;
  private boolean isPageFour2Three = false;
  private boolean isPageLastToFirst = false;
  private int[] pagesLayoutLists;
  private ViewPager viewPager;
  
  public GuideDialog(Context paramContext, int[] paramArrayOfInt)
  {
    super(paramContext, 16973834);
    this.pagesLayoutLists = paramArrayOfInt;
  }
  
  private void performDismissAnimation()
  {
    if (this.currentPosition != this.pagesLayoutLists.length - 1) {
      this.indicator.startAnimation(this.dialogDismissAnimation);
    }
    this.viewPager.startAnimation(this.dialogDismissAnimation);
    this.dialogDismissAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        GuideDialog.this.dismiss();
      }
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
      
      public void onAnimationStart(Animation paramAnonymousAnimation) {}
    });
  }
  
  private void performShowUpAnimation()
  {
    this.viewPager.startAnimation(this.dialogShowUpAnimation);
    this.indicator.startAnimation(this.dialogShowUpAnimation);
  }
  
  public void onBackPressed()
  {
    performDismissAnimation();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2131427403);
    SharedPreferencesUtils.setShowStartDesignGuide();
    this.dialogShowUpAnimation = new TranslateAnimation(ScreenHelper.getPercentWidthToPx(1.0F), 0.0F, 0.0F, 0.0F);
    this.dialogShowUpAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
    this.dialogShowUpAnimation.setDuration(400L);
    this.dialogDismissAnimation = new TranslateAnimation(0.0F, ScreenHelper.getPercentWidthToPx(1.0F) * 2, 0.0F, 0.0F);
    this.dialogDismissAnimation.setInterpolator(new AccelerateInterpolator());
    this.dialogDismissAnimation.setDuration(400L);
    this.indicator = ((CirclePageIndicator)findViewById(2131296766));
    this.viewPager = ((ViewPager)findViewById(2131296761));
    this.viewPager.setBackgroundResource(2131165343);
    this.viewPager.setOffscreenPageLimit(4);
    this.viewPager.setAdapter(new GuidePagerAdapter(this.pagesLayoutLists));
    this.indicator.setViewPager(this.viewPager);
    this.indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      public void onPageScrollStateChanged(int paramAnonymousInt) {}
      
      public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2) {}
      
      public void onPageSelected(int paramAnonymousInt)
      {
        GuideDialog.access$002(GuideDialog.this, paramAnonymousInt);
        if (paramAnonymousInt == GuideDialog.this.pagesLayoutLists.length - 1)
        {
          GuideDialog.access$202(GuideDialog.this, true);
          localAnimation = AnimationUtils.loadAnimation(GuideDialog.this.getContext(), 17432577);
          localAnimation.setDuration(500L);
          GuideDialog.this.indicator.startAnimation(localAnimation);
          GuideDialog.this.indicator.setVisibility(4);
        }
        while (((paramAnonymousInt != GuideDialog.this.pagesLayoutLists.length - 2) || (!GuideDialog.this.isPageFour2Three)) && (!GuideDialog.this.isPageLastToFirst)) {
          return;
        }
        GuideDialog.access$202(GuideDialog.this, false);
        GuideDialog.access$402(GuideDialog.this, false);
        Animation localAnimation = AnimationUtils.loadAnimation(GuideDialog.this.getContext(), 17432576);
        localAnimation.setDuration(1000L);
        localAnimation.setStartOffset(500L);
        GuideDialog.this.indicator.startAnimation(localAnimation);
        GuideDialog.this.indicator.setVisibility(0);
      }
    });
    performShowUpAnimation();
  }
  
  public void show()
  {
    if (getWindow() != null)
    {
      getWindow().setBackgroundDrawable(new ColorDrawable(-1442840576));
      super.show();
    }
  }
  
  private class GuidePagerAdapter
    extends PagerAdapter
  {
    private int[] list;
    
    GuidePagerAdapter(int[] paramArrayOfInt)
    {
      this.list = paramArrayOfInt;
    }
    
    public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
    {
      paramViewGroup.removeView((View)paramObject);
    }
    
    public int getCount()
    {
      return this.list.length;
    }
    
    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
    {
      View localView1 = LayoutInflater.from(paramViewGroup.getContext()).inflate(this.list[paramInt], null);
      if (paramInt == this.list.length - 1)
      {
        View localView2 = localView1.findViewById(2131296380);
        View localView3 = localView1.findViewById(2131296767);
        if ((localView2 != null) && (localView3 != null))
        {
          localView2.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              GuideDialog.this.performDismissAnimation();
            }
          });
          localView3.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              GuideDialog.access$402(GuideDialog.this, true);
              GuideDialog.this.viewPager.setCurrentItem(0, true);
            }
          });
        }
      }
      paramViewGroup.addView(localView1);
      return localView1;
    }
    
    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      return paramView == paramObject;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\dialog\GuideDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */