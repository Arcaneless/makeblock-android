package android.databinding.generated.callback;

import android.graphics.PointF;
import cc.makeblock.makeblock.customview.playground.PathMap.OnDrawPathFinishListener;
import java.util.List;

public final class OnDrawPathFinishListener
  implements PathMap.OnDrawPathFinishListener
{
  final Listener mListener;
  final int mSourceId;
  
  public OnDrawPathFinishListener(Listener paramListener, int paramInt)
  {
    this.mListener = paramListener;
    this.mSourceId = paramInt;
  }
  
  public void onDrawPathFinish(List<PointF> paramList)
  {
    this.mListener._internalCallbackOnDrawPathFinish(this.mSourceId, paramList);
  }
  
  public static abstract interface Listener
  {
    public abstract void _internalCallbackOnDrawPathFinish(int paramInt, List<PointF> paramList);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\generated\callback\OnDrawPathFinishListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */