package cc.makeblock.makeblock.viewmodel;

import android.app.Activity;
import android.content.res.AssetManager;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import cc.makeblock.makeblock.base.App;
import cc.makeblock.makeblock.bean.ExpandGuideData;
import cc.makeblock.makeblock.bean.ExpandGuideData.Link;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class ExpandGuideViewModel
  extends BaseObservable
{
  public ObservableBoolean isClose = new ObservableBoolean(false);
  private String mDirectory;
  private ExpandGuideData mGuideData;
  private String mName;
  
  public ExpandGuideViewModel(ExpandGuideData paramExpandGuideData, String paramString1, String paramString2)
  {
    this.mName = paramString1;
    this.mDirectory = paramString2;
    this.mGuideData = paramExpandGuideData;
  }
  
  public void close()
  {
    this.isClose.set(true);
  }
  
  public String getFormMsg()
  {
    return this.mGuideData.formMsg;
  }
  
  public Drawable getIconDrawable()
  {
    Object localObject1 = null;
    try
    {
      Object localObject2 = App.getContext().getAssets().open("settings/MakeblockAppResource/Expand/" + this.mDirectory + "/" + this.mGuideData.imgName + ".png");
      localObject1 = localObject2;
      Bitmap localBitmap = BitmapFactory.decodeStream((InputStream)localObject2);
      localObject1 = localObject2;
      ((InputStream)localObject2).close();
      localObject1 = localObject2;
      localObject2 = new BitmapDrawable(App.getContext().getResources(), localBitmap);
      return (Drawable)localObject2;
    }
    catch (IOException localIOException2)
    {
      localIOException2.printStackTrace();
      if (localObject1 == null) {}
    }
    try
    {
      ((InputStream)localObject1).close();
      return null;
    }
    catch (IOException localIOException1)
    {
      for (;;)
      {
        localIOException1.printStackTrace();
      }
    }
  }
  
  public String getLinkUrl()
  {
    String str = this.mGuideData.link.en;
    if (Locale.getDefault().getLanguage().equalsIgnoreCase("zh")) {
      str = this.mGuideData.link.cn;
    }
    return str;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getPlayMsg()
  {
    return this.mGuideData.playMsg;
  }
  
  public void skipToMoreInfo(Activity paramActivity)
  {
    ActivityJumpUtil.jumpToGroupActivity(paramActivity, getLinkUrl());
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\ExpandGuideViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */