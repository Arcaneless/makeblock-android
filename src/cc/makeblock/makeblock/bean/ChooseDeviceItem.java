package cc.makeblock.makeblock.bean;

import android.support.annotation.DrawableRes;
import java.io.Serializable;

public class ChooseDeviceItem
  implements Serializable
{
  public final String boardName;
  public final String description;
  public final int deviceBg;
  public final int deviceIcon;
  public final String deviceName;
  public final int devicePic;
  
  public ChooseDeviceItem(@DrawableRes int paramInt1, @DrawableRes int paramInt2, @DrawableRes int paramInt3, String paramString1, String paramString2)
  {
    this.deviceBg = paramInt1;
    this.devicePic = paramInt2;
    this.deviceIcon = paramInt3;
    this.boardName = paramString1;
    this.deviceName = paramString2;
    this.description = "";
  }
  
  public ChooseDeviceItem(@DrawableRes int paramInt1, @DrawableRes int paramInt2, @DrawableRes int paramInt3, String paramString1, String paramString2, String paramString3)
  {
    this.deviceBg = paramInt1;
    this.devicePic = paramInt2;
    this.deviceIcon = paramInt3;
    this.boardName = paramString1;
    this.deviceName = paramString2;
    this.description = paramString3;
  }
  
  public String toString()
  {
    return "ChooseDeviceItem{deviceBg=" + this.deviceBg + ", devicePic=" + this.devicePic + ", deviceIcon=" + this.deviceIcon + ", boardName='" + this.boardName + '\'' + ", deviceName='" + this.deviceName + '\'' + ", description='" + this.description + '\'' + '}';
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\bean\ChooseDeviceItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */