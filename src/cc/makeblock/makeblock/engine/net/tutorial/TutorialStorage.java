package cc.makeblock.makeblock.engine.net.tutorial;

import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;

class TutorialStorage
  implements TutorialStorageInterface
{
  public TutorialInfo getTutorialInfo()
  {
    return SharedPreferencesUtils.getTutorialInfo();
  }
  
  public void saveTutorialInfo(TutorialInfo paramTutorialInfo)
  {
    SharedPreferencesUtils.setTutorialInfo(paramTutorialInfo);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\net\tutorial\TutorialStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */