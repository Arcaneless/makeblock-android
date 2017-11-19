package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract interface CallAdapter<T>
{
  public abstract <R> T adapt(Call<R> paramCall);
  
  public abstract Type responseType();
  
  public static abstract class Factory
  {
    protected static Type getParameterUpperBound(int paramInt, ParameterizedType paramParameterizedType)
    {
      return Utils.getParameterUpperBound(paramInt, paramParameterizedType);
    }
    
    protected static Class<?> getRawType(Type paramType)
    {
      return Utils.getRawType(paramType);
    }
    
    public abstract CallAdapter<?> get(Type paramType, Annotation[] paramArrayOfAnnotation, Retrofit paramRetrofit);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\retrofit2\CallAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */