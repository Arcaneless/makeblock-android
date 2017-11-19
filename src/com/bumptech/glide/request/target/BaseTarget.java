package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import com.bumptech.glide.request.Request;

public abstract class BaseTarget<Z>
  implements Target<Z>
{
  private Request request;
  
  @Nullable
  public Request getRequest()
  {
    return this.request;
  }
  
  public void onDestroy() {}
  
  public void onLoadCleared(@Nullable Drawable paramDrawable) {}
  
  public void onLoadFailed(@Nullable Drawable paramDrawable) {}
  
  public void onLoadStarted(@Nullable Drawable paramDrawable) {}
  
  public void onStart() {}
  
  public void onStop() {}
  
  public void setRequest(@Nullable Request paramRequest)
  {
    this.request = paramRequest;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\request\target\BaseTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */