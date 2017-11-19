package android.support.v4.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public abstract interface NestedScrollingParent2
  extends NestedScrollingParent
{
  public abstract void onNestedPreScroll(@NonNull View paramView, int paramInt1, int paramInt2, @Nullable int[] paramArrayOfInt, int paramInt3);
  
  public abstract void onNestedScroll(@NonNull View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  
  public abstract void onNestedScrollAccepted(@NonNull View paramView1, @NonNull View paramView2, int paramInt1, int paramInt2);
  
  public abstract boolean onStartNestedScroll(@NonNull View paramView1, @NonNull View paramView2, int paramInt1, int paramInt2);
  
  public abstract void onStopNestedScroll(@NonNull View paramView, int paramInt);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\support\v4\view\NestedScrollingParent2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */