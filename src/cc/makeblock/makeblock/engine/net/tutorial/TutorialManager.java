package cc.makeblock.makeblock.engine.net.tutorial;

import android.util.Log;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;

public class TutorialManager
{
  private static TutorialManager tutorialManager = new TutorialManager();
  private TutorialDownloader tutorialDownloader = new TutorialDownloader();
  private TutorialRequest tutorialRequest = new TutorialRequest();
  private TutorialStorage tutorialStorage = new TutorialStorage();
  
  private boolean checkIfPicsExist(TutorialInfo paramTutorialInfo)
  {
    return this.tutorialDownloader.checkIfPicsExist(paramTutorialInfo);
  }
  
  private void downloadPics(final TutorialInfo paramTutorialInfo)
  {
    this.tutorialDownloader.downLoadPics(paramTutorialInfo, new TutorialDownloader.OnDownloadFinishedListener()
    {
      public void downLoadFinish()
      {
        TutorialManager.this.tutorialStorage.saveTutorialInfo(paramTutorialInfo);
      }
    });
  }
  
  public static TutorialManager getInstance()
  {
    if (tutorialManager == null) {
      tutorialManager = new TutorialManager();
    }
    return tutorialManager;
  }
  
  private void removeOldPics(TutorialInfo paramTutorialInfo)
  {
    if ((SharedPreferencesUtils.getTutorialInfo() != null) && (!this.tutorialDownloader.removeOldPics(SharedPreferencesUtils.getTutorialInfo(), paramTutorialInfo))) {
      Log.w("TutorialManager", "image delete failed!");
    }
  }
  
  public String getPicFileName(String paramString)
  {
    return this.tutorialDownloader.getPicFileName(paramString);
  }
  
  public TutorialInfo.Data.Devices getTutorial()
  {
    if (this.tutorialStorage.getTutorialInfo() != null) {
      return this.tutorialStorage.getTutorialInfo().getData().getDevices();
    }
    return null;
  }
  
  public void getTutorialFromNet()
  {
    this.tutorialRequest.start(new TutorialRequest.RequestCallback()
    {
      public void callback(TutorialInfo paramAnonymousTutorialInfo)
      {
        if (SharedPreferencesUtils.getTutorialInfo() == null) {
          TutorialManager.this.tutorialStorage.saveTutorialInfo(paramAnonymousTutorialInfo);
        }
        do
        {
          do
          {
            TutorialManager.this.removeOldPics(paramAnonymousTutorialInfo);
            TutorialManager.this.downloadPics(paramAnonymousTutorialInfo);
            return;
          } while (paramAnonymousTutorialInfo.getData().getVersion() > SharedPreferencesUtils.getTutorialInfo().getData().getVersion());
        } while (TutorialManager.this.checkIfPicsExist(paramAnonymousTutorialInfo));
        TutorialManager.this.removeOldPics(paramAnonymousTutorialInfo);
        TutorialManager.this.tutorialStorage.saveTutorialInfo(null);
        TutorialManager.this.downloadPics(paramAnonymousTutorialInfo);
      }
    });
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\net\tutorial\TutorialManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */