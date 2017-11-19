package android.databinding.generated.callback;

import android.view.View;
import cc.makeblock.makeblock.customview.MenuDrawerLayout.OnShowListener;

public final class OnShowListener
  implements MenuDrawerLayout.OnShowListener
{
  final Listener mListener;
  final int mSourceId;
  
  public OnShowListener(Listener paramListener, int paramInt)
  {
    this.mListener = paramListener;
    this.mSourceId = paramInt;
  }
  
  public void onShow(View paramView)
  {
    this.mListener._internalCallbackOnShow(this.mSourceId, paramView);
  }
  
  public static abstract interface Listener
  {
    public abstract void _internalCallbackOnShow(int paramInt, View paramView);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\generated\callback\OnShowListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */