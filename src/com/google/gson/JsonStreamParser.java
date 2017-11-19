package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import java.io.EOFException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class JsonStreamParser
  implements Iterator<JsonElement>
{
  private final Object lock;
  private final JsonReader parser;
  
  public JsonStreamParser(Reader paramReader)
  {
    this.parser = new JsonReader(paramReader);
    this.parser.setLenient(true);
    this.lock = new Object();
  }
  
  public JsonStreamParser(String paramString)
  {
    this(new StringReader(paramString));
  }
  
  /* Error */
  public boolean hasNext()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 28	com/google/gson/JsonStreamParser:lock	Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 22	com/google/gson/JsonStreamParser:parser	Lcom/google/gson/stream/JsonReader;
    //   11: invokevirtual 45	com/google/gson/stream/JsonReader:peek	()Lcom/google/gson/stream/JsonToken;
    //   14: astore_3
    //   15: getstatic 51	com/google/gson/stream/JsonToken:END_DOCUMENT	Lcom/google/gson/stream/JsonToken;
    //   18: astore 4
    //   20: aload_3
    //   21: aload 4
    //   23: if_acmpeq +33 -> 56
    //   26: iconst_1
    //   27: istore_1
    //   28: aload_2
    //   29: monitorexit
    //   30: iload_1
    //   31: ireturn
    //   32: new 53	com/google/gson/JsonSyntaxException
    //   35: dup
    //   36: aload_3
    //   37: invokespecial 56	com/google/gson/JsonSyntaxException:<init>	(Ljava/lang/Throwable;)V
    //   40: athrow
    //   41: astore_3
    //   42: aload_2
    //   43: monitorexit
    //   44: aload_3
    //   45: athrow
    //   46: astore_3
    //   47: new 58	com/google/gson/JsonIOException
    //   50: dup
    //   51: aload_3
    //   52: invokespecial 59	com/google/gson/JsonIOException:<init>	(Ljava/lang/Throwable;)V
    //   55: athrow
    //   56: iconst_0
    //   57: istore_1
    //   58: goto -30 -> 28
    //   61: astore_3
    //   62: goto -30 -> 32
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	65	0	this	JsonStreamParser
    //   27	31	1	bool	boolean
    //   14	23	3	localJsonToken1	com.google.gson.stream.JsonToken
    //   41	4	3	localObject2	Object
    //   46	6	3	localIOException	java.io.IOException
    //   61	1	3	localMalformedJsonException	com.google.gson.stream.MalformedJsonException
    //   18	4	4	localJsonToken2	com.google.gson.stream.JsonToken
    // Exception table:
    //   from	to	target	type
    //   7	20	41	finally
    //   28	30	41	finally
    //   32	41	41	finally
    //   42	44	41	finally
    //   47	56	41	finally
    //   7	20	46	java/io/IOException
    //   7	20	61	com/google/gson/stream/MalformedJsonException
  }
  
  public JsonElement next()
    throws JsonParseException
  {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    try
    {
      JsonElement localJsonElement = Streams.parse(this.parser);
      return localJsonElement;
    }
    catch (StackOverflowError localStackOverflowError)
    {
      throw new JsonParseException("Failed parsing JSON source to Json", localStackOverflowError);
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      throw new JsonParseException("Failed parsing JSON source to Json", localOutOfMemoryError);
    }
    catch (JsonParseException localJsonParseException)
    {
      Object localObject = localJsonParseException;
      if ((localJsonParseException.getCause() instanceof EOFException)) {
        localObject = new NoSuchElementException();
      }
      throw ((Throwable)localObject);
    }
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\gson\JsonStreamParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */