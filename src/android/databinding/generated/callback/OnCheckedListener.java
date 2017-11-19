package android.databinding.generated.callback;

import cc.makeblock.makeblock.customview.RadioLinearLayoutItem;
import cc.makeblock.makeblock.customview.RadioLinearLayoutItem.OnCheckedListener;

public final class OnCheckedListener
  implements RadioLinearLayoutItem.OnCheckedListener
{
  final Listener mListener;
  final int mSourceId;
  
  public OnCheckedListener(Listener paramListener, int paramInt)
  {
    this.mListener = paramListener;
    this.mSourceId = paramInt;
  }
  
  public void onChecked(RadioLinearLayoutItem paramRadioLinearLayoutItem)
  {
    this.mListener._internalCallbackOnChecked(this.mSourceId, paramRadioLinearLayoutItem);
  }
  
  public static abstract interface Listener
  {
    public abstract void _internalCallbackOnChecked(int paramInt, RadioLinearLayoutItem paramRadioLinearLayoutItem);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\generated\callback\OnCheckedListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */