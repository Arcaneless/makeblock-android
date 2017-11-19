package android.databinding.generated.callback;

import android.view.View;
import cc.makeblock.makeblock.customview.bindingadapter.RecyclerViewBindings.OnItemClickListener;

public final class OnItemClickListener
  implements RecyclerViewBindings.OnItemClickListener
{
  final Listener mListener;
  final int mSourceId;
  
  public OnItemClickListener(Listener paramListener, int paramInt)
  {
    this.mListener = paramListener;
    this.mSourceId = paramInt;
  }
  
  public void onItemClick(View paramView, int paramInt)
  {
    this.mListener._internalCallbackOnItemClick(this.mSourceId, paramView, paramInt);
  }
  
  public static abstract interface Listener
  {
    public abstract void _internalCallbackOnItemClick(int paramInt1, View paramView, int paramInt2);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\generated\callback\OnItemClickListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */