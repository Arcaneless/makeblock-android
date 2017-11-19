package com.bumptech.glide.load.resource.drawable;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import com.bumptech.glide.load.engine.Initializable;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.util.Preconditions;

public abstract class DrawableResource<T extends Drawable>
  implements Resource<T>, Initializable
{
  protected final T drawable;
  
  public DrawableResource(T paramT)
  {
    this.drawable = ((Drawable)Preconditions.checkNotNull(paramT));
  }
  
  public final T get()
  {
    return this.drawable.getConstantState().newDrawable();
  }
  
  public void initialize()
  {
    if ((this.drawable instanceof BitmapDrawable)) {
      ((BitmapDrawable)this.drawable).getBitmap().prepareToDraw();
    }
    while (!(this.drawable instanceof GifDrawable)) {
      return;
    }
    ((GifDrawable)this.drawable).getFirstFrame().prepareToDraw();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\drawable\DrawableResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */