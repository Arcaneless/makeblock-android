package com.unity3d.player;

import android.os.Build.VERSION;

public final class g
{
  static final boolean a;
  static final boolean b;
  static final boolean c;
  static final c d;
  
  static
  {
    boolean bool2 = true;
    boolean bool1;
    if (Build.VERSION.SDK_INT >= 19)
    {
      bool1 = true;
      a = bool1;
      if (Build.VERSION.SDK_INT < 21) {
        break label66;
      }
      bool1 = true;
      label26:
      b = bool1;
      if (Build.VERSION.SDK_INT < 23) {
        break label71;
      }
      bool1 = bool2;
      label40:
      c = bool1;
      if (!bool1) {
        break label76;
      }
    }
    label66:
    label71:
    label76:
    for (f localf = new f();; localf = null)
    {
      d = localf;
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label26;
      bool1 = false;
      break label40;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\unity3d\player\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */