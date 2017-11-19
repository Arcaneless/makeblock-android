package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

final class GsonRequestBodyConverter<T>
  implements Converter<T, RequestBody>
{
  private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
  private static final Charset UTF_8 = Charset.forName("UTF-8");
  private final TypeAdapter<T> adapter;
  private final Gson gson;
  
  GsonRequestBodyConverter(Gson paramGson, TypeAdapter<T> paramTypeAdapter)
  {
    this.gson = paramGson;
    this.adapter = paramTypeAdapter;
  }
  
  public RequestBody convert(T paramT)
    throws IOException
  {
    Buffer localBuffer = new Buffer();
    Object localObject = new OutputStreamWriter(localBuffer.outputStream(), UTF_8);
    localObject = this.gson.newJsonWriter((Writer)localObject);
    this.adapter.write((JsonWriter)localObject, paramT);
    ((JsonWriter)localObject).close();
    return RequestBody.create(MEDIA_TYPE, localBuffer.readByteString());
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\retrofit2\converter\gson\GsonRequestBodyConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */