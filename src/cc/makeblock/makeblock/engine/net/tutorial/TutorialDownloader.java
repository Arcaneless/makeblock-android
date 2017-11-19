package cc.makeblock.makeblock.engine.net.tutorial;

import cc.makeblock.makeblock.engine.net.NetManager;
import cc.makeblock.makeblock.engine.net.NetResponseListener;
import cc.makeblock.makeblock.engine.utils.FileUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

class TutorialDownloader
{
  private int finishedCount = 0;
  private List<String> uris = new ArrayList();
  
  private ArrayList<String> getUnrepeatedUriList(TutorialInfo paramTutorialInfo)
  {
    ArrayList localArrayList = new ArrayList();
    paramTutorialInfo = paramTutorialInfo.getData().getDevices().getDeviceMap().entrySet().iterator();
    while (paramTutorialInfo.hasNext())
    {
      Iterator localIterator = ((TutorialInfo.Data.Devices.Device)((Map.Entry)paramTutorialInfo.next()).getValue()).getLanguageUnitDataMap().entrySet().iterator();
      while (localIterator.hasNext())
      {
        String str = ((TutorialInfo.Data.Devices.Device.LanguageUnitData)((Map.Entry)localIterator.next()).getValue()).getImage();
        if ((str != null) && (!localArrayList.contains(str))) {
          localArrayList.add(str);
        }
      }
    }
    return localArrayList;
  }
  
  boolean checkIfPicsExist(TutorialInfo paramTutorialInfo)
  {
    paramTutorialInfo = getUnrepeatedUriList(paramTutorialInfo);
    int i = 0;
    while (i < paramTutorialInfo.size())
    {
      String str = (String)paramTutorialInfo.get(i);
      if (!new File(FileUtils.getAppExternalStorageDirectoryPath() + "/pic/" + getPicFileName(str)).exists()) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  void downLoadPics(final TutorialInfo paramTutorialInfo, final OnDownloadFinishedListener paramOnDownloadFinishedListener)
  {
    this.uris = getUnrepeatedUriList(paramTutorialInfo);
    int i = 0;
    while (i < this.uris.size())
    {
      paramTutorialInfo = (String)this.uris.get(i);
      final String str = getPicFileName(paramTutorialInfo) + "temp";
      NetManager.getInstance().downloadFile(paramTutorialInfo, FileUtils.getAppExternalStorageDirectoryPath() + "/pic/", str, new NetResponseListener()
      {
        public void onResponse(Object paramAnonymousObject)
        {
          TutorialDownloader.access$008(TutorialDownloader.this);
          paramAnonymousObject = new File(FileUtils.getAppExternalStorageDirectoryPath() + "/pic/", str);
          if (((File)paramAnonymousObject).exists()) {
            ((File)paramAnonymousObject).renameTo(new File(FileUtils.getAppExternalStorageDirectoryPath() + "/pic/", TutorialDownloader.this.getPicFileName(paramTutorialInfo)));
          }
          if (TutorialDownloader.this.finishedCount == TutorialDownloader.this.uris.size()) {
            paramOnDownloadFinishedListener.downLoadFinish();
          }
        }
      });
      i += 1;
    }
  }
  
  String getPicFileName(String paramString)
  {
    if (paramString.length() > 20) {
      return paramString.substring(paramString.length() - 20);
    }
    return paramString;
  }
  
  boolean removeOldPics(TutorialInfo paramTutorialInfo1, TutorialInfo paramTutorialInfo2)
  {
    boolean bool = true;
    paramTutorialInfo1 = getUnrepeatedUriList(paramTutorialInfo1);
    int i = 0;
    while (i < paramTutorialInfo1.size())
    {
      paramTutorialInfo2 = (String)paramTutorialInfo1.get(i);
      paramTutorialInfo2 = new File(FileUtils.getAppExternalStorageDirectoryPath() + "/pic/" + getPicFileName(paramTutorialInfo2));
      if (paramTutorialInfo2.exists()) {
        bool = paramTutorialInfo2.delete();
      }
      i += 1;
    }
    i = 0;
    while (i < paramTutorialInfo1.size())
    {
      paramTutorialInfo2 = (String)paramTutorialInfo1.get(i);
      paramTutorialInfo2 = new File(FileUtils.getAppExternalStorageDirectoryPath() + "/pic/" + getPicFileName(paramTutorialInfo2) + "temp");
      if (paramTutorialInfo2.exists()) {
        bool = paramTutorialInfo2.delete();
      }
      i += 1;
    }
    return bool;
  }
  
  static abstract interface OnDownloadFinishedListener
  {
    public abstract void downLoadFinish();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\net\tutorial\TutorialDownloader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */