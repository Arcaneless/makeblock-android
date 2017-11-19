package com.bumptech.glide.request.transition;

import com.bumptech.glide.load.DataSource;

public abstract interface TransitionFactory<R>
{
  public abstract Transition<R> build(DataSource paramDataSource, boolean paramBoolean);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\request\transition\TransitionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */