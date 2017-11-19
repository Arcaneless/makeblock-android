package cc.makeblock.makeblock.customview.playground;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public class MusicKeyView
  extends FrequencyRestrictedView<MusicKeyState>
  implements View.OnClickListener
{
  public static final int KEY_BLACK_1 = 7;
  public static final int KEY_BLACK_2 = 8;
  public static final int KEY_BLACK_3 = 9;
  public static final int KEY_BLACK_4 = 10;
  public static final int KEY_BLACK_5 = 11;
  public static final int KEY_DO = 0;
  public static final int KEY_FA = 3;
  public static final int KEY_LA = 5;
  public static final int KEY_MI = 2;
  public static final int KEY_RE = 1;
  public static final int KEY_SI = 6;
  public static final int KEY_SO = 4;
  private OnKeyClickListener mOnClickListener;
  
  public MusicKeyView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public MusicKeyView(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public MusicKeyView(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2131427553, null, false);
    paramContext.findViewById(2131296580).setOnClickListener(this);
    paramContext.findViewById(2131296584).setOnClickListener(this);
    paramContext.findViewById(2131296583).setOnClickListener(this);
    paramContext.findViewById(2131296581).setOnClickListener(this);
    paramContext.findViewById(2131296586).setOnClickListener(this);
    paramContext.findViewById(2131296582).setOnClickListener(this);
    paramContext.findViewById(2131296585).setOnClickListener(this);
    paramContext.findViewById(2131296575).setOnClickListener(this);
    paramContext.findViewById(2131296576).setOnClickListener(this);
    paramContext.findViewById(2131296577).setOnClickListener(this);
    paramContext.findViewById(2131296578).setOnClickListener(this);
    paramContext.findViewById(2131296579).setOnClickListener(this);
    addView(paramContext);
  }
  
  public void onClick(View paramView)
  {
    if (this.mOnClickListener == null) {
      return;
    }
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131296575: 
      pushState(new MusicKeyState(7));
      return;
    case 2131296580: 
      pushState(new MusicKeyState(0));
      return;
    case 2131296584: 
      pushState(new MusicKeyState(1));
      return;
    case 2131296583: 
      pushState(new MusicKeyState(2));
      return;
    case 2131296581: 
      pushState(new MusicKeyState(3));
      return;
    case 2131296586: 
      pushState(new MusicKeyState(4));
      return;
    case 2131296582: 
      pushState(new MusicKeyState(5));
      return;
    case 2131296585: 
      pushState(new MusicKeyState(6));
      return;
    case 2131296576: 
      pushState(new MusicKeyState(8));
      return;
    case 2131296577: 
      pushState(new MusicKeyState(9));
      return;
    case 2131296578: 
      pushState(new MusicKeyState(10));
      return;
    }
    pushState(new MusicKeyState(11));
  }
  
  protected void sendState(MusicKeyState paramMusicKeyState)
  {
    this.mOnClickListener.onKeyClicked(paramMusicKeyState.pressedKey);
  }
  
  public void setOnKeyClickListener(OnKeyClickListener paramOnKeyClickListener)
  {
    this.mOnClickListener = paramOnKeyClickListener;
  }
  
  class MusicKeyState
  {
    private int pressedKey;
    
    MusicKeyState(int paramInt)
    {
      this.pressedKey = paramInt;
    }
  }
  
  public static abstract interface OnKeyClickListener
  {
    public abstract void onKeyClicked(int paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\playground\MusicKeyView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */