package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.Notification.InboxStyle;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequiresApi(16)
class NotificationCompatJellybean
{
  static final String EXTRA_ALLOW_GENERATED_REPLIES = "android.support.allowGeneratedReplies";
  static final String EXTRA_DATA_ONLY_REMOTE_INPUTS = "android.support.dataRemoteInputs";
  private static final String KEY_ACTION_INTENT = "actionIntent";
  private static final String KEY_DATA_ONLY_REMOTE_INPUTS = "dataOnlyRemoteInputs";
  private static final String KEY_EXTRAS = "extras";
  private static final String KEY_ICON = "icon";
  private static final String KEY_REMOTE_INPUTS = "remoteInputs";
  private static final String KEY_TITLE = "title";
  public static final String TAG = "NotificationCompat";
  private static Class<?> sActionClass;
  private static Field sActionIconField;
  private static Field sActionIntentField;
  private static Field sActionTitleField;
  private static boolean sActionsAccessFailed;
  private static Field sActionsField;
  private static final Object sActionsLock = new Object();
  private static Field sExtrasField;
  private static boolean sExtrasFieldAccessFailed;
  private static final Object sExtrasLock = new Object();
  
  public static void addBigPictureStyle(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor, CharSequence paramCharSequence1, boolean paramBoolean1, CharSequence paramCharSequence2, Bitmap paramBitmap1, Bitmap paramBitmap2, boolean paramBoolean2)
  {
    paramNotificationBuilderWithBuilderAccessor = new Notification.BigPictureStyle(paramNotificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(paramCharSequence1).bigPicture(paramBitmap1);
    if (paramBoolean2) {
      paramNotificationBuilderWithBuilderAccessor.bigLargeIcon(paramBitmap2);
    }
    if (paramBoolean1) {
      paramNotificationBuilderWithBuilderAccessor.setSummaryText(paramCharSequence2);
    }
  }
  
  public static void addBigTextStyle(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor, CharSequence paramCharSequence1, boolean paramBoolean, CharSequence paramCharSequence2, CharSequence paramCharSequence3)
  {
    paramNotificationBuilderWithBuilderAccessor = new Notification.BigTextStyle(paramNotificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(paramCharSequence1).bigText(paramCharSequence3);
    if (paramBoolean) {
      paramNotificationBuilderWithBuilderAccessor.setSummaryText(paramCharSequence2);
    }
  }
  
  public static void addInboxStyle(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor, CharSequence paramCharSequence1, boolean paramBoolean, CharSequence paramCharSequence2, ArrayList<CharSequence> paramArrayList)
  {
    paramNotificationBuilderWithBuilderAccessor = new Notification.InboxStyle(paramNotificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(paramCharSequence1);
    if (paramBoolean) {
      paramNotificationBuilderWithBuilderAccessor.setSummaryText(paramCharSequence2);
    }
    paramCharSequence1 = paramArrayList.iterator();
    while (paramCharSequence1.hasNext()) {
      paramNotificationBuilderWithBuilderAccessor.addLine((CharSequence)paramCharSequence1.next());
    }
  }
  
  public static SparseArray<Bundle> buildActionExtrasMap(List<Bundle> paramList)
  {
    Object localObject1 = null;
    int i = 0;
    int j = paramList.size();
    while (i < j)
    {
      Bundle localBundle = (Bundle)paramList.get(i);
      Object localObject2 = localObject1;
      if (localBundle != null)
      {
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new SparseArray();
        }
        ((SparseArray)localObject2).put(i, localBundle);
      }
      i += 1;
      localObject1 = localObject2;
    }
    return (SparseArray<Bundle>)localObject1;
  }
  
  private static boolean ensureActionReflectionReadyLocked()
  {
    boolean bool = true;
    if (sActionsAccessFailed) {
      return false;
    }
    try
    {
      if (sActionsField == null)
      {
        sActionClass = Class.forName("android.app.Notification$Action");
        sActionIconField = sActionClass.getDeclaredField("icon");
        sActionTitleField = sActionClass.getDeclaredField("title");
        sActionIntentField = sActionClass.getDeclaredField("actionIntent");
        sActionsField = Notification.class.getDeclaredField("actions");
        sActionsField.setAccessible(true);
      }
      if (!sActionsAccessFailed) {
        return bool;
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        Log.e("NotificationCompat", "Unable to access notification actions", localClassNotFoundException);
        sActionsAccessFailed = true;
      }
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;)
      {
        Log.e("NotificationCompat", "Unable to access notification actions", localNoSuchFieldException);
        sActionsAccessFailed = true;
        continue;
        bool = false;
      }
    }
  }
  
  /* Error */
  public static NotificationCompatBase.Action getAction(Notification paramNotification, int paramInt, NotificationCompatBase.Action.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1)
  {
    // Byte code:
    //   0: getstatic 62	android/support/v4/app/NotificationCompatJellybean:sActionsLock	Ljava/lang/Object;
    //   3: astore 5
    //   5: aload 5
    //   7: monitorenter
    //   8: aload_0
    //   9: invokestatic 213	android/support/v4/app/NotificationCompatJellybean:getActionObjectsLocked	(Landroid/app/Notification;)[Ljava/lang/Object;
    //   12: astore 4
    //   14: goto +101 -> 115
    //   17: aload_0
    //   18: invokestatic 217	android/support/v4/app/NotificationCompatJellybean:getExtras	(Landroid/app/Notification;)Landroid/os/Bundle;
    //   21: astore 7
    //   23: goto +109 -> 132
    //   26: aload 7
    //   28: ldc -37
    //   30: invokevirtual 223	android/os/Bundle:getSparseParcelableArray	(Ljava/lang/String;)Landroid/util/SparseArray;
    //   33: astore 7
    //   35: goto +108 -> 143
    //   38: aload 7
    //   40: iload_1
    //   41: invokevirtual 224	android/util/SparseArray:get	(I)Ljava/lang/Object;
    //   44: checkcast 150	android/os/Bundle
    //   47: astore_0
    //   48: aload_2
    //   49: aload_3
    //   50: getstatic 183	android/support/v4/app/NotificationCompatJellybean:sActionIconField	Ljava/lang/reflect/Field;
    //   53: aload 6
    //   55: invokevirtual 228	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
    //   58: getstatic 185	android/support/v4/app/NotificationCompatJellybean:sActionTitleField	Ljava/lang/reflect/Field;
    //   61: aload 6
    //   63: invokevirtual 231	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   66: checkcast 131	java/lang/CharSequence
    //   69: getstatic 187	android/support/v4/app/NotificationCompatJellybean:sActionIntentField	Ljava/lang/reflect/Field;
    //   72: aload 6
    //   74: invokevirtual 231	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   77: checkcast 233	android/app/PendingIntent
    //   80: aload_0
    //   81: invokestatic 237	android/support/v4/app/NotificationCompatJellybean:readAction	(Landroid/support/v4/app/NotificationCompatBase$Action$Factory;Landroid/support/v4/app/RemoteInputCompatBase$RemoteInput$Factory;ILjava/lang/CharSequence;Landroid/app/PendingIntent;Landroid/os/Bundle;)Landroid/support/v4/app/NotificationCompatBase$Action;
    //   84: astore_0
    //   85: aload 5
    //   87: monitorexit
    //   88: aload_0
    //   89: areturn
    //   90: ldc 38
    //   92: ldc -57
    //   94: aload_0
    //   95: invokestatic 205	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   98: pop
    //   99: iconst_1
    //   100: putstatic 165	android/support/v4/app/NotificationCompatJellybean:sActionsAccessFailed	Z
    //   103: aload 5
    //   105: monitorexit
    //   106: goto +52 -> 158
    //   109: astore_0
    //   110: aload 5
    //   112: monitorexit
    //   113: aload_0
    //   114: athrow
    //   115: aload 4
    //   117: ifnull -14 -> 103
    //   120: aload 4
    //   122: iload_1
    //   123: aaload
    //   124: astore 6
    //   126: aconst_null
    //   127: astore 4
    //   129: goto -112 -> 17
    //   132: aload 4
    //   134: astore_0
    //   135: aload 7
    //   137: ifnull -89 -> 48
    //   140: goto -114 -> 26
    //   143: aload 4
    //   145: astore_0
    //   146: aload 7
    //   148: ifnull -100 -> 48
    //   151: goto -113 -> 38
    //   154: astore_0
    //   155: goto -65 -> 90
    //   158: aconst_null
    //   159: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	160	0	paramNotification	Notification
    //   0	160	1	paramInt	int
    //   0	160	2	paramFactory	NotificationCompatBase.Action.Factory
    //   0	160	3	paramFactory1	RemoteInputCompatBase.RemoteInput.Factory
    //   12	132	4	arrayOfObject	Object[]
    //   53	72	6	localObject2	Object
    //   21	126	7	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   8	14	109	finally
    //   17	23	109	finally
    //   26	35	109	finally
    //   38	48	109	finally
    //   48	85	109	finally
    //   85	88	109	finally
    //   90	103	109	finally
    //   103	106	109	finally
    //   110	113	109	finally
    //   8	14	154	java/lang/IllegalAccessException
    //   17	23	154	java/lang/IllegalAccessException
    //   26	35	154	java/lang/IllegalAccessException
    //   38	48	154	java/lang/IllegalAccessException
    //   48	85	154	java/lang/IllegalAccessException
  }
  
  public static int getActionCount(Notification paramNotification)
  {
    for (;;)
    {
      synchronized (sActionsLock)
      {
        paramNotification = getActionObjectsLocked(paramNotification);
        if (paramNotification != null)
        {
          i = paramNotification.length;
          return i;
        }
      }
      int i = 0;
    }
  }
  
  private static NotificationCompatBase.Action getActionFromBundle(Bundle paramBundle, NotificationCompatBase.Action.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1)
  {
    Bundle localBundle = paramBundle.getBundle("extras");
    boolean bool = false;
    if (localBundle != null) {
      bool = localBundle.getBoolean("android.support.allowGeneratedReplies", false);
    }
    return paramFactory.build(paramBundle.getInt("icon"), paramBundle.getCharSequence("title"), (PendingIntent)paramBundle.getParcelable("actionIntent"), paramBundle.getBundle("extras"), RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(paramBundle, "remoteInputs"), paramFactory1), RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(paramBundle, "dataOnlyRemoteInputs"), paramFactory1), bool);
  }
  
  private static Object[] getActionObjectsLocked(Notification paramNotification)
  {
    synchronized (sActionsLock)
    {
      if (!ensureActionReflectionReadyLocked()) {
        return null;
      }
    }
    return null;
  }
  
  public static NotificationCompatBase.Action[] getActionsFromParcelableArrayList(ArrayList<Parcelable> paramArrayList, NotificationCompatBase.Action.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1)
  {
    Object localObject;
    if (paramArrayList == null)
    {
      localObject = null;
      return (NotificationCompatBase.Action[])localObject;
    }
    NotificationCompatBase.Action[] arrayOfAction = paramFactory.newArray(paramArrayList.size());
    int i = 0;
    for (;;)
    {
      localObject = arrayOfAction;
      if (i >= arrayOfAction.length) {
        break;
      }
      arrayOfAction[i] = getActionFromBundle((Bundle)paramArrayList.get(i), paramFactory, paramFactory1);
      i += 1;
    }
  }
  
  private static Bundle getBundleForAction(NotificationCompatBase.Action paramAction)
  {
    Bundle localBundle2 = new Bundle();
    localBundle2.putInt("icon", paramAction.getIcon());
    localBundle2.putCharSequence("title", paramAction.getTitle());
    localBundle2.putParcelable("actionIntent", paramAction.getActionIntent());
    if (paramAction.getExtras() != null) {}
    for (Bundle localBundle1 = new Bundle(paramAction.getExtras());; localBundle1 = new Bundle())
    {
      localBundle1.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
      localBundle2.putBundle("extras", localBundle1);
      localBundle2.putParcelableArray("remoteInputs", RemoteInputCompatJellybean.toBundleArray(paramAction.getRemoteInputs()));
      return localBundle2;
    }
  }
  
  public static Bundle getExtras(Notification paramNotification)
  {
    synchronized (sExtrasLock)
    {
      if (sExtrasFieldAccessFailed) {
        return null;
      }
      try
      {
        if (sExtrasField == null)
        {
          localObject1 = Notification.class.getDeclaredField("extras");
          if (!Bundle.class.isAssignableFrom(((Field)localObject1).getType()))
          {
            Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
            sExtrasFieldAccessFailed = true;
            return null;
          }
          ((Field)localObject1).setAccessible(true);
          sExtrasField = (Field)localObject1;
        }
        localBundle = (Bundle)sExtrasField.get(paramNotification);
      }
      catch (IllegalAccessException paramNotification)
      {
        for (;;)
        {
          Bundle localBundle;
          Log.e("NotificationCompat", "Unable to access notification extras", paramNotification);
          for (;;)
          {
            sExtrasFieldAccessFailed = true;
            return null;
            Log.e("NotificationCompat", "Unable to access notification extras", paramNotification);
          }
          Object localObject1 = localBundle;
          if (localBundle != null) {}
        }
      }
      catch (NoSuchFieldException paramNotification)
      {
        for (;;) {}
      }
      localObject1 = new Bundle();
      sExtrasField.set(paramNotification, localObject1);
      return (Bundle)localObject1;
    }
  }
  
  public static ArrayList<Parcelable> getParcelableArrayListForActions(NotificationCompatBase.Action[] paramArrayOfAction)
  {
    Object localObject;
    if (paramArrayOfAction == null)
    {
      localObject = null;
      return (ArrayList<Parcelable>)localObject;
    }
    ArrayList localArrayList = new ArrayList(paramArrayOfAction.length);
    int j = paramArrayOfAction.length;
    int i = 0;
    for (;;)
    {
      localObject = localArrayList;
      if (i >= j) {
        break;
      }
      localArrayList.add(getBundleForAction(paramArrayOfAction[i]));
      i += 1;
    }
  }
  
  public static NotificationCompatBase.Action readAction(NotificationCompatBase.Action.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1, int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle)
  {
    RemoteInputCompatBase.RemoteInput[] arrayOfRemoteInput1 = null;
    RemoteInputCompatBase.RemoteInput[] arrayOfRemoteInput2 = null;
    boolean bool = false;
    if (paramBundle != null)
    {
      arrayOfRemoteInput1 = RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(paramBundle, "android.support.remoteInputs"), paramFactory1);
      arrayOfRemoteInput2 = RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(paramBundle, "android.support.dataRemoteInputs"), paramFactory1);
      bool = paramBundle.getBoolean("android.support.allowGeneratedReplies");
    }
    return paramFactory.build(paramInt, paramCharSequence, paramPendingIntent, paramBundle, arrayOfRemoteInput1, arrayOfRemoteInput2, bool);
  }
  
  public static Bundle writeActionAndGetExtras(Notification.Builder paramBuilder, NotificationCompatBase.Action paramAction)
  {
    paramBuilder.addAction(paramAction.getIcon(), paramAction.getTitle(), paramAction.getActionIntent());
    paramBuilder = new Bundle(paramAction.getExtras());
    if (paramAction.getRemoteInputs() != null) {
      paramBuilder.putParcelableArray("android.support.remoteInputs", RemoteInputCompatJellybean.toBundleArray(paramAction.getRemoteInputs()));
    }
    if (paramAction.getDataOnlyRemoteInputs() != null) {
      paramBuilder.putParcelableArray("android.support.dataRemoteInputs", RemoteInputCompatJellybean.toBundleArray(paramAction.getDataOnlyRemoteInputs()));
    }
    paramBuilder.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
    return paramBuilder;
  }
  
  public static class Builder
    implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions
  {
    private Notification.Builder b;
    private List<Bundle> mActionExtrasList = new ArrayList();
    private RemoteViews mBigContentView;
    private RemoteViews mContentView;
    private final Bundle mExtras;
    
    public Builder(Context paramContext, Notification paramNotification, CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3, RemoteViews paramRemoteViews1, int paramInt1, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, Bitmap paramBitmap, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, int paramInt4, CharSequence paramCharSequence4, boolean paramBoolean3, Bundle paramBundle, String paramString1, boolean paramBoolean4, String paramString2, RemoteViews paramRemoteViews2, RemoteViews paramRemoteViews3)
    {
      paramContext = new Notification.Builder(paramContext).setWhen(paramNotification.when).setSmallIcon(paramNotification.icon, paramNotification.iconLevel).setContent(paramNotification.contentView).setTicker(paramNotification.tickerText, paramRemoteViews1).setSound(paramNotification.sound, paramNotification.audioStreamType).setVibrate(paramNotification.vibrate).setLights(paramNotification.ledARGB, paramNotification.ledOnMS, paramNotification.ledOffMS);
      boolean bool;
      if ((paramNotification.flags & 0x2) != 0)
      {
        bool = true;
        paramContext = paramContext.setOngoing(bool);
        if ((paramNotification.flags & 0x8) == 0) {
          break label349;
        }
        bool = true;
        label123:
        paramContext = paramContext.setOnlyAlertOnce(bool);
        if ((paramNotification.flags & 0x10) == 0) {
          break label355;
        }
        bool = true;
        label143:
        paramContext = paramContext.setAutoCancel(bool).setDefaults(paramNotification.defaults).setContentTitle(paramCharSequence1).setContentText(paramCharSequence2).setSubText(paramCharSequence4).setContentInfo(paramCharSequence3).setContentIntent(paramPendingIntent1).setDeleteIntent(paramNotification.deleteIntent);
        if ((paramNotification.flags & 0x80) == 0) {
          break label361;
        }
        bool = true;
        label202:
        this.b = paramContext.setFullScreenIntent(paramPendingIntent2, bool).setLargeIcon(paramBitmap).setNumber(paramInt1).setUsesChronometer(paramBoolean2).setPriority(paramInt4).setProgress(paramInt2, paramInt3, paramBoolean1);
        this.mExtras = new Bundle();
        if (paramBundle != null) {
          this.mExtras.putAll(paramBundle);
        }
        if (paramBoolean3) {
          this.mExtras.putBoolean("android.support.localOnly", true);
        }
        if (paramString1 != null)
        {
          this.mExtras.putString("android.support.groupKey", paramString1);
          if (!paramBoolean4) {
            break label367;
          }
          this.mExtras.putBoolean("android.support.isGroupSummary", true);
        }
      }
      for (;;)
      {
        if (paramString2 != null) {
          this.mExtras.putString("android.support.sortKey", paramString2);
        }
        this.mContentView = paramRemoteViews2;
        this.mBigContentView = paramRemoteViews3;
        return;
        bool = false;
        break;
        label349:
        bool = false;
        break label123;
        label355:
        bool = false;
        break label143;
        label361:
        bool = false;
        break label202;
        label367:
        this.mExtras.putBoolean("android.support.useSideChannel", true);
      }
    }
    
    public void addAction(NotificationCompatBase.Action paramAction)
    {
      this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.b, paramAction));
    }
    
    public Notification build()
    {
      Notification localNotification = this.b.build();
      Object localObject = NotificationCompatJellybean.getExtras(localNotification);
      Bundle localBundle = new Bundle(this.mExtras);
      Iterator localIterator = this.mExtras.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (((Bundle)localObject).containsKey(str)) {
          localBundle.remove(str);
        }
      }
      ((Bundle)localObject).putAll(localBundle);
      localObject = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
      if (localObject != null) {
        NotificationCompatJellybean.getExtras(localNotification).putSparseParcelableArray("android.support.actionExtras", (SparseArray)localObject);
      }
      if (this.mContentView != null) {
        localNotification.contentView = this.mContentView;
      }
      if (this.mBigContentView != null) {
        localNotification.bigContentView = this.mBigContentView;
      }
      return localNotification;
    }
    
    public Notification.Builder getBuilder()
    {
      return this.b;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\support\v4\app\NotificationCompatJellybean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */