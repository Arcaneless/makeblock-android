package android.databinding.generated.callback;

import cc.makeblock.makeblock.customview.playground.MusicKeyView.OnKeyClickListener;

public final class OnKeyClickListener
  implements MusicKeyView.OnKeyClickListener
{
  final Listener mListener;
  final int mSourceId;
  
  public OnKeyClickListener(Listener paramListener, int paramInt)
  {
    this.mListener = paramListener;
    this.mSourceId = paramInt;
  }
  
  public void onKeyClicked(int paramInt)
  {
    this.mListener._internalCallbackOnKeyClicked(this.mSourceId, paramInt);
  }
  
  public static abstract interface Listener
  {
    public abstract void _internalCallbackOnKeyClicked(int paramInt1, int paramInt2);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\generated\callback\OnKeyClickListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */