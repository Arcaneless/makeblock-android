package com.iflytek.cloud.util;

import android.content.Context;
import com.iflytek.cloud.thirdparty.aD;

public abstract class ContactManager
{
  public static ContactManager createManager(Context paramContext, ContactListener paramContactListener)
  {
    return aD.a(paramContext, paramContactListener);
  }
  
  public static void destroy() {}
  
  public static ContactManager getManager()
  {
    return aD.a();
  }
  
  public abstract void asyncQueryAllContactsName();
  
  public abstract String queryAllContactsName();
  
  public static abstract interface ContactListener
  {
    public abstract void onContactQueryFinish(String paramString, boolean paramBoolean);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\util\ContactManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */