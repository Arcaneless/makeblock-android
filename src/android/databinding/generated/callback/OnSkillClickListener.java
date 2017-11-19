package android.databinding.generated.callback;

import cc.makeblock.makeblock.customview.playground.SkillView.OnSkillClickListener;

public final class OnSkillClickListener
  implements SkillView.OnSkillClickListener
{
  final Listener mListener;
  final int mSourceId;
  
  public OnSkillClickListener(Listener paramListener, int paramInt)
  {
    this.mListener = paramListener;
    this.mSourceId = paramInt;
  }
  
  public void onSkillTriggered()
  {
    this.mListener._internalCallbackOnSkillTriggered(this.mSourceId);
  }
  
  public static abstract interface Listener
  {
    public abstract void _internalCallbackOnSkillTriggered(int paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\generated\callback\OnSkillClickListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */