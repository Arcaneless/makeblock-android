package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;

public final class JsonAdapterAnnotationTypeAdapterFactory
  implements TypeAdapterFactory
{
  private final ConstructorConstructor constructorConstructor;
  
  public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor paramConstructorConstructor)
  {
    this.constructorConstructor = paramConstructorConstructor;
  }
  
  public <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken)
  {
    JsonAdapter localJsonAdapter = (JsonAdapter)paramTypeToken.getRawType().getAnnotation(JsonAdapter.class);
    if (localJsonAdapter == null) {
      return null;
    }
    return getTypeAdapter(this.constructorConstructor, paramGson, paramTypeToken, localJsonAdapter);
  }
  
  TypeAdapter<?> getTypeAdapter(ConstructorConstructor paramConstructorConstructor, Gson paramGson, TypeToken<?> paramTypeToken, JsonAdapter paramJsonAdapter)
  {
    paramJsonAdapter = paramConstructorConstructor.get(TypeToken.get(paramJsonAdapter.value())).construct();
    if ((paramJsonAdapter instanceof TypeAdapter)) {}
    for (paramConstructorConstructor = (TypeAdapter)paramJsonAdapter;; paramConstructorConstructor = ((TypeAdapterFactory)paramJsonAdapter).create(paramGson, paramTypeToken))
    {
      paramGson = paramConstructorConstructor;
      if (paramConstructorConstructor != null) {
        paramGson = paramConstructorConstructor.nullSafe();
      }
      return paramGson;
      if (!(paramJsonAdapter instanceof TypeAdapterFactory)) {
        break;
      }
    }
    if (((paramJsonAdapter instanceof JsonSerializer)) || ((paramJsonAdapter instanceof JsonDeserializer)))
    {
      if ((paramJsonAdapter instanceof JsonSerializer))
      {
        paramConstructorConstructor = (JsonSerializer)paramJsonAdapter;
        label102:
        if (!(paramJsonAdapter instanceof JsonDeserializer)) {
          break label139;
        }
      }
      label139:
      for (paramJsonAdapter = (JsonDeserializer)paramJsonAdapter;; paramJsonAdapter = null)
      {
        paramConstructorConstructor = new TreeTypeAdapter(paramConstructorConstructor, paramJsonAdapter, paramGson, paramTypeToken, null);
        break;
        paramConstructorConstructor = null;
        break label102;
      }
    }
    throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer reference.");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\gson\internal\bind\JsonAdapterAnnotationTypeAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */