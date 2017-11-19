package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.Util;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class DiskLruCache
  implements Closeable, Flushable
{
  static final long ANY_SEQUENCE_NUMBER = -1L;
  private static final String CLEAN = "CLEAN";
  private static final String DIRTY = "DIRTY";
  static final String JOURNAL_FILE = "journal";
  static final String JOURNAL_FILE_BACKUP = "journal.bkp";
  static final String JOURNAL_FILE_TEMP = "journal.tmp";
  static final Pattern LEGAL_KEY_PATTERN;
  static final String MAGIC = "libcore.io.DiskLruCache";
  private static final Sink NULL_SINK;
  private static final String READ = "READ";
  private static final String REMOVE = "REMOVE";
  static final String VERSION_1 = "1";
  private final int appVersion;
  private final Runnable cleanupRunnable = new Runnable()
  {
    public void run()
    {
      int i = 1;
      synchronized (DiskLruCache.this)
      {
        if (!DiskLruCache.this.initialized)
        {
          if ((i | DiskLruCache.this.closed) == 0) {}
        }
        else {
          i = 0;
        }
      }
      for (;;)
      {
        try
        {
          DiskLruCache.this.trimToSize();
          try
          {
            if (DiskLruCache.this.journalRebuildRequired())
            {
              DiskLruCache.this.rebuildJournal();
              DiskLruCache.access$602(DiskLruCache.this, 0);
            }
            return;
          }
          catch (IOException localIOException2)
          {
            for (;;) {}
          }
          localObject = finally;
          throw ((Throwable)localObject);
        }
        catch (IOException localIOException1)
        {
          DiskLruCache.access$302(DiskLruCache.this, true);
          continue;
        }
        DiskLruCache.access$702(DiskLruCache.this, true);
        DiskLruCache.access$802(DiskLruCache.this, Okio.buffer(DiskLruCache.NULL_SINK));
      }
    }
  };
  private boolean closed;
  private final File directory;
  private final Executor executor;
  private final FileSystem fileSystem;
  private boolean hasJournalErrors;
  private boolean initialized;
  private final File journalFile;
  private final File journalFileBackup;
  private final File journalFileTmp;
  private BufferedSink journalWriter;
  private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap(0, 0.75F, true);
  private long maxSize;
  private boolean mostRecentRebuildFailed;
  private boolean mostRecentTrimFailed;
  private long nextSequenceNumber = 0L;
  private int redundantOpCount;
  private long size = 0L;
  private final int valueCount;
  
  static
  {
    if (!DiskLruCache.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
      NULL_SINK = new Sink()
      {
        public void close()
          throws IOException
        {}
        
        public void flush()
          throws IOException
        {}
        
        public Timeout timeout()
        {
          return Timeout.NONE;
        }
        
        public void write(Buffer paramAnonymousBuffer, long paramAnonymousLong)
          throws IOException
        {
          paramAnonymousBuffer.skip(paramAnonymousLong);
        }
      };
      return;
    }
  }
  
  DiskLruCache(FileSystem paramFileSystem, File paramFile, int paramInt1, int paramInt2, long paramLong, Executor paramExecutor)
  {
    this.fileSystem = paramFileSystem;
    this.directory = paramFile;
    this.appVersion = paramInt1;
    this.journalFile = new File(paramFile, "journal");
    this.journalFileTmp = new File(paramFile, "journal.tmp");
    this.journalFileBackup = new File(paramFile, "journal.bkp");
    this.valueCount = paramInt2;
    this.maxSize = paramLong;
    this.executor = paramExecutor;
  }
  
  private void checkNotClosed()
  {
    try
    {
      if (isClosed()) {
        throw new IllegalStateException("cache is closed");
      }
    }
    finally {}
  }
  
  private void completeEdit(Editor paramEditor, boolean paramBoolean)
    throws IOException
  {
    Entry localEntry;
    try
    {
      localEntry = paramEditor.entry;
      if (localEntry.currentEditor != paramEditor) {
        throw new IllegalStateException();
      }
    }
    finally {}
    int i;
    label129:
    label148:
    long l1;
    if ((paramBoolean) && (!localEntry.readable))
    {
      break label463;
      while (i < this.valueCount)
      {
        if (paramEditor.written[i] == 0)
        {
          paramEditor.abort();
          throw new IllegalStateException("Newly created entry didn't create value for index " + i);
        }
        if (!this.fileSystem.exists(localEntry.dirtyFiles[i]))
        {
          paramEditor.abort();
          return;
        }
        i += 1;
      }
    }
    else
    {
      i = 0;
      if (i < this.valueCount)
      {
        paramEditor = localEntry.dirtyFiles[i];
        break label468;
        if (!this.fileSystem.exists(paramEditor)) {
          break label475;
        }
        File localFile = localEntry.cleanFiles[i];
        this.fileSystem.rename(paramEditor, localFile);
        l1 = localEntry.lengths[i];
        long l2 = this.fileSystem.size(localFile);
        localEntry.lengths[i] = l2;
        this.size = (this.size - l1 + l2);
        break label475;
        label230:
        this.fileSystem.delete(paramEditor);
        break label475;
      }
      this.redundantOpCount += 1;
      Entry.access$1302(localEntry, null);
      if ((localEntry.readable | paramBoolean))
      {
        Entry.access$1202(localEntry, true);
        this.journalWriter.writeUtf8("CLEAN").writeByte(32);
        this.journalWriter.writeUtf8(localEntry.key);
        localEntry.writeLengths(this.journalWriter);
        this.journalWriter.writeByte(10);
        break label482;
      }
    }
    for (;;)
    {
      l1 = this.nextSequenceNumber;
      this.nextSequenceNumber = (1L + l1);
      Entry.access$2002(localEntry, l1);
      label463:
      label468:
      label475:
      label482:
      do
      {
        for (;;)
        {
          this.journalWriter.flush();
          if ((this.size <= this.maxSize) && (!journalRebuildRequired())) {
            break;
          }
          this.executor.execute(this.cleanupRunnable);
          break;
          this.lruEntries.remove(localEntry.key);
          this.journalWriter.writeUtf8("REMOVE").writeByte(32);
          this.journalWriter.writeUtf8(localEntry.key);
          this.journalWriter.writeByte(10);
        }
        i = 0;
        break;
        if (!paramBoolean) {
          break label230;
        }
        break label148;
        i += 1;
        break label129;
      } while (!paramBoolean);
    }
  }
  
  public static DiskLruCache create(FileSystem paramFileSystem, File paramFile, int paramInt1, int paramInt2, long paramLong)
  {
    if (paramLong <= 0L) {
      throw new IllegalArgumentException("maxSize <= 0");
    }
    if (paramInt2 <= 0) {
      throw new IllegalArgumentException("valueCount <= 0");
    }
    return new DiskLruCache(paramFileSystem, paramFile, paramInt1, paramInt2, paramLong, new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true)));
  }
  
  private Editor edit(String paramString, long paramLong)
    throws IOException
  {
    Object localObject2 = null;
    Entry localEntry;
    try
    {
      initialize();
      checkNotClosed();
      validateKey(paramString);
      localEntry = (Entry)this.lruEntries.get(paramString);
    }
    finally {}
    long l = localEntry.sequenceNumber;
    Object localObject1;
    if (l != paramLong) {
      localObject1 = localObject2;
    }
    for (;;)
    {
      label52:
      return (Editor)localObject1;
      label57:
      if (localEntry != null)
      {
        localObject1 = localObject2;
        if (localEntry.currentEditor != null) {
          break;
        }
      }
      else if ((this.mostRecentTrimFailed) || (this.mostRecentRebuildFailed))
      {
        this.executor.execute(this.cleanupRunnable);
        localObject1 = localObject2;
      }
      else
      {
        this.journalWriter.writeUtf8("DIRTY").writeByte(32).writeUtf8(paramString).writeByte(10);
        this.journalWriter.flush();
        localObject1 = localObject2;
        if (!this.hasJournalErrors) {
          break;
        }
      }
    }
    for (;;)
    {
      localObject1 = new Entry(paramString, null);
      this.lruEntries.put(paramString, localObject1);
      do
      {
        paramString = new Editor((Entry)localObject1, null);
        Entry.access$1302((Entry)localObject1, paramString);
        localObject1 = paramString;
        break label52;
        if (paramLong == -1L) {
          break label57;
        }
        localObject1 = localObject2;
        if (localEntry == null) {
          break label52;
        }
        break;
        localObject1 = localEntry;
      } while (localEntry != null);
    }
  }
  
  private boolean journalRebuildRequired()
  {
    return (this.redundantOpCount >= 2000) && (this.redundantOpCount >= this.lruEntries.size());
  }
  
  private BufferedSink newJournalWriter()
    throws FileNotFoundException
  {
    Okio.buffer(new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile))
    {
      static
      {
        if (!DiskLruCache.class.desiredAssertionStatus()) {}
        for (boolean bool = true;; bool = false)
        {
          $assertionsDisabled = bool;
          return;
        }
      }
      
      protected void onException(IOException paramAnonymousIOException)
      {
        assert (Thread.holdsLock(DiskLruCache.this));
        DiskLruCache.access$1002(DiskLruCache.this, true);
      }
    });
  }
  
  private void processJournal()
    throws IOException
  {
    this.fileSystem.delete(this.journalFileTmp);
    Iterator localIterator = this.lruEntries.values().iterator();
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      int i;
      if (localEntry.currentEditor == null)
      {
        i = 0;
        while (i < this.valueCount)
        {
          this.size += localEntry.lengths[i];
          i += 1;
        }
      }
      else
      {
        Entry.access$1302(localEntry, null);
        i = 0;
        while (i < this.valueCount)
        {
          this.fileSystem.delete(localEntry.cleanFiles[i]);
          this.fileSystem.delete(localEntry.dirtyFiles[i]);
          i += 1;
        }
        localIterator.remove();
      }
    }
  }
  
  private void readJournal()
    throws IOException
  {
    BufferedSource localBufferedSource = Okio.buffer(this.fileSystem.source(this.journalFile));
    label241:
    try
    {
      String str1 = localBufferedSource.readUtf8LineStrict();
      String str2 = localBufferedSource.readUtf8LineStrict();
      String str3 = localBufferedSource.readUtf8LineStrict();
      String str4 = localBufferedSource.readUtf8LineStrict();
      String str5 = localBufferedSource.readUtf8LineStrict();
      if ((!"libcore.io.DiskLruCache".equals(str1)) || (!"1".equals(str2)) || (!Integer.toString(this.appVersion).equals(str3)) || (!Integer.toString(this.valueCount).equals(str4)) || (!"".equals(str5))) {
        throw new IOException("unexpected journal header: [" + str1 + ", " + str2 + ", " + str4 + ", " + str5 + "]");
      }
    }
    finally
    {
      Util.closeQuietly(localBufferedSource);
      throw ((Throwable)localObject);
      int i = 0;
      try
      {
        for (;;)
        {
          readJournalLine(localBufferedSource.readUtf8LineStrict());
          i += 1;
        }
        rebuildJournal();
      }
      catch (EOFException localEOFException)
      {
        this.redundantOpCount = (i - this.lruEntries.size());
        if (localBufferedSource.exhausted()) {
          break label241;
        }
      }
      Util.closeQuietly(localBufferedSource);
      return;
    }
  }
  
  private void readJournalLine(String paramString)
    throws IOException
  {
    int i = paramString.indexOf(' ');
    if (i == -1) {
      throw new IOException("unexpected journal line: " + paramString);
    }
    int j = i + 1;
    int k = paramString.indexOf(' ', j);
    Object localObject2;
    Object localObject1;
    if (k == -1)
    {
      localObject2 = paramString.substring(j);
      localObject1 = localObject2;
      if (i != "REMOVE".length()) {
        break label112;
      }
      localObject1 = localObject2;
      if (!paramString.startsWith("REMOVE")) {
        break label112;
      }
      this.lruEntries.remove(localObject2);
    }
    label112:
    do
    {
      return;
      localObject1 = paramString.substring(j, k);
      Entry localEntry = (Entry)this.lruEntries.get(localObject1);
      localObject2 = localEntry;
      if (localEntry == null)
      {
        localObject2 = new Entry((String)localObject1, null);
        this.lruEntries.put(localObject1, localObject2);
      }
      if ((k != -1) && (i == "CLEAN".length()) && (paramString.startsWith("CLEAN")))
      {
        paramString = paramString.substring(k + 1).split(" ");
        Entry.access$1202((Entry)localObject2, true);
        Entry.access$1302((Entry)localObject2, null);
        ((Entry)localObject2).setLengths(paramString);
        return;
      }
      if ((k == -1) && (i == "DIRTY".length()) && (paramString.startsWith("DIRTY")))
      {
        Entry.access$1302((Entry)localObject2, new Editor((Entry)localObject2, null));
        return;
      }
    } while ((k == -1) && (i == "READ".length()) && (paramString.startsWith("READ")));
    throw new IOException("unexpected journal line: " + paramString);
  }
  
  private void rebuildJournal()
    throws IOException
  {
    for (;;)
    {
      Entry localEntry;
      try
      {
        if (this.journalWriter != null) {
          this.journalWriter.close();
        }
        BufferedSink localBufferedSink1 = Okio.buffer(this.fileSystem.sink(this.journalFileTmp));
        try
        {
          localBufferedSink1.writeUtf8("libcore.io.DiskLruCache").writeByte(10);
          localBufferedSink1.writeUtf8("1").writeByte(10);
          localBufferedSink1.writeDecimalLong(this.appVersion).writeByte(10);
          localBufferedSink1.writeDecimalLong(this.valueCount).writeByte(10);
          localBufferedSink1.writeByte(10);
          Iterator localIterator = this.lruEntries.values().iterator();
          if (!localIterator.hasNext()) {
            break;
          }
          localEntry = (Entry)localIterator.next();
          if (localEntry.currentEditor != null)
          {
            localBufferedSink1.writeUtf8("DIRTY").writeByte(32);
            localBufferedSink1.writeUtf8(localEntry.key);
            localBufferedSink1.writeByte(10);
            continue;
            localBufferedSink2 = finally;
          }
        }
        finally
        {
          localBufferedSink1.close();
        }
        localBufferedSink2.writeUtf8("CLEAN").writeByte(32);
      }
      finally {}
      localBufferedSink2.writeUtf8(localEntry.key);
      localEntry.writeLengths(localBufferedSink2);
      localBufferedSink2.writeByte(10);
    }
    localBufferedSink2.close();
    if (this.fileSystem.exists(this.journalFile)) {
      this.fileSystem.rename(this.journalFile, this.journalFileBackup);
    }
    this.fileSystem.rename(this.journalFileTmp, this.journalFile);
    this.fileSystem.delete(this.journalFileBackup);
    this.journalWriter = newJournalWriter();
    this.hasJournalErrors = false;
    this.mostRecentRebuildFailed = false;
  }
  
  private boolean removeEntry(Entry paramEntry)
    throws IOException
  {
    if (paramEntry.currentEditor != null) {
      paramEntry.currentEditor.detach();
    }
    int i = 0;
    while (i < this.valueCount)
    {
      this.fileSystem.delete(paramEntry.cleanFiles[i]);
      this.size -= paramEntry.lengths[i];
      paramEntry.lengths[i] = 0L;
      i += 1;
    }
    this.redundantOpCount += 1;
    this.journalWriter.writeUtf8("REMOVE").writeByte(32).writeUtf8(paramEntry.key).writeByte(10);
    this.lruEntries.remove(paramEntry.key);
    if (journalRebuildRequired()) {
      this.executor.execute(this.cleanupRunnable);
    }
    return true;
  }
  
  private void trimToSize()
    throws IOException
  {
    while (this.size > this.maxSize) {
      removeEntry((Entry)this.lruEntries.values().iterator().next());
    }
    this.mostRecentTrimFailed = false;
  }
  
  private void validateKey(String paramString)
  {
    if (!LEGAL_KEY_PATTERN.matcher(paramString).matches()) {
      throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + paramString + "\"");
    }
  }
  
  public void close()
    throws IOException
  {
    int j;
    int i;
    Entry localEntry;
    try
    {
      if ((!this.initialized) || (this.closed))
      {
        this.closed = true;
        return;
      }
      Entry[] arrayOfEntry = (Entry[])this.lruEntries.values().toArray(new Entry[this.lruEntries.size()]);
      j = arrayOfEntry.length;
      i = 0;
    }
    finally {}
    if (localEntry.currentEditor != null) {
      localEntry.currentEditor.abort();
    }
    label126:
    for (;;)
    {
      trimToSize();
      this.journalWriter.close();
      this.journalWriter = null;
      this.closed = true;
      break;
      for (;;)
      {
        if (i >= j) {
          break label126;
        }
        localEntry = localObject[i];
        break;
        i += 1;
      }
    }
  }
  
  public void delete()
    throws IOException
  {
    close();
    this.fileSystem.deleteContents(this.directory);
  }
  
  public Editor edit(String paramString)
    throws IOException
  {
    return edit(paramString, -1L);
  }
  
  public void evictAll()
    throws IOException
  {
    int i = 0;
    int j;
    Entry localEntry;
    try
    {
      initialize();
      Entry[] arrayOfEntry = (Entry[])this.lruEntries.values().toArray(new Entry[this.lruEntries.size()]);
      j = arrayOfEntry.length;
    }
    finally {}
    removeEntry(localEntry);
    label81:
    for (;;)
    {
      this.mostRecentTrimFailed = false;
      return;
      for (;;)
      {
        if (i >= j) {
          break label81;
        }
        localEntry = localObject[i];
        break;
        i += 1;
      }
    }
  }
  
  /* Error */
  public void flush()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 161	okhttp3/internal/cache/DiskLruCache:initialized	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifne +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: invokespecial 382	okhttp3/internal/cache/DiskLruCache:checkNotClosed	()V
    //   18: aload_0
    //   19: invokespecial 174	okhttp3/internal/cache/DiskLruCache:trimToSize	()V
    //   22: aload_0
    //   23: getfield 223	okhttp3/internal/cache/DiskLruCache:journalWriter	Lokio/BufferedSink;
    //   26: invokeinterface 332 1 0
    //   31: goto -20 -> 11
    //   34: astore_2
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_2
    //   38: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	39	0	this	DiskLruCache
    //   6	2	1	bool	boolean
    //   34	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	34	finally
    //   14	31	34	finally
  }
  
  public Snapshot get(String paramString)
    throws IOException
  {
    for (;;)
    {
      Object localObject;
      try
      {
        initialize();
        checkNotClosed();
        validateKey(paramString);
        localObject = (Entry)this.lruEntries.get(paramString);
        if (localObject != null)
        {
          boolean bool = ((Entry)localObject).readable;
          if (bool) {}
        }
        else
        {
          paramString = null;
          return paramString;
        }
        localObject = ((Entry)localObject).snapshot();
      }
      finally {}
      this.redundantOpCount += 1;
      this.journalWriter.writeUtf8("READ").writeByte(32).writeUtf8(paramString).writeByte(10);
      paramString = (String)localObject;
      if (journalRebuildRequired())
      {
        this.executor.execute(this.cleanupRunnable);
        paramString = (String)localObject;
        continue;
        if (localObject == null) {
          paramString = null;
        }
      }
    }
  }
  
  public File getDirectory()
  {
    return this.directory;
  }
  
  public long getMaxSize()
  {
    try
    {
      long l = this.maxSize;
      return l;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void initialize()
    throws IOException
  {
    try
    {
      if ((!$assertionsDisabled) && (!Thread.holdsLock(this))) {
        throw new AssertionError();
      }
    }
    finally {}
    boolean bool = this.initialized;
    if (bool) {
      return;
    }
    if (this.fileSystem.exists(this.journalFileBackup))
    {
      if (!this.fileSystem.exists(this.journalFile)) {
        break label191;
      }
      this.fileSystem.delete(this.journalFileBackup);
    }
    for (;;)
    {
      bool = this.fileSystem.exists(this.journalFile);
      if (bool) {
        try
        {
          readJournal();
          processJournal();
          this.initialized = true;
        }
        catch (IOException localIOException)
        {
          Platform.get().log(5, "DiskLruCache " + this.directory + " is corrupt: " + localIOException.getMessage() + ", removing", localIOException);
          delete();
          this.closed = false;
        }
      }
      rebuildJournal();
      this.initialized = true;
      break;
      label191:
      this.fileSystem.rename(this.journalFileBackup, this.journalFile);
    }
  }
  
  public boolean isClosed()
  {
    try
    {
      boolean bool = this.closed;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public boolean remove(String paramString)
    throws IOException
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: invokevirtual 380	okhttp3/internal/cache/DiskLruCache:initialize	()V
    //   8: aload_0
    //   9: invokespecial 382	okhttp3/internal/cache/DiskLruCache:checkNotClosed	()V
    //   12: aload_0
    //   13: aload_1
    //   14: invokespecial 385	okhttp3/internal/cache/DiskLruCache:validateKey	(Ljava/lang/String;)V
    //   17: aload_0
    //   18: getfield 127	okhttp3/internal/cache/DiskLruCache:lruEntries	Ljava/util/LinkedHashMap;
    //   21: aload_1
    //   22: invokevirtual 388	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   25: checkcast 23	okhttp3/internal/cache/DiskLruCache$Entry
    //   28: astore_1
    //   29: aload_1
    //   30: ifnonnull +7 -> 37
    //   33: aload_0
    //   34: monitorexit
    //   35: iload_2
    //   36: ireturn
    //   37: aload_0
    //   38: aload_1
    //   39: invokespecial 204	okhttp3/internal/cache/DiskLruCache:removeEntry	(Lokhttp3/internal/cache/DiskLruCache$Entry;)Z
    //   42: istore_3
    //   43: iload_3
    //   44: istore_2
    //   45: iload_3
    //   46: ifeq -13 -> 33
    //   49: iload_3
    //   50: istore_2
    //   51: aload_0
    //   52: getfield 119	okhttp3/internal/cache/DiskLruCache:size	J
    //   55: aload_0
    //   56: getfield 155	okhttp3/internal/cache/DiskLruCache:maxSize	J
    //   59: lcmp
    //   60: ifgt -27 -> 33
    //   63: aload_0
    //   64: iconst_0
    //   65: putfield 198	okhttp3/internal/cache/DiskLruCache:mostRecentTrimFailed	Z
    //   68: iload_3
    //   69: istore_2
    //   70: goto -37 -> 33
    //   73: astore_1
    //   74: aload_0
    //   75: monitorexit
    //   76: aload_1
    //   77: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	78	0	this	DiskLruCache
    //   0	78	1	paramString	String
    //   1	69	2	bool1	boolean
    //   42	27	3	bool2	boolean
    // Exception table:
    //   from	to	target	type
    //   4	29	73	finally
    //   37	43	73	finally
    //   51	68	73	finally
  }
  
  public void setMaxSize(long paramLong)
  {
    try
    {
      this.maxSize = paramLong;
      if (this.initialized) {
        this.executor.execute(this.cleanupRunnable);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public long size()
    throws IOException
  {
    try
    {
      initialize();
      long l = this.size;
      return l;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public Iterator<Snapshot> snapshots()
    throws IOException
  {
    try
    {
      initialize();
      Iterator local3 = new Iterator()
      {
        final Iterator<DiskLruCache.Entry> delegate = new ArrayList(DiskLruCache.this.lruEntries.values()).iterator();
        DiskLruCache.Snapshot nextSnapshot;
        DiskLruCache.Snapshot removeSnapshot;
        
        public boolean hasNext()
        {
          if (this.nextSnapshot != null) {
            return true;
          }
          for (;;)
          {
            synchronized (DiskLruCache.this)
            {
              if (DiskLruCache.this.closed) {
                return false;
              }
              if (this.delegate.hasNext())
              {
                DiskLruCache.Snapshot localSnapshot = ((DiskLruCache.Entry)this.delegate.next()).snapshot();
                break label79;
                this.nextSnapshot = localSnapshot;
                return true;
              }
            }
            return false;
            label79:
            if (localObject == null) {}
          }
        }
        
        public DiskLruCache.Snapshot next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          this.removeSnapshot = this.nextSnapshot;
          this.nextSnapshot = null;
          return this.removeSnapshot;
        }
        
        public void remove()
        {
          if (this.removeSnapshot == null) {
            throw new IllegalStateException("remove() before next()");
          }
          try
          {
            DiskLruCache.this.remove(DiskLruCache.Snapshot.access$2400(this.removeSnapshot));
            this.removeSnapshot = null;
            return;
          }
          catch (IOException localIOException)
          {
            localIOException = localIOException;
            this.removeSnapshot = null;
            return;
          }
          finally
          {
            localObject = finally;
            this.removeSnapshot = null;
            throw ((Throwable)localObject);
          }
        }
      };
      return local3;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public final class Editor
  {
    private boolean done;
    private final DiskLruCache.Entry entry;
    private final boolean[] written;
    
    private Editor(DiskLruCache.Entry paramEntry)
    {
      this.entry = paramEntry;
      if (DiskLruCache.Entry.access$1200(paramEntry)) {}
      for (this$1 = null;; this$1 = new boolean[DiskLruCache.this.valueCount])
      {
        this.written = DiskLruCache.this;
        return;
      }
    }
    
    public void abort()
      throws IOException
    {
      synchronized (DiskLruCache.this)
      {
        if (this.done) {
          throw new IllegalStateException();
        }
      }
      if (DiskLruCache.Entry.access$1300(this.entry) == this) {
        DiskLruCache.this.completeEdit(this, false);
      }
      this.done = true;
    }
    
    public void abortUnlessCommitted()
    {
      synchronized (DiskLruCache.this)
      {
        if (!this.done)
        {
          Editor localEditor = DiskLruCache.Entry.access$1300(this.entry);
          if (localEditor != this) {}
        }
      }
      try
      {
        DiskLruCache.this.completeEdit(this, false);
        return;
        localObject = finally;
        throw ((Throwable)localObject);
      }
      catch (IOException localIOException)
      {
        for (;;) {}
      }
    }
    
    public void commit()
      throws IOException
    {
      synchronized (DiskLruCache.this)
      {
        if (this.done) {
          throw new IllegalStateException();
        }
      }
      if (DiskLruCache.Entry.access$1300(this.entry) == this) {
        DiskLruCache.this.completeEdit(this, true);
      }
      this.done = true;
    }
    
    void detach()
    {
      int i;
      if (DiskLruCache.Entry.access$1300(this.entry) == this) {
        i = 0;
      }
      for (;;)
      {
        if (i < DiskLruCache.this.valueCount) {}
        try
        {
          DiskLruCache.this.fileSystem.delete(DiskLruCache.Entry.access$1800(this.entry)[i]);
          i += 1;
          continue;
          DiskLruCache.Entry.access$1302(this.entry, null);
          return;
        }
        catch (IOException localIOException)
        {
          for (;;) {}
        }
      }
    }
    
    public Sink newSink(int paramInt)
    {
      synchronized (DiskLruCache.this)
      {
        if (this.done) {
          throw new IllegalStateException();
        }
      }
      if (DiskLruCache.Entry.access$1300(this.entry) != this)
      {
        localObject2 = DiskLruCache.NULL_SINK;
        return (Sink)localObject2;
      }
      if (!DiskLruCache.Entry.access$1200(this.entry)) {
        this.written[paramInt] = true;
      }
      Object localObject2 = DiskLruCache.Entry.access$1800(this.entry)[paramInt];
      try
      {
        localObject2 = DiskLruCache.this.fileSystem.sink((File)localObject2);
        localObject2 = new FaultHidingSink((Sink)localObject2)
        {
          protected void onException(IOException arg1)
          {
            synchronized (DiskLruCache.this)
            {
              DiskLruCache.Editor.this.detach();
              return;
            }
          }
        };
        return (Sink)localObject2;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        for (;;) {}
      }
      localObject2 = DiskLruCache.NULL_SINK;
      return (Sink)localObject2;
    }
    
    public Source newSource(int paramInt)
    {
      synchronized (DiskLruCache.this)
      {
        if (this.done) {
          throw new IllegalStateException();
        }
      }
      if ((!DiskLruCache.Entry.access$1200(this.entry)) || (DiskLruCache.Entry.access$1300(this.entry) != this)) {
        return null;
      }
      try
      {
        Source localSource = DiskLruCache.this.fileSystem.source(DiskLruCache.Entry.access$1700(this.entry)[paramInt]);
        return localSource;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        for (;;) {}
      }
      return null;
    }
  }
  
  private final class Entry
  {
    private final File[] cleanFiles;
    private DiskLruCache.Editor currentEditor;
    private final File[] dirtyFiles;
    private final String key;
    private final long[] lengths;
    private boolean readable;
    private long sequenceNumber;
    
    private Entry(String paramString)
    {
      this.key = paramString;
      this.lengths = new long[DiskLruCache.this.valueCount];
      this.cleanFiles = new File[DiskLruCache.this.valueCount];
      this.dirtyFiles = new File[DiskLruCache.this.valueCount];
      paramString = new StringBuilder(paramString).append('.');
      int j = paramString.length();
      int i = 0;
      while (i < DiskLruCache.this.valueCount)
      {
        paramString.append(i);
        this.cleanFiles[i] = new File(DiskLruCache.this.directory, paramString.toString());
        paramString.append(".tmp");
        this.dirtyFiles[i] = new File(DiskLruCache.this.directory, paramString.toString());
        paramString.setLength(j);
        i += 1;
      }
    }
    
    private IOException invalidLengths(String[] paramArrayOfString)
      throws IOException
    {
      throw new IOException("unexpected journal line: " + Arrays.toString(paramArrayOfString));
    }
    
    private void setLengths(String[] paramArrayOfString)
      throws IOException
    {
      if (paramArrayOfString.length != DiskLruCache.this.valueCount) {
        throw invalidLengths(paramArrayOfString);
      }
      int i = 0;
      try
      {
        while (i < paramArrayOfString.length)
        {
          this.lengths[i] = Long.parseLong(paramArrayOfString[i]);
          i += 1;
        }
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw invalidLengths(paramArrayOfString);
      }
    }
    
    DiskLruCache.Snapshot snapshot()
    {
      if (!Thread.holdsLock(DiskLruCache.this)) {
        throw new AssertionError();
      }
      Source[] arrayOfSource = new Source[DiskLruCache.this.valueCount];
      Object localObject = (long[])this.lengths.clone();
      int i = 0;
      for (;;)
      {
        try
        {
          if (i < DiskLruCache.this.valueCount)
          {
            arrayOfSource[i] = DiskLruCache.this.fileSystem.source(this.cleanFiles[i]);
          }
          else
          {
            localObject = new DiskLruCache.Snapshot(DiskLruCache.this, this.key, this.sequenceNumber, arrayOfSource, (long[])localObject, null);
            return (DiskLruCache.Snapshot)localObject;
          }
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          i = 0;
          if ((i < DiskLruCache.this.valueCount) && (arrayOfSource[i] != null))
          {
            Util.closeQuietly(arrayOfSource[i]);
            i += 1;
          }
          else
          {
            try
            {
              DiskLruCache.this.removeEntry(this);
              return null;
            }
            catch (IOException localIOException)
            {
              continue;
            }
            i += 1;
          }
        }
      }
    }
    
    void writeLengths(BufferedSink paramBufferedSink)
      throws IOException
    {
      long[] arrayOfLong = this.lengths;
      int j = arrayOfLong.length;
      int i = 0;
      while (i < j)
      {
        long l = arrayOfLong[i];
        paramBufferedSink.writeByte(32).writeDecimalLong(l);
        i += 1;
      }
    }
  }
  
  public final class Snapshot
    implements Closeable
  {
    private final String key;
    private final long[] lengths;
    private final long sequenceNumber;
    private final Source[] sources;
    
    private Snapshot(String paramString, long paramLong, Source[] paramArrayOfSource, long[] paramArrayOfLong)
    {
      this.key = paramString;
      this.sequenceNumber = paramLong;
      this.sources = paramArrayOfSource;
      this.lengths = paramArrayOfLong;
    }
    
    public void close()
    {
      Source[] arrayOfSource = this.sources;
      int j = arrayOfSource.length;
      int i = 0;
      while (i < j)
      {
        Util.closeQuietly(arrayOfSource[i]);
        i += 1;
      }
    }
    
    public DiskLruCache.Editor edit()
      throws IOException
    {
      return DiskLruCache.this.edit(this.key, this.sequenceNumber);
    }
    
    public long getLength(int paramInt)
    {
      return this.lengths[paramInt];
    }
    
    public Source getSource(int paramInt)
    {
      return this.sources[paramInt];
    }
    
    public String key()
    {
      return this.key;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\internal\cache\DiskLruCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */