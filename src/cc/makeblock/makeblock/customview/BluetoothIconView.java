package cc.makeblock.makeblock.customview;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import cc.makeblock.makeblock.R.styleable;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.ControllerManager.OnConnectedStateChangeListener;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;

public class BluetoothIconView
  extends RelativeLayout
  implements ControllerManager.OnConnectedStateChangeListener, View.OnClickListener
{
  private int connectedIconId;
  private Context context;
  private int disconnectedIconId;
  private boolean isConnectOK = false;
  private ImageView mBluetoothImage;
  private ObjectAnimator mBluetoothImageAnimator;
  private boolean withAnimation;
  
  public BluetoothIconView(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    init(paramContext, null);
  }
  
  public BluetoothIconView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    init(paramContext, paramAttributeSet);
  }
  
  public BluetoothIconView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.context = paramContext;
    init(paramContext, paramAttributeSet);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    boolean bool = true;
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.BluetoothIconView, 0, 0);
    this.connectedIconId = paramAttributeSet.getResourceId(0, 0);
    this.disconnectedIconId = paramAttributeSet.getResourceId(1, 0);
    if (this.disconnectedIconId == 0) {}
    for (;;)
    {
      this.withAnimation = bool;
      paramAttributeSet.recycle();
      if (this.withAnimation)
      {
        inflate(paramContext, 2131427464, this);
        this.mBluetoothImage = ((ImageView)findViewById(2131296314));
        initPropertyAnim();
      }
      return;
      bool = false;
    }
  }
  
  private void initPropertyAnim()
  {
    this.mBluetoothImageAnimator = ObjectAnimator.ofFloat(this.mBluetoothImage, "alpha", new float[] { 1.0F, 0.1F });
    this.mBluetoothImageAnimator.setDuration(1000L);
    this.mBluetoothImageAnimator.setRepeatMode(2);
    this.mBluetoothImageAnimator.setRepeatCount(-1);
    this.mBluetoothImageAnimator.addListener(new Animator.AnimatorListener()
    {
      public void onAnimationCancel(Animator paramAnonymousAnimator) {}
      
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        BluetoothIconView.this.mBluetoothImage.setAlpha(1.0F);
      }
      
      public void onAnimationRepeat(Animator paramAnonymousAnimator) {}
      
      public void onAnimationStart(Animator paramAnonymousAnimator) {}
    });
  }
  
  private void setConnectState(boolean paramBoolean)
  {
    if (getVisibility() == 8) {}
    label56:
    do
    {
      do
      {
        return;
        this.isConnectOK = paramBoolean;
        if (!this.isConnectOK) {
          break label56;
        }
        if (!this.withAnimation) {
          break;
        }
      } while (!this.mBluetoothImageAnimator.isRunning());
      this.mBluetoothImageAnimator.end();
      return;
      setBackgroundResource(this.connectedIconId);
      return;
      if (!this.withAnimation) {
        break;
      }
    } while ((this.mBluetoothImageAnimator.isRunning()) || (isInEditMode()));
    this.mBluetoothImageAnimator.start();
    return;
    setBackgroundResource(this.disconnectedIconId);
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    setConnectState(BleManager.getInstance().isConnected());
    setOnClickListener(this);
    ControllerManager.getInstance().registerConnectingStateListener(this);
  }
  
  public void onClick(View paramView)
  {
    if (this.isConnectOK)
    {
      StatisticsTool.getInstance().onEvent("ClickBluetoothConnected", "连接状态下点击蓝牙");
      ActivityJumpUtil.jumpToConnectActivity((Activity)this.context, 1);
      return;
    }
    StatisticsTool.getInstance().onEvent("ClickBluetoothDisconnected", "未连接状态下点击蓝牙");
    ActivityJumpUtil.jumpToConnectActivity((Activity)this.context, 1);
  }
  
  public void onConnectedStateChange(boolean paramBoolean)
  {
    setConnectState(paramBoolean);
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.withAnimation)
    {
      this.mBluetoothImageAnimator.end();
      this.mBluetoothImageAnimator.removeAllListeners();
    }
    ControllerManager.getInstance().unRegisterConnectingStateListener(this);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\BluetoothIconView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */