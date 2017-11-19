package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class GlideException
  extends Exception
{
  private static final StackTraceElement[] EMPTY_ELEMENTS = new StackTraceElement[0];
  private final List<Exception> causes;
  private Class<?> dataClass;
  private DataSource dataSource;
  private Key key;
  
  public GlideException(String paramString)
  {
    this(paramString, Collections.emptyList());
  }
  
  public GlideException(String paramString, Exception paramException)
  {
    this(paramString, Collections.singletonList(paramException));
  }
  
  public GlideException(String paramString, List<Exception> paramList)
  {
    super(paramString);
    setStackTrace(EMPTY_ELEMENTS);
    this.causes = paramList;
  }
  
  private void addRootCauses(Exception paramException, List<Exception> paramList)
  {
    if ((paramException instanceof GlideException))
    {
      paramException = ((GlideException)paramException).getCauses().iterator();
      while (paramException.hasNext()) {
        addRootCauses((Exception)paramException.next(), paramList);
      }
    }
    paramList.add(paramException);
  }
  
  private static void appendCauses(List<Exception> paramList, Appendable paramAppendable)
  {
    try
    {
      appendCausesWrapped(paramList, paramAppendable);
      return;
    }
    catch (IOException paramList)
    {
      throw new RuntimeException(paramList);
    }
  }
  
  private static void appendCausesWrapped(List<Exception> paramList, Appendable paramAppendable)
    throws IOException
  {
    int j = paramList.size();
    int i = 0;
    if (i < j)
    {
      paramAppendable.append("Cause (").append(String.valueOf(i + 1)).append(" of ").append(String.valueOf(j)).append("): ");
      Exception localException = (Exception)paramList.get(i);
      if ((localException instanceof GlideException)) {
        ((GlideException)localException).printStackTrace(paramAppendable);
      }
      for (;;)
      {
        i += 1;
        break;
        appendExceptionMessage(localException, paramAppendable);
      }
    }
  }
  
  private static void appendExceptionMessage(Exception paramException, Appendable paramAppendable)
  {
    try
    {
      paramAppendable.append(paramException.getClass().toString()).append(": ").append(paramException.getMessage()).append('\n');
      return;
    }
    catch (IOException paramAppendable)
    {
      throw new RuntimeException(paramException);
    }
  }
  
  private void printStackTrace(Appendable paramAppendable)
  {
    appendExceptionMessage(this, paramAppendable);
    appendCauses(getCauses(), new IndentedAppendable(paramAppendable));
  }
  
  public Throwable fillInStackTrace()
  {
    return this;
  }
  
  public List<Exception> getCauses()
  {
    return this.causes;
  }
  
  public String getMessage()
  {
    StringBuilder localStringBuilder = new StringBuilder().append(super.getMessage());
    if (this.dataClass != null)
    {
      str = ", " + this.dataClass;
      localStringBuilder = localStringBuilder.append(str);
      if (this.dataSource == null) {
        break label132;
      }
      str = ", " + this.dataSource;
      label81:
      localStringBuilder = localStringBuilder.append(str);
      if (this.key == null) {
        break label138;
      }
    }
    label132:
    label138:
    for (String str = ", " + this.key;; str = "")
    {
      return str;
      str = "";
      break;
      str = "";
      break label81;
    }
  }
  
  public List<Exception> getRootCauses()
  {
    ArrayList localArrayList = new ArrayList();
    addRootCauses(this, localArrayList);
    return localArrayList;
  }
  
  public void logRootCauses(String paramString)
  {
    Log.e(paramString, getClass() + ": " + getMessage());
    List localList = getRootCauses();
    int i = 0;
    int j = localList.size();
    while (i < j)
    {
      Log.i(paramString, "Root cause (" + (i + 1) + " of " + j + ")", (Throwable)localList.get(i));
      i += 1;
    }
  }
  
  public void printStackTrace()
  {
    printStackTrace(System.err);
  }
  
  public void printStackTrace(PrintStream paramPrintStream)
  {
    printStackTrace(paramPrintStream);
  }
  
  public void printStackTrace(PrintWriter paramPrintWriter)
  {
    printStackTrace(paramPrintWriter);
  }
  
  void setLoggingDetails(Key paramKey, DataSource paramDataSource)
  {
    setLoggingDetails(paramKey, paramDataSource, null);
  }
  
  void setLoggingDetails(Key paramKey, DataSource paramDataSource, Class<?> paramClass)
  {
    this.key = paramKey;
    this.dataSource = paramDataSource;
    this.dataClass = paramClass;
  }
  
  private static final class IndentedAppendable
    implements Appendable
  {
    private static final String EMPTY_SEQUENCE = "";
    private static final String INDENT = "  ";
    private final Appendable appendable;
    private boolean printedNewLine = true;
    
    IndentedAppendable(Appendable paramAppendable)
    {
      this.appendable = paramAppendable;
    }
    
    @NonNull
    private CharSequence safeSequence(@Nullable CharSequence paramCharSequence)
    {
      Object localObject = paramCharSequence;
      if (paramCharSequence == null) {
        localObject = "";
      }
      return (CharSequence)localObject;
    }
    
    public Appendable append(char paramChar)
      throws IOException
    {
      boolean bool = false;
      if (this.printedNewLine)
      {
        this.printedNewLine = false;
        this.appendable.append("  ");
      }
      if (paramChar == '\n') {
        bool = true;
      }
      this.printedNewLine = bool;
      this.appendable.append(paramChar);
      return this;
    }
    
    public Appendable append(@Nullable CharSequence paramCharSequence)
      throws IOException
    {
      paramCharSequence = safeSequence(paramCharSequence);
      return append(paramCharSequence, 0, paramCharSequence.length());
    }
    
    public Appendable append(@Nullable CharSequence paramCharSequence, int paramInt1, int paramInt2)
      throws IOException
    {
      boolean bool2 = false;
      paramCharSequence = safeSequence(paramCharSequence);
      if (this.printedNewLine)
      {
        this.printedNewLine = false;
        this.appendable.append("  ");
      }
      boolean bool1 = bool2;
      if (paramCharSequence.length() > 0)
      {
        bool1 = bool2;
        if (paramCharSequence.charAt(paramInt2 - 1) == '\n') {
          bool1 = true;
        }
      }
      this.printedNewLine = bool1;
      this.appendable.append(paramCharSequence, paramInt1, paramInt2);
      return this;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\engine\GlideException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */