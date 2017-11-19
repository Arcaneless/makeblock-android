package com.unity3d.player;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.DisplayMetrics;
import android.view.View;

public final class i
  extends View
{
  final int a;
  final int b;
  Bitmap c;
  Bitmap d;
  
  public i(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.a = paramInt;
    this.b = getResources().getIdentifier("unity_static_splash", "drawable", getContext().getPackageName());
    if (this.b != 0) {
      forceLayout();
    }
  }
  
  public final void a()
  {
    if (this.c != null)
    {
      this.c.recycle();
      this.c = null;
    }
    if (this.d != null)
    {
      this.d.recycle();
      this.d = null;
    }
  }
  
  public final void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.b == 0) {}
    label7:
    Object localObject;
    int i;
    do
    {
      return;
      if (this.c == null)
      {
        localObject = new BitmapFactory.Options();
        ((BitmapFactory.Options)localObject).inScaled = false;
        this.c = BitmapFactory.decodeResource(getResources(), this.b, (BitmapFactory.Options)localObject);
      }
      paramInt2 = this.c.getWidth();
      paramInt4 = this.c.getHeight();
      paramInt1 = getWidth();
      i = getHeight();
    } while ((paramInt1 == 0) || (i == 0));
    float f = paramInt2 / paramInt4;
    if (paramInt1 / i <= f) {}
    for (paramInt3 = 1;; paramInt3 = 0) {
      switch (1.a[(this.a - 1)])
      {
      default: 
        paramInt1 = paramInt4;
        if (this.d != null)
        {
          if ((this.d.getWidth() == paramInt2) && (this.d.getHeight() == paramInt1)) {
            break label7;
          }
          this.d.recycle();
          this.d = null;
        }
        this.d = Bitmap.createScaledBitmap(this.c, paramInt2, paramInt1, true);
        this.d.setDensity((int)(this.d.getDensity() * getResources().getDisplayMetrics().density));
        localObject = new ColorDrawable(-16777216);
        BitmapDrawable localBitmapDrawable = new BitmapDrawable(getResources(), this.d);
        localBitmapDrawable.setGravity(17);
        setBackground(new LayerDrawable(new Drawable[] { localObject, localBitmapDrawable }));
        return;
      }
    }
    if (paramInt1 < paramInt2)
    {
      paramInt3 = (int)(paramInt1 / f);
      paramInt2 = paramInt1;
    }
    for (paramInt1 = paramInt3;; paramInt1 = paramInt4)
    {
      if (i < paramInt1) {
        label373:
        for (;;)
        {
          paramInt1 = i;
          paramInt2 = (int)(paramInt1 * f);
          break;
          if (this.a == a.c) {}
          for (paramInt2 = 1;; paramInt2 = 0)
          {
            if ((paramInt3 ^ paramInt2) == 0) {
              break label373;
            }
            paramInt3 = (int)(paramInt1 / f);
            paramInt2 = paramInt1;
            paramInt1 = paramInt3;
            break;
          }
        }
      }
      break;
    }
  }
  
  static enum a
  {
    public static int[] a()
    {
      return (int[])d.clone();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\unity3d\player\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */