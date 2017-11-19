package com.bumptech.glide.disklrucache;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;

final class Util
{
  static final Charset US_ASCII = Charset.forName("US-ASCII");
  static final Charset UTF_8 = Charset.forName("UTF-8");
  
  static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (RuntimeException paramCloseable)
    {
      throw paramCloseable;
    }
    catch (Exception paramCloseable) {}
  }
  
  static void deleteContents(File paramFile)
    throws IOException
  {
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null) {
      throw new IOException("not a readable directory: " + paramFile);
    }
    int j = arrayOfFile.length;
    int i = 0;
    while (i < j)
    {
      paramFile = arrayOfFile[i];
      if (paramFile.isDirectory()) {
        deleteContents(paramFile);
      }
      if (!paramFile.delete()) {
        throw new IOException("failed to delete file: " + paramFile);
      }
      i += 1;
    }
  }
  
  static String readFully(Reader paramReader)
    throws IOException
  {
    for (;;)
    {
      int i;
      try
      {
        localStringWriter = new StringWriter();
        arrayOfChar = new char['Ѐ'];
        i = paramReader.read(arrayOfChar);
      }
      finally
      {
        StringWriter localStringWriter;
        char[] arrayOfChar;
        paramReader.close();
      }
      localStringWriter.write(arrayOfChar, 0, i);
      continue;
      while (i == -1)
      {
        String str = ((StringWriter)localObject).toString();
        paramReader.close();
        return str;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\disklrucache\Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */