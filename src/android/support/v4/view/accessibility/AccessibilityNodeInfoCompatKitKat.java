package android.support.v4.view.accessibility;

import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityNodeInfo.RangeInfo;

@RequiresApi(19)
class AccessibilityNodeInfoCompatKitKat
{
  static class RangeInfo
  {
    static float getCurrent(Object paramObject)
    {
      return ((AccessibilityNodeInfo.RangeInfo)paramObject).getCurrent();
    }
    
    static float getMax(Object paramObject)
    {
      return ((AccessibilityNodeInfo.RangeInfo)paramObject).getMax();
    }
    
    static float getMin(Object paramObject)
    {
      return ((AccessibilityNodeInfo.RangeInfo)paramObject).getMin();
    }
    
    static int getType(Object paramObject)
    {
      return ((AccessibilityNodeInfo.RangeInfo)paramObject).getType();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\support\v4\view\accessibility\AccessibilityNodeInfoCompatKitKat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */