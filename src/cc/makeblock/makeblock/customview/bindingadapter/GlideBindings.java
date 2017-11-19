package cc.makeblock.makeblock.customview.bindingadapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import cc.makeblock.makeblock.customview.util.GlideRoundedCorners;
import cc.makeblock.makeblock.engine.GlideApp;
import cc.makeblock.makeblock.engine.GlideRequest;
import cc.makeblock.makeblock.engine.GlideRequests;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import java.io.File;

public class GlideBindings
{
  public static final String ANDROID_RESOURCE = "android.resource://";
  
  public static Uri resourceIdToUri(Context paramContext, int paramInt)
  {
    return Uri.parse("android.resource://" + paramContext.getPackageName() + File.separator + paramInt);
  }
  
  @BindingAdapter(requireAll=false, value={"backgroundSrc", "roundedCornerRadius"})
  public static void setBackground(View paramView, @DrawableRes int paramInt, float paramFloat)
  {
    if (paramFloat > 0.0F)
    {
      GlideApp.with(paramView.getContext()).load(Integer.valueOf(paramInt)).diskCacheStrategy(DiskCacheStrategy.NONE).transform(new GlideRoundedCorners((int)paramFloat)).into(new SimpleTarget()
      {
        public void onResourceReady(Drawable paramAnonymousDrawable, Transition<? super Drawable> paramAnonymousTransition)
        {
          this.val$view.setBackground(paramAnonymousDrawable);
        }
      });
      return;
    }
    GlideApp.with(paramView.getContext()).load(Integer.valueOf(paramInt)).diskCacheStrategy(DiskCacheStrategy.NONE).into(new SimpleTarget()
    {
      public void onResourceReady(Drawable paramAnonymousDrawable, Transition<? super Drawable> paramAnonymousTransition)
      {
        this.val$view.setBackground(paramAnonymousDrawable);
      }
    });
  }
  
  @BindingAdapter({"imageSrc"})
  public static void setImageSrc(ImageView paramImageView, @DrawableRes int paramInt)
  {
    GlideApp.with(paramImageView.getContext()).load(Integer.valueOf(paramInt)).diskCacheStrategy(DiskCacheStrategy.NONE).into(paramImageView);
  }
  
  @BindingAdapter({"imageSrc"})
  public static void setImageUri(ImageView paramImageView, Uri paramUri)
  {
    GlideApp.with(paramImageView.getContext()).load(paramUri).diskCacheStrategy(DiskCacheStrategy.NONE).into(paramImageView);
  }
  
  @BindingAdapter({"imageSrc", "roundedCornerRadius"})
  public static void setRoundImageUri(ImageView paramImageView, Uri paramUri, float paramFloat)
  {
    GlideApp.with(paramImageView.getContext()).load(paramUri).diskCacheStrategy(DiskCacheStrategy.NONE).transform(new GlideRoundedCorners((int)paramFloat)).into(paramImageView);
  }
  
  @BindingAdapter({"imageSrc", "placeholderImage", "roundedCornerRadius"})
  public static void setRoundImageUriWithPlaceHolder(ImageView paramImageView, Uri paramUri, Drawable paramDrawable, float paramFloat)
  {
    GlideApp.with(paramImageView.getContext()).load(paramUri).diskCacheStrategy(DiskCacheStrategy.NONE).transform(new GlideRoundedCorners((int)paramFloat)).placeholder(paramDrawable).into(paramImageView);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\bindingadapter\GlideBindings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */