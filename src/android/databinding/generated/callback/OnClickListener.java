package android.databinding.generated.callback;

import android.view.View;
import android.view.View.OnClickListener;

public final class OnClickListener
  implements View.OnClickListener
{
  final Listener mListener;
  final int mSourceId;
  
  public OnClickListener(Listener paramListener, int paramInt)
  {
    this.mListener = paramListener;
    this.mSourceId = paramInt;
  }
  
  public void onClick(View paramView)
  {
    this.mListener._internalCallbackOnClick(this.mSourceId, paramView);
  }
  
  public static abstract interface Listener
  {
    public abstract void _internalCallbackOnClick(int paramInt, View paramView);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\generated\callback\OnClickListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */