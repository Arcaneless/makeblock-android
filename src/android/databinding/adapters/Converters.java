package android.databinding.adapters;

import android.content.res.ColorStateList;
import android.databinding.BindingConversion;
import android.graphics.drawable.ColorDrawable;

public class Converters
{
  @BindingConversion
  public static ColorStateList convertColorToColorStateList(int paramInt)
  {
    return ColorStateList.valueOf(paramInt);
  }
  
  @BindingConversion
  public static ColorDrawable convertColorToDrawable(int paramInt)
  {
    return new ColorDrawable(paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\Converters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */