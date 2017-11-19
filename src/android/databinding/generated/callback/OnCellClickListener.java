package android.databinding.generated.callback;

import android.view.View;
import cc.makeblock.makeblock.customview.laboratory.LaboratoryPanelLayout.OnCellClickListener;

public final class OnCellClickListener
  implements LaboratoryPanelLayout.OnCellClickListener
{
  final Listener mListener;
  final int mSourceId;
  
  public OnCellClickListener(Listener paramListener, int paramInt)
  {
    this.mListener = paramListener;
    this.mSourceId = paramInt;
  }
  
  public void onCellClick(View paramView, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    this.mListener._internalCallbackOnCellClick(this.mSourceId, paramView, paramBoolean, paramInt1, paramInt2);
  }
  
  public static abstract interface Listener
  {
    public abstract void _internalCallbackOnCellClick(int paramInt1, View paramView, boolean paramBoolean, int paramInt2, int paramInt3);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\generated\callback\OnCellClickListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */