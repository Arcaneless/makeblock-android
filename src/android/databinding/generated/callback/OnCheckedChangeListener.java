package android.databinding.generated.callback;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public final class OnCheckedChangeListener
  implements CompoundButton.OnCheckedChangeListener
{
  final Listener mListener;
  final int mSourceId;
  
  public OnCheckedChangeListener(Listener paramListener, int paramInt)
  {
    this.mListener = paramListener;
    this.mSourceId = paramInt;
  }
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    this.mListener._internalCallbackOnCheckedChanged(this.mSourceId, paramCompoundButton, paramBoolean);
  }
  
  public static abstract interface Listener
  {
    public abstract void _internalCallbackOnCheckedChanged(int paramInt, CompoundButton paramCompoundButton, boolean paramBoolean);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\generated\callback\OnCheckedChangeListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */