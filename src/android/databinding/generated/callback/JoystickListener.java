package android.databinding.generated.callback;

import cc.makeblock.makeblock.customview.playground.Joystick.JoystickListener;

public final class JoystickListener
  implements Joystick.JoystickListener
{
  final Listener mListener;
  final int mSourceId;
  
  public JoystickListener(Listener paramListener, int paramInt)
  {
    this.mListener = paramListener;
    this.mSourceId = paramInt;
  }
  
  public void onJoystickMoved(int paramInt, float paramFloat)
  {
    this.mListener._internalCallbackOnJoystickMoved(this.mSourceId, paramInt, paramFloat);
  }
  
  public static abstract interface Listener
  {
    public abstract void _internalCallbackOnJoystickMoved(int paramInt1, int paramInt2, float paramFloat);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\generated\callback\JoystickListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */