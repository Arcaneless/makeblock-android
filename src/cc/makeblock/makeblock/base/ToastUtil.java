package cc.makeblock.makeblock.base;

import android.widget.Toast;
import java.lang.ref.WeakReference;

public class ToastUtil
{
  private static WeakReference<Toast> toastWeakReference = new WeakReference(null);
  
  public static void showToast(int paramInt)
  {
    showToast(App.getContext().getString(paramInt));
  }
  
  public static void showToast(String paramString)
  {
    paramString = Toast.makeText(App.getContext(), paramString, 0);
    if (toastWeakReference.get() != null) {
      ((Toast)toastWeakReference.get()).cancel();
    }
    paramString.show();
    toastWeakReference = new WeakReference(paramString);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\base\ToastUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */