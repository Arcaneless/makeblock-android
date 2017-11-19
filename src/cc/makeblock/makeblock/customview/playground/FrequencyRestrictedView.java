package cc.makeblock.makeblock.customview.playground;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public abstract class FrequencyRestrictedView<T>
  extends FrameLayout
{
  private static final long DEFAULT_INTERVAL = 100L;
  private T lastState;
  private HandleState mHandleState = HandleState.INIT;
  private FrequencyRestrictedView<T>.RefreshStateRunnable mRefreshStateRunnable;
  
  public FrequencyRestrictedView(Context paramContext)
  {
    super(paramContext);
  }
  
  public FrequencyRestrictedView(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public FrequencyRestrictedView(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected final void pushState(T paramT)
  {
    switch (this.mHandleState)
    {
    default: 
      return;
    case ???: 
      sendState(paramT);
      if (this.mRefreshStateRunnable == null) {
        this.mRefreshStateRunnable = new RefreshStateRunnable(100L);
      }
      postDelayed(this.mRefreshStateRunnable, 100L);
      this.mHandleState = HandleState.SEND;
      return;
    case ???: 
      this.lastState = paramT;
      this.mHandleState = HandleState.WAIT;
      return;
    }
    this.lastState = paramT;
  }
  
  protected abstract void sendState(T paramT);
  
  protected static enum HandleState
  {
    INIT,  SEND,  WAIT;
    
    private HandleState() {}
  }
  
  private class RefreshStateRunnable
    implements Runnable
  {
    private long interval = 50L;
    
    public RefreshStateRunnable(long paramLong)
    {
      this.interval = paramLong;
    }
    
    public void run()
    {
      switch (FrequencyRestrictedView.1.$SwitchMap$cc$makeblock$makeblock$customview$playground$FrequencyRestrictedView$HandleState[FrequencyRestrictedView.this.mHandleState.ordinal()])
      {
      default: 
        return;
      case 1: 
        throw new RuntimeException("不应该进到这里的");
      case 2: 
        FrequencyRestrictedView.access$102(FrequencyRestrictedView.this, null);
        FrequencyRestrictedView.access$002(FrequencyRestrictedView.this, FrequencyRestrictedView.HandleState.INIT);
        return;
      }
      FrequencyRestrictedView.this.sendState(FrequencyRestrictedView.this.lastState);
      FrequencyRestrictedView.access$002(FrequencyRestrictedView.this, FrequencyRestrictedView.HandleState.SEND);
      FrequencyRestrictedView.this.postDelayed(this, this.interval);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\playground\FrequencyRestrictedView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */