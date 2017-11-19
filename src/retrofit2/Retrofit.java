package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import okhttp3.Call.Factory;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public final class Retrofit
{
  private final List<CallAdapter.Factory> adapterFactories;
  private final HttpUrl baseUrl;
  private final Call.Factory callFactory;
  private final Executor callbackExecutor;
  private final List<Converter.Factory> converterFactories;
  private final Map<Method, ServiceMethod> serviceMethodCache = new LinkedHashMap();
  private final boolean validateEagerly;
  
  Retrofit(Call.Factory paramFactory, HttpUrl paramHttpUrl, List<Converter.Factory> paramList, List<CallAdapter.Factory> paramList1, Executor paramExecutor, boolean paramBoolean)
  {
    this.callFactory = paramFactory;
    this.baseUrl = paramHttpUrl;
    this.converterFactories = Collections.unmodifiableList(paramList);
    this.adapterFactories = Collections.unmodifiableList(paramList1);
    this.callbackExecutor = paramExecutor;
    this.validateEagerly = paramBoolean;
  }
  
  private void eagerlyValidateMethods(Class<?> paramClass)
  {
    Platform localPlatform = Platform.get();
    paramClass = paramClass.getDeclaredMethods();
    int j = paramClass.length;
    int i = 0;
    while (i < j)
    {
      Method localMethod = paramClass[i];
      if (!localPlatform.isDefaultMethod(localMethod)) {
        loadServiceMethod(localMethod);
      }
      i += 1;
    }
  }
  
  public HttpUrl baseUrl()
  {
    return this.baseUrl;
  }
  
  public CallAdapter<?> callAdapter(Type paramType, Annotation[] paramArrayOfAnnotation)
  {
    return nextCallAdapter(null, paramType, paramArrayOfAnnotation);
  }
  
  public List<CallAdapter.Factory> callAdapterFactories()
  {
    return this.adapterFactories;
  }
  
  public Call.Factory callFactory()
  {
    return this.callFactory;
  }
  
  public Executor callbackExecutor()
  {
    return this.callbackExecutor;
  }
  
  public List<Converter.Factory> converterFactories()
  {
    return this.converterFactories;
  }
  
  public <T> T create(final Class<T> paramClass)
  {
    Utils.validateServiceInterface(paramClass);
    if (this.validateEagerly) {
      eagerlyValidateMethods(paramClass);
    }
    ClassLoader localClassLoader = paramClass.getClassLoader();
    InvocationHandler local1 = new InvocationHandler()
    {
      private final Platform platform = Platform.get();
      
      public Object invoke(Object paramAnonymousObject, Method paramAnonymousMethod, Object... paramAnonymousVarArgs)
        throws Throwable
      {
        if (paramAnonymousMethod.getDeclaringClass() == Object.class) {
          return paramAnonymousMethod.invoke(this, paramAnonymousVarArgs);
        }
        if (this.platform.isDefaultMethod(paramAnonymousMethod)) {
          return this.platform.invokeDefaultMethod(paramAnonymousMethod, paramClass, paramAnonymousObject, paramAnonymousVarArgs);
        }
        paramAnonymousObject = Retrofit.this.loadServiceMethod(paramAnonymousMethod);
        paramAnonymousMethod = new OkHttpCall((ServiceMethod)paramAnonymousObject, paramAnonymousVarArgs);
        return ((ServiceMethod)paramAnonymousObject).callAdapter.adapt(paramAnonymousMethod);
      }
    };
    return (T)Proxy.newProxyInstance(localClassLoader, new Class[] { paramClass }, local1);
  }
  
  ServiceMethod loadServiceMethod(Method paramMethod)
  {
    for (;;)
    {
      ServiceMethod localServiceMethod2;
      synchronized (this.serviceMethodCache)
      {
        localServiceMethod2 = (ServiceMethod)this.serviceMethodCache.get(paramMethod);
        break label62;
        localServiceMethod1 = new ServiceMethod.Builder(this, paramMethod).build();
        this.serviceMethodCache.put(paramMethod, localServiceMethod1);
        return localServiceMethod1;
      }
      label62:
      ServiceMethod localServiceMethod1 = localServiceMethod2;
      if (localServiceMethod2 != null) {}
    }
  }
  
  public CallAdapter<?> nextCallAdapter(CallAdapter.Factory paramFactory, Type paramType, Annotation[] paramArrayOfAnnotation)
  {
    Utils.checkNotNull(paramType, "returnType == null");
    Utils.checkNotNull(paramArrayOfAnnotation, "annotations == null");
    int i = this.adapterFactories.indexOf(paramFactory) + 1;
    int j = i;
    int k = this.adapterFactories.size();
    while (j < k)
    {
      CallAdapter localCallAdapter = ((CallAdapter.Factory)this.adapterFactories.get(j)).get(paramType, paramArrayOfAnnotation, this);
      if (localCallAdapter != null) {
        return localCallAdapter;
      }
      j += 1;
    }
    paramType = new StringBuilder("Could not locate call adapter for ").append(paramType).append(".\n");
    if (paramFactory != null)
    {
      paramType.append("  Skipped:");
      j = 0;
      while (j < i)
      {
        paramType.append("\n   * ").append(((CallAdapter.Factory)this.adapterFactories.get(j)).getClass().getName());
        j += 1;
      }
      paramType.append('\n');
    }
    paramType.append("  Tried:");
    j = this.adapterFactories.size();
    while (i < j)
    {
      paramType.append("\n   * ").append(((CallAdapter.Factory)this.adapterFactories.get(i)).getClass().getName());
      i += 1;
    }
    throw new IllegalArgumentException(paramType.toString());
  }
  
  public <T> Converter<T, RequestBody> nextRequestBodyConverter(Converter.Factory paramFactory, Type paramType, Annotation[] paramArrayOfAnnotation1, Annotation[] paramArrayOfAnnotation2)
  {
    Utils.checkNotNull(paramType, "type == null");
    Utils.checkNotNull(paramArrayOfAnnotation1, "parameterAnnotations == null");
    Utils.checkNotNull(paramArrayOfAnnotation2, "methodAnnotations == null");
    int i = this.converterFactories.indexOf(paramFactory) + 1;
    int j = i;
    int k = this.converterFactories.size();
    while (j < k)
    {
      Converter localConverter = ((Converter.Factory)this.converterFactories.get(j)).requestBodyConverter(paramType, paramArrayOfAnnotation1, paramArrayOfAnnotation2, this);
      if (localConverter != null) {
        return localConverter;
      }
      j += 1;
    }
    paramType = new StringBuilder("Could not locate RequestBody converter for ").append(paramType).append(".\n");
    if (paramFactory != null)
    {
      paramType.append("  Skipped:");
      j = 0;
      while (j < i)
      {
        paramType.append("\n   * ").append(((Converter.Factory)this.converterFactories.get(j)).getClass().getName());
        j += 1;
      }
      paramType.append('\n');
    }
    paramType.append("  Tried:");
    j = this.converterFactories.size();
    while (i < j)
    {
      paramType.append("\n   * ").append(((Converter.Factory)this.converterFactories.get(i)).getClass().getName());
      i += 1;
    }
    throw new IllegalArgumentException(paramType.toString());
  }
  
  public <T> Converter<ResponseBody, T> nextResponseBodyConverter(Converter.Factory paramFactory, Type paramType, Annotation[] paramArrayOfAnnotation)
  {
    Utils.checkNotNull(paramType, "type == null");
    Utils.checkNotNull(paramArrayOfAnnotation, "annotations == null");
    int i = this.converterFactories.indexOf(paramFactory) + 1;
    int j = i;
    int k = this.converterFactories.size();
    while (j < k)
    {
      Converter localConverter = ((Converter.Factory)this.converterFactories.get(j)).responseBodyConverter(paramType, paramArrayOfAnnotation, this);
      if (localConverter != null) {
        return localConverter;
      }
      j += 1;
    }
    paramType = new StringBuilder("Could not locate ResponseBody converter for ").append(paramType).append(".\n");
    if (paramFactory != null)
    {
      paramType.append("  Skipped:");
      j = 0;
      while (j < i)
      {
        paramType.append("\n   * ").append(((Converter.Factory)this.converterFactories.get(j)).getClass().getName());
        j += 1;
      }
      paramType.append('\n');
    }
    paramType.append("  Tried:");
    j = this.converterFactories.size();
    while (i < j)
    {
      paramType.append("\n   * ").append(((Converter.Factory)this.converterFactories.get(i)).getClass().getName());
      i += 1;
    }
    throw new IllegalArgumentException(paramType.toString());
  }
  
  public <T> Converter<T, RequestBody> requestBodyConverter(Type paramType, Annotation[] paramArrayOfAnnotation1, Annotation[] paramArrayOfAnnotation2)
  {
    return nextRequestBodyConverter(null, paramType, paramArrayOfAnnotation1, paramArrayOfAnnotation2);
  }
  
  public <T> Converter<ResponseBody, T> responseBodyConverter(Type paramType, Annotation[] paramArrayOfAnnotation)
  {
    return nextResponseBodyConverter(null, paramType, paramArrayOfAnnotation);
  }
  
  public <T> Converter<T, String> stringConverter(Type paramType, Annotation[] paramArrayOfAnnotation)
  {
    Utils.checkNotNull(paramType, "type == null");
    Utils.checkNotNull(paramArrayOfAnnotation, "annotations == null");
    int i = 0;
    int j = this.converterFactories.size();
    while (i < j)
    {
      Converter localConverter = ((Converter.Factory)this.converterFactories.get(i)).stringConverter(paramType, paramArrayOfAnnotation, this);
      if (localConverter != null) {
        return localConverter;
      }
      i += 1;
    }
    return BuiltInConverters.ToStringConverter.INSTANCE;
  }
  
  public static final class Builder
  {
    private List<CallAdapter.Factory> adapterFactories = new ArrayList();
    private HttpUrl baseUrl;
    private Call.Factory callFactory;
    private Executor callbackExecutor;
    private List<Converter.Factory> converterFactories = new ArrayList();
    private Platform platform;
    private boolean validateEagerly;
    
    public Builder()
    {
      this(Platform.get());
    }
    
    Builder(Platform paramPlatform)
    {
      this.platform = paramPlatform;
      this.converterFactories.add(new BuiltInConverters());
    }
    
    public Builder addCallAdapterFactory(CallAdapter.Factory paramFactory)
    {
      this.adapterFactories.add(Utils.checkNotNull(paramFactory, "factory == null"));
      return this;
    }
    
    public Builder addConverterFactory(Converter.Factory paramFactory)
    {
      this.converterFactories.add(Utils.checkNotNull(paramFactory, "factory == null"));
      return this;
    }
    
    public Builder baseUrl(String paramString)
    {
      Utils.checkNotNull(paramString, "baseUrl == null");
      HttpUrl localHttpUrl = HttpUrl.parse(paramString);
      if (localHttpUrl == null) {
        throw new IllegalArgumentException("Illegal URL: " + paramString);
      }
      return baseUrl(localHttpUrl);
    }
    
    public Builder baseUrl(HttpUrl paramHttpUrl)
    {
      Utils.checkNotNull(paramHttpUrl, "baseUrl == null");
      List localList = paramHttpUrl.pathSegments();
      if (!"".equals(localList.get(localList.size() - 1))) {
        throw new IllegalArgumentException("baseUrl must end in /: " + paramHttpUrl);
      }
      this.baseUrl = paramHttpUrl;
      return this;
    }
    
    public Retrofit build()
    {
      if (this.baseUrl == null) {
        throw new IllegalStateException("Base URL required.");
      }
      Object localObject2 = this.callFactory;
      Object localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = new OkHttpClient();
      }
      Object localObject3 = this.callbackExecutor;
      localObject2 = localObject3;
      if (localObject3 == null) {
        localObject2 = this.platform.defaultCallbackExecutor();
      }
      localObject3 = new ArrayList(this.adapterFactories);
      ((List)localObject3).add(this.platform.defaultCallAdapterFactory((Executor)localObject2));
      ArrayList localArrayList = new ArrayList(this.converterFactories);
      return new Retrofit((Call.Factory)localObject1, this.baseUrl, localArrayList, (List)localObject3, (Executor)localObject2, this.validateEagerly);
    }
    
    public Builder callFactory(Call.Factory paramFactory)
    {
      this.callFactory = ((Call.Factory)Utils.checkNotNull(paramFactory, "factory == null"));
      return this;
    }
    
    public Builder callbackExecutor(Executor paramExecutor)
    {
      this.callbackExecutor = ((Executor)Utils.checkNotNull(paramExecutor, "executor == null"));
      return this;
    }
    
    public Builder client(OkHttpClient paramOkHttpClient)
    {
      return callFactory((Call.Factory)Utils.checkNotNull(paramOkHttpClient, "client == null"));
    }
    
    public Builder validateEagerly(boolean paramBoolean)
    {
      this.validateEagerly = paramBoolean;
      return this;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\retrofit2\Retrofit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */