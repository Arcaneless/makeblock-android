package android.databinding.generated.callback;

import cc.makeblock.makeblock.customview.playground.ButtonView.OnButtonClickListener;

public final class OnButtonClickListener
  implements ButtonView.OnButtonClickListener
{
  final Listener mListener;
  final int mSourceId;
  
  public OnButtonClickListener(Listener paramListener, int paramInt)
  {
    this.mListener = paramListener;
    this.mSourceId = paramInt;
  }
  
  public void onButtonClicked()
  {
    this.mListener._internalCallbackOnButtonClicked(this.mSourceId);
  }
  
  public static abstract interface Listener
  {
    public abstract void _internalCallbackOnButtonClicked(int paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\generated\callback\OnButtonClickListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */