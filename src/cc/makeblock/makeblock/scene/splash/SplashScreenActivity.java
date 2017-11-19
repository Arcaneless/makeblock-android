package cc.makeblock.makeblock.scene.splash;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.percent.PercentRelativeLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cc.makeblock.makeblock.base.BaseActivity;
import cc.makeblock.makeblock.engine.net.NetManager;
import cc.makeblock.makeblock.engine.net.NetResponseListener;
import cc.makeblock.makeblock.engine.net.data.AdInfoData;
import cc.makeblock.makeblock.engine.net.data.AdInfoData.DataBean;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.FileUtils;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.project.SettingsManager;
import cc.makeblock.makeblock.scene.main.MainActivity;
import java.io.File;
import java.util.Locale;

public class SplashScreenActivity
  extends BaseActivity
  implements View.OnClickListener
{
  private ImageView adImage;
  private volatile boolean backgroundLoadingFinish = false;
  private TextView countDownText;
  private CountDownTimer countDownTimer;
  private boolean hasClickAd = false;
  private volatile boolean hasShowAD = false;
  private volatile boolean isTimeOut = false;
  private Runnable jumpToNextRunnable = new Runnable()
  {
    public void run()
    {
      SplashScreenActivity.access$002(SplashScreenActivity.this, true);
      if (!SplashScreenActivity.this.backgroundLoadingFinish)
      {
        SplashScreenActivity.this.mHandler.postDelayed(this, 1000L);
        return;
      }
      if (!SplashScreenActivity.this.hasShowAD)
      {
        if (SplashScreenActivity.this.localAdInfoData == null) {
          SplashScreenActivity.access$402(SplashScreenActivity.this, SharedPreferencesUtils.getAdInfo());
        }
        if (SplashScreenActivity.this.localAdInfoData.getData().getShow() != 0)
        {
          SplashScreenActivity.access$302(SplashScreenActivity.this, true);
          SplashScreenActivity.this.mHandler.removeCallbacks(this);
          if (SplashScreenActivity.this.showAd(SplashScreenActivity.this.localAdInfoData))
          {
            SplashScreenActivity.this.showCountDown();
            SplashScreenActivity.this.mHandler.postDelayed(this, 3000L);
            return;
          }
          SplashScreenActivity.this.mHandler.post(this);
          return;
        }
        SplashScreenActivity.this.jumpToMainActivity();
        return;
      }
      SplashScreenActivity.this.jumpToMainActivity();
    }
  };
  private volatile AdInfoData localAdInfoData = null;
  private Handler mHandler = new Handler();
  private PercentRelativeLayout skipButton;
  
  private void getAdInfoFromNet()
  {
    NetManager.getInstance().getAdInfo(new NetResponseListener()
    {
      public void onResponse(AdInfoData paramAnonymousAdInfoData)
      {
        if (paramAnonymousAdInfoData != null)
        {
          int i = paramAnonymousAdInfoData.getData().getVersion();
          if (SplashScreenActivity.this.localAdInfoData == null) {
            SplashScreenActivity.access$402(SplashScreenActivity.this, SharedPreferencesUtils.getAdInfo());
          }
          int j = SplashScreenActivity.this.localAdInfoData.getData().getVersion();
          if ((i > j) || ((i == j) && (SplashScreenActivity.this.localAdInfoData.getData().getShow() != paramAnonymousAdInfoData.getData().getShow())))
          {
            SharedPreferencesUtils.setAdInfo(paramAnonymousAdInfoData);
            if (i != 1) {
              SharedPreferencesUtils.setNeedDownloadAdPic(true);
            }
            SplashScreenActivity.access$402(SplashScreenActivity.this, paramAnonymousAdInfoData);
          }
          if (SharedPreferencesUtils.getNeedDownloadAdPic())
          {
            paramAnonymousAdInfoData = SplashScreenActivity.this.localAdInfoData.getData().getImgUrl();
            if (!SplashScreenActivity.this.getResources().getConfiguration().locale.toString().equals("zh_CN")) {
              break label215;
            }
            if (SplashScreenActivity.this.localAdInfoData.getData().getImgUrlAndroidCn() != null) {
              paramAnonymousAdInfoData = SplashScreenActivity.this.localAdInfoData.getData().getImgUrlAndroidCn();
            }
          }
        }
        for (;;)
        {
          NetManager.getInstance().downloadImageByFresco(paramAnonymousAdInfoData, FileUtils.getAppExternalStorageDirectoryPath() + "/pic/", "ad_image.png", new NetResponseListener()
          {
            public void onResponse(Object paramAnonymous2Object)
            {
              SharedPreferencesUtils.setNeedDownloadAdPic(false);
            }
          });
          return;
          label215:
          if (SplashScreenActivity.this.localAdInfoData.getData().getLinkAndroidEn() != null) {
            paramAnonymousAdInfoData = SplashScreenActivity.this.localAdInfoData.getData().getImgUrlAndroidEn();
          }
        }
      }
    });
  }
  
  private void jumpToMainActivity()
  {
    if (isFinishing()) {
      return;
    }
    startActivity(new Intent(this, MainActivity.class));
    finish();
    overridePendingTransition(2130771968, 2130771969);
  }
  
  private void loadAdInfo()
  {
    SettingsManager.loadAdInfo(this);
    getAdInfoFromNet();
  }
  
  private void loadOnBackground()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        SettingsManager.loadSettingsFile(SplashScreenActivity.this);
        SplashScreenActivity.access$102(SplashScreenActivity.this, true);
        if (SplashScreenActivity.this.isTimeOut)
        {
          SplashScreenActivity.this.mHandler.removeCallbacks(SplashScreenActivity.this.jumpToNextRunnable);
          SplashScreenActivity.this.mHandler.post(SplashScreenActivity.this.jumpToNextRunnable);
        }
      }
    }).start();
  }
  
  private boolean showAd(AdInfoData paramAdInfoData)
  {
    this.adImage.setVisibility(0);
    if (!SharedPreferencesUtils.getNeedDownloadAdPic())
    {
      if (paramAdInfoData.getData().getVersion() != 1)
      {
        paramAdInfoData = FileUtils.readSuitableBitmapFromSDCard(FileUtils.getAppExternalStorageDirectoryPath() + File.separator + "pic" + File.separator + "ad_image.png");
        if (paramAdInfoData != null)
        {
          this.adImage.setImageBitmap(paramAdInfoData);
          return true;
        }
        SharedPreferencesUtils.setNeedDownloadAdPic(true);
        jumpToMainActivity();
        return false;
      }
      this.adImage.setImageResource(2131165512);
      return true;
    }
    return false;
  }
  
  private void showCountDown()
  {
    this.skipButton.setVisibility(0);
    this.countDownTimer = new CountDownTimer(3000L, 500L)
    {
      public void onFinish() {}
      
      public void onTick(long paramAnonymousLong)
      {
        SplashScreenActivity.this.countDownText.setText(String.valueOf((int)(paramAnonymousLong / 1000L + 1L)));
      }
    };
    this.countDownTimer.start();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
    case 2131296287: 
      do
      {
        return;
      } while ((this.localAdInfoData.getData().getLink() == null) || (this.localAdInfoData.getData().getLink().equalsIgnoreCase("")));
      this.hasClickAd = true;
      this.mHandler.removeCallbacks(this.jumpToNextRunnable);
      paramView = new Intent("android.intent.action.VIEW");
      paramView.setData(Uri.parse(this.localAdInfoData.getData().getLink()));
      if (getResources().getConfiguration().locale.toString().equals("zh_CN")) {
        if (this.localAdInfoData.getData().getLinkAndroidCn() != null) {
          paramView.setData(Uri.parse(this.localAdInfoData.getData().getLinkAndroidCn()));
        }
      }
      break;
    }
    try
    {
      for (;;)
      {
        startActivity(paramView);
        StatisticsTool.getInstance().onEvent("EnterADPage", "进入广告页");
        return;
        if (this.localAdInfoData.getData().getLinkAndroidEn() != null) {
          paramView.setData(Uri.parse(this.localAdInfoData.getData().getLinkAndroidEn()));
        }
      }
      this.mHandler.removeCallbacks(this.jumpToNextRunnable);
      jumpToMainActivity();
      return;
    }
    catch (ActivityNotFoundException paramView)
    {
      for (;;) {}
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427391);
    loadOnBackground();
    loadAdInfo();
    this.adImage = ((ImageView)findViewById(2131296287));
    this.adImage.setOnClickListener(this);
    this.skipButton = ((PercentRelativeLayout)findViewById(2131296384));
    this.skipButton.setOnClickListener(this);
    this.countDownText = ((TextView)findViewById(2131296418));
    this.mHandler.postDelayed(this.jumpToNextRunnable, 2000L);
  }
  
  protected void onDestroy()
  {
    if (this.countDownTimer != null) {
      this.countDownTimer.cancel();
    }
    this.mHandler.removeCallbacks(this.jumpToNextRunnable);
    super.onDestroy();
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.hasClickAd) {
      jumpToMainActivity();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\splash\SplashScreenActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */