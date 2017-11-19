package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethods;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

@BindingMethods({@android.databinding.BindingMethod(attribute="android:tint", method="setImageTintList", type=ImageView.class), @android.databinding.BindingMethod(attribute="android:tintMode", method="setImageTintMode", type=ImageView.class)})
public class ImageViewBindingAdapter
{
  @BindingAdapter({"android:src"})
  public static void setImageDrawable(ImageView paramImageView, Drawable paramDrawable)
  {
    paramImageView.setImageDrawable(paramDrawable);
  }
  
  @BindingAdapter({"android:src"})
  public static void setImageUri(ImageView paramImageView, Uri paramUri)
  {
    paramImageView.setImageURI(paramUri);
  }
  
  @BindingAdapter({"android:src"})
  public static void setImageUri(ImageView paramImageView, String paramString)
  {
    if (paramString == null)
    {
      paramImageView.setImageURI(null);
      return;
    }
    paramImageView.setImageURI(Uri.parse(paramString));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\ImageViewBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */