package cc.makeblock.makeblock.engine.net.tutorial;

import android.util.Log;
import cc.makeblock.makeblock.engine.net.NetManager;
import cc.makeblock.makeblock.engine.net.NetResponseListener;

class TutorialRequest
{
  public void start(final RequestCallback paramRequestCallback)
  {
    NetManager.getInstance().getProjectItemAdInfo(new NetResponseListener()
    {
      public void onResponse(TutorialInfo paramAnonymousTutorialInfo)
      {
        if (paramAnonymousTutorialInfo != null)
        {
          paramRequestCallback.callback(paramAnonymousTutorialInfo);
          return;
        }
        Log.w("TutorialRequest", "Fail downloading!");
      }
    });
  }
  
  static abstract interface RequestCallback
  {
    public abstract void callback(TutorialInfo paramTutorialInfo);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\net\tutorial\TutorialRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */