package cc.makeblock.makeblock.customview.playground;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cc.makeblock.makeblock.R.styleable;

public class SkillView
  extends FrameLayout
  implements View.OnClickListener
{
  private int coolingTime;
  private CountDownTimer countDownTimer;
  private boolean isCooling = false;
  private Boolean isStartCooling;
  private OnSkillClickListener mOnSkillClickListener;
  private ProgressMaskView progressMaskView;
  
  public SkillView(@NonNull Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0);
  }
  
  public SkillView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0);
  }
  
  public SkillView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, @AttrRes int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt);
  }
  
  public SkillView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, @AttrRes int paramInt1, @StyleRes int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    init(paramContext, paramAttributeSet, paramInt1);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SkillView, paramInt, 0);
    this.coolingTime = paramAttributeSet.getInt(0, -1);
    paramInt = paramAttributeSet.getResourceId(1, -1);
    paramAttributeSet.recycle();
    paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2131427559, null, false);
    this.progressMaskView = ((ProgressMaskView)paramContext.findViewById(2131296635));
    paramAttributeSet = (ImageView)paramContext.findViewById(2131296383);
    paramAttributeSet.setImageResource(paramInt);
    paramAttributeSet.setOnClickListener(this);
    addView(paramContext);
    this.progressMaskView.setDuration(this.coolingTime);
  }
  
  public void onClick(View paramView)
  {
    if (!this.isCooling)
    {
      this.mOnSkillClickListener.onSkillTriggered();
      if (this.isStartCooling == null) {
        startCooling();
      }
      this.isCooling = true;
      this.progressMaskView.setProgress(this.coolingTime * 1000);
    }
  }
  
  public void setCancel(boolean paramBoolean)
  {
    if ((this.countDownTimer != null) && (this.progressMaskView != null))
    {
      this.countDownTimer.cancel();
      this.progressMaskView.finish();
      this.isCooling = false;
    }
  }
  
  public void setOnSkillTriggerListener(OnSkillClickListener paramOnSkillClickListener)
  {
    this.mOnSkillClickListener = paramOnSkillClickListener;
  }
  
  public void setStartCooling(boolean paramBoolean)
  {
    this.isStartCooling = Boolean.valueOf(paramBoolean);
    if (paramBoolean)
    {
      startCooling();
      this.isCooling = true;
      return;
    }
    setCancel(true);
  }
  
  public void startCooling()
  {
    if (this.countDownTimer != null)
    {
      this.countDownTimer.cancel();
      this.countDownTimer = null;
    }
    this.countDownTimer = new CountDownTimer(this.coolingTime * 1000, 1L)
    {
      public void onFinish()
      {
        SkillView.this.progressMaskView.finish();
        SkillView.access$102(SkillView.this, false);
      }
      
      public void onTick(long paramAnonymousLong)
      {
        SkillView.this.progressMaskView.setProgress(paramAnonymousLong);
      }
    };
    this.countDownTimer.start();
  }
  
  public static abstract interface OnSkillClickListener
  {
    public abstract void onSkillTriggered();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\playground\SkillView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */