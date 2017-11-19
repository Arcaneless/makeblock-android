package com.bumptech.glide;

import android.content.Context;
import android.util.Log;
import cc.makeblock.makeblock.engine.GlideModule;
import java.util.Collections;
import java.util.Set;

final class GeneratedAppGlideModuleImpl
  extends GeneratedAppGlideModule
{
  private final GlideModule appGlideModule = new GlideModule();
  
  GeneratedAppGlideModuleImpl()
  {
    if (Log.isLoggable("Glide", 3)) {
      Log.d("Glide", "Discovered AppGlideModule from annotation: cc.makeblock.makeblock.engine.GlideModule");
    }
  }
  
  public void applyOptions(Context paramContext, GlideBuilder paramGlideBuilder)
  {
    this.appGlideModule.applyOptions(paramContext, paramGlideBuilder);
  }
  
  public Set<Class<?>> getExcludedModuleClasses()
  {
    return Collections.emptySet();
  }
  
  GeneratedRequestManagerFactory getRequestManagerFactory()
  {
    return new GeneratedRequestManagerFactory();
  }
  
  public boolean isManifestParsingEnabled()
  {
    return this.appGlideModule.isManifestParsingEnabled();
  }
  
  public void registerComponents(Context paramContext, Glide paramGlide, Registry paramRegistry)
  {
    this.appGlideModule.registerComponents(paramContext, paramGlide, paramRegistry);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\GeneratedAppGlideModuleImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */