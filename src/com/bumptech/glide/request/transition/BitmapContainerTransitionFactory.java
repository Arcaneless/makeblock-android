package com.bumptech.glide.request.transition;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.bumptech.glide.load.DataSource;

public abstract class BitmapContainerTransitionFactory<R>
  implements TransitionFactory<R>
{
  private final TransitionFactory<Drawable> realFactory;
  
  public BitmapContainerTransitionFactory(TransitionFactory<Drawable> paramTransitionFactory)
  {
    this.realFactory = paramTransitionFactory;
  }
  
  public Transition<R> build(DataSource paramDataSource, boolean paramBoolean)
  {
    return new BitmapGlideAnimation(this.realFactory.build(paramDataSource, paramBoolean));
  }
  
  protected abstract Bitmap getBitmap(R paramR);
  
  private class BitmapGlideAnimation
    implements Transition<R>
  {
    private final Transition<Drawable> transition;
    
    public BitmapGlideAnimation()
    {
      Transition localTransition;
      this.transition = localTransition;
    }
    
    public boolean transition(R paramR, Transition.ViewAdapter paramViewAdapter)
    {
      paramR = new BitmapDrawable(paramViewAdapter.getView().getResources(), BitmapContainerTransitionFactory.this.getBitmap(paramR));
      return this.transition.transition(paramR, paramViewAdapter);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\request\transition\BitmapContainerTransitionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */