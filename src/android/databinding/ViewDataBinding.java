package android.databinding;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import com.android.databinding.library.R.id;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public abstract class ViewDataBinding
  extends BaseObservable
{
  private static final int BINDING_NUMBER_START;
  public static final String BINDING_TAG_PREFIX = "binding_";
  private static final CreateWeakListener CREATE_LIST_LISTENER;
  private static final CreateWeakListener CREATE_MAP_LISTENER;
  private static final CreateWeakListener CREATE_PROPERTY_LISTENER;
  private static final int HALTED = 2;
  private static final int REBIND = 1;
  private static final CallbackRegistry.NotifierCallback<OnRebindCallback, ViewDataBinding, Void> REBIND_NOTIFIER;
  private static final int REBOUND = 3;
  private static final View.OnAttachStateChangeListener ROOT_REATTACHED_LISTENER;
  static int SDK_INT;
  private static final boolean USE_CHOREOGRAPHER;
  private static final boolean USE_TAG_ID;
  private static final ReferenceQueue<ViewDataBinding> sReferenceQueue;
  protected final DataBindingComponent mBindingComponent;
  private Choreographer mChoreographer;
  private ViewDataBinding mContainingBinding;
  private final Choreographer.FrameCallback mFrameCallback;
  private boolean mIsExecutingPendingBindings;
  private WeakListener[] mLocalFieldObservers;
  private boolean mPendingRebind = false;
  private CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> mRebindCallbacks;
  private boolean mRebindHalted = false;
  private final Runnable mRebindRunnable = new Runnable()
  {
    public void run()
    {
      try
      {
        ViewDataBinding.access$202(ViewDataBinding.this, false);
        ViewDataBinding.access$300();
        if ((Build.VERSION.SDK_INT >= 19) && (!ViewDataBinding.this.mRoot.isAttachedToWindow()))
        {
          ViewDataBinding.this.mRoot.removeOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
          ViewDataBinding.this.mRoot.addOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
          return;
        }
      }
      finally {}
      ViewDataBinding.this.executePendingBindings();
    }
  };
  private final View mRoot;
  private Handler mUIThreadHandler;
  
  static
  {
    boolean bool2 = true;
    SDK_INT = Build.VERSION.SDK_INT;
    BINDING_NUMBER_START = "binding_".length();
    if (DataBinderMapper.TARGET_MIN_SDK >= 14)
    {
      bool1 = true;
      USE_TAG_ID = bool1;
      if (SDK_INT < 16) {
        break label112;
      }
    }
    label112:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      USE_CHOREOGRAPHER = bool1;
      CREATE_PROPERTY_LISTENER = new CreateWeakListener()
      {
        public ViewDataBinding.WeakListener create(ViewDataBinding paramAnonymousViewDataBinding, int paramAnonymousInt)
        {
          return new ViewDataBinding.WeakPropertyListener(paramAnonymousViewDataBinding, paramAnonymousInt).getListener();
        }
      };
      CREATE_LIST_LISTENER = new CreateWeakListener()
      {
        public ViewDataBinding.WeakListener create(ViewDataBinding paramAnonymousViewDataBinding, int paramAnonymousInt)
        {
          return new ViewDataBinding.WeakListListener(paramAnonymousViewDataBinding, paramAnonymousInt).getListener();
        }
      };
      CREATE_MAP_LISTENER = new CreateWeakListener()
      {
        public ViewDataBinding.WeakListener create(ViewDataBinding paramAnonymousViewDataBinding, int paramAnonymousInt)
        {
          return new ViewDataBinding.WeakMapListener(paramAnonymousViewDataBinding, paramAnonymousInt).getListener();
        }
      };
      REBIND_NOTIFIER = new CallbackRegistry.NotifierCallback()
      {
        public void onNotifyCallback(OnRebindCallback paramAnonymousOnRebindCallback, ViewDataBinding paramAnonymousViewDataBinding, int paramAnonymousInt, Void paramAnonymousVoid)
        {
          switch (paramAnonymousInt)
          {
          default: 
          case 1: 
            do
            {
              return;
            } while (paramAnonymousOnRebindCallback.onPreBind(paramAnonymousViewDataBinding));
            ViewDataBinding.access$002(paramAnonymousViewDataBinding, true);
            return;
          case 2: 
            paramAnonymousOnRebindCallback.onCanceled(paramAnonymousViewDataBinding);
            return;
          }
          paramAnonymousOnRebindCallback.onBound(paramAnonymousViewDataBinding);
        }
      };
      sReferenceQueue = new ReferenceQueue();
      if (Build.VERSION.SDK_INT >= 19) {
        break label117;
      }
      ROOT_REATTACHED_LISTENER = null;
      return;
      bool1 = false;
      break;
    }
    label117:
    ROOT_REATTACHED_LISTENER = new View.OnAttachStateChangeListener()
    {
      @TargetApi(19)
      public void onViewAttachedToWindow(View paramAnonymousView)
      {
        ViewDataBinding.getBinding(paramAnonymousView).mRebindRunnable.run();
        paramAnonymousView.removeOnAttachStateChangeListener(this);
      }
      
      public void onViewDetachedFromWindow(View paramAnonymousView) {}
    };
  }
  
  protected ViewDataBinding(DataBindingComponent paramDataBindingComponent, View paramView, int paramInt)
  {
    this.mBindingComponent = paramDataBindingComponent;
    this.mLocalFieldObservers = new WeakListener[paramInt];
    this.mRoot = paramView;
    if (Looper.myLooper() == null) {
      throw new IllegalStateException("DataBinding must be created in view's UI Thread");
    }
    if (USE_CHOREOGRAPHER)
    {
      this.mChoreographer = Choreographer.getInstance();
      this.mFrameCallback = new Choreographer.FrameCallback()
      {
        public void doFrame(long paramAnonymousLong)
        {
          ViewDataBinding.this.mRebindRunnable.run();
        }
      };
      return;
    }
    this.mFrameCallback = null;
    this.mUIThreadHandler = new Handler(Looper.myLooper());
  }
  
  protected static ViewDataBinding bind(DataBindingComponent paramDataBindingComponent, View paramView, int paramInt)
  {
    return DataBindingUtil.bind(paramDataBindingComponent, paramView, paramInt);
  }
  
  private void executeBindingsInternal()
  {
    if (this.mIsExecutingPendingBindings) {
      requestRebind();
    }
    while (!hasPendingBindings()) {
      return;
    }
    this.mIsExecutingPendingBindings = true;
    this.mRebindHalted = false;
    if (this.mRebindCallbacks != null)
    {
      this.mRebindCallbacks.notifyCallbacks(this, 1, null);
      if (this.mRebindHalted) {
        this.mRebindCallbacks.notifyCallbacks(this, 2, null);
      }
    }
    if (!this.mRebindHalted)
    {
      executeBindings();
      if (this.mRebindCallbacks != null) {
        this.mRebindCallbacks.notifyCallbacks(this, 3, null);
      }
    }
    this.mIsExecutingPendingBindings = false;
  }
  
  protected static void executeBindingsOn(ViewDataBinding paramViewDataBinding)
  {
    paramViewDataBinding.executeBindingsInternal();
  }
  
  private static int findIncludeIndex(String paramString, int paramInt1, IncludedLayouts paramIncludedLayouts, int paramInt2)
  {
    paramString = paramString.subSequence(paramString.indexOf('/') + 1, paramString.length() - 2);
    paramIncludedLayouts = paramIncludedLayouts.layouts[paramInt2];
    paramInt2 = paramIncludedLayouts.length;
    while (paramInt1 < paramInt2)
    {
      if (TextUtils.equals(paramString, paramIncludedLayouts[paramInt1])) {
        return paramInt1;
      }
      paramInt1 += 1;
    }
    return -1;
  }
  
  private static int findLastMatching(ViewGroup paramViewGroup, int paramInt)
  {
    String str1 = (String)paramViewGroup.getChildAt(paramInt).getTag();
    String str2 = str1.substring(0, str1.length() - 1);
    int k = str2.length();
    int m = paramViewGroup.getChildCount();
    int i = paramInt;
    paramInt += 1;
    for (;;)
    {
      if (paramInt < m)
      {
        localObject = paramViewGroup.getChildAt(paramInt);
        if (!(((View)localObject).getTag() instanceof String)) {
          break label132;
        }
      }
      label132:
      for (Object localObject = (String)((View)localObject).getTag();; localObject = null)
      {
        j = i;
        if (localObject == null) {
          break label152;
        }
        j = i;
        if (!((String)localObject).startsWith(str2)) {
          break label152;
        }
        if ((((String)localObject).length() != str1.length()) || (((String)localObject).charAt(((String)localObject).length() - 1) != '0')) {
          break;
        }
        return i;
      }
      int j = i;
      if (isNumeric((String)localObject, k)) {
        j = paramInt;
      }
      label152:
      paramInt += 1;
      i = j;
    }
  }
  
  static ViewDataBinding getBinding(View paramView)
  {
    if (paramView != null)
    {
      if (USE_TAG_ID) {
        return (ViewDataBinding)paramView.getTag(R.id.dataBinding);
      }
      paramView = paramView.getTag();
      if ((paramView instanceof ViewDataBinding)) {
        return (ViewDataBinding)paramView;
      }
    }
    return null;
  }
  
  public static int getBuildSdkInt()
  {
    return SDK_INT;
  }
  
  protected static int getColorFromResource(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return paramView.getContext().getColor(paramInt);
    }
    return paramView.getResources().getColor(paramInt);
  }
  
  protected static ColorStateList getColorStateListFromResource(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return paramView.getContext().getColorStateList(paramInt);
    }
    return paramView.getResources().getColorStateList(paramInt);
  }
  
  protected static Drawable getDrawableFromResource(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramView.getContext().getDrawable(paramInt);
    }
    return paramView.getResources().getDrawable(paramInt);
  }
  
  protected static <K, T> T getFrom(Map<K, T> paramMap, K paramK)
  {
    if (paramMap == null) {
      return null;
    }
    return (T)paramMap.get(paramK);
  }
  
  protected static byte getFromArray(byte[] paramArrayOfByte, int paramInt)
  {
    if ((paramArrayOfByte == null) || (paramInt < 0) || (paramInt >= paramArrayOfByte.length)) {
      return 0;
    }
    return paramArrayOfByte[paramInt];
  }
  
  protected static char getFromArray(char[] paramArrayOfChar, int paramInt)
  {
    if ((paramArrayOfChar == null) || (paramInt < 0) || (paramInt >= paramArrayOfChar.length)) {
      return '\000';
    }
    return paramArrayOfChar[paramInt];
  }
  
  protected static double getFromArray(double[] paramArrayOfDouble, int paramInt)
  {
    if ((paramArrayOfDouble == null) || (paramInt < 0) || (paramInt >= paramArrayOfDouble.length)) {
      return 0.0D;
    }
    return paramArrayOfDouble[paramInt];
  }
  
  protected static float getFromArray(float[] paramArrayOfFloat, int paramInt)
  {
    if ((paramArrayOfFloat == null) || (paramInt < 0) || (paramInt >= paramArrayOfFloat.length)) {
      return 0.0F;
    }
    return paramArrayOfFloat[paramInt];
  }
  
  protected static int getFromArray(int[] paramArrayOfInt, int paramInt)
  {
    if ((paramArrayOfInt == null) || (paramInt < 0) || (paramInt >= paramArrayOfInt.length)) {
      return 0;
    }
    return paramArrayOfInt[paramInt];
  }
  
  protected static long getFromArray(long[] paramArrayOfLong, int paramInt)
  {
    if ((paramArrayOfLong == null) || (paramInt < 0) || (paramInt >= paramArrayOfLong.length)) {
      return 0L;
    }
    return paramArrayOfLong[paramInt];
  }
  
  protected static <T> T getFromArray(T[] paramArrayOfT, int paramInt)
  {
    if ((paramArrayOfT == null) || (paramInt < 0) || (paramInt >= paramArrayOfT.length)) {
      return null;
    }
    return paramArrayOfT[paramInt];
  }
  
  protected static short getFromArray(short[] paramArrayOfShort, int paramInt)
  {
    if ((paramArrayOfShort == null) || (paramInt < 0) || (paramInt >= paramArrayOfShort.length)) {
      return 0;
    }
    return paramArrayOfShort[paramInt];
  }
  
  protected static boolean getFromArray(boolean[] paramArrayOfBoolean, int paramInt)
  {
    if ((paramArrayOfBoolean == null) || (paramInt < 0) || (paramInt >= paramArrayOfBoolean.length)) {
      return false;
    }
    return paramArrayOfBoolean[paramInt];
  }
  
  protected static int getFromList(SparseIntArray paramSparseIntArray, int paramInt)
  {
    if ((paramSparseIntArray == null) || (paramInt < 0)) {
      return 0;
    }
    return paramSparseIntArray.get(paramInt);
  }
  
  @TargetApi(18)
  protected static long getFromList(SparseLongArray paramSparseLongArray, int paramInt)
  {
    if ((paramSparseLongArray == null) || (paramInt < 0)) {
      return 0L;
    }
    return paramSparseLongArray.get(paramInt);
  }
  
  protected static <T> T getFromList(android.support.v4.util.LongSparseArray<T> paramLongSparseArray, int paramInt)
  {
    if ((paramLongSparseArray == null) || (paramInt < 0)) {
      return null;
    }
    return (T)paramLongSparseArray.get(paramInt);
  }
  
  @TargetApi(16)
  protected static <T> T getFromList(android.util.LongSparseArray<T> paramLongSparseArray, int paramInt)
  {
    if ((paramLongSparseArray == null) || (paramInt < 0)) {
      return null;
    }
    return (T)paramLongSparseArray.get(paramInt);
  }
  
  protected static <T> T getFromList(SparseArray<T> paramSparseArray, int paramInt)
  {
    if ((paramSparseArray == null) || (paramInt < 0)) {
      return null;
    }
    return (T)paramSparseArray.get(paramInt);
  }
  
  protected static <T> T getFromList(List<T> paramList, int paramInt)
  {
    if ((paramList == null) || (paramInt < 0) || (paramInt >= paramList.size())) {
      return null;
    }
    return (T)paramList.get(paramInt);
  }
  
  protected static boolean getFromList(SparseBooleanArray paramSparseBooleanArray, int paramInt)
  {
    if ((paramSparseBooleanArray == null) || (paramInt < 0)) {
      return false;
    }
    return paramSparseBooleanArray.get(paramInt);
  }
  
  private void handleFieldChange(int paramInt1, Object paramObject, int paramInt2)
  {
    if (onFieldChange(paramInt1, paramObject, paramInt2)) {
      requestRebind();
    }
  }
  
  private static boolean isNumeric(String paramString, int paramInt)
  {
    int i = paramString.length();
    if (i == paramInt) {
      return false;
    }
    for (;;)
    {
      if (paramInt >= i) {
        break label35;
      }
      if (!Character.isDigit(paramString.charAt(paramInt))) {
        break;
      }
      paramInt += 1;
    }
    label35:
    return true;
  }
  
  private static void mapBindings(DataBindingComponent paramDataBindingComponent, View paramView, Object[] paramArrayOfObject, IncludedLayouts paramIncludedLayouts, SparseIntArray paramSparseIntArray, boolean paramBoolean)
  {
    if (getBinding(paramView) != null) {}
    Object localObject1;
    label29:
    int i;
    label109:
    label112:
    do
    {
      return;
      localObject1 = paramView.getTag();
      if (!(localObject1 instanceof String)) {
        break;
      }
      localObject1 = (String)localObject1;
      j = 0;
      if ((!paramBoolean) || (localObject1 == null) || (!((String)localObject1).startsWith("layout"))) {
        break label457;
      }
      i = ((String)localObject1).lastIndexOf('_');
      if ((i <= 0) || (!isNumeric((String)localObject1, i + 1))) {
        break label451;
      }
      i = parseTagInt((String)localObject1, i + 1);
      if (paramArrayOfObject[i] == null) {
        paramArrayOfObject[i] = paramView;
      }
      if (paramIncludedLayouts != null) {
        break label448;
      }
      i = -1;
      j = 1;
      if (j == 0)
      {
        j = paramView.getId();
        if ((j > 0) && (paramSparseIntArray != null))
        {
          j = paramSparseIntArray.get(j, -1);
          if ((j >= 0) && (paramArrayOfObject[j] == null)) {
            paramArrayOfObject[j] = paramView;
          }
        }
      }
    } while (!(paramView instanceof ViewGroup));
    paramView = (ViewGroup)paramView;
    int i3 = paramView.getChildCount();
    int n = 0;
    int j = 0;
    label184:
    int i2;
    int i1;
    int m;
    Object localObject2;
    int i4;
    if (j < i3)
    {
      localObject1 = paramView.getChildAt(j);
      i2 = 0;
      k = j;
      i1 = i2;
      m = n;
      if (i >= 0)
      {
        k = j;
        i1 = i2;
        m = n;
        if ((((View)localObject1).getTag() instanceof String))
        {
          localObject2 = (String)((View)localObject1).getTag();
          k = j;
          i1 = i2;
          m = n;
          if (((String)localObject2).endsWith("_0"))
          {
            k = j;
            i1 = i2;
            m = n;
            if (((String)localObject2).startsWith("layout"))
            {
              k = j;
              i1 = i2;
              m = n;
              if (((String)localObject2).indexOf('/') > 0)
              {
                i4 = findIncludeIndex((String)localObject2, n, paramIncludedLayouts, i);
                k = j;
                i1 = i2;
                m = n;
                if (i4 >= 0)
                {
                  i1 = 1;
                  m = i4 + 1;
                  n = paramIncludedLayouts.indexes[i][i4];
                  i2 = paramIncludedLayouts.layoutIds[i][i4];
                  k = findLastMatching(paramView, j);
                  if (k != j) {
                    break label516;
                  }
                  paramArrayOfObject[n] = DataBindingUtil.bind(paramDataBindingComponent, (View)localObject1, i2);
                }
              }
            }
          }
        }
      }
    }
    for (int k = j;; k = j + (i4 - 1))
    {
      if (i1 == 0) {
        mapBindings(paramDataBindingComponent, (View)localObject1, paramArrayOfObject, paramIncludedLayouts, paramSparseIntArray, false);
      }
      j = k + 1;
      n = m;
      break label184;
      break;
      localObject1 = null;
      break label29;
      label448:
      break label109;
      label451:
      i = -1;
      break label112;
      label457:
      if ((localObject1 != null) && (((String)localObject1).startsWith("binding_")))
      {
        i = parseTagInt((String)localObject1, BINDING_NUMBER_START);
        if (paramArrayOfObject[i] == null) {
          paramArrayOfObject[i] = paramView;
        }
        j = 1;
        if (paramIncludedLayouts == null) {
          i = -1;
        }
        for (;;)
        {
          break;
        }
      }
      i = -1;
      break label112;
      label516:
      i4 = k - j + 1;
      localObject2 = new View[i4];
      k = 0;
      while (k < i4)
      {
        localObject2[k] = paramView.getChildAt(j + k);
        k += 1;
      }
      paramArrayOfObject[n] = DataBindingUtil.bind(paramDataBindingComponent, (View[])localObject2, i2);
    }
  }
  
  protected static Object[] mapBindings(DataBindingComponent paramDataBindingComponent, View paramView, int paramInt, IncludedLayouts paramIncludedLayouts, SparseIntArray paramSparseIntArray)
  {
    Object[] arrayOfObject = new Object[paramInt];
    mapBindings(paramDataBindingComponent, paramView, arrayOfObject, paramIncludedLayouts, paramSparseIntArray, true);
    return arrayOfObject;
  }
  
  protected static Object[] mapBindings(DataBindingComponent paramDataBindingComponent, View[] paramArrayOfView, int paramInt, IncludedLayouts paramIncludedLayouts, SparseIntArray paramSparseIntArray)
  {
    Object[] arrayOfObject = new Object[paramInt];
    paramInt = 0;
    while (paramInt < paramArrayOfView.length)
    {
      mapBindings(paramDataBindingComponent, paramArrayOfView[paramInt], arrayOfObject, paramIncludedLayouts, paramSparseIntArray, true);
      paramInt += 1;
    }
    return arrayOfObject;
  }
  
  protected static byte parse(String paramString, byte paramByte)
  {
    try
    {
      byte b = Byte.parseByte(paramString);
      return b;
    }
    catch (NumberFormatException paramString) {}
    return paramByte;
  }
  
  protected static char parse(String paramString, char paramChar)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return paramChar;
    }
    return paramString.charAt(0);
  }
  
  protected static double parse(String paramString, double paramDouble)
  {
    try
    {
      double d = Double.parseDouble(paramString);
      return d;
    }
    catch (NumberFormatException paramString) {}
    return paramDouble;
  }
  
  protected static float parse(String paramString, float paramFloat)
  {
    try
    {
      float f = Float.parseFloat(paramString);
      return f;
    }
    catch (NumberFormatException paramString) {}
    return paramFloat;
  }
  
  protected static int parse(String paramString, int paramInt)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (NumberFormatException paramString) {}
    return paramInt;
  }
  
  protected static long parse(String paramString, long paramLong)
  {
    try
    {
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (NumberFormatException paramString) {}
    return paramLong;
  }
  
  protected static short parse(String paramString, short paramShort)
  {
    try
    {
      short s = Short.parseShort(paramString);
      return s;
    }
    catch (NumberFormatException paramString) {}
    return paramShort;
  }
  
  protected static boolean parse(String paramString, boolean paramBoolean)
  {
    if (paramString == null) {
      return paramBoolean;
    }
    return Boolean.parseBoolean(paramString);
  }
  
  private static int parseTagInt(String paramString, int paramInt)
  {
    int j = paramString.length();
    int i = 0;
    while (paramInt < j)
    {
      i = i * 10 + (paramString.charAt(paramInt) - '0');
      paramInt += 1;
    }
    return i;
  }
  
  private static void processReferenceQueue()
  {
    for (;;)
    {
      Reference localReference = sReferenceQueue.poll();
      if (localReference == null) {
        break;
      }
      if ((localReference instanceof WeakListener)) {
        ((WeakListener)localReference).unregister();
      }
    }
  }
  
  protected static byte safeUnbox(Byte paramByte)
  {
    if (paramByte == null) {
      return 0;
    }
    return paramByte.byteValue();
  }
  
  protected static char safeUnbox(Character paramCharacter)
  {
    if (paramCharacter == null) {
      return '\000';
    }
    return paramCharacter.charValue();
  }
  
  protected static double safeUnbox(Double paramDouble)
  {
    if (paramDouble == null) {
      return 0.0D;
    }
    return paramDouble.doubleValue();
  }
  
  protected static float safeUnbox(Float paramFloat)
  {
    if (paramFloat == null) {
      return 0.0F;
    }
    return paramFloat.floatValue();
  }
  
  protected static int safeUnbox(Integer paramInteger)
  {
    if (paramInteger == null) {
      return 0;
    }
    return paramInteger.intValue();
  }
  
  protected static long safeUnbox(Long paramLong)
  {
    if (paramLong == null) {
      return 0L;
    }
    return paramLong.longValue();
  }
  
  protected static short safeUnbox(Short paramShort)
  {
    if (paramShort == null) {
      return 0;
    }
    return paramShort.shortValue();
  }
  
  protected static boolean safeUnbox(Boolean paramBoolean)
  {
    if (paramBoolean == null) {
      return false;
    }
    return paramBoolean.booleanValue();
  }
  
  protected static void setBindingInverseListener(ViewDataBinding paramViewDataBinding, InverseBindingListener paramInverseBindingListener, PropertyChangedInverseListener paramPropertyChangedInverseListener)
  {
    if (paramInverseBindingListener != paramPropertyChangedInverseListener)
    {
      if (paramInverseBindingListener != null) {
        paramViewDataBinding.removeOnPropertyChangedCallback((PropertyChangedInverseListener)paramInverseBindingListener);
      }
      if (paramPropertyChangedInverseListener != null) {
        paramViewDataBinding.addOnPropertyChangedCallback(paramPropertyChangedInverseListener);
      }
    }
  }
  
  protected static <T> void setTo(android.support.v4.util.LongSparseArray<T> paramLongSparseArray, int paramInt, T paramT)
  {
    if ((paramLongSparseArray == null) || (paramInt < 0) || (paramInt >= paramLongSparseArray.size())) {
      return;
    }
    paramLongSparseArray.put(paramInt, paramT);
  }
  
  @TargetApi(16)
  protected static <T> void setTo(android.util.LongSparseArray<T> paramLongSparseArray, int paramInt, T paramT)
  {
    if ((paramLongSparseArray == null) || (paramInt < 0) || (paramInt >= paramLongSparseArray.size())) {
      return;
    }
    paramLongSparseArray.put(paramInt, paramT);
  }
  
  protected static <T> void setTo(SparseArray<T> paramSparseArray, int paramInt, T paramT)
  {
    if ((paramSparseArray == null) || (paramInt < 0) || (paramInt >= paramSparseArray.size())) {
      return;
    }
    paramSparseArray.put(paramInt, paramT);
  }
  
  protected static void setTo(SparseBooleanArray paramSparseBooleanArray, int paramInt, boolean paramBoolean)
  {
    if ((paramSparseBooleanArray == null) || (paramInt < 0) || (paramInt >= paramSparseBooleanArray.size())) {
      return;
    }
    paramSparseBooleanArray.put(paramInt, paramBoolean);
  }
  
  protected static void setTo(SparseIntArray paramSparseIntArray, int paramInt1, int paramInt2)
  {
    if ((paramSparseIntArray == null) || (paramInt1 < 0) || (paramInt1 >= paramSparseIntArray.size())) {
      return;
    }
    paramSparseIntArray.put(paramInt1, paramInt2);
  }
  
  @TargetApi(18)
  protected static void setTo(SparseLongArray paramSparseLongArray, int paramInt, long paramLong)
  {
    if ((paramSparseLongArray == null) || (paramInt < 0) || (paramInt >= paramSparseLongArray.size())) {
      return;
    }
    paramSparseLongArray.put(paramInt, paramLong);
  }
  
  protected static <T> void setTo(List<T> paramList, int paramInt, T paramT)
  {
    if ((paramList == null) || (paramInt < 0) || (paramInt >= paramList.size())) {
      return;
    }
    paramList.set(paramInt, paramT);
  }
  
  protected static <K, T> void setTo(Map<K, T> paramMap, K paramK, T paramT)
  {
    if (paramMap == null) {
      return;
    }
    paramMap.put(paramK, paramT);
  }
  
  protected static void setTo(byte[] paramArrayOfByte, int paramInt, byte paramByte)
  {
    if ((paramArrayOfByte == null) || (paramInt < 0) || (paramInt >= paramArrayOfByte.length)) {
      return;
    }
    paramArrayOfByte[paramInt] = paramByte;
  }
  
  protected static void setTo(char[] paramArrayOfChar, int paramInt, char paramChar)
  {
    if ((paramArrayOfChar == null) || (paramInt < 0) || (paramInt >= paramArrayOfChar.length)) {
      return;
    }
    paramArrayOfChar[paramInt] = paramChar;
  }
  
  protected static void setTo(double[] paramArrayOfDouble, int paramInt, double paramDouble)
  {
    if ((paramArrayOfDouble == null) || (paramInt < 0) || (paramInt >= paramArrayOfDouble.length)) {
      return;
    }
    paramArrayOfDouble[paramInt] = paramDouble;
  }
  
  protected static void setTo(float[] paramArrayOfFloat, int paramInt, float paramFloat)
  {
    if ((paramArrayOfFloat == null) || (paramInt < 0) || (paramInt >= paramArrayOfFloat.length)) {
      return;
    }
    paramArrayOfFloat[paramInt] = paramFloat;
  }
  
  protected static void setTo(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    if ((paramArrayOfInt == null) || (paramInt1 < 0) || (paramInt1 >= paramArrayOfInt.length)) {
      return;
    }
    paramArrayOfInt[paramInt1] = paramInt2;
  }
  
  protected static void setTo(long[] paramArrayOfLong, int paramInt, long paramLong)
  {
    if ((paramArrayOfLong == null) || (paramInt < 0) || (paramInt >= paramArrayOfLong.length)) {
      return;
    }
    paramArrayOfLong[paramInt] = paramLong;
  }
  
  protected static <T> void setTo(T[] paramArrayOfT, int paramInt, T paramT)
  {
    if ((paramArrayOfT == null) || (paramInt < 0) || (paramInt >= paramArrayOfT.length)) {
      return;
    }
    paramArrayOfT[paramInt] = paramT;
  }
  
  protected static void setTo(short[] paramArrayOfShort, int paramInt, short paramShort)
  {
    if ((paramArrayOfShort == null) || (paramInt < 0) || (paramInt >= paramArrayOfShort.length)) {
      return;
    }
    paramArrayOfShort[paramInt] = paramShort;
  }
  
  protected static void setTo(boolean[] paramArrayOfBoolean, int paramInt, boolean paramBoolean)
  {
    if ((paramArrayOfBoolean == null) || (paramInt < 0) || (paramInt >= paramArrayOfBoolean.length)) {
      return;
    }
    paramArrayOfBoolean[paramInt] = paramBoolean;
  }
  
  private boolean updateRegistration(int paramInt, Object paramObject, CreateWeakListener paramCreateWeakListener)
  {
    if (paramObject == null) {
      return unregisterFrom(paramInt);
    }
    WeakListener localWeakListener = this.mLocalFieldObservers[paramInt];
    if (localWeakListener == null)
    {
      registerTo(paramInt, paramObject, paramCreateWeakListener);
      return true;
    }
    if (localWeakListener.getTarget() == paramObject) {
      return false;
    }
    unregisterFrom(paramInt);
    registerTo(paramInt, paramObject, paramCreateWeakListener);
    return true;
  }
  
  public void addOnRebindCallback(OnRebindCallback paramOnRebindCallback)
  {
    if (this.mRebindCallbacks == null) {
      this.mRebindCallbacks = new CallbackRegistry(REBIND_NOTIFIER);
    }
    this.mRebindCallbacks.add(paramOnRebindCallback);
  }
  
  protected void ensureBindingComponentIsNotNull(Class<?> paramClass)
  {
    if (this.mBindingComponent == null) {
      throw new IllegalStateException("Required DataBindingComponent is null in class " + getClass().getSimpleName() + ". A BindingAdapter in " + paramClass.getCanonicalName() + " is not static and requires an object to use, retrieved from the " + "DataBindingComponent. If you don't use an inflation method taking a " + "DataBindingComponent, use DataBindingUtil.setDefaultComponent or " + "make all BindingAdapter methods static.");
    }
  }
  
  protected abstract void executeBindings();
  
  public void executePendingBindings()
  {
    if (this.mContainingBinding == null)
    {
      executeBindingsInternal();
      return;
    }
    this.mContainingBinding.executePendingBindings();
  }
  
  void forceExecuteBindings()
  {
    executeBindings();
  }
  
  protected Object getObservedField(int paramInt)
  {
    WeakListener localWeakListener = this.mLocalFieldObservers[paramInt];
    if (localWeakListener == null) {
      return null;
    }
    return localWeakListener.getTarget();
  }
  
  public View getRoot()
  {
    return this.mRoot;
  }
  
  public abstract boolean hasPendingBindings();
  
  public abstract void invalidateAll();
  
  protected abstract boolean onFieldChange(int paramInt1, Object paramObject, int paramInt2);
  
  protected void registerTo(int paramInt, Object paramObject, CreateWeakListener paramCreateWeakListener)
  {
    if (paramObject == null) {
      return;
    }
    WeakListener localWeakListener2 = this.mLocalFieldObservers[paramInt];
    WeakListener localWeakListener1 = localWeakListener2;
    if (localWeakListener2 == null)
    {
      localWeakListener1 = paramCreateWeakListener.create(this, paramInt);
      this.mLocalFieldObservers[paramInt] = localWeakListener1;
    }
    localWeakListener1.setTarget(paramObject);
  }
  
  public void removeOnRebindCallback(OnRebindCallback paramOnRebindCallback)
  {
    if (this.mRebindCallbacks != null) {
      this.mRebindCallbacks.remove(paramOnRebindCallback);
    }
  }
  
  protected void requestRebind()
  {
    if (this.mContainingBinding != null)
    {
      this.mContainingBinding.requestRebind();
      return;
    }
    try
    {
      if (this.mPendingRebind) {
        return;
      }
    }
    finally {}
    this.mPendingRebind = true;
    if (USE_CHOREOGRAPHER)
    {
      this.mChoreographer.postFrameCallback(this.mFrameCallback);
      return;
    }
    this.mUIThreadHandler.post(this.mRebindRunnable);
  }
  
  protected void setContainedBinding(ViewDataBinding paramViewDataBinding)
  {
    if (paramViewDataBinding != null) {
      paramViewDataBinding.mContainingBinding = this;
    }
  }
  
  protected void setRootTag(View paramView)
  {
    if (USE_TAG_ID)
    {
      paramView.setTag(R.id.dataBinding, this);
      return;
    }
    paramView.setTag(this);
  }
  
  protected void setRootTag(View[] paramArrayOfView)
  {
    int j = 0;
    int i = 0;
    if (USE_TAG_ID)
    {
      j = paramArrayOfView.length;
      while (i < j)
      {
        paramArrayOfView[i].setTag(R.id.dataBinding, this);
        i += 1;
      }
    }
    int k = paramArrayOfView.length;
    i = j;
    while (i < k)
    {
      paramArrayOfView[i].setTag(this);
      i += 1;
    }
  }
  
  public abstract boolean setVariable(int paramInt, Object paramObject);
  
  public void unbind()
  {
    WeakListener[] arrayOfWeakListener = this.mLocalFieldObservers;
    int j = arrayOfWeakListener.length;
    int i = 0;
    while (i < j)
    {
      WeakListener localWeakListener = arrayOfWeakListener[i];
      if (localWeakListener != null) {
        localWeakListener.unregister();
      }
      i += 1;
    }
  }
  
  protected boolean unregisterFrom(int paramInt)
  {
    WeakListener localWeakListener = this.mLocalFieldObservers[paramInt];
    if (localWeakListener != null) {
      return localWeakListener.unregister();
    }
    return false;
  }
  
  protected boolean updateRegistration(int paramInt, Observable paramObservable)
  {
    return updateRegistration(paramInt, paramObservable, CREATE_PROPERTY_LISTENER);
  }
  
  protected boolean updateRegistration(int paramInt, ObservableList paramObservableList)
  {
    return updateRegistration(paramInt, paramObservableList, CREATE_LIST_LISTENER);
  }
  
  protected boolean updateRegistration(int paramInt, ObservableMap paramObservableMap)
  {
    return updateRegistration(paramInt, paramObservableMap, CREATE_MAP_LISTENER);
  }
  
  private static abstract interface CreateWeakListener
  {
    public abstract ViewDataBinding.WeakListener create(ViewDataBinding paramViewDataBinding, int paramInt);
  }
  
  protected static class IncludedLayouts
  {
    public final int[][] indexes;
    public final int[][] layoutIds;
    public final String[][] layouts;
    
    public IncludedLayouts(int paramInt)
    {
      this.layouts = new String[paramInt][];
      this.indexes = new int[paramInt][];
      this.layoutIds = new int[paramInt][];
    }
    
    public void setIncludes(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
    {
      this.layouts[paramInt] = paramArrayOfString;
      this.indexes[paramInt] = paramArrayOfInt1;
      this.layoutIds[paramInt] = paramArrayOfInt2;
    }
  }
  
  private static abstract interface ObservableReference<T>
  {
    public abstract void addListener(T paramT);
    
    public abstract ViewDataBinding.WeakListener<T> getListener();
    
    public abstract void removeListener(T paramT);
  }
  
  protected static abstract class PropertyChangedInverseListener
    extends Observable.OnPropertyChangedCallback
    implements InverseBindingListener
  {
    final int mPropertyId;
    
    public PropertyChangedInverseListener(int paramInt)
    {
      this.mPropertyId = paramInt;
    }
    
    public void onPropertyChanged(Observable paramObservable, int paramInt)
    {
      if ((paramInt == this.mPropertyId) || (paramInt == 0)) {
        onChange();
      }
    }
  }
  
  private static class WeakListListener
    extends ObservableList.OnListChangedCallback
    implements ViewDataBinding.ObservableReference<ObservableList>
  {
    final ViewDataBinding.WeakListener<ObservableList> mListener;
    
    public WeakListListener(ViewDataBinding paramViewDataBinding, int paramInt)
    {
      this.mListener = new ViewDataBinding.WeakListener(paramViewDataBinding, paramInt, this);
    }
    
    public void addListener(ObservableList paramObservableList)
    {
      paramObservableList.addOnListChangedCallback(this);
    }
    
    public ViewDataBinding.WeakListener<ObservableList> getListener()
    {
      return this.mListener;
    }
    
    public void onChanged(ObservableList paramObservableList)
    {
      ViewDataBinding localViewDataBinding = this.mListener.getBinder();
      if (localViewDataBinding == null) {}
      ObservableList localObservableList;
      do
      {
        return;
        localObservableList = (ObservableList)this.mListener.getTarget();
      } while (localObservableList != paramObservableList);
      localViewDataBinding.handleFieldChange(this.mListener.mLocalFieldId, localObservableList, 0);
    }
    
    public void onItemRangeChanged(ObservableList paramObservableList, int paramInt1, int paramInt2)
    {
      onChanged(paramObservableList);
    }
    
    public void onItemRangeInserted(ObservableList paramObservableList, int paramInt1, int paramInt2)
    {
      onChanged(paramObservableList);
    }
    
    public void onItemRangeMoved(ObservableList paramObservableList, int paramInt1, int paramInt2, int paramInt3)
    {
      onChanged(paramObservableList);
    }
    
    public void onItemRangeRemoved(ObservableList paramObservableList, int paramInt1, int paramInt2)
    {
      onChanged(paramObservableList);
    }
    
    public void removeListener(ObservableList paramObservableList)
    {
      paramObservableList.removeOnListChangedCallback(this);
    }
  }
  
  private static class WeakListener<T>
    extends WeakReference<ViewDataBinding>
  {
    protected final int mLocalFieldId;
    private final ViewDataBinding.ObservableReference<T> mObservable;
    private T mTarget;
    
    public WeakListener(ViewDataBinding paramViewDataBinding, int paramInt, ViewDataBinding.ObservableReference<T> paramObservableReference)
    {
      super(ViewDataBinding.sReferenceQueue);
      this.mLocalFieldId = paramInt;
      this.mObservable = paramObservableReference;
    }
    
    protected ViewDataBinding getBinder()
    {
      ViewDataBinding localViewDataBinding = (ViewDataBinding)get();
      if (localViewDataBinding == null) {
        unregister();
      }
      return localViewDataBinding;
    }
    
    public T getTarget()
    {
      return (T)this.mTarget;
    }
    
    public void setTarget(T paramT)
    {
      unregister();
      this.mTarget = paramT;
      if (this.mTarget != null) {
        this.mObservable.addListener(this.mTarget);
      }
    }
    
    public boolean unregister()
    {
      boolean bool = false;
      if (this.mTarget != null)
      {
        this.mObservable.removeListener(this.mTarget);
        bool = true;
      }
      this.mTarget = null;
      return bool;
    }
  }
  
  private static class WeakMapListener
    extends ObservableMap.OnMapChangedCallback
    implements ViewDataBinding.ObservableReference<ObservableMap>
  {
    final ViewDataBinding.WeakListener<ObservableMap> mListener;
    
    public WeakMapListener(ViewDataBinding paramViewDataBinding, int paramInt)
    {
      this.mListener = new ViewDataBinding.WeakListener(paramViewDataBinding, paramInt, this);
    }
    
    public void addListener(ObservableMap paramObservableMap)
    {
      paramObservableMap.addOnMapChangedCallback(this);
    }
    
    public ViewDataBinding.WeakListener<ObservableMap> getListener()
    {
      return this.mListener;
    }
    
    public void onMapChanged(ObservableMap paramObservableMap, Object paramObject)
    {
      paramObject = this.mListener.getBinder();
      if ((paramObject == null) || (paramObservableMap != this.mListener.getTarget())) {
        return;
      }
      ((ViewDataBinding)paramObject).handleFieldChange(this.mListener.mLocalFieldId, paramObservableMap, 0);
    }
    
    public void removeListener(ObservableMap paramObservableMap)
    {
      paramObservableMap.removeOnMapChangedCallback(this);
    }
  }
  
  private static class WeakPropertyListener
    extends Observable.OnPropertyChangedCallback
    implements ViewDataBinding.ObservableReference<Observable>
  {
    final ViewDataBinding.WeakListener<Observable> mListener;
    
    public WeakPropertyListener(ViewDataBinding paramViewDataBinding, int paramInt)
    {
      this.mListener = new ViewDataBinding.WeakListener(paramViewDataBinding, paramInt, this);
    }
    
    public void addListener(Observable paramObservable)
    {
      paramObservable.addOnPropertyChangedCallback(this);
    }
    
    public ViewDataBinding.WeakListener<Observable> getListener()
    {
      return this.mListener;
    }
    
    public void onPropertyChanged(Observable paramObservable, int paramInt)
    {
      ViewDataBinding localViewDataBinding = this.mListener.getBinder();
      if (localViewDataBinding == null) {}
      while ((Observable)this.mListener.getTarget() != paramObservable) {
        return;
      }
      localViewDataBinding.handleFieldChange(this.mListener.mLocalFieldId, paramObservable, paramInt);
    }
    
    public void removeListener(Observable paramObservable)
    {
      paramObservable.removeOnPropertyChangedCallback(this);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\ViewDataBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */