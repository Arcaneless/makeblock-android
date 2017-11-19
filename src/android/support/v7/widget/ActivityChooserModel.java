package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class ActivityChooserModel
  extends DataSetObservable
{
  static final String ATTRIBUTE_ACTIVITY = "activity";
  static final String ATTRIBUTE_TIME = "time";
  static final String ATTRIBUTE_WEIGHT = "weight";
  static final boolean DEBUG = false;
  private static final int DEFAULT_ACTIVITY_INFLATION = 5;
  private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0F;
  public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
  public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
  private static final String HISTORY_FILE_EXTENSION = ".xml";
  private static final int INVALID_INDEX = -1;
  static final String LOG_TAG = ActivityChooserModel.class.getSimpleName();
  static final String TAG_HISTORICAL_RECORD = "historical-record";
  static final String TAG_HISTORICAL_RECORDS = "historical-records";
  private static final Map<String, ActivityChooserModel> sDataModelRegistry = new HashMap();
  private static final Object sRegistryLock = new Object();
  private final List<ActivityResolveInfo> mActivities = new ArrayList();
  private OnChooseActivityListener mActivityChoserModelPolicy;
  private ActivitySorter mActivitySorter = new DefaultSorter();
  boolean mCanReadHistoricalData = true;
  final Context mContext;
  private final List<HistoricalRecord> mHistoricalRecords = new ArrayList();
  private boolean mHistoricalRecordsChanged = true;
  final String mHistoryFileName;
  private int mHistoryMaxSize = 50;
  private final Object mInstanceLock = new Object();
  private Intent mIntent;
  private boolean mReadShareHistoryCalled = false;
  private boolean mReloadActivities = false;
  
  private ActivityChooserModel(Context paramContext, String paramString)
  {
    this.mContext = paramContext.getApplicationContext();
    if ((!TextUtils.isEmpty(paramString)) && (!paramString.endsWith(".xml")))
    {
      this.mHistoryFileName = (paramString + ".xml");
      return;
    }
    this.mHistoryFileName = paramString;
  }
  
  private boolean addHistoricalRecord(HistoricalRecord paramHistoricalRecord)
  {
    boolean bool = this.mHistoricalRecords.add(paramHistoricalRecord);
    if (bool)
    {
      this.mHistoricalRecordsChanged = true;
      pruneExcessiveHistoricalRecordsIfNeeded();
      persistHistoricalDataIfNeeded();
      sortActivitiesIfNeeded();
      notifyChanged();
    }
    return bool;
  }
  
  private void ensureConsistentState()
  {
    boolean bool1 = loadActivitiesIfNeeded();
    boolean bool2 = readHistoricalDataIfNeeded();
    pruneExcessiveHistoricalRecordsIfNeeded();
    if ((bool1 | bool2))
    {
      sortActivitiesIfNeeded();
      notifyChanged();
    }
  }
  
  public static ActivityChooserModel get(Context paramContext, String paramString)
  {
    for (;;)
    {
      ActivityChooserModel localActivityChooserModel2;
      synchronized (sRegistryLock)
      {
        localActivityChooserModel2 = (ActivityChooserModel)sDataModelRegistry.get(paramString);
        break label56;
        localActivityChooserModel1 = new ActivityChooserModel(paramContext, paramString);
        sDataModelRegistry.put(paramString, localActivityChooserModel1);
        return localActivityChooserModel1;
      }
      label56:
      ActivityChooserModel localActivityChooserModel1 = localActivityChooserModel2;
      if (localActivityChooserModel2 != null) {}
    }
  }
  
  private boolean loadActivitiesIfNeeded()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.mReloadActivities)
    {
      bool1 = bool2;
      if (this.mIntent != null)
      {
        this.mReloadActivities = false;
        this.mActivities.clear();
        List localList = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
        int j = localList.size();
        int i = 0;
        while (i < j)
        {
          ResolveInfo localResolveInfo = (ResolveInfo)localList.get(i);
          this.mActivities.add(new ActivityResolveInfo(localResolveInfo));
          i += 1;
        }
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private void persistHistoricalDataIfNeeded()
  {
    if (!this.mReadShareHistoryCalled) {
      throw new IllegalStateException("No preceding call to #readHistoricalData");
    }
    if (!this.mHistoricalRecordsChanged) {}
    do
    {
      return;
      this.mHistoricalRecordsChanged = false;
    } while (TextUtils.isEmpty(this.mHistoryFileName));
    new PersistHistoryAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[] { new ArrayList(this.mHistoricalRecords), this.mHistoryFileName });
  }
  
  private void pruneExcessiveHistoricalRecordsIfNeeded()
  {
    int j = this.mHistoricalRecords.size() - this.mHistoryMaxSize;
    if (j <= 0) {}
    for (;;)
    {
      return;
      this.mHistoricalRecordsChanged = true;
      int i = 0;
      while (i < j)
      {
        HistoricalRecord localHistoricalRecord = (HistoricalRecord)this.mHistoricalRecords.remove(0);
        i += 1;
      }
    }
  }
  
  private boolean readHistoricalDataIfNeeded()
  {
    if ((this.mCanReadHistoricalData) && (this.mHistoricalRecordsChanged) && (!TextUtils.isEmpty(this.mHistoryFileName)))
    {
      this.mCanReadHistoricalData = false;
      this.mReadShareHistoryCalled = true;
      readHistoricalDataImpl();
      return true;
    }
    return false;
  }
  
  private void readHistoricalDataImpl()
  {
    label345:
    label346:
    label349:
    for (;;)
    {
      try
      {
        FileInputStream localFileInputStream = this.mContext.openFileInput(this.mHistoryFileName);
        try
        {
          localXmlPullParser = Xml.newPullParser();
          localXmlPullParser.setInput(localFileInputStream, "UTF-8");
        }
        catch (XmlPullParserException localXmlPullParserException)
        {
          XmlPullParser localXmlPullParser;
          Log.e(LOG_TAG, "Error reading historical recrod file: " + this.mHistoryFileName, localXmlPullParserException);
          if (localFileInputStream == null) {
            break label345;
          }
          try
          {
            localFileInputStream.close();
            return;
          }
          catch (IOException localIOException1)
          {
            return;
          }
          localList = this.mHistoricalRecords;
          localList.clear();
          i = localXmlPullParserException.next();
          if (i != 1) {
            continue;
          }
          if (localIOException1 == null) {
            break label345;
          }
          try
          {
            localIOException1.close();
            return;
          }
          catch (IOException localIOException2)
          {
            return;
          }
          if ((i == 3) || (i == 4)) {
            continue;
          }
          if ("historical-record".equals(localXmlPullParserException.getName())) {
            continue;
          }
          throw new XmlPullParserException("Share records file not well-formed.");
        }
        catch (IOException localIOException5)
        {
          List localList;
          Log.e(LOG_TAG, "Error reading historical recrod file: " + this.mHistoryFileName, localIOException5);
          if (localIOException2 == null) {
            break label345;
          }
          try
          {
            localIOException2.close();
            return;
          }
          catch (IOException localIOException3)
          {
            return;
          }
          String str = localIOException5.getAttributeValue(null, "activity");
          break label346;
          long l = Long.parseLong(localIOException5.getAttributeValue(null, "time"));
          break label349;
          localList.add(new HistoricalRecord(str, l, Float.parseFloat(localIOException5.getAttributeValue(null, "weight"))));
          continue;
        }
        finally
        {
          if (localIOException3 == null) {
            continue;
          }
          try
          {
            localIOException3.close();
            throw ((Throwable)localObject);
          }
          catch (IOException localIOException4)
          {
            continue;
          }
          int i = 0;
          if ((i == 1) || (i == 2)) {
            continue;
          }
          continue;
        }
        i = localXmlPullParser.next();
        continue;
        if (!"historical-records".equals(localXmlPullParser.getName())) {
          throw new XmlPullParserException("Share records file does not start with historical-records tag.");
        }
        return;
      }
      catch (FileNotFoundException localFileNotFoundException) {}
    }
  }
  
  private boolean sortActivitiesIfNeeded()
  {
    if ((this.mActivitySorter != null) && (this.mIntent != null) && (!this.mActivities.isEmpty()) && (!this.mHistoricalRecords.isEmpty()))
    {
      this.mActivitySorter.sort(this.mIntent, this.mActivities, Collections.unmodifiableList(this.mHistoricalRecords));
      return true;
    }
    return false;
  }
  
  public Intent chooseActivity(int paramInt)
  {
    for (;;)
    {
      boolean bool;
      synchronized (this.mInstanceLock)
      {
        if (this.mIntent == null) {
          return null;
        }
        ensureConsistentState();
        Object localObject2 = (ActivityResolveInfo)this.mActivities.get(paramInt);
        localObject2 = new ComponentName(((ActivityResolveInfo)localObject2).resolveInfo.activityInfo.packageName, ((ActivityResolveInfo)localObject2).resolveInfo.activityInfo.name);
        Intent localIntent1 = new Intent(this.mIntent);
        localIntent1.setComponent((ComponentName)localObject2);
        if (this.mActivityChoserModelPolicy != null)
        {
          Intent localIntent2 = new Intent(localIntent1);
          bool = this.mActivityChoserModelPolicy.onChooseActivity(this, localIntent2);
          break label157;
          return null;
        }
        else
        {
          addHistoricalRecord(new HistoricalRecord((ComponentName)localObject2, System.currentTimeMillis(), 1.0F));
          return localIntent1;
        }
      }
      label157:
      if (!bool) {}
    }
  }
  
  public ResolveInfo getActivity(int paramInt)
  {
    synchronized (this.mInstanceLock)
    {
      ensureConsistentState();
      ResolveInfo localResolveInfo = ((ActivityResolveInfo)this.mActivities.get(paramInt)).resolveInfo;
      return localResolveInfo;
    }
  }
  
  public int getActivityCount()
  {
    synchronized (this.mInstanceLock)
    {
      ensureConsistentState();
      int i = this.mActivities.size();
      return i;
    }
  }
  
  public int getActivityIndex(ResolveInfo paramResolveInfo)
  {
    int j;
    synchronized (this.mInstanceLock)
    {
      ensureConsistentState();
      List localList = this.mActivities;
      j = localList.size();
      break label64;
      if (((ActivityResolveInfo)localList.get(i)).resolveInfo != paramResolveInfo) {
        break label74;
      }
      return i;
      label53:
      return -1;
    }
    label64:
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label53;
      }
      break;
      label74:
      i += 1;
    }
  }
  
  public ResolveInfo getDefaultActivity()
  {
    synchronized (this.mInstanceLock)
    {
      ensureConsistentState();
      if (!this.mActivities.isEmpty())
      {
        ResolveInfo localResolveInfo = ((ActivityResolveInfo)this.mActivities.get(0)).resolveInfo;
        return localResolveInfo;
      }
    }
    return null;
  }
  
  public int getHistoryMaxSize()
  {
    synchronized (this.mInstanceLock)
    {
      int i = this.mHistoryMaxSize;
      return i;
    }
  }
  
  public int getHistorySize()
  {
    synchronized (this.mInstanceLock)
    {
      ensureConsistentState();
      int i = this.mHistoricalRecords.size();
      return i;
    }
  }
  
  public Intent getIntent()
  {
    synchronized (this.mInstanceLock)
    {
      Intent localIntent = this.mIntent;
      return localIntent;
    }
  }
  
  public void setActivitySorter(ActivitySorter paramActivitySorter)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mActivitySorter == paramActivitySorter) {
        return;
      }
      this.mActivitySorter = paramActivitySorter;
      if (sortActivitiesIfNeeded()) {
        notifyChanged();
      }
      return;
    }
  }
  
  public void setDefaultActivity(int paramInt)
  {
    for (;;)
    {
      ActivityResolveInfo localActivityResolveInfo2;
      float f;
      synchronized (this.mInstanceLock)
      {
        ensureConsistentState();
        ActivityResolveInfo localActivityResolveInfo1 = (ActivityResolveInfo)this.mActivities.get(paramInt);
        localActivityResolveInfo2 = (ActivityResolveInfo)this.mActivities.get(0);
        break label115;
        f = localActivityResolveInfo2.weight - localActivityResolveInfo1.weight + 5.0F;
        addHistoricalRecord(new HistoricalRecord(new ComponentName(localActivityResolveInfo1.resolveInfo.activityInfo.packageName, localActivityResolveInfo1.resolveInfo.activityInfo.name), System.currentTimeMillis(), f));
        return;
      }
      label115:
      if (localActivityResolveInfo2 == null) {
        f = 1.0F;
      }
    }
  }
  
  public void setHistoryMaxSize(int paramInt)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mHistoryMaxSize == paramInt) {
        return;
      }
      this.mHistoryMaxSize = paramInt;
      pruneExcessiveHistoricalRecordsIfNeeded();
      if (sortActivitiesIfNeeded()) {
        notifyChanged();
      }
      return;
    }
  }
  
  public void setIntent(Intent paramIntent)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mIntent == paramIntent) {
        return;
      }
      this.mIntent = paramIntent;
      this.mReloadActivities = true;
      ensureConsistentState();
      return;
    }
  }
  
  public void setOnChooseActivityListener(OnChooseActivityListener paramOnChooseActivityListener)
  {
    synchronized (this.mInstanceLock)
    {
      this.mActivityChoserModelPolicy = paramOnChooseActivityListener;
      return;
    }
  }
  
  public static abstract interface ActivityChooserModelClient
  {
    public abstract void setActivityChooserModel(ActivityChooserModel paramActivityChooserModel);
  }
  
  public static final class ActivityResolveInfo
    implements Comparable<ActivityResolveInfo>
  {
    public final ResolveInfo resolveInfo;
    public float weight;
    
    public ActivityResolveInfo(ResolveInfo paramResolveInfo)
    {
      this.resolveInfo = paramResolveInfo;
    }
    
    public int compareTo(ActivityResolveInfo paramActivityResolveInfo)
    {
      return Float.floatToIntBits(paramActivityResolveInfo.weight) - Float.floatToIntBits(this.weight);
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (ActivityResolveInfo)paramObject;
      } while (Float.floatToIntBits(this.weight) == Float.floatToIntBits(((ActivityResolveInfo)paramObject).weight));
      return false;
    }
    
    public int hashCode()
    {
      return Float.floatToIntBits(this.weight) + 31;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append("resolveInfo:").append(this.resolveInfo.toString());
      localStringBuilder.append("; weight:").append(new BigDecimal(this.weight));
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
  
  public static abstract interface ActivitySorter
  {
    public abstract void sort(Intent paramIntent, List<ActivityChooserModel.ActivityResolveInfo> paramList, List<ActivityChooserModel.HistoricalRecord> paramList1);
  }
  
  private static final class DefaultSorter
    implements ActivityChooserModel.ActivitySorter
  {
    private static final float WEIGHT_DECAY_COEFFICIENT = 0.95F;
    private final Map<ComponentName, ActivityChooserModel.ActivityResolveInfo> mPackageNameToActivityMap = new HashMap();
    
    public void sort(Intent paramIntent, List<ActivityChooserModel.ActivityResolveInfo> paramList, List<ActivityChooserModel.HistoricalRecord> paramList1)
    {
      paramIntent = this.mPackageNameToActivityMap;
      paramIntent.clear();
      int j = paramList.size();
      int i = 0;
      Object localObject;
      while (i < j)
      {
        localObject = (ActivityChooserModel.ActivityResolveInfo)paramList.get(i);
        ((ActivityChooserModel.ActivityResolveInfo)localObject).weight = 0.0F;
        paramIntent.put(new ComponentName(((ActivityChooserModel.ActivityResolveInfo)localObject).resolveInfo.activityInfo.packageName, ((ActivityChooserModel.ActivityResolveInfo)localObject).resolveInfo.activityInfo.name), localObject);
        i += 1;
      }
      i = paramList1.size();
      float f1 = 1.0F;
      i -= 1;
      while (i >= 0)
      {
        localObject = (ActivityChooserModel.HistoricalRecord)paramList1.get(i);
        ActivityChooserModel.ActivityResolveInfo localActivityResolveInfo = (ActivityChooserModel.ActivityResolveInfo)paramIntent.get(((ActivityChooserModel.HistoricalRecord)localObject).activity);
        float f2 = f1;
        if (localActivityResolveInfo != null)
        {
          localActivityResolveInfo.weight += ((ActivityChooserModel.HistoricalRecord)localObject).weight * f1;
          f2 = f1 * 0.95F;
        }
        i -= 1;
        f1 = f2;
      }
      Collections.sort(paramList);
    }
  }
  
  public static final class HistoricalRecord
  {
    public final ComponentName activity;
    public final long time;
    public final float weight;
    
    public HistoricalRecord(ComponentName paramComponentName, long paramLong, float paramFloat)
    {
      this.activity = paramComponentName;
      this.time = paramLong;
      this.weight = paramFloat;
    }
    
    public HistoricalRecord(String paramString, long paramLong, float paramFloat)
    {
      this(ComponentName.unflattenFromString(paramString), paramLong, paramFloat);
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (HistoricalRecord)paramObject;
        if (this.activity == null)
        {
          if (((HistoricalRecord)paramObject).activity != null) {
            return false;
          }
        }
        else if (!this.activity.equals(((HistoricalRecord)paramObject).activity)) {
          return false;
        }
        if (this.time != ((HistoricalRecord)paramObject).time) {
          return false;
        }
      } while (Float.floatToIntBits(this.weight) == Float.floatToIntBits(((HistoricalRecord)paramObject).weight));
      return false;
    }
    
    public int hashCode()
    {
      if (this.activity == null) {}
      for (int i = 0;; i = this.activity.hashCode()) {
        return ((i + 31) * 31 + (int)(this.time ^ this.time >>> 32)) * 31 + Float.floatToIntBits(this.weight);
      }
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append("; activity:").append(this.activity);
      localStringBuilder.append("; time:").append(this.time);
      localStringBuilder.append("; weight:").append(new BigDecimal(this.weight));
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
  
  public static abstract interface OnChooseActivityListener
  {
    public abstract boolean onChooseActivity(ActivityChooserModel paramActivityChooserModel, Intent paramIntent);
  }
  
  private final class PersistHistoryAsyncTask
    extends AsyncTask<Object, Void, Void>
  {
    PersistHistoryAsyncTask() {}
    
    /* Error */
    public Void doInBackground(Object... paramVarArgs)
    {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: aaload
      //   3: checkcast 33	java/util/List
      //   6: astore 4
      //   8: aload_1
      //   9: iconst_1
      //   10: aaload
      //   11: checkcast 35	java/lang/String
      //   14: astore 5
      //   16: aload_0
      //   17: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   20: getfield 39	android/support/v7/widget/ActivityChooserModel:mContext	Landroid/content/Context;
      //   23: aload 5
      //   25: iconst_0
      //   26: invokevirtual 45	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
      //   29: astore_1
      //   30: invokestatic 51	android/util/Xml:newSerializer	()Lorg/xmlpull/v1/XmlSerializer;
      //   33: astore 5
      //   35: aload 5
      //   37: aload_1
      //   38: aconst_null
      //   39: invokeinterface 57 3 0
      //   44: aload 5
      //   46: ldc 59
      //   48: iconst_1
      //   49: invokestatic 65	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   52: invokeinterface 69 3 0
      //   57: aload 5
      //   59: aconst_null
      //   60: ldc 71
      //   62: invokeinterface 75 3 0
      //   67: pop
      //   68: aload 4
      //   70: invokeinterface 79 1 0
      //   75: istore_3
      //   76: goto +375 -> 451
      //   79: aload 4
      //   81: iconst_0
      //   82: invokeinterface 83 2 0
      //   87: checkcast 85	android/support/v7/widget/ActivityChooserModel$HistoricalRecord
      //   90: astore 6
      //   92: aload 5
      //   94: aconst_null
      //   95: ldc 87
      //   97: invokeinterface 75 3 0
      //   102: pop
      //   103: aload 5
      //   105: aconst_null
      //   106: ldc 89
      //   108: aload 6
      //   110: getfield 92	android/support/v7/widget/ActivityChooserModel$HistoricalRecord:activity	Landroid/content/ComponentName;
      //   113: invokevirtual 98	android/content/ComponentName:flattenToString	()Ljava/lang/String;
      //   116: invokeinterface 102 4 0
      //   121: pop
      //   122: aload 5
      //   124: aconst_null
      //   125: ldc 104
      //   127: aload 6
      //   129: getfield 107	android/support/v7/widget/ActivityChooserModel$HistoricalRecord:time	J
      //   132: invokestatic 110	java/lang/String:valueOf	(J)Ljava/lang/String;
      //   135: invokeinterface 102 4 0
      //   140: pop
      //   141: aload 5
      //   143: aconst_null
      //   144: ldc 112
      //   146: aload 6
      //   148: getfield 115	android/support/v7/widget/ActivityChooserModel$HistoricalRecord:weight	F
      //   151: invokestatic 118	java/lang/String:valueOf	(F)Ljava/lang/String;
      //   154: invokeinterface 102 4 0
      //   159: pop
      //   160: aload 5
      //   162: aconst_null
      //   163: ldc 87
      //   165: invokeinterface 121 3 0
      //   170: pop
      //   171: iload_2
      //   172: iconst_1
      //   173: iadd
      //   174: istore_2
      //   175: goto +278 -> 453
      //   178: astore_1
      //   179: getstatic 125	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
      //   182: new 127	java/lang/StringBuilder
      //   185: dup
      //   186: invokespecial 128	java/lang/StringBuilder:<init>	()V
      //   189: ldc -126
      //   191: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   194: aload 5
      //   196: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   199: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   202: aload_1
      //   203: invokestatic 143	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   206: pop
      //   207: aconst_null
      //   208: areturn
      //   209: aload 5
      //   211: aconst_null
      //   212: ldc 71
      //   214: invokeinterface 121 3 0
      //   219: pop
      //   220: aload 5
      //   222: invokeinterface 146 1 0
      //   227: aload_0
      //   228: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   231: iconst_1
      //   232: putfield 150	android/support/v7/widget/ActivityChooserModel:mCanReadHistoricalData	Z
      //   235: aload_1
      //   236: ifnull +7 -> 243
      //   239: aload_1
      //   240: invokevirtual 155	java/io/FileOutputStream:close	()V
      //   243: aconst_null
      //   244: areturn
      //   245: astore 4
      //   247: getstatic 125	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
      //   250: new 127	java/lang/StringBuilder
      //   253: dup
      //   254: invokespecial 128	java/lang/StringBuilder:<init>	()V
      //   257: ldc -126
      //   259: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   262: aload_0
      //   263: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   266: getfield 158	android/support/v7/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
      //   269: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   272: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   275: aload 4
      //   277: invokestatic 143	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   280: pop
      //   281: aload_0
      //   282: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   285: iconst_1
      //   286: putfield 150	android/support/v7/widget/ActivityChooserModel:mCanReadHistoricalData	Z
      //   289: aload_1
      //   290: ifnull -47 -> 243
      //   293: aload_1
      //   294: invokevirtual 155	java/io/FileOutputStream:close	()V
      //   297: goto -54 -> 243
      //   300: astore_1
      //   301: goto -58 -> 243
      //   304: astore 4
      //   306: getstatic 125	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
      //   309: new 127	java/lang/StringBuilder
      //   312: dup
      //   313: invokespecial 128	java/lang/StringBuilder:<init>	()V
      //   316: ldc -126
      //   318: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   321: aload_0
      //   322: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   325: getfield 158	android/support/v7/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
      //   328: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   331: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   334: aload 4
      //   336: invokestatic 143	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   339: pop
      //   340: aload_0
      //   341: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   344: iconst_1
      //   345: putfield 150	android/support/v7/widget/ActivityChooserModel:mCanReadHistoricalData	Z
      //   348: aload_1
      //   349: ifnull -106 -> 243
      //   352: aload_1
      //   353: invokevirtual 155	java/io/FileOutputStream:close	()V
      //   356: goto -113 -> 243
      //   359: astore_1
      //   360: goto -117 -> 243
      //   363: astore 4
      //   365: getstatic 125	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
      //   368: new 127	java/lang/StringBuilder
      //   371: dup
      //   372: invokespecial 128	java/lang/StringBuilder:<init>	()V
      //   375: ldc -126
      //   377: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   380: aload_0
      //   381: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   384: getfield 158	android/support/v7/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
      //   387: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   390: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   393: aload 4
      //   395: invokestatic 143	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   398: pop
      //   399: aload_0
      //   400: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   403: iconst_1
      //   404: putfield 150	android/support/v7/widget/ActivityChooserModel:mCanReadHistoricalData	Z
      //   407: aload_1
      //   408: ifnull -165 -> 243
      //   411: aload_1
      //   412: invokevirtual 155	java/io/FileOutputStream:close	()V
      //   415: goto -172 -> 243
      //   418: astore_1
      //   419: goto -176 -> 243
      //   422: astore 4
      //   424: aload_0
      //   425: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   428: iconst_1
      //   429: putfield 150	android/support/v7/widget/ActivityChooserModel:mCanReadHistoricalData	Z
      //   432: aload_1
      //   433: ifnull +7 -> 440
      //   436: aload_1
      //   437: invokevirtual 155	java/io/FileOutputStream:close	()V
      //   440: aload 4
      //   442: athrow
      //   443: astore_1
      //   444: goto -201 -> 243
      //   447: astore_1
      //   448: goto -8 -> 440
      //   451: iconst_0
      //   452: istore_2
      //   453: iload_2
      //   454: iload_3
      //   455: if_icmpge -246 -> 209
      //   458: goto -379 -> 79
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	461	0	this	PersistHistoryAsyncTask
      //   0	461	1	paramVarArgs	Object[]
      //   171	285	2	i	int
      //   75	381	3	j	int
      //   6	74	4	localList	List
      //   245	31	4	localIllegalArgumentException	IllegalArgumentException
      //   304	31	4	localIllegalStateException	IllegalStateException
      //   363	31	4	localIOException	IOException
      //   422	19	4	localObject1	Object
      //   14	207	5	localObject2	Object
      //   90	57	6	localHistoricalRecord	ActivityChooserModel.HistoricalRecord
      // Exception table:
      //   from	to	target	type
      //   16	30	178	java/io/FileNotFoundException
      //   35	76	245	java/lang/IllegalArgumentException
      //   79	171	245	java/lang/IllegalArgumentException
      //   209	227	245	java/lang/IllegalArgumentException
      //   293	297	300	java/io/IOException
      //   35	76	304	java/lang/IllegalStateException
      //   79	171	304	java/lang/IllegalStateException
      //   209	227	304	java/lang/IllegalStateException
      //   352	356	359	java/io/IOException
      //   35	76	363	java/io/IOException
      //   79	171	363	java/io/IOException
      //   209	227	363	java/io/IOException
      //   411	415	418	java/io/IOException
      //   35	76	422	finally
      //   79	171	422	finally
      //   209	227	422	finally
      //   247	281	422	finally
      //   306	340	422	finally
      //   365	399	422	finally
      //   239	243	443	java/io/IOException
      //   436	440	447	java/io/IOException
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\support\v7\widget\ActivityChooserModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */