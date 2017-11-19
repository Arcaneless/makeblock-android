package cc.makeblock.makeblock.scene.choosedevice;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import cc.makeblock.makeblock.base.BaseActivity;
import cc.makeblock.makeblock.bean.ChooseDeviceItem;
import cc.makeblock.makeblock.customview.CycleLinePageIndicator;
import cc.makeblock.makeblock.customview.drawable.ChooseDeviceDrawable;
import cc.makeblock.makeblock.databinding.ActivityChooseDeviceBinding;
import cc.makeblock.makeblock.viewmodel.choosedevice.ChooseDeviceViewModel;
import java.util.List;

public class ChooseDeviceActivity
  extends BaseActivity
{
  public static final String KEY_BOARD_NAME = "boardName";
  private ActivityChooseDeviceBinding mBinding;
  private String mBoardName;
  private DeviceFragment mCurrentFragment;
  private int mCurrentIndex;
  private ChooseDeviceDrawable mDeviceDrawable;
  private long mLastTime;
  private DeviceViewPageAdapter mPageAdapter;
  private Drawable mShadeDrawable;
  
  private void initCurrentIndex(List<ChooseDeviceItem> paramList)
  {
    int j = paramList.size();
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        if (((ChooseDeviceItem)paramList.get(i)).boardName.equals(this.mBoardName)) {
          this.mCurrentIndex = i;
        }
      }
      else {
        return;
      }
      i += 1;
    }
  }
  
  public Drawable getShadeDrawable()
  {
    return this.mShadeDrawable;
  }
  
  public void onBackPressed()
  {
    this.mBinding.getViewModel().onBackPressed(this);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mBoardName = getIntent().getStringExtra("boardName");
    this.mShadeDrawable = getResources().getDrawable(2131165380);
    this.mBinding = ((ActivityChooseDeviceBinding)DataBindingUtil.setContentView(this, 2131427360));
    this.mPageAdapter = new DeviceViewPageAdapter(getSupportFragmentManager(), null);
    this.mBinding.viewPager.setAdapter(this.mPageAdapter);
    this.mBinding.linePageIndicator.setViewPager(this.mBinding.viewPager);
    this.mBinding.setViewModel(new ChooseDeviceViewModel(this.mBoardName));
    this.mDeviceDrawable = new ChooseDeviceDrawable();
    this.mBinding.getRoot().setBackground(this.mDeviceDrawable);
    this.mBinding.linePageIndicator.setOnPageChangeListener(new MyPageChangeListener(null));
    this.mBinding.viewPager.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        if (paramAnonymousMotionEvent.getAction() == 0) {
          ChooseDeviceActivity.this.mDeviceDrawable.setCurrentPage(ChooseDeviceActivity.this.mCurrentIndex);
        }
        return false;
      }
    });
  }
  
  public class DeviceViewPageAdapter
    extends FragmentStatePagerAdapter
  {
    private List<ChooseDeviceItem> mDeviceItems;
    private int size;
    
    public DeviceViewPageAdapter(List<ChooseDeviceItem> paramList)
    {
      super();
      List localList;
      this.mDeviceItems = localList;
      if (this.mDeviceItems != null) {
        this.size = this.mDeviceItems.size();
      }
    }
    
    public int getCount()
    {
      if (this.mDeviceItems != null) {
        return Integer.MAX_VALUE;
      }
      return 0;
    }
    
    public Fragment getItem(int paramInt)
    {
      int i = this.size;
      return DeviceFragment.newInstance((ChooseDeviceItem)this.mDeviceItems.get(paramInt % i), ChooseDeviceActivity.this.mBoardName);
    }
    
    public int getItemPosition(Object paramObject)
    {
      return -2;
    }
    
    public int getSize()
    {
      return this.size;
    }
    
    public void replaceData(List<ChooseDeviceItem> paramList)
    {
      this.mDeviceItems = paramList;
      if (paramList != null) {}
      for (this.size = paramList.size();; this.size = 0)
      {
        ChooseDeviceActivity.this.initCurrentIndex(paramList);
        ChooseDeviceActivity.this.mBinding.linePageIndicator.setReleaSize(this.size);
        ChooseDeviceActivity.this.mDeviceDrawable.setDrawables(paramList);
        ChooseDeviceActivity.this.mDeviceDrawable.setCurrentPage(ChooseDeviceActivity.this.mCurrentIndex);
        ChooseDeviceActivity.this.mBinding.viewPager.setCurrentItem(ChooseDeviceActivity.this.mCurrentIndex + 6000);
        ChooseDeviceActivity.this.mBinding.linePageIndicator.setCurrentItem(ChooseDeviceActivity.this.mCurrentIndex);
        notifyDataSetChanged();
        return;
      }
    }
  }
  
  private class MyPageChangeListener
    implements ViewPager.OnPageChangeListener
  {
    private MyPageChangeListener() {}
    
    public void onPageScrollStateChanged(int paramInt)
    {
      if (paramInt == 0)
      {
        ChooseDeviceActivity.this.mDeviceDrawable.setCurrentPage(ChooseDeviceActivity.this.mCurrentIndex);
        if (ChooseDeviceActivity.this.mCurrentFragment != null) {
          ChooseDeviceActivity.this.mCurrentFragment.setAlpha(1.0F);
        }
        ChooseDeviceActivity.access$502(ChooseDeviceActivity.this, (DeviceFragment)ChooseDeviceActivity.this.mPageAdapter.instantiateItem(ChooseDeviceActivity.this.mBinding.viewPager, ChooseDeviceActivity.this.mBinding.viewPager.getCurrentItem()));
      }
    }
    
    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      if (System.currentTimeMillis() - ChooseDeviceActivity.this.mLastTime < 16L) {}
      do
      {
        return;
        paramInt1 %= ChooseDeviceActivity.this.mPageAdapter.getSize();
        ChooseDeviceActivity.this.mDeviceDrawable.refresh(paramInt1, paramFloat);
        if (ChooseDeviceActivity.this.mCurrentFragment == null) {
          ChooseDeviceActivity.access$502(ChooseDeviceActivity.this, (DeviceFragment)ChooseDeviceActivity.this.mPageAdapter.instantiateItem(ChooseDeviceActivity.this.mBinding.viewPager, ChooseDeviceActivity.this.mBinding.viewPager.getCurrentItem()));
        }
      } while (ChooseDeviceActivity.this.mCurrentFragment == null);
      if (ChooseDeviceActivity.this.mCurrentIndex == paramInt1) {
        ChooseDeviceActivity.this.mCurrentFragment.setAlpha(1.0F - paramFloat);
      }
      for (;;)
      {
        ChooseDeviceActivity.access$302(ChooseDeviceActivity.this, System.currentTimeMillis());
        return;
        ChooseDeviceActivity.this.mCurrentFragment.setAlpha(paramFloat);
      }
    }
    
    public void onPageSelected(int paramInt)
    {
      ChooseDeviceActivity.access$102(ChooseDeviceActivity.this, paramInt % ChooseDeviceActivity.this.mPageAdapter.getSize());
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\choosedevice\ChooseDeviceActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */