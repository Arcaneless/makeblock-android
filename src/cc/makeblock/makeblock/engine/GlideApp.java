package cc.makeblock.makeblock.engine;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.bumptech.glide.Glide;
import java.io.File;

public final class GlideApp
{
  public static Glide get(Context paramContext)
  {
    return Glide.get(paramContext);
  }
  
  @Nullable
  public static File getPhotoCacheDir(Context paramContext)
  {
    return Glide.getPhotoCacheDir(paramContext);
  }
  
  @Nullable
  public static File getPhotoCacheDir(Context paramContext, String paramString)
  {
    return Glide.getPhotoCacheDir(paramContext, paramString);
  }
  
  @SuppressLint({"VisibleForTests"})
  @VisibleForTesting
  public static void init(Glide paramGlide)
  {
    Glide.init(paramGlide);
  }
  
  @SuppressLint({"VisibleForTests"})
  @VisibleForTesting
  public static void tearDown() {}
  
  public static GlideRequests with(Activity paramActivity)
  {
    return (GlideRequests)Glide.with(paramActivity);
  }
  
  public static GlideRequests with(android.app.Fragment paramFragment)
  {
    return (GlideRequests)Glide.with(paramFragment);
  }
  
  public static GlideRequests with(Context paramContext)
  {
    return (GlideRequests)Glide.with(paramContext);
  }
  
  public static GlideRequests with(android.support.v4.app.Fragment paramFragment)
  {
    return (GlideRequests)Glide.with(paramFragment);
  }
  
  public static GlideRequests with(FragmentActivity paramFragmentActivity)
  {
    return (GlideRequests)Glide.with(paramFragmentActivity);
  }
  
  public static GlideRequests with(View paramView)
  {
    return (GlideRequests)Glide.with(paramView);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\GlideApp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */