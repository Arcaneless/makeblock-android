package android.databinding.generated.callback;

import android.view.View;
import cc.makeblock.makeblock.customview.MenuDrawerLayout.OnHideListener;

public final class OnHideListener
  implements MenuDrawerLayout.OnHideListener
{
  final Listener mListener;
  final int mSourceId;
  
  public OnHideListener(Listener paramListener, int paramInt)
  {
    this.mListener = paramListener;
    this.mSourceId = paramInt;
  }
  
  public void onHide(View paramView)
  {
    this.mListener._internalCallbackOnHide(this.mSourceId, paramView);
  }
  
  public static abstract interface Listener
  {
    public abstract void _internalCallbackOnHide(int paramInt, View paramView);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\generated\callback\OnHideListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */