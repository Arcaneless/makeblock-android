package android.databinding.generated.callback;

import cc.makeblock.makeblock.customview.playground.PathMap.ResetListener;

public final class ResetListener
  implements PathMap.ResetListener
{
  final Listener mListener;
  final int mSourceId;
  
  public ResetListener(Listener paramListener, int paramInt)
  {
    this.mListener = paramListener;
    this.mSourceId = paramInt;
  }
  
  public void onReset()
  {
    this.mListener._internalCallbackOnReset(this.mSourceId);
  }
  
  public static abstract interface Listener
  {
    public abstract void _internalCallbackOnReset(int paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\generated\callback\ResetListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */