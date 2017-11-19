package cc.makeblock.makeblock.customview.laboratory;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public abstract class LaboratoryView<T>
  extends RelativeLayout
{
  private static final long DEFAULT_INTERVAL = 150L;
  public static final int MODE_DESIGN = 1;
  public static final int MODE_IMAGE = 2;
  public static final int MODE_PLAY = 0;
  private T lastState;
  private HandleState mHandleState = HandleState.INIT;
  private LaboratoryView<T>.RefreshStateRunnable mRefreshStateRunnable;
  protected int mode = 0;
  
  public LaboratoryView(Context paramContext)
  {
    super(paramContext);
  }
  
  public LaboratoryView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public float getSizePercent()
  {
    return 0.0F;
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
        this.mRefreshStateRunnable = new RefreshStateRunnable(150L);
      }
      postDelayed(this.mRefreshStateRunnable, 150L);
      this.mHandleState = HandleState.SEND;
      return;
    case ???: 
      this.lastState = paramT;
      this.mHandleState = HandleState.WAIT;
      return;
    }
    this.lastState = paramT;
  }
  
  protected void reset() {}
  
  protected abstract void sendState(T paramT);
  
  public void setMode(int paramInt)
  {
    this.mode = paramInt;
    if (paramInt != 0) {
      reset();
    }
  }
  
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
      switch (LaboratoryView.1.$SwitchMap$cc$makeblock$makeblock$customview$laboratory$LaboratoryView$HandleState[LaboratoryView.this.mHandleState.ordinal()])
      {
      default: 
        return;
      case 1: 
        throw new RuntimeException("不应该进到这里的");
      case 2: 
        LaboratoryView.access$102(LaboratoryView.this, null);
        LaboratoryView.access$002(LaboratoryView.this, LaboratoryView.HandleState.INIT);
        return;
      }
      LaboratoryView.this.sendState(LaboratoryView.this.lastState);
      LaboratoryView.access$002(LaboratoryView.this, LaboratoryView.HandleState.SEND);
      LaboratoryView.this.postDelayed(this, this.interval);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\laboratory\LaboratoryView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */