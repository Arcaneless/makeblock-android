package android.support.v4.widget;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract interface AutoSizeableTextView
{
  public abstract int getAutoSizeMaxTextSize();
  
  public abstract int getAutoSizeMinTextSize();
  
  public abstract int getAutoSizeStepGranularity();
  
  public abstract int[] getAutoSizeTextAvailableSizes();
  
  public abstract int getAutoSizeTextType();
  
  public abstract void setAutoSizeTextTypeUniformWithConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IllegalArgumentException;
  
  public abstract void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] paramArrayOfInt, int paramInt)
    throws IllegalArgumentException;
  
  public abstract void setAutoSizeTextTypeWithDefaults(int paramInt);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\support\v4\widget\AutoSizeableTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */