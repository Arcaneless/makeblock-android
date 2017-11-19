package com.bumptech.glide;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.manager.RequestManagerRetriever.RequestManagerFactory;
import com.bumptech.glide.module.AppGlideModule;
import java.util.Set;

abstract class GeneratedAppGlideModule
  extends AppGlideModule
{
  @Deprecated
  @NonNull
  abstract Set<Class<?>> getExcludedModuleClasses();
  
  @Nullable
  RequestManagerRetriever.RequestManagerFactory getRequestManagerFactory()
  {
    return null;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\GeneratedAppGlideModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */