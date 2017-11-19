package com.iflytek.speech;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.thirdparty.ad;
import com.iflytek.cloud.thirdparty.ag;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

abstract class SpeechModuleAidl<I extends IInterface>
  implements ISpeechModule
{
  private String mBindAction = null;
  private ServiceConnection mConnection = null;
  protected Context mContext = null;
  private InitListener mInitListener = null;
  private HashMap<String, String> mParams = new HashMap();
  protected I mService;
  protected Object mSynLock = new Object();
  private Handler mUiHandler = new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (SpeechModuleAidl.this.mInitListener == null) {
        return;
      }
      SpeechModuleAidl.this.mInitListener.onInit(paramAnonymousMessage.what);
    }
  };
  private volatile boolean userDestroy = false;
  
  public SpeechModuleAidl(Context paramContext, InitListener paramInitListener, String paramString)
  {
    this.mContext = paramContext;
    this.mInitListener = paramInitListener;
    this.mBindAction = paramString;
    bindService();
  }
  
  private void bindService()
  {
    if (!isActionInstalled(this.mContext, this.mBindAction))
    {
      if (this.mInitListener != null) {
        Message.obtain(this.mUiHandler, 21001, 0, 0, null).sendToTarget();
      }
      return;
    }
    Intent localIntent = getIntent();
    localIntent.setAction(this.mBindAction);
    localIntent.setPackage("com.iflytek.vflynote");
    this.mConnection = new ServiceConnection()
    {
      public void onServiceConnected(ComponentName arg1, IBinder paramAnonymousIBinder)
      {
        synchronized (SpeechModuleAidl.this.mSynLock)
        {
          Log.d(SpeechModuleAidl.this.getTag(), "init success");
          SpeechModuleAidl.this.mService = SpeechModuleAidl.this.getService(paramAnonymousIBinder);
          Log.d(SpeechModuleAidl.this.getTag(), "mService :" + SpeechModuleAidl.this.mService);
          if (SpeechModuleAidl.this.mInitListener != null) {
            Message.obtain(SpeechModuleAidl.this.mUiHandler, 0, 0, 0, null).sendToTarget();
          }
          return;
        }
      }
      
      public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
      {
        Log.d(SpeechModuleAidl.this.getTag(), "onServiceDisconnected");
        SpeechModuleAidl.this.mService = null;
        if (!SpeechModuleAidl.this.userDestroy) {}
        try
        {
          SpeechModuleAidl.this.bindService();
          return;
        }
        catch (Exception paramAnonymousComponentName)
        {
          Log.e(SpeechModuleAidl.this.getTag(), "rebindService error = " + paramAnonymousComponentName.toString());
        }
      }
    };
    try
    {
      this.mContext.bindService(localIntent, this.mConnection, 1);
      return;
    }
    catch (SecurityException localSecurityException)
    {
      ad.a(localSecurityException);
    }
  }
  
  private I getService(IBinder paramIBinder)
  {
    try
    {
      String str = ((Class)((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
      Log.d(getTag(), "className = " + str);
      paramIBinder = (IInterface)Class.forName(str + "$Stub").getDeclaredMethod("asInterface", new Class[] { IBinder.class }).invoke(null, new Object[] { paramIBinder });
      return paramIBinder;
    }
    catch (ClassNotFoundException paramIBinder)
    {
      ad.a(paramIBinder);
      return null;
    }
    catch (SecurityException paramIBinder)
    {
      for (;;)
      {
        ad.a(paramIBinder);
      }
    }
    catch (NoSuchMethodException paramIBinder)
    {
      for (;;)
      {
        ad.a(paramIBinder);
      }
    }
    catch (IllegalArgumentException paramIBinder)
    {
      for (;;)
      {
        ad.a(paramIBinder);
      }
    }
    catch (IllegalAccessException paramIBinder)
    {
      for (;;)
      {
        ad.a(paramIBinder);
      }
    }
    catch (InvocationTargetException paramIBinder)
    {
      for (;;)
      {
        ad.a(paramIBinder);
      }
    }
    catch (Exception paramIBinder)
    {
      for (;;)
      {
        ad.a(paramIBinder);
      }
    }
  }
  
  public boolean destory()
  {
    Log.d(getTag(), "destory");
    try
    {
      this.userDestroy = true;
      if (this.mConnection != null)
      {
        this.mContext.unbindService(this.mConnection);
        this.mConnection = null;
      }
      return true;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      ad.a(localIllegalArgumentException);
    }
    return false;
  }
  
  public Intent getIntent()
  {
    Intent localIntent = new Intent();
    if (!this.mParams.isEmpty())
    {
      Object localObject1 = this.mParams.keySet().iterator();
      Object localObject2;
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (String)((Iterator)localObject1).next();
        localIntent.putExtra((String)localObject2, (String)this.mParams.get(localObject2));
      }
      localObject1 = new ag((String)this.mParams.get("params"), (String[][])null).c();
      if ((localObject1 != null) && (!((HashMap)localObject1).isEmpty()))
      {
        localObject2 = ((HashMap)localObject1).keySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          String str = (String)((Iterator)localObject2).next();
          localIntent.putExtra(str, (String)((HashMap)localObject1).get(str));
        }
      }
    }
    localIntent.putExtra("caller.appid", SpeechUtility.getUtility().getParameter("appid"));
    localIntent.putExtra("caller.name", UtilityConfig.getCallerInfo(this.mContext, "caller.name"));
    localIntent.putExtra("caller.pkg", UtilityConfig.getCallerInfo(this.mContext, "caller.pkg"));
    localIntent.putExtra("caller.ver.name", UtilityConfig.getCallerInfo(this.mContext, "caller.ver.name"));
    localIntent.putExtra("caller.ver.code", UtilityConfig.getCallerInfo(this.mContext, "caller.ver.code"));
    return localIntent;
  }
  
  public String getParameter(String paramString)
  {
    return (String)this.mParams.get(paramString);
  }
  
  protected final String getTag()
  {
    return getClass().toString();
  }
  
  public boolean isActionInstalled(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {}
    while (paramContext.getPackageManager().resolveService(new Intent(paramString), 0) == null) {
      return false;
    }
    return true;
  }
  
  public boolean isAvailable()
  {
    return this.mService != null;
  }
  
  public int setParameter(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return 20012;
    }
    if (TextUtils.isEmpty(paramString2))
    {
      this.mParams.remove(paramString1);
      return 0;
    }
    this.mParams.put(paramString1, paramString2);
    return 0;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\speech\SpeechModuleAidl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */